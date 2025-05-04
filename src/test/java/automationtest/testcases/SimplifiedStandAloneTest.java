package automationtest.testcases;

import automationtest.pageobjects.*;
import automationtest.testcomponents.BaseTestClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SimplifiedStandAloneTest extends BaseTestClass {

    @Test
    public void placeOrderTest() throws InterruptedException, IOException {
        //login
        HomePage homepage = loginpage.loginToApp("akash.kalsare@gmail.com","Akash@123");

        //get list of items
        String prodname = "IPHONE 13 PRO";
        List<WebElement> products = homepage.getProductList();

        //add the desired product in cart
        homepage.addProductToCart(prodname);

        //cart page
        CartPage cartpage = homepage.goToCart();

        //get list of product names on cart page
        Boolean match = cartpage.verifyProductDisplay(prodname);
        Assert.assertTrue(match);

        //checkout
        CheckoutPage checkoutpage = cartpage.goToCheckout();

        //Place the order
        checkoutpage.selectCountry("india");
        ConfirmationPage cnfpage = checkoutpage.submitOrder();

        //confirm that order is successful
        String confirmMessage = cnfpage.getConfirmationMsg();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
}
