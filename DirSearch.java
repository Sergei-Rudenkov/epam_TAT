package dir;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    
public class DirSearch {
	
	static String[] linkAndExtension;
	// метод принемает параметры из командной строки, проверяет наличие такой директории
	public static void getFileLocation(){
		Scanner scn = new Scanner(System.in);
		System.out.println("Puts, please file directory and then file extension, like: /home/rudzik/work/ java");
		String answer = scn.nextLine();
		linkAndExtension = answer.split(" ");
		File folder = new File(linkAndExtension[0]);
		if(!folder.exists() && !folder.isDirectory()){
			System.out.println("No such directory, sorry");
		}
	}
	// метод возвращает список всех файлов в указаной директории
	public static ArrayList<File> createListOfFiles(){
		ArrayList <File> resultListOfFiles = new ArrayList<File>();
		String dir = linkAndExtension[0];
		ArrayList <File> listOfDir = new ArrayList<File>();
		
		int counter = 0;
		do{
			// инициализация директории папки с которой работает цикл: dir 
			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();
			// foreach заглядываем во внуторь
			for (File directoryItem : listOfFiles){
				// если это файл закидываем его в коллецию файлов
				if (directoryItem.isFile()){
					resultListOfFiles.add(directoryItem);
				}
				// если это каталог и он не пустой - закидываем в список каталогов
				if (directoryItem.isDirectory() && directoryItem.list().length>0){ 
					listOfDir.add(directoryItem); 
					}
			}
			
			//переопределяем dir для цикла 
			dir = listOfDir.get(counter).toString();
			counter++;		
		}while(counter < listOfDir.size());	
		return resultListOfFiles;
	}
	// рег-вырожения, возвращает множество с нужным расширение 
	public static HashSet<File> findRightExtension(ArrayList<File> list){
		HashSet<File> finalSet =  new HashSet<File>();
		String extension = linkAndExtension[1]; 
		Pattern pattern;
		final String FILE_PATTERN = "([^\\s]+(\\.(?i)(" +  extension  + "))$)";
		 pattern = Pattern.compile(FILE_PATTERN);
		 for (int i = 0; i < list.size(); i++){
			 Matcher matcher = pattern.matcher(list.get(i).toString());
			 if (matcher.find()){
			  finalSet.add(list.get(i));
			 }
		 }
		 return finalSet;
	}
	// метод выводит список в консоль 
	public static void printList(HashSet list){
		 Iterator iterator = list.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
    
	public static void main(String[] args) {
		DirSearch ex = new DirSearch();
		ex.getFileLocation();	
		HashSet<File> result = ex.findRightExtension(ex.createListOfFiles());
		ex.printList(result);
	}

}
