package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobjects.CartPage;
import org.pageobjects.checkOutPage;
import org.pageobjects.loginPage;
import org.pageobjects.productCatalogue;
import org.pageobjects.confirmationPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.Arrays;



public class AppTest
{
    WebDriver driver;

    @BeforeMethod
    public void before()
    {
        // Initialize the WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void testEcommerce()
    {

        // Open the website and log in
        loginPage newloginpage = new loginPage(driver);
        newloginpage.go_On_URL();
        productCatalogue productList = newloginpage.loginApp("soly@gmail.com" ,"Soly5555@");

        productList.clickFashion();
        productList.clickElectronics();
        productList.clickShirts();
        productList.clickMobiles();
        productList.clickMen();
        productList.clickWoman();

        String productName="IPHONE 13 PRO";
        String productName2="QWERTY";

        List <WebElement> products= productList.getProductList();

        productList.addProductToCart(productName,productName2);

        CartPage cartpage=productList.GoToCartPage();

        Boolean match = cartpage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        Boolean match2 = cartpage.verifyProductDisplay2(productName2);
        Assert.assertTrue(match2);

        checkOutPage checkoutPage= cartpage.goToCheckoutPage();
        checkoutPage.selectCountry("eg");
        Boolean invalid =checkoutPage.isInvalidCouponDisplayed();
        Assert.assertTrue(invalid,"* Invalid Coupon");

        confirmationPage confirmationPagee = checkoutPage.sumbitOrder();
         String confirmationMessage = confirmationPagee.getConfirmationMessage();
         Assert.assertTrue(confirmationMessage.contains("FOR"));

    }

    @AfterMethod
    public void after()
    {
        //driver.quit();
    }

}
