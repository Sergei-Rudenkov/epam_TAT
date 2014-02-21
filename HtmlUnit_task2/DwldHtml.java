package HtmlD;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class DwldHtml {
	static ArrayList<HtmlImage> urlList = new ArrayList<HtmlImage>();
	final static String URL = "http://www.animewallpapers.com/"; // 
	final static String[] ALBUMS = {"misc/", "naruto/",  "evangelion/"}; //hardcoding of album names
	//the better way is - to ask user for albums names. 

	
	public static List<?> crapUrl() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);// turn off javascript
        
        HtmlPage currentPage = (HtmlPage) webClient.getPage(new URL(URL));
        List<HtmlImage> urls = new ArrayList<HtmlImage>();
        urls = (List<HtmlImage>) currentPage.getByXPath("//img"); // cutting links from tag <img>
        webClient.closeAllWindows();
            return urls; // return list of urls but not only with images, and not only images we need
	}
	// hear method choose correct links
	public static void getImageUrl(List<?> urls){
		Pattern pattern;
		 pattern = Pattern.compile("\\bmedia\\b"); // firebug  show me that all images come from media.animewallpapers.com
		for (Object line : urls) {
			 Matcher matcher = pattern.matcher(line.toString());
			 if (matcher.find()){
            HtmlImage image = (HtmlImage) line;
            urlList.add(image); 
            System.out.println(image.getSrcAttribute());
			 }
	     }
	}
	// Saving method
	 private static void downloadImage(String argDir)  {
	        int imgSrc = 0;
	        for(HtmlImage img : urlList){ 
	        	String imgPath = argDir + imgSrc + ".jpg";
	            imgSrc++;
	            if (img != null) {
	                File file = new File(imgPath);
	                try {
						img.saveAs(file);
					}  catch (IOException e) {
						System.out.println(e.getMessage());
					}
				
	            }
	        }  
	    }

	 // method complete same task as crapUrl, but with albums names
	 public static void moreImages() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		 WebClient webClient = new WebClient();
	        webClient.getOptions().setJavaScriptEnabled(false);
	        for (int j = 0; j < ALBUMS.length; j++){ // loop for array of albums
	        	for (int i = 1; i < 140; i++){ // first 140 
	        	try {	
	        		HtmlImage currenturl = urlList.get(i);
	        HtmlPage currentPage = webClient.getPage(URL + "wallpapers/" + ALBUMS[j] + "full_" + i + ".php");
	        		
	        List<HtmlImage> urls = new ArrayList<HtmlImage>();
	        urls = (List<HtmlImage>) currentPage.getByXPath("//img");
	        getImageUrl(urls);
	        		
	        	}catch (MalformedURLException e) {
	        		e.printStackTrace();
	        		System.out.println(e.getMessage());
	        	}	
	        }
	        }
	        webClient.closeAllWindows(); 
	 }
	 
        public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
        	File file = null;
        	// checking if indicated dirrectory is exist 
        	if (args.length > 0 ){
        	try {
        		file = new File(args[0]);
        	}  catch (Exception e) {
        		System.out.println("Wrong dirrectory to save");
        	}
        	if (file.isDirectory()){
        	DwldHtml ex = new DwldHtml();
        	ex.getImageUrl(ex.crapUrl());
        	ex.moreImages();
        	ex.downloadImage(args[0]);
        	}else{
        		System.out.println("Wrong dirrectory to save");
        	}
        }
    }
         
}
