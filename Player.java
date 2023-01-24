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
 * This class implements a player for the game Ladder And Snake with multiple attributes (such as name and position)
 * @version 1.0
 */
public class Player {
	
	// Attributes
	private String name;
	private int diceValue;
	private boolean tie;
	private int position;
	
	/**
	 * This constructs a player with no name and a default set of attributes
	 */
	public Player() {
		this.name = null;
		this.diceValue = 0;
		this.tie = true;
		this.position = 1;
	}
	
	/**
	 * This constructs a player with a name (chosen by the user or set as a number by the program) and a default set of attributes
	 * @param name
	 */
	public Player(String name) {
		super();
		this.name = name;
		this.diceValue = 0;
		this.tie = true;
		this.position = 1;
	}
	
	/**
	 * This constructd a player identical to the player passed as a parameter
	 * @param player
	 */
	public Player(Player player) {
		this.name = player.name;
		this.diceValue = player.diceValue;
		this.tie = player.tie;
		this.position = player.position;
	}
	
	/**
	 * This compares the dice value of two players and returns true if they are the same
	 * @param p2 dice value of the second player
	 * @return true when players have the same dice value
	 */
	public boolean equalDiceValue(Player p2) {
		if(this.diceValue == p2.diceValue)
				return true;
		else
			return false;
	}
	
	/**
	 * This returns the player's name and his current dice value
	 * @return Player's name and current dice value
	 */
	public String toString() {
		return this.name + " got a dice value of " + this.diceValue;
	}
	
	/**
	 * This returns the player's name
	 * @return Player's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * This takes in a string and sets it as the player's name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This returns the player's current dice value
	 * @return Player's dice value
	 */
	public int getDiceValue() {
		return diceValue;
	}
	/**
	 * This takes in an integer and sets it as the player's dice value
	 * @param diceValue
	 */
	public void setDiceValue(int diceValue) {
		this.diceValue = diceValue;
	}
	/**
	 * This returns a boolean value describing if the player is in a tie
	 * @return True if the player is in a tie
	 */
	public boolean getTie() {
		return tie;
	}
	/**
	 * This takes in a boolean value and sets it as the player's situation regarding a tie
	 * @param tie
	 */
	public void setTie(boolean tie) {
		this.tie = tie;
	}
	/**
	 * This returns the player's current position
	 * @return Player's position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * This takes in an integer and sets it as the player's position
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
}
