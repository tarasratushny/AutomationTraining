package com.miamato.test;

import com.miamato.BaseTest;
import com.miamato.PropertyManager;
import org.testng.annotations.Test;

public class SteamTest extends BaseTest {

    private static final String URL = PropertyManager.getProperty("homepage.url");

    @Test
    public void steamFun(){
        driver.navigate().to(URL);
    }
}
