/**
 * Author: Grace Zhang
 * File: Character.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * This class creates the characters for the document.
 */
public class Character extends Glyph {
	
	private char c; // The current character to add.
	private Glyph parent; // Parent glyph.
	private int height; // The height of the character.
	private int width; // The width of the character.
	private String font; // The font of the character.
	private int fontSize; // The font size of the character.
	private static Font fontObject; // The Font object of the character.
	private boolean highlight; // Boolean of whether character is highlighted.

	public Character(char c, int fontSize, String font) {
		super();
		this.c = c;
		this.fontSize = fontSize;
		this.height = fontSize * 4/3;
		this.width = fontSize * 3/4;
		this.font = font;
		this.highlight = false;
	}

	/**
	 * Visits this character.
	 */
	public void accept(Visitor visitor) {
		visitor.visitCharacter(this);
	}
	
	/**
	 * Returns the character.
	 */
	public char getChar() {
		return c;
	}
	
	/**
	 * Sets the character.
	 */
	public void setChar(char c) {
		this.c = c;
	}

	/**
	 * Draws the character on our UI.
	 */
	public void draw(PaintEvent e, Display display) {
		if (e.gc.getFont().toString().equals(font) == false) {
			if (fontObject != null) {
				fontObject.dispose();
			}
    		fontObject = new Font(display, this.font, fontSize, SWT.BOLD);
    		e.gc.setFont(fontObject);
		}
		if (highlight) {
			e.gc.setForeground(display.getSystemColor(SWT.COLOR_RED));
		}
		else {
			e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLUE));
		}
		e.gc.drawString("" + c, getX(), getY());
	}

	/**
	 * Returns the width of the character.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets the width of the character.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Returns the height of the character.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets whether a character is highlighted.
	 */
	public void setHighlight(boolean val) {
		highlight = val;
	}
	
	/**
	 * Returns the parent glyph.
	 */
	public Glyph parent() {
		return parent;
	}

	/**
	 * Iterator for character.
	 */
	public Iterator<Glyph> createIterator() {
		return new NullIterator<Glyph>();
	}
}
