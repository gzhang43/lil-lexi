/**
 * Author: Grace Zhang
 * File: ListIterator.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import java.util.List;

public class ListIterator extends Iterator<Glyph> {

	private List<Glyph> list;
	private int index;
	
	public ListIterator(List<? extends Glyph> rows) {
		this.list = (List<Glyph>) rows;
		index = 0;
	}

	/**
	 * 
	 */
	public void first() {
		index = 0;
	}

	/**
	 * 
	 */
	public void next() {
		index++;
	}

	/**
	 * 
	 */
	public boolean isDone() {
		if (index >= list.size()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	public Glyph currentItem() {
		return list.get(index);
	}

}
