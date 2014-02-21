package sel;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DBCreator {
	 static WebDriver driver = new FirefoxDriver(); // Initialization of the selenium WebDriver
	 static String baseUrl  =  "http://localhost/phpmyadmin"; // home directory
	 static Wait wait = new WebDriverWait(driver, 10); // 10 seconds for request answer waiting
	 
	 static public void logIn(){
		// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 driver.get(baseUrl + "/index.php"); // going to the base url

		
		 
		 wait.until(visibilityOfElementLocated(By.id("input_username"))); // We wait for form loading 10 seconds, hear we use private method from AT_book
		 driver.findElement(By.id("input_username")).sendKeys("root"); // logining
		 driver.findElement(By.id("input_password")).sendKeys("123456");
		 driver.findElement(By.id("input_go")).click(); // push
		
	 }
	 
	 static public void goToDB(){
		 driver.switchTo().frame("frame_content"); // switch frame
		 wait.until(visibilityOfElementLocated(By.id("topmenucontainer")));
		 driver.findElement(By.xpath("//a[contains(@href,'server_databases.php?token=')]")).click();
		 
		 wait.until(visibilityOfElementLocated(By.xpath("//*[@id='text_create_db']")));
		 
		
		 //driver.findElement(By.xpath("//*[@id='text_create_db']")).sendKeys("aaNewTable"); //create new table
		 //driver.findElement(By.xpath("//*[@id='buttonGo']")).click();

		 driver.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr/td[2]/a")).click(); // get from existing
		 wait.until(visibilityOfElementLocated(By.xpath("/html/body/form/fieldset/div/input"))); // waiting
		 driver.findElement(By.xpath("/html/body/form/fieldset/div/input")).sendKeys("users");  // table name
		 driver.findElement(By.xpath("/html/body/form/fieldset/div[2]/input")).sendKeys("6");  // columns number
		 driver.findElement(By.xpath("/html/body/form/fieldset[2]/input")).click(); // push
	 }
	 
	 public static void createDB(){
		 wait.until(visibilityOfElementLocated(By.xpath("//*[@id='field_0_1']")));
		 Collection<WebElement> forms = driver.findElements(By.tagName("form"));
		 Assert.assertFalse(forms.isEmpty(), "No forms found!");
		 Iterator<WebElement> i = forms.iterator();
		 boolean form_found = false;
		 WebElement form = null;
		 while (i.hasNext()){
		 form = i.next();
		 if
		 ((form.findElement(By.xpath("//*[@id='field_0_1']")).getAttribute("type").equalsIgnoreCase(
		 "text"))&&
		 (form.findElement(By.xpath("//*[@id='field_1_1']")).getAttribute("type").equalsIgnoreCase
		 ("text"))&&
		(form.findElement(By.xpath("//*[@id='field_2_1']")).getAttribute("type").equalsIgnoreCase(
		 "text"))&&
		(form.findElement(By.xpath("//*[@id='field_3_1']")).getAttribute("type").equalsIgnoreCase
		("text"))&&
		(form.findElement(By.xpath("//*[@id='field_4_1']")).getAttribute("type").equalsIgnoreCase(
		 "text"))&&
		(form.findElement(By.xpath("//*[@id='field_5_1']")).getAttribute("type").equalsIgnoreCase
		 ("text")))
		 
		 {
			 form_found = true;
			 break;
		 		}
			 }
			 Assert.assertTrue(form_found, "No suitable forms found!");
			 
			 
		 //first column
		driver.findElement(By.xpath("//*[@id='field_0_1']")).sendKeys("u_id");
		driver.findElement(By.xpath("//*[@id='field_1_1']")).sendKeys("u_login");
		driver.findElement(By.xpath("//*[@id='field_2_1']")).sendKeys("u_password");
		driver.findElement(By.xpath("//*[@id='field_3_1']")).sendKeys("u_email");
		driver.findElement(By.xpath("//*[@id='field_4_1']")).sendKeys("u_name");
		driver.findElement(By.xpath("//*[@id='field_5_1']")).sendKeys("u_remember");
		
		Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_0_1']")).getAttribute("value"),
		"u_id", "Unable to fill field");
		Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_1_1']")).getAttribute("value"),
				"u_login", "Unable to fill field");
		Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_2_1']")).getAttribute("value"),
				"u_password", "Unable to fill field");
		Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_3_1']")).getAttribute("value"),
				"u_email", "Unable to fill field");
		Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_4_1']")).getAttribute("value"),
				"u_name", "Unable to fill field");
		Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_5_1']")).getAttribute("value"),
				"u_remember", "Unable to fill field");
		
		
	
		//second column
		new Select(driver.findElement(By.xpath("//*[@id='field_1_2']"))).selectByVisibleText("VARCHAR");
		new Select(driver.findElement(By.xpath("//*[@id='field_2_2']"))).selectByVisibleText("CHAR");
		new Select(driver.findElement(By.xpath("//*[@id='field_3_2']"))).selectByVisibleText("VARCHAR");
		new Select(driver.findElement(By.xpath("//*[@id='field_4_2']"))).selectByVisibleText("VARCHAR");
		new Select(driver.findElement(By.xpath("//*[@id='field_5_2']"))).selectByVisibleText("CHAR");
		
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_0_2']")).getAttribute("value"),
						"INT", "Unable to fill 'type' field");
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_1_2']")).getAttribute("value"),
						"VARCHAR", "Unable to fill 'type' field");
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_2_2']")).getAttribute("value"),
						"CHAR", "Unable to fill 'type' field");
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_3_2']")).getAttribute("value"),
						"VARCHAR", "Unable to fill 'type' field");
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_4_2']")).getAttribute("value"),
						"VARCHAR", "Unable to fill 'type' field");
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_5_2']")).getAttribute("value"),
						"CHAR", "Unable to fill 'type' field");
				
		
		//3th column
		driver.findElement(By.xpath("//*[@id='field_0_3']")).sendKeys("11");
		driver.findElement(By.xpath("//*[@id='field_1_3']")).sendKeys("255");
		driver.findElement(By.xpath("//*[@id='field_2_3']")).sendKeys("40");
		driver.findElement(By.xpath("//*[@id='field_3_3']")).sendKeys("255");
		driver.findElement(By.xpath("//*[@id='field_4_3']")).sendKeys("255");
		driver.findElement(By.xpath("//*[@id='field_5_3']")).sendKeys("40");
		
		Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_0_3']")).getAttribute("value"),
				"11", "Unable to fill 'length' field");
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_1_3']")).getAttribute("value"),
						"255", "Unable to fill 'length' field");
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_2_3']")).getAttribute("value"),
						"40", "Unable to fill 'length' field");
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_3_3']")).getAttribute("value"),
						"255", "Unable to fill 'length' field");
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_4_3']")).getAttribute("value"),
						"255", "Unable to fill 'length' field");
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_5_3']")).getAttribute("value"),
						"40", "Unable to fill 'length' field");
		
		//primary key
		new Select(driver.findElement(By.xpath("//*[@id='field_0_8']"))).selectByVisibleText("PRIMARY");
		Assert.assertEquals(form.findElement(By.xpath("//*[@id='field_0_8']")).getAttribute("value"),
				"primary_0", "Unable to fill 'type' field");
		//save table
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='frame_content']")));
		driver.findElement(By.name("do_save_data")).click();
		// go to table sittings 
		wait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/form/table/tbody/tr[1]/th/a")));
		driver.findElement(By.xpath("/html/body/div[1]/form/table/tbody/tr[1]/th/a")).click();
		driver.findElement(By.xpath("/html/body/div[2]/ul/li[3]/a")).click();
		
		//send sql code to users
		driver.findElement(By.xpath("//*[@id='clear']")).click();
		driver.findElement(By.xpath("//*[@id='sqlquery']")).sendKeys(" INSERT INTO `users` (`u_id`, `u_login`, `u_password`, `u_email`, `u_name`, `u_remember`) VALUES (1, 'user1', 'e38ad214943daad1d64c102faec29de4afe9da3d', 'user1@mail.com', 'Pupkin', ''), (2, 'user2', '2aa60a8ff7fcd473d321e0146afd9e26df395147', 'user2@mail.com', 'Smith', '');");
		driver.findElement(By.xpath("//*[@id='button_submit_query']")).click();
		driver.quit();
	 }

	 
	 private static ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
			 return new ExpectedCondition<WebElement>() {
				 public WebElement apply(WebDriver driver) {
					 WebElement toReturn = driver.findElement(locator);
					 if (toReturn.isDisplayed()) {
						 return toReturn;
					 }
					 return null;
				 }
			 };
	 }
	 
	 public static void main(String[] args){
		 logIn();
		 goToDB();
		 createDB();
	 }
}
