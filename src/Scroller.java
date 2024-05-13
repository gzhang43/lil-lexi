/**
 * Author: Ralph Acosta
 * File: Scroller.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import java.awt.Rectangle;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Slider;

/**
 * Decorator class that adds a scroll bar.
 */
public class Scroller extends MonoGlyph {
	
	private Composition composition;
	
	private List<Row> rows;
	private int canvasHeight = 800;
	private int from;
	private Canvas canvas;
	
	public Scroller(Composition composition, Canvas canvas) {
		this.composition = composition;
		rows = this.composition.getRows();
		from = 0;
		this.canvas = canvas;
	}
	
	/**
	 * Returns the number of rows.
	 */
	public int getSize() {
		return rows.size();
	}
	
	/**
	 * Gets where we want to start from.
	 */
	public int getFrom() {
		return from;
	}
	
	/**
	 * Sets beginning row.
	 */
	public void setFrom(int newFrom) {
		from = newFrom;
		composition.updateRowHeight(from);
	}
	
	/**
	 * Increments beginning row.
	 */
	public void incrementFrom() {
		this.from = 1;
		composition.updateRowHeight(from);
	}
	
	/**
	 * Decrements beginning row.
	 */
	public void decrementFrom() {
		this.from = -1;
		composition.updateRowHeight(from);
		
	}
	
	@Override
	public void draw(PaintEvent e, Display display) {;
		composition.draw(e, display);
	}
	
	public void drawScroller() {
        Slider slider = new Slider (canvas, SWT.VERTICAL);
		org.eclipse.swt.graphics.Rectangle clientArea = canvas.getClientArea();
		slider.setBounds (clientArea.width - 40, clientArea.y + 10, 32, clientArea.height);
		slider.addListener (SWT.Selection, event -> {
			String string = "SWT.NONE";
			switch (event.detail) {
				case SWT.DRAG: string = "SWT.DRAG"; break;
				case SWT.PAGE_DOWN: string = "SWT.PAGE_DOWN"; decrementFrom(); canvas.redraw(); break;
				case SWT.PAGE_UP: string = "SWT.PAGE_UP"; incrementFrom(); canvas.redraw(); break;
			}
			System.out.println ("Scroll detail -> " + string);
		});
	}
	
	public Composition getComposition() {
		return composition;
	}
	
	public boolean compositionFull() {
		if (composition.getRows().size() == 0) {
			return false;
		}
		if (composition.getLastRow().getY()+composition.getLastRow().getHeight() > 500) {
			return true;
		}
		return false;
	}

	/**
	 * Creates iterator for scroller.
	 */
	@Override
	public Iterator<Glyph> createIterator() {
		return composition.createIterator();
	}

	/**
	 * Visits scroller.
	 */
	@Override
	public void accept(Visitor v) {
		v.visitComposition(composition);
	}	
}
