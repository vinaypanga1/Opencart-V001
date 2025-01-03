package utilities;

import org.testng.annotations.DataProvider;
import java.io.IOException;

public class Dataproviders {

	@DataProvider(name = "loginData")
	public Object[][] getExcelData() throws IOException {
		String excelFilePath = "C:/Users/hp/OneDrive/Desktop/Vinay files/Login_Data_Opencart.xlsx";
		ExcelUtility excel = new ExcelUtility(excelFilePath, "Sheet1");

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				data[i - 1][j] = excel.getCellData(i, j);
			}
		}

		excel.close();
		return data;
	}
}