package fr.commands;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

public class Search {
	public static HashMap<String, Boolean> srch(String[] params) { // method works with input search form
		HashMap<String, Boolean> searchResult = new HashMap<String, Boolean>(); // return hashmap: param => true(if was found), false (if failed)
		for(int i = 0; i < params.length; i++){ //for all params
			SelWC.driver.findElement(By.xpath("//*[@id='keywords']")).click(); // select input form
			SelWC.driver.findElement(By.xpath("//*[@id='keywords']")).sendKeys(params[i]);
			SelWC.driver.findElement(By.xpath("//*[@id='search-box']/form/fieldset/input[2]")).click(); // search button
			SelWC.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // wait for 2 seconds
			List<String> visableText = Elements.getLinks(); // getLinks() - return all links from page
			List<String> visableTextTitles = Elements.getTitles(); // getTitles() - mostly all titles/names/messages
			visableText.addAll(visableTextTitles); // merge 
			for(int j = 0; j < visableText.size(); j++){ // for all links and titles
				Pattern patternFalse = Pattern.compile("\\bnot contained\\b"); // search 'not contained' with regexp- presence will mean that search return no values
				Matcher matcherFalse = patternFalse.matcher(visableText.get(j));
					if (matcherFalse.find()){
						searchResult.put(params[i], false); 
					}
				Pattern patternTrue = Pattern.compile("\\bSearch found\\b"); // search 'Search found' with regexp- presence will mean that search return values
				Matcher matcherTrue = patternTrue.matcher(visableText.get(j));
					if (matcherTrue.find()){
						searchResult.put(params[i], true);
					}
				}
		}
		return searchResult;	
	}	
}
