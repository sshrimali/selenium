package testcases;

import org.junit.Test;
import org.testng.annotations.AfterClass;
import utils.BaseUtil;
import utils.RegisterationUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class AppDirectRegisterationTest {

	private static Properties registerationTestData;

	static
	{
		try
		{
			registerationTestData = new Properties();
			// Read Test data as property and pass to utilities
			FileInputStream userDataFis = new FileInputStream("testdata/appdirect/appDirectRegisteration.txt");
			registerationTestData.load(userDataFis);
			userDataFis.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	@AfterClass
	public void cleanUp()
	{
		BaseUtil.close();

	}

	@Test
	public void verifyElements_1() throws Exception {
		RegisterationUtil util = new RegisterationUtil(registerationTestData,"test_1");
		util.verifyElements();
	}

	@Test
	public void invalidEmailValidation_2() throws Exception {
		RegisterationUtil util = new RegisterationUtil(registerationTestData,"test_2");
		util.register();
	}
	@Test
	public void verifyEmailRegisteration_3() throws Exception {
		RegisterationUtil util = new RegisterationUtil(registerationTestData,"test_3");
		util.register();
	}
	@Test
	public void verifyDuplicateEmailRegisteration_4() throws Exception {
		RegisterationUtil util = new RegisterationUtil(registerationTestData,"test_4_1");
		util.register();
		util = new RegisterationUtil(registerationTestData,"test_4_2");
		util.register();

	}

}
