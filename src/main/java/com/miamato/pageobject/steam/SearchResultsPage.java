package com.miamato.pageobject.steam;

import com.miamato.PropertyManager;
import com.miamato.pageobject.BasePage;
import io.qameta.allure.Step;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SearchResultsPage.class.getSimpleName());

    @FindAll(@FindBy(xpath = "//a[contains(@class,'search_result_row')]"))
    public List<WebElement> searchResultsProdutTiles;


    public SearchResultsPage(WebDriver driver, PropertyManager propertyManager){
        super(driver, propertyManager);
    }


    @Step("Select product with {position} position in search results")
    public SearchResultsPage selectProductByPosition(int position){
        logger.info("Trying to select product with position: " + position + " from search results");
        searchResultsProdutTiles.get(position-1).click();
        return this;
    }

    public SearchResultsPage selectProductByPosition(String position){
        return selectProductByPosition(Integer.parseInt(position));
    }

}
