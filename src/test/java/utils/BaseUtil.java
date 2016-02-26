package utils;


/**
 * Created by sourabh on 2/25/16.
 */
public class BaseUtil {
    private String browser = "firefox";

    public BaseUtil() throws Exception
    {
        setup();
        tearDown();

    }

    private void tearDown() {

    }

    public void setup()
 {
     getWebDriver();
 }

    private String getWebDriver() {
        try
        {
            if (browser.equalsIgnoreCase("firefox"))
            {
//                FirefoxProfile profile = new FirefoxProfile();
//                // add any custom firefox configurations...
//                profile.setPreference("javascript.options.showInConsole", true);
//                profile.setPreference("dom.max_script_run_time", 340);
//                profile.setPreference("dom.max_chrome_script_run_time", 460);
//                profile.setEnableNativeEvents(true);
//                capabilities.setCapability(FirefoxDriver.PROFILE,profile);
            }
    //        if (driver == null)
    //        {
    //           WebDriver driver = new FirefoxDriver(capabilities);
    //        }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        String webDriver="";
        return webDriver;

    }


}
