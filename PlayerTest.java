public class PlayerTest {
    public static void main (String[] args){
        Deck deck = new Deck();
        deck.shuffle();
        Player p1 = new Player();
        for (int i=0; i<7;i++){
            p1.addCard(deck.deal());
            System.out.println(p1.handToString());
        }
        
    }
    //Card c = p1.playsTurn(deck);
    //System.out.println(c.toString);

}