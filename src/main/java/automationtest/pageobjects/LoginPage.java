package automationtest.pageobjects;

import automationtest.pagecomponents.BasePageClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePageClass {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail") WebElement username;
    @FindBy(id="userPassword") WebElement password;
    @FindBy(id="login") WebElement loginbutton;
    @FindBy(css="[class*='flyInOut']") WebElement errorMessage;

    public HomePage loginToApp(String uname, String pwd){
        username.sendKeys(uname);
        password.sendKeys(pwd);
        loginbutton.click();
        HomePage homepage = new HomePage(driver);
        return homepage;
    }

    public void goToApp(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage() {
        waitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}
