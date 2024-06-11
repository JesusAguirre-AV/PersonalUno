public class Wild extends Card {

    public Wild(Color cardColor) {
        super(cardColor);
    }

    @Override
    public void doAction(Game game) {}

    @Override
    public boolean matchValue(Card other) {
        return true;
    }

    @Override
    public String strRep() {
        return "W";
    }
}
