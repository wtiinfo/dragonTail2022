package manager;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    private final ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENU_STATE = 0;
    public static final int LEVEL_1_STATE = 1;

    public GameStateManager() {
        gameStates = new ArrayList<>();
        currentState = MENU_STATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Level1State(this));
    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int key) {
        gameStates.get(currentState).keyPressed(key);
    }

    public void keyReleased(int key) {
        gameStates.get(currentState).keyReleased(key);
    }
}
