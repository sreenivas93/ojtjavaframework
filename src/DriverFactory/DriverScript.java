package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.FunctionalLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript 
{


static WebDriver driver;
ExtentReports reports;
ExtentTest test;
private ExtentReports report;

@Test
public void ERP_Account() throws Throwable
{
//creating reference object for excel util methods
ExcelFileUtil xl=new ExcelFileUtil();
//iterating all row in master test cases sheet

for(int i=1;i<=xl.rowCount("MasterTestCases");i++)


{

if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
{
//store module name into  TCModule
String TCModule=xl.getCellData("MasterTestCases", i, 1);
//generate user define html report
report=new ExtentReports("./Reports//"+TCModule+FunctionalLibrary.generateDate()+".html");



//iterate all rows in TCModule sheet
for(int j=1;j<=xl.rowCount(TCModule);j++){

//to start test case here
test=report.startTest(TCModule);


//read all columns from tc module


String Description=xl.getCellData(TCModule, j, 0);
String Function_Name=xl.getCellData(TCModule, j, 1);
String Locator_Type=xl.getCellData(TCModule, j, 2);
       String Locator_Value=xl.getCellData(TCModule, j, 3);
String Test_Data=xl.getCellData(TCModule, j, 4);
try{
if(Function_Name.equalsIgnoreCase("startBrowser"))
{

driver=FunctionalLibrary.startBrowser();
test.log(LogStatus.INFO, Description);

}
else if (Function_Name.equalsIgnoreCase("openApplication")){
FunctionalLibrary.openApplication(driver);
test.log(LogStatus.INFO, Description);
}

else if (Function_Name.equalsIgnoreCase("waitForElement"))

{
FunctionalLibrary.waitElement(driver, Locator_Type, Locator_Value, Test_Data);
test.log(LogStatus.INFO, Description);
}
else if(Function_Name.equalsIgnoreCase("typeAction")){

FunctionalLibrary.typeaction(driver,Locator_Type,Locator_Value,Test_Data);
test.log(LogStatus.INFO, Description);

}
else if (Function_Name.equalsIgnoreCase("clickAction")){
FunctionalLibrary.clickAction(driver, Locator_Type, Locator_Value);
test.log(LogStatus.INFO, Description);
}

else if (Function_Name.equalsIgnoreCase("closeBrowser"))
{
FunctionalLibrary.closeBrowser(driver);
test.log(LogStatus.INFO, Description);
}

else if (Function_Name.equalsIgnoreCase("captureData")){
FunctionalLibrary.captureData(driver, Locator_Type, Locator_Value);
test.log(LogStatus.INFO, Description);

}
else if (Function_Name.equalsIgnoreCase("tableValidation"))
{
FunctionalLibrary.tablevalidation(driver, Test_Data);
test.log(LogStatus.INFO, Description);

}

//write as pass into status column
xl.setCellData(TCModule, j, 5, "pass");
xl.setCellData("MasterTestCases", i, 3, "pass");
test.log(LogStatus.PASS, Description);


}catch(Throwable t)
{

System.out.println("exception handle");
xl.setCellData(TCModule, j, 5,"Fail");

xl.setCellData("MasterTestCases", i, 3, "Fail");
test.log(LogStatus.FAIL, Description);
}
report.endTest(test);
report.flush();
}


}
else

{
// write  as not executed in status column in master test cases
xl.setCellData("MasterTestCases",i, 3, "Not Executed");
}
}




}



}

	
	
	

