package dua0empat8;

public class Score {
	private Integer __PLAYER_SCORE;
	private Integer __PLAYER_HIGHEST_SCORE;
	private Integer __GAME_LAST_DIMENSION_POINT;
	private Boolean __IS2048;
	public static int i2048Counter = 0;
	
	public Score(){
		this.__PLAYER_SCORE = this.__PLAYER_HIGHEST_SCORE = this.__GAME_LAST_DIMENSION_POINT = 0;
		this.__IS2048 = false;
	}
	
	public int getScore(){
		return this.__PLAYER_SCORE;
	}
	
	public void setScore(int iScore){
		this.__PLAYER_SCORE = iScore;
	}
	
	public void setLastDimensionPoint(int iPoint){
		this.__GAME_LAST_DIMENSION_POINT = iPoint;
	}
	
	public void setIs2048(boolean b2048){
		this.__IS2048 = b2048;
	}
	
	public boolean Is2048(){
		return this.__IS2048;
	}
	
	public int getLastDimensionPoint(){
		return this.__GAME_LAST_DIMENSION_POINT;
	}
	
	public int getHighScore(){
		return this.__PLAYER_HIGHEST_SCORE;
	}
	
	public void setHighScore(int iScore){
		this.__PLAYER_HIGHEST_SCORE = iScore;
	}
}
