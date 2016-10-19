package logic;

import model.*;

import java.util.*;

/**
 * Created by Chinmaiy on 10/19/2016.
 */
public class BacktrackSolver extends Solver {

    boolean solutionFound = false;

    @Override
    public void solve(State initialState) {

        currentState = initialState;
        nrOfTowers = initialState.nrOfTowers;
        generateAllPossibleMoves(); //selective

        Deque<State> previousStates = new ArrayDeque<>();
        previousStates.push(initialState);

        backtrack(previousStates);
    }

    @Override
    public Move pickNextMove() {

        return null;
    }

    private void backtrack(Deque<State> previousStates) {

        currentState = previousStates.getLast();

        if(currentState.isFinal()) {
            printSolution();
            solutionFound = true;
            return;
        }

        for (Move move : possibleMoves) {

            if(solutionFound)
                return;

            try {
                State newState = currentState.transitionUsing(move);
                if(!previousStates.contains(newState)) {

                    previousStates.add(newState);
                    solution.add(move);
                    backtrack(previousStates);
                }
            } catch (InvalidMoveException e) {

            }
        }

        previousStates.removeLast();
        if(previousStates.isEmpty()) {
            System.out.println("No solution found.");
            return;
        }
        int lastMove = solution.size() - 1;
        solution.remove(lastMove);
    }
}
