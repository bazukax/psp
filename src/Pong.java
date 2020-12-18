
import java.util.*;


public class Pong {
    public static void main(String[] args) {
        switch (menu()) {
            case 1:
                GameFrame frame = new GameFrame(true);
                break;
            case 2:
                GameFrame AIframe = new GameFrame(false);
                break;
            case 3:
                break;
        }


    }

    private static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - One Player");
        System.out.println("2 - Two Playes");
        System.out.println("3 - Quit");

        selection = input.nextInt();
        return selection;
    }


}
