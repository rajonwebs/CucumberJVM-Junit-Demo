package pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
	public WebDriver driver;
	public WebDriver csrDriver;
	public boolean bResult;
	public static String baseUrl;
	public static String adminBaseUrl;
	private static String callerClassName = "";

	public static String getBaseUrl() {
		baseUrl = "http://google.com";
		return baseUrl;
	}

	public void setDrivers(WebDriver driver) {
		this.driver = driver;
		this.bResult = true;
	}

	
}