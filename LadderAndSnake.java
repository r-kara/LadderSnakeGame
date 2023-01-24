// ---------------------------------------------------------------
// Assignment 1
// Part I
// Written by: Racha Kara 40210865
// ---------------------------------------------------------------

/**
 * Name and ID		Racha Kara 40210865
 * COMP249
 * Assignment #		1
 * Due Date			February 9th, 2022
 * 
 * This calls implements a Ladder And Snake game by creating Player objects, determining the playing order by dice flipping, and looping through them until there is a winner
 */
public class LadderAndSnake {
	
	// Attributes
	private int numOfPlayers;
	private Player[] players;
	private Board board; //10x10
	boolean stillInTie = true;
	boolean isAllTie = true; // true only when all players get the same dice value at once
	
	/**
	 * This constructs a Ladder And Snake game with the minimum number of players
	 * It also constructs the needed number of players and a board.
	 */
	public LadderAndSnake() {
		this.numOfPlayers = 2; // min number of players
		players = new Player[numOfPlayers];
		board = new Board();
	}
	
	/**
	 * This constructs a Ladder And Snake game by copying an already-existing game of the same type.
	 * It also constructs the needed number of players and a board.
	 * @param ladderAndSnake existing Ladder And Snake game
	 */
	public LadderAndSnake(LadderAndSnake ladderAndSnake) {
		this.numOfPlayers = ladderAndSnake.numOfPlayers;
		players = new Player[numOfPlayers];
		board = new Board();
	}
	
	/**
	 * This constructs a Ladder And Snake game with the specific number of players inputed.
	 * It also constructs the needed number of players and a board.
	 * @param numOfPlayers
	 */
	public LadderAndSnake(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
		players = new Player[numOfPlayers];
		board = new Board();
	}
	
