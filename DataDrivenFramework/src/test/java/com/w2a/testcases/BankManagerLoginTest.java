package com.w2a.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.base.*;

public class BankManagerLoginTest extends TestBase{


@Test	
public void loginAsManager() {
	
	log.debug("Inside Login Method");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("bmlBtn"))));
	driver.findElement(By.xpath(OR.getProperty("bmlBtn"))).click();
	
	
	Assert.assertTrue(isElementPPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login not successfull");
	
	log.debug("login done successfully");
	
	
}
}
	
   
