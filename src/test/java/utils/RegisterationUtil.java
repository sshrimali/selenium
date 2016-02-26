package utils;

import java.sql.Driver;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;

/**
 * Created by sourabh on 2/25/16.
 */
public class RegisterationUtil extends BaseUtil{
    private final Properties suiteProperties;
    private final String testcaseId;
private String emailAddress;
private WebElement element;
private String loginLink;
private String signUpLink;
private String emailTextBox;
private String signUpButton; 

    public RegisterationUtil(Properties suiteDataProp, String testcaseId) throws Exception
    {
        this.suiteProperties = suiteDataProp;
        this.testcaseId = testcaseId;
        getTestDataContext();
        setElementId();
    }
    void setElementId() {
    loginLink="//span[contains(text(),'Log In')]";
    signUpLink="//a[contains(text(),'Sign up for an account')]";
    emailTextBox="//input[@placeholder='email@address.com']";
    signUpButton="userSignupButton";
    }
    
    private void getTestDataContext() {
        if (suiteProperties.containsKey(testcaseId + "_emailAddress")) {
            emailAddress = suiteProperties.getProperty(testcaseId + "_emailAddress");
        }
    }
    
    public void navigateToRegisterationPage() throws Exception
    {
    	//Thread.sleep(20000);
    	element = driver.findElement(By.xpath(loginLink));
    	element.click();
    	//Thread.sleep(20000);
    	System.out.println("TstSUcc");
    	
    	element = driver.findElement(By.xpath(signUpLink));
    	element.click();
       System.out.println("test data : " + emailAddress);
    }
	public void verifyElements() throws Exception {
		// TODO Auto-generated method stub
		navigateToRegisterationPage();
		element = driver.findElement(By.xpath(emailTextBox));
		element = driver.findElement(By.name(signUpButton));
	}
}
