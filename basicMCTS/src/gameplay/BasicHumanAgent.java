package gameplay;

import boardgame.elements.Action;
import boardgame.elements.GameState;

import java.util.List;
import java.util.Scanner;

/**
 *  Basic class for Human player, should be able to handle any MCTS compatible game
 */
public class BasicHumanAgent<T extends GameState, A extends Action> implements Agent<T, A>{

    /**
     * Function prints list of possible actions to the console and let player choose one of them
     */
    @Override
    public A chooseActionToPlay(T gameState) {
        List<A> actions = gameState.getAllPossibleActions();
        A chosen = null;
        while(chosen == null){
            int i = 0;
            System.out.println("------");
            System.out.println("Possible actions:");
            for (A action : actions) {
                System.out.println(i+": "+action.toString());
                i++;
            }
            System.out.println("------");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose action number\n");
            String input = scanner.nextLine();
            try{
                int liczba = Integer.parseInt(input);
                chosen = actions.get(liczba);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        return chosen;
    }
}
