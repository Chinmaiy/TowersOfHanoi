package logic;

import model.State;

import java.util.Random;

/**
 * Created by Chinmaiy on 10/19/2016.
 */
public class Game {

    public static void main(String[] args) {

        State initialState = new State(3, 6);

        Solver solver = new RandomSolver();

        solver.solve(initialState);

    }
}
