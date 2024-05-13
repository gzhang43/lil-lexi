/*
 * Author: Ralph Acosta
 * File: GlyphImage.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Image;

/**
 * Class for creating Glyphs containing images to insert into the document.
 */
public class GlyphImage extends Glyph {
	private String imageName; // Name of the image.
	private Image currentImage; // Image object.
	private Glyph parent; // Parent glpyh.
	private int height;
	private int width;
	
	public GlyphImage(String name, Display display) {
		currentImage = new Image(display, name);
		height = currentImage.getBounds().height;
		width = currentImage.getBounds().width;
	}
	
	/**
	 * Draws the image on the UI.
	 */
	@Override
	public void draw(PaintEvent e, Display display) {
		e.gc.drawImage(currentImage, getX(), getY());
	}

	/**
	 * Returns the width of the image.
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the image.
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the parent glyph.
	 */
	public Glyph parent() {
		return parent;
	}

	/**
	 * Returns the glyph's iterator.
	 */
	public Iterator<Glyph> createIterator() {
		return new NullIterator<Glyph>();
	}

	/*
	 * Visits the glyph image.
	 */
	public void accept(Visitor v) {		
	}
}
