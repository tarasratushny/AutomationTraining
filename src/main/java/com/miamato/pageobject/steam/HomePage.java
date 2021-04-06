package com.miamato.pageobject.steam;


import com.miamato.PropertyManager;
import com.miamato.pageobject.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(HomePage.class.getSimpleName());
    private final String HOME_PAGE_URL = propertyManager.getProperty("homepage.url");
    public final String PAGE_TITLE = propertyManager.getProperty("homepage.title");

    @FindBy(xpath = "//input[@id='store_nav_search_term']")
    public WebElement searchField;

    public HomePage(WebDriver driver, PropertyManager propertyManager){
        super(driver, propertyManager);
    }

    @Step("Open application home page")
    public HomePage open(){
        logger.info("Trying to open application home page");
        driver.navigate().to(HOME_PAGE_URL);
        Assert.assertEquals(PAGE_TITLE, driver.getTitle());
        return this;
    }

    @Step("Search for a product with name: {gameTitle}")
    public HomePage searchByGameTitle(String gameTitle){
        logger.info("Performing search for product with title: " + gameTitle);
        searchField.sendKeys(gameTitle);
        searchField.sendKeys(Keys.ENTER);
        return this;
    }

}
