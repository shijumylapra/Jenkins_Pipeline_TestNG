package TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import com.google.common.base.Ascii;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaseClass {
    public static WebDriver Wdriver, Rdriver;
    public XSSFWorkbook wbook;
    public XSSFSheet sheet;
    private ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private void SetDriver(WebDriver driver) {threadLocalDriver.set(driver); }
    private WebDriver GetDriver() {return threadLocalDriver.get(); }

    @BeforeMethod(alwaysRun = true)
    public void SetUpDriver() throws MalformedURLException {
        local_driver_setup();
        //remote_driver_setup();
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown() {
        Wdriver.close();
    }

    @BeforeTest(alwaysRun = true)
    public void SetUpExcel() throws IOException {
        //FileInputStream will allow to read xl data
        FileInputStream fis = new FileInputStream("user.xlsx");
        //fis object will pass it to constructor of XSSFWorkbook
        wbook = new XSSFWorkbook(fis);
        sheet = wbook.getSheet("Sheet1");

    }

    @AfterTest(alwaysRun = true)
    public void CloseExcel() throws IOException {
        wbook.close();
    }
    public void local_driver_setup()
    {
        String browser = System.getProperty("Browser");
        Wdriver = new ChromeDriver();
        Wdriver.get("https://simplilearn.com/");
        Wdriver.manage().window().maximize();
        Wdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    void remote_driver_setup() throws MalformedURLException {
        {
            String browser = System.getProperty("Browser");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-using");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--headless");
            options.addArguments("--ignore-ssl-errors=yes");
            options.addArguments("--ignore-certificate-errors");

            if (browser.equalsIgnoreCase("firefox")) {
                Rdriver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("remote-chrome")) {

                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setPlatform(Platform.WIN10);
                cap.setBrowserName("chrome");

                URL hub = new URL("http://localhost:4444/");
                Rdriver = new RemoteWebDriver(hub, cap);

            } else {
                Rdriver = new ChromeDriver(options);
            }

            SetDriver(Rdriver);
            GetDriver().get("https://simplilearn.com/");
            GetDriver().manage().window().maximize();
            GetDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

}



