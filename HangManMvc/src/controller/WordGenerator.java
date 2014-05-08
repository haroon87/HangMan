package controller;

import java.util.Random;

public class WordGenerator {
	
	private Random selector ;
	private String word;
	private int index;
	private String gameWord;
	
	public WordGenerator()
	{
		selector = new Random(System.currentTimeMillis());
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void generate(String words[],int len)
	{
		index =selector.nextInt(len);
		word = words[index];
		gameWord = word.replaceAll("[A-Za-z]","-");
	}

	public String getGameWord() {
		return gameWord;
	}

	public void setGameWord(String gameWord) {
		this.gameWord = gameWord;
	}

}
