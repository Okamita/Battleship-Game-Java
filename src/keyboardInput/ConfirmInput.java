package keyboardInput;
import utility.UtilityTools;

/** {@literal <<Boundary>>}
 * Sottoclasse di KeyboardInput si occupa di prendere l'input di conferma.
 * La classe ConfirmInput è una sottoclasse di KeyboardInput e si occupa di gestire
 * l'input di conferma da parte dell'utente. La sua responsabilità principale è controllare
 * se l'input dell'utente corrisponde alle opzioni valide, che in questo caso sono "yes" e "no".
 * La classe fornisce il metodo isConfirmed che richiede all'utente di inserire una conferma
 * e restituisce true se l'input è "yes" e false se l'input è "no". Se l'input non è corretto,
 * la classe visualizza un messaggio di errore e richiede nuovamente l'input.
 * Inoltre, eredita il metodo takeInput dalla classe padre KeyboardInput per ottenere l'input dell'utente.
 * In generale, la classe ConfirmInput facilita l'acquisizione di input di conferma coerenti dall'utente. */
public class ConfirmInput extends KeyboardInput {

    /** Costruttore di default della classe. Non esegue alcuna operazione.
    * Aggiunto per prevenire il warning di Javadoc. */
    public ConfirmInput() { };

    /** Controlla che la stringa di input non abbia errori.
     * @return boolean se il l'input é sbagliato ritorna falso altrimenti vero */
    protected boolean isTheInputCorrect() {
        if (getUserInput().equalsIgnoreCase("yes") || getUserInput().equalsIgnoreCase("no")) {
            return true;
        }

        if (getUserInput().isEmpty()) {
            System.out.print(UtilityTools.RED + "\nErrore: Nessun comando inserito, premere INVIO "
                    + "per riprovare..." + UtilityTools.RESET);
        } else {
            System.out.print(UtilityTools.RED + "\nErrore: Solo le parole yes/no sono accettate, "
                    + "riprova con una parola corretta" + UtilityTools.RESET);
        }
        System.out.println();
        UtilityTools.SCANNER.nextLine();
        return false;
    };

    /** Metodo che controlla in base all'input se l'utente ha scritto yes oppure no.
     * @return boolean  Ritorna vero se l'utente scrive "yes", e falso se scrive "no"
     * @param menuIndicator Per indicare che print deve fare takeInput al refresh dello schermo */
    public boolean isConfirmed(final int menuIndicator) {
        takeInput(menuIndicator);

        if (getUserInput().equalsIgnoreCase("yes")) {
            return true;
        }
        return false;
    }
}
