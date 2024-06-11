public class Numbers extends Card {
    private final int n;

    public Numbers(Color color, int n) {
        super(color);

        if (!(n >= 0 && n <= 9)) {
            throw new IllegalArgumentException(n + " must be between [0,9]");
        }

        this.n = n;
    }

    public int getN() {
        return n;
    }

    @Override
    public void doAction(Game game) {}

    @Override
    public boolean matchValue(Card other) {
        if (other instanceof Numbers o) {
            return getN() == o.getN();
        }
        else {
            return false;
        }
    }

    @Override
    public String strRep() {
        return Integer.toString(n);
    }
}