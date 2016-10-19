package logic;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Chinmaiy on 10/18/2016.
 */
public class RandomSolver extends Solver {

    public int MAX_MOVES_BEFORE_RESTART = 1000;
    public int movesContor = 0;

    public List<State> previousStates = new ArrayList<>();

    private List<Move> selectivePossibleMoves = new ArrayList<>();
    private int nrOfDisks;

    @Override
    public void solve(State initialState) {

        init(initialState);
        nrOfTowers = initialState.nrOfTowers;
        nrOfDisks = initialState.nrOfDisks;

        generateAllPossibleMoves();

        while(!currentState.isFinal()) {

            if(movesContor == MAX_MOVES_BEFORE_RESTART) {
                init(initialState); //maybe initialize it to a random state previously seen
            }

            Move move = pickNextMove();

            try {
                State newState = currentState.transitionUsing(move);
                if(!previousStates.contains(newState)) {

                    currentState = newState;
                    previousStates.add(currentState);
                    solution.add(move);
                    ++movesContor;
                }
            } catch (InvalidMoveException e) {

            }
        }

        printSolution();

    }

    /*@Override
    public Move pickNextMove() {

        Random r = new Random();
        int fromTower = r.nextInt(nrOfTowers);
        int toTower = fromTower;
        while(toTower == fromTower)
            toTower = r.nextInt(nrOfTowers);

        return new Move(fromTower, toTower);
    }*/

    @Override
    public Move pickNextMove() {

        selectivePossibleMoves.clear();

        Set<Integer> possibleFromTowers = new HashSet<>();

        /*for (int i = 0; i < nrOfDisks; i++) {
            possibleFromTowers.add(currentState.arrangement[i]);
        }

        for (Move move : possibleMoves) {
            if(possibleFromTowers.contains(move.fromTower))
                selectivePossibleMoves.add(move);
        }*/

        selectivePossibleMoves =
                possibleMoves.stream().filter(move -> currentState.isValid(move)).collect(Collectors.toList());

        Random r = new Random();
        int noOfPossibleMoves = selectivePossibleMoves.size();
        int selectedMove = r.nextInt(noOfPossibleMoves);

        return selectivePossibleMoves.get(selectedMove);
    }

    private void init(State initialState) {

        currentState = initialState;
        previousStates.clear();
        previousStates.add(initialState);
        solution.clear();
        movesContor = 0;
    }
}
