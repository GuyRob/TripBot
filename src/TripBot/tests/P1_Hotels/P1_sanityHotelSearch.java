import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.ProductPages.HotelProductPage;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.base;
import io.qameta.allure.Allure;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.guyrob.tripbot.ProductPages.HotelProductPage.checkDates;

public class P1_sanityHotelSearch extends base {
    HomePage homepage;
    SearchPage searchPage;
    HotelProductPage hotelProductPage;
    List<String> tabs;

    /** Test Data: */
    String hotelName = "Leonardo Privilege Hotel Eilat";
    String startDate = "2023-11-01";
    String endDate = "2023-11-04";
    int[] childAges = {2, 3, 4, 5};
    int rooms = 2, adults =3, children =4;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        searchPage = new SearchPage();
        hotelProductPage = new HotelProductPage();

        driver.manage().window().maximize();
        driver.get("https://www.tripadvisor.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void P1_searchHotel(){
        homepage.SearchHotel(hotelName);
        allure_Log("Search " + hotelName);
        Assert.assertTrue(hotelName.contains(searchPage.getSearchText()), "Wrong search hotel!");
    }

    @Test
    public void P2_selectHotel(){
        searchPage.selectProduct_ByName(hotelName);
        tabs = switchTab(1);
        screenShot("Hotels", "P2_selectHotel");
        allure_LogAttachment("Selecting: " + hotelName, "Hotels", "P2_selectHotel");
        Assert.assertTrue(hotelName.contains(hotelProductPage.getHotelName()), "Wrong hotel selected!");
    }

    @Test
    public void P3_selectDates(){
        hotelProductPage.setDates(startDate, endDate);
        allure_Log("Start date: " + startDate + " End date: " + endDate);
        Assert.assertTrue(checkDates(startDate, endDate));
    }

    @Test
    public void P4_selectGuests(){
        hotelProductPage.setGuests_children(rooms, adults, children, childAges);
        if (hotelProductPage.checkGuests_children(rooms, adults, children, childAges)){
            screenShot("Hotels", "P4_selectGuests");
            allure_LogAttachment("Rooms: " + rooms + " Adults: " + adults + " Children: " + children + " Children ages: " + childAges.toString(), "Hotels", "P4_selectGuests");
            hotelProductPage.updateGuests();
        } else {
            Assert.fail("Wrong guests selected!");
        }
    }


    @Test
    public void P5_selectDeal(){
        String vendor = hotelProductPage.selectDeal(1);
        tabs = switchTab(2);
        screenShot("Hotels", "P5_selectDeal");
        allure_LogAttachment("Deal", "Hotels", "P5_selectDeal");
        Assert.assertTrue(getCurrentURL(vendor), "Wrong hotel selected!");
    }


    }
