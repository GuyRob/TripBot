package com.guyrob.tripbot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.emulation.Emulation;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Month;
import java.util.*;


public class base {
    public static WebDriver driver;
    public static DevTools devTools;
    public static Actions actions;


    public void sleep(int time) {
        try {

            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

        public List<String> switchTab(int tabID){
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandles);
        driver.switchTo().window(windowHandlesList.get(tabID));
        return windowHandlesList;
    }

    public static int convertMonthTextToInt(String monthText) {
        // Parse the month text using the Month enum
        Month month = Month.valueOf(monthText.toUpperCase(Locale.ENGLISH));

        // Get the month value as an integer (1 to 12)
        int monthInt = month.getValue();

        return monthInt;
    }

    public static WebElement waitVisibility(int time, By locator){
        // After clicking the button, wait for the dropdown content to become visible (if necessary)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void changeLocation(float latitude, float longitude, int accuracy){
        devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(latitude),Optional.of(-
                longitude),Optional.of(accuracy)));
    }




}
