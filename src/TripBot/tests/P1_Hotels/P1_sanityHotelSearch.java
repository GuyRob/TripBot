import com.guyrob.tripbot.HomePage.HomePage;
import com.guyrob.tripbot.HotelPages.HotelProductPage;
import com.guyrob.tripbot.HotelPages.SearchHotelPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class P1_sanityHotelSearch extends base {
    HomePage homepage;
    SearchHotelPage searchHotelPage;
    HotelProductPage hotelProductPage;

    // Test Data
    String hotelName = "Leonardo Privilege Hotel Eilat";
    String startDate = "2023-09-01";
    String endDate = "2023-09-04";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        searchHotelPage = new SearchHotelPage();
        hotelProductPage = new HotelProductPage();

        driver.manage().window().maximize();
        driver.get("https://www.tripadvisor.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    @Test
    public void P1_searchHotel(){
        homepage.SearchHotel(hotelName);
        Assert.assertTrue(hotelName.contains(searchHotelPage.getSearchText()));
    }

    @Test
    public void P2_selectHotel(){
        searchHotelPage.selectHotel_ByName(hotelName);
        List<String> tabs = switchTab(1);
        Assert.assertTrue(hotelName.contains(hotelProductPage.getSearchText()));
    }

    @Test
    public void P3_selectDates(){
        hotelProductPage.setCheckIn(startDate, endDate);
    }

    @Test
    public void P4_selectGuests(){
        int[] childages = {2, 3, 4, 5};
        hotelProductPage.setGuests_childrens(2, 3, 4, childages);
        hotelProductPage.updateGuests();
    }

    }
