//package sample;
//
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//import javafx.scene.paint.Color;
//
///**
// * Created by RENT on 2017-08-23.
// */
//public class GameThread implements Runnable {
//
//    GraphicsContext g;
//    Snake snake;
//
//    public GameThread(GraphicsContext g, Snake snake){
//        this.g = g;
//        this.snake = snake;
//    }
//
//    @Override
//    public void run() {
//
//            areWeStillInAGame = true;
//
//            while (areWeStillInAGame) {
//                // checkForCollisions();
//                renderBackground();
//
//                snake.setDirection(direction);
//                snake.update();
//                snake.render(g);
//
//                appleManager.render(g);
//
//                checkForCollisions();
//
//                try {
//                    Thread.sleep(60);
//                    //   System.out.println("Petla dziala");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            //   g.setFill(new Color(1, 0.0078, 0.1529, 1));
//            System.out.println("after GameThread Over");
//            Image go = new Image("file:go.png");
//            g.drawImage(go,0,0);
//            //  g.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
//
//
//    }
//
//    private void renderBackground() {
//        //render background and przy okazji clean screen
//        g.setFill(new Color(0, 0, 0, 1));
//        g.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
//    }
//}
