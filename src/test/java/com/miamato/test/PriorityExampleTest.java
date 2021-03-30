package com.miamato.test;

import com.miamato.BaseTest;
import org.testng.annotations.Test;

public class PriorityExampleTest extends BaseTest {

    //naming of methods was chosen only for sake of example.
    //By default methods have priority 0 and are executed in alphabetical order

    @Test(priority = 2)
    public void aa(){
        driver.navigate().to("https://www.google.com/");
        System.out.println("This is method a with priority 2");
    }

    @Test(priority = 3)
    public void bb(){
        driver.navigate().to("https://www.google.com/");
        System.out.println("This is method b with priority 3");
    }

    @Test(priority = 1)
    public void cc(){
        driver.navigate().to("https://www.google.com/");
        System.out.println("This is method c with priority 1");
    }

}
