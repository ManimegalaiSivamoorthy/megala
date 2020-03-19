package PageObjects;

import Utilities.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchName {
    public WebDriver localdriver;
    public WaitHelper waitHelper;
    public SearchName(WebDriver remotedriver){
        localdriver = remotedriver;
        PageFactory.initElements(localdriver,this);
        waitHelper = new WaitHelper(localdriver);
    }
    @FindBy(how = How.XPATH, using = "//input[@id='SearchFirstName']")
    @CacheLookup
    WebElement searchFirstName;

    @FindBy(how = How.XPATH,using = "//input[@id='SearchLastName']")
    @CacheLookup
    WebElement searchLastName;
    @FindBy(how = How.XPATH , using = "//table[@id='customers-grid']//tbody/tr")
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH , using = "//table[@id='customers-grid']//tbody/tr/td")
    List<WebElement> tableColumns;

    @FindBy(how = How.XPATH , using = "//table[@id='customers-grid']")
    @CacheLookup
    WebElement table;

    public void SetFirstName(String fname){
        waitHelper.WaitForElement(searchFirstName,30);
        searchFirstName.clear();
        searchFirstName.sendKeys(fname);
    }

    public void SetLastName(String lname){
        waitHelper.WaitForElement(searchLastName,30);
        searchLastName.clear();
        searchLastName.sendKeys(lname);
    }
    public int getNoOfRows(){
        return (tableRows.size());
    }

    public int getNoOfColumns(){
        return (tableColumns.size());
    }
    public Boolean searchCustomerByName(String Name){
        Boolean flag = false;
        for(int i=1; i<= getNoOfRows(); i++){
            String name = table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[3]")).getText();
            String [] names = name.split(" ");
            if(names[0].equals("Victoria") && names[1].equals("Terces")){
                flag = true;
            }
        }
        return  flag;
    }
}
