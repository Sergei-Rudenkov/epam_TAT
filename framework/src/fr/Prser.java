package fr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
 

public class Prser {
// This method gets xml file and looks for command tag, return list of all commands with params 
	public static NodeList getXml() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder =  factory.newDocumentBuilder();
	    Document doc = null;
	    try{
	    doc = builder.parse("journal.xml");
	    }catch(SAXParseException e){
	    	e.getMessage();
	    	System.out.println("Error, file is not xml!");
	    }
	    XPathFactory xFactory = XPathFactory.newInstance();
	    XPath xpath = xFactory.newXPath();
	    XPathExpression  expr = xpath.compile("//command/text()");
	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    
	    
	    NodeList nodes = (NodeList) result;
	    for (int i=0; i<nodes.getLength();i++){
	    	if(nodes.getLength() == 0){
	      		System.out.println("Error, empty xml file!");
	      		 System.exit(0);
	      	}
	      System.out.println(nodes.item(i).getNodeValue());
	      	
	      }
	    return nodes;
	    }		
	public static void outPutLog(String line, int preority) throws IOException{
		String tag = null;
		if(preority == 2){tag = "h2";}
		if(preority == 1){tag = "h4";}
		else{tag = "p";}
		

		BufferedWriter out = null;
		try  
		{
		    FileWriter fstream = new FileWriter("log.html", true); //true tells to append data.
		    out = new BufferedWriter(fstream);
		    out.write("<"+tag+">" + line + "</"+tag+">");
		}
		catch (IOException e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
		finally
		{
		    if(out != null) {
		        out.close();
		    }
		}
	}
}

