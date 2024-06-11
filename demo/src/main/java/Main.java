import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Main {
    public static void main(String[] args) {
        System.out.println("Number of Players?");
        Scanner sc = new Scanner(System.in);
        List<String> PlayerN = new ArrayList<>();
        int PlN=0;
        int a=1;
        if(sc.hasNextInt()){
            PlN = sc.nextInt();
            for(int i=0; i<PlN; i++){
                System.out.println("Name of player "+(i+1)+"?");
                Scanner NameIn = new Scanner(System.in);
                PlayerN.add(NameIn.nextLine()+" Player Number: "+a);
                a++;
            }
        }else {System.out.println("Please enter a number.");}
        Game game = new Game(PlN, PlayerN);
        game.start();
    }
}
