package fr.commands;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import fr.Prser;

public class LogIn {
	//method check logining
		public static boolean lgn(String[] params) throws IOException, InterruptedException { // params[] => arguments to command from xml [0] - user name; [1] - password
			SelWC.driver.findElement(By.xpath("//*[@id='username']")).sendKeys(params[0]); // first param from the array should to be username
			try{
			SelWC.driver.findElement(By.xpath("//*[@id='password']")).sendKeys(params[1]); // second param - password
			}catch(ArrayIndexOutOfBoundsException e){                                // if it not presence we catch exception and send it to log 
				e.getMessage();
				System.out.println("Second argument password - is not presence");
				Prser.writeToLog("Second argument password - is not presence (FAILED)",2);
			}
			SelWC.driver.findElement(By.xpath("//*[@id = 'page-body']/form/fieldset/input[3]")).click();
			Thread.sleep(2000);	// wait little bit
			List<String> links = Elements.getLinks(); // extract all links to list, to find "logout" button, that will show us that logining was correct 
			for (int i =0; i < links.size(); i++){ // i did like this be course have had problems with xpath of this button 
				 Pattern patternLog = Pattern.compile("\\bLogout\\b"); // searching for logout button with regexp
				 Matcher matcherLog = patternLog.matcher(links.get(i));
				 Pattern patternName = Pattern.compile("\\b"+params[0].toLowerCase()+"\\b"); // looking for link to user name
				 Matcher matcherName = patternName.matcher(links.get(i));
				if(matcherLog.find() && matcherName.find()){ // check presence of logout link and username link
					return true;   // return true to the Resender class, where this true will go to Prser.writeToLog
				}
			}
			return true;
		}
}
