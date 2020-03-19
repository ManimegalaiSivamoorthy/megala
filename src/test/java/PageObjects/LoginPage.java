package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver loginDriver;
    public LoginPage(WebDriver constructorDriver){
        loginDriver = constructorDriver;
        PageFactory.initElements(constructorDriver , this);
    }
    @FindBy(id = "Email")
    @CacheLookup
    WebElement textEmail;

    @FindBy(id = "Password")
    @CacheLookup
    WebElement textPassword;

    @FindBy(xpath = "//input[@value=\"Log in\"]")
    @CacheLookup
    WebElement loginButton;

    @FindBy(linkText = "Logout")
    @CacheLookup
    WebElement logoutButton;

    public void setUserName(String uName){
        textEmail.clear();
        textEmail.sendKeys(uName);
    }
    public void setTextPassword(String password){
        textPassword.clear();
        textPassword.sendKeys(password);
    }
    public void clickLogin(){
        loginButton.click();
    }
    public void clickLogout(){
        logoutButton.click();
    }
}
