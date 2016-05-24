//package com.mygdx.game.model;
//
//import com.mygdx.game.model.obstacles.Wall;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// * Created by martinso on 22/05/16.
// */
//public class WallTest {
//
//    /**
//     * Test building an area of walls, size: height: 1, width: 3.
//     */
//    @Test
//    public void testBuildWalls() {
//        Wall wall = new Wall();
//        wall.createAreaBounds(1, 3, 32);
//        for(int i = 0; i<wall.getWalls().size(); i++) {
//            assertTrue(wall.getWalls().get(i).getPosX() == i*32);
//            assertTrue(wall.getWalls().get(i).getPosY() == 0);
//        }
//    }
//
//}
