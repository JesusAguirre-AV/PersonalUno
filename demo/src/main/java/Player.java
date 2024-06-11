import java.util.ArrayList;

public class Player {
    private final String name;
    private final Game game;
    private final Hand hand;

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
        this.hand = new Hand(new ArrayList<>());
    }

    /**
     * This function does the following:
     * - Attempts to draw num number of cards
     * - If a EmptyDeckException is caught then the play area
     *   must be shuffled into the deck. Note this a function of game class
     * - Adds each drawn card to hand
     * @param num Number of cards to be drawn
     * ODO: Implement this
     */
    public void drawCards(int num) {
        //making the deck visible to the player
        Deck deck = game.getDeck();
        //for every num the player should draw, it will try to do so but if the deck is empty it will shuffle the play
        //area into the Deck and draw from the resulting deck
        for(int i=0; i<num; i++) {
            try {
                Card cdDraw = deck.drawCard();
                hand.addCard(cdDraw);
            } catch (Deck.EmptyDeckException e) {
                //So the player doesn't miss out on drawing a card because the deck is empty
                i--;
                game.shufflePlayAreaIntoDeck();
            }

        }

    }

    /**
     * Performs IO to figure out what moves the user
     * wants to make. It does this as follows:
     * - Loops until the user has successfully played a card
     * - Prints out "Play area:\n"
     * - Prints out the top card
     * - Checks to see if the hand has any matches against the top card
     *   - If it does not then print: "Your hand had no matches, a card was drawn."
     *   - Then draw 1 card
     * - Then prints "Hand:\n"
     * - Then prints out the hand
     * - If the hand still has no matches then print: "Your hand still has no matches your turn is being passed"
     *   and ends the turn
     * - Otherwise it asks the user: "Which card would you like to play?" using the game::interact function
     * - The code loops until the user successfully answers this question, the three criteria are:
     *   - A valid int, if not print:
     *     "$cardNumStr is not a valid integer, please try again."
     *     where cardNumStr is the user input
     *   - A valid match, if not print:
     *     "Card $cardNumStr cannot currently be played, please try again."
     *     where cardNumStr is the user input
     *   - A valid index, if not print:
     *     "$cardNumStr is not a valid index, please try again."
     * ODO: Implement this
     */
    public void takeTurn() {
        //Print the play area
        System.out.print("Play area:\n"+game.getTopCard().toString());
        //Looks if the player has anything usable or if the player must draw a card

        if(!hand.noMatches(game.getTopCard())){
            System.out.println("Your hand had no matches, a card was drawn.");
            drawCards(1);
        }
        int a = 1;
        a=a+game.getPlayers().getCurIndex();
        System.out.print(game.getNames().get(game.getPlayers().getCurIndex())+"\n"+"Hand:"+"\n"+hand.toString());
        if(!hand.noMatches(game.getTopCard())){
            System.out.println("\nYour hand still has no matches your turn is being passed");
        }
        //If there is something playable, it initially or after draw
        else {
            while (true) {
                //Takes the players input
                String intIn = game.interact("\nWhich card would you like to play?");
                //We first attempt to make the input into an integer, if the input is invalid it throw 1
                try {
                    int inInt = Integer.parseInt(intIn);
                    //if the conversion to integer is successful, we see if the index is within range 2
                    if (inInt > hand.numCardsRemaining() - 1 || inInt < 0) {
                        //If it's not, it lets the player know
                        System.out.println(intIn + " is not a valid index, please try again.");
                    } else {
                        //lastly we attempt to play the card 3
                        try {
                            //3 if it is done successfully the loop breaks and we continue
                            hand.playCard(game, inInt);
                            break;
                        } catch (Card.CannotPlayCardException e) {
                            //3 if the card is not playable we catch the exception thrown by card and let the player
                            //know
                            System.out.println("Card " + intIn + " cannot currently be played.");
                        }
                    }
                } catch (NumberFormatException e) {//1 integer is not valid
                    System.out.println(intIn + " is not a valid integer, please try again.");
                }
            }
        }
    }

    public boolean emptyHand() {
        return hand.numCardsRemaining() == 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
