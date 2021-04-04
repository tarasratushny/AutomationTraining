package com.miamato.actions;

import com.miamato.LogUtil;
import java.time.Duration;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Elements {

    /*

    public static WebElement findElement(WebElement xpath, WebDriver driver, Logger logger){
        return findElement(xpath, -1, driver, logger);
    }

    public static WebElement findElement(WebElement xpath, int position, WebDriver driver, Logger logger){
        WebElement element = null;
        try{
            if(position == -1)
                element = driver.findElement(xpath);
            else
                element = driver.findElements(xpath).get(position-1);
        } catch (Exception e) {
            logger.error(" Element with XPath: " + xpath + "  was not found on the page." );
            LogUtil.logStackTrace(e, logger);
        }
        return element;
    }
    */


    public static WebElement waitForElemnentToBeVisible(WebElement element, WebDriver driver, Logger logger){
        WebElement visibleElement = null;
        try{
            visibleElement = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error(" ---- Element with XPath: " + element + "  was not found on the page." );
            LogUtil.logStackTrace(e, logger);
        }
        return visibleElement;
    }
}
