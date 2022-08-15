package com.arkanoid.client.gameObj;

import com.arkanoid.client.Arkanoid;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;

import java.util.List;
import java.util.ListIterator;

public class GameLogic {
    public Bat bat;
    public Ball ball;
    public Level level;
    private int score = 0;
    private int maxtime = 120000; // ms
    private int time = 0;

    Image background = new Image("images/background.jpg");
    Image batImage = new Image("images/bat.jpg");
    Image ballImage = new Image("images/Ball.svg");

    Image[] brickImages = new Image[3];

    Image YBrickImage = new Image("images/YBrick.png");
    Image BBrickImage = new Image("images/BBrick.png");
    Image RBrickImage = new Image("images/RBrick.png");



    public GameLogic() {
        bat = new Bat();
        ball = new Ball();
        level = new Level(44);
        brickImages[0] = YBrickImage;
        brickImages[1] = BBrickImage;
        brickImages[2] = RBrickImage;
    }

    public Context2d mainGameLoop(Context2d context, int finalVectorUnProc) {
        //preperation
        context.clearRect(0, 0, Arkanoid.SpaceWidth, Arkanoid.SpaceHeight);
        //draw background

        //context.drawImage(ImageElement.as(background.getElement()), 0, 0);

        //draw bat


        //ImageElement batImageElement = ImageElement.as(batImage.getElement());
        bat.move(finalVectorUnProc);
        context.drawImage(ImageElement.as(batImage.getElement()), bat.getPosition(), Arkanoid.SpaceHeight - bat.getBatHeight(), bat.getBatWidth(), bat.getBatHeight());
        //context.drawImage(batImageElement, 0, 0);
        //draw bricks
//int i = 0;
//int j = 0;
        for (Brick brick : level.bricks) {
            context.drawImage(ImageElement.as(ImageElement.as(brickImages[brick.hp - 1].getElement())), brick.getBrickX(), brick.getBrickY(), brick.getBrickWidth(), brick.getBrickHeight());
           // context.drawImage(ImageElement.as(ImageElement.as(brickImages[brick.hp - 1].getElement())), i, j, brick.getBrickWidth(), brick.getBrickHeight());
            //i += brick.getBrickWidth();
           // if (i >= Arkanoid.SpaceWidth) {
          //      i = 0;
          //      j += brick.getBrickHeight();
          //  }
       }
       // int x = 400;
       // int y = 400;

      //  context.drawImage(ImageElement.as(ImageElement.as(brickImages[1].getElement())), 0, 0);

        //draw ball
        context.drawImage(ImageElement.as(ballImage.getElement()), ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        ball.move();


        this.time += level.getFrameMs();

        return context;


    }

}
