import java.util.Scanner;

import dua0empat8.Score;
import dua0empat8.helper.*;

/*
 * @author					: Jeffry Angtoni (jeffryang24)
 * @description				: Simple 2048 game
 * @date					: October 3, 2016
 */
public class Game {
	
	// Define Global Variable
	static RNG random = new RNG();
	static Score score = new Score();
	// Set notification Check Point. Default to 2048
	final static int iCheckPoint = 2048;
	static int[][] arrDimension;
	// Board Dimension. Default: 4
	static int iDimension = 4;
	static int[] arrNumberPool = {2,2,2,2,2,2,2,2,2,4};
	static boolean isGameOver = false;
	
	public static void main(String[] args) {
		//------------------ Initialization ------------------//
		
		// Input vessel for scanner
		String sInput = "";
		
		// Scanner object
		Scanner Scan = new Scanner(System.in);
		
		//------------------ Print Embel-Embel --------------//
		do{
			sInput = "";
			score.setScore(0);	// reset score
			score.setIs2048(false);
			Score.i2048Counter = 0;
			isGameOver = false;
			
			Utility.clearScreen();
			Banner.printTitle();
			boolean error = false;
			do{
				try{
					error = false;
					Utility.print("Input dimension [default: 4, max. 8]: ");
					sInput = Scan.nextLine();
					
					iDimension = Integer.parseInt(sInput);
					
					if (iDimension > 8){
						Utility.println("Max. board dimension is 8!");
						error = true;
					}
					
					if (iDimension < 4){
						Utility.println("Min. board dimension is 4!");
						error = true;
					}
				}catch(Exception ex){
					if (sInput.isEmpty() || sInput.matches("[ ]{1,}")){
						iDimension = 4;
						error = false;
					}else{
						Utility.println("Invalid input!");
						error = true;
					}
				}
			}while(error);
			
			
			
			// Initializing array
			arrDimension = new int[iDimension][iDimension];
			
			
			
			// Play Game
			Utility.clearScreen();
			randSetValue(iDimension, arrDimension, true);
			do{
				Utility.clearScreen();
				printScore();
				printBoard(iDimension, arrDimension);
				System.out.print("Input: ");
				sInput = Scan.nextLine();
				
				// check GameOver
				if (!SlideValue(iDimension, arrDimension, 1, true) && !SlideValue(iDimension, arrDimension, 2, true) && !SlideValue(iDimension, arrDimension, 3, true) && !SlideValue(iDimension, arrDimension, 4, true)){
					isGameOver = true;
				}
				
				if (sInput.equalsIgnoreCase("w")){
					if (SlideValue(iDimension, arrDimension, 1, false)) randSetValue(iDimension, arrDimension, false);
				}else if (sInput.equalsIgnoreCase("s")){
					if (SlideValue(iDimension, arrDimension, 2, false)) randSetValue(iDimension, arrDimension, false); 
				}else if (sInput.equalsIgnoreCase("a")){
					if (SlideValue(iDimension, arrDimension, 3, false)) randSetValue(iDimension, arrDimension, false);
				}else if (sInput.equalsIgnoreCase("d")){
					if (SlideValue(iDimension, arrDimension, 4, false)) randSetValue(iDimension, arrDimension, false);
				}else{
					System.out.print("Your input is not defined! Consider w,s,a, or d!");
					System.out.print("Press any key to continue...");
					Scan.nextLine();
				}
			}while(!isGameOver);
			
			Utility.clearScreen();
			Utility.println("GAME OVER!!");
			Utility.println("Score: " + score.getScore());
			Utility.print("Are you want to play again...? [Y/N]: ");
			
			sInput = Scan.nextLine();
			
		}while(sInput.equalsIgnoreCase("y"));
		
		Scan.close();
		Utility.println("Thanks for playing our games...");
		System.exit(0);
	}
	
