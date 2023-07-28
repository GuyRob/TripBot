package HomePage;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HomePage {
    Locate locate = new Locate();
    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void SearchHotel(String hotel) {
        driver.findElement(locate.btn_SearchHotel).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


    }
}
