package edu.colostate.cs.cs414.method_men.jungle.client.tileTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Game.tile.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void testGetAttribute() {
        Tile tile = new Tile('p');
        assertEquals(tile.getAttribute(),'p');
    }
}
