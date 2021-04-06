package com.miamato.pageobject;

import com.miamato.PropertyManager;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v89.page.Page;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public abstract class BasePage extends Page {

    protected WebDriver driver;
    protected PropertyManager propertyManager;

    public BasePage(WebDriver driver, PropertyManager propertyManager){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.propertyManager = propertyManager;
    }

    protected void selectFromDropdownByValue(WebElement element, String value, Logger logger){
        logger.info("Trying to select option: \"" + value + "\" from dropdown: " + element);
        Select dropdown = new Select(element);
        try {
            dropdown.selectByValue(value);
        } catch (Exception e) {
            logger.error("Option cannot be selected from dropdown");
            throw e;
        }
    }

}
