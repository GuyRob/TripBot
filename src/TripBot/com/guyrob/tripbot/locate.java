/*
 * (c) guyrob.tripbot
 */
package com.guyrob.tripbot;

import org.openqa.selenium.By;

public class locate {
    /** HomePage */
    public static By HP_btn_Logo = By.xpath("//a[@href='/']");
    public static By HP_inp_SearchOptionList = By.xpath("//div[@id='typeahead_results']//a");


    public static By HP_btn_SearchHotel = By.xpath("//*[@href='/Hotels' or text()='Hotels']");
    public static By HP_inp_SearchHotel = By.xpath("((//input[@role='searchbox'])[2]) | //input[@placeholder='Where to?'] | //input[contains(text(), 'Hotel')] | //input[@placeholder='Hotel name or destination']");

    public static By HP_btn_SearchThing = By.xpath("//*[@href='/Attractions' or text()='Things to Do']");
    public static By HP_inp_SearchThing = By.xpath("((//input[@role='searchbox']) | //input[@placeholder='Where to?'])[3] | //input[@placeholder='Attraction, activity or destination'] | //input[@title='Search']");

    public static By HP_btn_SearchRental = By.xpath("//*[@href='/Rentals' or text()='Vacation Rentals']");
    public static By HP_inp_SearchRental = By.xpath("((//input[@role='searchbox']) | //input[@placeholder='Where to?'])[3] | //input[@placeholder='Destination'] | //input[@placeholder='Where do you want to go?']");

    public static By HP_btn_SearchRestaurant = By.xpath("//*[@href='/Restaurants' or text()='Restaurants']");
    public static By HP_inp_SearchRestaurant = By.xpath("((//input[@role='searchbox']) | //input[@placeholder='Where to?'])[3] | //input[@placeholder='Restaurant or destination']");

    // Sign In
    public static By HP_frame_signIn = By.xpath("//iframe[@title='regcontroller']");
    public static By HP_btn_profile = By.xpath("//a[@aria-label='Profile']");

    public static By HP_btn_signIn = By.xpath("//a[contains(@href, 'RegistrationController')]");
    public static By HP_btn_signIn_Options = By.xpath("//div[@id='regBody']//span[@class='textContainer']");
    public static By HP_inp_signIn_Email = By.xpath("//input[@id='regSignIn.email']");
    public static By HP_inp_signIn_Password = By.xpath("//input[@id='regSignIn.password']");
    public static By HP_btn_signIn_Submit = By.xpath("//div[@id='regSignIn']//button[@type='submit']");

    public static By HP_btn_signOut = By.id("menu-item-5");

    // Trips (Need to be sign in)
    public static By HP_btn_tripsTab = By.xpath("//div[@data-automation='topNav_trips']");
    public static By HP_btn_tripsPlanTab = By.id("menu-item-0");





    /** Search Page (General)*/
    public static By SP_txt_searchResult = By.xpath("//span[@class='title-query'] | //h1[@data-automation='header_geo_title'] | //div[@data-automation='main_h1']//h1");
    public static By SP_btn_productsList = By.xpath("//div[@class='result-title']/span | //button[@data-testid='view_deal_button']");
    public static By SP_txt_productsList_prices = By.xpath("//div[@class='hjfcb']//span[@class='fwoto']/span");

    public static By SP_btn_save = By.xpath("//button[@aria-label='Save to a trip']");

    // Filters
    public static String SP_xpath_filters = "//div[@data-automation='LeftRail']";
    public static By SP_txt_selectedFilters = By.xpath("//div[@data-test-target='filter-bar']//span/div"); // OLD: //div[@data-test-target='filter-bar']//text()


    // Sort
    public static By SP_btn_sort = By.xpath("//div[@data-automation='sort'] | //span[@data-automation='sort']");
    public static By SP_btn_sortOptions = By.xpath("//div[@data-automation='sort']//li[@role='none']//div/span | //span[@data-automation='sort']//div[@class='BHdOL w t UXISu q Wh wBoXT yydJZ sVRnI sHXIF']/div");

    // Hotels
    public static By SP_HOT_childsAge = By.xpath("//span[@class='NK']");
    public static By SP_HOT_btn_checkIn = By.xpath("//div[@data-automation='checkin']//span");
    public static By SP_HOT_btn_checkOut = By.xpath("//div[@data-automation='checkout']//span");

    // Things
    public static By SP_TNG_btn_TopAttractions = By.xpath("//section[@data-automation='WebPresentation_WebCategoryListShelf']//div[@class='hZuqH']//span/div");


    /** LoggedInPages */

    // Trips
    public static By LI_TRP_btn_createNewTrip = By.xpath("//button[@data-automation='trips-create-trip-default'] | //div[text()='Do it yourself']");
    public static By LI_TRP_inp_tripName = By.xpath("//input[@name='tripName']");
    public static By LI_TRP_btn_createTrip = By.xpath("//button[@data-automation='stat_modal_create_button']");
    public static By LI_TRP_List_Trips = By.xpath("//a//div[contains(@data-automation, 'trip-card')]");

