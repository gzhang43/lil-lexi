/**
 * Author: Grace Zhang
 * File: AddGlyphCommand.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

/**
 * Command class for adding glyphs.
 */
public class AddGlyphCommand extends Command {
	private Composition composition; // composition containing all glyphs
	private Glyph glyph; // the glyph that is inserted
	private int index; // index the glyph is inserted at
	
	public AddGlyphCommand(Glyph glyph, int index, Composition composition) {
		this.composition = composition;
		this.glyph = glyph;
		this.index = index;
	}

	/**
	 * Inserts a glyph at an index.
	 */
	public void execute() {
		composition.insert(glyph, index);
	}

	/**
	 * Removes the glyph that was inserted.
	 */
	public void unexecute() {
		composition.remove(glyph);
	}
}
