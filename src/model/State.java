package model;

import java.util.Arrays;

/**
 * Created by Chinmaiy on 10/18/2016.
 */
public class State {

    public int nrOfTowers; //the to test against for final state
    public int nrOfDisks; //length of the array that describes the arrangement of disks in a state
    public int[] arrangement; //the position

    private State(int nrOfTowers, int nrOfDisks, int[] arrangement) {

        this.nrOfTowers = nrOfTowers;
        this.nrOfDisks = nrOfDisks;
        this.arrangement = arrangement;
    }

    public State(int nrOfTowers, int nrOfDisks) {

        this.nrOfTowers = nrOfTowers;
        this.nrOfDisks = nrOfDisks;

        arrangement = new int[nrOfDisks];
        Arrays.fill(arrangement, 0);
    }

    public boolean isFinal() {

        int finalTower = nrOfTowers - 1;
        for (int i : arrangement)
            if (i != finalTower)
                return false;

        return true;
    }

    public boolean isValid(Move move) {

        int topDiskOnTheFromTower = -1;
        for (int i = 0; i < nrOfDisks; i++) {
            if (arrangement[i] == move.fromTower) {
                topDiskOnTheFromTower = i;
                break;
            }
        }

        if (topDiskOnTheFromTower == -1) return false; //there no disk on the from tower to move

        int topDiskOnTheToTower = -1;
        for (int i = 0; i < nrOfDisks; i++) {
            if (arrangement[i] == move.toTower) {
                topDiskOnTheToTower = i;
                break;
            }
        }

        if (topDiskOnTheToTower > -1 && topDiskOnTheToTower < topDiskOnTheFromTower)
            return false;

        return true;
    }

    public State transitionUsing(Move move) throws InvalidMoveException {

        if (isValid(move)) {
            int topDiskOnTheFromTower = 0;
            for (int i = 0; i < nrOfDisks; i++) {
                if (arrangement[i] == move.fromTower) {
                    topDiskOnTheFromTower = i;
                    break;
                }
            }

            int[] newArrangement = new int[nrOfDisks];
            System.arraycopy(arrangement, 0, newArrangement, 0, nrOfDisks);
            newArrangement[topDiskOnTheFromTower] = move.toTower;

            return new State(nrOfTowers, nrOfDisks, newArrangement);
        }
        else throw new InvalidMoveException("Invalid move.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (nrOfTowers != state.nrOfTowers) return false;
        if (nrOfDisks != state.nrOfDisks) return false;

        return Arrays.equals(arrangement, state.arrangement);
    }

    @Override
    public int hashCode() {
        int result = nrOfTowers;
        result = 31 * result + nrOfDisks;
        result = 31 * result + Arrays.hashCode(arrangement);
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "arrangement=" + Arrays.toString(arrangement) +
                '}';
    }
}
