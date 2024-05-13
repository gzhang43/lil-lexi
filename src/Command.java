/**
 * Author: Grace Zhang
 * File: Command.java
 * Project: LilLexi
 * Course: CSC 335 Fall 2022
 */

/**
 * Abstract command class
 */
public abstract class Command {
	
	/**
	 * Executes the command.
	 */
	public abstract void execute();
	
	/**
	 * Unexecutes the command.
	 */
	public abstract void unexecute();
}
