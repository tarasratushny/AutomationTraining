package com.miamato.steam;

import com.miamato.BaseTest;
import com.miamato.PropertyManager;
import com.miamato.asserts.TextAsserts;
import com.miamato.pageobject.steam.ProductDetailsPage;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class ProductInfoOnPdp extends BaseTest {


    @Test
    public void discountedProductInfoOnPdp(){
        homePage.open()
                .searchByGameTitle(propertyManager.getProperty("game.with.discount.title"));
        searchResultsPage.selectProductByPosition(Integer.parseInt(propertyManager.getProperty("game.with.discount.position.in.search")));
        ageConfirmationPage.selectBirthDate(propertyManager.getProperty("birth.day")
                                            ,propertyManager.getProperty("birth.month")
                                            ,propertyManager.getProperty("birth.year"))
                .confirmCustomerAge();
        assertPdpReleaseDate(propertyManager.getProperty("game.with.discount.release.date"));
        assertPdpDeveloperName(propertyManager.getProperty("game.with.discount.developer"));
        assertPdpPublisherName(propertyManager.getProperty("game.with.discount.publisher"));
        assertPdpOriginalPriceForDiscountedProduct(propertyManager.getProperty("game.with.discount.original.price"));
    }


    @Step("Check that release date is {expectedReleaseDate}")
    private void assertPdpReleaseDate(String expectedReleaseDate){
        assertLogger.info("Verifying release date on PDP page");
        TextAsserts.assertThatTextIsPresentInField(productDetailsPage.PDP_RELEASE_DATE_XPATH, expectedReleaseDate, driver, assertLogger);
    }
    @Step("Check that publisher name is {expectedPublisherName}")
    private void assertPdpPublisherName(String expectedPublisherName){
        assertLogger.info("Verifying publisher name on PDP page");
        TextAsserts.assertThatTextIsPresentInField(productDetailsPage.PDP_PUBLISHERS_LIST_XPATH, expectedPublisherName, driver, assertLogger);
    }
    @Step("Check that developer name is {expectedReleaseDate}")
    private void assertPdpDeveloperName(String expectedDeveloperName){
        assertLogger.info("Verifying developer name on PDP page");
        TextAsserts.assertThatTextIsPresentInField(productDetailsPage.PDP_DEVELOPERS_LIST_XPATH, expectedDeveloperName, driver, assertLogger);
    }
    @Step("Check that original price is {expectedOriginalPrice}")
    private void assertPdpOriginalPriceForDiscountedProduct(String expectedOriginalPrice){
        assertLogger.info("Verifying original price on PDP page");
        TextAsserts.assertThatTextIsPresentInField(productDetailsPage.PDP_MAIN_PRODUCT_ORIGINAL_PRICE, expectedOriginalPrice, driver, assertLogger);
    }
}
