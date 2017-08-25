package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-08-23.
 */
public class AppleManager {
    List<Apple> appleList;
    int size = Controller.GRIDSIZE;

    public AppleManager(){
        appleList = new ArrayList<>();
    }


    public void render(GraphicsContext g){
        g.setFill(new Color(0.0118, 0.9608, 1, 1));

        for (Apple apple : appleList) {
            g.fillRect(apple.x, apple.y, size, size);
        }

    }

    public void generateNewApple(){
        appleList.add(new Apple());
    }
}
