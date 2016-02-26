package utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by sourabh on 2/25/16.
 */
public class BaseUtil {
	private String browser = "firefox";
	private DesiredCapabilities capabilities;
	protected static WebDriver driver;
	private Properties globalProperties;
	private int timeOutInSeconds;
	private static String baseURL;

	public BaseUtil() throws Exception
	{
		setup();
		tearDown();
	}

	private void tearDown() {
		// Future : Used to bring system to base page as expected by next testcase.
	}

	public void setup() throws Exception
	{
		// TO be executed before start of every util initialization
		setGlobalProperties();
		driver = getWebDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();
		//driver.close();
	}

	private void setGlobalProperties() throws Exception {
		// read properties from config file that are applibale to framework
		globalProperties = new Properties();
		FileInputStream config = new FileInputStream("config/config.cfg");
		globalProperties.load(config);
		config.close();
		baseURL=globalProperties.getProperty("baseUrl");
		timeOutInSeconds=Integer.valueOf(globalProperties.getProperty("timeOutInSeconds"));
	}

	private WebDriver getWebDriver() {
		try
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();

			if (browser.equalsIgnoreCase("firefox"))
			{
				// Configure custom profile parameters
				capabilities.setBrowserName("firefox");
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAcceptUntrustedCertificates(true);
				profile.setAssumeUntrustedCertificateIssuer(true);
				profile.setEnableNativeEvents(true);
				capabilities.setCapability(FirefoxDriver.PROFILE,profile);
			}
			// Configure generic parameters
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			// Create driver session only if it does not exist
			if (driver == null)
			{
				driver = new FirefoxDriver(capabilities);
			}
			//Configure implicit time out globally
			driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return driver;
	}

	public static void close() {
		driver.quit();
	}
}
