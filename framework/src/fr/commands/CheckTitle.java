package fr.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckTitle {
	//Here we check title and others items on the web page
	List<String> titles = new ArrayList<String>();
    static WebDriver driver = new FirefoxDriver(); // Initialization of the selenium WebDriver
    static String baseUrl  =  "http://localhost/phpBB3"; // home directory
    static Wait wait = new WebDriverWait(driver, 30); // 10 seconds for request answer waiting
	public static void checkTitle(){
		
	    driver.get(baseUrl);
	    wait.until(visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/p[3]")));
	    WebElement form = null;
	}
	static public List<String> getTitles(){

	    List<String> titles = new ArrayList<String>();
	    List<WebElement> strongs = driver.findElements(By.tagName("p"));
	    for (WebElement strong : strongs){
	        titles.add(strong.getText());}
	    driver.quit();
	    return titles;
	}

	public static boolean checkTitles(List<String> titles,String suffix){
    	Pattern pattern;
	    for (String title : titles){
		 pattern = Pattern.compile("\\b"+ suffix +"\\b");
		 Matcher matcher = pattern.matcher(title);
	        if (matcher.find()){
	            return true;
	           }
	    }
		return false;
	}
	    
	 public static ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
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
}
