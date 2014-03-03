package fr;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.commands.SelWC;
//Main class
public class Starter {
	public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, InterruptedException{
		NodeList nodes = Prser.getXml(); // Scan xml for commands and params
		 for (int i=0; i<nodes.getLength();i++){ // first command tag from xml
			 for (int j=0; j < StaticData.COMMANDS.length; j++){ // Scan for framework commands presence 
				 String command = StaticData.COMMANDS[j];
				 if(SearchCommand.findExtension(command, nodes.item(i))){ // There regexp will search 'nominative xml command' == 'framework command'
					 String params[] = SearchCommand.findParam(nodes.item(i)); // There regexp will looking for params in brackets
					 int line = i + 1; // just for beautiful log(look at the next code-line)
					 String log = "Command '" + command + "' was found in the " + line + "th line";
					 System.out.println(log);
					 int preority = 1;
					 Prser.writeToLog(log, preority);
					 Resender.send(command,params); // Truly command and params going to the class where their fates will designed  
				 }
			 }
		 }
		 SelWC.driver.quit(); 
	}
}
