package logic;

import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chinmaiy on 10/18/2016.
 */
public abstract class Solver {

    public List<Move> solution = new ArrayList<>();
    public State currentState;

    public int nrOfTowers;

    protected List<Move> possibleMoves = new ArrayList<>();

    public void solve(State state) {

        currentState = state;
        while(!currentState.isFinal()) {

            Move move = pickNextMove();
            try {
                currentState = currentState.transitionUsing(move);
                solution.add(move);
            } catch (InvalidMoveException e) {

            }
        }

        printSolution();
    }

    public abstract Move pickNextMove();

    public void printSolution() {

        solution.stream().forEach(System.out::println);
    }

    protected void generateAllPossibleMoves() {

        for (int fromTower = 0; fromTower < nrOfTowers; fromTower++) {
            for (int toTower = 0; toTower < nrOfTowers; toTower++) {
                if(fromTower == toTower)
                    continue;
                possibleMoves.add(new Move(fromTower, toTower));
            }
        }
    }
}
