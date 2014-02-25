package fr;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Node;


public class SearchCommand {
	//method search 'framework' command among xml commands 
	public static boolean findExtension(String command, Node node){ // 1 framework commamd, 1 command from xml
		Pattern pattern;
		final String FILE_PATTERN = "\\b" + command + "\\b";
		 pattern = Pattern.compile(FILE_PATTERN);  // compare with regexp
			 Matcher matcher = pattern.matcher(node.toString());
			 if (matcher.find()){
			  return true;
			 }
		 return false;
	}
	//this method finds brackets in the command tag from xml, split it and return it like array
	// it will be array of parameters for our 'framework' command
	public static String[] findParam(Node node){
		String params[];
		Pattern pattern;
		 pattern = Pattern.compile("\\((.*?)\\)");
			 Matcher matcher = pattern.matcher(node.toString());
			 if (matcher.find()){
				 
				 params = matcher.group(1).split(",");
				 return params; 
			 }
			 return null;
	}
}
