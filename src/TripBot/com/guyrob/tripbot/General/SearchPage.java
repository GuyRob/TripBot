/*
 * (c) guyrob.tripbot
 */

package com.guyrob.tripbot.General;
import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends base {



    public void selectProduct_ByName(String name){
        waitVisibility(5, locate.SP_btn_productsList);
        List<WebElement> products = driver.findElements(locate.SP_btn_productsList);
        for (WebElement ele : products){
            if (ele.getText().equals(name)){
                ele.click();
            }
        }
    }

    public void selectProduct_ByIndex(int index){
        waitVisibility(5, locate.SP_btn_productsList);
        List<WebElement> products = driver.findElements(locate.SP_btn_productsList);
        products.get(index-1).click();
    }

    public void selectProduct_ByPrice() {
        int lowestPrice = Integer.MAX_VALUE;
        int lowestPriceIndex = -1;
        waitVisibility(5, locate.SP_btn_productsList);
        List<WebElement> products = driver.findElements(locate.SP_btn_productsList);
        List<WebElement> products_Prices = driver.findElements(locate.SP_txt_productsList_prices);

        for (int i = 0; i < products.size(); i++) {
            String priceText = products_Prices.get(i).getText(); // Use getText() directly
            String numericOnlyText = priceText.replaceAll("[^0-9]", "");
            int priceInt = Integer.parseInt(numericOnlyText);

            if (priceInt < lowestPrice) {
                lowestPrice = priceInt;
                lowestPriceIndex = i;
            }
        }

        if (lowestPriceIndex != -1) {
            products.get(lowestPriceIndex).click();
        }
    }

    public String getSearchText(){
        return driver.findElement(locate.SP_txt_searchResult).getText();
    }

    // Filter
    public WebElement filterEle_ByName (String name){
        waitVisibility(5 , By.xpath(locate.SP_xpath_filters));
        return driver.findElement(By.xpath(locate.SP_xpath_filters+"//div[@class='CHHoy']//h3[contains(text(), '"+name+"')]"));
    }

    public void filterOpt_ByText(WebElement filterEle, String text){
        List <WebElement> options = filterEle.findElements(By.xpath("//span[@data-automation] | //span[normalize-space(text())]"));
        for (WebElement ele : options){
            if (ele.getText().equals(text)){
                ele.click();
                sleep(4000);
                break;
            }
        }
    }

    public boolean checkFilter(String filter) {
        List <WebElement> filters =  driver.findElements(locate.SP_txt_selectedFilters);

        for (WebElement ele : filters){
            if (ele.getText().equals(filter)){
                return true;
            }
        }
                return false;
    }

    // Sort
    public void sort_ByText(String sortName) {
        waitVisibility(5, locate.SP_btn_sort);
        driver.findElement(locate.SP_btn_sort).click();
        List <WebElement> options = driver.findElements(locate.SP_btn_sortOptions);
        for (WebElement ele : options){
            if (ele.getText().equals(sortName)){
                ele.click();
                sleep(4000);
                break;
            }
        }
    }

    public boolean checkSort(String sort) {
        return driver.findElement(locate.SP_btn_sort).getText().equals(sort);
    }

    /** Hotels: */
    public boolean HOT_checkDates(String startDate, String endDate) { // TODO not working need to fix - in search page the date is without year
        try {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat requiredDateFormat = new SimpleDateFormat("MM/dd/yy");
            SimpleDateFormat SP_Format = new SimpleDateFormat("E, MMM dd");

            // Actual (web)
            String actualStartDate = driver.findElement(locate.SP_HOT_btn_checkIn).getText();
            String actualEndDate = driver.findElement(locate.SP_HOT_btn_checkOut).getText();

            Date dateActualStart = SP_Format.parse(actualStartDate);
            Date dateActualSEnd = SP_Format.parse(actualEndDate);

            // Set the current year to the parsed date
            Calendar calendarStart = Calendar.getInstance();
            calendarStart.setTime(dateActualStart);
            calendarStart.set(Calendar.YEAR, currentYear);

            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(dateActualSEnd);
            calendarEnd.set(Calendar.YEAR, currentYear);

            String formattedActualStartDate = requiredDateFormat.format(calendarStart.getTime());
            String formattedActualEndDate = requiredDateFormat.format(calendarEnd.getTime());

            // Selected (input)
            Date selectedStartDate = dateFormat.parse(startDate);
            Date selectedEndDate = dateFormat.parse(endDate);

            // Format input dates using requiredDateFormat
            String formattedStartDate = requiredDateFormat.format(selectedStartDate);
            String formattedEndDate = requiredDateFormat.format(selectedEndDate);


            System.out.println("Actual: s " + formattedActualStartDate);
            System.out.println("Actual: e " + formattedActualEndDate);

            System.out.println("selected: s " + formattedStartDate);
            System.out.println("selected: e " + formattedEndDate);




            return true; // TODO need to update
        } catch (Exception e) {
            Assert.fail("ERROR: Exception - " + e);
            return false;
        }
    }



}
