import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.ProductPages.HotelProductPage;
import com.guyrob.tripbot.ProductPages.RentalProductPage;
import com.guyrob.tripbot.ProductPages.RestaurantProductPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.testdata;

import java.util.List;
import java.util.concurrent.TimeUnit;


// TODO not working - need to check
public class P1_sanityRentSearch extends base {
    HomePage homepage;
    SearchPage searchPage;
    RentalProductPage rentalProductPage;

    List<String> tabs;

    /** Test Data: */
    String vilaName = "Villa Riviera Eilat";
    String startDate = "2023-11-01";
    String endDate = "2023-11-04";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        searchPage = new SearchPage();
        rentalProductPage = new RentalProductPage();

        driver.manage().window().maximize();
        driver.get(testdata.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    @Test
    public void P1_searchRental(){
        homepage.SearchRental(vilaName);
        allure_Log("Search " + vilaName);
        Assert.assertTrue(vilaName.contains(searchPage.getSearchText()), "Wrong search rental!");
    }

    @Test
    public void P2_selectRental(){
        searchPage.selectProduct_ByIndex(1);
        tabs = switchTab(1);
        screenShot("Rentals", "P2_selectHotel");
        allure_LogAttachment("Selecting: " + vilaName, "Rentals", "P2_selectRental");
        Assert.assertTrue(vilaName.contains(rentalProductPage.getRentalName()), "Wrong rental selected!");
    }

    @Test
    public void P3_selectDates(){
        rentalProductPage.setDates(startDate, endDate);
        allure_Log("Start date: " + startDate + " End date: " + endDate);
        Assert.assertTrue(rentalProductPage.checkDates(startDate, endDate));
    }

    @Test
    public void P4_selectGuests(){
        rentalProductPage.setGuests(7);
        screenShot("Rentals", "P4_selectGuests");
        allure_LogAttachment("Guests", "Rentals", "P4_selectGuests");
        rentalProductPage.updateGuests();

        // TODO add assert
    }



}
