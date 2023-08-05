import com.guyrob.tripbot.HomePage;
import com.guyrob.tripbot.SearchHotelPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class P1_sanityHotelSearch extends base {
    HomePage homepage;
    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        driver.manage().window().maximize();
        driver.get("https://www.tripadvisor.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void searchHotel(){
        homepage.SearchHotel("Leonardo Privilege Hotel Eilat");
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        searchHotelPage.selectHotel_ByName("Leonardo Privilege Hotel Eilat");
    }

    }
