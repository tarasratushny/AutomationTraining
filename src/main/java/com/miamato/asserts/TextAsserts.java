package com.miamato.asserts;

import com.miamato.actions.Elements;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TextAsserts {


    public static void assertThatTextIsPresentInField(By xpath, String expectedText, WebDriver driver, Logger logger) {
        logger.info("Checking if expected text: \"" + expectedText
            + "\" is equal to text in field with XPath:  " + xpath);
        Assert.assertEquals(Elements.findElementWithWait(xpath, driver, logger).getText(), expectedText);
        logger.info("Text is equal to expected");
    }
}
