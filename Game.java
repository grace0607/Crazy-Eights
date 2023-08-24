/** Game.java
*   Author: Grace Cheong, hc3316
*   
*   
*   Game class for playing crazy eights in commandline
*   To be used with Player, Card, Deck classes
*
*/


import java.util.Scanner;
import java.util.ArrayList;

class Game{

    private char currentSuit; // need in case an 8 is played
    private Card faceup; 
    private Scanner input;
    private Player p1;
    private ArrayList<Card> compHand;
    private Deck cards;
    private boolean isValid;
    private Card c; 
    private boolean keepPlaying;
    private boolean canPlay;

    
    // sets up the Game object for play
    public Game(){
        input = new Scanner(System.in);
        p1 = new Player();
        cards = new Deck(); // call on Deck constructor object from the Deck class
        cards.shuffle();
        compHand = new ArrayList<Card>();
        for (int i=0; i<7; i++){
            compHand.add(cards.deal());
            p1.addCard(cards.deal());
        }
        faceup = cards.deal();
        currentSuit = faceup.getSuit();
        isValid = true;
        keepPlaying = true;
        canPlay = true;

        //System.out.println(p1.hand);
        

        
    }

   

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing

    public boolean play(){
        
        System.out.println(welcome());//print the situation    
        
        boolean canPlayNow = canPlay();
        while (canPlayNow==true){//
            System.out.println("\n"+ situation());
            c = p1.playsTurn(cards);
            //System.out.println("c= "+c); to check if cards are being drawn properly
            if (c == null){
                endDeck();
                break;
            }
                                    
            while (checkCard(c)==false){//check if the card is valid or not
                 // if not try to add that card BACK to the player's hand (done in checkcard method)
                c = p1.playsTurn(cards);//and get a new card from playsturn
            }
                       
            update();//put all this stuff that I've commented out in a helper method called update
            /*faceup = c;//if the card is valid, update the faceup card and possibly the suit
            currentSuit = c.getSuit();
            handleEight();//handle case where an eight card is played
            */

            //System.out.println("\n"+"now the computer is playing");
            canPlayNow = canPlay();
            if (canPlayNow==false){
                break;
            }
            computerTurn(cards);//assume valid input
            //System.out.println("computer turn ends");

            canPlayNow = canPlay();
            //System.out.println("canPlayNow= "+canPlayNow);
            //System.out.println("index of top= " + cards.top);

            }
        
        return playAgain();
             
    }

    /* Naive computer player AI that does one of two actions:
        1) Plays the first card in their hand that is a valid play
        2) If no valid cards, draws until they can play

        You may choose to use a different approach if you wish but
        this one is fine and will earn maximum marks
     */
    private void computerTurn(Deck deck){
        
        for (int i=0; i < compHand.size(); i++){//for all the cards in the computer's hand
            boolean isValidCard = checkCompCard(compHand.get(i));// check if the computer's card is valid
            //then turn in the first card in their hand that is valid

            //System.out.println("i=" +i); debugging
            //System.out.println("the computer's card is valid= " + isValidCard); debugging
            //System.out.println("computer's hand is " + compHand); debugging
            if (isValidCard==true){
                c = compHand.get(i);
                updateComp();
                //System.out.println("c= " +c); check if the card is printing properly
                compHand.remove(i);//remove the card that was just drawn from the computer's hand
                break;
            }

            if (i==compHand.size()-1){//if the card is the last one in the hand (and none of the cards are valid)
                if (deck.canDeal()==true){// and there are still cards left in the deck to deal from
                    compHand.add(deck.deal()); //make the computer draw a card from the deck
                    System.out.println("The computer drew a card");
                }
        
            }
        }
    }
    
    private String welcome() {
        
        return "\n"+"Welcome to Crazy Eights! You'll start with 7 cards." +"\n"
        + "Your job is to match a card in your hand with the up card." + "\n"
        + "You can match it by suit or rank." +"\n"
        + "If you play an 8, you can switch the active suit." +"\n"
        + "If you run out of cards, you win!" + "\n"
        + "If you make it through the whole deck then whoever has" + "\n"
        + "the fewest cards left wins!" + "\n"
        + "Good luck!"+ "\n";
    }

    public String situation(){//the situation method prints the situation
        
        return "\n"+ "** The up card is the " + faceup.toString()+" **"+ "\n"//prints the faceup card
        + "The current suit is " + suitTranslation() +"\n"+"\n" //prints current suit
        + "Type 'draw' to draw a card, or type the number next to the card in your hand that you wish to play"
        + "\n"+ "If you wish to exit, type 'e'"
        + "\n"+"\n"+ "Your cards are:" +"\n"
        + p1.handToString();//prints all the cards in the player's hand
        
    }

    public String suitTranslation(){// creating a new method to turn currentSuit into a string
        String csuit = "";
        switch (currentSuit){
            case 'c': csuit = csuit + "Clubs"; break;
            case 'd': csuit = csuit + "Diamonds";  break;
            case 'h': csuit = csuit + "Hearts";  break;
            case 's': csuit = csuit + "Spades";  break;
        }
        return csuit;
        
        
    }
    
