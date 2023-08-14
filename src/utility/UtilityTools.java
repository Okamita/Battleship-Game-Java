package utility;

import java.util.Scanner;

/** {@literal <<Boundary>>}
 * Contiene metodi e costanti utili per l'interfaccia grafica dell'intero programma.
 * La responsabilità della classe UtilityTools è quella di contenere metodi e costanti utili per l'interfaccia
 * grafica dell'intero programma. La classe fornisce metodi per pulire il terminale, per stampare il menu di
 * gioco e per ottenere il comando da tastiera che l'utente desidera digitare. La classe utilizza la classe
 * Scanner per ottenere il comando da tastiera che l'utente desidera digitare. */
public final class UtilityTools {

    /** Costante per utilizzare il colore BASE nei printf. */
    public static final String RESET = "\u001B[0m";

    /** Costante per utilizzare il colore NERO nei printf. */
    public static final String BLACK = "\u001B[30m";

    /** Costante per utilizzare il colore ROSSO nei printf. */
    public static final String RED = "\u001B[31m";

    /** Costante per utilizzare il colore VERDE nei printf. */
    public static final String GREEN = "\u001B[32m";

    /** Costante per utilizzare il colore GIALLO nei printf. */
    public static final String YELLOW = "\u001B[33m";

    /** Costante per utilizzare il colore BLU nei printf. */
    public static final String BLUE = "\u001B[34m";

    /** Costante per utilizzare il colore VIOLA nei printf. */
    public static final String PURPLE = "\u001B[35m";

    /** Costante per utilizzare il colore CIANO nei printf. */
    public static final String CYAN = "\u001B[36m";

    /** Costante per utilizzare il colore BIANCO nei printf. */
    public static final String WHITE = "\u001B[37m";

    /** Costruttore privato per evitare che venga istanziata la classe. */
    private UtilityTools() { }

    /** Serve per l'ottenimento del comando da tastiera che l'utente desidera digitare. */
    public static final Scanner SCANNER = new Scanner(System.in, "UTF-8");

    /** Serve a pulire il terminale per una maggior chiarezza nell'utilizzo. */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /** Fa una stampa a video per dare il benvenuto all'utente e per orientarlo all'utilizzo dei comandi. */
    public static void printMainMenuChoices() {
        printMainMenuAsciiArt();
        System.out.println(UtilityTools.GREEN + "Benvenuto nel menu, cosa vuoi fare?" + UtilityTools.RESET);
        System.out.println("Scrivere il comando" + UtilityTools.YELLOW + " /help " + UtilityTools.RESET
            + "per visionare i comandi utilizzabili");
    }

    /** Fa una stampa a video per stampare nel menu di gioca. */
    public static void printGameMenuChoices() {
        System.out.println(UtilityTools.GREEN + "Benvenuto nel menu di gioco, cosa vuoi fare?" + UtilityTools.RESET);
        System.out.println("Scrivere il comando" + UtilityTools.YELLOW + " /help " + UtilityTools.RESET
            + "per visionare i comandi utilizzabili");
    }

