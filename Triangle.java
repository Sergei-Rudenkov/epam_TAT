package triangle;
import java.util.*;

public class Triangle {
	static Scanner scn = new Scanner(System.in);
	String answer;
	Triangle(){
		System.out.println("Hello puts please the sides of a traingle, like: 12,21,12");
		answer = scn.nextLine();
		while(notOk(answer)){
			System.out.println("Your line contains wrong symbols, please try again, like: 12,21,12");
			answer = scn.nextLine();
		}
	}	
	
	public boolean daesItTriang(String  answer){
		String[] arrOfSides = answer.split(",");
		int a = Integer.parseInt(arrOfSides[0]);
		int b = Integer.parseInt(arrOfSides[1]);
		int c = Integer.parseInt(arrOfSides[2]);
		if(a >= b+c || b >= a+c || c >= b+a ){
			return false;
		}else{
			return true;
		}	
	}
	public void whichOne(String  answer){
		String[] arrOfSides = answer.split(",");
		int a = Integer.parseInt(arrOfSides[0]);
		int b = Integer.parseInt(arrOfSides[1]);
		int c = Integer.parseInt(arrOfSides[2]);
		if(a == b || c == b || a == c ){
			System.out.println("It's isosceles triangle");
		}
		if(a == b && c == b){
			System.out.println("It's equilateral triangle");
		}
		if(Math.pow(a,2) == Math.pow(b,2) + Math.pow(c,2) || Math.pow(b,2) == Math.pow(a,2) + Math.pow(c,2) || Math.pow(c,2) == Math.pow(b,2) + Math.pow(a,2)){
			System.out.println("It's rectangular triangle");
		}	
	}
	private static boolean notOk(String  answer){
		if(answer != null ){
			String[] arr = answer.split(",");
			if(answer != null && answer.matches("[0-9,]+") && arr.length == 3){
				return false;
				}else{
				return true;
			}
		}else{
			return true;
		}	
		
		
	}
	public static void main(String[] args) {
		Triangle tr = new Triangle();
			if(tr.daesItTriang(tr.answer)){
				System.out.println("It's a triangle!");
				tr.whichOne(tr.answer);
			}else{
				System.out.println("It's not a triangle, sorry!");
			}
	}
}
