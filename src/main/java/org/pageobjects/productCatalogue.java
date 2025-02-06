package org.pageobjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class productCatalogue extends AbstractComponent {

    Actions ana=new Actions(driver);

    public productCatalogue(WebDriver driver) {
        super(driver); //to give the dirver to the parent class
        this.driver = driver; //make it with the same driver
        PageFactory.initElements(driver,this);
        this.ana= new Actions(driver);

    }

    @FindBy(css = ".mb-3")
    List <WebElement> products;

    @FindBy(css = ".ng-animating")
     WebElement waitforloadproduct;

    @FindBy(xpath = "(//input[@type='checkbox'])[20]")
    WebElement waitforMen;

    @FindBy(xpath = "(//input[@type='checkbox'])[21]")
    WebElement waitforWoman;

    By productNames=By.cssSelector("h5 b");
    By products_By = By.cssSelector(".mb-3");

    //By addToCart = By.cssSelector(".card-body button:last-of-type");
    By addToCart = By.cssSelector(".btn.w-10.rounded");

    By waitforAddToCart = By.xpath("//div[@id='toast-container']");

    By waitFashion = By.xpath("(//input[@type='checkbox'])[12]");
    By waitElectronics = By.xpath("(//input[@type='checkbox'])[13]");
    By waitShirts = By.xpath("(//input[@type='checkbox'])[16]");
    By waitMobiles = By.xpath("(//input[@type='checkbox'])[18]");
    By waitMen = By.xpath("(//input[@type='checkbox'])[20]");
    By waitWoman = By.xpath("(//input[@type='checkbox'])[21]");


    // Method to retrieve the product list
    public List<WebElement> getProductList()
    {
        waitforElementTOAppear(products_By);
        return products;
    }

    // Method to find a specific product by name
    public WebElement getProductByName(String productName)
    {
        WebElement targetProduct = getProductList().stream().filter(product ->
        product.findElement(productNames).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return targetProduct;
    }

    public WebElement getProductByName2(String productName2)
    {
        WebElement targetProduct = getProductList().stream().filter(product ->
        product.findElement(productNames).getText().equalsIgnoreCase(productName2)).findFirst().orElse(null);
        return targetProduct;
    }


    public void clickFashion() {
        WebElement fashionCheckbox = driver.findElement(waitFashion);
        fashionCheckbox.click();
        waitforElementTOAppear(addToCart);
    }

    public void clickElectronics() {
        WebElement electronicsCheckbox = driver.findElement(waitElectronics);
        electronicsCheckbox.click();
        waitforElementTOAppear(addToCart);
    }

    public void clickShirts() {
        WebElement ShirtsCheckbox = driver.findElement(waitShirts);
        ShirtsCheckbox.click();
        waitforElementTOAppear(addToCart);
    }

    public void clickMobiles() {
        WebElement MobilesCheckbox = driver.findElement(waitMobiles);
        MobilesCheckbox.click();
        waitforElementTOAppear(addToCart);
    }


    public void clickMen() {
        WebElement MenCheckbox = driver.findElement(waitMen);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", MenCheckbox);
        waitforElementTOAppear(waitMen);
        //ana.moveToElement(waitforMen).click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", MenCheckbox);
        waitforElementTOAppear(addToCart);
    }

    public void clickWoman() {
        WebElement WomanCheckbox = driver.findElement(waitWoman);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",WomanCheckbox);
        waitforElementTOAppear(waitWoman);
        //ana.moveToElement(waitforWoman).click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", WomanCheckbox);
        waitforElementTOAppear(addToCart);
    }


    // Method to add a product to the cart
    public void addProductToCart(String productName ,String productName2)
    {
        WebElement targetProduct=getProductByName(productName);
        targetProduct.findElement(addToCart).click();
        waitforElementTOAppear(waitforAddToCart);
        waitforElementTODisAppear(waitforloadproduct);

        WebElement targetProduct2=getProductByName2(productName2);
        targetProduct2.findElement(addToCart).click();
        waitforElementTOAppear(waitforAddToCart);
        waitforElementTODisAppear(waitforloadproduct);
    }



}
