package com.codesse.codetest.wordgame;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (C) 2021 Codesse. All rights reserved.
 * ••••••••••••••••••••••••••••••••••••••••••••••••
 */
public class SubmissionTest {

	static ValidWords validWords;
	WordGame service;

	@BeforeClass
	public static void oneTimeSetUp() {
		validWords = new ValidWordsImpl();
	}

	@Before
	public void setUp() throws Exception {
		service = new WordGameImpl("areallylongword", validWords);
	}

	@Test
	public void testSubmission() throws Exception {
		assertEquals(3, service.submitWord("player1", "all"));
		assertEquals(3, service.submitWord("player1", "all"));
		assertEquals(4, service.submitWord("player2", "word"));
		assertEquals(0, service.submitWord("player3", "tale"));
		assertEquals(0, service.submitWord("player4", "glly"));
		assertEquals(6, service.submitWord("player5", "woolly"));
		assertEquals(0, service.submitWord("player6", "adder"));
	}

}
