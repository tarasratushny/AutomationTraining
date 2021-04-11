package com.miamato.pageobject;

import com.miamato.PropertyManager;
import com.miamato.pageobject.clothstore.HomePage;
import com.miamato.pageobject.clothstore.LoginPage;
import com.miamato.pageobject.clothstore.MyAccountPage;
import com.miamato.pageobject.clothstore.MyAddressesPage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public class PageManager {

    public WebDriver driver;
    public PropertyManager propertyManager;

    public HomePage homePage;
    public LoginPage loginPage;
    public MyAddressesPage myAddressesPage;
    public MyAccountPage myAccountPage;

    public PageManager(ITestContext context, PropertyManager propertyManager){
        this.driver = (WebDriver) context.getAttribute("WebDriver");
        this.propertyManager = propertyManager;
    }

    public HomePage homePage(){
        if(this.homePage == null)
            homePage = new HomePage(driver, propertyManager);
        return homePage;
    }

    public LoginPage loginPage(){
        if(this.loginPage == null)
            loginPage = new LoginPage(driver, propertyManager);
        return loginPage;
    }

}
