package edu.colostate.cs.cs414.method_men.jungle.client;

import java.util.ArrayList;
import java.util.Arrays;

public class Location {
    private int row, col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Location(Location loc) {
        this(loc.getRow(), loc.getCol());
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    /**
     * Checks if the next move's location is out of bounds
     * @param row the next move's horizontal location on the board
     * @param col the next move's vertical location on the board
     * @return true if it is OOB, false if in bounds
     */
    public static boolean isOutOfBounds(int row, int col) {
        return (row < 0 || row > 8 || col < 0 || col > 6);
    }

    /**
     * Checks if the next move's location is out of bounds
     * @param location the location in question
     * @return true if it is OOB, false if in bounds
     */
    public static boolean isOutOfBounds(Location location) {
        return isOutOfBounds(location.getRow(), location.getCol());
    }

    //Checks if the move is 1 up/down/left/right move away. Doesn't care about bounds
    public boolean isAdjacent(Location location){
        if (this.getCol()+1 == location.getCol() && this.getRow()   == location.getRow() || //Moving right
            this.getCol()-1 == location.getCol() && this.getRow()   == location.getRow() || //Moving left
            this.getCol()   == location.getCol() && this.getRow()-1 == location.getRow() || //Moving up
            this.getCol()   == location.getCol() && this.getRow()+1 == location.getRow()){  //Moving down
            return true;
        }
        return false;
    }

    //Takes a location and returns all locations next to it. Does not return out of bounds locations.
    public static ArrayList<Location> getAdjacent(Location location){
        ArrayList<Location> adjacent = new ArrayList<>();
        //Go through and look at up/down/left/right, returning the set of those which are in bounds.
        if (!(isOutOfBounds(new Location(location.getRow() + 1, location.getCol())))){
            adjacent.add(new Location(location.getRow() + 1, location.getCol()));
        }

        if (!(isOutOfBounds(new Location(location.getRow() - 1, location.getCol())))) {
            adjacent.add(new Location(location.getRow() - 1, location.getCol()));
        }

        if (!(isOutOfBounds(new Location(location.getRow(), location.getCol() + 1)))){
            adjacent.add(new Location(location.getRow(), location.getCol() + 1));
        }

        if (!(isOutOfBounds(new Location(location.getRow(), location.getCol() - 1)))){
            adjacent.add(new Location(location.getRow(), location.getCol() - 1));
        }
        return adjacent;
    }

    //Gives amount of moves to reach location
    public static int getDistance(Location start, Location end){
        return Math.abs(start.getCol()-end.getCol()) + Math.abs(start.getRow() - end.getRow());
    }

    //Tells direction from start to end: up, down, left, right, same, bad
    public static String getDirection(Location start, Location end){
        if (start.getRow() > end.getRow() && start.getCol() == end.getCol()){
            return "up";
        } else if (start.getRow() < end.getRow() && start.getCol() == end.getCol()){
            return "down";
        } else if (start.getRow() == end.getRow() && start.getCol() > end.getCol()){
            return "left";
        } else if (start.getRow() == end.getRow() && start.getCol() < end.getCol()){
            return "right";
        }  else if (start.getRow() == end.getRow() && start.getCol() == end.getCol()){
            return "same";
        } else {
            return "bad";
        }
    }

    @Override
    public String toString(){
        return "[" + this.getRow() + ", " + this.getCol() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (row != location.row) return false;
        return col == location.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}
