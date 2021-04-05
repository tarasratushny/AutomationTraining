package com.miamato.pageobject.steam;

import com.miamato.PropertyManager;
import com.miamato.pageobject.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(ProductDetailsPage.class.getSimpleName());

    @FindBy(xpath = "//div[@class='date']")
    public WebElement pdpReleaseDate;
    @FindBy(xpath = "//div[@id='developers_list']/child::a")
    public WebElement pdpDevelopersList;
    @FindBy(xpath = "(//div[text()='Publisher:']/following::a)[1]")
    public WebElement pdpPublishersList;
    @FindBy(xpath = "//div[@class='game_area_purchase_game']//div[@class='discount_original_price']")
    public WebElement pdpMainProductOriginalPrice;
    @FindBy(xpath = "//div[@id='agegate_box']/h2")
    public WebElement ageGateMessaege;


    public ProductDetailsPage(WebDriver driver, PropertyManager propertyManager){
        super(driver, propertyManager);
    }




}
