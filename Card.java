/** Card.java
*   Author: Grace Cheong, hc3316
*   
*   
*   Models a typical playing card
*
*/
//Card c = new Card('h', 4);
class Card{
    
    private char suit;
    private int rank;

    // Initializes a card instance
    public Card(char suit, int rank){//cards have a character for the suit
        this.suit=suit;
        this.rank=rank;
    }

    // Accessor for suit
    public char getSuit(){
        return suit;
    }
    
    // Accessor for rank
    public int getRank(){
        return rank;
    }

    // Returns a human readable form of the card (eg. King of Diamonds)
    public String toString(){
        String desc = "";
 
        switch (rank) {
            case 1: desc = desc + "Ace";break;
            case 11: desc = desc + "Jack"; break;
            case 12: desc = desc + "Queen"; break;
            case 13: desc = desc + "King"; break;
            default: desc = desc + Integer.toString(rank);
        }
        desc = desc + " of ";
        switch (suit) {
            case 'c': desc = desc + "Clubs"; break;
            case 'd': desc = desc + "Diamonds"; break;
            case 'h': desc = desc + "Hearts"; break;
            case 's': desc = desc + "Spades"; break;
        }

        return desc;

    }
    
}