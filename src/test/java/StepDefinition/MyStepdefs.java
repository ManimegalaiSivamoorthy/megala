package StepDefinition;

import PageObjects.AddCustomerPage;
import PageObjects.LoginPage;
import PageObjects.SearchCustomerPage;
import PageObjects.SearchName;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MyStepdefs extends BaseClass {

    @Before
    public void setup() throws IOException {
        //reading properties
        /*configProp = new Properties();
        FileInputStream configPropFile = new FileInputStream("Config.properties");
        configProp.load(configPropFile);*/
        logger = Logger.getLogger("megala"); //added logger
        PropertyConfigurator.configure("log4j.properties");
        // to run on different browser
        //likewise add other browsers like firefox and IE
       // String br = configProp.getProperty("browser");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        logger.info("*****Launching Browser******");
    }
    @Given("User launch the chrome browser")
    public void userLaunchTheChromeBrowser() {
        loginPageObject = new LoginPage(driver); //creation of loginpage object //constructor in the pageobject is invoked
    }

    @When("user opens URL {string}")
    public void userOpensURL(String url) {
        logger.info("********Opening Url*********");
        driver.get(url);
        driver.manage().window().maximize();
    }

    @And("User enter Email as {string} and password as {string}")
    public void userEnterEmailAsAndPasswordAs(String email, String password) {
        logger.info("*****User enter email and password******");
        loginPageObject.setUserName(email);
        loginPageObject.setTextPassword(password);
    }

    @And("click on login")
    public void clickOnLogin() {
        logger.info("*****Click on login******");
        loginPageObject.clickLogin();
    }

    @Then("Page title should be {string}")
    public void pageTitleShouldBe(String title) {
        if(driver.getPageSource().contains("Login was unsuccessful")){
            driver.close();
            Assert.assertTrue(false);
        }else{
            Assert.assertEquals(title, driver.getTitle());
        }
    }

    @When("User clicks on log out")
    public void userClicksOnLogOut() throws InterruptedException {
        logger.info("*****click on logout******");
        loginPageObject.clickLogout();
        Thread.sleep(3000);
    }


    @And("close browser")
    public void closeBrowser() {
        logger.info("*****Closing Browser******");
        driver.quit();
    }

    //customer feature step definition.................
    @Then("User can view the Dashboard")
    public void userCanViewTheDashboard() throws InterruptedException {
        addCustomerPageObject = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration", addCustomerPageObject.getPageTitle());
        Thread.sleep(3000);
    }

    @When("User clicks on the customer menu")
    public void userClicksOnTheCustomerMenu() throws InterruptedException {
        addCustomerPageObject.clickOnCustomerMenu();
        Thread.sleep(2000);
    }

    @And("Click on the customer menu item")
    public void clickOnTheCustomerMenuItem() throws InterruptedException {
        addCustomerPageObject.clickOnCustomerItem();
        Thread.sleep(2000);
    }

    @And("click on add new button")
    public void clickOnAddNewButton() throws InterruptedException {
        logger.info("*****Adding new customer******");
        addCustomerPageObject.clickOnAddNew();
        Thread.sleep(3000);
    }

    @Then("User can view add new customer page")
    public void userCanViewAddNewCustomerPage() throws InterruptedException {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCustomerPageObject.getPageTitle());
        Thread.sleep(200);
    }

    @When("User enters customer information")
    public void userEntersCustomerInformation() throws InterruptedException {
        logger.info("*****Proving customer data******");
        String email = randomString()+"@gmail.com";
        addCustomerPageObject.setEmail(email);
        addCustomerPageObject.setPassword("test123");

        //Registered is default
        //The customer role cannot be both registered and guests
        addCustomerPageObject.setCustomerRoles("Guests");
        Thread.sleep(3000);
        addCustomerPageObject.setFirstName("Pavan");
        addCustomerPageObject.setLastName("Kumar");
        addCustomerPageObject.setManagerOfVendor("Vendor 2");
        addCustomerPageObject.setCompanyName("busyQA");
        addCustomerPageObject.setDateOfBirth("07/12/1980");
        addCustomerPageObject.writeAdminContent("This is testing...");
    }

    @And("click on save button")
    public void clickOnSaveButton() throws InterruptedException {
        logger.info("*****Saving the customer data******");
        addCustomerPageObject.clickSaveButton();
        Thread.sleep(3000);
    }

    @Then("User can view confirmation message {string}")
    public void userCanViewConfirmationMessage(String message) {
        logger.info("*****Successfully added the customer******");
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='alert alert-success alert-dismissable']")).getText()
                .contains("The new customer has been added successfully"));
    }

    // steps for searching a customer using email id
    @And("Create customer page object")
    public void createCustomerPageObject() {
        searchCustomerPageObject = new SearchCustomerPage(driver);
    }

    @And("Enter customer email")
    public void enterCustomerEmail() {
        logger.info("*****searching customer using emailid******");
        searchCustomerPageObject = new SearchCustomerPage(driver);
        searchCustomerPageObject.setEmail("victoria_victoria@nopCommerce.com");
    }

    @Then("User should find email on the search table")
    public void userShouldFindEmailOnTheSearchTable() {
        Boolean status = searchCustomerPageObject.SearchEmailById("victoria_victoria@nopCommerce.com");
        Assert.assertEquals(true, status);
    }

    @When("Click on search button")
    public void clickOnSearchButton() throws InterruptedException {
        searchCustomerPageObject.clickSearchButton();
        Thread.sleep(3000);
    }

    // search customer by name
    @And("Enter customer FirstName")
    public void enterCustomerFirstName() {
        searchNameObject = new SearchName(driver);
        searchNameObject.SetFirstName("Victoria");
    }

    @And("Enter customer LastName")
    public void enterCustomerLastName() {
        searchNameObject.SetLastName("Terces");
    }

    @Then("User should find name on the search table")
    public void userShouldFindNameOnTheSearchTable() {
        logger.info("*****Searching customer using first and last name******");
        Boolean status = searchNameObject.searchCustomerByName("Victoria Terces");
        Assert.assertEquals(true, status);
    }
}
