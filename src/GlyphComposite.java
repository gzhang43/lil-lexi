/**
 * Author: Ralph Acosta
 * File: GlyphComposite.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for composite Glyphs.
 */
public abstract class GlyphComposite extends Glyph {
	
	private List<Glyph> children; // The glyphs in the document.
	private Glyph parent; // Parent glyph.

	public GlyphComposite() {
		children = new ArrayList<>();
	}

	/**
	 * Inserts a glyph at specified index.
	 */
	public abstract void insert(Glyph g, int i);
	
	/**
	 * Removes the specified glyph.
	 */
	public abstract void remove(Glyph g);
	
	/**
	 * Returns the child glyph at the specified index.
	 */
	public abstract Glyph child(int i);
	
	/**
	 * Returns the parent glyph.
	 */
	public Glyph parent() {
		return parent;
	}
}
