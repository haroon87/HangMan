package model;

public class HangManPuzzle {
	
	 private String puzzleBlocks[] ;
	 private String word;
	 private int misses;
	 
	
	public String getWord() {
		return word;
	}


	public String[] getPuzzleBlocks() {
		return puzzleBlocks;
	}


	public void setPuzzleBlocks(String[] puzzleBlocks) {
		this.puzzleBlocks = puzzleBlocks;
	}


	public HangManPuzzle()
	{
		 puzzleBlocks = new String[6];
		 this.puzzleBlocks[0] = "    ______ ";
		 this.puzzleBlocks[1]=  "    |    | ";
		 this.puzzleBlocks[2]=  "         | ";
		 this.puzzleBlocks[3] = "         | ";
		 this.puzzleBlocks[4] = "         | ";
		 this.puzzleBlocks[5]=  "  _______| ";
		 
	}
	
	public void setWord(String str)
	{
		int l = str.length();
		for(int i=0;i<l;i++)
		{
			if(str.charAt(i)==' ')
			word=""+"  ";
			else
		   	word=""+"_";	
		}
		 
	}


	public int getMisses() {
		return misses;
	}


	public void setMisses(int misses) {
		this.misses = misses;
	}

}
