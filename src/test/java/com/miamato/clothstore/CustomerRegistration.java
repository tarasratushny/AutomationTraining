package com.miamato.clothstore;

import com.miamato.BaseTest;
import com.miamato.valueobjects.Customer;
import org.testng.annotations.Test;

public class CustomerRegistration extends BaseTest {



    @Test
    public void customerRegistrationSmoke(){
        Customer customer = new Customer(propertyManager, "default");

        homePage.open()
                .clickLoginButton();
        loginPage.createNewAccount(customer);
    }
}
