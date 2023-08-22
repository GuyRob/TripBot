
/*
 * (c) guyrob.tripbot
 */

package com.guyrob.tripbot.General;
import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends base {

    public void SearchHotel(String hotel) {
            driver.findElement(locate.HP_btn_SearchHotel).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            driver.findElement(locate.HP_inp_SearchHotel).sendKeys(hotel + Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    public void SearchHotel_Options(String destination, int index) {
        driver.findElement(locate.HP_btn_SearchHotel).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(locate.HP_inp_SearchHotel).sendKeys(destination);
        sleep(2000);
        List<WebElement> options = driver.findElements(locate.HP_inp_HotelOptionList);

        options.get(index-1).click();
    }

    public void searchThing(String thing) {
        driver.findElement(locate.HP_btn_SearchThing).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(locate.HP_inp_SearchThing).sendKeys(thing + Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    public void searchRestaurant(String thing) {
        driver.findElement(locate.HP_btn_SearchRestaurant).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(locate.HP_inp_SearchRestaurant).sendKeys(thing + Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    public void SearchRental(String rentName) {
        driver.findElement(locate.HP_btn_SearchRental).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(locate.HP_inp_SearchRental).sendKeys(rentName + Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
}
