/**
 * Author: Both
 * File: LilLexi.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

 
import java.util.ArrayList;
import java.util.List;

/*
 * Lil Lexi Document Editor
 */
public class LilLexi
{
	static private LilLexiDoc currentDoc = null;

	public static void main(String args[])
	{	
		if (currentDoc == null)
			currentDoc = new LilLexiDoc();
		LilLexiUI lexiUI = new LilLexiUI();
		lexiUI.setCurrentDoc(currentDoc);
		
		// Use lilLexi UI to check if we need to add a document.
		currentDoc.setUI(lexiUI);
		
		Compositor compositor = new SimpleCompositor();
		Composition composition = new Composition();
		compositor.setComposition(composition);
		composition.setCompositor(compositor);
		currentDoc.setComposition(composition);
		
		LilLexiControl lexiControl = new LilLexiControl(currentDoc);
		lexiUI.setController(lexiControl);
		
		lexiUI.start();
	} 
}


