package com.arkanoid.client;

import com.arkanoid.client.gameObj.GameLogic;
import com.arkanoid.client.gameObj.Level;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.canvas.client.Canvas;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Arkanoid implements EntryPoint {


    /**
     * This is the entry point method.
     */
    private int keyBordSensetivity = 10;
    public static int SpaceWidth = 800;
    public static int SpaceHeight = 800;
    public static int mposx = 0;
    public static GameLogic gameLogic;

    private boolean playmusic = false;

    public static int speed = 0;
    public static int newSpeed = 0;
    public static int direction = 0;
    public static int position = 0;
    public static int shiftvector = 0;

    public static Audio prePlay;
    public static Audio gamePlay;
    public static Audio gameOver;

    public static Audio batHit;
    public static Audio crack;
    public static Audio brake;
    public static Audio lostlife;
    public static Audio wallHit;


    void startNewGame(){
        gameLogic.ball.hasStarted = true;
        gameLogic.newGameStarted = true;
    }
    public void onModuleLoad() {

        prePlay = Audio.createIfSupported();
        prePlay.getAudioElement().setSrc("sounds/prePlay.wav");
        prePlay.setVolume(0.3);
        prePlay.setLoop(true);
        prePlay.play();
        RootPanel.get("prePlay").add(prePlay);

        gamePlay = Audio.createIfSupported();
        gamePlay.getAudioElement().setSrc("sounds/gamePlay.wav");
        gamePlay.setVolume(0.3);
        gamePlay.setLoop(true);
        RootPanel.get("gamePlay").add(gamePlay);

        gameOver = Audio.createIfSupported();
        gameOver.getAudioElement().setSrc("sounds/gameOver.wav");
        gameOver.setVolume(0.3);
        gameOver.setLoop(true);
        RootPanel.get("gameOver").add(gameOver);

        batHit = Audio.createIfSupported();
        batHit.getAudioElement().setSrc("sounds/batHit.wav");
        RootPanel.get("batHit").add(batHit);

        crack = Audio.createIfSupported();
        crack.getAudioElement().setSrc("sounds/crack.wav");
        RootPanel.get("crack").add(crack);

        brake = Audio.createIfSupported();
        brake.getAudioElement().setSrc("sounds/brake.wav");
        RootPanel.get("brake").add(brake);

        lostlife = Audio.createIfSupported();
        lostlife.getAudioElement().setSrc("sounds/lostlife.wav");
        RootPanel.get("lostlife").add(lostlife);

        wallHit = Audio.createIfSupported();
        wallHit.getAudioElement().setSrc("sounds/wallHit.wav");
        RootPanel.get("wallHit").add(wallHit);




        final Canvas canvas = Canvas.createIfSupported();
        final Label label = new Label();
        label.setText("Hello, World!");
        RootPanel.get("slot1").add(label);

        final Label label2 = new Label();
        label2.setText("Hello, World!");
        RootPanel.get("slot2").add(label);

        canvas.setCoordinateSpaceWidth(SpaceWidth);
        canvas.setCoordinateSpaceHeight(SpaceHeight);


        canvas.setWidth("800px");
        canvas.setHeight("800px");
        gameLogic = new GameLogic();
        FocusPanel focusPanel = new FocusPanel();
        focusPanel.add(canvas);
        focusPanel.setFocus(true);


        RootPanel.get("focus").add(focusPanel);


        focusPanel.addKeyDownHandler(new KeyDownHandler() {
            //@Override
            public void onKeyDown(KeyDownEvent event) {
                int key = event.getNativeKeyCode();
                if (key == KeyCodes.KEY_LEFT) {
                    mposx -= keyBordSensetivity;
                    if(mposx < 0) {
                        mposx = 0;
                    }
                } else if (key == KeyCodes.KEY_RIGHT) {
                    mposx += keyBordSensetivity;
                    if(mposx > SpaceWidth - gameLogic.bat.getBatWidth()) {
                        mposx = SpaceWidth - gameLogic.bat.getBatWidth();
                    }
                }else if (key == KeyCodes.KEY_SPACE) {
                    startNewGame();

                }else if(key == KeyCodes.KEY_UP) {
                    if(gameLogic.ball.difficulty< 10 && !gameLogic.newGameStarted)
                        gameLogic.ball.difficulty++;
                }else if(key == KeyCodes.KEY_DOWN) {
                    if(gameLogic.ball.difficulty> 2 && !gameLogic.newGameStarted)
                        gameLogic.ball.difficulty--;
                }

            }
        });

        canvas.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if(event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
                    startNewGame();
                }
                startNewGame();

            }
        });

        canvas.addMouseMoveHandler(new MouseMoveHandler() {

            @Override
            public void onMouseMove(MouseMoveEvent event) {

                focusPanel.setFocus(true);
                mposx = event.getX();


            }
        });

        Timer timer = new Timer() {
            @Override
            public void run() {

                if(playmusic = false){
                    prePlay.play();
                    playmusic = true;
                }
                gameLogic.mainGameLoop(canvas.getContext2d(), mposx);
                RootPanel.get("canvas").add(canvas);
                label.setText(mposx + " timer");
                RootPanel.get("slot1").add(label);

                if(gameLogic.ball.hasStarted)
                    label2.setText(" " + gameLogic.time);
                    //label2.setText("    X rad" + Math.cos(Math.toRadians(gameLogic.ball.speedVector)) + "    Y rad" + Math.sin(Math.toRadians(gameLogic.ball.speedVector)));
                RootPanel.get("slot2").add(label2);
            }
        };
        timer.scheduleRepeating(Level.getFrameMs());



    }

}