	/*
	 * @slidePosition: 1 -> Up
	 * 				   2 -> Down
	 *                 3 -> Left
	 *                 4 -> Right
	 */
	
	public static boolean SlideValue(int iDimension, int[][] arrTile, int iSlidePosition, boolean bOnlyCheck){
		if (iSlidePosition == 1){
			int iStep = 0;
			for(int i=0; i<iDimension; i++){
				// fixing zero position
				for(int x=1;x<=iDimension;x++){
					for(int y=iDimension-1;y>=0;y--){
						if (y-1 >= 0 && arrDimension[y-1][i] == 0 && arrDimension[y][i] != 0){
							if (!bOnlyCheck){
								arrDimension[y-1][i] = arrDimension[y][i];
								arrDimension[y][i] = 0;
							}
							iStep++;
						}
					}
				}
				
				for(int j=0;j<iDimension;j++){
					if (j+1 < iDimension && arrDimension[j][i] == arrDimension[j+1][i] && arrDimension[j][i] != 0 && arrDimension[j+1][i] != 0){
						if (!bOnlyCheck){
							arrDimension[j][i] += arrDimension[j+1][i];
							arrDimension[j+1][i] = 0;
							check2048(arrDimension[j][i]);
							if (score.getScore() >= score.getHighScore()){
								int tmp = score.getScore();
								score.setScore(score.getScore() + arrDimension[j][i]);
								score.setHighScore(tmp + arrDimension[j][i]);
							}else{
								score.setScore(score.getScore() + arrDimension[j][i]);
							}
						}
						iStep++;
					}
				}
				
				// re-fixing zero position
				for(int x=1;x<=iDimension;x++){
					for(int y=iDimension-1;y>=0;y--){
						if (y-1 >= 0 && arrDimension[y-1][i] == 0 && arrDimension[y][i] != 0){
							if (!bOnlyCheck){
								arrDimension[y-1][i] = arrDimension[y][i];
								arrDimension[y][i] = 0;
							}
							iStep++;
						}
					}
				}
			}
			
			if (iStep == 0){
				// No move anymore
				return false;
			}
		}else if (iSlidePosition == 2){
			int iStep = 0;
			for(int i=0; i<iDimension; i++){
				// fixing zero position
				for(int x=1;x<=iDimension;x++){
					for(int y=0;y<iDimension;y++){
						if (y+1 < iDimension && arrDimension[y+1][i] == 0 && arrDimension[y][i] != 0){
							if (!bOnlyCheck){
								arrDimension[y+1][i] = arrDimension[y][i];
								arrDimension[y][i] = 0;
							}
							iStep++;
						}
					}
				}
				
				// merging
				for(int j=iDimension-1;j>=0;j--){
					if (j-1 >= 0 && arrDimension[j][i] == arrDimension[j-1][i] && arrDimension[j][i] != 0 && arrDimension[j-1][i] != 0){
						if (!bOnlyCheck){
							arrDimension[j-1][i] += arrDimension[j][i];
							arrDimension[j][i] = 0;
							check2048(arrDimension[j-1][i]);
							if (score.getScore() >= score.getHighScore()){
								int tmp = score.getScore();
								score.setScore(score.getScore() + arrDimension[j-1][i]);
								score.setHighScore(tmp + arrDimension[j-1][i]);
							}else{
								score.setScore(score.getScore() + arrDimension[j-1][i]);
							}
						}
						iStep++;
					}
					
				}
				
				// re-fixing zero position
				for(int x=1;x<=iDimension;x++){
					for(int y=0;y<iDimension;y++){
						if (y+1 < iDimension && arrDimension[y+1][i] == 0 && arrDimension[y][i] != 0){
							if (!bOnlyCheck){
								arrDimension[y+1][i] = arrDimension[y][i];
								arrDimension[y][i] = 0;
							}
							iStep++;
						}
					}
				}
			}
			
			if (iStep == 0){
				// No move anymore
				return false;
			}
		}else if (iSlidePosition == 3){
			int iStep = 0;
			for(int i=0;i<iDimension;i++){
				// fixing zero position
				for(int x=1;x<=iDimension;x++){
					for(int j=iDimension-1;j>=0;j--){
						if (j-1 >= 0 && arrDimension[i][j-1] == 0 && arrDimension[i][j] != 0){
							if (!bOnlyCheck){
								arrDimension[i][j-1] = arrDimension[i][j];
								arrDimension[i][j] = 0;
							}
							iStep++;
						}
					}
				}
				
				// merging
				for(int j=0;j<iDimension;j++){
					if (j+1 < iDimension && arrDimension[i][j+1] == arrDimension[i][j] && arrDimension[i][j] != 0 && arrDimension[i][j+1] != 0){
						if (!bOnlyCheck){
							arrDimension[i][j] += arrDimension[i][j+1];
							arrDimension[i][j+1] = 0;
							check2048(arrDimension[i][j]);
							if (score.getScore() >= score.getHighScore()){
								int tmp = score.getScore();
								score.setScore(score.getScore() + arrDimension[i][j]);
								score.setHighScore(tmp + arrDimension[i][j]);
							}else{
								score.setScore(score.getScore() + arrDimension[i][j]);
							}
						}
						iStep++;
					}
				}
				
				// fixing zero position
				for(int x=1;x<=iDimension;x++){
					for(int j=iDimension-1;j>=0;j--){
						if (j-1 >= 0 && arrDimension[i][j-1] == 0 && arrDimension[i][j] != 0){
							if (!bOnlyCheck){
								arrDimension[i][j-1] = arrDimension[i][j];
								arrDimension[i][j] = 0;	
							}
							iStep++;
						}
					}
				}
			}
			
			if (iStep == 0){
				//System.out.println("No Move!");
				return false;
			}
		}else if (iSlidePosition == 4){
			int iStep = 0;
			for(int i=0;i<iDimension;i++){
				// fixing zero position
				for(int x=1;x<=iDimension;x++){
					for(int j=0;j<iDimension;j++){
						if (j+1 < iDimension && arrDimension[i][j+1] == 0 && arrDimension[i][j] != 0){
							if (!bOnlyCheck){
								arrDimension[i][j+1] = arrDimension[i][j];
								arrDimension[i][j] = 0;							
							}
							iStep++;
						}
					}
				}
				
				// merging
				for(int j=iDimension-1;j>=0;j--){
					if (j-1 >= 0 && arrDimension[i][j-1] == arrDimension[i][j] && arrDimension[i][j] != 0 && arrDimension[i][j-1] != 0){
						if (!bOnlyCheck){
							arrDimension[i][j] += arrDimension[i][j-1];
							arrDimension[i][j-1] = 0;
							check2048(arrDimension[i][j]);
							if (score.getScore() >= score.getHighScore()){
								int tmp = score.getScore();
								score.setScore(score.getScore() + arrDimension[i][j]);
								score.setHighScore(tmp + arrDimension[i][j]);
							}else{
								score.setScore(score.getScore() + arrDimension[i][j]);
							}
						}
						iStep++;
					}
				}
				
				// fixing zero position
				for(int x=1;x<=iDimension;x++){
					for(int j=0;j<iDimension;j++){
						if (j+1 < iDimension && arrDimension[i][j+1] == 0 && arrDimension[i][j] != 0){
							if (!bOnlyCheck){
								arrDimension[i][j+1] = arrDimension[i][j];
								arrDimension[i][j] = 0;
							}
							iStep++;
						}
					}
				}
			}
			
			if (iStep == 0){
				// No move anymore
				return false;
			}
		}
		return true;
	}
	
