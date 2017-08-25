package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;


/**
 * Created by RENT on 2017-08-23.
 */

public class SnakePart {
    int x;
    int y;
    int size = Controller.GRIDSIZE;
    int position;

    public SnakePart(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SnakePart(int x, int y, int position) {
        this.x = x;
        this.y = y;
        this.position = position;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void render(GraphicsContext g){
        if(position == 0){
            g.setFill(new Color(0.1098, 0.0549, 1, 1));
        } else {
            g.setFill(new Color(0, 0.0039, 0.3176, 1));

            //     g.setFill(new Color(Snake.red, Snake.green, Snake.blue, Snake.opacity));
        }

        Random r = new Random();
        int Low = -1;
        int High = 1;
        int res = r.nextInt(High-Low) + Low;
        g.fillRect(x+res, y+res, size, size);
    }
}
