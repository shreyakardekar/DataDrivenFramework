package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;


public class TestBase {

/*Webdriver
 * properties
 * logs
 * extentreports
 * DB
 * excel
 * mail
 * */	
	public static WebDriver driver;
	public static Properties config=new Properties();
	public static Properties OR=new Properties();
	public static FileInputStream fis;
	public static Logger log= Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public static ExtentTest test;
	public static ExtentReports reo=ExtentManager.getInstance();
	
	@BeforeSuite
	public void setUp() {
		if(driver==null) {
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e1) {
			
				e1.printStackTrace();
			}
		try {
			config.load(fis);
			log.debug("Config file loaded !!!");
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		try {
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			OR.load(fis);
			log.debug("OR file loaded !!!");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		if (config.getProperty("browser").equals("firefox")) {

			// System.setProperty("webdriver.gecko.driver", "gecko.exe");
			driver = new FirefoxDriver();

		} else if (config.getProperty("browser").equals("chrome")) {

		System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
		driver = new ChromeDriver();
		log.debug("Chrome Launched !!!");
		} else if (config.getProperty("browser").equals("ie")) {

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.get(config.getProperty("testsiteurl"));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,5);
		} 
	}
	
	public boolean isElementPPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
		}
		catch(Exception e){
			return false;
		}
	
		
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
	driver.quit();}
}
}
