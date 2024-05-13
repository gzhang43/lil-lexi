/**
 * AUTHOR: Grace Zhang
 * File: LilLexiDoc
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import java.util.List;

import java.util.ArrayList;

/**
 * This class establishes what the current document is. 
 */
public class LilLexiDoc
{
	private LilLexiUI ui; // UI of the current document.
	private Composition composition; // Composition of the current document.
	private List<Command> commandHistory; // The command history of the current document.
	private int pageHeight; // The page's height.
	private int pageWidth; // The page's width.
	private int numCols; // Number of columns in the document. 
	private int cursorIndex; // The cursor's index.
	private int commandHistIndex; // The current location in the history of the document.
	private String font; // The font style of the document.
	private int fontSize; // The font size of the document.
	private SpellingCheckingVisitor spellChecker;
	private PreorderIterator preorderIter;
	List<List<Character>> misspelled;
	
	public LilLexiDoc() 
	{
		commandHistory = new ArrayList<>();
		font = "Courier"; // Default font style.
		fontSize = 24;
		cursorIndex = 0;
		commandHistIndex = 0;
		spellChecker = new SpellingCheckingVisitor();
	}
	
	/**
	 * Sets the UI of the document.
	 */
	public void setUI(LilLexiUI ui) {
		this.ui = ui;
	}
	
	/**
	 * Sets the composition of the document.
	 */
	public void setComposition(Composition composition) {
		this.composition = composition;
		preorderIter = new PreorderIterator(composition);
	}
	
	/**
	 * Adds a glyph to the document at the cursor.
	 */
	public void add(Glyph g) {
		Command command = new AddGlyphCommand(g, cursorIndex, composition);
		command.execute();
		commandHistory.add(command);
		commandHistIndex++;
		cursorIndex++;
		checkSpelling();
		ui.updateUI();
	}
	
	/**
	 * Removes the glyph from the document at the cursor.
	 */
	public void remove() {
		if (composition.getGlyphs().size() > 0 && cursorIndex > 0) {
			Command command = new RemoveGlyphCommand(composition.child(cursorIndex - 1),
					cursorIndex, composition);
			command.execute();
			commandHistory.add(command);
			commandHistIndex++;
			cursorIndex--;
			
			if (composition.getGlyphs().size() >= 1) {
				checkSpelling();
			}
			ui.updateUI();
			spellChecker.clearHighlights();
		}
	}
	
	/**
	 * Undo a command in the document.
	 */
	public void undo() {
		if (commandHistory.size() > 0) {
			commandHistory.get(commandHistIndex-1).unexecute();
			commandHistIndex--;
			cursorIndex--;
			if (composition.getGlyphs().size() >= 1) {
				checkSpelling();
			}
			ui.updateUI();
		}
	}
	
	/**
	 * Redo a command in the document.
	 */
	public void redo() {
		if (commandHistIndex < commandHistory.size()) {
			commandHistory.get(commandHistIndex).execute();
			commandHistIndex++;
			cursorIndex++;
			if (composition.getGlyphs().size() >= 1) {
				checkSpelling();
			}
			ui.updateUI();
		}
	}
	
	/**
	 * Checks the spelling by visiting each character in the composition.
	 */
	void checkSpelling() {
		Glyph g;
		spellChecker.clear();
		for (preorderIter.first(); !preorderIter.isDone(); preorderIter.next()) {
			g = preorderIter.currentItem();
			g.accept(spellChecker);
		}
	}
	
	/**
	 * Sets the font of the document.
	 */
	public void setFont(String font) {
		this.font = font;
	}
	
	/**
	 * Get the current font of the document.
	 */
	public String getFont() {
		return font;
	}
	
	/**
	 * Set the font size of the document.
	 */
	public void setFontSize(int size) {
		this.fontSize = size;
	}
	
	/**
	 * Get the font size of the document.
	 */
	public int getFontSize() {
		return fontSize;
	}
	
	/**
	 * Get the page width of the document.
	 */
	public int getPageW() {
		return pageWidth;
	}
	
	/**
	 * Get the page height of the document.
	 */
	public int getPageH() {
		return pageHeight;
	}
	
	/**
	 * Get the number of columns in the document.
	 */
	public int getNumCols() {
		return numCols;
	}
	
	/**
	 * Get the cursor index.
	 */
	public int getCursorIndex() {
		return cursorIndex;
	}
	
	/**
	 * Set the cursor index.
	 */
	public void setCursorIndex(int index) {
		cursorIndex = index;
	}
	
	/**
	 * Get the rows in the current document.
	 */
	public List<Row> getRows(){
		return composition.getRows();
	}
	
	/**
	 * Get the composition of the document.
	 */
	public Composition getComposition() {
		return composition;
	}
}