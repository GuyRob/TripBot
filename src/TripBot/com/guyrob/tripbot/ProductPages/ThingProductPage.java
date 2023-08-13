/*
 * (c) guyrob.tripbot
 */

package com.guyrob.tripbot.ProductPages;

import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class ThingProductPage extends base {

    /** Details:*/
    public String getThingName(){
        return driver.findElement(locate.TNGP_txt_searchResult).getText();
    }

    public String visitWebsite(){
        waitVisibility(10, locate.TNGP_btn_visitWebsite);

        WebElement btn_visitWebsite = driver.findElement(locate.TNGP_btn_visitWebsite);
        WebElement father_VisitWebsite_href = getParent(btn_visitWebsite);
        String urlLink = father_VisitWebsite_href.getAttribute("href");

        btn_visitWebsite.click();
        driver.manage().timeouts().implicitlyWait(3 , TimeUnit.SECONDS);

        // Find the index of "www."
        int startIndex = urlLink.indexOf("www.") + 4;
        // Find the index of the next "."
        int endIndex = urlLink.indexOf(".", startIndex);

        String domain = urlLink.substring(startIndex, endIndex);
        return domain;
    }

}
