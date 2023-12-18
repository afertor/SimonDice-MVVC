package com.dam.simondice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimonDiceGame {
    private List<Integer> pattern;
    private int currentIndex;

    public SimonDiceGame() {
        pattern = new ArrayList<>();
        currentIndex = 0;
    }

    public List<Integer> getPattern() {
        return pattern;
    }

    public void addToPattern(int number) {
        pattern.add(number);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public boolean checkPlayerInput(int input) {
        if (currentIndex < pattern.size()) {
            if (pattern.get(currentIndex) == input) {
                currentIndex++;
                return true;
            }
        }
        return false;
    }

    public void resetGame() {
        pattern.clear();
        currentIndex = 0;
    }

    public int generateRandomNumber() {
        return new Random().nextInt(4); // 4 colores posibles (0-3)
    }
}
