import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.ProductPages.HotelProductPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.testdata;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class P2_filterDestination extends base {
    HomePage homepage;
    SearchPage searchPage;
    HotelProductPage hotelProductPage;
    List<String> tabs;

    /** Test Data: */
    String destinationName = "Bucharest Romania, Europe";
    String destinationOnly = "Bucharest";
    int rooms = 2, adults =3;
    String filter_Deals = "Deals";
    String Deals_refund = "Fully refundable";
    String filter_Amenities = "Amenities";
    String Amenities_breakfast = "Breakfast included";


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        searchPage = new SearchPage();
        hotelProductPage = new HotelProductPage();

        driver.manage().window().maximize();
        driver.get(testdata.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void P1_searchDestination(){
        homepage.SearchHotel_SelectOption(destinationName, 1);
        allure_Log("Search " + destinationName);
        screenShot("Hotels\\P2", "P1_searchDestination");
        allure_LogAttachment("Selecting: " + destinationName, "Hotels\\P2", "P1_searchDestination");
        Assert.assertTrue(searchPage.getSearchText().contains(destinationOnly), "Wrong search destination!");
    }

    @Test
    public void P2_selectDates(){
        sleep(1000);
        hotelProductPage.setDates(testdata.sanity_StartDate, testdata.sanity_EndDate, false);
        allure_Log("Start date: " + testdata.sanity_StartDate + " End date: " + testdata.sanity_EndDate);
//        Assert.assertTrue(searchPage.HOT_checkDates(testdata.hotels_StartDate, testdata.hotels_EndDate)); // TODO need to fix - in search page date is without year
    }

    @Test
    public void P3_selectGuests(){
        hotelProductPage.setGuests(rooms, adults);
        if (hotelProductPage.checkGuests(rooms, adults)){
            screenShot("Hotels\\P2", "P3_selectGuests");
            allure_LogAttachment("Rooms: " + rooms + " Adults: " + adults, "Hotels\\P2", "P3_selectGuests");
            hotelProductPage.updateGuests();
        } else {
            Assert.fail("Wrong guests selected!");
        }
    }

    @Test
    public void P4_filterDeals(){
        WebElement deals = searchPage.filterEle_ByName(filter_Deals);
        searchPage.filterOpt_ByText(deals, Deals_refund);
        allure_Log("Filtering " + Deals_refund);
        Assert.assertTrue(searchPage.checkFilter(Deals_refund));
    }

    @Test
    public void P5_filterAmenities(){
        WebElement amenities = searchPage.filterEle_ByName(filter_Amenities);
        searchPage.filterOpt_ByText(amenities, Amenities_breakfast);
        screenShot("Hotels\\P2", "P5_filterAmenities");
        allure_LogAttachment("Filtering " + Amenities_breakfast, "Hotels\\P2", "P5_filterAmenities");
        Assert.assertTrue(searchPage.checkFilter(Amenities_breakfast));
    }

    @Test
    public void P6_selectHotel(){
        searchPage.selectProduct_ByIndex(1);
        tabs = switchTab(1);
        screenShot("Hotels\\P2", "P6_selectHotel");
        allure_LogAttachment("Selecting hotel", "Hotels\\P2", "P6_selectHotel");
        Assert.assertTrue(getCurrentURL_notContains(testdata.url));
    }



}
