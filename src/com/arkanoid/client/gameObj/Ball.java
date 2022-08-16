package com.arkanoid.client.gameObj;

import com.arkanoid.client.Arkanoid;

public class Ball {
    private int speed = 1; //px/ms
    private int X = 400;
    private int Y = 700;
    private int radius = 30;
    public int speedVector = 270;//for now lets just say 0 to 360 in degrees
    public boolean hasStarted = false;
    public boolean hasEnded = false;

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

        if(hasStarted == false) {
            this.X = Arkanoid.gameLogic.bat.getPosition() + (Arkanoid.gameLogic.bat.getBatWidth() - this.radius)/2;
            this.Y = Arkanoid.SpaceHeight - Arkanoid.gameLogic.bat.getBatHeight() - this.radius;
            return true;
        }
        //bug fix
        if(this.speedVector > 359) {
            this.speedVector -= 360;
        }

        if(this.speedVector < 0) {
            this.speedVector += 360;
        }

        int frameDuration = Level.getFrameMs();
        int newX = (int)(this.X + this.speed * frameDuration * Math.cos(Math.toRadians(this.speedVector))/5);
        int newY = (int)(this.Y + this.speed * frameDuration * Math.sin(Math.toRadians(this.speedVector))/5);

        //checking collisions


        //collisions with bat

        if(newY > Arkanoid.SpaceHeight - Arkanoid.gameLogic.bat.getBatHeight() - this.radius/2){
            if(newX > Arkanoid.gameLogic.bat.getPosition() && newX < Arkanoid.gameLogic.bat.getPosition() + Arkanoid.gameLogic.bat.getBatWidth()){
                this.speedVector = (int)(210 + 120 * (newX - Arkanoid.gameLogic.bat.getPosition())/Arkanoid.gameLogic.bat.getBatWidth());
                this.X = newX;
                return true;
            }else{
                return false;
            }
        }

        //collisions with walls

            //upper wall
        if(newY < this.radius){


            if(this.speedVector > 180){
                this.speedVector = 360 - this.speedVector;
            }
            else{
                this.speedVector = 180 - this.speedVector;
            }
            //bug fix
            if(this.speedVector < 5 || this.speedVector > 355){
                this.speedVector = 5;
            }
            if(this.speedVector < 185 && this.speedVector > 175){
                this.speedVector = 185;
            }



            this.Y = this.radius;
            //this.X = newX;
            return true;
        }
            //side walls
        if(newX < 0){
            if(this.speedVector > 90 && this.speedVector < 270){
                this.speedVector = 180 - this.speedVector;
            }
            else{
                this.speedVector = 180 - this.speedVector;// not need?
            }
            this.X = this.radius;
            this.Y = newY;
            //bug fix
            if(this.speedVector < 275 && this.speedVector > 265){
                this.speedVector = 275;
            }
            if(this.speedVector < 95 && this.speedVector > 85){
                this.speedVector = 85;
            }
            return true;
        }
        if(newX > Arkanoid.SpaceWidth - this.radius){
            if(this.speedVector > 90 && this.speedVector < 270){
                this.speedVector = 360 - this.speedVector;// not need?
            }
            else{
                this.speedVector = 180 - this.speedVector;
            }
            this.X = Arkanoid.SpaceWidth - this.radius;
            this.Y = newY;
            //bug fix
            if(this.speedVector < 275 && this.speedVector > 265){
                this.speedVector = 265;
            }
            if(this.speedVector < 95 && this.speedVector > 85){
                this.speedVector = 95;
            }
            return true;
        }

        // collisons with bricks

        /*  UL                  UR
        *   BrickX BrickY       BrickX+W BrickY
        *   newX+Rad  newY+Rad          newX  newY+Rad
        *
        *   newX+Rad  newY          newX  newY
        *   BrickX BrickY+H     BrickX+W BrickY+H
        *   LL                  LR
        */

        for(Brick brick : Arkanoid.gameLogic.level.bricks){
            if(brick.hp == 0){
                continue;
            }
            if(newX + this.radius > brick.getBrickX() && newX < brick.getBrickX() + brick.getBrickWidth() && newY + this.radius > brick.getBrickY() && newY < brick.getBrickY() + brick.getBrickHeight()){
                this.speedVector = 180 + this.speedVector;

                //this.X = newX;
               // this.Y = newY;
                brick.hp--;

                return true;
            }
        }

        this.X = newX;
        this.Y = newY;



        return true;
    }


}
