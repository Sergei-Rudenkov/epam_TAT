package fr;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.commands.*;

public class Resender {
	static private final int PRIORITY = 2; // priority - html tag in log.html
	public static void send(String command, String[] params) throws IOException, InterruptedException{
		if(command.equals("title")){
			String result = null;
			SelWC.getHomePage(); // get base url
			List<String> titles = Elements.getTitles(); // get all titles from the page 
			for (int i = 0; i < params.length; i++){  
				if(SelWC.checkTitles(titles, params[i])){  // titles and 'titles from param' will be compared in the checkTitles class
					result ="'" + params[i] + "'" + " was found on the page (OK)";
					System.out.println(result);
					
				}else{result = "'" + params[i] + "'" + " was NOT found on the page (FAILED)";
				System.out.println(result);
				}
				try {
					Prser.writeToLog(result, PRIORITY); // write to log result and according priority of result
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(command.equals("links")){
			String result = null;
			SelWC.getHomePage();
			List<String> links = Elements.getLinks();
			for (int i = 0; i < params.length; i++){
				if(SelWC.checkTitles(links, params[i])){ // method will compare 'params links' and page links
					result ="'" + params[i] + "'" + " was found on the page (OK)";
					System.out.println(result);
					
				}else{result = "'" + params[i] + "'" + " was NOT found on the page (FAILED)";
				System.out.println(result);
				}
				try {
					Prser.writeToLog(result, PRIORITY);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(command.equals("login")){
			String result = null;
			SelWC.getHomePage();
			if(LogIn.lgn(params)){ // send params(name and password) to method, where webdriver will try to login 
				result = "Logining with username '" + params[0]+ "' and password '"+ params[1]+ "' wass succesful (OK)";
				System.out.println(result);
				Prser.writeToLog(result, PRIORITY);
			}else{
				result ="Wromg username or password (FAILD)";
				System.out.println(result);
				Prser.writeToLog(result, PRIORITY);
			}
		}
		if(command.equals("search")){
			String result = null;
			SelWC.getHomePage();
			HashMap<String, Boolean> serchResult = Search.srch(params);
			try{
				for(int i = 0; i < params.length; i++ ){
					if(serchResult.get(params[i]) == true ){
						result = params[i] + " - was found (OK)";
					}
					if(serchResult.get(params[i]) == false){
						result = params[i] + " - was not found (FAILED)";
					}
					System.out.println(result);
					Prser.writeToLog(result, PRIORITY);
				}
			}catch(NullPointerException e){
				e.getMessage();
				result = "Wrong arguments for 'search' command (FAILED)";
				System.out.println(result);
				Prser.writeToLog(result, PRIORITY);
			}
		}
		if(command.equals("post")){
			String result = null;
			SelWC.getHomePage();
			if(Posting.post(params)){
				result = "Post '" + params[0] + "' was posted (OK)" ;
			}else{
				result = "Post '" + params[0] + "' was not posted (FAILED)" ;
			}
			System.out.println(result);
			Prser.writeToLog(result, PRIORITY);
			
			SelWC.getHomePage();
			
			if(Posting.getEdit(params)){  // attempt to edit
				result = "Post '" + params[0] + "' can be edited (OK)" ;
			}else{
				result = "Post '" + params[0] + "' can not be edited (FAILED)" ;
			}
			System.out.println(result);
			Prser.writeToLog(result, PRIORITY);
			
			if(Posting.testPostsIncrease()){ // check increasing 
				result = "Tottal value of posts and topics was increased(OK)" ;
			}else{
				result = "Tottal value of posts and topics was not been increased(FAILED)" ;
			}
			System.out.println(result);
			Prser.writeToLog(result, PRIORITY);
		}
	}
}
