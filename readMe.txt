1. Card class
My Card class represents a playing card and has two private instance variables, suit and rank, which are initialized in the constructor method.

My getSuit() and getRank() methods are accessor methods that return the suit and rank variables respectively.

My toString() method returns a human-readable string representation of the Card instance. Using the switch statement I convert the numerical rank of the card (1-13) to a string representation (Ace, Jack, Queen, King or a number), and the single-letter suit code ('c', 'd', 'h', 's') to a string representation (Clubs, Diamonds, Hearts, Spades). So now the card ('h', 11) might read Jack of Hearts.

2. Deck class
My Deck class represents the standard 52-card deck of playing cards. 
I have five instance variables that are initialized in the constructor.
The variable deck is an array of 52 Card objects representing the cards in the deck. The variable top is an integer representing the index of the top card in the deck. The variable suit is an array of four char values representing the four suits in a deck of cards (clubs, diamonds, hearts, spades). The variable rank is an array of 13 int values representing the ranks of cards in a deck (Ace, 2-10, Jack, Queen, King). The variable index is an integer used to keep track of the current index in the deck array when shuffling the deck.

In my constructor Deck(), I create a new deck of cards by iterating through each suit and rank using a for loop, creating a new Card object for each combination of suit and rank and adding it to the deck array.

In my deal() method, I deal a card of the deck (the top card) and increment the index of top by 1 so that I can keep track of how many cards are left in the deck later on.

My canDeal() method returns true if there are any cards left in the deck to deal. I do this by checking if the variable top is less than 51.

My shuffle() method shuffles the deck by randomly swapping pairs of cards in the deck array 100 times (using the Math.random() function). After shuffling, the top index is reset to 0.

My toString() method returns a string representation of the entire deck by iterating through each card in the deck array by calling on the toString() method from the Card class.


3. Player class
This is where things start getting complex. My Player class represents a player in a card game and has the instance variables hand, input, and answer initialized in the construtor. hand is an ArrayList of Card objects representing the player's hand. input is a Scanner object used to read input that the human player types. answer is a variable used to store the player's response.

My addCard(Card c) method adds a Card object to the player's hand using the add() method for ArrayLists.

The playsTurn(Deck deck) method covers all the logic for a human player's turn. I call on multiple helper methods here. The method takes a Deck object as an argument and returns the Card object that the player chooses to play. It first asks the player what they want to do by prompting for input using the Scanner object. If the input is longer than 2 characters, it assumes the player wants to draw a card, so it checks if the deck can deal another card and if so, adds the new card to the player's hand and calls the situationDraw() method to prompt the player again. If the deck cannot deal another card, it returns null to indicate that the player's turn is over. If the input is not longer than 2 characters, it assumes the player wants to play a card, so it calls the properCard() method to validate the input and return the appropriate Card object.

The getHand() method returns the hand field of the player.

handToString() method returns a String representation of the player's hand, with each card's number in the hand and its String representation printed on a separate line.

situationDraw() prints out information for the player when they draw a card, including the player's updated hand and a prompt to either draw another card or play a card.

properCard() validates the player's input when they choose to play a card. It first checks if the input is a valid number corresponding to a card in the player's hand. If it is not, it prompts the player to try again. If it is valid, it returns the appropriate Card object, removes it from the player's hand, and prints out a message indicating that the player has played that card.


4. Game class
This is where everything happens. I use A LOT of helper methods in this class. The Game class creates a new game by initializing all the necessary attributes for playing Crazy Eights such as the player's hand, the computer's hand, the faceup card, the current suit, and other booleans that will be used to control the flow of the game.

The constructor of the Game class initializes all these instance variables. It sets up the deck of cards, shuffles them, deals seven cards to each player (the player and the computer), and sets the face-up card. It also sets the current suit to be the suit of the face-up card, and initializes other boolean values.

play() method:
This is the main method that runs the game loop. It starts by printing a welcome message and calling the canPlay() method to check if either player can play a card. If at least one player can play, the game continues. Otherwise, the game ends.

The method then enters a loop that alternates between the player's turn and the computer's turn until one of them runs out of cards or the deck is empty. In each iteration, it first prints the current situation, which includes the face-up card, the current suit, and some instructions for the player. It then calls the playsTurn() method of the player object to get the card they want to play. If the player chooses to draw a card, the method calls endDeck() to end the game if the deck is empty, and breaks out of the loop.

If the player chooses to play a card, the method calls checkCard() to verify that the card is a valid play. If the card is invalid, the method enters a loop that prompts the player to play a different card until they do. If the card is valid, the method updates the face-up card and the current suit (if necessary) through the update() method and calls the computerTurn() method.

The computerTurn() method is a helper method that plays the computer's turn. It loops through the computer's hand, looking for the first valid card it can play by calling on the checkCompCard() method. If it finds one, it plays it and updates the faceup card and the current suit by calling on the updateComp() method. If it doesn't find a valid card, it draws a card from the deck if the deck is not empty.

After the computer's turn, the canPlay() method is called again to check if either player can play. It checks whether either the player or the computer ran out of cards or whether the faceup card is a null (no more cards left in the deck to draw). If at least one player can play, the loop continues. Otherwise, the game ends.

Finally, the playAgain() method is called to ask the player if they want to play again. If they do, the method returns true to start a new game. If they don't, the method returns false to end the program.

My welcome() method returns a String that contains the welcome message that is printed at the beginning of the game. 

My situation() method returns a String that contains the current situation of the game, including the face-up card, the current suit, and some instructions for the player including how to draw a card or how to exit the game.

The canPlay() method checks if either the human player or the computer can play a card. It returns true if either player has a valid card to play and false otherwise.

My checkCard() method checks if a given card is a valid play. It takes a Card object as an argument and returns true if the card can be played and false otherwise. The method checks if the card matches the current suit or rank of the face-up card, or if the card is an 8. If the card is an 8, it is always a valid play.

Here are some brief explanations of what my other methods are doing. Please refer to the code annotations for the line by line explanations.

checkCompCard(Card c): This method works similarly to checkCard but is used to check the validity of the computer player's card.

handleEight(): This method is called when the human player plays an eight. It displays a message asking the player to choose a suit to set and calls checkCheating to ensure that the player inputs a valid suit.

checkCheating(): This method is called by the handleEight() method to ensure that the human player inputs a valid suit. It takes the player's input and checks if it is a single character that represents one of the four suits (c, d, h, or s). If the input is valid, it sets the current suit to the chosen suit, and if it is invalid, it prompts the player to try again. I designed this method to prevent the human player from cheating i.e. choosing a random letter like 'f' to set a non-existant suit that makes the computer player draw till it finds an 8.

handleEightComp(): This method is called when the computer player plays an eight. It chooses the suit of the first card in its hand and sets the current suit to that suit.

finalResult(): This method is called when one player has zero cards left in their hand. It displays a message pointing to the winner.

endDeck(): This method is called when there are no more cards left in the deck to draw. It prints a message indicating the winner or if it was a draw.

playAgain(): This method is called at the end of the game and asks the player if they want to play again. It takes input from the player asking and reads the first character that is given to check if it's a 'y'. If so, it returns a boolean value indicating that the player wants to play again. If not, the player is able to exit the game.





External Sources:

Switch Statement
https://www.w3schools.com/java/java_switch.asp
Integer to String
https://www.edureka.co/blog/convert-int-to-string-in-java/
Char to String
https://www.javatpoint.com/java-char-to-string
nextChar method
https://www.geeksforgeeks.org/gfact-51-java-scanner-nextchar/
if and else if 
https://www.w3schools.com/java/java_conditions.asp
