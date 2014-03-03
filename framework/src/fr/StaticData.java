package fr;

public class StaticData {
	public static final String[] COMMANDS = {"title", "links", "login", "search", "post"};
	public static final String BASEURL  =  "http://localhost/phpBB3"; 
	public static final String PAGE_HEADER = "//*[@id='page-header']";
	public static final String FIRST_POST = "//*[@id = 'page-body']/div/div/ul[2]/li/dl/dt/a";
	public static final String POST = "//*[@id = 'page-body']/div/div/div/a/span";
	public static final String POST_BUTTON = "//*[@id = 'postform']/div[2]/div/fieldset/input[5]";
	public static final String MESSAGE_REVIEW = "//*[@id = 'page-body']div/div/ul[2]/li/dl/dt/a";
	public static final String TOTAL_POSTS = "//html/body/div/div[2]/p[4]/strong"; 
	public static final String TOTAL_TOPICS = "//html/body/div/div[2]/p[4]/strong[2]"; 
}
