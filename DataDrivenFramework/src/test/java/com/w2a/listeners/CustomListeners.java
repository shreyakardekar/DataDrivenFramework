package com.w2a.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		TestBase.test.log(LogStatus.PASS,result.getName().toUpperCase());
		
	}

	public void onTestSuccess(ITestResult result) {
		
		TestBase.test.log(LogStatus.PASS,result.getName().toUpperCase()+"PASS");
		reo.endTest(test);
		reo.flush();
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-ouput", "false");
		Reporter.log("login Successfully executed");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestBase.test.log(LogStatus.FAIL,result.getName().toUpperCase()+"FAIL");
		TestBase.test.log(LogStatus.FAIL,test.addScreenCapture(TestUtil.screenshotName));
		Reporter.log("<a target=\"_blank\" href = \"C:\\Users\\Shreya Kardekar\\Pictures\\error.jpg\">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href = \"C:\\Users\\Shreya Kardekar\\Pictures\\error.jpg\">Screenshot</a>");
		reo.endTest(test);
		reo.flush();
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
