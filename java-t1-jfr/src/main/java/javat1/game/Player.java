package javat1.game;

import lombok.Data;

/**
 * Clase que representa a un jugador.
 */
@Data
public class Player {
    private String name;

    /**
     * Constructor de la clase Player.
     * @param name El nombre del jugador.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Método para obtener el nombre del jugador.
     * @return El nombre del jugador.
     */
    public String getName() {
        return name;
    }
}
