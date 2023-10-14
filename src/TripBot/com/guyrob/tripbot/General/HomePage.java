
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

    public void clickLogo(){
        driver.findElement(locate.HP_btn_Logo).click();
        waitVisibility(5000, locate.HP_btn_SearchHotel);
    }

    public void SearchHotel(String hotel) {
            driver.findElement(locate.HP_btn_SearchHotel).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            driver.findElement(locate.HP_inp_SearchHotel).sendKeys(hotel + Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    public void SearchHotel_SelectOption(String destination, int index) {
        driver.findElement(locate.HP_btn_SearchHotel).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(locate.HP_inp_SearchHotel).sendKeys(destination);
        sleep(2000);
        List<WebElement> options = driver.findElements(locate.HP_inp_SearchOptionList);

        options.get(index-1).click();
    }

    public void searchThing(String thing) {
        driver.findElement(locate.HP_btn_SearchThing).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(locate.HP_inp_SearchThing).sendKeys(thing + Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    public void SearchThing_SelectOption(String destination, int index) {
        driver.findElement(locate.HP_btn_SearchThing).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(locate.HP_inp_SearchThing).sendKeys(destination);
        sleep(2000);
        List<WebElement> options = driver.findElements(locate.HP_inp_SearchOptionList);

        options.get(index-1).click();
    }

    public void searchThingBtn() {
        driver.findElement(locate.HP_btn_SearchThing).click();
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

    // Sign In
    public void signIn_Email(String email, String password){
        driver.findElement(locate.HP_btn_signIn).click();
        sleep(2000);

        WebElement ele_signRegister = driver.findElement(locate.HP_frame_signIn);
        driver.switchTo().frame(ele_signRegister);

        List <WebElement> signInOptions = driver.findElements(locate.HP_btn_signIn_Options);
        signInOptions.get(1).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(locate.HP_inp_signIn_Email).sendKeys(email);
        driver.findElement(locate.HP_inp_signIn_Password).sendKeys(password);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        driver.findElement(locate.HP_btn_signIn_Submit).click();
        sleep(2000);
        driver.switchTo().defaultContent();
    }

    public void signOut() {
        driver.findElement(locate.HP_btn_profile).click();
        waitVisibility(5, locate.HP_btn_signOut);
        driver.findElement(locate.HP_btn_signOut).click();
        sleep(2000);
    }

    public boolean checkSignIn(){
        return driver.findElement(locate.HP_btn_profile).isDisplayed();
    }

    public boolean checkSignOut(){
        return driver.findElement(locate.HP_btn_signIn).isDisplayed();
    }

    public void selectTripsTab() {
        driver.findElement(locate.HP_btn_tripsTab).click();
        waitVisibility(5, locate.HP_btn_tripsPlanTab);
        driver.findElement(locate.HP_btn_tripsPlanTab).click();
        sleep(2000);
    }

}
