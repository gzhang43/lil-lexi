/**
 * Author: Ralph Acosta
 * File: Row.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Display;

/**
 * This class instantiates a row to be inserted in the document.
 */
public class Row extends GlyphComposite {

	private int currWidth = 0; // The current width of the row.
	private int width = 40*18; // Davids max row width
	private int yPosition; // The Y position of the row.
	private List<Glyph> glyphs; // The glyphs in the row.
	
	public Row() {
		glyphs = new ArrayList<Glyph>();
	}
	
	/**
	 * Inserts a glyph into the row.
	 */
	public void insert(Glyph g, int i) {
		glyphs.add(i, g);
	}
	
	/**
	 * Removes a glyph from a row.
	 */
	public void remove(Glyph g) {
		glyphs.remove(g);
	}
	
	/**
	 * Gets a specified glyph in the row.
	 */
	public Glyph child(int i) {
		return glyphs.get(i);
	}
	
	/**
	 * Sets the Y position of the row.
	 */
	public void setYPosition(int y) {
		yPosition = y;
	}
	
	/**
	 * Gets the Y position of the row.
	 */
	public int getYPosition() {
		return yPosition;
	}
	
	/**
	 * Gets the glyphs in the row.
	 */
	public List<Glyph> getGlyphs(){
		return glyphs;
	}

	/**
	 * Adds a glyph's width to the current row's width.
	 */
	public void addGlyphsWidth(Glyph glyph) {
		currWidth += glyph.getWidth();
	}
	
	/**
	 * Gets the current spot in the row. (X position)
	 */
	public int lastXPosition() {
		return currWidth;
	}

	/**
	 * Gets the total possible width of a row.
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the height of a row. The height of a row is determined by the 
	 * tallest glyph in the row.
	 */
	@Override
	public int getHeight() {
		int max = glyphs.get(0).getHeight();
		for (int i = 1; i < glyphs.size(); i++) {
			if (glyphs.get(i).getHeight() > max) {
				max = glyphs.get(i).getHeight();
			}
		}
		return max;
	}

	/**
	 * Draws the row's glyphs to the UI.
	 */
	@Override
	public void draw(PaintEvent e, Display display) {
		for (Glyph glyph : glyphs) {
			glyph.draw(e, display);
		}
	}

	/*
	 * Creates iterator for row.
	 */
	@Override
	public Iterator<Glyph> createIterator() {
		return new ListIterator(glyphs);
	}

	/*
	 * Visits the row.
	 */
	@Override
	public void accept(Visitor v) {
		v.visitRow(this);
	}
}
