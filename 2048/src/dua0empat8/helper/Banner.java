package dua0empat8.helper;

public class Banner extends Utility{
	
	public static void printTitle(){
		printf("%12d %d %d %d\n", 2, 0, 4, 8);
		printf("%20s", "By: Jfree\n");
		printf("=============================\n");
	}
	
	public static void printInputBanner(){
		println("Type w or s or a or d to slide up/down/left/right the tile!!");
	}
}
