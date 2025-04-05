import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JuegoAhorcado {

    // Atributos de la clase
    private static final String[] palabras = {"universo", "computadora", "papel", "lapiz", "borrador", "telefono", "teclado", "universidad", "programacion", "objeto"};
    private ArrayList<String> letrasAdivinadas;
    private int intentos;
    private ArrayList<String> yaAdivinadas;

    // Método para mostrar la palabra con las letras adivinadas
    public static String mostrarPalabra(String palabra, ArrayList<String> letrasAdivinadas) {
        StringBuilder palabraMostrada = new StringBuilder();
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            if (letrasAdivinadas.contains(String.valueOf(letra))) {
                palabraMostrada.append(letra);
            } else {
                palabraMostrada.append("_");
            }
        }
        return palabraMostrada.toString();
    }

    // Método para iniciar el juego
    public void juegoAhorcado() {
        Random random = new Random();
        String palabra = palabras[random.nextInt(palabras.length)];
        letrasAdivinadas = new ArrayList<>();
        intentos = 5;
        yaAdivinadas = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("La palabra tiene " + palabra.length() + " letras.");

        while (intentos > 0) {
            System.out.println("Palabra actual: " + mostrarPalabra(palabra, letrasAdivinadas));
            System.out.println("Tiene " + intentos + " intentos restantes.");
            System.out.print("Adivina una letra: ");
            String letra = scanner.nextLine().toLowerCase();

            if (yaAdivinadas.contains(letra)) {
                System.out.println("Ya ha adivinado esa letra.");
                continue;
            }

            yaAdivinadas.add(letra);

            if (palabra.contains(letra)) {
                letrasAdivinadas.add(letra);
            } else {
                intentos--;
                System.out.println("Incorrecto");
            }

            if (letrasAdivinadas.size() == palabra.length()) {
                System.out.println("Ha adivinado la palabra: " + palabra);
                break;
            }
        }

        if (intentos == 0) {
            System.out.println("No hay más intentos. La palabra era: " + palabra);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        JuegoAhorcado juego = new JuegoAhorcado();
        juego.juegoAhorcado();
    }
}