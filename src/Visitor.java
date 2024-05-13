/**
 * Author: Ralph Acosta
 * File: Visitor.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

/**
 * Abstract interface Visitor that "visits" objects and does appropriate task
 * (e.g. check spelling or hyphenate).
 */
public interface Visitor {
	
	/**
	 * Visits the character class.
	 */
	void visitCharacter(Character character);
	
	/**
	 * Visits the image class.
	 */
	void visitImage(GlyphImage image);
	
	/**
	 * Visits the shape class.
	 */
	void visitShape(Shape shape);
	
	void visitRow(Row row);
	
	void visitComposition(Composition c);
}
