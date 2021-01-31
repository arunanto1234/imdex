package core;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelDriver {

	private String strQuery;

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	

	public Object[][] getTestData() throws FilloException{

		int columnCnt = 0;
		int rowCnt =0;
		String [][] dataArray = null;
		Connection connection= null;
		Recordset recordset = null;
		try{

			Fillo fillo=new Fillo();
			connection=fillo.getConnection("test-data/testdata.xlsx");
			recordset=connection.executeQuery(strQuery);

			rowCnt = recordset.getCount();
			columnCnt = recordset.getFieldNames().size();

			dataArray = new String[rowCnt][columnCnt];
			//populate data structure
			for(int row=0; row<rowCnt; row++)
			{

				recordset.next();
				for(int col=0; col<columnCnt; col++) {

					dataArray[row][col] = recordset.getField(col).value(); 

				}
			}
		}catch(FilloException e){
			e.printStackTrace();

		}finally {

			if(!(connection == null)){
				recordset.close();
				connection.close();	
			}
			recordset.close();
			connection.close();	
		}

		return dataArray;

	}
}