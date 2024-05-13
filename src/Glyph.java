/**
 * Author: Ralph Acosta
 * File: Glyph.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

/**
 * Abstract class for Glyphs in the document.
 */
public abstract class Glyph
{
	private PaintEvent toPaint; // The swt paint object.
	private int x, y; // The x and y position of the glyph.
	
	public Glyph() {}
	
	/**
	 * Gets the width of a glyph.
	 */
	public abstract int getWidth();
	
	/**
	 * Gets the height of a glyph.
	 */
	public abstract int getHeight();
	
	/**
	 * Gets the parent of a glyph.
	 */
	public abstract Glyph parent();

	/**
	 * Draws a glyph to the UI.
	 */
	public abstract void draw(PaintEvent e, Display display);
	
	/*
	 * Creates the iterator for each type of glyph.
	 */
	public abstract Iterator<Glyph> createIterator();
	
	/*
	 * Accepts a visitor for each type of glyph.
	 */
	public abstract void accept(Visitor v);

	/**
	 * Returns a character glyph. 
	 */
	public char getChar() {
		return 0;
	}
	
	/**
	 * Sets the x and y position of a glyph on the UI.
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x position of a glyph.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y position of a glyph.
	 */
	public int getY() {
		return y;
	}
}