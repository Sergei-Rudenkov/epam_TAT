package htmlgradient;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class HtmlColor {
	
	static public String line;
	static public ArrayList<String> list = new ArrayList<String>();
	
	// метод перебирает 256 оттенков серого, 
	// переводит число в 16ую систему, 
	// записывает в коллекцию list
	public static ArrayList increaseCollor(){ 
		int step = 1;
		for (int i = 255; i >= 0; i-=step  ) {
			String hex = Integer.toString(i, 16).toUpperCase();
			line = '#' + hex + hex + hex;
			list.add(line);
		}
		return list;
	}
	
	//метод вкладывает поочерёдно экземпляры коллекции в html форму 
	// и сохраняет файл.
	static public void makeHtmlFile(){
		PrintWriter writer;
		try {
			writer = new PrintWriter("/home/rudzik/work/htmlcolor.html", "UTF-8");
			writer.println("<!DOCTYPE html>");
			writer.println("<html>");
			writer.println("<body>");
			for(String item : list){
				writer.println("<table width=500 cellspacing=0 cellpadding=5>");
				writer.println("<td bgcolor=" + item + ">");
				writer.println("</table>");	
			}
			writer.println("</html>");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		HtmlColor ex = new HtmlColor();
		ex.increaseCollor();
		ex.makeHtmlFile();
	}
}
