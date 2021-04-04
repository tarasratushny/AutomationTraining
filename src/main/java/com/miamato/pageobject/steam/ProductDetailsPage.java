package com.miamato.pageobject.steam;

import com.miamato.pageobject.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(ProductDetailsPage.class.getSimpleName());
    private static ProductDetailsPage instance = null;

    public static final By PDP_RELEASE_DATE_XPATH = By.xpath("//div[@class='date']");
    public static final By PDP_DEVELOPERS_LIST_XPATH = By.xpath("//div[@id='developers_list']/child::a");
    public static final By PDP_PUBLISHERS_LIST_XPATH = By.xpath("(//div[text()='Publisher:']/following::a)[1]");
    public static final By PDP_MAIN_PRODUCT_ORIGINAL_PRICE = By.xpath("//div[@class='game_area_purchase_game']//div[@class='discount_original_price']");


    private ProductDetailsPage(WebDriver driver){
        this.driver = driver;
    }

    public static ProductDetailsPage getInstance(WebDriver driver){
        if(instance == null)
            instance = new ProductDetailsPage(driver);
        return instance;
    }



}
