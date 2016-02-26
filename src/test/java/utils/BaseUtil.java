package utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Created by sourabh on 2/25/16.
 */
public class BaseUtil {
    private String browser = "firefox";
    private DesiredCapabilities capabilities;
    private WebDriver driver;

    public BaseUtil() throws Exception
    {
        setup();
        tearDown();

    }

    private void tearDown() {

    }

    public void setup()
 {

     driver = getWebDriver();
     driver.get("http://www.google.com/");
     driver.close();
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
                profile.setPreference("javascript.options.showInConsole", true);
                profile.setPreference("dom.max_script_run_time", 340);
                profile.setPreference("dom.max_chrome_script_run_time", 460);
                profile.setEnableNativeEvents(true);
                capabilities.setCapability(FirefoxDriver.PROFILE,profile);
            }
            capabilities.setJavascriptEnabled(true);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            if (driver == null)
            {
               driver = new FirefoxDriver(capabilities);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return driver;

    }


}
