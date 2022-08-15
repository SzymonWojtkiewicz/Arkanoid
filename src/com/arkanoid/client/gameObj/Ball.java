package com.arkanoid.client.gameObj;

import com.arkanoid.client.Arkanoid;

public class Ball {
    private int speed = 1; //px/ms
    private int X = 400;
    private int Y = 700;
    private int radius = 10;
    public int speedVector = 90;//for now lets just say 0 to 360 in degrees
    boolean hasStarted = false;

    public Ball() {
    }

    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public int getRadius() {
        return radius;
    }

    public boolean move(){
        int frameDuration = Level.getFrameMs();
        int newX = (int)(this.X + this.speed * frameDuration * Math.cos(Math.toRadians(this.speedVector))/5);
        int newY = (int)(this.Y + this.speed * frameDuration * Math.sin(Math.toRadians(this.speedVector))/5);

        //checking collisions

        //collisions with bat

        if(newY > Arkanoid.SpaceHeight - Arkanoid.gameLogic.bat.getBatHeight() - this.radius){
            if(newX > Arkanoid.gameLogic.bat.getPosition() && newX < Arkanoid.gameLogic.bat.getPosition() + Arkanoid.gameLogic.bat.getBatWidth()){
                this.speedVector = (int)(210 + 120 * (newX - Arkanoid.gameLogic.bat.getPosition())/Arkanoid.gameLogic.bat.getBatWidth());
                this.X = newX;
                return true;
            }
        }


        this.X = newX;
        this.Y = newY;



        return true;
    }


}
