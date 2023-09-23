/*
 * (c) guyrob.tripbot
 */

package com.guyrob.tripbot.General;

import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
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

}
