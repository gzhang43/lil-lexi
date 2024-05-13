/**
 * Author: Both
 * File: LilLexiControl.java 
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */


/**
 * This class establishes the controller for the document.
 */
public class LilLexiControl 
{
	private LilLexiDoc currentDoc;

	/**
	 * LilLexiControl
	 */
	public LilLexiControl(LilLexiDoc doc)
	{
		this.currentDoc = doc;
	}
	
	/**
	 * Adds a glyph to the current document.
	 */
	void add(Glyph g) {
		currentDoc.add(g);
	}
	
	/**
	 * Removes glyph from the current document at the cursor. 
	 */
	void remove() {
		currentDoc.remove();
	}
	
	/**
	 * Moves the cursor through the glyphs in the document.
	 */
	void moveCursor(int dir) {
		int index = currentDoc.getCursorIndex();
		if (dir == 0 && index > 0) {
			currentDoc.setCursorIndex(index - 1);
		}
		else if (index < currentDoc.getComposition().getGlyphs().size()) {
			currentDoc.setCursorIndex(index + 1);
		}
	}
	
	/**
	 * Undo a change in the document.
	 */
	void undo(){
		currentDoc.undo();
	}
	
	/**
	 * Redo a change in the document.
	 */
	void redo() {
		currentDoc.redo();
	}

	/**
	 * Set the font style of characters drawn in the document.
	 */
	void setFont(String font) {
		currentDoc.setFont(font);
	}
	
	/**
	 * Set the font size of characters drawn in the document.
	 */
	void setFontSize(int size) {
		currentDoc.setFontSize(size);
	}
	
	/**
	 * quitEditor  user quits
	 */
	void quitEditor() 
	{ 
		System.exit(0); 
	}	
}






