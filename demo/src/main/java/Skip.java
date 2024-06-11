import java.util.ArrayList;
import java.util.List;

public final class Skip extends Card {

    public Skip(Color cardColor) {
        super(cardColor);
    }

    /**
     * Skip can skip any player except for the player who played it.
     * This function accomplishes the following:
     * - Prompts the user who they would like to skip with the following message:
     *   "Who would you like to skip? (n)ext or (s)pecific user?"
     * - If the answer is "n" then the next player is skipped
     * - If the answer is "s" then a specific player is skipped
     *   - The user must then be prompted with the following prompt:
     *     "Please choose from the following numbers: $playerNumbers"
     *     where playerNumbers are all the indices of players other than the current player seperated by spaces
     *   - You must loop until they give a valid index, if they fail output the following message:
     *     "$playerNumber is not valid. $playersToChoose"
     *     where playerNumber is the number they input
     *   - If they give an index that is not a number then output the following message and loop again:
     *     "$n not an int, please try again."
     *     where n is the index they input
     * - You must loop until they give you a valid command, if they fail output the following message:
     *   "$answer is not a recognized command, please try again."
     * @param game
     * TODO: Implement this
     */
    @Override
    public void doAction(Game game) {
        //To make it easier to show the selectable players we make a list of them and a string to put the list in
        String selectable="";
        List<String> ls = new ArrayList<>();
        int a=1;
        for (int i=0; i< game.getNumPlayers(); i++){
            ls.add(Integer.toString(a));
            a++;
        }
        ls.remove(game.getPlayers().getCurIndex());
        for (int i=0; i< ls.size(); i++){
            selectable=selectable+ls.get(i);
            if(ls.iterator().hasNext()){
                selectable=selectable+", ";
            }
        }
        String answer = game.interact("Who would you like to skip? (n)ext or (s)pecific user?");
        while (true) {
        //loops after asking player what option they would like to skip the next player or a player of their choosing
            if (answer.equals("n")) {
        //technically this will make it the next player's turn, but game.start will call next again effectively skipping
                game.getPlayers().next();
                break;
            } else if (answer.equals("s")) {
                //if the current player want to select it makes a loop until there is a valid input
                while (true) {
                    //takes the input as selectedP after printing the player's options as the selectable string
                    String selectedP = game.interact("Please choose from the following numbers: "
                            +selectable);
                    //Because we made ls a list where the index integer the same as their String equivalent we can use
                        //either one to compare/interact so if the String is in the list its index is the same int.
                    if (ls.contains(selectedP)) {
                        game.getPlayers().skip(Integer.parseInt(selectedP)-1);
                        break;
                    } else {
                    //If the input isn't one of the options it says it's not valid and reminds the player of the options
                        System.out.println(selectedP + " is not valid" + selectable);
                    }
                }
                break;
            } else {
                answer=game.interact(answer + " is not a recognized command, please try again.");
            }
        }
    }

    @Override
    public boolean matchValue(Card other) {
        return other instanceof Skip;
    }

    @Override
    public String strRep() {
        return "S";
    }
}
