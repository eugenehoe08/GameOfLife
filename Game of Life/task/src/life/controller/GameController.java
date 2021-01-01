package life.controller;

import life.model.GameOfLifeModel;
import life.GameOfLife;

public class GameController {
    private GameOfLifeModel model;
    private GameOfLife view;


    public GameController(GameOfLifeModel model, GameOfLife view) {
        this.model = model;
        this.view = view;

    }

    public void play() {
        while (true) {
            if (view.isReset()) {
                this.model = new GameOfLifeModel(10);
                view.setReset(false);
                continue;
            }
            if (!view.isPaused()) {
                model.generateNewBoard();
                view.updateBoard(model.getUniverse(), model.getGeneration(), model.noOfAliveCellsInMatrix());
            }
            try {
                Thread.sleep(view.getGameSpeed().getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
