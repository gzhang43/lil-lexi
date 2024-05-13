/**
 * Author: Both
 * File: LilLexiUI.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Font;

/**
 * LilLexiUI. Establishes the user interface for the user.
 */
public class LilLexiUI
{
	private LilLexiDoc currentDoc;
	private LilLexiControl lexiControl;
	private Display display;
	private Shell shell;
	private Label statusLabel;
	private Canvas canvas;
	
	/**
	 * Ctor
	 */
	public LilLexiUI() 
	{
		//---- create the window and the shell
		Display.setAppName("Lil Lexi");
		display = new Display();  
		shell = new Shell(display);
	    shell.setText("Lil Lexi");
		shell.setSize(900,900);
		shell.setLayout( new GridLayout());	
	}
		
	/**
	 * start the editor
	 */
	public void start()
	{	
		//---- create widgets for the interface
	    Composite upperComp = new Composite(shell, SWT.NO_FOCUS);
	    Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);
	    
	    //---- canvas for the document
		canvas = new Canvas(upperComp, SWT.NONE);
		canvas.setSize(800,800);

		Scroller scroller = new Scroller(currentDoc.getComposition(), canvas);
		canvas.addPaintListener(e -> {
			System.out.println("PaintListener");
			Rectangle rect = shell.getClientArea();
			e.gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE)); 
            e.gc.fillRectangle(rect.x, rect.y, rect.width, rect.height);
            e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLUE)); 
    		// recursively draw all glyphs
    		scroller.draw(e, display);    		
		});	
		
        canvas.addMouseListener(new MouseListener() {
            public void mouseDown(MouseEvent e) {
            	System.out.println("mouseDown in canvas");
            } 
            public void mouseUp(MouseEvent e) {} 
            public void mouseDoubleClick(MouseEvent e) {} 
        });
        
        canvas.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent e) {
        		if (e.character == 8) {
        			lexiControl.remove();
        		}
        		else if (e.keyCode == SWT.ARROW_LEFT) {
        			lexiControl.moveCursor(0);
        		}
        		else if (e.keyCode == SWT.ARROW_RIGHT) {
        			lexiControl.moveCursor(1);
        		}
        		else {
        			System.out.println("key " + e.character);
        			// set the height and width of the character here
            		lexiControl.add(new Character(e.character, currentDoc.getFontSize(), currentDoc.getFont()));
        		}
        	}
        	public void keyReleased(KeyEvent e) {}
        });
        
        // The scroller/slider for the document.
        scroller.drawScroller();
                
        //---- status label
        lowerComp.setLayout(new RowLayout());
        statusLabel = new Label(lowerComp, SWT.NONE);		

		FontData[] fD = statusLabel.getFont().getFontData();
		fD[0].setHeight(24);
		statusLabel.setFont( new Font(display,fD[0]));
		statusLabel.setText("Ready to edit!");
		
		//---- main menu
		Menu menuBar, fileMenu, insertMenu, helpMenu, fontsMenu, fontSizeMenu, undoRedoMenu;
		MenuItem fileMenuHeader, insertMenuHeader, helpMenuHeader, fontsMenuHeader;
		MenuItem fontSizeMenuHeader, undoRedoHeader;
		MenuItem fileExitItem, fileSaveItem, helpGetHelpItem;
		MenuItem insertCartoonCar, insertCartoonHeart, insertCartoonLion; 
		MenuItem insertRectItem100_100, insertRectItem100_200, insertRectItem200_400;
		MenuItem courierNew, andale, courier, fileUndoOption, fileRedoOption;
		MenuItem size12, size18, size24;
		
		menuBar = new Menu(shell, SWT.BAR);
		
		// File menu option.
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("File");
		fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);

		// Save and Exit menu options.
	    fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileSaveItem.setText("Save");
	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("Exit");
	    
	    // Inserting menu options.
		insertMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		insertMenuHeader.setText("Insert");
		insertMenu = new Menu(shell, SWT.DROP_DOWN);
		insertMenuHeader.setMenu(insertMenu);

		// Images menu options.
	    insertCartoonCar = new MenuItem(insertMenu, SWT.PUSH);
	    insertCartoonCar.setText("Cartoon Car");
	    insertCartoonHeart = new MenuItem(insertMenu, SWT.PUSH);
	    insertCartoonHeart.setText("Cartoon Heart");
	    insertCartoonLion = new MenuItem(insertMenu, SWT.PUSH);
	    insertCartoonLion.setText("Cartoon Lion");
	    
	    // Rectangle menu options.
	    insertRectItem100_100 = new MenuItem(insertMenu, SWT.PUSH);
	    insertRectItem100_100.setText("Rectangle (100x100)");
	    insertRectItem100_200 = new MenuItem(insertMenu, SWT.PUSH);
	    insertRectItem100_200.setText("Rectangle (100x200)");
	    insertRectItem200_400 = new MenuItem(insertMenu, SWT.PUSH);
	    insertRectItem200_400.setText("Rectangle (200x400)");

	    // Help menu options.
	    helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    helpMenuHeader.setText("Help");
	    helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);
	    helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("Get Help");
	    
	    // Fonts styles menu options.
	    fontsMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fontsMenuHeader.setText("Fonts");
		fontsMenu = new Menu(shell, SWT.DROP_DOWN);
		fontsMenuHeader.setMenu(fontsMenu);
	    courier = new MenuItem(fontsMenu, SWT.PUSH);
	    courier.setText("Courier");
	    courierNew = new MenuItem(fontsMenu, SWT.PUSH);
	    courierNew.setText("Courier New");
	    andale = new MenuItem(fontsMenu, SWT.PUSH);
	    andale.setText("Andale Mono");
	    
	    // Font sizes menu options.
	    fontSizeMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    fontSizeMenuHeader.setText("Font Size");
		fontSizeMenu = new Menu(shell, SWT.DROP_DOWN);
		fontSizeMenuHeader.setMenu(fontSizeMenu);
		size12 = new MenuItem(fontSizeMenu, SWT.PUSH);
		size12.setText("12");
	    size18 = new MenuItem(fontSizeMenu, SWT.PUSH);
	    size18.setText("18");
	    size24 = new MenuItem(fontSizeMenu, SWT.PUSH);
	    size24.setText("24");
	    
	    // Undo and redo menu options.
	    undoRedoHeader = new MenuItem(menuBar, SWT.CASCADE);
	    undoRedoHeader.setText("Undo/Redo");
	    undoRedoMenu = new Menu(shell, SWT.DROP_DOWN);
	    undoRedoHeader.setMenu(undoRedoMenu);
	    
	    fileUndoOption = new MenuItem(undoRedoMenu, SWT.PUSH);
	    fileUndoOption.setText("Undo");
	    fileRedoOption = new MenuItem(undoRedoMenu, SWT.PUSH);
	    fileRedoOption.setText("Redo");
	    
	    // listener to allow user to exit a document.
	    fileExitItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		shell.close();
	    		display.dispose();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		shell.close();
	    		display.dispose();
	    	}
	    });
	    
	    // listener to allow user to save a document.
	    fileSaveItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}	    		
	    });

		// listener to allow user to get help in the file.
	    helpGetHelpItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}	    		
	    });	
	    
	    // listener to allow user to undo a command.
	    fileUndoOption.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		System.out.println("Undo");
	    		lexiControl.undo();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}	    		
	    });	
	    
	    // listener to allow user to redo a command.
	    fileRedoOption.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		System.out.println("Redo");
	    		lexiControl.redo();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}	    		
	    });	
	    
	    // listener to allow user to insert an car image.
	    insertCartoonCar.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		Glyph image = new GlyphImage("CartoonCar.png", display);
	    		lexiControl.add(image);
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		
	    	}
	    });
	    
	    // listener to allow user to insert heart image.
	    insertCartoonHeart.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		Glyph image = new GlyphImage("CartoonHeart.png", display);
	    		lexiControl.add(image);
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		
	    	}
	    });
	    
	    // listener to allow user to insert lion image.
	    insertCartoonLion.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		Glyph image = new GlyphImage("CartoonLion.png", display);
	    		lexiControl.add(image);
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		
	    	}
	    });
	    
	    // listeners to insert rectangles
	    insertRectItem100_100.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		Glyph rect = new Shape(100, 100);
	    		lexiControl.add(rect);
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}
	    });
	    
	    insertRectItem100_200.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		Glyph rect = new Shape(100, 200);
	    		lexiControl.add(rect);
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}
	    });
	    
	    insertRectItem200_400.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		Glyph rect = new Shape(200, 400);
	    		lexiControl.add(rect);
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}
	    });
	    
	    // listener to allow user to choose the "Courier New" font.
	    courierNew.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		lexiControl.setFont("Courier New");
	    		System.out.println("Courier New");
	    		canvas.redraw();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		
	    	}
	    });
	    
	    // listener to allow user to choose the "Andale Mono" font.
	    andale.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		lexiControl.setFont("Andale Mono");
	    		canvas.redraw();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		
	    	}
	    });
	    
	    // listener to allow user to choose the "Courier" font.
	    courier.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		lexiControl.setFont("Courier");
	    		canvas.redraw();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		
	    	}
	    });
	    
	    // listener to allow user to choose size 12 font.
	    size12.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		lexiControl.setFontSize(12);
	    		canvas.redraw();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		
	    	}
	    });
	    
	    // listener to allow user to choose the size 18 font.
	    size18.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		lexiControl.setFontSize(18);
	    		canvas.redraw();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		
	    	}
	    });
	    
	    // listener to allow user to choose size 24 font.
	    size24.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		lexiControl.setFontSize(24);
	    		canvas.redraw();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		
	    	}
	    });
	    
	    // Handles our system menu.
        Menu systemMenu = Display.getDefault().getSystemMenu();
        try {
        	MenuItem[] mi = systemMenu.getItems();
            mi[0].addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event event){
                	System.out.println("About");
                }
            });
        }
        catch (Exception e) {
        	System.out.println("There was an error with the systemMenu.");
        }
        
	    shell.setMenuBar(menuBar);
	      
		//---- event loop
		shell.open();
		while(!shell.isDisposed())
			if(!display.readAndDispatch()){}
		display.dispose();
	} 
	
	/**
	 * updateUI
	 */
	public void updateUI()
	{
		System.out.println("updateUI");
		canvas.redraw();
	}
	
	/**
	 * setCurrentDoc
	 */
	public void setCurrentDoc(LilLexiDoc cd) { 
		currentDoc = cd; 
	}
	
	/**
	 * setController
	 */
	public void setController(LilLexiControl lc) { 
		lexiControl = lc; 
	}
}
