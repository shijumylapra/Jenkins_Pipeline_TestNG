package LoginPages;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import TestCases.BaseClass;

public class UserLoginPage
{

    WebDriver driver = BaseClass.Wdriver;

   //==================== Locators ======================
    @FindBy(linkText = "Log in")
    WebElement LoginLink;

    @FindBy(name = "user_login")
    WebElement UserName;

    @FindBy(id = "password")
    WebElement Password;

    @FindBy(id = "remember-me")
    WebElement RememberMe;

    @FindBy(name = "btn_login")
    WebElement LoginBtn;

    @FindBy(className = "error_msg")
    WebElement Error;



    //====================== Functions =====================
    public UserLoginPage()
    {
        PageFactory.initElements(driver, this);
    }

    public void LoginFunction(String UserNameVal, String PwdVal) {

        LoginLink.click();
        UserName.sendKeys(UserNameVal);
        Password.sendKeys(PwdVal);
        RememberMe.click();
        LoginBtn.click();
    }

    public void ValidateErrorMsg(String ExpMsg) {
        String ActMsg = Error.getText();
        System.out.println(Error.getText());
        Assert.assertEquals(ExpMsg, ActMsg);

        System.out.println(Error.getText()+ " "+ExpMsg);
    }


}
