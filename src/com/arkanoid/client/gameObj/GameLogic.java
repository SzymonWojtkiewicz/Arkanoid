package com.arkanoid.client.gameObj;

import com.arkanoid.client.Arkanoid;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;

public class GameLogic {
    public Bat bat;
    public Ball ball;
    public Level level;
    private int score = 0;
    private int maxtime = 120000; // ms
    public int lives = 3;
    public int time = 0;
    public boolean newGameStarted = false;



    Image background = new Image("images/background.jpg");
    Image batImage = new Image("images/bat.jpg");
    Image ballImage = new Image("images/Ball.svg");

    Image heartImage = new Image("images/heart.png");

    Image[] numbersImage = {new Image("images/numbers/0.svg"), new Image("images/numbers/1.svg"), new Image("images/numbers/2.svg"), new Image("images/numbers/3.svg"), new Image("images/numbers/4.svg"), new Image("images/numbers/5.svg"), new Image("images/numbers/6.svg"), new Image("images/numbers/7.svg"), new Image("images/numbers/8.svg"), new Image("images/numbers/9.svg"), new Image("images/numbers/sec.svg")};

    String[] brickImagesColor = new String[3];

    String YBrickImage ="images/Bricks/YBricks/";
    String BBrickImage = "images/Bricks/BBricks/";
    String RBrickImage = "images/Bricks/RBricks/";
    Image gameoverImage = new Image("images/GameOver.jpg");

    Image[][] brickImages = {{new Image(YBrickImage + "1Y.png")},
            {new Image(BBrickImage + "1B.png"), new Image(BBrickImage + "2B.png")},
            {new Image(RBrickImage + "1R.png"), new Image(RBrickImage + "2R.png"), new Image(RBrickImage + "3R.png")}};



    public GameLogic() {
        bat = new Bat();
        ball = new Ball();
        level = new Level(44);

        //creating brick images array dynamically
        //dose not work for some reason

        /*
        //brickImages = new Image[brickImagesColor.length][];

        brickImagesColor[0] = YBrickImage;
        brickImagesColor[1] = BBrickImage;
        brickImagesColor[2] = RBrickImage;

        /*
        for(int i = 0; i < brickImagesColor.length; i++) {
            brickImages[i] = new Image[i + 1];
            for(int j = 0; j < i + 1; j++) {
                brickImages[i][j] = new Image(brickImagesColor[i] + (j+1) + ".png");
            }
        }
        */


    }

    public Context2d mainGameLoop(Context2d context, int finalVectorUnProc) {

        //sounds
        if(!newGameStarted){
            Arkanoid.prePlay.play();
            Arkanoid.gamePlay.pause();
            Arkanoid.gameOver.pause();
        }
        if(newGameStarted){
            Arkanoid.prePlay.pause();
            Arkanoid.gamePlay.play();
            Arkanoid.gameOver.pause();
        }
        if(lives == 0 || this.time >= maxtime){
            Arkanoid.gamePlay.pause();
            Arkanoid.prePlay.pause();
            Arkanoid.gameOver.play();
        }


        //preperation

        if(ball.hasStarted && !ball.hasEnded) {
            this.time += level.getFrameMs();
        }
        if(ball.hasEnded)
            ball = new Ball();

        context.clearRect(0, 0, Arkanoid.SpaceWidth, Arkanoid.SpaceHeight);
        //draw background

        context.drawImage(ImageElement.as(background.getElement()), 0, 0);


        //draw bat



        if(lives > 0)
            bat.move(finalVectorUnProc);
        context.drawImage(ImageElement.as(batImage.getElement()), bat.getPosition(), Arkanoid.SpaceHeight - bat.getBatHeight(), bat.getBatWidth(), bat.getBatHeight());

        //draw bricks

        for (Brick brick : level.bricks) {
            if(brick.hp == 0) {
                continue;
            }
            context.drawImage(ImageElement.as(ImageElement.as(brickImages[brick.color - 1][brick.hp - 1].getElement())), brick.getBrickX(), brick.getBrickY(), brick.getBrickWidth(), brick.getBrickHeight());
       }


        //draw ball
        context.drawImage(ImageElement.as(ballImage.getElement()), ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        if(lives > 0){
            if(!ball.move())
                 lives--;
        }

        //draw difficulty
        context.drawImage(ImageElement.as(numbersImage[ball.difficulty].getElement()), Arkanoid.SpaceWidth/2 , Arkanoid.SpaceHeight/20, Arkanoid.SpaceWidth/20, Arkanoid.SpaceHeight/20);

        //draw lives
        for(int i = 0; i < lives; i++) {
            context.drawImage(ImageElement.as(heartImage.getElement()), Arkanoid.SpaceWidth/20 + i * Arkanoid.SpaceWidth/20 + 5, Arkanoid.SpaceHeight/20, Arkanoid.SpaceWidth/20, Arkanoid.SpaceHeight/20);
        }
        //draw timer

        int tempTime = this.time/1000;

        int[] timeInNumbers = {tempTime/100 % 10, tempTime/10 % 10, tempTime % 10};

        for(int i = 0; i <3; i++) {
            context.drawImage(ImageElement.as(numbersImage[timeInNumbers[i]].getElement()),(16 * Arkanoid.SpaceWidth)/20 + i * Arkanoid.SpaceWidth/40, Arkanoid.SpaceHeight/20, Arkanoid.SpaceWidth/20, Arkanoid.SpaceHeight/20);
        }
        context.drawImage(ImageElement.as(numbersImage[10].getElement()), (16 * Arkanoid.SpaceWidth)/20 + 3 * Arkanoid.SpaceWidth/40, Arkanoid.SpaceHeight/20, Arkanoid.SpaceWidth/20, Arkanoid.SpaceHeight/20);

        if(lives == 0 || this.time >= maxtime) {
            context.drawImage(ImageElement.as(gameoverImage.getElement()), Arkanoid.SpaceWidth/5 , 2 * Arkanoid.SpaceHeight/5, 3 * Arkanoid.SpaceWidth/5, 1 * Arkanoid.SpaceHeight/5);
        }

        return context;


    }

}
