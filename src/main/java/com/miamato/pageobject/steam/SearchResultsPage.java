package com.miamato.pageobject.steam;

import com.miamato.pageobject.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SearchResultsPage.class.getSimpleName());

    public static final By SEARCH_RESULTS_PRODUCT_TILES = By.xpath("//a[contains(@class,'search_result_row')]");


    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
    }


    @Step("Select product with {position} position in search results")
    public SearchResultsPage selectProductByPosition(int position){
        logger.info("Trying to select product with position: " + position + " from search results");
        clickOnElement(SEARCH_RESULTS_PRODUCT_TILES, logger);
        return this;
    }

}
