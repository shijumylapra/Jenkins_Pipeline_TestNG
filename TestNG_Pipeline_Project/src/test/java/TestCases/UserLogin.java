package TestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import LoginPages.UserLoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Listeners(Listener.class) //Take screen short, something was wrong with any of the testcase
//listener class continuously listing with the test cases

public class UserLogin extends BaseClass
{
    UserLoginPage ULP = new UserLoginPage();

    @Test(groups = {"sanity"},description = "Login Failure Test")
    public void TC01_LoginFailureTest()
    {
        UserLoginPage ULP = new UserLoginPage();
        ULP.LoginFunction("loginfailure@gmail.com", "xdc@125");
       //ULP.ValidateErrorMsg("The email or password you have entered is invalid.");
        //ULP.ValidateErrorMsg("The email or password you have entered is invalid");
    }
    @Test(groups = {"sanity"},description = "Login success Test")
    public void TC02_LoginSuccessTest() {
        ULP = new UserLoginPage();
        ULP.LoginFunction("loginsuccess@gmail.com", "pass@1234");
    }

    @Test
    @Parameters({"param1","param2"}) //parameter pass from TestNG.xml file (Run this method as TestNG suit)
    public void TC03_Read_data_TestNG_XML_file(String Uname, String Pwd) {
        UserLoginPage ULP = new UserLoginPage();
        ULP.LoginFunction(Uname,Pwd);
    }

   Map<String,String> dptestData= new HashMap<String,String>();
   @Test(dataProvider = "DP")
    public void TC05_Read_data_from_Data_provider(String key) {
       UserLoginPage ULP = new UserLoginPage();
        ULP.LoginFunction(key,dptestData.get(key));
    }

    @DataProvider(name="DP")
    public Iterator<String> method1()
    {
        dptestData.put("user1@gmail.com","xyz1sss");
        dptestData.put("user2@gmail.com","xyz1aaa");
        return dptestData.keySet().iterator();
    }

@Test
    public void TC05_Read_data_from_excel() {
        String usrName = sheet.getRow(0).getCell(0).getStringCellValue();
        String Pass = sheet.getRow(0).getCell(1).getStringCellValue();
        UserLoginPage ULP = new UserLoginPage();
        ULP.LoginFunction(usrName, Pass);
    }

}
