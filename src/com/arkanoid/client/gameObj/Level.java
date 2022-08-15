package com.arkanoid.client.gameObj;

import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

public class Level {
    private static int frameMs = 16;//ms
    private static int batLeftlimit = 0;//just bc
    private static int batRightlimit = 800;

    private int[] hpTab = {1, 2, 3, 2, 1, 3, 2, 2, 1, 3, 2, 2, 3, 1 ,3, 2, 3, 1, 3, 2, 1, 3, 1, 2, 3, 3, 2, 1, 2, 2, 2, 1};
    public List<Brick> bricks;


    public Level(int numberOfBricks) {
        bricks = new ArrayList<Brick>();
        int nextBrickX = 100;
        int nextBrickY = 100;
        int brickWidth = 50;
        int brickHeight = 20;
        //Random random = new Random(); // its starts wrong strange when in use
        for(int i = 0; i < numberOfBricks; i++) {
//random.nextInt(4)
            int hpTabIndex = i%hpTab.length;;

            bricks.add(new Brick(nextBrickX, nextBrickY,hpTab[hpTabIndex], brickWidth, brickHeight));
            nextBrickX = nextBrickX +brickWidth +5;
            if(nextBrickX + brickWidth > batRightlimit - 50) {
                nextBrickX = 100;
                nextBrickY = nextBrickY + brickHeight + 5;
            }
        }
    }


    public static int getFrameMs() {
        return frameMs;
    }


    public static void setFrameMs() {
        frameMs = frameMs;
    }
    public static int getBatLeftlimit() {
        return batLeftlimit;
    }
    public static int getBatRightlimit() {
        return batRightlimit;
    }
    public static void setBatLeftlimit(int batLeftlimit) {
        Level.batLeftlimit = batLeftlimit;
    }
    public static void setBatRightlimit(int batRightlimit) {
        Level.batRightlimit = batRightlimit;
    }

}
