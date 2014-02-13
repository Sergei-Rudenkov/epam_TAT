package mp3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;


public class Mp3Cataloguer {
	
	 HashMap<String, Integer> listOfSongAndDuration = new HashMap(); // Хэш аудиозапись => длительность
	 HashMap<String, String> listOfSongAndArtist = new HashMap();   // и ещё несколько хэшей для составления 
	 HashMap<String, String> listOfSongAndAlbum = new HashMap();    // для реляционности данных.
	 HashMap<String, String> listOfArtistAndAlbum = new HashMap();
	 HashSet artists = new HashSet(); // множества, для записи в html  
	 HashSet albums = new HashSet();  // песьня, албом(под заголовок) или артист(заголовок) 
	 HashSet songs = new HashSet();   // не могут быть упомянуты два раза 
	 
	public ArrayList<File> getAndCheckLinks(){
		ArrayList<File> workingLinks = new ArrayList<File>();
		Scanner scn = new Scanner(System.in);
		// метод принимает ссылки разделёные пробелом
		
		System.out.println("Puts catalogs you need to scan");
		String answer = scn.nextLine();
		String[] urls = answer.split(" ");
		
		for(int i = 0; i < urls.length; i ++ ){
			File folder = new File(urls[i]);
			// возвращает коллекцию ссылок, проверяя кaталог ли это, и не пуст ли он	
			if (folder.isDirectory() && folder.list().length>0){
				workingLinks.add(folder);
			}
		}
		if(workingLinks.size() == 0){
			System.out.println("Sorry, but all your links - are not links or empty folders");
			return null;
		}else{
			return workingLinks;
		}	
	}
	
	// метод возвращает список всех файлов mp3 в указаной директории
		public ArrayList<File> createListOfMp3Files(ArrayList<File> links){
			ArrayList <File> resultListOfFiles = new ArrayList<File>();
			
			int counter = 0;
			String dir = links.get(counter).toString();
			
			
			do{
				// инициализация директории папки с которой работает цикл: dir 
				File folder = new File(dir);
				File[] listOfFiles = folder.listFiles();
				// foreach заглядываем во внуторь
				for (File directoryItem : listOfFiles){
					// если это файл закидываем его в коллецию файлов
					if (directoryItem.isFile() && isMp3(directoryItem)){
						resultListOfFiles.add(directoryItem);
					}
					// если это каталог и он не пустой - закидываем в список каталогов
					if (directoryItem.isDirectory() && directoryItem.list().length>0){ 
						links.add(directoryItem); 
						}
				}

				//переопределяем dir для цикла 
				dir = links.get(counter).toString();
				counter++;
				
			}while(counter < links.size());	
			return resultListOfFiles;
		}
	
	// метод заглядывает во внуторь mp3, доставая информацию о продолжительности, альбоме и тд
	// используется import org.jaudiotagger.audio.*	
	    public void getData( ArrayList<File> allMp3) throws UnsupportedAudioFileException, IOException{
	    	 AudioFile audioFile;
	    	
	    	for (File mp3 : allMp3){
				try {
					audioFile = AudioFileIO.read(mp3);
					Tag tag = audioFile.getTag();
					listOfSongAndDuration.put(tag.getFirst(FieldKey.TITLE), audioFile.getAudioHeader().getTrackLength());
					listOfSongAndArtist.put(tag.getFirst(FieldKey.TITLE), tag.getFirst(FieldKey.ARTIST));
					listOfSongAndAlbum.put(tag.getFirst(FieldKey.TITLE), tag.getFirst(FieldKey.ALBUM));
					listOfArtistAndAlbum.put(tag.getFirst(FieldKey.ARTIST), tag.getFirst(FieldKey.ALBUM));
					songs.add(tag.getFirst(FieldKey.TITLE));
					artists.add(tag.getFirst(FieldKey.ARTIST));
					albums.add(tag.getFirst(FieldKey.ALBUM));
					
				} catch (CannotReadException | TagException
						| ReadOnlyFileException | InvalidAudioFrameException e) {
				}
			}
		}
	    
	    public void createHtmlFile(){ 
	    	PrintWriter writer;
	    	// Всё что было HashSet записывем в ArrayList, для работы в циклах
	    	ArrayList<String> songsList = new ArrayList<String>();
	    	ArrayList<String> artistsList = new ArrayList<String>();
	    	ArrayList<String> albumsList = new ArrayList<String>();
	    	songsList.addAll(songs);
	    	artistsList.addAll(artists);
	    	albumsList.addAll(albums);
	    	// разворачиваем HashMap, теперь не обходимо искать компазиции по альбому и артисту,
	    	// инициализировать в таком виде сразу было не возможно тк Artist has many songs
	    	reverse(listOfSongAndArtist);
	    	reverse(listOfSongAndAlbum);
			try {
				
				writer = new PrintWriter("/home/rudzik/work/mp3list.html", "UTF-8");
				writer.println("<!DOCTYPE html>");
				writer.println("<html>");
				writer.println("<body>");
				// for each для артистов альбомов и компазиций, сверяем с помощью HashMap подходит ли 
				// альбом к артисту, а песьня к альбому 
				for(String artist : artistsList){
					writer.println("<h1> "+ artist +"</h1>");
					for(String album : albumsList){
						String alb = listOfArtistAndAlbum.get(artist);
						if(alb.equals(album)){ 
							writer.println("<h3>Albom: "+ album +"</h3>");
						}else{continue;}
						writer.println("<table width=300>");
						for(String song : songsList){
							String S = listOfSongAndAlbum.get(song);
							if( album.equals(S) && alb.equals(album) ){
								writer.println("<tr>");
								writer.println("<td>"+ song + "</td>");
								writer.println("<td>duration: " + listOfSongAndDuration.get(song) + "  sec" + "</td>");
								writer.println("</tr>");
							}
						}
						writer.println("</table>");
					}
				}
				
				writer.println("</body>");
				writer.println("</html>");
				writer.close();
			} catch (FileNotFoundException e) {
				System.out.println("Wrong directory to save file: /home/rudzik/work/mp3list.html");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				System.out.println("Wrong directory to save file: /home/rudzik/work/mp3list.html");
				e.printStackTrace();
			}
	    }
	    
	    // RegExp mp3 ли это файл?
		private static boolean isMp3(File file){
			Pattern pattern;
			final String FILE_PATTERN = "([^\\s]+(\\.(?i)(mp3))$)";
			pattern = Pattern.compile(FILE_PATTERN);
				 Matcher matcher = pattern.matcher(file.toString());
				 if (matcher.find()){
				  return true;
				 }else{
			return false;
				 }
		}
		
		// метод разворачивает HashMapы
		private static <K,V> HashMap<V,K> reverse(Map<K,V> map) {
		    HashMap<V,K> rev = new HashMap<V, K>();
		    for(Map.Entry<K,V> entry : map.entrySet())
		        rev.put(entry.getValue(), entry.getKey());
		    return rev;
		}
	    
	public static void main(String[] args) {
		Mp3Cataloguer ex = new Mp3Cataloguer();
		ArrayList<File> result = ex.getAndCheckLinks();
		result = ex.createListOfMp3Files(result);
		try {
			ex.getData(result);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ex.createHtmlFile();

	}

}


