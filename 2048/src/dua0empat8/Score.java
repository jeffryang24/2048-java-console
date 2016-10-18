package dua0empat8;

public class Score {
	private Integer __PLAYER_SCORE;
	private Integer __PLAYER_HIGHEST_SCORE;
	
	public Score(){
		//set first anonymous player for first time play
		this.__PLAYER_SCORE = this.__PLAYER_HIGHEST_SCORE = 0;
	}
	
	public int getScore(){
		return this.__PLAYER_SCORE;
	}
	
	public void setScore(int iScore){
		this.__PLAYER_SCORE = iScore;
	}
	
	public int getHighScore(){
		return this.__PLAYER_HIGHEST_SCORE;
	}
	
	public void setHighScore(int iScore){
		this.__PLAYER_HIGHEST_SCORE = iScore;
	}
}
