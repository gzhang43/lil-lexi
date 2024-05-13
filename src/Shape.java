/**
 * Author: Grace Zhang
 * File: Shape.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Display;

/**
 * Class for shape glyphs to insert into the document.
 */
public class Shape extends Glyph {
	
	private Glyph parent;
	private int heightDraw; // The actual height of the shape. 
	private int widthDraw; // The actual width of the shape.
	private int height; // The padded height of the shape.
	private int width; // The padded height of the shape.
	
	public Shape(int heightDraw, int widthDraw) {
		this.heightDraw = heightDraw;
		this.widthDraw = widthDraw;
		this.height = heightDraw + 10;
		this.width = widthDraw + 10;	
	}

	/**
	 * Visits this shape.
	 */
	public void accept(Visitor visitor) {
		visitor.visitShape(this);
	}
	
	/**
	 * Draws the rectangle on the UI.
	 */
	public void draw(PaintEvent e, Display display) {
		e.gc.drawRectangle(getX(), getY(), widthDraw, heightDraw);
	}

	/**
	 * Return the width of the shape.
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the shape.
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the parent of the glyph.
	 */
	public Glyph parent() {
		return parent;
	}
	
	/*
	 * Gets the character. (Not used)
	 */
	@Override
	public char getChar() {
		return 0;
	}

	/*
	 * Creates the iterator for shape.
	 */
	public Iterator<Glyph> createIterator() {
		return new NullIterator<Glyph>();
	}
}
