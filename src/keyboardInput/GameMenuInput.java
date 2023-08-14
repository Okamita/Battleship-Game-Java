package keyboardInput;

import java.nio.charset.StandardCharsets;

import game.Settings;
import game.Settings.CommandsState;
import grid.Grid;
import utility.UtilityTools;

/** {@literal <<Boundary>>}
 * Sottoclasse di KeyboardInput si occupa di controllare l'input dentro il menu di gioco.
 * La classe GameMenuInput è una sottoclasse di KeyboardInput e si occupa di gestire l'input dell'utente
 * all'interno del menu di gioco. La sua responsabilità principale è controllare se l'input inserito
 * dall'utente corrisponde a comandi validi del menu di gioco.
 * La classe fornisce il metodo isTheInputCorrect, che controlla l'input dell'utente e imposta lo stato
 * del comando corrispondente nella classe Settings. I comandi validi includono /help, /esci,
 * /mostralivello, /mostranavi, /svelagriglia, /mostratempo, /mostragriglia, /abbandona, e /mostratentativi.
 * Se l'input dell'utente non corrisponde a nessun comando valido, viene visualizzato un messaggio di
 * errore appropriato. Inoltre, la classe fornisce il metodo isTheCoordinatesCorrect che controlla se
 * le coordinate inserite dall'utente per l'attacco sono valide. Questo metodo controlla se le coordinate
 * sono state inserite correttamente nel formato corretto (ad esempio, "a-1" o "B-3") e se le coordinate
 * sono all'interno dei limiti della griglia di gioco. Se le coordinate non sono corrette,
 * viene visualizzato un messaggio di errore.
 * In generale, la classe GameMenuInput facilita il controllo e la gestione dell'input dell'utente
 * nel menu di gioco, assicurandosi che vengano eseguite le azioni corrispondenti ai comandi validi
 * e che le coordinate di attacco siano corrette. */
public class GameMenuInput extends KeyboardInput {

    /** Costruttore di default della classe, aggiunto per warning di Javadoc.*/
    public GameMenuInput() { };

    /** Utilizzo del Singleton per accedere a variabili condivise. */
    private Settings settings = Settings.getIstance();

    /** Controlla se l'utente ha inserito correttamente il comando,
     * nel caso il comando fosse sbagliato il programma lo segnalerebbe e il metodo ritornerebbe "false"
     * altrimenti imposta l'CommandsState di gameMenu con il relativo comando e ritorna "true".
     * @return false, true Ritorna vero se il comando è implementato nel gioco,
     * ritorna falso se l'utente ha inserito una stringa errato o se il comando non è implementato nel gioco */
    protected boolean isTheInputCorrect() {
        if (getUserInput().equalsIgnoreCase(("/help"))) {
            settings.setCurrentState(CommandsState.HELP);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/esci")) {
            settings.setCurrentState(CommandsState.ESCI);
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
        } else if (getUserInput().equalsIgnoreCase("/mostratempo")) {
            settings.setCurrentState(CommandsState.MOSTRATEMPO);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/mostragriglia")) {
            settings.setCurrentState(CommandsState.MOSTRAGRIGLIA);
            return true;
        } else if (getUserInput().matches("[a-z]-\\d[0-9]?") || getUserInput().matches("[A-Z]-\\d[0-9]?")) {

            if (!isTheCoordinatesCorrect(settings.getEnemyGrid(), getUserInput().toLowerCase())) {
                UtilityTools.SCANNER.nextLine();
                return false;
            }

            settings.setCurrentState(CommandsState.ATTACCA);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/abbandona")) {
            settings.setCurrentState(CommandsState.ABBANDONA);
            return true;
        } else if (getUserInput().equalsIgnoreCase("/mostratentativi")) {
            settings.setCurrentState(CommandsState.MOSTRATENTATIVI);
            return true;
        }
        if (getUserInput().isEmpty()) {
            System.out.print(UtilityTools.RED + "\nErrore: Nessun comando inserito,"
                    + " premere INVIO per continuare..." + UtilityTools.RESET);
        } else if (!getUserInput().startsWith("/") && !getUserInput().matches("[a-z]\\d[0-9]?")
                && !getUserInput().matches("[A-Z]\\d[0-9]?")) {
            System.out.print(UtilityTools.RED + "\nErrore: Il prefisso / non e' stato inserito correttamente,"
                    + " premere INVIO per continuare..." + UtilityTools.RESET);
        } else if (getUserInput().matches("[a-z]\\d[0-9]?") || getUserInput().matches("[A-Z]\\d[0-9]?")) {
            System.out.println(UtilityTools.RED + "\nErrore: La stringa " + getUserInput() + " non contiene il -"
                    + UtilityTools.RESET);
        } else {
            System.out.print(UtilityTools.RED + "\nErrore: Comando non supportato,"
                    + " premere INVIO per continuare..." + UtilityTools.RESET);
        }
        UtilityTools.SCANNER.nextLine();
        return false;
    }

    /** Controlla se le coordinate inserite dall'utente sono corrette.
     * @param mat La griglia di gioco
     * @param coordinates Le coordinate inserite dall'utente
     * @return boolean Ritorna vero se le coordinate sono corrette, falso altrimenti */
    private boolean isTheCoordinatesCorrect(final Grid mat, final String coordinates) {
        boolean result = false;
        int column = 0;
        int row = 0;
        final int coverter = 97;

        String[] cord = coordinates.split("-");
        if (cord[0].length() != 1) {
            System.out.println(UtilityTools.RED + "\nErrore: Le coordinate sono state inserite male"
                    + UtilityTools.RESET);
            return false;
        }

        if (!UtilityTools.isNumeric(cord[1])) {
            System.out.println(UtilityTools.RED + "\nErrore: Le coordinate sono state inserite male"
                    + UtilityTools.RESET);
            return false;
        }

        if (cord[1].length() <= 2) {
            byte[] byteFirstCord = cord[0].getBytes(StandardCharsets.US_ASCII);
            column = byteFirstCord[0] - coverter;
            row = Integer.parseInt(cord[1]) - 1;
            if (column >= 0 && column <= mat.getColumnLength() - 1
                    && row >= 0 && row <= mat.getRowLength() - 1) {
                result = true;
            } else {
                System.out.println(UtilityTools.RED + "\nErrore: Le coordinate sono state inserite male"
                        + UtilityTools.RESET);
            }
        } else {
            System.out.println(UtilityTools.RED + "\nErrore: Le coordinate sono state inserite male"
                    + UtilityTools.RESET);
        }

        return result;
    }
}
