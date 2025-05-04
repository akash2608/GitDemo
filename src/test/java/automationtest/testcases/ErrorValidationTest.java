package automationtest.testcases;

import automationtest.pageobjects.CartPage;
import automationtest.pageobjects.HomePage;
import automationtest.testcomponents.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationTest extends BaseTestClass {
    @Test()
    public void LoginErrorValidation() throws IOException, InterruptedException {
        loginpage.loginToApp("akash.kalsare@gmail.com", "IamAkash000");
        Assert.assertEquals("Incorrect email or password.", loginpage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        HomePage homepage = loginpage.loginToApp("akash.kalsare@gmail.com", "Akash@123");
        List<WebElement> products = homepage.getProductList();
        homepage.addProductToCart(productName);
        CartPage cartpage = homepage.goToCart();
        Boolean match = cartpage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
