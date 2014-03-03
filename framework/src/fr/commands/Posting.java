package fr.commands;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import fr.Prser;
import fr.StaticData;

public class Posting {
	public static boolean post(String[] params) throws InterruptedException, IOException{
		try {
			SelWC.tottalPostsBefore = Integer.parseInt(SelWC.driver.findElement(By.xpath(StaticData.TOTAL_POSTS)).getText());
			SelWC.tottalTopicsBefore = Integer.parseInt(SelWC.driver.findElement(By.xpath(StaticData.TOTAL_TOPICS)).getText());
		}catch(NoSuchElementException e){
			e.getMessage();
			Prser.writeToLog(" Value of totall posts can not be located (FAILED)", SelWC.PRIORITY );
		}
		SelWC.driver.findElement(By.xpath(StaticData.FIRST_POST)).click();
		Thread.sleep(2000);
		SelWC.driver.findElement(By.xpath(StaticData.POST)).click();
		Thread.sleep(2000);
		SelWC.driver.findElement(By.xpath("//*[@id='subject']")).sendKeys(params[0]);
		
		Collection<WebElement> forms = SelWC.driver.findElements(By.tagName("form"));
		 Assert.assertFalse(forms.isEmpty(), "No forms found!");
		 Iterator<WebElement> i = forms.iterator();

		 SelWC.driver.findElement(By.xpath("//*[@id='message']")).sendKeys(params[1]);
		Thread.sleep(2000);	
		
	 while (i.hasNext()){
		 WebElement form = i.next();
			Assert.assertEquals(form.findElement(By.xpath("//*[@id='subject']")).getAttribute("value"),
					params[0], "Unable to fill subject field ");
		 Assert.assertEquals(form.findElement(By.xpath("//*[@id='message']")).getAttribute("value"),
					params[1], "Unable to fill message field ");
	 }
	 SelWC.driver.findElement(By.xpath(StaticData.POST_BUTTON)).click();
		Thread.sleep(3000);	
		try{
			SelWC.driver.findElement(By.xpath(StaticData.MESSAGE_REVIEW)).click();	
		}catch(Exception e){
			e.getMessage();
		}
		if(SelWC.driver.findElement(By.className("content")).getText().equals(params[1])){
			return true;
			}
		return false;
		
	}
	public static boolean getEdit(String[] params) throws InterruptedException, IOException {
		
		SelWC.driver.findElement(By.xpath("//html/body/div/div[2]/div/div/ul[2]/li/dl/dt/a")).click();
		Thread.sleep(2000);
		SelWC.driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/ul[2]/li/dl/dt/a")).click();
		Thread.sleep(2000);
		try{
			SelWC.driver.findElement(By.xpath("/html/body/div/div[2]/div[4]/div/div/ul/li/a")).click(); 
		}catch(NoSuchElementException e){
			e.getMessage();
			Prser.writeToLog(" User can not edit post (FAILED)", SelWC.PRIORITY );
		}
		Thread.sleep(3000);
		
		Collection<WebElement> forms = SelWC.driver.findElements(By.tagName("form"));
		 Assert.assertFalse(forms.isEmpty(), "No forms found!");
		 Iterator<WebElement> i = forms.iterator();
		
		while (i.hasNext()){
			 WebElement form = i.next();
				Assert.assertEquals(form.findElement(By.xpath("//*[@id='subject']")).getAttribute("value"),
						params[0], "Unable to find subject field ");
			 Assert.assertEquals(form.findElement(By.xpath("//*[@id='message']")).getAttribute("value"),
						params[1], "Unable to find message field ");
		 }
		SelWC.driver.findElement(By.xpath("//*[@id='message']")).sendKeys(" was edited");
		Thread.sleep(2000);	
		
		SelWC.driver.findElement(By.xpath(StaticData.POST_BUTTON)).click();
		Thread.sleep(3000);	
		try{
			SelWC.driver.findElement(By.xpath(StaticData.MESSAGE_REVIEW)).click();	
		}catch(Exception e){
			e.getMessage();
		}
		if(SelWC.driver.findElement(By.className("content")).getText().equals(params[1] + " was edited")){
			return true;
			}
		return false;
	
		//html/body/div/div[2]/p[4]/strong
	}	
	public static boolean testPostsIncrease(){
		SelWC.driver.get(StaticData.BASEURL);
		SelWC.wait.until(SelWC.visibilityOfElementLocated(By.xpath(StaticData.PAGE_HEADER)));	
		SelWC.tottalPostsAfter = Integer.parseInt(SelWC.driver.findElement(By.xpath(StaticData.TOTAL_POSTS)).getText());
		SelWC.tottalTopicsAfter = Integer.parseInt(SelWC.driver.findElement(By.xpath(StaticData.TOTAL_TOPICS)).getText());
		    if(SelWC.tottalPostsAfter == SelWC.tottalPostsBefore + 1 && SelWC.tottalTopicsAfter == SelWC.tottalTopicsBefore + 1){
		    	return true;
		    }else{
		    	return false;
		    }
	}
}
