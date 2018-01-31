package config;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import dataProvider.ExcelDataProvider;
import lib.BrowserAction;
import lib.ClickEvents;
import lib.SelectEvents;
import lib.TypeEvents;
import lib.typeScreenShots;

public class TestConfig {
	
	static WebDriver driver;
	static ExtentReports report;
	static ExtentTest logger;
	
	@Test
	public static void startTest()
	{
		ExcelDataProvider excel = new ExcelDataProvider();
		
		report = new ExtentReports("C:\\Users\\anoosha\\eclipse-workspacePart\\Maven-KeywordFrameWorkDriver-Project\\Reports\\report.html");
	
		logger = report.startTest("Keyword Data Driven Maven");
		
		int rowcount=excel.rowCount("LoginForm");
		
		for (int i=1; i<=rowcount; i++) {
			
			String description = ExcelDataProvider.getData("LoginForm", i, 0);
			
			String object_type = ExcelDataProvider.getData("LoginForm", i, 1);
			
			String Action = ExcelDataProvider.getData("LoginForm", i, 2);
			
			String locator_type = ExcelDataProvider.getData("LoginForm", i, 3);
			
			String locator_value = ExcelDataProvider.getData("LoginForm", i, 4);
			
			String  testData = ExcelDataProvider.getData("LoginForm", i, 5);
			
			if (object_type.equalsIgnoreCase("browser"))
			{
				if (testData.equalsIgnoreCase("Firefox"))
				{
					if (Action.equalsIgnoreCase("startBrowser"))
						
					{
						driver = new FirefoxDriver();
						
						
						
						
					}
					
					if (Action.equalsIgnoreCase("closeBrowser"))
					{
						driver.quit();
					}
						
					
				}
				
				if (testData.equalsIgnoreCase("Chrome"))
				{
					if(Action.equalsIgnoreCase("startBrowser"))
					{
						System.setProperty("webdriver.chrome.driver", "C:\\Users\\anoosha\\Downloads\\chromedriver_latest\\chromedriver.exe");
						driver = new ChromeDriver();
						
					}
					if (Action.equalsIgnoreCase("closeBrowser"))
					{
						driver.quit();
					}
					
				}
			}
			
			
			if (object_type.equalsIgnoreCase("OpenApp"))
			{
				if (Action.equalsIgnoreCase("navigate"))
				{
					BrowserAction.openApplication(driver, testData);
				}
			}
			
			if(object_type.equalsIgnoreCase("typeText"))
			{
				TypeEvents.typeAction(driver, locator_type, locator_value, testData);
				logger.log(LogStatus.INFO, description);
				try {
					logger.log(LogStatus.INFO, logger.addScreenCapture(typeScreenShots.captureScreenShots(driver, "ScreenShot")));
				} catch (Exception e) {
					e.getMessage();
				}
			}
		
		if (object_type.equalsIgnoreCase("typeSelect"))
		{
			SelectEvents.typeSelect(driver, locator_type, locator_value, testData);
		}
		
		if(object_type.equalsIgnoreCase("click"))
		{
			ClickEvents.clickAction(driver, locator_type, locator_value);
		}
		report.endTest(logger);
		report.flush();
		
		}
		
	
		
	}
	
	
}
