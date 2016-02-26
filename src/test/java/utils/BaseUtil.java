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

    }

    public void setup() throws Exception
 {
     setGlobalProperties();
     driver = getWebDriver();
     driver.get(baseURL);
     driver.manage().window().maximize();
     //driver.close();
 }

    private void setGlobalProperties() throws Exception {
		// TODO Auto-generated method stub
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
                capabilities.setBrowserName("firefox");
                FirefoxProfile profile = new FirefoxProfile();
                // add any custom firefox configurations...
//                profile.setPreference("javascript.options.showInConsole", true);
//                profile.setPreference("dom.max_script_run_time", 340);
//                profile.setPreference("dom.max_chrome_script_run_time", 460);
                profile.setAcceptUntrustedCertificates(true);
                profile.setAssumeUntrustedCertificateIssuer(true);
                profile.setEnableNativeEvents(true);
                capabilities.setCapability(FirefoxDriver.PROFILE,profile);
            }
            capabilities.setJavascriptEnabled(true);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
           
            if (driver == null)
            {
               driver = new FirefoxDriver(capabilities);
            }
           // driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return driver;

    }


}
