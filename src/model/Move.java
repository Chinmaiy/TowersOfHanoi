package model;

/**
 * Created by Chinmaiy on 10/18/2016.
 */
public class Move {

    public int fromTower;
    public int toTower;

    public Move(int fromTower, int toTower) {

        this.fromTower = fromTower;
        this.toTower = toTower;
    }

    @Override
    public String toString() {
        return "Move{" + (fromTower + 1) + ", " + (toTower + 1) + "}";
    }
}
