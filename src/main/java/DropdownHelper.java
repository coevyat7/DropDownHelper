import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public record DropdownHelper(WebDriver driver) {

    public Select getSelect(WebElement element) {
        Select select = new Select(element);
        return select;
    }

    public void selectByVisibleText(WebElement element, String value) {
        getSelect(element).selectByVisibleText(value);
    }

    public void selectByIndex(WebElement element, int index) {
        getSelect(element).selectByIndex(index);
    }

    public void selectByValue(WebElement element, String value) {
        getSelect(element).selectByValue(value);
    }
    public void deSelectByValue(WebElement element,String value){
        Select select=new Select(element);
        select.deselectByValue(value);
    }

    public List<WebElement> getDropDownData(WebElement element) {
        List<WebElement> list = getSelect(element).getOptions();
        return list;
    }

    public void selectFromBootStrapDropdown(@NotNull WebElement element, List<WebElement> list, String target) {
        element.click();
        for (WebElement element1 : list) {
            String value = element1.getText();
            if (value.equalsIgnoreCase(target)) {
                element1.click();
                break;
            }
        }
    }
    public void multiSelect( List<WebElement> list, String ...value){
        if(!value[0].equalsIgnoreCase("All")){
            for(WebElement x:list){
               // String str=x.getText();
                //System.out.println(str);
                String str=x.getAttribute("id");
                for(String st:value){
                    if(str.equalsIgnoreCase(st)){
                        x.click();
                        break;
                    }
                }
            }

        }else{
            for(WebElement element1:list){
                element1.click();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=invokeBrowser();
        DropdownHelper dp=new DropdownHelper(driver);
        /*
        //Basic Select
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        WebElement countries=driver.findElement(By.name("country"));
        dp.selectByValue(countries,"ISRAEL");

        //Boostrap Select
        driver.get("https://www.hdfcbank.com/");
        WebElement value1=driver.findElement(By.cssSelector(".drp1 a"));
        List<WebElement>list1=driver.findElements(By.cssSelector(".dropdown1>li"));
        String target1="Cards";
        dp.selectFromBootStrapDropdown(value1,list1,target1);
        WebElement value2=driver.findElement(By.cssSelector(".drp2 a"));
        List<WebElement>list2=driver.findElements(By.cssSelector(".dropdown2>li"));
        String target2="SmartEMi";
        dp.selectFromBootStrapDropdown(value2, list2,target2);
        Thread.sleep(3500);
*/
        //MultiSelect
        driver.get("https://itera-qa.azurewebsites.net/home/automation/");
        List<WebElement>myList=driver.findElements(By.cssSelector("input[class*=form][type='checkbox']"));
        dp.multiSelect(myList,"Monday","Sunday");





    }
    public static WebDriver invokeBrowser(){
        WebDriver driver;
        try{
            driver= WebDriverManager.chromedriver().create();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
            driver.manage().deleteAllCookies();


        }catch (Exception e){
            e.printStackTrace();
            driver=null;
        }return driver;
    }
}
