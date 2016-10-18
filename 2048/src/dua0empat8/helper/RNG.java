package dua0empat8.helper;

import java.util.Random;

/**
 * @author				: Jfree
 * @description			: Random Number Generator
 */
public class RNG implements RNG_Interface {
	
	/*
	 * Global Variable
	 */
	private Random __RNG_OBJ;
	private long __RNG_SEED;
	
	
	public RNG(){
		this.__RNG_OBJ = new Random();
	}
	
	public RNG(long seed){
		this.__RNG_SEED = seed;
		this.__RNG_OBJ = new Random(this.__RNG_SEED);
	}
	
	public void setSeed(long seed){
		this.__RNG_SEED = seed;
	}
	
	public long getSeed(){
		return this.__RNG_SEED;
	}
	
	public int rand(int max){
		return this.__RNG_OBJ.nextInt(max + 1);
	}
	
	public int rand(int min, int max){
		return this.__RNG_OBJ.nextInt((max - min) + 1) + min;
	}
}
