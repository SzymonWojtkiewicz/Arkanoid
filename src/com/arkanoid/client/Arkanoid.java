package com.arkanoid.client;

import com.arkanoid.client.gameObj.GameLogic;
import com.arkanoid.client.gameObj.Level;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
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

    public static int speed = 0;
    public static int newSpeed = 0;
    public static int direction = 0;
    public static int position = 0;
    public static int shiftvector = 0;

    void startNewGame(){
        gameLogic.ball.hasStarted = true;
    }
    public void onModuleLoad() {

        /*
        final Button button = new Button("Click me");
        final Label label = new Label();

        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (label.getText().equals("")) {
                    ArkanoidService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
                } else {
                    label.setText("");
                }
            }
        });

        // Assume that the host HTML has elements defined whose
        // IDs are "slot1", "slot2".  In a real app, you probably would not want
        // to hard-code IDs.  Instead, you could, for example, search for all
        // elements with a particular CSS class and replace them with widgets.
        //
        //RootPanel.get("slot1").add(button);
        //RootPanel.get("slot2").add(label);

         */
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
        //focusPanel.setSize("800px", "800px");
        focusPanel.add(canvas);
        focusPanel.setFocus(true);

        //RootPanel.get("canvas").add(canvas);
        RootPanel.get("focus").add(focusPanel);
        //

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
                }
                //startNewGame();
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

                //startNewGame();
                focusPanel.setFocus(true);
                mposx = event.getX();

              //  label.setText(mposx + " move");
              //  RootPanel.get("slot1").add(label);
               // label.setText(gameLogic.level.bricks.size() + "");
               // RootPanel.get("slot1").add(label);
            }
        });

        Timer timer = new Timer() {
            @Override
            public void run() {

                gameLogic.mainGameLoop(canvas.getContext2d(), mposx);
                RootPanel.get("canvas").add(canvas);
                label.setText(mposx + " timer");
                RootPanel.get("slot1").add(label);
                //label2.setText("Speed: " + speed + "\nNewSpeed " + newSpeed +"\n Direction: " + direction + " \nPosition: " + position + " \nShiftvector: " + shiftvector);
                //label2.setText("    X rad" + Math.cos(Math.toRadians(gameLogic.ball.speedVector)) + "    Y rad" + Math.sin(Math.toRadians(gameLogic.ball.speedVector)));
                if(gameLogic.ball.hasStarted)
                    label2.setText("    X rad" + Math.cos(Math.toRadians(gameLogic.ball.speedVector)) + "    Y rad" + Math.sin(Math.toRadians(gameLogic.ball.speedVector)));
                RootPanel.get("slot2").add(label2);
            }
        };
        timer.scheduleRepeating(Level.getFrameMs());







       // while(true){

            //Context2d context = canvas.getContext2d();

        //}

    //   Context2d context = canvas.getContext2d();
    //   Image image = new Image("Untitled.jpg");
    //   final ImageElement imageElement = ImageElement.as(image.getElement());
        /*image.addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
                context.drawImage(imageElement, 0, 0);
            }
        });*/
     //    context.drawImage(imageElement, 0, 0);
        //RootPanel.get("canvas").add(image);
    //    RootPanel.get("canvas").add(canvas);



    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
