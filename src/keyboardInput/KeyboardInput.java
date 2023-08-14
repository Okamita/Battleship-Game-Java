package keyboardInput;
import game.Settings;
import utility.UtilityTools;

/** {@literal <<Boundary>>}
 * Superclasse che contiene i valori comuni da utilizzare nelle sottoclassi degli input.
 * La classe KeyboardInput è una superclasse astratta che contiene valori comuni e funzionalità
 * di gestione dell'input da tastiera nelle sue sottoclassi. La sua responsabilità principale è fornire
 * un meccanismo per acquisire l'input dell'utente e verificare la correttezza dell'input.
 * La classe fornisce un metodo astratto isTheInputCorrect che deve essere implementato nelle sottoclassi.
 * Questo metodo controlla se l'input inserito dall'utente è corretto in base alle regole specifiche
 * della sottoclasse. Se l'input non è corretto, vengono visualizzati messaggi di errore appropriati.
 * La classe contiene anche un metodo takeInput che gestisce l'acquisizione dell'input da tastiera.
 * A seconda del parametro controllPosition passato al metodo, viene visualizzato un prompt appropriato
 * per l'input. Questo metodo richiama il metodo isTheInputCorrect per verificare la correttezza dell'input
 * e continua a richiedere l'input finché non viene fornito un input valido.
 * Inoltre, la classe fornisce metodi per impostare e ottenere il valore dell'attributo userInput, che
 * rappresenta la stringa inserita dall'utente.
 * In generale, la classe KeyboardInput fornisce un'implementazione di base per la gestione dell'input
 * da tastiera e delega il compito di verificare la correttezza specifica dell'input alle sue sottoclassi. */
abstract class KeyboardInput {


    /** Costruttore di default della classe, aggiunto per warning di Javadoc. */
    KeyboardInput() { };

    /** Utilizzo del Singleton per accedere a variabili condivise. */
    private Settings settings = Settings.getIstance();

    /** Rappresenta la variabile che contiene la stringa digitata da tastiera dall'utente. */
    private String userInput;

    /** Dichiarazione del metodo da implementare dentro le sottoclassi.
     * @return boolean ritorna se l'input é corretto */
    protected abstract boolean isTheInputCorrect();

    /** Imposta l'attributo userInput uguale al parametro assegnatoli.
     * @param userInputString Utilizzato nell' assegnazione della stringa */
    protected final void setUserInput(final String userInputString) {
        userInput = userInputString;
    }

    /** Prende l'input dal giocatore, in base da dove viene richiamato stampa diversamente.
     * @param controllPosition Indica dove viene richiamato takeInput */
    public void takeInput(final int controllPosition) {
        //I valori che rappresentano i print da eseguire in base al menu
        final int mainMenuNormal = 1;           //Print del menu principale appena si apre il gioco
        final int mainMenuExitConfirm = 2;      //Print del menu principale quando si esce
        final int gameMenuNormal = 3;           //Print del menu di gioco appena di fa /gioca
        final int gameMenuExitConfirm = 4;      //Print del menu di gioco quando si cerca di uscire durante la partita
        final int gameMenuAbandonConfirm = 5;   //Print del menu di gioco quando si cerca di abbandonare la partita

        do {
            UtilityTools.clearScreen();
            if (controllPosition == mainMenuNormal) {
                UtilityTools.printMainMenuChoices();
                System.out.print(UtilityTools.GREEN + "Inserire il comando: " + UtilityTools.RESET);
            } else if (controllPosition == mainMenuExitConfirm) {
                UtilityTools.printMainMenuChoices();
                System.out.print(UtilityTools.YELLOW + "\nVuoi veramente uscire? (Scrivere yes/no): "
                        + UtilityTools.RESET);
            } else if (controllPosition == gameMenuNormal) {
                UtilityTools.printGameMenuAsciiArt();
                settings.getPlayerGrid().printGrid();
                UtilityTools.printGameMenuChoices();
                System.out.print(UtilityTools.GREEN + "Inserire il comando o la coordinata d'attacco es.(A-1): "
                        + UtilityTools.RESET);

            } else if (controllPosition == gameMenuExitConfirm) {
                UtilityTools.printGameMenuAsciiArt();
                settings.getPlayerGrid().printGrid();
                UtilityTools.printGameMenuChoices();
                System.out.print(UtilityTools.YELLOW + "\nVuoi veramente uscire? (Scrivere yes/no): "
                        + UtilityTools.RESET);
            } else if (controllPosition == gameMenuAbandonConfirm) {
                UtilityTools.printGameMenuAsciiArt();
                UtilityTools.printGameMenuChoices();
                System.out.print(UtilityTools.YELLOW + "\nVuoi veramente abbandonare "
                        + "la partita? (Scrivere yes/no): " + UtilityTools.RESET);
            }
            setUserInput(UtilityTools.SCANNER.nextLine());
        } while (!isTheInputCorrect());
    };

    /** Accede all'attributo userInput.
     * @return userInput ritorna sempre userInput. */
    public final String getUserInput() {
        return userInput;
    }
}
