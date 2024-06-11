import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
        shuffleDeck();
    }

    public static class EmptyDeckException extends Exception {}

    /**
     * This function does the following:
     * - Checks if cards is empty
     *   - If it is then throw a new EmptyDeckException
     *   - If not then return and remove the first card in cards
     * @return The top card from the deck
     * @throws EmptyDeckException
     * ODO: Implement this
     */
    public Card drawCard() throws EmptyDeckException {
        if(cards.isEmpty()){
            throw new EmptyDeckException();
        }
        //if cards isn't empty it will return and remove the card in the first index (that index being 0)
        else {
            return cards.remove(0);
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    }

    public void addCards(Collection<Card> cards) {
        this.cards.addAll(cards);
        shuffleDeck();
    }
}
