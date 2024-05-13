/**
 * Author: Ralph Acosta
 * File: Monoglyph.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Display;

/**
 * This class allows for the extension of various decorator objects.
 */
public abstract class MonoGlyph extends Glyph {

	public MonoGlyph() {}
	
	/*
	 * Pulled from glyph. (Not needed)
	 */
	@Override
	public char getChar() {
		return 0;
	}

	/*
	 * Gets the width of the decorator.
	 */
	@Override
	public int getWidth() {
		return 0;
	}

	/*
	 * Gets the height of the decorator.
	 */
	@Override
	public int getHeight() {
		return 0;
	}

	/*
	 * Gets the parent glyph.
	 */
	@Override
	public Glyph parent() {
		return null;
	}

	/*
	 * Draws the changes with decorators.
	 */
	@Override
	public abstract void draw(PaintEvent e, Display display);
}
