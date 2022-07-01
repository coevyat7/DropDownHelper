import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropdownHelper {
    private WebDriver driver;

    public DropdownHelper(WebDriver driver){
        this.driver=driver;
    }
    public Select getSelect(WebElement element){
        Select select=new Select(element);
        return select;
    }
    public void selectByVisibleText(WebElement element,String value){
        getSelect(element).selectByVisibleText(value);
    }
    public void selectByIndex(WebElement element,int index){
        getSelect(element).selectByIndex(index);
    }
    public void selectByValue(WebElement element,String value){
        getSelect(element).selectByValue(value);
    }

    public  List<WebElement> getDropDownData(WebElement element){
        List<WebElement>list=getSelect(element).getOptions();
        return list;
    }
    public void selectBootStrapDropdown(WebElement element, List<WebElement> list, String target){
        element.click();
        for(WebElement element1:list){
            String value=element1.getText();
            if(value.equalsIgnoreCase(target)){
                element1.click();
                break;
            }
        }

    }

}
