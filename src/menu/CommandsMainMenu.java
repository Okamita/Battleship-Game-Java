package menu;

import java.time.LocalDateTime;

import game.Game;
import game.Settings;
import game.Settings.CommandsState;
import grid.Grid;
import keyboardInput.ConfirmInput;
import utility.UtilityTools;

/** {@literal <<Control>>}
 * Gestisce e implementa i vari comandi che sara' possibile utilizzare nel progetto.
 * La responsabilità della classe CommandsGame è quella di gestire e implementare i vari comandi che sara'
 * possibile utilizzare nel progetto. La classe fornisce metodi per uscire dal gioco, per visualizzare
 * le informazioni sulle navi e il numero di esemplari per ogni nave, nonché per visualizzare il livello
 * di gioco e il numero massimo di tentativi falliti. */
public class CommandsMainMenu extends Commands {

    /**Costruttore di default della classe, aggiunto per warning di Javadoc.*/
    public CommandsMainMenu() { };

    /** Utilizzo del Singleton per accedere a variabili condivise. */
    private Settings settings = Settings.getIstance();

    /** Costante che indica la grandezza standard delle griglie (statiche). */
    private static final int STANDARD_GRID_DIM = 10;

    /** Costante che indica la grandezza large delle griglie (statiche). */
    private static final int LARGE_GRID_DIM = 18;

    /** Costante che indica la grandezza extra-large delle griglie (statiche). */
    private static final int EXTRA_LARGE_GRID_DIM = 26;

    /** Attributo che indica la dimensione della riga, la dimensione é standard. */
    private int dimRow = STANDARD_GRID_DIM;

    /** Attributo che indica la dimensione della colonna, la dimensione é standard. */
    private int dimColumn = STANDARD_GRID_DIM;

    /** Metodo che inizializza la griglia con dimensione standard. */
    public void standardGrid() {
        dimColumn = STANDARD_GRID_DIM;
        dimRow = STANDARD_GRID_DIM;
        System.out.println(UtilityTools.GREEN + "\nLa griglia e' stata correttamente impostata "
                + "alla dimensione standard" + UtilityTools.RESET);
        System.out.println("Se vuoi puoi visualizzare la griglia con il comando "
                + UtilityTools.GREEN + "/svelagriglia " + UtilityTools.RESET + " :)");
    }

    /** Metodo che inizializza la griglia con dimensione large. */
    public void largeGrid() {
        dimColumn = LARGE_GRID_DIM;
        dimRow = LARGE_GRID_DIM;
        System.out.println(UtilityTools.GREEN + "\nLa griglia e' stata correttamente impostata "
                + "alla dimensione large" + UtilityTools.RESET);
        System.out.println("Se vuoi puoi visualizzare la griglia con il comando "
                + UtilityTools.GREEN + "/svelagriglia " + UtilityTools.RESET + " :)");
    }

    /** Metodo che inizializza la griglia con dimensione extralarge. */
    public void extraLargeGrid() {
        dimColumn = EXTRA_LARGE_GRID_DIM;
        dimRow = EXTRA_LARGE_GRID_DIM;
        System.out.println(UtilityTools.GREEN + "\nLa griglia e' stata correttamente"
        + " impostata alla dimensione extra large" + UtilityTools.RESET);
        System.out.println("Se vuoi puoi visualizzare la griglia con il comando "
                + UtilityTools.GREEN + "/svelagriglia " + UtilityTools.RESET + " :)");
    }

    /** Metodo che restituisce la cardinalita' delle colonne di Grid.
     * @return dimColumn cardinalita' delle colonne della griglia */
    public int getDimColumn() {
        return dimColumn;
    }

    /** Metodo che restituisce la cardinalita' delle righe di Grid.
     * @return dimRow cardinalita' delle righe della griglia */
    public int getDimRow() {
        return dimRow;
    }

    /** Metodo costruttore della classe. */
    public void play() {
        System.out.println();
        settings.setEndTime(LocalDateTime.now().plusMinutes(settings.getTime()));

        settings.setTime(settings.getTime());
        Game game = new Game(getDimRow(), getDimColumn());
        settings.setGameMenuLoop(true);

        game.startNewGame();
    }

    /** Esce dal loop del menu principale e di conseguenza dal gioco impostando mainMenuLoop a "false". */
    public void exit() {

        final int mainMenuExitConfirm = 2;      //Print del menu principale quando si esce
        ConfirmInput confirmInput = new ConfirmInput();

        if (confirmInput.isConfirmed(mainMenuExitConfirm)) {
            System.out.println(UtilityTools.GREEN + "\nGrazie per aver giocato a Battleship, ti meriti un cornetto!"
            + UtilityTools.RESET);
            settings.setMainMenuLoop(false);
        } else {
            settings.setMainMenuLoop(true);
        }
    }

