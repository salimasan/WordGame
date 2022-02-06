package com.codesse.codetest.wordgame;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This is the shell implementation of the WordGame interface.
 * It is the class that you should focus on when developing your solution to the Challenge.
 */
public class WordGameImpl implements WordGame {

	//private String startingString;
	private ValidWords validWords;
	private int startingCharCount[];
	private SortedSet<LeaderBoard> board = new TreeSet<LeaderBoard>();
	
	public WordGameImpl(String string, ValidWords validWords) {
		//this.startingString = string;
		this.validWords = validWords;
		this.startingCharCount = findCharCountArray(string);
	}

	private int[] findCharCountArray(String string) {
		int charCount[] = new int[26];
		for (char ch: string.toCharArray()) {
			charCount[ch - 97]++;
		}
		return charCount;
	}

	public static void main(String [] args) {
		WordGameImpl t = new WordGameImpl("areallylongword", new ValidWordsImpl());
	}
	
	@Override
	public synchronized int submitWord(String playerName, String word) {
		
		int [] wordArr  = findCharCountArray(word);
		for (int i = 0 ;  i < wordArr.length ; i++) {
			if (startingCharCount[i] < wordArr[i]  ) {
				return 0; // Word contains other chars than starting string's characters
			}
		}
		if (!validWords.contains(word)) {
			return 0; // not a valid word
		}
		if (board.size() < 10) {
			LeaderBoard elem = new LeaderBoard();
			elem.setName(playerName);
			elem.setWord(word);
			board.add(elem);
		} else {
			
			LeaderBoard lastElem = board.last();
			if (lastElem.score < word.length()) {
				board.remove(lastElem);
				LeaderBoard newElem = new LeaderBoard();
				newElem.setName(playerName);
				newElem.setWord(word);
				board.add(newElem);
			}
		}
		
		return word.length();
	}

	@Override
	public String getPlayerNameAtPosition(int position) {
		return findEntryAtPosition(position).name;
	}

	@Override
	public String getWordEntryAtPosition(int position) {
		return findEntryAtPosition(position).word;
	}

	@Override
	public Integer getScoreAtPosition(int position) {
		return findEntryAtPosition(position).score;
	}
	
	private LeaderBoard findEntryAtPosition(int position) {
		Iterator<LeaderBoard> iter = board.iterator();
	    while (position >= 0){
			position--;
			if (position < 0) {
				return iter.next();
			} else {
				iter.next();
			}
		}
	    return null;
	}
	
	 class LeaderBoard implements Comparable<LeaderBoard> {
		
		private String name;
		private String word;
	    private int score;
		 
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
			this.score = word.length();
		}
	
		
		@Override
		public boolean equals(Object o) {
			
	        if (o == this) {
	            return true;
	        }
	 
	        if (!(o instanceof LeaderBoard)) {
	            return false;
	        }
	        
	        LeaderBoard l = (LeaderBoard) o;
			
			if (word.equals(l.word)) {
				return true;
			} else {
				return false;
			}
		}
		
		
		@Override
		public int compareTo(LeaderBoard o) {
			if(score == o.score)  {
				if (word == o.word) {
					return 0;
				} else {
					return -1; // First inserted should be before the latter  
				}
				 
			} else if(score > o.score) { 
				return -1;  
			} else  {
				return  1;  
			}  
		}
		 
     }

}
