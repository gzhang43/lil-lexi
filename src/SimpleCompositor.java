/**
 * AUTHOR: Grace Zhang
 * File: SimpleCompositor.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import java.util.List;
import java.util.ArrayList;

/**
 * This class composes glyphs into rows by utilizing a line formatting algorithm.
 */
public class SimpleCompositor extends Compositor {
	private Composition composition; // The current composition.
	
	/**
	 * Sets the composition of the document.
	 */
	@Override
	public void setComposition(Composition composition) {
		this.composition = composition;
	}

	/**
	 * Composes the glyphs in the document into rows by accounting for line capacity and the
	 * changes within the document.
	 */
	@Override
	public void compose() {	
		List<Row> rows = new ArrayList<Row>();;
		if (composition.getGlyphs().size() == 0) {
			composition.setRows(rows);
			return;
		}
		
		Row currRow = new Row();
		currRow.setYPosition(10 + composition.getRowHeight());
		rows.add(currRow);
		
		int j = 0;
		for (int i = 0; i < composition.getGlyphs().size(); i++) {
		Glyph currentGlyph = composition.getGlyphs().get(i);
			// Adds a new row if current row will max out with the next glyph
			if (currRow.lastXPosition() + currentGlyph.getWidth() >= currRow.getWidth()) {
				Row temp = new Row();
				// Set y position of new row depending on max height of glyphs in previous row
				temp.setYPosition(currRow.getYPosition() + currRow.getHeight());
				rows.add(temp);
				currRow = temp;
				j = 0;
			}
			currentGlyph.setPosition(currRow.lastXPosition(), currRow.getYPosition());
			currRow.insert(currentGlyph, j);
			currRow.addGlyphsWidth(currentGlyph);
			j++;
		}
		composition.setRows(rows);
	}
}