    /** Stampa una lista dei comandi utilizzabili dall'utente. */
    public void help() {
        System.out.println(UtilityTools.YELLOW + "\nPer interagire con l'applicazione, vanno utilizzati "
                + "i seguenti comandi:");
        System.out.println("- /gioca: " + UtilityTools.RESET + "Permette di iniziare una nuova partita");
        System.out.println(UtilityTools.YELLOW + "- /help: " + UtilityTools.RESET + "Mostra l’help con "
                + "l’elenco dei comandi");
        System.out.println(UtilityTools.YELLOW + "- /esci: " + UtilityTools.RESET + "Chiude il gioco");
        System.out.println(UtilityTools.YELLOW + "- /facile: " + UtilityTools.RESET + "Imposta il livello "
                + "di gioco in facile dando " + settings.getEasyNumTry() + " tentativi falliti massimi");
        System.out.println(UtilityTools.YELLOW + "- /medio: " + UtilityTools.RESET + "Imposta il livello "
                + "di gioco in medio dando " + settings.getMediumNumTry() + " tentativi falliti massimi");
        System.out.println(UtilityTools.YELLOW + "- /difficile: " + UtilityTools.RESET + "Imposta il livello "
                + "di gioco in difficile dando " + settings.getHardNumTry() + " tentativi falliti massimi");
        System.out.println(UtilityTools.YELLOW + "- /mostralivello: " + UtilityTools.RESET + "Mostra il livello "
                + "di gioco e il numero massimo di tentativi falliti");
        System.out.println(UtilityTools.YELLOW + "- /mostranavi: " + UtilityTools.RESET + "Mostra i tipi "
                + "di nave e la loro quantita'");
        System.out.println(UtilityTools.YELLOW + "- /svelagriglia: " + UtilityTools.RESET
                + "Mostra la griglia di gioco");
        System.out.println(UtilityTools.YELLOW + "- /tempo " + UtilityTools.CYAN + "numero" + UtilityTools.RESET
        + ": " + UtilityTools.RESET + "Imposta il tempo di gioco");
        System.out.println(UtilityTools.YELLOW + "- /standard: " + UtilityTools.RESET + "Imposta la griglia "
                + "di gioco con una grandezza " + STANDARD_GRID_DIM + "x" + STANDARD_GRID_DIM);
        System.out.println(UtilityTools.YELLOW + "- /large: " + UtilityTools.RESET + "Imposta la griglia "
                + "di gioco con una grandezza " + LARGE_GRID_DIM + "x" + LARGE_GRID_DIM);
        System.out.println(UtilityTools.YELLOW + "- /extralarge: " + UtilityTools.RESET + "Imposta la griglia "
                + "di gioco con una grandezza " + EXTRA_LARGE_GRID_DIM + "x" + EXTRA_LARGE_GRID_DIM);
        System.out.println(UtilityTools.YELLOW + "- /mostratempo: " + UtilityTools.RESET
                + "Mostra il tempo di gioco");
        System.out.println(UtilityTools.YELLOW + "- /facile " + UtilityTools.CYAN + "numero" + UtilityTools.RESET
                + ": (Es: /facile 40) "
                + "Imposta il numero di tentativi falliti massimi\n della modalita' facile e la seleziona");
        System.out.println(UtilityTools.YELLOW + "- /medio " + UtilityTools.CYAN + "numero" + UtilityTools.RESET
                + ": (Es: /medio 25) "
                + "Imposta il numero di tentativi falliti massimi\n della modalita' medio e la seleziona");
        System.out.println(UtilityTools.YELLOW + "- /difficile " + UtilityTools.CYAN + "numero " + UtilityTools.RESET
                + ": (Es: /difficile 8) "
                + "Imposta il numero di tentativi falliti massimi\n della modalita' difficile e la seleziona");
        System.out.println(UtilityTools.YELLOW + "- /tentativi " + UtilityTools.CYAN + "numero " + UtilityTools.RESET
                + ": (Es: /tentativi 20) Imposta il numero di tentativi falliti massimi\n"
                + " che si useranno in partita, indipendentemente da quale difficolta' e' impostata");

        System.out.println(UtilityTools.YELLOW + "\nPer i comandi che impostano il numero di tentativi"
                + " della difficolta' ci sono alcuni vincoli. \n 1. " + UtilityTools.RESET
                + "Numero tentativi facile > numero tentativi medio > numero tentativi difficile"
        + UtilityTools.YELLOW + "\n 2. " + UtilityTools.RESET + "Numero minimo di tentativi per facile: "
                + settings.getEasyNumTry()
        + UtilityTools.YELLOW + "\n 3. " + UtilityTools.RESET + "Numero minimo di tentativi per medio: "
                + settings.getMediumNumTry()
        + UtilityTools.YELLOW + "\n 4. " + UtilityTools.RESET + "Numero minimo di tentativi per difficile"
                + " e per il comando tentativi: " + settings.getHardNumTry()
        + UtilityTools.YELLOW + "\n 5. " + UtilityTools.RESET + "Numero massimo di tentativi possibili "
                + "selezionabili: 99" + UtilityTools.RESET);
    }

