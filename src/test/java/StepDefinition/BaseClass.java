package StepDefinition;

import PageObjects.AddCustomerPage;
import PageObjects.LoginPage;
import PageObjects.SearchCustomerPage;
import PageObjects.SearchName;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public LoginPage loginPageObject;  //declaration of pageobject object to access the class
    public AddCustomerPage addCustomerPageObject;
    public SearchCustomerPage searchCustomerPageObject;
    public SearchName searchNameObject;
    public static Logger logger;
    //public Properties configProp;

    // method created for generating unique email id for everry customer
    public static String randomString(){
        String generatedString1 = RandomStringUtils.randomAlphabetic(5);
        return generatedString1;
    }
}
