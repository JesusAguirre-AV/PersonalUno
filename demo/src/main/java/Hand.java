import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int numCardsRemaining() {
        return cards.size();
    }

    /**
     * This function does the following:
     * - Gets the current card using index
     * - Plays the current card
     * - Removes the current card from cards
     * @param game State of the game
     * @param index Index of desired card to play in cards
     * @throws Card.CannotPlayCardException
     * ODO: Implement this
     */
    public void playCard(Game game, int index) throws Card.CannotPlayCardException {
        //assigns the card at the given index
        Card plCard = cards.get(index);
        //plays the assigned card on the deck in game
        plCard.play(game);
        //after trying to play the card it removes it from the hand
        cards.remove(index);
    }

    /**
     * This function checks to see if your hand has any
     * matches to the given card
     * @param topCard Card currently in play
     * @return true if match is found and false otherwise
     * ODO: Implement this
     */
    public boolean noMatches(Card topCard) {
        //we have a boolean function already existing just for a situation like this
        for(int i=0; i<cards.size(); i++){
            if(cards.get(i).match(topCard)){
                return true;
            }
        }
        return false;
        //it was a little confusing that this method is called noMatches if it returns true when there IS a match
    }

    /**
     * Prints out your current hand's cards horizontally.
     * This is accomplished by calling the Card::prettyPrint
     * function on all cards in your hands. The prettyPrint function
     * transforms each card into a List<String> where each element of
     * the list represents a line of output for that card. You must then
     * Append all the first lines together seperated by a space
     * then all the second lines seperated by spaces
     * etc.
     * You must then put an index label under each card that
     * is centered between each card.
     * Then put a new line below
     * Then return the String you constructed
     * For example if your hand consisted of a red reverse and a blue skip
     * then your output would look like:
     * /-------\ /-------\
     * | R |Rev| | B | S |
     * \-------/ \-------/
     *     0         1
     *
     * @return
     * ODO: Implement this
     */
    @Override
    public String toString() {
        //Making the Strings that we will add/layer to along with the List of Lists to have all the layers of the cards
        String top="";
        String midd="";
        String bott="";
        String indStr="";
        List<List<String>> cdInd = new ArrayList<>();
        //we put the cards (currently a list of their layers) in our List-List and include a layer containing the index
        for (int i=0; i<cards.size(); i++){
            List<String> cdI=cards.get(i).prettyPrint();
            cdI.add("    "+i+"    ");
            cdInd.add(cdI);
        }
        //We then get the layers of each card and add them
        for (int i=0; i< cdInd.size(); i++){
            //specifically we look at each card and get the top/middle/bottom/index
            top=top+cdInd.get(i).get(0);//String with the top layer of each card
            //and add that specific layer to the total String for that layer
            midd=midd+cdInd.get(i).get(1);//String with the middle layer of each card
            bott=bott+cdInd.get(i).get(2);//String with the bottom layer of each card
            indStr=indStr+cdInd.get(i).get(3);//String with the index layer of each card
        }
        //lastly we just stack those layers just like we would for a cake
        StringBuilder sb = new StringBuilder();
        sb.append(top).append("\n").append(midd).append("\n").append(bott).append("\n").append(indStr);
        return sb.toString();
    }

    // Code you can use to test your implementation
    public static void main(String[] args) {
        Hand hand = new Hand(Arrays.asList(new Reverse(Card.Color.RED),
                                           new Skip(Card.Color.BLUE)));
        String handStr = hand.toString();
        System.out.println(handStr);

    }

}
