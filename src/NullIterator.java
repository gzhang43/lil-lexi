/**
 * AUTHOR: Grace Zhang
 * File: NullIterator.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */
public class NullIterator<E> extends Iterator<E> {

	/*
	 * Determines first in iteration.
	 */
	public void first() {}

	/*
	 * Determines next in iteration.
	 */
	public void next() {}

	/*
	 * Determines if iteration is done.
	 */
	public boolean isDone() {
		return true;
	}

	/*
	 * Returns the current item in iterations.
	 */
	public E currentItem() {
		return null;
	}
}
