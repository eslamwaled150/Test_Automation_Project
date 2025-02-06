package org.pageobjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {


    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='cartSection']")
    List<WebElement> cartProducts;

    @FindBy(xpath = "//button[contains(text(), 'Checkout')]")
    WebElement checkoutElement;

    public Boolean verifyProductDisplay(String productName)
    {
        Boolean match= cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().contains(productName));
        return match;
    }

    public Boolean verifyProductDisplay2(String productName2)
    {
        Boolean match2= cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().contains(productName2));
        return match2;
    }

    public checkOutPage goToCheckoutPage()
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutElement);
        checkoutElement.click();
        return new checkOutPage(driver);
    }


}
