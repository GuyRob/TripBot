package com.guyrob.tripbot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.emulation.Emulation;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Month;
import java.util.*;

import io.qameta.allure.*;
import ru.yandex.qatools.allure.report.AllureReportBuilder;
import ru.yandex.qatools.allure.report.AllureReportBuilderException;


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

    public void screenShot(String folder, String name, String info){
        try {
            File DestFile = new File("src\\ExtFiles\\screenShots\\"+folder+"\\"+name+".png");

                TakesScreenshot scrShot = ((TakesScreenshot) driver);
                File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(SrcFile, DestFile);
//                System.out.println("LOG: " + info); TODO switch with log

        } catch (Exception e){
            System.out.println("ERROR: Screenshot failed - " + e);
        }
    }

    /** Allure: */

    // TODO - Fix - not working
    public void generateReport(){
        try {
            String allureVersion = "2.23.0"; // Replace with your Allure version
            File reportDirectory = new File("allure-report");
            File resultsDirectory = new File("allure-results");

            AllureReportBuilder allureReportBuilder = new AllureReportBuilder(allureVersion, reportDirectory);

            // Unpack the report face
            allureReportBuilder.unpackFace();

            // Process the results
            allureReportBuilder.processResults(resultsDirectory);

            System.out.println("Allure report generated successfully.");
        }
     catch (Exception e){
            System.out.println("Exception on generate report: " + e);
        }

    }

    public void allure_Log(String message) {
        Allure.step(message);
    }



        public void allure_LogWithAttachment(String folder, String name , String info) {
            allure_Log(info);
            attachScreenshot(folder, name);
    }

    private void attachScreenshot(String folder, String name) {
        try {
            String imagePath = "src\\ExtFiles\\screenShots\\" + folder + "\\" + name + ".png";

            // Load the screenshot file and attach it directly
            // You need to implement this part based on how you capture screenshots
            Allure.addAttachment(name, "image/png", imagePath);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }




}
