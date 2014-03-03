package fr.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import fr.Prser;
import fr.StaticData;

public class SelWC {
	//Here we check title and others items on the web page
	static protected final int  PRIORITY = 2; // priority of log messages
	static int tottalPostsBefore;
	static int tottalPostsAfter;
	static int tottalTopicsBefore;
	static int tottalTopicsAfter;
	
	List<String> titles = new ArrayList<String>();
    public static WebDriver driver = new FirefoxDriver(); // Initialization of the selenium WebDriver
    static Wait wait = new WebDriverWait(driver, 30); // 10 seconds for request answer waiting
    
	public static void getHomePage(){
		
	    driver.get(StaticData.BASEURL);
	    wait.until(visibilityOfElementLocated(By.xpath(StaticData.PAGE_HEADER)));
	    WebElement form = null;
	}


	public static boolean checkTitles(List<String> titles,String suffix){
    	Pattern pattern;
	    for (String title : titles){
		 pattern = Pattern.compile("\\b"+ suffix +"\\b"); // check if text from page contains word that we search
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

