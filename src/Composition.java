/**
 * Author: Ralph Acosta
 */

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Display;

/**
 * This class establishes the composition to be used in the document.
 */
public class Composition extends GlyphComposite {
	private List<Glyph> glyphs; // The glyphs to compose.
	private List<Row> rows; // The rows in the document.
	private int numRows; // The number of rows in the document.
	private Compositor compositor; // The compositor we are using.
	private int totalHeight;
	
	public Composition() {
		super();
		glyphs = new ArrayList<>();
		rows = new ArrayList<>();
		numRows = 0;
		totalHeight = 0;
	}
	
	/**
	 * Sets the compositor.
	 * 
	 * @param compositor
	 */
	public void setCompositor(Compositor compositor) {
		this.compositor = compositor;
	}
		
	/**
	 * Inserts a glyph into glyph collection.
	 */
	public void insert(Glyph g, int i) {
		glyphs.add(i, g);
		compositor.compose();
	}
	
	/**
	 * Removes the specified glyph from the glyph collection.
	 */
	public void remove(Glyph g) {
		glyphs.remove(g);
		compositor.compose();
	}
	
	/**
	 * Gets a specified glyph in our glyph collection.
	 */
	public Glyph child(int i) {
		return glyphs.get(i);
	}

	/**
	 * Gets the width of the composition. (Not currently used)
	 */
	@Override
	public int getWidth() {
		return 0;
	}

	/**
	 * Gets the height of the composition. (Not currently used)
	 */
	@Override
	public int getHeight() {
		return 0;
	}

	/**
	 * Gets the glyphs in the document.
	 */
	public List<Glyph> getGlyphs() {
		return glyphs;
	}
	
	/**
	 * Gets the rows in the document.
	 */
	public List<Row> getRows() {
		return rows;
	}
	
	/**
	 * Sets the rows in the document.
	 */
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	/**
	 * Updates the y position of every glyph to make it seem
	 * as though its moving up or down with the change in 
	 * scroller.
	 */
	public void updateRowHeight(int newFrom) {
		// Figure out height to add to the y position
		totalHeight = totalHeight + newFrom*50;
		compositor.compose();
	}
	
	public int getRowHeight() {
		return totalHeight;
	}
	
	/**
	 * Gets the last row in the document.
	 */
	public Glyph getLastRow() {
		return rows.get(numRows);
	}
	
	/**
	 * Draws the rows on the UI.
	 */
	public void draw(PaintEvent e, Display display) {
		for (Glyph row: rows) {
			row.draw(e, display);
		}
	}

	/**
	 * Iterator for composition.
	 */
	public ListIterator createIterator() {
		return new ListIterator(rows);
	}

	/**
	 * Visits the composition.
	 */
	@Override
	public void accept(Visitor v) {
		v.visitComposition(this);
	}
}