    /** Metodo per impostare la difficolta' del gioco a facile. */
    public void setEasyDifficulty() {
        settings.setCurrentDifficulty(CommandsState.FACILE);
        System.out.println(UtilityTools.GREEN + "\nOk, hai selezionato la difficolta' facile.");
        System.out.println("Hai a disposizione " + settings.getEasyNumTry() + " tentativi." + UtilityTools.RESET);
    }

    /** Metodo per impostare la difficolta' del gioco a media. */
    public void setMediumDifficulty() {
        settings.setCurrentDifficulty(CommandsState.MEDIO);
        System.out.println(UtilityTools.GREEN + "\nOk, hai selezionato la difficolta' media.");
        System.out.println("Hai a disposizione " + settings.getMediumNumTry() + " tentativi." + UtilityTools.RESET);
    }

    /** Metodo per impostare la difficolta' del gioco a difficile. */
    public void setHardDifficulty() {
        settings.setCurrentDifficulty(CommandsState.DIFFICILE);
        System.out.println(UtilityTools.GREEN + "\nOk, hai selezionato la difficolta' difficile.");
        System.out.println("Hai a disposizione " + settings.getHardNumTry() + " tentativi." + UtilityTools.RESET);
    }

    /** Metodo per impostare il numero di tentativi della difficoltá facile. */
    public void setEasyAttempts() {
        settings.setCurrentDifficulty(CommandsState.FACILE);
        System.out.println(UtilityTools.GREEN + "\nHai modificato il numero di"
        + " tentativi della modalita' Facile a: " + settings.getEasyNumTry());
        System.out.println("- Il livello di difficolta' corrente é il seguente: " + CommandsState.FACILE);
        System.out.println("- Hai a disposizione "  + settings.getEasyNumTry() + " tentativi." + UtilityTools.RESET);
    }

    /** Metodo per impostare il numero di tentativi della difficoltá media. */
    public void setMediumAttempts() {
        settings.setCurrentDifficulty(CommandsState.MEDIO);
        System.out.println(UtilityTools.GREEN + "\nHai modificato il numero di"
        + " tentativi della modalita' Medio a: " + settings.getMediumNumTry());
        System.out.println("- Il livello di difficolta' corrente é il seguente: " + CommandsState.MEDIO);
        System.out.println("- Hai a disposizione "  + settings.getMediumNumTry() + " tentativi." + UtilityTools.RESET);
    }

    /** Metodo per impostare il numero di tentativi della difficoltá difficile. */
    public void setHardAttempts() {
        settings.setCurrentDifficulty(CommandsState.DIFFICILE);
        System.out.println(UtilityTools.GREEN + "\nHai modificato il numero di"
        + " tentativi della modalita' Difficile a: " + settings.getHardNumTry());
        System.out.println("- Il livello di difficolta' corrente é il seguente: " + CommandsState.DIFFICILE);
        System.out.println("- Hai a disposizione "  + settings.getHardNumTry() + " tentativi." + UtilityTools.RESET);
    }

    /** Metodo per impostare il numero di tentativi della difficoltá personalizzata. */
    public void setPersonalizedAttempts() {
        settings.setCurrentDifficulty(CommandsState.PERSONALIZZATA);
        System.out.println(UtilityTools.GREEN + "\nHai modificato il numero di"
        + " tentativi della modalita' Personalizzata a: " + settings.getCurrentDifficultyNumTry());
        System.out.println("- Il livello di difficolta' corrente é il seguente: "
        + CommandsState.PERSONALIZZATA);
        System.out.println("- Hai a disposizione "  + settings.getCurrentDifficultyNumTry()
                + " tentativi." + UtilityTools.RESET);
    }

    /** Metodo per impostare il tempo di gioco. */
    public void setTime() {
        System.out.println(UtilityTools.GREEN + "\nIl tempo di gioco e' stato impostato a "
                + settings.getTime() + " minuti." + UtilityTools.RESET);
    }

    /** Metodo per visualizzare la griglia di gioco. */
    public void showGrid() {
        Grid grid = new Grid(getDimRow(), getDimColumn());  //Temporaneo, in attesa d'implementazione di game
        System.out.println();
        grid.printGrid();
    }

    /** Metodo per visualizzare il tempo di gioco. */
    public void showTime() {
        System.out.println(UtilityTools.GREEN + "\nIl tempo di gioco e' di "
                + settings.getTime() + " minuti." + UtilityTools.RESET);
    }
}
