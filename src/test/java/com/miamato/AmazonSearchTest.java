package com.miamato;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonSearchTest {

    static WebDriver driver;

    @Test
    public void basicAmazonProductSearch(){
        System.setProperty("webdriver.chrome.driver", "drivers/windows/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.amazon.co.uk/");
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more");
        driver.findElement(new ById("sp-cc-accept")).click();
        driver.findElement(new ById("twotabsearchtextbox")).sendKeys("Drills");
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'s-breadcrumb')]//span[@class='a-color-state a-text-bold']")).isDisplayed());
        Assert.assertEquals(driver.findElements(By.xpath("//div[@id='departments']//span[@class='a-size-base a-color-base']")).get(0).getText(),"Drills");
        driver.quit();
    }

}
