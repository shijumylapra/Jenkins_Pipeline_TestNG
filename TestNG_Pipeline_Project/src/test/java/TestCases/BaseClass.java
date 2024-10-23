package TestCases;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import com.google.common.base.Ascii;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaseClass
{
    public static WebDriver Wdriver;
    public  XSSFWorkbook wbook;
    public  XSSFSheet sheet;

    @BeforeMethod(alwaysRun = true)
    //If alwaysRun = false) the group wull stop before and after method
    //alwways run =  true (added sanity group in the xml file)
    public void SetUpDriver()
    {
       // parameterize my browser
      //  mvn clean test -Dsurefire.suiteXmlFiles=sanity.xml -DBrowser=firefox
        String browser = System.getProperty("Browser");
//        if (browser.equalsIgnoreCase("firefox")) {
//            Wdriver = new FirefoxDriver();
//        }else {
//            Wdriver = new ChromeDriver();
//        }
        Wdriver = new ChromeDriver();
        Wdriver.get("https://simplilearn.com/");
        Wdriver.manage().window().maximize();
        Wdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown()
    {
        Wdriver.close();
    }
   @BeforeTest(alwaysRun = true)
   public void SetUpExcel() throws IOException
   {
        //FileInputStream will allow to read xl data
        FileInputStream fis = new FileInputStream("user.xlsx");
        //fis object will pass it to constructor of XSSFWorkbook
        wbook = new XSSFWorkbook(fis);
        sheet = wbook.getSheet("Sheet1");

    }
    @AfterTest(alwaysRun = true)
    public void CloseExcel() throws IOException
    {
        wbook.close();
    }

}

//String browser = "chrome";
//
//        if (browser.equalsIgnoreCase("firefox")) {
//Wdriver = new FirefoxDriver();
//        }else if(browser.equalsIgnoreCase("remote-chrome")) {
//
//DesiredCapabilities cap = new DesiredCapabilities();
//            cap.setPlatform(Platform.LINUX);
//            cap.setBrowserName("chrome");
//
//URL hub = new URL("http://localhost:4444/");
//Wdriver = new RemoteWebDriver(hub, cap);
//        }
//                else {
//Wdriver = new ChromeDriver();
//        }
//                //SetDriver(driver);
//                Wdriver.get("https://simplilearn.com/");
//        Wdriver.manage().window().maximize();