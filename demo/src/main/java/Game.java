import java.util.*;

public final class Game {
    private final Scanner io;
    private final UnusIterator<Player> players;
    private final int numPlayers;
    private final Deck deck;
    private final Deque<Card> playArea;

    private final List<String> Names;

    /**
     * Constructs all the data necessary to run a game.
     * This includes the following:
     * - Create a scanner of System.in and saves it into io
     * - Creates a deck using the createDeck function and saves it into deck.
     * - Creates a list of players with length given by numPlayers
     * - Has each player draw 5 cards
     * - Creates a UnusIterator with the aforementioned player list
     * - Assigns the parameter numPlayers to the instance variable numPlayers
     * - Initializes playArea with a new ArrayDeque
     * @param numPlayers
     * ODO: Implement this
     */
    public Game(int numPlayers, List<String> Names) {
        //Scanner that takes input and saves it in io
        Scanner in = new Scanner(System.in);
        io=in;
        //Making deck using createDeck()
        this.deck = createDeck();
        //make a quick list of players
        List<Player> lsPl = new ArrayList<>();
        for (int i=0; i<numPlayers; i++){
            //makes different players named after the number of the order they go in
            Player a=new Player(Integer.toString(i), this);
            //the player draws 5 cards
            a.drawCards(5);
            //adds the current player to the list of players
            lsPl.add(a);
        }
        //list of players lsPs is made to players
        this.players = new UnusIterator<>(lsPl);
        //Assign/make the other objects
        this.numPlayers = numPlayers;
        this.playArea = new ArrayDeque<>();
        this.Names = Names;
    }

    /**
     * The main game loop function.
     * Does the following:
     * - Loops until the curPlayer's hand is empty
     *   - When this is the case the curPlayer has won the game.
     *   - It then prints: "$curPlayer won!"
     * - The current player is received from the UnusIterator
     * - The player then takes their turn
     * - The UnusIterator is then moved to the next player
     * ODO: Implement this
     */
    public void start() {
        //Loop that runs until the current player has an empty hand
        while(!players.current().emptyHand()){
            //Have it looked through iterator this will call next after the player takes their turn
            players.current().takeTurn();
            //after the player takes a turn it checks if the hand is empty, if so it congratulates the player and dies
            if(players.current().emptyHand()){
                System.out.println(getNames().get(players.getCurIndex()) + " won!");
                break;
            }
            //then we just call on the next player if there is no victor
            players.next();
        }
    }

    public String interact(String toUser) {
        System.out.println(toUser);
        return io.nextLine();
    }

    public UnusIterator<Player> getPlayers() {
        return players;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getNumberOfCardsInPlay() {
        return playArea.size();
    }

    public Deck getDeck() {
        return deck;
    }

    public Card getTopCard() {
        if (playArea.isEmpty()) {
            return new None();
        }

        return playArea.getFirst();
    }

    public void playCard(Card card) {
        playArea.addFirst(card);
    }

    public List<String> getNames(){return Names;}

    public void shufflePlayAreaIntoDeck() {
        deck.addCards(playArea);
        deck.shuffleDeck();
    }

    /**
     * Creates the standard 108 card Unus deck.
     * The deck contains the following cards:
     * - 19 red cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 blue cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 green cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 yellow cards
     *   - 1 zero
     *   - 2 of every number
     * - 8 skip cards - two of each color
     * - 8 reverse cards - two of each color
     * - 8 draw 2 cards - two of each color
     * - 4 wild cards
     * - 4 wild draw 4 cards
     * @return A standard Unus deck of 108 cards
     * ODO: Implement this
     */
    private Deck createDeck() {
        //making a list that we can place all the cards into then add to deck
        List<Card> cards = new ArrayList<>();
        //adding the 0s first because there is only one of each color
        cards.add(new Numbers(Card.Color.YELLOW, 0));
        cards.add(new Numbers(Card.Color.RED, 0));
        cards.add(new Numbers(Card.Color.BLUE, 0));
        cards.add(new Numbers(Card.Color.GREEN, 0));
        //then for loops to add every other number 2 at a time
        for(int i=0; i<9; i++){
            cards.add(new Numbers(Card.Color.YELLOW, i+1));
            cards.add(new Numbers(Card.Color.YELLOW, i+1));
            cards.add(new Numbers(Card.Color.RED, i+1));
            cards.add(new Numbers(Card.Color.RED, i+1));
            cards.add(new Numbers(Card.Color.BLUE, i+1));
            cards.add(new Numbers(Card.Color.BLUE, i+1));
            cards.add(new Numbers(Card.Color.GREEN, i+1));
            cards.add(new Numbers(Card.Color.GREEN, i+1));
        }
        //8 of the skip, draw2, and reverse. Two of each color
        for(int i=0; i<2; i++){
            cards.add(new Skip(Card.Color.BLUE));
            cards.add(new Skip(Card.Color.YELLOW));
            cards.add(new Skip(Card.Color.RED));
            cards.add(new Skip(Card.Color.GREEN));
            cards.add(new DrawN(Card.Color.BLUE,2));
            cards.add(new DrawN(Card.Color.YELLOW,2));
            cards.add(new DrawN(Card.Color.RED,2));
            cards.add(new DrawN(Card.Color.GREEN,2));
            cards.add(new Reverse(Card.Color.BLUE));
            cards.add(new Reverse(Card.Color.YELLOW));
            cards.add(new Reverse(Card.Color.RED));
            cards.add(new Reverse(Card.Color.GREEN));
        }
        //lastly 4 wild and wild draw 4, each different color
        for (int i=0; i<4; i++){
            cards.add(new Wild(Card.Color.WILD));
        }
        for(int i=0; i<4; i++) {
            cards.add(new DrawN(Card.Color.WILD, 4));
        }
        //add the list to deck and shuffle it
        Deck deck1 = new Deck(cards);
        //deck.addCards(cards);
        return deck1;
    }
}
