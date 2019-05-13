package com.company;

import java.util.Random;
import java.util.Scanner;

public class ahorcado {

    public static void main(String[] args) {
        do {
            ahorcadoJuego();
        }
        while (preguntarContinuarJugando());
    }

    ///////////////////////////////////////////////////////////////////////
    /////////////////// SUBPROGRAMAS DEBAJO //////////////////
    ///////////////////////////////////////////////


    private static void ahorcadoJuego() {
        mostrarInstrucciones();
        char[] palabraElegidaObtenida = siguientePalabra();
        char[] palabraElegidaOculta = new char[palabraElegidaObtenida.length];
        llenadorBarrasBajas(palabraElegidaOculta);
        int vidas = 6;
        boolean resultadoGanador=true;
        while (vidas > 0) {
            imprimirPalabraOculta(palabraElegidaOculta);
            System.out.println("  | Vidas: " + vidas);
            char letraTMP = pedirLetra();
            if (comprobadorPresencia(letraTMP, palabraElegidaOculta))
                System.out.println("Ya has introducido antes este valor, a ver si estamos a lo que hay que estar!   ヽ(ﾟДﾟ)ﾉ ");
            unificadorCoincidencias(buscadorCoincidencia(palabraElegidaObtenida, letraTMP), palabraElegidaOculta);
            if (!comprobadorPresencia(letraTMP, palabraElegidaObtenida)) vidas--;
            mostrarBarra();
            System.out.println();
            if (palabraEstaCompleta(palabraElegidaOculta)) {
                finJuego(palabraElegidaOculta, palabraElegidaObtenida, vidas,resultadoGanador);
                System.out.println();
                break;
            }
        }
        if (vidas == 0) {
            resultadoGanador=false;
            finJuego(palabraElegidaOculta, palabraElegidaObtenida, vidas,resultadoGanador);
            System.out.println();
        }
    }



    public static void mostrarInstrucciones() {
        System.out.println(" ____________________________________");
        System.out.println("|   AHORCADO - Tajo Valley Edition   |");
        System.out.println("|====================================|____________________________________________________________");
        System.out.println("|                                                                                  ____           |");
        System.out.println("|   Este juego consiste en adivinar la palabra oculta paso a paso                 |    |          |");
        System.out.println("|   introduciendo las letras que crees la componen e intentando salvar           (_)   |          |");
        System.out.println("|   al prisionero del patibulo. Para ello, utiliza el ingenio y trata                  |          |");
        System.out.println("|   de elegir bien o poco a poco perdera vidas y acabara ahorcado.             ________|____      |");
        System.out.println("|                                                                             |-------------|     |");
        System.out.println("|   Buena suerte!!                                                                                |");
        System.out.println("|_________________________________________________________________________________________________|");
        System.out.println();
    }

    public static char pedirLetra() {
        System.out.print("Al lio, introduce una letra a comprobar: ");
        Scanner letra = new Scanner(System.in);
        char letraIntroducida = letra.next().charAt(0);
        if (esNumero(letraIntroducida))
            System.out.println("Si, muy bien, genial, jugando al ahorcado vas e introduces un numero... si eso apaga el ordenador y dejame morir con dignidad por favor!!!     (╯°□°）╯︵ ┻━┻");
        System.out.println();
        return letraIntroducida;
    }

    public static void imprimirPalabra(char[] palabra) {
        System.out.print("Efectivamente y si, la palabra es: ");
        for (int i = 0; i < palabra.length; i++) {
            System.out.print(palabra[i]);
        }
        System.out.println();
        System.out.println();
    }

    public static void imprimirPalabraOculta(char[] palabra) {
        System.out.print("   ");
        for (int i = 0; i < palabra.length; i++) {
            System.out.print(palabra[i]);
            System.out.print(" ");
        }
    }

    public static char[] siguientePalabra() {
        String[] words = {"banana", "orange", "cherry", "apple", "pineapple", "melon", "plum"};
        Random random = new Random();
        int index = random.nextInt(words.length);
        return words[index].toCharArray();
    }

    public static char[] llenadorBarrasBajas(char[] palabra) {
        for (int i = 0; i < palabra.length; i++) {
            palabra[i] = '_';
        }
        return palabra;
    }

    public static boolean esNumero(char numero) {
        if ((numero == '0') || (numero == '1') || (numero == '2') || (numero == '3') || (numero == '4') || (numero == '5') || (numero == '6') || (numero == '7') || (numero == '8') || (numero == '9'))
            return true;
        else return false;
    }

    public static char[] buscadorCoincidencia(char[] palabra, char letra) {
        char[] info = new char[palabra.length];
        llenadorBarrasBajas(info);
        for (int i = 0; i < palabra.length; i++) {
            if (palabra[i] == letra) {
                info[i] = palabra[i];
            }
        }
        return info;
    }

    public static void unificadorCoincidencias(char[] palabraProbada, char[] palabraOculta) {
        for (int i = 0; i < palabraOculta.length; i++) {
            if (palabraProbada[i] != '_') palabraOculta[i] = palabraProbada[i];
        }
    }

    public static void mostrarBarra() {
        System.out.println("__________________________________________________________________________________________________");
    }

    public static boolean comprobadorPresencia(char letra, char[] palabra) {
        for (int i = 0; i < palabra.length; i++) {
            if (palabra[i] == letra) return true;
        }
        return false;
    }

    public static boolean palabraEstaCompleta(char[] palabra) {
        for (int i = 0; i < palabra.length; i++) {
            if (palabra[i] == '_') return false;
        }
        return true;
    }



    public static void finJuego(char[] palabraElegidaOculta, char[] palabraElegidaObtenida, int vidas, boolean resultadoGanador) {
        imprimirPalabraOculta(palabraElegidaOculta);
        System.out.println("  | Vidas: " + vidas);
        imprimirPalabra(palabraElegidaObtenida);
        if (resultadoGanador)lineaGanar();
        else lineaPerder();
        mostrarBarra();
        mostrarBarra();
        System.out.print("                                                                                      FIN DE JUEGO");
    }

    public static void lineaGanar(){
        System.out.println("Enhorabuena, has salvado al ahorcado. Estaras contento, era un asesino en serie...   ಠ_ಠ ");
    }

    public static void lineaPerder(){
        System.out.println("Mala suerte!! No das la talla, como con el resto de cosas de tu vida  ¯|(ツ)/¯ ");
    }

    public static boolean preguntarContinuarJugando() {
        System.out.println();
        System.out.println();
        System.out.print("Quieres hacer vida como una persona normal o te apetece seguir sentado e iniciar una nueva partida? S/N: ");
        Scanner cn = new Scanner(System.in);
        char decisionChar = cn.next().charAt(0);
        boolean decisionTMP = false;
        if (decisionChar == 'S' || decisionChar == 's') return true;
        else if (decisionChar == 'N' || decisionChar == 'n') return false;
        else if ((decisionChar != 'S') && (decisionChar != 's') && (decisionChar != 'N') && (decisionChar != 'n')) {
            System.out.println(" ///ERROR DE ANALFABETISMO/// Mete un valor aceptable anda, venga... de nuevo, S/N:");
            if (!preguntarContinuarJugando()){
                decisionTMP = false;
                System.out.println();
            }
            else decisionTMP = true;
        }
        return decisionTMP;
    }
}
