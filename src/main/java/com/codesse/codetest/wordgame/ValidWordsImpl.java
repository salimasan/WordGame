package com.codesse.codetest.wordgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Copyright (C) 2021 Codesse. All rights reserved.
 * ••••••••••••••••••••••••••••••••••••••••••••••••
 */
public class ValidWordsImpl implements ValidWords {

	/*
	 * You should use other Collection classes other than Vector. Vector is synchronized.
	 * Also v should be an interface instead of an implementation
	 * Instead of Vector which has a N BigO for search operation, Tree (logN) or hashmap (1) implementations should be used
	 * One last thing access modifier is not given. Which means it is default. 
	 * The access modifier should be private
	 */
	Vector v = new Vector();

	/*
	 * Scanner can be used to read file instead of Streams 
	 * 
	 */
	public ValidWordsImpl() {
		try {
			InputStreamReader reader = new InputStreamReader(this.getClass().getResourceAsStream("/wordlist.txt"), "utf-8");
			BufferedReader in = new BufferedReader(reader);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				v.add(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean contains(String word) {
		return v.contains(word);
	}

	@Override
	public int size() {
		return v.size();
	}
}
