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
	private String regConfirmationText1;
	private String regConfirmationText2;
	private String regConfirmationText3;
	private String errorMessageLoc;
	private String expectedErrorMessage;

	public RegisterationUtil(Properties suiteDataProp, String testcaseId) throws Exception
	{
		this.suiteProperties = suiteDataProp;
		this.testcaseId = testcaseId;
		// read all test data
		getTestDataContext();
		//configure all element locators
		setElementId();
	}
	void setElementId() {
		// Configure all locators
		loginLink="//span[contains(text(),'Log In')]";
		signUpLink="//a[contains(text(),'Sign up for an account')]";
		emailTextBox="//input[@placeholder='email@address.com']";
		signUpButton="userSignupButton";
		regConfirmationText1="//*[contains(text(),'Thanks for registering')]";
		regConfirmationText2="//*[contains(text(),'We have sent a verification email to %s')]";
		regConfirmationText3="//*[contains(text(),'Please check your inbox and click the link to activate your account.')]";
		errorMessageLoc="//div/p[contains(text(),'%s')]";
	}

	private void getTestDataContext() {
		if (suiteProperties.containsKey(testcaseId + "_emailAddress")) {
			emailAddress = suiteProperties.getProperty(testcaseId + "_emailAddress");
		}
		if (suiteProperties.containsKey(testcaseId + "_invalidAddress")) {
			emailAddress = suiteProperties.getProperty(testcaseId + "_invalidAddress");
		}
		if (suiteProperties.containsKey(testcaseId + "_expectedErrorMessage")) {
			expectedErrorMessage = suiteProperties.getProperty(testcaseId + "_expectedErrorMessage");
		}

	}

	public void navigateToRegisterationPage() throws Exception
	{
		// Navigate till registeration page
		element = driver.findElement(By.xpath(loginLink));
		element.click();
		element = driver.findElement(By.xpath(signUpLink));
		element.click();
	}
	public void verifyElements() throws Exception {
		navigateToRegisterationPage();
		// Verify all basic required elements are present on page
		element = driver.findElement(By.xpath(emailTextBox));
		element = driver.findElement(By.name(signUpButton));
	}
	public void register() throws Exception {
		navigateToRegisterationPage();
		if (suiteProperties.containsKey(testcaseId + "_invalidAddress"))
		{
			for (String email : emailAddress.split("::"))
			{
				element = driver.findElement(By.xpath(emailTextBox));
				element.clear();
				element.sendKeys(email);
				element = driver.findElement(By.name(signUpButton));
				element.click();

			}
			//Ensure that sign up button still present which means confirmation mail has not been sent to invalid users
			element = driver.findElement(By.name(signUpButton));
			return;
		}
		element = driver.findElement(By.xpath(emailTextBox));
		element.clear();
		if (!suiteProperties.containsKey(testcaseId + "_nonDynamicEmail"))
		{
			emailAddress = emailAddress.replace("@","_" + System.currentTimeMillis() + "@");
		}
		element.sendKeys(emailAddress);
		element = driver.findElement(By.name(signUpButton));
		element.click();
		if (suiteProperties.containsKey(testcaseId + "_expectedErrorMessage"))
		{
			// Expect error message as per test data.
			element = driver.findElement(By.xpath(String.format(errorMessageLoc, expectedErrorMessage)));
			return;
		}
		element = driver.findElement(By.xpath(regConfirmationText1));
		element = driver.findElement(By.xpath(String.format(regConfirmationText2,emailAddress)));
		element = driver.findElement(By.xpath(regConfirmationText3));
	}
}
