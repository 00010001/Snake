package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by RENT on 2017-08-23.
 */

public class Snake {

    List<SnakePart> snakeParts;

    char direction;
    boolean addNewSnakePart;
    int segmentSize = 10;


   static double red = 1;
    static double green = 1;
    static double blue = 0.2;
    static double opacity = 0.5;

    public Snake() {
        snakeParts = new ArrayList<>();
        SnakePart head = new SnakePart(200, 200, snakeParts.size());
        snakeParts.add(head);
    }

    public SnakePart getHead() {
        return snakeParts.get(0);
    }

    public void render(GraphicsContext graphicsContext) {
        for (int i = 0; i < snakeParts.size(); i++) {
            changeColors();
            snakeParts.get(i).render(graphicsContext);
        }
    }

    private void changeColors() {
        Random rg = new Random();
       // red = rg.nextDouble();
      //  green = rg.nextDouble();
        blue = rg.nextDouble();

        opacity = rg.nextDouble();

    }

    public void update() {
        moveSegments();
        checkCollisionsWithApples();
        checkCollisionsWithItself();
    }

    private void checkCollisionsWithItself() {
        SnakePart head = snakeParts.get(0);

        for (SnakePart snakePart : snakeParts) {
            if (snakePart.getX() == head.x && snakePart.getY() == head.y && snakePart.position != 0) {
                System.out.println("intersects");
                Controller.areWeStillInAGame = false;
            }
        }
    }

    private void checkCollisionsWithApples() {
        SnakePart head = snakeParts.get(0);
        Apple appleToRemove = null;
        for (Apple apple : Controller.appleManager.appleList) {
            if (apple.getX() == head.x && apple.getY() == head.y) {
                addNewSnakePart = true;
                appleToRemove = apple;
                Controller.modifyScore();
            }
        }
        if (addNewSnakePart) {
            Controller.appleManager.appleList.remove(appleToRemove);
            Controller.appleManager.generateNewApple();
        }
    }

    private void moveSegments() {

        SnakePart head = snakeParts.get(0);
        SnakePart tmp = new SnakePart(head.x, head.y, snakeParts.size());
        SnakePart tmp2 = new SnakePart(head.x, head.y, snakeParts.size());

        switch (direction) {
            case 'd':
                head.y += segmentSize;
                break;
            case 'u':
                head.y -= segmentSize;
                break;
            case 'l':
                head.x -= segmentSize;
                break;
            case 'r':
                head.x += segmentSize;
                break;
            default:
                head.x = head.x;
                head.y = head.y;
                break;
        }

        for (int i = 1; i < snakeParts.size(); i++) {
            tmp2 = new SnakePart(snakeParts.get(i).x, snakeParts.get(i).y);
            snakeParts.get(i).x = tmp.x;
            snakeParts.get(i).y = tmp.y;
            tmp = tmp2;
        }
        if (addNewSnakePart) {
            snakeParts.add(new SnakePart(tmp.x, tmp.y, snakeParts.size()));
            addNewSnakePart = false;
        }

    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}
