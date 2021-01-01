package life.view;


import life.GameOfLife;
import life.controller.GameController;
import life.model.GameOfLifeModel;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController(new GameOfLifeModel(10), new GameOfLife());
        gameController.play();
    }
}
