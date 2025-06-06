package automationtest.pageobjects;

import automationtest.pagecomponents.BasePageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePageClass {

    WebDriver driver;

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".mb-3") List<WebElement> products;

    @FindBy(css="#toast-container") WebElement toast;

    @FindBy(css=".ng-animating") WebElement loader;

    By addToCart = By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getProductList(){
        waitForElementToAppear(products);
        return products;
    }

    public WebElement getProductByName(String productname){
        WebElement prod = products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productname) throws InterruptedException {
        WebElement prod = getProductByName(productname);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toast);
        Thread.sleep(2000);
        //waitForElementToDisappear(loader);
    }
}
