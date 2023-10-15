/*
 * (c) guyrob.tripbot
 */

package com.guyrob.tripbot.General;

import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoggedInPages extends base {


    /** Trips: */
    public void createTrip(String name) {
        driver.findElement(locate.LI_TRP_btn_createNewTrip).click();
        waitVisibility(5, locate.LI_TRP_inp_tripName);
        driver.findElement(locate.LI_TRP_inp_tripName).sendKeys(name);
        waitVisibility(5, locate.LI_TRP_btn_createTrip);
        driver.findElement(locate.LI_TRP_btn_createTrip).click();
    }

    public WebElement getTrip(int index){
        waitVisibility(5, locate.LI_TRP_List_Trips);
        List<WebElement> Trips = driver.findElements(locate.LI_TRP_List_Trips);
        return Trips.get(index);
    }

    public boolean isInTripTab(){
        waitVisibility(5, locate.LI_TRP_btn_createNewTrip);
        return driver.findElement(locate.LI_TRP_btn_createNewTrip).isDisplayed();
    }

    // TODO need to continue
    public void createTripAI(String destination, int days, String comingWith, List<String> things) {
        driver.findElement(locate.LI_TRP_btn_tripAI).click();
        waitVisibility(10, locate.LI_TRP_inp_tripAI_search);
        driver.findElement(locate.LI_TRP_inp_tripAI_search).sendKeys(destination);
        driver.findElement(locate.LI_TRP_btn_tripAI_next).click();
        waitVisibility(10, locate.LI_TRP_btn_tripAI_days);
        driver.findElement(locate.LI_TRP_btn_tripAI_days).click();

        // Selecting days
        if (Integer.parseInt(driver.findElement(locate.LI_TRP_txt_tripAI_days).getText()) != days) {
            while (Integer.parseInt(driver.findElement(locate.LI_TRP_txt_tripAI_days).getText()) < days) {
                driver.findElement(locate.LI_TRP_btn_tripAI_nextDay).click();
                sleep(1000);
            }
            while (Integer.parseInt(driver.findElement(locate.RENP_txt_guests).getText()) > days) {
                driver.findElement(locate.LI_TRP_btn_tripAI_prevDay).click();
                sleep(1000);
            }
        }
        driver.findElement(locate.LI_TRP_btn_tripAI_next).click();
        sleep(2000);

        // Select Coming with
        driver.findElement(locate.LI_TRP_btn_tripAI_comingWIth(comingWith)).click();
        driver.findElement(locate.LI_TRP_btn_tripAI_next).click();

        // TODO Things






    }
}
