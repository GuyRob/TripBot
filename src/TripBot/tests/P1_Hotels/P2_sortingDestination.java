import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.ProductPages.HotelProductPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.testdata;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.guyrob.tripbot.ProductPages.HotelProductPage.checkDates;

public class P2_sortingDestination extends base {
    HomePage homepage;
    SearchPage searchPage;
    HotelProductPage hotelProductPage;
    List<String> tabs;

    /** Test Data: */
    String destinationName = "Bucharest Romania, Europe";
    int rooms = 1, adults =2;


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
//        driver.quit();
    }

    @Test
    public void P1_searchDestination(){
        homepage.SearchHotel_Options(destinationName, 1);
        allure_Log("Search " + destinationName);
        screenShot("Hotels\\P2", "P1_searchDestination");
        allure_LogAttachment("Selecting: " + destinationName, "Hotels\\P2", "P1_searchDestination");
        Assert.assertTrue(destinationName.contains(searchPage.getSearchText()), "Wrong search destination!"); // TODO need to trim hotels
    }

    @Test
    public void P2_selectDates(){
        sleep(1000);
        hotelProductPage.setDates(testdata.hotels_StartDate, testdata.hotels_EndDate);
        allure_Log("Start date: " + testdata.hotels_StartDate + " End date: " + testdata.hotels_EndDate);
        Assert.assertTrue(checkDates(testdata.hotels_StartDate, testdata.hotels_EndDate));
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



}
