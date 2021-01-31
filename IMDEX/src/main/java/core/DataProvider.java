package core;

import com.codoid.products.exception.FilloException;

public class DataProvider {
	
	@org.testng.annotations.DataProvider(name="getBookPrice")
	public static Object [][] getBookPrice() throws FilloException 
	{
		String [][] propArray = null;
		String testDataSql = "select * from bookprice";

		ExcelDriver excelDriver = new ExcelDriver();
		excelDriver.setStrQuery(testDataSql);

		propArray = (String[][]) excelDriver.getTestData();

		return propArray;
	}
}
