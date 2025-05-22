import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class PicasYFijas {

    // Método para mezclar los dígitos
    public static void mezclar(ArrayList<Integer> digitos) {
        for (int i = digitos.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Collections.swap(digitos, i, j);
        }
    }

    // Método para generar un número aleatorio de 4 dígitos sin repetirse
    public static String generarNumero() {
        ArrayList<Integer> digitos = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            digitos.add(i);
        }
        mezclar(digitos);

        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            numero.append(digitos.get(i));
        }
        return numero.toString();
    }

    // Método para calcular las picas y las fijas
    public static void calcularPicasFijas(String numeroSecreto, String intento, int[] picasFijas) {
        int picas = 0;
        int fijas = 0;

        for (int i = 0; i < 4; i++) {
            if (intento.charAt(i) == numeroSecreto.charAt(i)) {
                fijas++;
            } else if (numeroSecreto.indexOf(intento.charAt(i)) != -1) {
                picas++;
            }
        }
        picasFijas[0] = picas;
        picasFijas[1] = fijas;
    }

    // Método para verificar si hay dígitos repetidos
    public static boolean tieneDigitosRepetidos(String numero) {
        for (int i = 0; i < numero.length(); i++) {
            for (int j = i + 1; j < numero.length(); j++) {
                if (numero.charAt(i) == numero.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String numeroSecreto = generarNumero();
        String intento;
        int picas, fijas;
        int intentos = 0;

        System.out.println("Bienvenido a Picas y Fijas");
        System.out.println("Debes adivinar un número de 4 dígitos sin repetir.");

        fijas = 0;

        do {
            System.out.print("Ingresa tu intento: ");
            try {
                intento = br.readLine();
            } catch (IOException e) {
                System.out.println("Error al leer la entrada. Intenta de nuevo.");
                continue;
            }

            if (intento == null || intento.length() != 4 || tieneDigitosRepetidos(intento) || !intento.matches("\\d{4}")) {
                System.out.println("Debes ingresar un número de 4 dígitos sin repetir.");
                continue;
            }

            intentos++;

            int[] picasFijas = new int[2]; // [picas, fijas]
            calcularPicasFijas(numeroSecreto, intento, picasFijas);
            picas = picasFijas[0];
            fijas = picasFijas[1];

            System.out.println("Fijas: " + fijas + ", Picas: " + picas);

        } while (fijas < 4);

        System.out.println("¡Felicidades, adivinaste el número " + numeroSecreto + " en " + intentos + " intentos!");
    }
}

