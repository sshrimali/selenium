package utils;

import java.util.Properties;

/**
 * Created by sourabh on 2/25/16.
 */
public class RegisterationUtil extends BaseUtil{
    private final Properties suiteProperties;
    private final String testcaseId;
private String emailAddress;

    public RegisterationUtil(Properties suiteDataProp, String testcaseId) throws Exception
    {
        this.suiteProperties = suiteDataProp;
        this.testcaseId = testcaseId;
        getTestDataContext();
        setElementId();
    }
    void setElementId() {
    }
    private void getTestDataContext() {
        if (suiteProperties.containsKey(testcaseId + "_emailAddress")) {
            emailAddress = suiteProperties.getProperty(testcaseId + "_emailAddress");
        }
    }
    public void navigateToRegisterationPage()
    {
System.out.println("test data : " + emailAddress);
    }
}
