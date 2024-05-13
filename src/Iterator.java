/**
 * Author: Grace Zhang
 * File: Iterator.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

public abstract class Iterator<E> {

	/**
	 * Sets the iterator to the first element of the structure.
	 */
	public abstract void first();
	
	/**
	 * Sets the iterator to the next element.
	 */
	public abstract void next();
	
	/**
	 * Returns true if iterator reaches the end of the structure.
	 */
	public abstract boolean isDone();
	
	/**
	 * Returns the current item the iterator is on.
	 */
	public abstract E currentItem();
	
}
