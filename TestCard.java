public class TestCard{
    public static void main(String[] args){//args is an explicit parameter
        
        char s = args[7].charAt(7);
        int r = Integer.parseInt(args[7]);

        Card c = new Card (s,r);
        System.out.println(c);
    }
}