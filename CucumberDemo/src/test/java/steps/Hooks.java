package steps;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.ImmutableMap;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	public static WebDriver driver;
	public static WebDriver csrDriver;
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	@Before
	public void setUp() throws Throwable {
		if (driver != null)
			driver.quit();
		if (OS.indexOf("win") >= 0) {
			createLocalChromeDrivers();
//			createFirefoxDrivers();
		} else {
			 createLinuxChromeDrivers();
//			 createFirefoxDrivers();
		}
	}

	
	public void createLocalChromeDrivers() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver",
				"./resources/chromedriver.exe");
		driver = new ChromeDriver();
	}

	public void createLinuxChromeDrivers() {
		String xPort = System.getProperty("Importal.xvfb.id", "0:0");
		System.out.println(xPort);
		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File("/home/ubuntu/chromedriver/chromedriver"))
				.usingAnyFreePort()
				.withEnvironment(ImmutableMap.of("DISPLAY", xPort)).build();
		System.setProperty("webdriver.chrome.driver",
				"/home/ubuntu/chromedriver/chromedriver");
		driver = new ChromeDriver(service);
		csrDriver = new ChromeDriver(service);
	}
	
	public void createFirefoxDrivers() {
		driver = new FirefoxDriver();
		 driver.manage().window().maximize();
	}

	@After
	// * Embed a screenshot in test report if test is marked as failed
	public void embedScreenshot(Scenario scenario) {

		if (scenario.isFailed()) {
			try {
				scenario.write("Current Page URL is " + driver.getCurrentUrl());
				byte[] screenshot = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots
						.getMessage());
			}

		}
		if (driver != null)
			driver.quit();
	}

	@Rule
	public TestWatcher watchman = new TestWatcher() {

		@Override
		protected void failed(Throwable e, Description description) {
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File("C:\\screenshot.png"));
			} catch (IOException e1) {
				System.out.println("Fail to take screen shot");
			}
			driver.quit();
		}

		@Override
		protected void succeeded(Description description) {
			driver.quit();
		}
	};

	

}