    /** Metodo che controlla se la stringa è un numero.
     * @param s stringa nella quale si controlla se é numerica o meno
     * @return boolean se é numerico o meno */
    public static boolean isNumeric(final String s) {
        if (s == null || s.equals("") || s.equals(".") || s.equals("..")) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /** Stampa la grafica della nave nel menu principale. */
    public static void printMainMenuAsciiArt() {
        System.out.println("                                                     __/___    ");
        System.out.println("                                              _____/______| ");
        System.out.println("                                     _______/_____\\_______\\_____   ");
        System.out.println("                                     \\              < < <       |   ");
        System.out.println(CYAN + "-..__..- ~~--..__...----... -..__..- _-..__..- ~~--..__...__..- ~~--..__..."
        + "----... -..__..- _-..__..- ~~--..__..." + RESET);
        System.out.println("");
        System.out.println("");
        System.out.println(YELLOW + "   ____    _  _____ _____ _     _____ ____  _   _ ___ ____                   "
        + CYAN + "               _.====.._" + RESET);
        System.out.println(YELLOW + "  | __ )  / \\|_   _|_   _| |   | ____/ ___|| | | |_ _|  _ \\                  "
        + CYAN + "             ,:._       ~-_" + RESET);
        System.out.println(YELLOW + "  |  _ \\ / _ \\ | |   | | | |   |  _| \\___ \\| |_| || || |_) |                "
        + CYAN + "                  `\\        ~-" + RESET);
        System.out.println(YELLOW + "  | |_) / ___ \\| |   | | | |___| |___ ___) |  _  || ||  __/           "
        + CYAN + "                          |          `." + RESET);
        System.out.println(YELLOW + "  |____/_/   \\_\\_|   |_| |_____|_____|____/|_| |_|___|_|                  "
        + CYAN + "                    ,/             ~-_" + RESET);
        System.out.println(CYAN
        + "-..__..- ~~--..__...----... -..__..--..__..- ~~--..__...----... -..__..--..__..- ~~--."
        + ".__..-''                 ~~--." + RESET);
        System.out.println("\n\n");
    }

    /** Stampa la grafica della nave nel GAME menu. */
    public static void printGameMenuAsciiArt() {
        System.out.println("                                                     __/___    ");
        System.out.println("                                              _____/______| ");
        System.out.println("                                     _______/_____\\_______\\_____   ");
        System.out.println("                                     \\              < < <       |   ");
        System.out.println(CYAN + "-..__..- ~~--..__...----... -..__..- _-..__..- ~~--..__...__..- ~~--..__..."
        + "----... -..__..- _-..__..- ~~--..__..." + RESET);
        System.out.println("");
        System.out.println("");
        System.out.println(YELLOW + "   _____                           __  __                     " + RESET);
        System.out.println(YELLOW + "  / ____|                         |  \\/  |                    "
        + CYAN + "               _.====.._" + RESET);

        System.out.println(YELLOW + " | |  __   __ _  _ __ ___    ___  | \\  / |  ___  _ __   _   _ "
        + CYAN + "             ,:._       ~-_" + RESET);
        System.out.println(YELLOW + " | | |_ | / _` || '_ ` _ \\  / _ \\ | |\\/| | / _ \\| '_ \\ | | | |"
        + CYAN + "                  `\\        ~-" + RESET);
        System.out.println(YELLOW + " | |__| || (_| || | | | | ||  __/ | |  | ||  __/| | | || |_| |"
        + CYAN + "                     |          `." + RESET);
        System.out.println(YELLOW + "  \\_____| \\__,_||_| |_| |_| \\___| |_|  |_| \\___||_| |_| \\__,_|"
        + CYAN + "                    ,/            ~-__...-----..__.-" + RESET);
        System.out.println(CYAN
        + "-..__..- ~~--..__...----... -..__..--..__..- ~~--..__...----... -..__..--."
        + ".__..-''                 " + RESET);
        System.out.println("\n\n");
    }

    /** Stampa la grafica della vittoria. */
    public static void printWin() {
        System.out.println(CYAN + "-..__..- ~~--..__...----... -..__..- _-..__." + RESET);
        System.out.println(GREEN + "       _      _                   ");
        System.out.println("      (_)    | |                  ");
        System.out.println("__   ___  ___| |_ ___  _ __ _   _ ");
        System.out.println("\\ \\ / / |/ __| __/ _ \\| '__| | | |");
        System.out.println(" \\ V /| | (__| || (_) | |  | |_| |");
        System.out.println("  \\_/ |_|\\___|\\__\\___/|_|   \\__, |");
        System.out.println("                             __/ |");
        System.out.println("                            |___/ " + RESET);
        System.out.println(CYAN + "-..__..- ~~--..__...----... -..__..- _-..__." + RESET);
    }
}
