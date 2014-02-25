package fr;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.commands.*;

public class Resender {
	public static void send(String command, String[] params){
		if(command.equals("title")){
			int preority = 2;
			String result = null;
			CheckTitle.checkTitle();
			List<String> titles = CheckTitle.getTitles();
			for (int i = 0; i < params.length; i++){
				if(CheckTitle.checkTitles(titles, params[i])){
					result ="'" + params[i] + "'" + " was found on the page";
					System.out.println(result);
					
				}else{result = params[i] + " was NOT found on the page";}
				try {
					Prser.outPutLog(result, preority);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
