/** Deck.java
*   Author: Grace Cheong/hc3316
*   
*   Models a typical deck of playing cards
*   To be used with Card class
*
*/

class Deck{

    private Card[] deck; // contains the cards to play with
    public int top; // controls the "top" of the deck to deal from
    private char[] suit;
    private int[] rank;
    private int index;

    // constructs a default Deck of 52 cards
    public Deck(){//nested for loop structure 
        
        deck = new Card[52];
        index = 0;
        top = 0; 
        suit = new char[]{'c', 'd', 'h', 's'};
        rank = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        for (int i=0; i<suit.length; i++){//iterate through c,d,s,h and then the inner loop through A to 13
            for (int j=0; j<rank.length; j++){
                Card c = new Card((char)suit[i], (int)rank[j]);
                deck[index++] = c;
            }
        }
    }

    // Deals the top card off the deck
    public Card deal(){//returns top card of the deck. increment top- return deck[0]=top then deck[1]=top
        Card topCard = deck[top]; // get the top card
        top++; // increment the top index
        return topCard; // return the top card
    }


    // returns true provided there is a card left in the deck to deal
    public boolean canDeal(){
        if (top<51){//as long as top is less than 52
            return true;
        }
        else {
            return false;
        }
    }

    // Shuffles the deck
    public void shuffle(){//exchange two cards in a deck and repeat that 100 times or a 100,000 times then reset the top to 0
        int index1;
        int index2;
        int shuffleCount = 100; // number of times to shuffle the deck
        for (int i = 0; i < shuffleCount; i++){
            index1 = (int) (Math.random() * 52);
            index2 = (int) (Math.random() * 52);
            Card temp = deck[index1];
            deck[index1] = deck[index2];
            deck[index2] = temp;
        }
        
        top = 0; //reset top to 0 after shuffling the deck

        }
        

    // Returns a string representation of the whole deck
    public String toString(){
        String res = "";
        for (Card c: deck){
        res+= c.toString() + "\n";
        }
        return res;
    }

    // you may wish to have more helper methods to simplify
    // and shorten the methods above.
}