    public static By LI_TRP_btn_saveToATrip = By.xpath("//button[contains(@data-automation, 'stat-modal-trip')]");

    /** HotelsProductPage */
    public static By HOTP_txt_searchResult = By.id("HEADING");

    // Dates (CheckIn)
    public static By HOTP_btn_checkIn = By.xpath("//div[@data-automation='checkin']");
    public static By HOTP_btn_checkOut = By.xpath("//div[@data-automation='checkout']");

    public static By HOTP_btn_datePicker = By.xpath("//div[@data-testid='day_picker']");
    public static By HOTP_btn_dateDays = By.xpath("//div[@role='row']//div[@role='gridcell']/div/div[1] | //div[@role='row']//div[@role='gridcell']"); // OLD //div[@role='row']//div[@role='gridcell']
    public static By HOTP_txt_dateCurrentMonths_Year = By.xpath("//div[@role='heading']//div | //div[@data-automation='dayCalendar']//h2");

    public static By HOTP_btn_datePrev = By.xpath("//button[@data-testid='nav_prev'] | //button[@aria-label='Previous month']");
    public static By HOTP_btn_dateNext = By.xpath("//button[@data-testid='nav_next'] | //button[@aria-label='Next month']");

    // Guests
    public static By HOTP_btn_guestsMenu = By.xpath("//div[@data-automation='guest']");
    public static By HOTP_inp_guestsUpdate = By.xpath("//div[@data-automation='guestsUpdateBtn'] | //button[@data-automation='guestsUpdateBtn']");

    public static By HOTP_txt_rooms = By.xpath("//span[@data-automation='roomsNum'] | //div[@data-automation='roomsNum']");
    public static By HOTP_btn_rooms_prev = By.xpath("//button[@data-automation='roomsLess']");
    public static By HOTP_btn_rooms_next = By.xpath("//button[@data-automation='roomsMore']");


    public static By HOTP_txt_adults = By.xpath("//span[@data-automation='adultsNum'] | //div[@data-automation='adultsNum']");
    public static By HOTP_btn_adults_prev = By.xpath("//button[@data-automation='adultsLess']");
    public static By HOTP_btn_adults_next = By.xpath("//button[@data-automation='adultsMore']");

    public static By HOTP_txt_childrens = By.xpath("//span[@data-automation='childrenNum'] | //div[@data-automation='childrenNum']");
    public static By HOTP_btn_childrens_prev = By.xpath("//button[@data-automation='childrenLess']");
    public static By HOTP_btn_childrens_next = By.xpath("//button[@data-automation='childrenMore']");
    public static By HOTP_txt_agesList = By.xpath("//div[@class='QTOPt Z R2']//button/span | //li[@role='none']");


    public static By childrenAges_Xpath(int index){
        return By.xpath("//button[@data-automation='child" + index + "']");
    }

    // Deals
    public static By HOTP_btn_Deals = By.xpath("//div[@id='DEALS']//a");


    /** ThingsPages */
    public static By TNGP_txt_searchResult = By.xpath("//h1[@data-automation='mainH1']");
    public static By TNGP_btn_visitWebsite = By.xpath("//div[@data-automation='WebPresentation_PoiOverviewWeb']//span[contains(text(), 'Visit website')] | //a[@rel='nofollow']");

    /** RentalProductPage*/
    public static By RENP_txt_searchResult = By.xpath("//h1[@data-test-target='rental-detail-header']");
    public static By RENP_btn_dates = By.xpath("//div[@data-automation='checkInCheckOutPicker']");

    // Date
    public static By RENP_txt_dateCurrentMonths_Year = By.xpath("//div[@class='dQscM']//div[@data-automation='dayCalendar']//h2"); // OLD //div[@data-automation='dayCalendar']//h2
    public static By RENP_btn_dateDays = By.xpath("//div[@class='dQscM']//div[@role='row']//div[@role='gridcell' and @aria-disabled='false']//div");

    public static By RENP_btn_dateNext = By.xpath("//div[@class='dQscM']//button[@data-testid='nav_next'] | //div[@class='dQscM']//button[@aria-label='Next month']");


    // Guests
    public static By RENP_btn_guestsMenu = By.xpath("//div[@data-automation='guestPickerField']");
    public static By RENP_inp_guestsUpdate = By.xpath("(//div[@data-automation='guestMix']//span)[2]");

    public static By RENP_txt_guests = By.xpath("(//div[@data-automation='guestMix']//span)[1]");
    public static By RENP_btn_guests_prev = By.xpath("//div[@data-automation='guestMix']//button[@title='decrease']");
    public static By RENP_btn_guests_next = By.xpath("//div[@data-automation='guestMix']//button[@title='increase']");




    /** RestaurantPage */
    public static By RSTP_txt_searchResult = By.xpath("//h1[@data-test-target='top-info-header']");
    public static By RSTP_btn_visitWebsite = By.xpath("//div[@data-test-target='restaurant-detail-info']//a[contains(text(), 'Website')]");



}
