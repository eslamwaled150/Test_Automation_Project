package org.pageobjects;


import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage extends AbstractComponent
{

    public loginPage(WebDriver driver) {
        super(driver); //to give the dirver to the parent class
        this.driver = driver; //make it with the same driver
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//input[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath="//input[@id='userPassword']")
    WebElement userPassword;

    @FindBy(xpath="//input[@id='login']")
    WebElement login;

    public void go_On_URL()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public productCatalogue loginApp(String email, String pass)
    {
        userEmail.sendKeys(email);
        userPassword.sendKeys(pass);
        login.click();

        productCatalogue productCatalogue = new productCatalogue(driver); // Correct class name
        return productCatalogue; // Return the correct object
    }

}










