public class DrawN extends Card {
    private final int n;

    public DrawN(Color cardColor, int n) {
        super(cardColor);
        this.n = n;
    }

    public int getN() {
        return n;
    }

    /**
     * Makes the next player draw n cards
     * @param game Current game state
     * ODO: Implement this
     */
    @Override
    public void doAction(Game game) {
        //takes a look at the next player according to UnisIterator in game and gets it to draw
        game.getPlayers().peekNext().drawCards(n);
    }

    /**
     * Checks if other has the same value as this
     * @param other Other card to match against
     * @return true if other is an instanceof DrawN and our n equals their n, false otherwise
     * ODO: Implement this
     */
    @Override
    public boolean matchValue(Card other) {
        if(other.getClass()==DrawN.class && ((DrawN) other).getN()==this.getN()){
           return true;
        }
        return false;
    }

    @Override
    public String strRep() {
        return "D+" + n;
    }
}
