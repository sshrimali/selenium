package testcases;

import static org.junit.Assert.*;

import org.junit.Test;
import utils.RegisterationUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class AppDirectRegisterationTestTest {

	private static Properties registerationTestData;

	static
	{
		try
		{
			registerationTestData = new Properties();

			FileInputStream userDataFis = new FileInputStream("testdata/appdirect/appDirectRegisteration.txt");
			registerationTestData.load(userDataFis);
			userDataFis.close();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test
	public void test() throws Exception {
		RegisterationUtil util = new RegisterationUtil(registerationTestData,"test_1");
		util.navigateToRegisterationPage();

	}

}