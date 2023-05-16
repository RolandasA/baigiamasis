package lt.Rolandas.basketball.controller;

import java.util.ArrayList;
import java.util.Random;

public class BasketballGame {
    public static String yourMessage = "";
    public static String opponentMessage = "";
    static Random rand = new Random();
    static ArrayList<Integer> yourScore = new ArrayList<>();
    static ArrayList<Integer> opponentScore = new ArrayList<>();


    public static int yourTotalScore(ArrayList<Integer> yourScore) {
        if (yourScore.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Integer score : yourScore) {
            sum += score;
        }
        return sum;
    }

    public static int opponentsTotalScore(ArrayList<Integer> opponentScore) {
        if (opponentScore.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (Integer score : opponentScore) {
            sum += score;
        }
        return sum;
    }

    public static Object yourTwoPointShot(ArrayList<Integer> yourScore) {
        int success = rand.nextInt(1, 101);
        if (success <= 65) {
            yourMessage = "Congratulations, you scored 2 points!";
            yourScore.add(twoPointShotMade());
        } else {
            yourMessage = "You missed!";
        }
        return opponentsDesicion(opponentScore);
    }

    public static Object yourThreePointShot(ArrayList<Integer> yourScore) {
        int success = rand.nextInt(1, 101);
        if (success <= 30) {
            yourScore.add(threePointShotMade());
            yourMessage = "Congratulations, you scored 3 points!";
        } else {
            yourMessage = "You missed!";
        }
        return opponentsDesicion(opponentScore);
    }

    public static void opponentsTwoPointShot(ArrayList<Integer> opponentScore) {
        int success = rand.nextInt(1, 101);
        if (success < 65) {
            opponentScore.add(twoPointShotMade());
            opponentMessage = "Opponent scored 2 points!";
        } else {
            opponentMessage = "Opponent missed!";
        }
    }

    public static void opponentsThreePointShot(ArrayList<Integer> opponentsScore) {
        int success = rand.nextInt(1, 101);
        if (success <= 30) {
            opponentsScore.add(threePointShotMade());
            opponentMessage = "Opponent scored 3 points!";
        } else {
            opponentMessage = "Opponent missed!";
        }
    }

    public static int twoPointShotMade() {
        return 2;
    }

    public static int threePointShotMade() {
        return 3;
    }

    public static Object opponentsDesicion(ArrayList<Integer> opponentsScore) {
        int desicion = rand.nextInt(1, 4);
        if (desicion > 1) {
            opponentsTwoPointShot(opponentsScore);
        } else {
            opponentsThreePointShot(opponentsScore);
        }
        return null;
    }
}
