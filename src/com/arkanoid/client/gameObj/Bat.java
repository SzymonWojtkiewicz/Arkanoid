package com.arkanoid.client.gameObj;

import com.arkanoid.client.Arkanoid;

public class Bat {
    private int batWidth = 100;
    private int batHeight = 25;
    private int  maxSpeed = 50;//px/sec

    private int position = 0;
    private int speed = 0;

    private int acceleration = 1; // px/ms^2

    public Bat() {
    }

    public int getBatHeight() {
        return batHeight;
    }
    public int getBatWidth() {
        return batWidth;
    }
    public int getPosition() {
        return position;
    }
    public int getSpeed() {
        return speed;
    }
    public int getAcceleration() {
        return acceleration;
    }
    public void setPosition(int position) {
        this.position = position;
    }



    public int move(int finalVectorUnProc){

        this.position = finalVectorUnProc - this.batWidth/2;
        if(this.position < Level.getBatLeftlimit()) {
            this.position =  Level.getBatLeftlimit();
        }
        else if(this.position > Level.getBatRightlimit() - this.batWidth) {
            this.position = Level.getBatRightlimit() - this.batWidth;
        }
      return position ;
    }



}
