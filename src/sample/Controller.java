package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Controller {
    @FXML
    Canvas mainCanvas;
    @FXML
    Button u;
    @FXML
    Button d;
    @FXML
    Button l;
    @FXML
    Button r;
    @FXML
    VBox root;
    @FXML
    Label score;
    @FXML
    Label highestScore;

    private GraphicsContext g;
    private char direction;
    private static int scoreValue = 0;

    static final int GRIDSIZE = 8;

    public static double canvasWidth;
    public static double canvasHeight;
    private static Snake snake;
    static AppleManager appleManager;


    private int speed = 50;
    static boolean areWeStillInAGame;


    private Thread thread;

    public void initialize() {
        canvasWidth = mainCanvas.getWidth();
        canvasHeight = mainCanvas.getHeight();


        initButtons();
        initKeys();
        snake = new Snake();
        g = mainCanvas.getGraphicsContext2D();

        appleManager = new AppleManager();
        appleManager.generateNewApple();

        scoreValue = 0;
        score.setFont(Font.font("Verdana", 20));
        highestScore.setFont(Font.font("Verdana", 20));


        thread = new Thread(() -> {
            areWeStillInAGame = true;

            while (areWeStillInAGame) {
                renderBackground();

                snake.setDirection(direction);
                snake.update();
                snake.render(g);

                appleManager.render(g);

                checkForCollisionsWithScreenBorders();

                Platform.runLater(() -> {
                    score.setText("Score: " + scoreValue);
                });

                try {
                    Thread.sleep(speed);
                    //   System.out.println("Petla dziala");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //   g.setFill(new Color(1, 0.0078, 0.1529, 1));
            compareScores();
            Image go = new Image("file:go.png");
            g.drawImage(go, 0, 0);
            //  g.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());

        });


        thread.setDaemon(true);
        thread.start();

        // score.

    }

    private void compareScores() {
        if(scoreValue > Integer.parseInt(highestScore.getText())){
            Platform.runLater(() -> {
                highestScore.setText(scoreValue+"");
            });
        }
    }

    public static void modifyScore() {
        scoreValue += 1;
    }


    private void checkForCollisionsWithScreenBorders() {
        int x = snake.getHead().x;
        int y = snake.getHead().y;
        if (x > mainCanvas.getWidth() || x < 0) {
            areWeStillInAGame = false;
            System.out.println("GAME OVER");
        }
        if (y > mainCanvas.getHeight() || y < 0) {
            System.out.println("GAME OVER");
            areWeStillInAGame = false;
        }
    }

    private void renderBackground() {
        //render background and przy okazji clean screen
        g.setFill(new Color(0, 0, 0, 1));
        g.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
    }

    private void initButtons() {
        u.setOnAction(event -> {
            direction = 'u';
        });
        d.setOnAction(event -> {
            direction = 'd';
        });
        l.setOnAction(event -> {
            direction = 'l';
        });
        r.setOnAction(event -> {
            direction = 'r';
        });
    }

    private void initKeys() {
        root.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                if (direction != 'r')
                    direction = 'l';
            }
            if (e.getCode() == KeyCode.D) {
                if (direction != 'l')
                    direction = 'r';
            }
            if (e.getCode() == KeyCode.W) {
                if (direction != 'd')
                    direction = 'u';
            }
            if (e.getCode() == KeyCode.S) {
                if (direction != 'u')
                    direction = 'd';
            }
            if (e.getCode() == KeyCode.M) {
                System.out.println(speed);
                speed = speed + 10;
            }
            if (e.getCode() == KeyCode.N) {
                if(speed > 21){
                    System.out.println(speed);
                    speed = speed - 10;

                }

            }
            if (e.getCode() == KeyCode.X) {
                snake.addNewSnakePart = true;
            }
            if (e.getCode() == KeyCode.P) {
                this.initialize();
            }
        });
    }
}
