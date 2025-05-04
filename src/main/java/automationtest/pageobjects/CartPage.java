package automationtest.pageobjects;

import automationtest.pagecomponents.BasePageClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePageClass {

    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".cartSection h3") List<WebElement> cartProducts;

    @FindBy(css=".totalRow button") WebElement checkout;

    public Boolean verifyProductDisplay(String productname){
        Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productname));
        return match;
    }

    public CheckoutPage goToCheckout(){
        checkout.click();
        CheckoutPage checkoutpage = new CheckoutPage(driver);
        return checkoutpage;
    }
}
