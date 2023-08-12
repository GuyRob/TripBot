import com.guyrob.tripbot.HomePage.HomePage;
import com.guyrob.tripbot.HotelPages.HotelProductPage;
import com.guyrob.tripbot.HotelPages.SearchHotelPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.report.AllureReportBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.guyrob.tripbot.HotelPages.HotelProductPage.checkDates;

public class P1_sanityHotelSearch extends base {
    HomePage homepage;
    SearchHotelPage searchHotelPage;
    HotelProductPage hotelProductPage;
    List<String> tabs;

    // Test Data
    String hotelName = "Leonardo Privilege Hotel Eilat";
    String startDate = "2023-09-01";
    String endDate = "2023-09-04";
    int[] childAges = {2, 3, 4, 5};
    int rooms = 2, adults =3, children =4;

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
        allure_Log("search");
        screenShot("Hotels", "P1_searchHotel", "search");
        Assert.assertTrue(hotelName.contains(searchHotelPage.getSearchText()), "Wrong search hotel!");
    }

    @Test
    public void P2_selectHotel(){
        searchHotelPage.selectHotel_ByName(hotelName);
        tabs = switchTab(1);
        screenShot("Hotels", "P2_selectHotel", "select");
        allure_LogWithAttachment("Hotels", "P2_selectHotel", "select");
        Assert.assertTrue(hotelName.contains(hotelProductPage.getHotelName()), "Wrong hotel selected!");
    }

    @Test
    public void P3_selectDates(){
        hotelProductPage.setDates(startDate, endDate);
        screenShot("Hotels", "P3_selectDates", "dates");
        Assert.assertTrue(checkDates(startDate, endDate));
    }

    @Test
    public void P4_selectGuests(){
        hotelProductPage.setGuests_children(rooms, adults, children, childAges);
        if (hotelProductPage.checkGuests_children(rooms, adults, children, childAges)){
            screenShot("Hotels", "P4_selectGuests", "guests");
            hotelProductPage.updateGuests();
        } else {
            Assert.fail("Wrong guests selected!");
        }
    }


    @Test
    public void P5_selectDeal(){
        String vendor = hotelProductPage.selectDeal(1);
        tabs = switchTab(2);
        screenShot("Hotels", "P5_selectDeal", "deal");
        Assert.assertTrue(hotelProductPage.checkDealURL(vendor), "Wrong hotel selected!");
    }


    }
