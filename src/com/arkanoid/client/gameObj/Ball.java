package com.arkanoid.client.gameObj;

import com.arkanoid.client.Arkanoid;

public class Ball {
    private int speed = 150; //px/ms
    private int X = 400;
    private int Y = 700;
    private int radius = 30;
    public int speedVector = 270;//for now lets just say 0 to 360 in degrees 270 for up 0 for right
    public boolean hasStarted = false;
    public boolean hasEnded = false;
    public static int difficulty = 1;
    public Ball() {
    }

    public int getSpeed() {
        return speed;
    }
    public int setSpeed(int speed) {
        this.speed = speed;
        return speed;
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

        if(hasStarted == false && hasEnded == false){
            this.X = Arkanoid.gameLogic.bat.getPosition() + (Arkanoid.gameLogic.bat.getBatWidth() - this.radius)/2;
            this.Y = Arkanoid.SpaceHeight - Arkanoid.gameLogic.bat.getBatHeight() - this.radius;
            return true;
        }
        if(hasStarted == true && hasEnded == true){
            return false;

        }
        //bug fix
        if(this.speedVector > 359) {
            this.speedVector -= 360;
        }

        if(this.speedVector < 0) {
            this.speedVector += 360;
        }

        int frameDuration = Level.getFrameMs();
        int newX = (int)(this.X + this.speed * difficulty * frameDuration * Math.cos(Math.toRadians(this.speedVector))/500);
        int newY = (int)(this.Y + this.speed * difficulty * frameDuration * Math.sin(Math.toRadians(this.speedVector))/500);

        //checking collisions


        //collisions with bat

        if(newY > Arkanoid.SpaceHeight - Arkanoid.gameLogic.bat.getBatHeight() - this.radius){
            if(newX > Arkanoid.gameLogic.bat.getPosition() - 3 * radius /4 && newX < Arkanoid.gameLogic.bat.getPosition() + Arkanoid.gameLogic.bat.getBatWidth() + 3 * radius /4){
                this.speedVector = (int)(210 + 120 * (newX - Arkanoid.gameLogic.bat.getPosition())/Arkanoid.gameLogic.bat.getBatWidth());
                this.X = newX;
                Arkanoid.batHit.play();
                return true;
            }else{
                if(newY > Arkanoid.SpaceHeight - this.radius){
                    this.hasEnded = true;
                    this.hasStarted = true;
                    this.X = newX;
                    this.Y = Arkanoid.SpaceHeight - this.radius;
                    Arkanoid.lostlife.play();
                    return false;
                }
                this.X = newX;
                this.Y = newY;

                return true;
            }
        }

        //collisions with walls

            //upper wall
        if(newY < 0){


            if(this.speedVector > 180){
                this.speedVector = 360 - this.speedVector;
            }
            else{
                this.speedVector = 180 - this.speedVector;
            }
            //bug fix
            if(this.speedVector < 5 || this.speedVector > 355){
                this.speedVector = 10;
            }
            if(this.speedVector < 185 && this.speedVector > 175){
                this.speedVector = 190;
            }

            Arkanoid.wallHit.play();

            this.Y = 0;

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
            this.X = 0;//this.radius;
            this.Y = newY;
            //bug fix
            if(this.speedVector < 275 && this.speedVector > 265){
                this.speedVector = 280;
            }
            if(this.speedVector < 95 && this.speedVector > 85){
                this.speedVector = 80;
            }
            Arkanoid.wallHit.play();
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
                this.speedVector = 270;
            }
            if(this.speedVector < 95 && this.speedVector > 85){
                this.speedVector = 100;
            }
            Arkanoid.wallHit.play();
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
                if(X + this.radius > brick.getBrickX() && X < brick.getBrickX() + brick.getBrickWidth())
                    this.speedVector = 360 - this.speedVector;//collision with horizontal side of brick
                else
                    this.speedVector = 180 - this.speedVector;//collision with vertical side of brick


                brick.hp--;
                if(brick.hp == 0)
                    Arkanoid.brake.play();
                Arkanoid.crack.play();
                return true;
            }
        }

        this.X = newX;
        this.Y = newY;



        return true;
    }


}
