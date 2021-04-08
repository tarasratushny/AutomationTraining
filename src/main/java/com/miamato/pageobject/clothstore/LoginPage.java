package com.miamato.pageobject.clothstore;

import com.miamato.PropertyManager;
import com.miamato.pageobject.BasePage;
import com.miamato.valueobjects.Address;
import com.miamato.valueobjects.Customer;
import io.qameta.allure.Step;
import java.sql.Timestamp;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class.getSimpleName());
    private final String EMAIL_SUFFIX = "customer@unexisting.email";

    @FindBy(xpath = "//input[@id='email_create']")
    public WebElement newCustomerEmailAddress;
    @FindBy(xpath = "//button[@id='SubmitCreate']")
    public WebElement createAccountButton;
    @FindBy(xpath = "//input[@id='customer_firstname']")
    public WebElement firstName;
    @FindBy(xpath = "//input[@id='customer_lastname']")
    public WebElement lastName;
    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailFieldOnCreationForm;
    @FindBy(xpath = "//input[@id='passwd']")
    public WebElement passwordFieldOnCreationForm;
    @FindBy(xpath = "//select[@id='days']")
    public WebElement birthDayDropdown;
    @FindBy(xpath = "//select[@id='months']")
    public WebElement birthMonthDropdown;
    @FindBy(xpath = "//select[@id='years']")
    public WebElement birtYearDropdown;

    @FindBy(xpath = "//h3[contains(text(),'Your personal information')]")
    public WebElement personalInfoTitle;
    @FindBy(xpath = "//input[@id='firstname']")
    public WebElement addressFirstName;
    @FindBy(xpath = "//input[@id='lastname']")
    public WebElement addressLastName;
    @FindBy(xpath = "//input[@id='company']")
    public WebElement addressCompany;
    @FindBy(xpath = "//input[@id='address1']")
    public WebElement addressLine1;
    @FindBy(xpath = "//input[@id='address2']")
    public WebElement addressLine2;
    @FindBy(xpath = "//input[@id='city']")
    public WebElement addressCity;
    @FindBy(xpath = "//select[@id='id_state']")
    public WebElement addressStateDropdown;
    @FindBy(xpath = "//input[@id='postcode']")
    public WebElement addressPostCode;
    @FindBy(xpath = "//select[@id='id_country']")
    public WebElement addressCountry;
    @FindBy(xpath = "//textarea[@id='other']")
    public WebElement addressAdditonalInfo;
    @FindBy(xpath = "//input[@id='phone']")
    public WebElement addressHomePhone;
    @FindBy(xpath = "//input[@id='phone_mobile']")
    public WebElement addressMobilePhone;
    @FindBy(xpath = "//input[@id='alias']")
    public WebElement addressAlias;
    @FindBy(xpath = "//button[@id='submitAccount']")
    public WebElement submitNewAccountButton;



    public LoginPage(WebDriver driver, PropertyManager propertyManager){
        super(driver, propertyManager);
    }

    @Step("Create new account")
    public LoginPage createNewAccount(Customer customer){
        enterNewCustomerEmail(customer);
        clickCreateAccountButton();
        enterPersonalInformation(customer);
        enterAddress(customer);
        submitNewAccountButton.click();
        return this;
    }

    @Step("Enter new customer email")
    public LoginPage enterNewCustomerEmail(Customer customer){
        logger.info("Generating unique email for customer");
        long customerIndex = new Timestamp(System.currentTimeMillis()).getTime();
        logger.info("Entering generated email: " + customerIndex + EMAIL_SUFFIX);
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1)).until(
            ExpectedConditions.visibilityOf(newCustomerEmailAddress)).sendKeys(customerIndex + EMAIL_SUFFIX);
        //newCustomerEmailAddress.sendKeys(customerIndex + EMAIL_SUFFIX);
        customer.setEmail(customerIndex+EMAIL_SUFFIX);
        return this;
    }

    @Step("Click create an account button")
    public LoginPage clickCreateAccountButton(){
        logger.info("Clicking create account button");
        createAccountButton.click();
        return this;
    }

    @Step("Enter customer personal information")
    public LoginPage enterPersonalInformation(Customer customer){

        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1))
            .until(ExpectedConditions.visibilityOf(personalInfoTitle));
        logger.info("Entering customer name");
        firstName.sendKeys(customer.name);
        logger.info("Entering customer last name");
        lastName.sendKeys(customer.lastName);
        logger.info("Entering password");
        passwordFieldOnCreationForm.sendKeys(customer.password);
        logger.info("Selecting birth date from dropdown");
        selectFromDropdownByValue(birthDayDropdown, customer.birthDay, logger);
        selectFromDropdownByVisibleText(birthMonthDropdown, customer.birthMonth, logger);
        selectFromDropdownByValue(birtYearDropdown, customer.birthYear, logger);
        return this;
    }

    @Step("Enter customer address")
    public LoginPage enterAddress(Customer customer){
        enterAddress(customer.address);
        return this;
    }

    @Step("Enter customer address")
    public LoginPage enterAddress(Address address){
        logger.info("Entering name in address");
        addressFirstName.sendKeys(address.name);
        logger.info("Entering last name in address");
        addressLastName.sendKeys(address.lastName);
        logger.info("Entering company in address");
        addressCompany.sendKeys(address.company);
        logger.info("Entering address line 1");
        addressLine1.sendKeys(address.addressLine1);
        logger.info("Entering address line 2");
        addressLine2.sendKeys(address.addressLine2);
        logger.info("Entering address city");
        addressCity.sendKeys(address.city);
        logger.info("Selecting city from dropdown");
        selectFromDropdownByVisibleText(addressStateDropdown, address.state, logger);
        logger.info("Entering address postal code");
        addressPostCode.sendKeys(address.postalCode);
        logger.info("Selecting city from dropdown");
        selectFromDropdownByVisibleText(addressCountry, address.country, logger);
        logger.info("Entering address additional info");
        addressAdditonalInfo.sendKeys(address.additionalInfo);
        logger.info("Entering address home phone");
        addressHomePhone.sendKeys(address.homePhone);
        logger.info("Entering address mobile phone");
        addressMobilePhone.sendKeys(address.mobilePhone);
        logger.info("Entering address alias");
        addressAlias.clear();
        addressAlias.sendKeys(address.addressAlias);
        return this;
    }
}
