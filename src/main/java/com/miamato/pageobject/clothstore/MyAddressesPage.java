package com.miamato.pageobject.clothstore;

import com.miamato.PropertyManager;
import com.miamato.pageobject.BasePage;
import io.qameta.allure.Step;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAddressesPage extends BasePage {


    private static final Logger logger = LogManager.getLogger(MyAddressesPage.class.getSimpleName());

    @FindBy(xpath = "//h1[contains(text(),'My addresses')]")
    public WebElement myAddressesHeading;
    @FindBy(xpath = "//h3[@class='page-subheading']")
    public WebElement firstAddressAlias;
    @FindBy(xpath = "//span[@class='address_name'][1]")
    public WebElement firstAddressName;
    @FindBy(xpath = "//span[@class='address_name'][2]")
    public WebElement firstAddressLastName;
    @FindBy(xpath = "//span[@class='address_company']")
    public WebElement firstAddressCompany;
    @FindBy(xpath = "//span[@class='address_address1']")
    public WebElement firstAddressLine1;
    @FindBy(xpath = "//span[@class='address_address2']")
    public WebElement firstAddressLine2;
    @FindBy(xpath = "//div[@class='bloc_adresses row']//li[5]/span[1]")
    public WebElement firstAddressCity;
    @FindBy(xpath = "//div[@class='bloc_adresses row']//li[5]/span[3]")
    public WebElement firstAddressPostalCode;
    @FindBy(xpath = "//div[@class='bloc_adresses row']//li[5]/span[2]")
    public WebElement firstAddressState;
    @FindBy(xpath = "//div[@class='bloc_adresses row']//li[6]/span")
    public WebElement firstAddressCountry;
    @FindBy(xpath = "//span[@class='address_phone']")
    public WebElement firstAddressHomePhone;
    @FindBy(xpath = "//span[@class='address_phone_mobile']")
    public WebElement firstAddressHomeMobilePhone;
    @FindBy(xpath = "//a[@title='Update']")
    public WebElement firstAddressUpdateButton;
    @FindBy(xpath = "//a[@title='Delete']")
    public WebElement firstAddressDeleteButton;


    public MyAddressesPage(WebDriver driver, PropertyManager propertyManager){
        super(driver, propertyManager);
    }

    @Step("Waiting for page to load")
    public MyAddressesPage waitForPageToLoad(){
        logger.info("Waiting for My Addresses page to load");
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1))
            .until(ExpectedConditions.visibilityOf(myAddressesHeading));
        return this;
    }
}
