package com.arkanoid.client.gameObj;

import com.arkanoid.client.Arkanoid;

public class Bat {
    private int batWidth = 40;
    private int batHeight = 10;
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
        /*
      int frameMs = Level.getFrameMs();
      int finalVector = finalVectorUnProc - this.batWidth/2; //center of a bat on mouse

      int shiftVector = finalVector - this.position;//if >0 right shift, if <0 left shift
        int accelerationDir = 0;
        if(shiftVector > 0) {
            accelerationDir = 1;
        }
        else if(shiftVector < 0) {
            accelerationDir = -1;
        }
        else {
            if(speed > 0) {
                accelerationDir = -1;
            }
            else if(speed < 0) {
                accelerationDir = 1;
            }
        }

      int newSpeed = speed + accelerationDir * acceleration * frameMs/100;
        if(newSpeed > maxSpeed) {
            newSpeed = maxSpeed;
        }
        else if(newSpeed < -maxSpeed) {
            newSpeed = -maxSpeed;
        }

        Arkanoid.speed = this.speed;
        Arkanoid.newSpeed = newSpeed;
        Arkanoid.direction = accelerationDir;



        this.speed = newSpeed;

        int maxShift = position + newSpeed * frameMs;
        //collision with bat's limits
        if(maxShift < Level.getBatRightlimit()) {
            maxShift = Level.getBatRightlimit();
            this.speed = 0;
        }
        else if(maxShift > Level.getBatLeftlimit()) {
            maxShift = Level.getBatLeftlimit();
            this.speed = 0;
        }

        Arkanoid.position = this.position;
        Arkanoid.shiftvector = maxShift;

        this.position = maxShift;
*/
        this.position = finalVectorUnProc - this.batWidth/2;
      return position ;
    }



}
