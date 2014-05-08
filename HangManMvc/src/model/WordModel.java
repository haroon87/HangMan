package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordModel {

	private int count;
	private String words[] = new String[100];

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void readFile() throws IOException {
		
		String line="";
		BufferedReader br = new BufferedReader(new FileReader(
				"C:/Users/Haroon/Desktop/puzzles.txt"));
		while ((line = br.readLine()) != null) {
			words[count]=line;
			count++;
			}
	}

}
