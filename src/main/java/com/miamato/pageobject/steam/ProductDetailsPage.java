package com.miamato.pageobject.steam;

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
    public static WebElement PDP_RELEASE_DATE_XPATH;
    @FindBy(xpath = "//div[@id='developers_list']/child::a")
    public static WebElement PDP_DEVELOPERS_LIST_XPATH;
    @FindBy(xpath = "(//div[text()='Publisher:']/following::a)[1]")
    public static WebElement PDP_PUBLISHERS_LIST_XPATH;
    @FindBy(xpath = "//div[@class='game_area_purchase_game']//div[@class='discount_original_price']")
    public static WebElement PDP_MAIN_PRODUCT_ORIGINAL_PRICE;


    public ProductDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }




}
