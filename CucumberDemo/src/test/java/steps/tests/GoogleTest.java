/**
 * 
 */
package steps.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import pageobjects.BaseTest;
import steps.Hooks;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * @author Shawkath Khan
 * 
 */
public class GoogleTest extends BaseTest {

	@Given("^I navigate to google\\.com$")
	public void i_navigate_to_google_com() throws Throwable {
		setDrivers(Hooks.driver);
		driver.get("https://www.google.com");
	}

	@Given("^I should be landed to google home page$")
	public void i_should_be_landed_to_google_home_page() throws Throwable {
		Thread.sleep(2000);
		boolean pageTitle = driver.getPageSource().contains("Google");
		Assert.assertEquals(pageTitle, true, "Google home page is not loaded");
	}

	@Then("^I search for the keyword '(.+)'$")
	public void i_search_for_the_keyword_Shawkath_Khan(String keyword) throws Throwable {
		setDrivers(Hooks.driver);
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys(keyword);
		Thread.sleep(2000);
		
	}

	@Then("^I click on one first search result$")
	public void i_click_on_one_first_search_result() throws Throwable {
		driver.findElement(By.cssSelector("[name='btnG']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String results = driver.findElement(By.cssSelector("#resultStats")).getText();
		System.out.println("The search returned : " + results);		
	}

}
