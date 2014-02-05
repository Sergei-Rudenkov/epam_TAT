package filerenamer;

public class Filerenamer {

	public static String belrusToEngTranlit (String text){
		char[] abcCyr = {'а','б','в','г','д','ё','ж','з','и','к','л','м','н','о','п','р','с','т','у','ў','ф','х','ц','ш','щ','ы','э','ю','я'};
		char[] okSymbols = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','w','x','y','z','_','.'};
		String[] abcLat = {"a","b","v","g","d","jo","zh","z","i","k","l","m","n","o","p","r","s","t","u","w","f","h","ts","sh","sch","","e","ju","ja"};
		   StringBuilder english = new StringBuilder();
		    for (int nTextSymbol = 0; nTextSymbol < text.length(); nTextSymbol++) {
		        for(int nCyrSymbol = 0; nCyrSymbol < abcCyr.length; nCyrSymbol++ )
		        if (text.charAt(nTextSymbol) == abcCyr[nCyrSymbol] ) {
		            english.append(abcLat[nCyrSymbol]);
		        }
		        for(int nOk = 0; nOk < okSymbols.length; nOk++ )
		        if (text.charAt(nTextSymbol) == okSymbols[nOk]){
		        	english.append(okSymbols[nOk]);
		        }
		    }
		    return english.toString();
	}
	
	public static String lastDot (String text){
		int counter = 0;
		int countOfDel = 0;
		StringBuilder lastDotText = new StringBuilder(text);
					
			for( int i=0; i<text.length(); i++ ) {
			    if( text.charAt(i) == '.' ) {
			        counter++;
			    } 
			}	
			for( int i=0; i<text.length(); i++ ) {
			    if( text.charAt(i) == '.' ) {		    	
			        if(counter - countOfDel == 1){break;}
			        lastDotText.setCharAt(i,'_');
			        countOfDel++;
			    } 
			}
			    
		  return lastDotText.toString();  
	}

	
	public static String ifBig (String text){
		StringBuilder resizeText = new StringBuilder(text);
		while (text.length() > 200 ){
			for( int i=0; i<text.length(); i++ ) {
			    if( text.charAt(i) == '.' ) {		    	
			    	resizeText.deleteCharAt(i--);
			    } 
			}
		}
		return resizeText.toString();
	}
	
	
	public static void main(String[] args) {
		String text = "в.ло.а.вл_werw.erf$%";
		text = Filerenamer.belrusToEngTranlit(text);
		text = Filerenamer.lastDot(text);
		System.out.println(Filerenamer.ifBig(text));

	}

}
