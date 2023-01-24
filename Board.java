// ---------------------------------------------------------------
// Assignment 1
// Part 2
// Written by: Racha Kara 40210865
// ---------------------------------------------------------------

/**
 * Name and ID		Racha Kara 40210865
 * COMP249
 * Assignment #		1
 * Due Date			February 9th, 2022
 *
 * This class implements a simple board that can be printed and used to determined the ladders and snakes' locations
 * @version 1.0
 */
public class Board {
	
	// Atributes
	// defines the start and end location of each ladder (will be used in the play() method)
	private int[][] laddersLocation = new int[][] {{1,38},{4,14},{9,31},{21,42},{28,84},{36,44},{51,67},{71,91},{80,100}}; 
	// defines the start and end location of each snake (will be used in the play() method)
	private int[][] snakesLocation = new int[][] {{16,6},{48,30},{62,19},{64,60},{93,68},{95,24},{97,76},{98,78}}; 
	private int square;
	
	/**
	 * This constructs a board for the Ladder And Snake game using no parameters
	 */
	public Board() {
	}
	
	/**
	 * This constructs a board for the Ladder And Snake game by copying another board
	 * @param board
	 */
	public Board(Board board) {
	}

	/**
	 * This prints a square with a certain emoji or not depending on its type.
	 * Squares containing a ladder or a snake have a ladder or a snake emoji printed instead of the number.
	 */
	public void printSquare() {
		String toPrint;
		// switch statement that defines whether a number or an emoji gets printed in the square
		switch (square) {
		case 1: case 4: case 9: case 21: case 28: case 36: case 51: case 71: case 80: {
			System.out.print(" [ 🪜 ] ");
			break;
		}
		case 16: case 48: case 62: case 64: case 93: case 95: case 97: case 98: {
			System.out.print(" [ 🐍 ] ");
			break;
		}
		// Align the squares that have a number that is less than 10 by adding a space (for the first row of the board to not be shorter)
		default: {
			if (square<10)
				toPrint = " [ " + Integer.toString(square) + "  ] ";
			else
				toPrint = " [ " + Integer.toString(square) + " ] ";
			System.out.print(toPrint);
			break;
		}
		}
	}
	
	/**
	 * This prints the board as a visual 2D array for the user to see the location of the ladders and the snakes
	 * @version 1.0
	 */
	public void printBoard() {
		int tensCount = 0;
		// Display a statement that the following figure represents the game board
		System.out.println("\nHere is the board\n");
		for (int row=0; row<10; row++) {
			// Multiply the row value by 10 to not repeat 1-10 each time, store it in a different variable to keep row as the count of rows
			tensCount = row*10;
			// if the row is even, print numbers in an ascending order (left to right), and if row is odd, print in a descending order 
			// (right to left) in order to create the zig-zag effect of the board
			if (row%2==0) {
				for (int col=0; col<10; col++) {
					// Add 1 to the value of each square in order to start at 1 and end at 100 (instead of 0-99)
					square = tensCount+col+1;
					printSquare();
				}
			}
			else {
					for (int col=9; col>=0; col--) {
					// Add 1 to the value of each square in order to start at 1 and end at 100 (instead of 0-99)
					square = tensCount+col+1;
					printSquare();
				}
			}
			System.out.println("\n"); // Print an empty line between each row to not get a cramped board
		}
		// Display the emoji code for the user to understand
		System.out.println("🪜 squares contain ladders.");
		System.out.println("🐍 squares contain snakes.");
	}
	
	/**
	 * This fetches the laddersLocations attribute
	 * @return a 2D array containing the start and end point of each ladder
	 */
	public int[][] getLaddersLocation() {
		return laddersLocation;
	}
	/**
	 * This takes in a 2D array and sets it as the laddersLocation attribute
	 * @param laddersLocation
	 */
	public void setLaddersLocation(int[][] laddersLocation) {
		this.laddersLocation = laddersLocation;
	}
	/**
	 * This takes in a 2D array and sets it as the snakesLocation attribute
	 * @return snakesLocation
	 */
	public int[][] getSnakesLocation() {
		return snakesLocation;
	}
	/**
	 * This takes in a 2D array and sets it as the snakesLocation attribute
	 * @param snakesLocation
	 */
	public void setSnakesLocation(int[][] snakesLocation) {
		this.snakesLocation = snakesLocation;
	}
}

