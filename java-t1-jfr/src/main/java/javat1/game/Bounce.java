package javat1.game;

import java.util.Random;
import lombok.Data;

/**
 * Clase que representa un rebote.
 */
@Data
public class Bounce {
    private Random rand;

    /**
     * Constructor de la clase Bounce.
     */
    public Bounce() {
        this.rand = new Random();
    }

    /**
     * Método para obtener la cantidad de puntos obtenidos en el rebote.
     * @return La cantidad de puntos obtenidos.
     */
    public int getPoints() {
        return rand.nextInt(20) + 1;
    }
}
