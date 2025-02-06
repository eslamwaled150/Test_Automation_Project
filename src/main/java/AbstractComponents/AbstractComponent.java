package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobjects.CartPage;

import java.time.Duration;

public class AbstractComponent {

    public WebDriver driver;
    public WebDriverWait wait;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//button[contains(@routerlink, 'cart')]")
    WebElement waitforGotoCartPage;

    public void waitforElementTOAppear(By findBy)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitforElementTODisAppear(WebElement elementt)
    {
        wait.until(ExpectedConditions.invisibilityOf(elementt));
    }

    public void waitforElementTO(By findBy)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public CartPage GoToCartPage()
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", waitforGotoCartPage);
        waitforGotoCartPage.click();
        CartPage cartpage = new CartPage(driver);
        return new CartPage(driver);
    }


}

















