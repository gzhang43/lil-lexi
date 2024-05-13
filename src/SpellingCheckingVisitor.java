/**
 * AUTHOR: Grace Zhang
 * File: SpellingCheckingVisitor.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpellingCheckingVisitor implements Visitor {
	
	private List<Character> currentWord;
	private List<List<Character>> misspellings;
	
	public SpellingCheckingVisitor() {
		currentWord = new ArrayList<>();
		misspellings = new ArrayList<>();
	}
	
	/**
	 * Adds the character to the current word if it is a letter, and checks
	 * the current word for misspellings if the character is not a letter.
	 */
	public void visitCharacter(Character c) {
		char ch = c.getChar();
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
			currentWord.add(c);
		}
		else {
			if (isMisspelled(currentWord)) {
				misspellings.add(currentWord);
			}
			currentWord.clear();
		}
	}
	
	public void visitRow(Row row) {
	}
	
	public void visitImage(GlyphImage image) {
	}
	
	public void visitShape(Shape rect) {
	}
	
	public void visitComposition(Composition c) {
	}
	
	public List<List<Character>> getMisspellings() {
		return misspellings;
	}
	
	/**
	 * Adds current word to the list of misspelled words if it is not
	 * found in the dictionary text file.
	 */
	private boolean isMisspelled(List<Character> currentWord) {
		char[] word = new char[currentWord.size()];
		for (int i = 0; i < currentWord.size(); i++) {
			word[i] = currentWord.get(i).getChar();
		}
		
		String wordString = new String(word); // convert to String
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("wordlist.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		boolean validWord = false;
		while (scanner.hasNextLine()) {
			if (scanner.nextLine().equals(wordString)) {
				System.out.println("Valid word.");
				validWord = true;
				break;
			}
		}
		
		if (validWord == false) {
			misspellings.add(currentWord);
			highlightMisspelled();
			System.out.println("Invalid word.");
		}
		return false;
	}
	
	/**
	 * Marks the letters of misspelled words as highlighted.
	 */
	public void highlightMisspelled() {
		for (int i = 0; i < misspellings.size(); i++) {
			for (int j = 0; j < misspellings.get(i).size(); j++) {
				misspellings.get(i).get(j).setHighlight(true);
			}
		}
	}
	
	/**
	 * Clears the letters of highlights.
	 */
	public void clearHighlights() {
		for (int i = 0; i < misspellings.size(); i++) {
			for (int j = 0; j < misspellings.get(i).size(); j++) {
				misspellings.get(i).get(j).setHighlight(false);
			}
		}
	}
	
	/**
	 * Clears the current word and misspellings.
	 */
	public void clear() {
		currentWord = new ArrayList<>();
		misspellings = new ArrayList<>();
	}
	
}
