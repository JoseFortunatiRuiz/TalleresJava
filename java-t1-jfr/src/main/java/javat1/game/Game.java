package javat1.game;

import lombok.Data;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Clase que representa el juego.
 */
@Data
public class Game {
    
    private Ball ball;
    private ArrayList<Player> players;
    private Score score;
    private int action;
    private Bounce bounce;
    private String finalMessage = "La bola ha caído por el medio";
    
    /**
     * Constructor de la clase Game.
     */
    public Game() {
        
        this.ball = new Ball();
        this.players = new ArrayList<>();
        this.score = new Score();
        this.bounce = new Bounce();
        
        players.add(new Player("Jugador 1"));
        
    }
    
    /**
     * Método para lanzar y comenzar el juego.
     */
    public void launchAndStart() {
        // Imprimir mensaje de inicio del juego
        System.out.println("---------INICIANDO EL JUEGO---------");
        System.out.println("ADVERTENCIA: Lee el archivo txt con las instrucciones del juego");
        
        // Lanzar la pelota
        ball.launch();
        
        // Inicializar variables y objetos necesarios
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        String letter1 = "a";
        String letter2 = "l";
        
        // Bucle principal del juego
        do {
            // Generar una acción aleatoria
            action = rand.nextInt(12);
            
            // Procesar la acción generada
            switch (action) {
                // Acción 0: La bola cae por el medio
                case 0:
                    System.out.println(finalMessage);
                    break;
                // Acciones 1-4: La bola cae en el gatillo izquierdo
                case 1, 2, 3, 4:
                    System.out.println("¡Ha caído en el gatillo izquierdo!");
                    // Esperar la entrada del usuario y procesarla
                    if (!waitForUserInput(scanner, executor, letter1)) {
                        // Si la entrada no coincide con la esperada, terminar el juego
                        action = 0;
                        System.out.println(finalMessage);
                    } else {
                        // Si la entrada coincide, añadir puntos y continuar el juego
                        score.addPoints(bounce.getPoints());
                        System.out.println("La bola ha vuelto a ser lanzada!!");
                    }
                    break;
                // Acciones 5-8: La bola cae en el gatillo derecho
                case 5, 6, 7, 8:
                    System.out.println("¡Ha caído en el gatillo derecho!");
                    // Esperar la entrada del usuario y procesarla
                    if (!waitForUserInput(scanner, executor, letter2)) {
                        // Si la entrada no coincide con la esperada, terminar el juego
                        action = 0;
                        System.out.println(finalMessage);
                    } else {
                        // Si la entrada coincide, añadir puntos y continuar el juego
                        score.addPoints(bounce.getPoints());
                        System.out.println("La bola ha vuelto a ser lanzada");
                    }
                    break;
                // Acción 9: Se invierten los controles
                case 9:
                	System.out.println("CUIDADO!! Se han invertido los controles!!");
                	// Cambiar las letras con las que se juegan utilizando una variable temporal
                	String tempLetter;
                	tempLetter = letter1;
                	letter1 = letter2;
                	letter2 = tempLetter;
                	break;
                case 10:
                	System.out.println("Has entrado en un agujero negro!!! Se te duplican los puntos!!!");
                	score.duplicatePoints(bounce.getPoints());
                	break;
                case 11:
                	System.out.println("Ha aparecido el jefe alienígena!!");

                	System.out.println("¿Has comprado alguna vez un MAC?");
                	String respuesta = scanner.nextLine();

                	if (respuesta.equalsIgnoreCase("si")) {
                	    System.out.println("Ahora podrás trabajar mucho mejor BUAJAJAJAJA!!!");
                	    System.out.println("Se te duplican los puntos!!!");
                    	score.duplicatePoints(bounce.getPoints());            	    
                	} else if (respuesta.equalsIgnoreCase("no")) {
                	    System.out.println("¡No tienes suficiente poder de procesamiento, muereee");
                	    score.subtractPoints(bounce.getPoints());
                	} else {
                	    System.out.println("No has introducido una respuesta válida. No podrás eliminar el jefe");
                	}
                	break;
                default:
                    break;
            }
        } while (action != 0); // Continuar el juego hasta que la acción sea 0
        
        // Imprimir la puntuación final del jugador
        System.out.println("Puntuación final de " + players.get(0).getName() + ": " + score.getTotalPoints());
        
        // Cerrar el executor y el scanner
        executor.shutdown();
        scanner.close();
    }

    /**
     * Método auxiliar que espera la entrada del usuario.
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * @param executor El servicio de ejecución de subprocesos para manejar la entrada del usuario.
     * @param expectedInput La entrada esperada del usuario.
     * @return true si la entrada del usuario coincide con la esperada, de lo contrario false.
     */
    private static boolean waitForUserInput(Scanner scanner, ExecutorService executor, String expectedInput) {
        Future<String> future = executor.submit(() -> scanner.nextLine());
        try {
            String userInput = future.get(7, TimeUnit.SECONDS); //Obtener la respuesta en 7 segundos
            return expectedInput.equals(userInput);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            return false;
        }
    }
        
}