/*
 * (c) guyrob.tripbot
 */

package com.guyrob.tripbot.ProductPages;

import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import java.net.URL;

public class ThingProductPage extends base {

    public String getThingName(){
        return driver.findElement(locate.TNGP_txt_searchResult).getText();
    }

    public String visitWebsite(){
        sleep(2000);
        waitVisibility(10, locate.TNGP_btn_visitWebsite);

        WebElement btn_visitWebsite = driver.findElement(locate.TNGP_btn_visitWebsite);
        WebElement father_VisitWebsite_href = getParentElement(btn_visitWebsite);

        String urlLink = btn_visitWebsite.getAttribute("href");
        URL url;
        // Try extract urlLink first from btn and if not working from father
        try {
            // Attempt to create a URL object from the href attribute of btn_visitWebsite
            url = new URL(urlLink);
        } catch (MalformedURLException e) {
            // If it's not a valid URL, use the href attribute of father_VisitWebsite_href
            urlLink = father_VisitWebsite_href.getAttribute("href");
        }


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
