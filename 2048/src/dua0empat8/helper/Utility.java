package dua0empat8.helper;

public class Utility {
	
	public static void printf(String format, Object... obj) {
		System.out.printf(format, obj);
	}
	
	public static void print(String obj){
		System.out.print(obj);
	}
	
	public static void println(String obj){
		System.out.println(obj);
	}
	
	public static void clearScreen() {
		for(int i=0;i<100;i++) System.out.println("");
	}
}
