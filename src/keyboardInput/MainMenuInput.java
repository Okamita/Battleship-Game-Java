package keyboardInput;
import game.Settings;
import game.Settings.CommandsState;
import utility.UtilityTools;

/** {@literal <<Boundary>>}
 * Classe che gestisce l'input dal menu principale, se l'input é corretto cambia lo stato utilizzato in App.java.
 * La classe MainMenuInput gestisce l'input proveniente dal menu principale del gioco. La sua responsabilità
 * principale è quella di controllare se l'input dell'utente corrisponde a uno dei comandi supportati e
 * d'impostare lo stato corrispondente all'interno della classe Settings utilizzata dall'applicazione
 * principale (App.java). Se l'input è corretto, la classe imposta lo stato corrispondente al comando inserito
 * e restituisce true, altrimenti restituisce false e visualizza un messaggio di errore a schermo.
 * La classe contiene una serie di costanti che rappresentano i comandi supportati, come ad esempio la selezione
 * della difficoltà di gioco o l'impostazione del tempo di gioco. Ogni comando viene controllato utilizzando
 * una serie di condizioni if-else if. Se un comando corrisponde all'input dell'utente, viene impostato
 * lo stato corrispondente nel Settings e restituito true.
 * La classe contiene anche metodi ausiliari per controllare la validità dei valori inseriti dall'utente,
 * ad esempio per verificare se il tempo inserito è accettabile o se il numero di tentativi per
 * una determinata difficoltà soddisfa i vincoli stabiliti. In caso contrario, vengono visualizzati
 * messaggi di errore appropriati. */
public final class MainMenuInput extends KeyboardInput {

    /** Costruttore di default della classe. Non esegue alcuna operazione.
     * Aggiunto per prevenire il warning di Javadoc. */
    public MainMenuInput() { };

    /** Costante per indicare che l'utente ha selezionaato "Facile", utile per i check. */
    private static final String EASY_SELECTOR = "Facile";

    /** Costante per indicare che ci l'utente ha selezionaato "Medio", utile per i check. */
    private static final String MEDIUM_SELECTOR = "Medio";

    /** Costante per indicare che ci l'utente ha selezionaato "Hard", utile per i check. */
    private static final String HARD_SELECTOR = "Hard";

    /** Costante per indicare che ci l'utente ha selezionaato "Personal", utile per i check. */
    private static final String PERSONAL_SELECTOR = "Personal";

    /** Costante per indicare il numero massimo di tenttivi. */
    private static final int MAX_INT = 99;

    /** Costante per indicare il numero mini di tentativi per la modalitá facile. */
    private static final int MIN_EASY_NUM_TRY = 15;

    /** Costante per indicare il numero mini di tentativi per la modalitá medio. */
    private static final int MIN_MEDIUM_NUM_TRY = 10;

    /** Costante per indicare il numero mini di tentativi per la modalitá difficile. */
    private static final int MIN_HARD_NUM_TRY = 5;

    /** Costante per indicare il numero mini di tentativi per la modalitá personalizzata. */
    private static final int MIN_PERSONAL_NUM_TRY = 5;

    /** Utilizzo del Singleton per accedere a variabili condivise. */
    private Settings settings = Settings.getIstance();

