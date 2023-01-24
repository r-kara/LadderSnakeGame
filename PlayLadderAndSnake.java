// ---------------------------------------------------------------
// Assignment 1
// Part 2
// Written by: Racha Kara 40210865
// ---------------------------------------------------------------

/*

This program was created as part of an assignment for COMP-249 due Wednesday, February 9th, 2022.

Overall, this program simulates a Ladder And Snake game that runs on its own until a player wins.

The game is implemented using 4 classes: Board (to create a board object), Player (to create each player as an object), LadderAndSnake (containing the play() method) and 
PlayLadderAndSnake (containing the main method).  

The Board class two 2D arrays of the ladders and snakes {start,end} locations (laddersLocation, snakesLocation), as well 
as a printSqure() method that prints a square of the board with a certain emoji or not depending on its type, and a printBoard() method that prints the board as a visual 
2D array for the user to see the location of the ladders and the snakes. The Player class only contains a toString() method that returns the name of a player and the current 
dice value he is holding. As for the LadderAndSnake class, it is comprised of multiple small methods that are used to run the game and that are explained below (createPlayers(), 
flipDice(), sortPlayers(), checkTie(), playingOrder() and play()). Finally, the PlayLadderAndSnake class contains the main method and takes in the user's input. Naturally, the 
first three classes also have their respective constructors, mutators and accessors.

First, the program welcomes the user and displays a visual board of the game that shows the location of the ladders and the snakes. The user is then asked to enter 
a number of players, and whether or not he would like to give the players names. If it is the case, the program prompts the user to enter the name of each player. In 
the other situation, the program addresses the players as "Player #". The users gets 4 attempts to both of these choices, and failing after this number results in the 
program being terminated.

Assuming that the user inputs correct answers, the program runs the play() method, which does not stop until there is a winner. It starts by deciding the playing order through 
the playingOrder() method, which is mainly composed of a do-while loop and other smaller methods that provide a dice number to each player (flipDice()), sort the players 
(sortPlayers()), and check whether there is a tie between two or more players (checkTie()). In the case of a tie, a statement is displayed to the user and the do-while loop 
runs again. This loop breaks when there is no longer a tie among the player. The playingOrder() method finishes by displaying the fixed order of playing. 

Then, still within the play() method, a while loop of dice flipping starts. For each loop (or round), each player flips the dice (flipDice()), and is faced with 3 possible 
outcomes (which are organized as a switch statement). First, the player's reaches a ladder square and moves to the top of it by using a 2D array from the Board class which 
contains the location of the start and end of each ladder (laddersLocation). The second situation is similar since it treats the case where the player reaches a snake square. 
The player is then moved to the bottom of the snake by the same mean as when he reaches a ladder (using the 2D array snakesLocation). Finally, the last case happens when a 
player falls on a normal square and nothing occurs during this turn, or when a player exceeds a 100 and has to go backwards by the excess number. In the latter scenario, it 
is verified again if the player falls on a snake square since there are multiple of them close to the 100th square.

After the switch statement (which represents 1 player moving), the program checks if this player is on square 100 (which can happen by reaching it normally or by reaching 
square 80 that contains a ladder to square 100). If it is the case, the program prints a closing message by stating the winner and announcing to the user that the game has 
ended before terminating.

 */

import java.util.Scanner;

/**
 * Name and ID		Racha Kara 40210865
 * COMP249
 * Assignment #		1
 * Due Date			February 9th, 2022
 * 
 * This class executes the program by displaying the board, prompting the user for input and running the play() method which initiates the game.
 */
public class PlayLadderAndSnake {
	/**
	 * This is the main method where the program starts its execution by printing the board and prompting inputs from the user.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Declare a scanner for the user's input
		Scanner userInput = new Scanner(System.in);
		
		// Declare variables that will determine whether the game starts or not
		String userChoice;
		int numOfPlayers;
		int numOfAttempts = 0;
		boolean isValid = false;
		
		// Display welcome message that includes my name
		System.out.println("\t     ++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println("\t         Welcome to Racha Kara's Ladder And Snake Game !!   ");
		System.out.println();
		System.out.println("\t     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\t     ++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		// Create a board and display for the user to see the location of the ladders and the snakes
		Board board = new Board();
		board.printBoard();
		
		// Warn the user about the limited number of attempts
		System.out.println("\n\nBe careful, you only have 4 attempts per choice! :)");
		
		// Prompt the user to enter the number of players
		// If invalid, prompt again (up to 4 times before terminating)
		System.out.println("\nEnter the # of players for your game – Number must be between 2 and 4 inclusively:");
		do {
			System.out.print("\t");
			numOfPlayers = userInput.nextInt();
			// Invalid input = less than 2 or over 4
			if((numOfPlayers < 2) || (numOfPlayers > 4)) {
				numOfAttempts += 1;
				if(numOfAttempts==4) {
					System.out.println("Bad Attempt 4! You have exhausted all your chances. Program will terminate! ");
					System.out.println("\n\nSee you next time!");
					System.exit(0);
				}
				System.out.println("Bad Attempt " + numOfAttempts + " - Invalid # of players. Please enter a # between 2 and 4 inclusively:");
				if (numOfAttempts == 3)
					System.out.print("This is your last attempt:\n");
			}
			else
				isValid = true;
		}
		while (isValid==false);
		
		// Reset isValid and the numOfAttempts for the next user input, and set the number of players
		isValid = false;
		numOfAttempts = 0;
		LadderAndSnake game = new LadderAndSnake(numOfPlayers);
		
		// Ask the user if the players should have names
		System.out.println("\nWould you like to give the players names? (Y or N)");
		do {
			System.out.print("\t");
			userChoice = userInput.next();
			System.out.println();
			
			// If the user enters an incorrect answer, ask again
			if (!(userChoice.equals("Y")) && !(userChoice.equals("N"))) {
				numOfAttempts++;
				if(numOfAttempts==4) { // All 4 attempts have been used
					System.out.println("Bad Attempt 4! You have exhausted all your chances. Program will terminate! ");
					System.out.println("\n\nSee you next time!");
					System.exit(0);
				}
				System.out.println("Your input is incorrect, try again. (Y or N)");
				if (numOfAttempts == 3) // Warn them that this is the last attempt
					System.out.print("This is your last attempt:\n");
			}
			
			// If user wants to give the players names; create an array of players and set the names to his choice
			else if (userChoice.equals("Y")) {
				for (int i=0; i<numOfPlayers; i++) {
					System.out.print("Enter the name of player " + (i+1) + ": ");
					game.createPlayer(i, userInput.next());
				}
				isValid = true;
			}
			
			// If user doesn't want to give the players names; create an array of players and set the names to numbers
			else {
				System.out.println("Then players will be addressed as Player 1 to " + numOfPlayers + "!\n");
				for (int i=0; i<numOfPlayers; i++) {
					game.createPlayer(i, "Player " + Integer.toString(i+1));
				}
				isValid = true;
			}
		}
		while(isValid==false);
		
		// User entered a valid number of players, game can now start
		game.play();
	}
}
