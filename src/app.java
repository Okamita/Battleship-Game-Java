import menu.CommandsMainMenu;
import utility.UtilityTools;
import game.Settings;
import keyboardInput.MainMenuInput;

/** {@literal <<Control>>}
 * Questo è il main della nostra applicazione.
 * Qui sono presenti diverse responsabilità:
 * Gestione del menu: Il main è responsabile di gestire il menu principale del gioco.
 * Utilizza uno switch-case per determinare l'azione da intraprendere in base alla scelta dell'utente.
 * Interazione con l'utente: Il main riceve l'input dell'utente tramite la classe MainMenuInput.
 * Questa classe si occupa di gestire l'input dell'utente e di comunicarlo al main.
 * Inizializzazione delle impostazioni di gioco: Il main crea un'istanza della classe Settings
 * per gestire le impostazioni di gioco. Le impostazioni vengono modificate durante l'esecuzione
 * del gioco in base alle scelte dell'utente nel menu.
 * Gestione degli argomenti della riga di comando: Il main verifica se sono stati passati argomenti
 * sulla riga di comando e, se necessario, mostra l'aiuto o segnala eventuali errori.
 * Iterazione nel menu principale: Il main esegue un ciclo while finché il valore della variabile
 * mainMenuLoopValue nelle impostazioni è vero. Questo consente all'utente d'interagire ripetutamente
 * con il menu fino a quando non decide di uscire dal gioco.
 * Chiamata ai comandi del menu: In base alla scelta dell'utente, il main chiama i metodi appropriati
 * nella classe CommandsMainMenu. Questi metodi sono responsabili di eseguire le azioni richieste,
 * come avviare il gioco, mostrare l'aiuto, impostare la difficoltà, visualizzare le informazioni
 * sulla griglia, ecc.
 * Gestione dell'output: Il main è responsabile di stampare i messaggi di output sullo schermo,
 * ad esempio i messaggi di errore, i messaggi di conferma e le istruzioni per tornare al menu.
 * Chiusura delle risorse: Alla fine del gioco, il main chiude l'istanza di Scanner utilizzata
 * per l'input dell'utente.
 * In generale, il main dell'applicazione della battaglia navale coordina il flusso del gioco,
 * gestisce l'input dell'utente, chiama i comandi appropriati e visualizza l'output corrispondente. */
public final class app {

    /** È il metodo principale del progetto da dove,
     * attraverso uno switch case è possibile gestire tutti i comandi implementati nel gioco.
     * @param args Command line arguments */
    public static void main(final String[] args) {

        int mainMenu = 1;

        CommandsMainMenu commandsMainMenu = new CommandsMainMenu();
        MainMenuInput menuInput = new MainMenuInput();
        Settings settings = Settings.getIstance();

        if (args.length == 1) {
            if (args[0].equals("-h") || args[0].equals("--help")) {
                commandsMainMenu.help();
                UtilityTools.SCANNER.nextLine();
            }
        } else if (args.length > 1) {
            System.out.println(UtilityTools.RED + "\nErrore: Sono stati inseriti troppi argomenti"
            + UtilityTools.RESET);
            System.exit(0);
        }

        while (settings.getMainMenuLoopValue()) {
            menuInput.takeInput(mainMenu);
            switch (settings.getCurrentState()) {
                default -> System.out.println(UtilityTools.RED + "Nessun UserChoice trovato" + UtilityTools.RESET);
                case GIOCA -> commandsMainMenu.play();
                case HELP -> commandsMainMenu.help();
                case ESCI -> commandsMainMenu.exit();
                case FACILE -> commandsMainMenu.setEasyDifficulty();
                case MEDIO -> commandsMainMenu.setMediumDifficulty();
                case DIFFICILE -> commandsMainMenu.setHardDifficulty();
                case FACILETENTATIVI -> commandsMainMenu.setEasyAttempts();
                case MEDIOTENTATIVI -> commandsMainMenu.setMediumAttempts();
                case DIFFICILETENTATIVI -> commandsMainMenu.setHardAttempts();
                case PERSONALIZZATA -> commandsMainMenu.setPersonalizedAttempts();
                case MOSTRANAVI -> commandsMainMenu.printShipsSet();
                case MOSTRALIVELLO -> commandsMainMenu.showLevel();
                case SVELAGRIGLIA -> commandsMainMenu.showGrid();
                case TEMPO -> commandsMainMenu.setTime();
                case STANDARD -> commandsMainMenu.standardGrid();
                case LARGE -> commandsMainMenu.largeGrid();
                case EXTRALARGE -> commandsMainMenu.extraLargeGrid();
                case MOSTRATEMPO -> commandsMainMenu.showTime();
                }

            if (settings.getMainMenuLoopValue()) {
                System.out.println("\nPremere " + UtilityTools.GREEN + "INVIO "
                        + UtilityTools.RESET + "per andare al menu...");
                UtilityTools.SCANNER.nextLine();
            }
        }
        UtilityTools.SCANNER.close();
    }
}