	public static void randSetValue(int iDimension, int[][] arrTile, boolean isNewGame){
		/*
		 * Declaration
		 */
		int iX, iY;
		int iVal;
		
		if (isNewGame){
			int iX1, iY1;
			int iX2, iY2;
			do{
				do{
					iX1 = random.rand(0, iDimension-1);
					iY1 = random.rand(0, iDimension-1);
				}while(arrTile[iX1][iY1] != 0);
				
				do{
					iX2 = random.rand(0, iDimension-1);
					iY2 = random.rand(0, iDimension-1);
				}while(arrTile[iX2][iY2] != 0);
			}while(iX1 == iX2);
			
			iVal = arrNumberPool[random.rand(9)];
			arrTile[iX1][iY1] = iVal;
			
			
			iVal = arrNumberPool[random.rand(9)];
			arrTile[iX2][iY2] = iVal;
			return;
		}
		
		do{
			iX = random.rand(0, iDimension-1);
			iY = random.rand(0, iDimension-1);
		}while(arrTile[iX][iY] != 0);
		
		iVal = arrNumberPool[random.rand(9)];
		arrTile[iX][iY] = iVal;
	}
	
	public static void printBoard(int iDimension, int[][] arrTile){
		int iLargestNumber = checkLargestNumber(iDimension, arrTile);
		int iLargestLength = Integer.toString(iLargestNumber).length();
		
		// bypass iLargestLength for fixing stuttering
		if (iLargestLength < 3) iLargestLength = 3;
		
		// print box
		for(int i=1;i<=(3+iLargestLength)*iDimension;i++){
			if (i==1){
				Utility.print(":-");
			}else if (i==(3+iLargestLength)*iDimension){
				Utility.print(":");
			}else{
				Utility.print("-");
			}
		}
		Utility.println("");
		
		for(int i=0;i<iDimension;i++){
			for(int j=0;j<iDimension;j++){
				if (j == iDimension-1){
					String sFormat = String.format("| %-" + iLargestLength + "d |", arrTile[i][j]);
					Utility.print(sFormat);
				}else{
					String sFormat = String.format("| %-" + iLargestLength + "d ", arrTile[i][j]);
					Utility.print(sFormat);
				}
			}
			Utility.println("");
			for(int j=1;j<=(3+iLargestLength)*iDimension;j++){
				if (j==1){
					Utility.print(":-");
				}else if (j==(3+iLargestLength)*iDimension){
					Utility.print(":");
				}else{
					Utility.print("-");
				}
			}
			Utility.println("");
		}
		Utility.println("");
		Banner.printInputBanner();
	}
	
