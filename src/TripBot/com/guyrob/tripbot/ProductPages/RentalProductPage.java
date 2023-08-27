/*
 * (c) guyrob.tripbot
 */

package com.guyrob.tripbot.ProductPages;

import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RentalProductPage extends base {

    public String getRentalName() {
        return driver.findElement(locate.RENP_txt_searchResult).getText();
    }


    /** Dates: */
    private void selectDate(String dateString) {
        try {
            final int FIX_YEAR = 1900;
            final int FIX_MONTH = 1;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);

            // Year
            List<WebElement> monthsYear = driver.findElements(locate.RENP_txt_dateCurrentMonths_Year);
            String[] monthYear_parts = monthsYear.get(0).getText().split(" ");
            int currentYear = Integer.parseInt(monthYear_parts[monthYear_parts.length-1]);

            int selectedYear = date.getYear() + FIX_YEAR;

            while (selectedYear > currentYear){
                driver.findElement(locate.HOTP_btn_dateNext).click(); // Using hotel next
                sleep(1000);

                monthsYear = driver.findElements(locate.RENP_txt_dateCurrentMonths_Year);
                monthYear_parts = monthsYear.get(0).getText().split(" ");
                currentYear = Integer.parseInt(monthYear_parts[1]);
            }

            // Month
            int currentMonth = convertMonthTextToInt(monthYear_parts[0]);
            int selectedMonth = date.getMonth() + FIX_MONTH;

            while (selectedMonth > currentMonth){
                driver.findElement(locate.HOTP_btn_dateNext).click();
                sleep(1000);

                monthsYear = driver.findElements(locate.RENP_txt_dateCurrentMonths_Year);
                monthYear_parts = monthsYear.get(0).getText().split(" ");
                currentMonth = convertMonthTextToInt(monthYear_parts[0]);
            }


            // Day
            List<WebElement> days = driver.findElements(locate.RENP_btn_dateDays);
            int selectedDay = date.getDate();
            for (WebElement ele : days){
                if (Integer.parseInt(ele.getText()) == selectedDay){
                    ele.click();
                    break;
                }
            }


        } catch (Exception e){
            Assert.fail("ERROR: Exception - " + e);
        }
    }

    public void setDates(String startDate, String endDate) {
        try {
            driver.findElement(locate.RENP_btn_dates).click();

            selectDate(startDate);
            sleep(2000);
            selectDate(endDate);
            sleep(1000);

        } catch (Exception e){
            Assert.fail("ERROR: Exception - " + e);
        }
    }


    public void setGuests(int guests) {
        driver.findElement(locate.RENP_btn_guestsMenu).click();
        waitVisibility(3, locate.RENP_txt_guests);

        if (Integer.parseInt(driver.findElement(locate.RENP_txt_guests).getText()) != guests) {
            while (Integer.parseInt(driver.findElement(locate.RENP_txt_guests).getText()) < guests) {
                driver.findElement(locate.RENP_btn_guests_next).click();
                sleep(1000);
            }
            while (Integer.parseInt(driver.findElement(locate.RENP_txt_guests).getText()) > guests) {
                driver.findElement(locate.RENP_btn_guests_prev).click();
                sleep(1000);
            }
        }
    }


    public void updateGuests() {
        driver.findElement(locate.RENP_inp_guestsUpdate).click();
    }

    // TODO need to update
    public boolean checkDates(String startDate, String endDate) {
        return true;
    }
}
