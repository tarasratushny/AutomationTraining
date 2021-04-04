package com.miamato.test;

import com.miamato.BaseTest;
import com.miamato.PropertyManager;
import com.miamato.asserts.TextAsserts;
import com.miamato.pageobject.steam.ProductDetailsPage;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class ProductInfoOnPdp extends BaseTest {

    private static final String GAME_WITH_DISCOUNT_TITLE = PropertyManager.getProperty("game.with.discount.title");
    private static final int GAME_WITH_DISCOUNT_POSITION_IN_SEARCH = Integer.parseInt(PropertyManager.getProperty("game.with.discount.position.in.search"));
    private static final String GAME_WITH_DISCOUNT_DEVELOPER = PropertyManager.getProperty("game.with.discount.developer");
    private static final String GAME_WITH_DISCOUNT_PUBLISHER = PropertyManager.getProperty("game.with.discount.publisher");
    private static final String GAME_WITH_DISCOUNT_RELEASE_DATE = PropertyManager.getProperty("game.with.discount.release.date");
    private static final String GAME_WITH_DISCOUNT_ORIGINAL_PRICE = PropertyManager.getProperty("game.with.discount.original.price");

    private static final String CUSTOMER_BIRTH_DAY = PropertyManager.getProperty("birth.day");
    private static final String CUSTOMER_BIRTH_MONTH = PropertyManager.getProperty("birth.month");
    private static final String CUSTOMER_BIRTH_YEAR = PropertyManager.getProperty("birth.year");


    @Test
    public void discountedProductInfoOnPdp(){
        homePage.open()
                .searchByGameTitle(GAME_WITH_DISCOUNT_TITLE);
        searchResultsPage.selectProductByPosition(GAME_WITH_DISCOUNT_POSITION_IN_SEARCH);
        ageConfirmationPage.selectBirthDate(CUSTOMER_BIRTH_DAY, CUSTOMER_BIRTH_MONTH, CUSTOMER_BIRTH_YEAR)
                .confirmCustomerAge();
        assertPdpReleaseDate(GAME_WITH_DISCOUNT_RELEASE_DATE);
        assertPdpDeveloperName(GAME_WITH_DISCOUNT_DEVELOPER);
        assertPdpPublisherName(GAME_WITH_DISCOUNT_PUBLISHER);
        assertPdpOriginalPriceForDiscountedProduct(GAME_WITH_DISCOUNT_ORIGINAL_PRICE);
    }


    @Step("Check that release date is {expectedReleaseDate}")
    private void assertPdpReleaseDate(String expectedReleaseDate){
        assertLogger.info("Verifying release date on PDP page");
        TextAsserts.assertThatTextIsPresentInField(ProductDetailsPage.PDP_RELEASE_DATE_XPATH, expectedReleaseDate, driver, assertLogger);
    }
    @Step("Check that publisher name is {expectedPublisherName}")
    private void assertPdpPublisherName(String expectedPublisherName){
        assertLogger.info("Verifying publisher name on PDP page");
        TextAsserts.assertThatTextIsPresentInField(ProductDetailsPage.PDP_PUBLISHERS_LIST_XPATH, expectedPublisherName, driver, assertLogger);
    }
    @Step("Check that developer name is {expectedReleaseDate}")
    private void assertPdpDeveloperName(String expectedDeveloperName){
        assertLogger.info("Verifying developer name on PDP page");
        TextAsserts.assertThatTextIsPresentInField(ProductDetailsPage.PDP_DEVELOPERS_LIST_XPATH, expectedDeveloperName, driver, assertLogger);
    }
    @Step("Check that original price is {expectedOriginalPrice}")
    private void assertPdpOriginalPriceForDiscountedProduct(String expectedOriginalPrice){
        assertLogger.info("Verifying original price on PDP page");
        TextAsserts.assertThatTextIsPresentInField(ProductDetailsPage.PDP_MAIN_PRODUCT_ORIGINAL_PRICE, expectedOriginalPrice, driver, assertLogger);
    }
}