    /** Controlla se l'utente ha inserito correttamente il comando,
     * nel caso il comando fosse sbagliato il programma lo segnalerebbe e il metodo ritornerebbe "false"
     * altrimenti imposta l'CommandsState di menu con il relativo comando e ritorna "true".
     * @return false, true Ritorna vero se il comando è implementato nel gioco,
     * ritorna falso se l'utente ha inserito una stringa errato o se il comando non è implementato nel gioco */
    protected boolean isTheInputCorrect() {
        if (getUserInput().equalsIgnoreCase(("/help"))) {
            settings.setCurrentState(CommandsState.HELP);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/gioca")) {
            settings.setCurrentState(CommandsState.GIOCA);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/esci")) {
            settings.setCurrentState(CommandsState.ESCI);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/facile")) {
            settings.setCurrentState(CommandsState.FACILE);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/medio")) {
            settings.setCurrentState(CommandsState.MEDIO);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/difficile")) {
            settings.setCurrentState(CommandsState.DIFFICILE);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/mostralivello")) {
            settings.setCurrentState(CommandsState.MOSTRALIVELLO);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/mostranavi")) {
            settings.setCurrentState(CommandsState.MOSTRANAVI);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/svelagriglia")) {
            settings.setCurrentState(CommandsState.SVELAGRIGLIA);
            return true;
        } else if (getUserInput().matches("/tempo \\d[0-9]?")) {

            if (!isTimeLegal()) {
                UtilityTools.SCANNER.nextLine();
                return false;
            }

            settings.setCurrentState(CommandsState.TEMPO);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/standard")) {
            settings.setCurrentState(CommandsState.STANDARD);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/large")) {
            settings.setCurrentState(CommandsState.LARGE);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/extralarge")) {
            settings.setCurrentState(CommandsState.EXTRALARGE);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/mostratempo")) {
            settings.setCurrentState(CommandsState.MOSTRATEMPO);
            return true;
        } else if (getUserInput().matches("/facile \\d[0-9]?")) {

            if (!isDifficultyLegal(EASY_SELECTOR)) {
                UtilityTools.SCANNER.nextLine();
                return false;
            }

            settings.setCurrentState(CommandsState.FACILETENTATIVI);
            String number = getUserInput().replaceAll("[^0-9]?", "");
            settings.setEasyNumTry(Integer.parseInt(number));
            return true;
        } else if (getUserInput().matches("/medio \\d[0-9]?")) {

            if (!isDifficultyLegal(MEDIUM_SELECTOR)) {
                UtilityTools.SCANNER.nextLine();
                return false;
            }

            settings.setCurrentState(CommandsState.MEDIOTENTATIVI);
            String number = getUserInput().replaceAll("[^0-9]?", "");
            settings.setMediumNumTry(Integer.parseInt(number));
            return true;
        } else if (getUserInput().matches("/difficile \\d[0-9]?")) {

            if (!isDifficultyLegal(HARD_SELECTOR)) {
                UtilityTools.SCANNER.nextLine();
                return false;
            }

            settings.setCurrentState(CommandsState.DIFFICILETENTATIVI);
            settings.setHardNumTry(Integer.parseInt(getUserInput().replaceAll("[^0-9]?", "")));
            return true;
        } else if (getUserInput().matches("/tentativi \\d[0-9]?")) {

            if (!isDifficultyLegal(PERSONAL_SELECTOR)) {
                UtilityTools.SCANNER.nextLine();
                return false;
            }

            settings.setCurrentState(CommandsState.PERSONALIZZATA);
            String number = getUserInput().replaceAll("[^0-9]?", "");
            settings.setCurrentDifficultyNumTry(Integer.parseInt(number));
            return true;
        }

        if (getUserInput().isEmpty()) {
            System.out.print(UtilityTools.RED + "\nErrore: Nessun comando inserito, "
                    + "premere INVIO per continuare..." + UtilityTools.RESET);
        } else if (!getUserInput().startsWith("/")) {
            System.out.print(UtilityTools.RED + "\nErrore: Il prefisso / non e' stato inserito "
                    + "correttamente, premere INVIO per continuare..." + UtilityTools.RESET);
        } else {
            System.out.print(UtilityTools.RED + "\nErrore: Comando non supportato, "
                    + "premere INVIO per continuare..." + UtilityTools.RESET);
        }

        System.out.println();
        UtilityTools.SCANNER.nextLine();
        return false;
    }

    /** Controlla se il valore selezionato dall'utente é accettato secondo i vincoli delle
     * difficoltá, nel caso il comando fosse sbagliato il programma lo segnalerebbe e il metodo ritornerebbe "false"
     * altrimenti imposta l'CommandsState di menu con il relativo comando e ritorna "true".
     * @return vero se il valore messo puó essere utilizzato, altrimenti falso stampando un errore a schermo */
    private boolean isTimeLegal() {
        int number = Integer.parseInt(getUserInput().replaceAll("[^0-9]?", ""));

        if (number < settings.getMinValueTime()) {
            System.out.println(UtilityTools.RED + "\nErrore: Bisogna inserire un tempo minimo di "
                    + settings.getMinValueTime() + " minuti." + UtilityTools.RESET);
            return false;
        }
        settings.setTime(number);
        return true;
    }

