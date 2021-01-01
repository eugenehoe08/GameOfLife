package life;

import life.controller.GameController;
import life.model.GameOfLifeModel;

import javax.swing.*;
import java.awt.*;

//GUI of the game
public class GameOfLife extends JFrame {
    int generation = 0;
    private JLabel generationLabel;
    private JLabel aliveLabel;
    private JSlider gameSpeed;
    private JPanel[][] gameCells;
    GameController gameController;
    boolean paused;
    boolean reset;
    Color color = Color.BLACK;
    public GameOfLife() throws HeadlessException {
        super("Game of Life");
        paused = false;
        reset = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        initGame();
        setVisible(true);
    }


    private void initGame() {
        information();
        buttons();
        board();
    }

    private void information() {
        JPanel information = new JPanel();
        information.setLayout(new BoxLayout(information, BoxLayout.Y_AXIS));
        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setText("Generation #" + getGeneration());
        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setText("Alive: ");
        information.add(generationLabel);
        information.add(aliveLabel);
        add(information, BorderLayout.NORTH);
    }

    //Buttons and sliders used to control the game
    private void buttons() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JToggleButton play = new JToggleButton("Pause");
        play.setName("PlayToggleButton");
        play.addActionListener(e -> {
            if (isPaused()) {
                paused = false;
                play.setText("Pause");
            } else {
                paused = true;
                play.setText("Play");
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.addActionListener(e -> {
            reset = true;
        });

        gameSpeed = new JSlider(0, 2000, 1000);
        gameSpeed.setMinorTickSpacing(100);
        gameSpeed.setMajorTickSpacing(500);
        gameSpeed.setPaintTicks(true);
        gameSpeed.setPaintLabels(true);
        gameSpeed.setPaintTrack(true);

        String[] colors = new String[5];
        colors[0] = "Blue";
        colors[1] = "Magenta";
        colors[2] = "Green";
        colors[3] = "Yellow";
        colors[4] = "Orange";
        JComboBox colorBox = new JComboBox(colors);
        colorBox.resize(300, 300);
        colorBox.addItemListener(e -> {
            color = stringtoColor((String) colorBox.getSelectedItem());
            repaint();
        });



        buttons.add(play);
        buttons.add(resetButton);
        buttons.add(gameSpeed);
        buttons.add(colorBox);
        add(buttons, BorderLayout.WEST);
    }

    private void board() {
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(10, 10));
        gameCells = new JPanel[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel cell = new JPanel();
                cell.setBackground(Color.black);
                board.add(cell);
                gameCells[i][j] = cell;
            }
        }
        add(board, BorderLayout.CENTER);
    }

    public void updateBoard(char[][] board,int generation, int alive) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameCells[i][j].setBackground(board[i][j] == 'O' ? color : Color.gray);
            }
        }

        generationLabel.setText("Generation #" + generation);
        aliveLabel.setText("Alive: " + alive);
    }


    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public JSlider getGameSpeed() {
        return gameSpeed;
    }

    private Color stringtoColor(String color) {
        switch (color) {
            case "Blue":
                return Color.BLUE;
            case "Magenta":
                return Color.MAGENTA;
            case "Green":
                return Color.GREEN;
            case "Yellow":
                return Color.YELLOW;
            case "Orange":
                return Color.ORANGE;
            default:
                return Color.BLACK;
        }
    }
}
