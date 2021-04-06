package com.miamato.steam;

import com.miamato.BaseTest;
import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductInfoOnPdp extends BaseTest {


    @Test
    public void discountedProductInfoOnPdp(){
        homePage.open()
                .searchByGameTitle(propertyManager.getProperty("game.with.discount.title"));
        searchResultsPage.selectProductByPosition(propertyManager.getProperty("game.with.discount.position.in.search"));
        ageConfirmationPage.selectBirthDate(propertyManager.getProperty("birth.day")
                                            ,propertyManager.getProperty("birth.month")
                                            ,propertyManager.getProperty("birth.year"))
                .confirmCustomerAge();
        checkPdpTitle();
        assertPdpReleaseDate(propertyManager.getProperty("game.with.discount.release.date"));
        assertPdpDeveloperName(propertyManager.getProperty("game.with.discount.developer"));
        assertPdpPublisherName(propertyManager.getProperty("game.with.discount.publisher"));
        assertPdpOriginalPriceForDiscountedProduct(propertyManager.getProperty("game.with.discount.original.price"));
    }

    @Step("Check pdp title")
    private void checkPdpTitle(){
        assertLogger.info("Check if page title is correct");
        Assert.assertEquals(propertyManager.getProperty("game.with.discount.pdp.title"), driver.getTitle());
    }

    @Step("Check that release date is {expectedReleaseDate}")
    private void assertPdpReleaseDate(String expectedReleaseDate){
        assertLogger.info("Verifying release date on PDP page");
        Assert.assertEquals(expectedReleaseDate, new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(ExpectedConditions.visibilityOf(productDetailsPage.pdpReleaseDate)).getText());
    }
    @Step("Check that publisher name is {expectedPublisherName}")
    private void assertPdpPublisherName(String expectedPublisherName){
        assertLogger.info("Verifying publisher name on PDP page");
        Assert.assertEquals(expectedPublisherName, productDetailsPage.pdpPublishersList.getText());
    }
    @Step("Check that developer name is {expectedReleaseDate}")
    private void assertPdpDeveloperName(String expectedDeveloperName){
        assertLogger.info("Verifying developer name on PDP page");
        Assert.assertEquals(expectedDeveloperName, productDetailsPage.pdpDevelopersList.getText());
    }
    @Step("Check that original price is {expectedOriginalPrice}")
    private void assertPdpOriginalPriceForDiscountedProduct(String expectedOriginalPrice){
        assertLogger.info("Verifying original price on PDP page");
        Assert.assertEquals(expectedOriginalPrice, productDetailsPage.pdpMainProductOriginalPrice.getText());
    }
}
