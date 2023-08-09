package com.guyrob.tripbot.HotelPages;

import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HotelProductPage extends base {

    /** General:*/
    public String getHotelName(){
        return driver.findElement(locate.HOTP_txt_searchResult).getText();
    }

    /** Dates:*/
    private void selectDate(String dateString) {
        try {
            final int FIX_YEAR = 1900;
            final int FIX_MONTH = 1;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);

            // Year
            List<WebElement> monthsYear = driver.findElements(locate.HOTP_txt_dateCurrentMonths_Year);
            String[] monthYear_parts = monthsYear.get(0).getText().split(" ");
            int currentYear = Integer.parseInt(monthYear_parts[monthYear_parts.length-1]);

            int selectedYear = date.getYear() + FIX_YEAR;

            while (selectedYear > currentYear){
                driver.findElement(locate.HOTP_btn_dateNext).click();
                sleep(1000);

                monthsYear = driver.findElements(locate.HOTP_txt_dateCurrentMonths_Year);
                monthYear_parts = monthsYear.get(0).getText().split(" ");
                currentYear = Integer.parseInt(monthYear_parts[1]);
            }

            // Month
            int currentMonth = convertMonthTextToInt(monthYear_parts[0]);
            int selectedMonth = date.getMonth() + FIX_MONTH;

            while (selectedMonth > currentMonth){
                driver.findElement(locate.HOTP_btn_dateNext).click();
                sleep(1000);

                monthsYear = driver.findElements(locate.HOTP_txt_dateCurrentMonths_Year);
                monthYear_parts = monthsYear.get(0).getText().split(" ");
                currentMonth = convertMonthTextToInt(monthYear_parts[0]);
            }


            // Day
            List<WebElement> days = driver.findElements(locate.HOTP_btn_dateDays);
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

    /** @param startDate - format : yyyy-MM-dd */
    public void setDates(String startDate, String endDate)   {
        try {
            actions = new Actions(driver);
            actions.moveToElement(driver.findElement(locate.HOTP_btn_checkIn)).perform();
            if (!driver.findElement(locate.HOTP_btn_checkIn).isSelected()) {
                driver.findElement(locate.HOTP_btn_checkIn).click();
            }

            selectDate(startDate);
            sleep(2000);
            selectDate(endDate);

        } catch (Exception e){
            Assert.fail("ERROR: Exception - " + e);
        }
    }


    public static boolean checkDates(String startDate, String endDate){
        try {
            String actualStartDate = driver.findElement(locate.HOTP_btn_checkIn).getText();
            String actualEndDate = driver.findElement(locate.HOTP_btn_checkOut).getText();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormatActual = new SimpleDateFormat("MM/dd/yy");
            Date selectedStartDate = dateFormat.parse(startDate);
            Date selectedEndDate = dateFormat.parse(endDate);
            String formattedStartDate = dateFormatActual.format(selectedStartDate);
            String formattedEndDate = dateFormatActual.format(selectedEndDate);

            return actualStartDate.contains(""+formattedStartDate) && actualEndDate.contains(""+formattedEndDate);
        } catch (Exception e){
            Assert.fail("ERROR: Exception - " + e);
            return false;
        }
    }

    /** Guests:*/
    public void setGuests(int rooms, int adults) {
        // Rooms
        if (!driver.findElement(locate.HOTP_txt_rooms).isDisplayed()) {
            driver.findElement(locate.HOTP_btn_guestsMenu).click();
        }

        if (Integer.parseInt(driver.findElement(locate.HOTP_txt_rooms).getText()) != rooms) {
            while (Integer.parseInt(driver.findElement(locate.HOTP_txt_rooms).getText()) < rooms) {
                driver.findElement(locate.HOTP_btn_rooms_next).click();
                sleep(1000);
            }
            while (Integer.parseInt(driver.findElement(locate.HOTP_txt_rooms).getText()) > rooms) {
                driver.findElement(locate.HOTP_btn_rooms_prev).click();
                sleep(1000);
            }
        }

        // Adults
        if (Integer.parseInt(driver.findElement(locate.HOTP_txt_adults).getText()) != adults) {
            while (Integer.parseInt(driver.findElement(locate.HOTP_txt_adults).getText()) < adults) {
                driver.findElement(locate.HOTP_btn_adults_next).click();
                sleep(1000);
            }
            while (Integer.parseInt(driver.findElement(locate.HOTP_txt_adults).getText()) > adults) {
                driver.findElement(locate.HOTP_btn_adults_prev).click();
                sleep(1000);
            }
        }
    }

    public void setGuests_children(int rooms, int adults, int children, int @NotNull []  childrenAges) {
        setGuests(rooms, adults);

        // Children
        if (Integer.parseInt(driver.findElement(locate.HOTP_txt_childrens).getText()) != children) {
            while (Integer.parseInt(driver.findElement(locate.HOTP_txt_childrens).getText()) < children) {
                driver.findElement(locate.HOTP_btn_childrens_next).click();
                sleep(1000);
            }
            while (Integer.parseInt(driver.findElement(locate.HOTP_txt_childrens).getText()) > children) {
                driver.findElement(locate.HOTP_btn_childrens_prev).click();
                sleep(1000);
            }
        }

        // Ages
        for (int i = 0; i < childrenAges.length; i++){
            driver.findElement(locate.childrenAges_Xpath(i)).click();
            // Locate and click the desired option within the dropdown menu
            List<WebElement> ageOption =  driver.findElements(locate.HOTP_txt_agesList);
            for (WebElement ele : ageOption){
                if (ele.getText().equals(String.valueOf(childrenAges[i]))){
                    ele.click();
                    break;
                }
            }
        }

    }

        public void updateGuests(){
        if (!driver.findElement(locate.HOTP_txt_rooms).isDisplayed()) {
            driver.findElement(locate.HOTP_btn_guestsMenu).click();
        }
        driver.findElement(locate.HOTP_inp_guestsUpdate).click();
    }

    public boolean checkGuests(int rooms, int adult){
        return String.valueOf(rooms).equals(driver.findElement(locate.HOTP_txt_rooms).getText()) && String.valueOf(adult).equals(driver.findElement(locate.HOTP_txt_adults).getText());
    }

    public boolean checkGuests_children(int rooms, int adults, int children, int @NotNull [] childages) {
        boolean childagesAssert = true;
        for (int i = 0; i < childages.length; i++){
            WebElement currentChild = driver.findElement(locate.childrenAges_Xpath(i));
            String currentChildText = currentChild.findElement(By.className("urOgU")).getText();
            if (!currentChildText.equals(String.valueOf(childages[i]))){
                childagesAssert = false;
                break;
            }
        }
        return checkGuests(rooms, adults) && String.valueOf(children).equals(driver.findElement(locate.HOTP_txt_childrens).getText()) && childagesAssert;
    }


}
