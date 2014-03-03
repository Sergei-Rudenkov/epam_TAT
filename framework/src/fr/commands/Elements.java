package fr.commands;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//methods return <li> and <p> elements from the page
public class Elements {
	 static public List<String> getLinks(){
		 List<String> links = new ArrayList<String>();
		    List<WebElement> strongs = SelWC.driver.findElements(By.tagName("li"));
		    for (WebElement strong : strongs){
		        links.add(strong.getText());
		        }
		    return links;
	 }
	 
		static public List<String> getTitles(){
		    List<String> titles = new ArrayList<String>();
		    List<WebElement> strongs = SelWC.driver.findElements(By.tagName("p"));
		    for (WebElement strong : strongs){
		        titles.add(strong.getText());}
		    return titles;
		}
}
