package javat1;

import javat1.game.Game;

/**
 * Clase principal que inicia el juego.
 */
public class T1MainJFR {
    
    /**
     * Método principal que crea una instancia del juego y lo inicia.
     */
    public static void main(String[] args) {
        // Crear una instancia del juego
        Game game = new Game();
        // Iniciar el juego
        game.launchAndStart();
    }

}