	public static void printScore(){
		// Add notification when user reachs 2048
		// @added by : Jeffry Angtoni
		// @date	 : October 30, 2016
		String sScore = "";
		if (score.Is2048() && Score.i2048Counter <= 1){
			sScore = String.format("Congratulations!!\n=======================\nYou just got %d!!!\nScore: %-10dHighest Score: %-10d \n\n", iCheckPoint, score.getScore(), score.getHighScore());
			score.setIs2048(false);
		}else{
			sScore = String.format("Score: %-10dHighest Score: %-10d \n\n", score.getScore(), score.getHighScore());
		}
		Utility.print(sScore);
	}
	
	public static int checkLargestNumber(int iDimension, int[][] arrTile){
		int tmp = -1;
		for(int i=0;i<iDimension;i++){
			for(int j=0;j<iDimension;j++){
				if (arrTile[i][j] > tmp){
					tmp = arrTile[i][j];
				}
			}
		}
		return tmp;
	}
	
	/**
	 * @author Jeffry Angtoni
	 * @date October 31st, 2016
	 * @description Check if current slide is 2048 or not?
	 * @param iDimensionPoint
	 * @param iCheckPoint
	 */
	public static void check2048(int iDimensionPoint){
		if (iDimensionPoint == iCheckPoint){
			score.setIs2048(true);
			Score.i2048Counter++;
			score.setLastDimensionPoint(iDimensionPoint);
		}else{
			score.setLastDimensionPoint(iDimensionPoint);
		}
	}
}
