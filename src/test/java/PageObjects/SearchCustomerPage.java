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

public class SearchCustomerPage {
    public WebDriver localdriver;
    public WaitHelper waitHelper;
    public SearchCustomerPage(WebDriver remotedriver){
        localdriver = remotedriver;
        PageFactory.initElements(localdriver,this);
        waitHelper = new WaitHelper(localdriver);
    }


    @FindBy(how = How.XPATH,using = "//input[@id='SearchEmail']")
    @CacheLookup
    WebElement  searchEmail;

    @FindBy(how = How.XPATH,using = "//*[@id='search-customers']")
    @CacheLookup
    WebElement searchButton;

    @FindBy(how = How.XPATH , using = "//table[@class='table table-bordered table-hover table-striped dataTable no-footer'] [@role='grid'] [@style='margin-left: 0px; width: 824px;']")
    @CacheLookup
    WebElement tableSearchResult;

    @FindBy(how = How.XPATH , using = "//table[@id='customers-grid']")
    @CacheLookup
    WebElement table;

    @FindBy(how = How.XPATH , using = "//table[@id='customers-grid']//tbody/tr")
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH , using = "//table[@id='customers-grid']//tbody/tr/td")
    List<WebElement> tableColumns;

    //action methods
    public void setEmail(String email){
        waitHelper.WaitForElement(searchEmail,30);
        searchEmail.clear();
        searchEmail.sendKeys(email);
    }



    public int getNoOfRows(){
        return (tableRows.size());
    }

    public int getNoOfColumns(){
        return (tableColumns.size());
    }

    public Boolean SearchEmailById(String emailId){
        Boolean flag = false;
        for(int i =1; i<= getNoOfRows(); i++){
            String email = table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[2]")).getText();
            System.out.println(email);
            if(email.equals("victoria_victoria@nopCommerce.com")){
                flag = true;
            }
        }
        return flag;
    }

    public void clickSearchButton(){
        searchButton.click();
    }
}
