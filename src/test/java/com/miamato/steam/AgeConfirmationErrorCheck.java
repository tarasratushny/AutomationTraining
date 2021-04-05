package com.miamato.steam;

import com.miamato.BaseTest;
import com.miamato.asserts.TextAsserts;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.testng.annotations.Test;

public class AgeConfirmationErrorCheck extends BaseTest {

    @Test
    public void checkAgeConfirmationError(){
        homePage.open()
            .searchByGameTitle(propertyManager.getProperty("game.with.discount.title"));
        searchResultsPage.selectProductByPosition(propertyManager.getProperty("game.with.discount.position.in.search"));
        ageConfirmationPage.selectBirthDate(propertyManager.getProperty("invalid.birth.day")
                                            ,propertyManager.getProperty("invalid.birth.month")
                                            ,propertyManager.getProperty("invalid.birth.year"))
            .confirmCustomerAge();
        checkAgeValidationErrorMessage(propertyManager.getProperty("invalid.age.error.message"));
        checkConfirmationButtonIsClickable();
    }

    @Test
    public void checkAgeConfirmationErrorOnSearchRetry(){
        homePage.open()
            .searchByGameTitle(propertyManager.getProperty("game.with.discount.title"));
        searchResultsPage.selectProductByPosition(propertyManager.getProperty("game.with.discount.position.in.search"));
        ageConfirmationPage.selectBirthDate(propertyManager.getProperty("invalid.birth.day")
                                            ,propertyManager.getProperty("invalid.birth.month")
                                            ,propertyManager.getProperty("invalid.birth.year"))
            .confirmCustomerAge()
            .closeAgeValidationErrorModal();
        checkCurrentUrl(propertyManager.getProperty("homepage.url"));
        homePage.searchByGameTitle(propertyManager.getProperty("game.with.discount.title"));
        searchResultsPage.selectProductByPosition(propertyManager.getProperty("game.with.discount.position.in.search"));
        checkAgeValidationErrorOnSecondSearch(propertyManager.getProperty("invalid.age.revisit.error.message"));

    }

    @Step("Check error message for failed age validation")
    private void checkAgeValidationErrorMessage(String expectedMessage){
        assertLogger.info("Verifying error message for failed age validation");
        TextAsserts.assertThatTextIsPresentInField(ageConfirmationPage.errorMessageAgeInvalid, expectedMessage, driver, assertLogger);
    }

    @Step("Check OK button is clickable on age validation error page")
    private void checkConfirmationButtonIsClickable(){
        assertLogger.info("Checking if confirmation button is clickable");
        Assert.assertTrue(ageConfirmationPage.errorMessageOkButton.isEnabled());
        assertLogger.info("Button is clickable");
    }

    @Step("Check customer is redirected to home page")
    private void checkCurrentUrl(String url){
        assertLogger.info("Checking that customer is redirected to homepage after closing age validation error modal window");
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @Step("Check age validation error message on second search attempt")
    private void checkAgeValidationErrorOnSecondSearch(String expectedMessage){
        assertLogger.info("Verifying error message for failed age validation on second search attempt");
        TextAsserts.assertThatTextIsPresentInField(productDetailsPage.ageGateMessaege, expectedMessage, driver, assertLogger);
    }
}
