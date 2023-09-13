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

public class P3_sortHotels extends base {
    HomePage homepage;
    SearchPage searchPage;
    HotelProductPage hotelProductPage;
    List<String> tabs;

    /** Test Data: */
    String destinationName = "Italy, Rome";
    String destinationOnly = "Rome";
    String sort_Price = "Price (low to high)";
    int[] childAges = {2, 3};
    int rooms = 2, adults =4, children =2;


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
        screenShot("Hotels\\P3", "P1_searchDestination");
        allure_LogAttachment("Selecting: " + destinationName, "Hotels\\P3", "P1_searchDestination");
        Assert.assertTrue(searchPage.getSearchText().contains(destinationOnly), "Wrong search destination!");
    }

    @Test
    public void P2_selectDates(){
        sleep(1000);
        hotelProductPage.setDates(testdata.sanity_StartDate, testdata.sanity_EndDate, false);
        allure_Log("Start date: " + testdata.sanity_StartDate + " End date: " + testdata.sanity_EndDate);
//        Assert.assertTrue(searchPage.HOT_checkDates(testdata.hotels_StartDate, testdata.hotels_EndDate)); // TODO need to check why not working
    }

    @Test
    public void P3_selectGuests(){
        hotelProductPage.setGuests(rooms, adults);

        if (hotelProductPage.checkGuests(rooms, adults)){
            screenShot("Hotels\\P3", "P3_selectGuestsChild");
            allure_LogAttachment("Rooms: " + rooms + " Adults: " + adults, "Hotels\\P3", "P3_selectGuestsChild");
            hotelProductPage.updateGuests();
        } else {
            Assert.fail("Wrong guests selected!");
        }
    }

    @Test
    public void P4_sortPrice(){
        searchPage.sort_ByText(sort_Price);
        screenShot("Hotels\\P3", "P4_sortPrice");
        allure_LogAttachment("Sort by " + sort_Price , "Hotels\\P3", "P4_sortPrice");
        Assert.assertTrue(searchPage.checkSort(sort_Price));
    }



    @Test
    public void P5_HotelByPrice(){
        searchPage.selectProduct_ByPrice(); // TODO check why out sometimes of bounds
        tabs = switchTab(1);
        screenShot("Hotels\\P3", "P5_HotelByPrice");
        allure_LogAttachment("Selecting lowest price hotel", "Hotels\\P3", "P5_HotelByPrice");
        Assert.assertTrue(getCurrentURL_notContains(testdata.url));
    }



}