    /** Controlla se il valore selezionato dall'utente é accettato secondo i vincoli delle
     * difficoltá, nel caso il comando fosse sbagliato il programma lo segnalerebbe e il metodo ritornerebbe "false"
     * altrimenti imposta CurrentStte di menu con il relativo comando e ritorna "true".
     * @param difficulty la difficoltá selezionata dall'utente
     * @return vero se il valore messo puó essere utilizzato, altrimenti falso stampando un errore a schermo */
    private boolean isDifficultyLegal(final String difficulty) {

        int number = Integer.parseInt(getUserInput().replaceAll("[^0-9]?", ""));

        if (number > MAX_INT) {
            System.out.println(UtilityTools.RED + "\nErrore: Il numero inserito non puó superare "
                    + MAX_INT + UtilityTools.RESET);
            return false;
        }

        if (difficulty == PERSONAL_SELECTOR && number < MIN_PERSONAL_NUM_TRY) {
            System.out.println(UtilityTools.RED + "\nErrore: Il numero di tentativi"
            + " personalizzati non possono essere minori di " + MIN_PERSONAL_NUM_TRY + UtilityTools.RESET);
            return false;
        }

        if (difficulty == EASY_SELECTOR && number < MIN_EASY_NUM_TRY) {
            System.out.println(UtilityTools.RED + "\nErrore: Il numero di tentativi"
            + " per la difficolta' facile non possono essere minori di " + MIN_EASY_NUM_TRY + UtilityTools.RESET);
            return false;
        } else if (difficulty == MEDIUM_SELECTOR && number < MIN_MEDIUM_NUM_TRY) {
            System.out.println(UtilityTools.RED + "\nErrore: Il numero di tentativi"
            + " per la difficolta' medio non possono essere minori di " + MIN_MEDIUM_NUM_TRY + UtilityTools.RESET);
            return false;
        } else if (difficulty == HARD_SELECTOR && number < MIN_HARD_NUM_TRY) {
            System.out.println(UtilityTools.RED + "\nErrore: Il numero di tentativi"
            + " per la difficolta' difficile non possono essere minori di " + MIN_HARD_NUM_TRY + UtilityTools.RESET);
            return false;
        }

        if (difficulty.equals(EASY_SELECTOR) && number <= settings.getMediumNumTry()) {
            System.out.println(UtilityTools.RED + "\nErrore: Il numero di tentativi per la"
            + " difficolta' facile non devono essere minori o uguali della difficolta' media");
            System.out.println("- Tentativi che vuoi impostare in facile: " + number);
            System.out.println("- Tentativi di medio: " + settings.getMediumNumTry());
            System.out.println("- Tentativi in difficile: " + settings.getHardNumTry() + UtilityTools.RESET);
            return false;
        } else if (difficulty.equals(MEDIUM_SELECTOR) && number <= settings.getHardNumTry()
                || difficulty.equals(MEDIUM_SELECTOR) && number >= settings.getEasyNumTry()) {
            System.out.println(UtilityTools.RED + "\nErrore: Il numero di tentativi per la difficolta'"
                    + " medio non devono essere minori o uguali della difficolta' difficile"
                    + " oppure maggiore o uguale della difficolta' facile");
            System.out.println("- Tentativi in facile: " + settings.getEasyNumTry());
            System.out.println("- Tentativi che vuoi impostare in medio: " + number);
            System.out.println("- Tentativi in difficile: " + settings.getHardNumTry() + UtilityTools.RESET);
            return false;
        } else if (difficulty.equals(HARD_SELECTOR) && number >= settings.getMediumNumTry()) {
            System.out.println(UtilityTools.RED + "\nErrore: Il numero di tentativi per la difficolta'"
                    + " difficile non devono essere maggiori o uguali della difficolta' medio");
            System.out.println("- Tentativi in facile: " + settings.getEasyNumTry());
            System.out.println("- Tentativi in medio: " + settings.getMediumNumTry());
            System.out.println("- Tentativi che vuoi impostare in difficile: " + number + UtilityTools.RESET);
            return false;
        }
        return true;
    }
}
