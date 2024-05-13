/**
 * Author: Grace Zhang
 * File: PreorderIterator.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * Preorder iterator to aid in traversing the glyphs for spell checking and
 * other visitor classes.
 */
public class PreorderIterator extends Iterator<Glyph> {

	private Glyph root;
	private Deque<Iterator<Glyph>> iterators;
	
	public PreorderIterator(Glyph g) {
		root = g;
		iterators = new LinkedList<Iterator<Glyph>>();
	}
	
	/**
	 * Gets the first element of the iterator at the top of the list of 
	 * iterators.
	 */
	public void first() {
		Iterator<Glyph> i = root.createIterator();
		
		if (i != null) {
			i.first();
			if (iterators.size() > 0) {
				iterators.removeAll(iterators);
			}
			iterators.push(i);
		}
	}

	/**
	 * Traverses to the bottom of the glyph structure. 
	 */
	public void next() {
		Iterator<Glyph> i = iterators.peek().currentItem().createIterator();
		i.first();
		iterators.push(i);
		
		while (iterators.size() > 0 && iterators.peek().isDone()) {
			iterators.pop();
			if (iterators.peek() != null) {
				iterators.peek().next();
			}
		}
	}

	/**
	 * Returns true if the list of iterators is empty.
	 */
	public boolean isDone() {
		return iterators.isEmpty();
	}

	/**
	 * Gets the current item from the top iterator in the list of iterators.
	 */
	public Glyph currentItem() {
		if (iterators.size() > 0) {
			return iterators.peek().currentItem();
		}
		return null;
	}

}