    private boolean canPlay(){
        if (p1.hand.size()==0 || compHand.size()==0){//1) check the size of player hand; if 0, the game is over
            canPlay = false;    
            finalResult();
            return canPlay;
            
        }           
        //System.out.println("cards.canDeal()");
        //System.out.println(cards.canDeal());
        if (cards.canDeal()==false){//2) check to see if faceup is null; if so the game is over
            canPlay = false;
            endDeck();
            //System.out.println("index of top= " + cards.top);    
            return canPlay;         
        }
        
        else {
            canPlay = true;
        }
        return canPlay;
    }

    private void update(){
        faceup = c;//if the card is valid, update the faceup card and possibly the suit
        currentSuit = c.getSuit();
        handleEight();//handle case where an eight card is played
    }
    
    private void updateComp(){//if the computer's card is valid, update the faceup card and possibly the suit
        faceup = c;
        currentSuit = c.getSuit();
        handleEightComp();
    }

    private boolean checkCard(Card c){//checking whether the human player's card is valid
        
        if (c.getSuit()==currentSuit || c.getRank()==faceup.getRank() || c.getRank()==8){
            isValid = true;
            
        }
        else {
            isValid = false;
            System.out.println("Invalid card: it doesn't match the suit or rank of the up card. Try again!");
            p1.addCard(c);
            System.out.println(situation());
            
        }
        return isValid;
    }

    
    private boolean checkCompCard(Card c){//checking whether the computer player's card is valid
        
        if (c.getSuit()==currentSuit || c.getRank()==faceup.getRank() || c.getRank()==8){
            isValid = true;
            System.out.println("** Computer played the " + c.toString() + " **");
        }
        else {
            isValid = false;
            //System.out.println("** Computer played the " + c.toString() + " **");
            //System.out.println("Invalid card: it doesn't match the suit or rank of the up card. Try again!");
            
        }
        return isValid;
    }


    
    public void handleEight(){//handle the case where the human plays an eight
        if (faceup.getRank() == 8){
            //System.out.println("** You played the "+ c.toString() + " **"); 
            System.out.println("Choose a suit to set! (c,d,h,s)");
            checkCheating();//make sure they are not typing in anything else than (c,h,d,s)
            
        }

    }

    private void checkCheating(){//makes sure human is not typing in anything else than (c,h,d,s)
        String pick = input.nextLine();
        boolean cheating = true;
        while (cheating=true){
                if ((pick.charAt(0)=='c'|| pick.charAt(0)=='d'|| pick.charAt(0)=='h'|| pick.charAt(0)=='s') 
                && pick.length()==1){//if they type c or d or h or s they are not cheating
                    cheating = false;
                    currentSuit = pick.charAt(0);
                    break;
                }
               
                else{
                    cheating = true;
                    System.out.println("You cannot set that suit! Try again using a single character.");
                    System.out.println("Choose a suit to set! (c,d,h,s)");
                    pick = input.nextLine();
                }

            }
    }

    private void handleEightComp(){//handle the case for when the computer plays an 8
        if (faceup.getRank() == 8){
            //System.out.println("** The computer played the "+ c.toString());
            System.out.println("**** Computer changed the suit ****");
            currentSuit = compHand.get(0).getSuit();
            System.out.println("The current suit is " + suitTranslation());
                  
        }
        
    }

    private void finalResult(){//method that prints the final result when someone reaches 0 cards first
        if (p1.hand.size()==0){
            System.out.println("You have 0 cards left" +"\n"
            + "Computer has " + Integer.toString(compHand.size()) + " cards left" + "\n"
            + "You win!");
        }
        if (compHand.size()==0){
            System.out.println("You have " + Integer.toString(p1.hand.size()) + " cards left" +"\n"
            +"Computer has 0 cards left"+"\n"
            +"You lose!");
        }
    }
    private void endDeck(){//method that prints the final result when there are no more cards in the deck left
        
            if (p1.hand.size() > compHand.size()){
                System.out.println("We've reached the end of the deck!");
                System.out.println("You have " + Integer.toString(p1.hand.size()) + " cards left");
                System.out.println("Computer has " + Integer.toString(compHand.size()) + " cards left");
                System.out.println("You lose!!");
            }
            if (p1.hand.size() <compHand.size()){
                System.out.println("We've reached the end of the deck!");
                System.out.println("You have " + Integer.toString(p1.hand.size()) + " cards left");
                System.out.println("Computer has " + Integer.toString(compHand.size()) + " cards left");
                System.out.println("You win!!");
            }
            if (p1.hand.size() == compHand.size()){
                System.out.println("We've reached the end of the deck!");
                System.out.println("You have " + Integer.toString(p1.hand.size()) + " cards left");
                System.out.println("Computer has " + Integer.toString(compHand.size()) + " cards left");
                System.out.println("Draw!");
            }
            //else{
            //    return null;
            //}    
        

            
    }

    public boolean playAgain(){
        System.out.println("Do you wanna play again? (y/n)");
        char yesNo = input.next().charAt(0);
        //while(true){//(yesNo =='y' || yesNo == 'n'){//or true
            if (yesNo=='y'){
                keepPlaying = true;
                
            }
            else {
                keepPlaying = false;
                
            }
            /*else{
                System.out.println("type y to keep playing or n to stop");
                yesNo = input.next().charAt(0);
            }*/
        
        return keepPlaying;
    }

    
// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.


}