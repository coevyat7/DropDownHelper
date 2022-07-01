import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class DropdownExample {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        invokeBrowser();
        driver.get("https://chercher.tech/practice/practice-dropdowns-selenium-webdriver");


        DropdownHelper dp = new DropdownHelper(driver);

        WebElement animals = driver.findElement(By.id("animals"));
        dp.selectByValue(animals, "babycat");
        WebElement products = driver.findElement(By.cssSelector("select#first"));
        dp.selectByIndex(products, 3);

        WebElement triggerClick = driver.findElement(By.cssSelector("button#custom"));
        List<WebElement> list = driver.findElements(By.cssSelector("ul.dropdown-menu>li>b"));
        String value = "td";
        dp.selectBootStrapDropdown(triggerClick, list, value);


    }

    public static void invokeBrowser() {
        try {
            driver = WebDriverManager.chromedriver().create();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().deleteAllCookies();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
