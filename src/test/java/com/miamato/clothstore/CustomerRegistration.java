package com.miamato.clothstore;

import com.miamato.BaseTest;
import com.miamato.valueobjects.Address;
import com.miamato.valueobjects.Customer;
import io.qameta.allure.Step;
import java.util.Locale;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomerRegistration extends BaseTest {


    @DataProvider
    public Object[][] customerTypes(){
        return new Object[][]{
            {"default"},
            {"max.length"}
        };
    }

    @Test(dataProvider = "customerTypes")
    public void customerRegistrationSmoke(String customerType){
        Customer customer = new Customer(propertyManager, customerType);

        homePage.open()
                .clickLoginButton();
        loginPage.createNewAccount(customer);
        myAccountPage.openMyAddressesList();
        myAddressesPage.waitForPageToLoad();
        checkAddressInProfile(customer);
    }

    @Step("Check first address details")
    private void checkAddressInProfile(Customer customer){
        checkName(customer);
        checkAddressDetails(customer.address);
    }

    @Step("Check customer name in address")
    private void checkName(Customer customer){
        assertLogger.info("Checking customer name in address");
        if (customer.address.name.isEmpty()) {
            Assert.assertEquals(myAddressesPage.firstAddressName.getText(), customer.name);
        } else {
            Assert.assertEquals(myAddressesPage.firstAddressName.getText(), customer.address.name);
        }
        if (customer.address.lastName.isEmpty()) {
            Assert.assertEquals(myAddressesPage.firstAddressLastName.getText(), customer.lastName);
        } else {
            Assert.assertEquals(myAddressesPage.firstAddressLastName.getText(), customer.address.lastName);
        }
    }

    @Step
    private void checkAddressDetails(Address address){
        assertLogger.info("Checking address details");
        assertLogger.info("Checking address line 1");
        Assert.assertEquals(myAddressesPage.firstAddressLine1.getText().trim(), address.addressLine1);
        assertLogger.info("Checking address line 2");
        Assert.assertEquals(myAddressesPage.firstAddressLine2.getText().trim(), address.addressLine2);
        assertLogger.info("Checking address company");
        Assert.assertEquals(myAddressesPage.firstAddressCompany.getText().trim(), address.company);
        assertLogger.info("Checking address city");
        Assert.assertEquals(myAddressesPage.firstAddressCity.getText().trim(), address.city + ",");
        assertLogger.info("Checking address state");
        Assert.assertEquals(myAddressesPage.firstAddressState.getText().trim(), address.state);
        assertLogger.info("Checking address postal code");
        Assert.assertEquals(myAddressesPage.firstAddressPostalCode.getText().trim(), address.postalCode);
        assertLogger.info("Checking address country");
        Assert.assertEquals(myAddressesPage.firstAddressCountry.getText().trim(), address.country);
        assertLogger.info("Checking address homePhone");
        Assert.assertEquals(myAddressesPage.firstAddressHomePhone.getText().trim(), address.homePhone);
        assertLogger.info("Checking address mobilePhone");
        Assert.assertEquals(myAddressesPage.firstAddressHomeMobilePhone.getText().trim(), address.mobilePhone);
        assertLogger.info("Checking address addressAlias");
        Assert.assertEquals(myAddressesPage.firstAddressAlias.getText().trim(), address.addressAlias.toUpperCase(Locale.ROOT));
    }
}