	/**
	 * This  return the number of players for the current game
	 * @return number of players
	 */
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	/**
	 * This takes in an integer and sets it as the number of players
	 * @param numOfPlayers
	 */
	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}
	/**
	 * This return the board of the current game
	 * @return Ladder And Snake board
	 */
	public Board getBoard() {
		return board;
	}
	/**
	 * This takes in an object of type Board and sets it as the game's board
	 * @param board 10x10 Ladder And Snake board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	/**
	 * This returns the array of players for the current games
	 * @return 1D array of players
	 */
	public Player[] getPlayers() {
		return players;
	}
	/**
	 * This takes in a 1D array of type Player[] and sets it as the game's players
	 * @param players 1D arrays of players
	 */
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	// toString() method: to print the number of players for the current game
	/**
	 * This returns a string that states the number of players
	 * @return String of the number of players for the game
	 */
	public String toString() {
		return "This game will be played by " + numOfPlayers + " players.";
	}
	
	/**
	 * This takes in an integer and a name and initializes a Player object from the array of players at index i (integer taken in)
	 * @param i index of the new Player in the array
	 * @param name name of the new Player
	 */
	public void createPlayer(int i, String name) {
		players[i] = new Player(name);
	}
	
	/**
	 * This returns a random integer between 1 and 6 inclusively
	 * @return random integer between 1 and 6
	 */
	public int flipDice() {
		int randomInt = (int)(Math.random() * 6) + 1; // adding 1 to avoid getting 0
		return randomInt;
	}
	
	/**
	 * This loops through the players that have their boolean Tie attribute as true and sorts them in a descending order according to their dice value
	 * @param playersList 1D array of type Player[]
	 */
	public void sortPlayers(Player[] playersList) {
		Player temp = new Player(); // temporary player created to help with the sorting
		for (int i=0; i<playersList.length-1; i++) {
			// Only check players whose tie value is true (in order to avoid mixing in players who have a unique dice value)
			if (playersList[i].getTie()==true) {
				for (int j=i+1; j<playersList.length; j++) {
					// Only check players whose tie value is true (in order to avoid mixing in players who have a unique dice value)
					if (playersList[j].getTie()==true) {
						if (playersList[i].getDiceValue() < playersList[j].getDiceValue()) {
							// Physically change their place in the array
							temp = playersList[i];
							playersList[i] = playersList[j];
							playersList[j] = temp;
						}
					}
				}
			}
		}
	}
	
	/**
	 * This loops through the players that have their boolean Tie attribute as true and changes it to false if they have a unique dice value
	 * @param playersList 1D array of type Player[]
	 * @return number of players in a tie left
	 */
	public int checkTie(Player[] playersList) {
		int playersLeft = 0; // counts the number of players with a tie
		for (int i=0; i<playersList.length; i++) {
			if (playersList[i].getTie()==true) {
				for (int j=0; j<playersList.length; j++) {
					// Compares 2 players only if they both got a tie, have equal value of dice and a player is not being compared to itself
					if ((playersList[j].getTie()==true) && (playersList[i].equalDiceValue(playersList[j])) && (i!=j)) {
						playersLeft++;
						playersList[i].setTie(true);
						break;
					}
					else {
						playersList[i].setTie(false);
					}
				}
			}
		}
		return playersLeft;
	}
	
	/**
	 * This determines the playing order by making each player throw the dice to get the highest number, sorting them, and checking for a tie.
	 * In case of a tie between players, they throw again until order is determined.
	 * @version 7.0
	 */
	public void playingOrder() {
				
		do {			
			// Each player flips the dice once
			for (int i=0; i<players.length; i++) {
				if (players[i].getTie()==true) { // check point for second loop to make sure that players with an already-unique dice value do not play again
					players[i].setDiceValue(flipDice());
					System.out.println(players[i].toString()); // Display to the user what each player got
					// If at least 2 players do not have the same dice value, "isAllTie" case is false
					if ((players[0].getDiceValue()) != (players[i].getDiceValue())) {
						isAllTie = false;
					}
				}
			}
			// Sort the players' order in descending order according to their dice value (in a descending order)
			sortPlayers(players);
			// Loop through the players and flag players that are in a tie
			int numInTie = checkTie(players);
			// If there are no more players in a tie, break out of the loop since order has been determined
			if (numInTie==0) {
				stillInTie = false;
				break;
			}
			// If all players get the same dice value at once, print the following message
			else if (isAllTie)
				System.out.println("\nAll the players got the same dice value. Everyone attemps again.\n");
			// Else, there must be a tie somewhere so the loop will play again
			else {
				// Display to the user which players are in a tie
				String toPrint = "\nA tie was achieved between";
				// Add the name of the players in a tie to the string to print
				for (int i=0; i<players.length; i++) {
					if (players[i].getTie()==true) {
						toPrint += " " + players[i].getName() + " and";
					}
				}
				// Take out the last " and" from the string (done this way since we do not know the length of the string)
				for (int i=toPrint.length()-1; i>0; i--) {
					if (toPrint.charAt(i)==' ') {
						toPrint = toPrint.substring(0,i);
						break;
					}
				}
				// Append the last part and print the message for the user to see
				toPrint += ". Attempting to break the tie.\n";
				System.out.println(toPrint);
			}
			
		}
		while(stillInTie==true); // Loop keeps running as long as there is a tie between the players
		
		// Display the playing order to the user
		System.out.println("\nReached final decision on order of playing: \n");
		for (int i=0; i<players.length; i++) {
			System.out.println("  " + (i+1) + ". " + players[i].getName());
		}
		System.out.println();
		
	}
	
	/**
	 * This initiates the games by calling the playingOrder() method, then entering a while loop of dice flipping unitl a player wins
	 * @version 3.0
	 */
	public void play() {
		
		// Display the number of players for the game
		System.out.println(toString());
		
		// Determine the order of playing turn
		System.out.println("\nNow deciding the order of playing turn.\n");
		playingOrder();
		
		// Game starts: Alternating dice flipping until there is a winner
		// One loop means that all players play once
		boolean endGame = false;
		while(endGame==false) {
			// Each dice flip moves the player from its location to *dice value* further
			for (int i=0; i<players.length; i++) {
				players[i].setDiceValue(flipDice());
				int newPosition = players[i].getPosition() + players[i].getDiceValue();
				players[i].setPosition(newPosition); // Sets the new position of the player
				
				// Check each players' new location and print a statement accordingly
				// 3 possible outcomes: Player falls on a ladder square, a snake square, a normal square, or wins the game
				switch (players[i].getPosition()) {
				
				// Case 1: Player has reached a square with ladder, player moves to the top of the ladder
				case 1: case 4: case 9: case 21: case 28: case 36: case 51: case 71: case 80: {
					// iterate through the array of ladders positions to match with the player's position
					for (int p=0; p<board.getLaddersLocation().length; p++) {
						if(players[i].getPosition() == board.getLaddersLocation()[p][0]) {
							// Print a statement of the player's movement from current to new position
							System.out.println(players[i].toString() + "; gone to square " + players[i].getPosition() + " then up to square " 
													+ board.getLaddersLocation()[p][1]);
							// set the player's new position
							players[i].setPosition(board.getLaddersLocation()[p][1]);
						}
					}
					break;
				}
				
				// Case 2: Player has reached a square with snake, player moves to the bottom of the snake
				case 16: case 48: case 62: case 64: case 93: case 95: case 97: case 98: {
					// iterate through the array of snakes positions to match with the player's position
					for (int p=0; p<board.getSnakesLocation().length; p++) {
						if(players[i].getPosition() == board.getSnakesLocation()[p][0]) {
							// Print a statement of the player's movement from current to new position
							System.out.println(players[i].toString() + "; gone to square " + players[i].getPosition() + " then down to square " 
													+ board.getSnakesLocation()[p][1]);
							// set the player's new position
							players[i].setPosition(board.getSnakesLocation()[p][1]);
						}
					}
					break;
				}
				
				// Case 3: Player falls on a normal square
				default: {
					// Sub-case 3.1: Player's position after throwing the dice is more than 100
					if (players[i].getPosition() > 100) {
						// Calculate the number of squares the players goes backwards from 100
						int backwardSteps = players[i].getPosition() - 100;
						// Print a statement of the player's movement from current to new position
						System.out.println(players[i].toString() + "; gone to square 100" + " then moves back to " + (100-backwardSteps) + " squares");
						// set the player's new position
						players[i].setPosition(100 - backwardSteps);
						
						// Special case: player could go back on a square with a snake !
						// iterate through the array of snakes positions to match with the player's position
						for (int p=0; p<board.getSnakesLocation().length; p++) {
							if(players[i].getPosition() == board.getSnakesLocation()[p][0]) {
								// Print a statement of the player's movement from current to new position
								System.out.println("then down to square " + board.getSnakesLocation()[p][1]);
								// set the player's new position
								players[i].setPosition(board.getSnakesLocation()[p][1]);
							}
						}
					}
					
					// Sub-case 3.2: Player falls on a normal square and nothing happens
					else
						System.out.println(players[i].toString() + "; now in square " + players[i].getPosition());
					break;
				}
				
				} // end of switch statement (1 player moved)
				
				// If a player reaches square 100 exactly, he wins and the game ends
				if (players[i].getPosition() == 100) {
					System.out.println("\n\n" + players[i].getName().toUpperCase() + " JUST WON THE GAME! \nCONGRATULATIONS!");
					System.out.println("\n\nThe game has ended. Program will terminate."
							+ "\nSee you next time!");
					endGame = true;
					System.exit(0);
				}
				
			}  // end of for-loop (all the players threw the dice once and moved)
			
			System.out.println("\nGame not over; flipping again\n");
			
		} // end of while loop of dice flipping
	} // end of play() method
	
}
