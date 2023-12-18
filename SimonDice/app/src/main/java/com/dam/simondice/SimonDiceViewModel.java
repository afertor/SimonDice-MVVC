package com.dam.simondice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class SimonDiceViewModel extends ViewModel {
    private SimonDiceGame game = new SimonDiceGame();
    private MutableLiveData<List<Integer>> patternLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> gameOverLiveData = new MutableLiveData<>();

    public LiveData<List<Integer>> getPatternLiveData() {
        return patternLiveData;
    }

    public LiveData<Boolean> getGameOverLiveData() {
        return gameOverLiveData;
    }

    public void startNewGame() {
        game.resetGame();
        game.addToPattern(game.generateRandomNumber());
        patternLiveData.setValue(game.getPattern());
        gameOverLiveData.setValue(false);
    }

    public void playerInput(int input) {
        if (game.checkPlayerInput(input)) {
            if (game.getPattern().size() == game.getCurrentIndex()) {
                // El jugador completó la secuencia actual correctamente
                game.addToPattern(game.generateRandomNumber());
                patternLiveData.setValue(game.getPattern());
            }
        } else {
            // El jugador cometió un error
            gameOverLiveData.setValue(true);
        }
    }

}
