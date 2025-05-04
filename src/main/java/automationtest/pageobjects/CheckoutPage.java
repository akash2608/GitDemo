package automationtest.pageobjects;

import automationtest.pagecomponents.BasePageClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends BasePageClass {

    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement countryField;

    @FindBy(css=".ta-results")
    List<WebElement> countryValues;

    @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
    WebElement desiredCountryValue;

    @FindBy(css=".action__submit")
    WebElement submitbutton;

    public void selectCountry(String country){
        Actions a = new Actions(driver);
        a.sendKeys(countryField, country).build().perform();
        waitForElementToAppear(countryValues);
        desiredCountryValue.click();
    }

    public ConfirmationPage submitOrder(){
        submitbutton.click();
        ConfirmationPage cnfpage = new ConfirmationPage(driver);
        return cnfpage;
    }
}
