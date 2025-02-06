package org.pageobjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.pageobjects.confirmationPage;


public class checkOutPage extends AbstractComponent {

    WebDriver driver;

    public checkOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//p[contains(text(),'Invalid Coupon')]")
    WebElement invalidCouponMessage_Find;

    @FindBy(css = ".ng-animating")
    WebElement waitforloadApplyCoupon;

    @FindBy(xpath = "//a[normalize-space()='Place Order']")
    WebElement submit;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;

    @FindBy(xpath = "(//input[@type='text'])[3]")
    WebElement nameCard;

    @FindBy(xpath = "//input[@name='coupon']")
    WebElement writeCoupon;

    @FindBy(css = "button[type='submit']")
    WebElement applyCoupon;

    By coupon = By.cssSelector("button[type='submit']");
    By invalidCouponMessage =By.xpath("//p[contains(text(),'Invalid Coupon')]");
    By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName)
    {
        Actions anaa =new Actions(driver);
        anaa.sendKeys(country,countryName).build().perform();
        waitforElementTOAppear(results);
        selectCountry.click();
    }

    public boolean isInvalidCouponDisplayed()
    {
        nameCard.sendKeys("eslam");
        writeCoupon.sendKeys("6784fd");
        applyCoupon.click();
        waitforElementTOAppear(coupon);
        waitforElementTODisAppear(waitforloadApplyCoupon);
        waitforElementTO(invalidCouponMessage);
        return invalidCouponMessage_Find.isDisplayed();
    }

    public confirmationPage sumbitOrder()
    {
        submit.click();
        return new confirmationPage(driver);
    }
}
