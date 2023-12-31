/*
 * (c) guyrob.tripbot
 */
package com.guyrob.tripbot;

import io.qameta.allure.model.Status;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

import io.qameta.allure.*;

public class base {
    public static WebDriver driver;
    public static DevTools devTools;
    public static Actions actions;

    /** Debugging: */
    public void markOverlapElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }


    /**General: */

    public boolean getCurrentURL(String provider) {
        return driver.getCurrentUrl().toLowerCase().contains(provider.toLowerCase());
    }

    public boolean getCurrentURL_notContains(String originalSite) {
        return !(driver.getCurrentUrl().toLowerCase().contains(originalSite.toLowerCase()));
    }

    public WebElement getParentElement(WebElement son) {
        WebElement parentAnchor = (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].parentNode;", son);
        return parentAnchor;
    }

    public void sleep(int time) {
        try {

            Thread.sleep(time);
        } catch (Exception e) {
            allure_FailLog("Exception: " + e);
        }
    }

    public List<String> switchTab(int tabID) {
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandles);
        driver.switchTo().window(windowHandlesList.get(tabID));
        return windowHandlesList;
    }

    public static int convertMonthTextToInt(String monthText) {
        // Parse the month text using the Month enum
        Month month = Month.valueOf(monthText.toUpperCase(Locale.ENGLISH));
        // Get the month value as an integer (1 to 12)
        return month.getValue();
    }

    public static WebElement waitVisibility(int time, By locator) {
        // After clicking the button, wait for the dropdown content to become visible (if necessary)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // @TODO check if needed
    public void changeLocation(float latitude, float longitude, int accuracy) {
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(latitude), Optional.of(-
                longitude), Optional.of(accuracy)));
    }

    public void screenShot(String folder, String name) {
        try {
            File DestFile = new File("src\\ExtFiles\\screenShots\\" + folder + "\\" + name + ".png");

            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(SrcFile, DestFile);

        } catch (Exception e) {
            allure_FailLog("ERROR: Screenshot failed - " + e);
        }
    }

    public int Calc_daysDuration(String startDate, String endDate){
        // Parse the dates
        LocalDate StartDate = LocalDate.parse(startDate);
        LocalDate EndDate = LocalDate.parse(endDate);

        // Calculate the duration in days
        long daysBetween = ChronoUnit.DAYS.between(StartDate, EndDate);

        return (int) daysBetween;
    }

    /** Actions: */
    public void scroll_Element(WebElement ele){
        actions = new Actions(driver);
        actions.scrollToElement(ele).perform();
    }

    public void scroll_XY(int x, int y){
        actions = new Actions(driver);
        actions.scrollByAmount(x, y).perform();
    }

    public void hoverElement(WebElement ele) {
        // Use the moveToElement method to move the mouse pointer to the specified element
        actions = new Actions(driver);
        actions.moveToElement(ele).perform();
    }

    public void clickElement(WebElement ele){
        actions = new Actions(driver);
        actions.click(ele).build().perform();
    }

    /** ============================
     * Allure:
     ===============================*/
    public void allure_Log(String message) {
        Allure.step(message);
    }

    public static void allure_LogAttachment(String info, String folder, String name) {
        String imagePath = "src\\ExtFiles\\screenShots\\" + folder + "\\" + name + ".png";
        Path imageFilePath = Paths.get(imagePath);

        if (Files.exists(imageFilePath) && Files.isReadable(imageFilePath)) {
            try (InputStream imageStream = new FileInputStream(imageFilePath.toFile())) {
                Allure.addAttachment(info, imageStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            allure_FailLog("Image file not found or not readable.");
        }
    }

    public static void allure_FailLog(String message) {
        Allure.step(message, Status.FAILED);
    }

//    @TODO not working - need to check
    public void allure_GenerateReport() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "generateAllureReport.sh");
            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            System.out.println("Script execution finished with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
