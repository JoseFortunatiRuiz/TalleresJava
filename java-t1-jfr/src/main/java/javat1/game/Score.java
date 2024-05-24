package javat1.game;

import lombok.Data;

@Data
public class Score {
    private int totalPoints;

    public Score() {
        this.totalPoints = 0;
    }

    public void addPoints(int points) {
        this.totalPoints += points;
        System.out.println("Ganaste " + points + " puntos.");
    }

    public void subtractPoints(int points) {
        this.totalPoints -= points;
        System.out.println("Perdiste " + points + " puntos.");
    }

    public int getTotalPoints() {
        return totalPoints;
    }
    
    public void duplicatePoints (int points) {
    	
    	this.totalPoints = totalPoints * 2;
    	
    }
}
