package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
    public WebDriver localdriver;
    public AddCustomerPage ( WebDriver remotedriver){
        localdriver = remotedriver;
        PageFactory.initElements(localdriver, this);
    }
    By customerMenu = By.xpath("//a[@href='#']//span[contains(text(), 'Customers')]");
    By customerItem = By.xpath("//span[@class='menu-item-title'][contains(text(), 'Customers')]");

    By AddNewButton = By.xpath("//a[@class='btn bg-blue']");

    By textEmail = By.xpath("//input[@id='Email']");
    By textPassword = By.xpath("//input[@id='Password']");

    By firstName = By.xpath("//input[@id='FirstName']");
    By lastName = By.xpath("//input[@id='LastName']");

    By radioBtnGenderMale = By.xpath("//input[@id='Gender_Male']");
    By radioBtnGenderFemale = By.xpath("//input[@id='Gender_Female']");

    By dateOfBirth = By.xpath("//input[@id='DateOfBirth']");

    By companyName = By.xpath("//input[@id='Company']");

    By customerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
    By listItemAdministrators = By.xpath("//li[contains(text(),'Administrators' )]");
    By listItemForumModerators = By.xpath("//li[contains(text(),'Forum Moderators' )]");
    By listItemGuests = By.xpath("//li[contains(text(),'Guests' )]");
    By listItemVendors= By.xpath("//li[contains(text(),'Vendors' )]");
    By listItemRegistered = By.xpath("//li[@class='k-button']//span[contains(text(),'Registered')]");

    By dropdownManagerOfVendor = By.xpath("//select[@id='VendorId']");

    By adminContent = By.xpath("//textarea[@id='AdminComment']");

    By saveButton = By.xpath("//button[@name='save']");

    //action methods

    public String getPageTitle(){
        return localdriver.getTitle();
    }

    public void clickOnCustomerMenu(){
        localdriver.findElement(customerMenu).click();
    }
    public void clickOnCustomerItem(){
        localdriver.findElement(customerItem).click();
    }
    public void clickOnAddNew(){
        localdriver.findElement(AddNewButton).click();
    }
    public void setEmail(String email){
        localdriver.findElement(textEmail).sendKeys(email);
    }
    public void setPassword(String password){
        localdriver.findElement(textPassword).sendKeys(password);
    }
    public void setFirstName(String fName){ localdriver.findElement(firstName).sendKeys(fName); }

    public void setLastName(String lName){
        localdriver.findElement(lastName).sendKeys(lName);
    }
    public void setGender(String gender){
        if(gender.equals("Male")){
            localdriver.findElement(radioBtnGenderMale).click();
        }else if(gender.equals("Female")){
            localdriver.findElement(radioBtnGenderFemale).click();
        }
        else {
            localdriver.findElement(radioBtnGenderMale).click(); //default
        }
    }
    public void setDateOfBirth(String dob){
        localdriver.findElement(dateOfBirth).click();
    }
    public void setCompanyName(String name){
        localdriver.findElement(companyName).click();
    }


    public void setCustomerRoles(String role) throws InterruptedException {
        if(!role.equals("Vendors")){
            localdriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[@class='k-icon k-delete']")).click();
        }
        localdriver.findElement(customerRoles).click();
        WebElement listItem;
        Thread.sleep(3000);
        switch (role) {
            case "Administrator":
                listItem = localdriver.findElement(listItemAdministrators);
                break;
            case "Guests":
                listItem = localdriver.findElement(listItemGuests);
                break;
            case "Vendors":
                listItem = localdriver.findElement(listItemVendors);
                break;
            case "Forum Moderators":
                listItem = localdriver.findElement(listItemForumModerators);
                break;
            case "Registered":
                listItem = localdriver.findElement(listItemRegistered);
                break;
            default:
                listItem = localdriver.findElement(listItemGuests);
                break;
        }
        listItem.click();
//        JavascriptExecutor js = (JavascriptExecutor)localdriver;
//        js.executeScript("arguments[0].click();", listItem);
    }
    public void setManagerOfVendor(String value){
        Select drp = new Select(localdriver.findElement(dropdownManagerOfVendor));
        drp.selectByVisibleText(value);
    }

    public void writeAdminContent(String msg) {
        localdriver.findElement(adminContent).sendKeys(msg);
    }

    public void clickSaveButton() throws InterruptedException {
        localdriver.findElement(saveButton).click();
        Thread.sleep(3000);
    }

}
