/** Player.java
*   Author: Grace Cheong, hc3316
*   
*   Player class as part of Crazy Eights
*   To be used with Game, Card, Deck classes
*
*/

import java.util.ArrayList;
import java.util.Scanner;

class Player{
    
    public ArrayList<Card> hand; // the player's hand
    private Scanner input;
    private String answer;

    public Player(){
        input = new Scanner(System.in);
        hand = new ArrayList<Card>();
        
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        hand.add(c);
    }
   
    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    public Card playsTurn(Deck deck){
                
        answer = input.nextLine();//ask player what they want to do   
        //if answer is longer than length 2 (that means the human typed "draw")
        //add a card to hand and do this again
        while (answer.length()>2){ 
            if (deck.canDeal()==true){
               this.addCard(deck.deal());
               situationDraw();
               answer = input.nextLine();
            }

            else {
                //System.out.println("index of top= "+deck.top); debugging purposes
                //System.out.println("There are no cards left in the deck to deal!");
                
                return null;
            }
            
        }
        //else, it will be a number so return that card
        
        return properCard();
        
    }

    
    // Accessor for the players hand
    public ArrayList<Card> getHand(){
        return hand;
    }

    // Returns a printable string representing the player's hand
    public String handToString(){
        String print = "";
        int i =1;
        for (Card c: hand){
        print+= Integer.toString(i++) + "\t" + c.toString() + "\n";
        // I make it print the card numbers in the player's hand
        }
        return print;
    }

    public void situationDraw(){
        System.out.println("You drew a card");
        System.out.println("Your cards are:" +"\n" + this.handToString());
        System.out.println("Type 'draw' to draw a card, or type the number next to the card in your hand that you wish to play");
               
    }
    
    public Card properCard(){//makes sure that human player doesn't accidentally pick a card that's not part of their hand
        while (Integer.parseInt(answer)>hand.size()){
            System.out.println("That is not an option in your hand! Try again!");
            System.out.println("Type 'draw' to draw a card, or type the number next to the card in your hand that you wish to play");
            answer = input.nextLine();
        }

        Card returnCard = hand.get(Integer.parseInt(answer)-1);
        System.out.println("**You played the " + returnCard.toString()+"**");
            //System.out.println(returnCard);
        hand.remove(Integer.parseInt(answer)-1);
        return returnCard;
    }

// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.

} // end
