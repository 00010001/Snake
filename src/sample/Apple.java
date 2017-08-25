package sample;

import java.util.Random;

class Apple {
    int x;
    int y;
    int size = 10;

//    int upperBound =
//    int lowerBound;

    Apple(){
        Random rg = new Random();
        x = rg.nextInt((int)Controller.canvasWidth/10) * 10;
        y = rg.nextInt(40) * 10;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
