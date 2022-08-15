package com.arkanoid.client.gameObj;

public class Brick {
    private int brickWidth;
    private int brickHeight;
    private int brickX = 0;
    private int brickY = 0;
    public int hp = 1;

    public Brick() {
    }
    public Brick(int brickX, int brickY, int hp, int brickWidth, int brickHeight) {
        this.brickX = brickX;
        this.brickY = brickY;
        this.hp = hp;
        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
    }

    public int getBrickWidth() {
        return brickWidth;
    }
    public int getBrickHeight() {
        return brickHeight;
    }
    public int getBrickX() {
        return brickX;
    }
    public int getBrickY() {
        return brickY;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }


}
