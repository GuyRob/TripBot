
/*
 * (c) guyrob.tripbot
 */

package com.guyrob.tripbot.General;
import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

public class HomePage extends base {

    public void SearchHotel(String hotel) {
            driver.findElement(locate.HP_btn_SearchHotel).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            driver.findElement(locate.HP_inp_SearchHotel).sendKeys(hotel + Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

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

}
