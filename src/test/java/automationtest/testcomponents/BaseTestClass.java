package automationtest.testcomponents;

import automationtest.pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTestClass {

    public WebDriver driver;
    public LoginPage loginpage;

    public WebDriver initialiseDriver() throws IOException {

        Properties prop = new Properties();
        InputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//GobalData.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecho.driver", "gechodriver-mac-arm64/gechodriver");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "edgedriver-mac-arm64/edgedriver");
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    public LoginPage launchApplication() throws IOException {
        driver = initialiseDriver();
        loginpage = new LoginPage(driver);
        loginpage.goToApp();
        return loginpage;
    }

    @AfterMethod
    public void teardown(){
        //driver.close();
        driver.quit();
    }
}
