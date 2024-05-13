/**
 * Author: Grace Zhang
 * File: RemoveGlyphComposite.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

/**
 * Command class for removing glyphs.
 */
public class RemoveGlyphCommand extends Command {

	private Composition composition;
	private Glyph glyph;
	private int index;
	
	public RemoveGlyphCommand(Glyph glyph, int index, Composition composition) {
		this.composition = composition;
		this.glyph = glyph;
		this.index = index;
	}
	
	/**
	 * Executes command to remove the specified glyph. 
	 */
	public void execute() {
		composition.remove(glyph);
		index--;
	}

	/**
	 * Unexecutes remove command by re-adding the glyph in the correct position.
	 */
	public void unexecute() {
		composition.insert(glyph, index);
		index++;
	}
}
