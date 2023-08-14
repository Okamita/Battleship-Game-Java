package menu;

import game.Settings;
import utility.UtilityTools;

/** {@literal <<Control>>}
 * Classe che contiene tutti i comandi comuni e i valori comuni tra i menu di gioco e del main.
 * La responsabilità della classe Commands è quella di gestire i comandi comuni e i valori comuni tra
 * i menu di gioco e il menu principale. La classe fornisce metodi per stampare le informazioni sulle navi
 * e il numero di esemplari per ogni nave, nonché per visualizzare il livello di gioco e il numero massimo
 * di tentativi falliti. La classe utilizza la classe Settings per accedere alle impostazioni di gioco
 * condivise attraverso il pattern Singleton. */
class Commands {

        /** Costruttore di default della classe, aggiunto per warning di Javadoc. */
        Commands() { };

        /** Utilizzo del Singleton per accedere a variabili condivise. */
        private Settings settings = Settings.getIstance();

        /** Metodo per visualizzare le navi e il numero di esemplari per ogni nave. */
        public void printShipsSet() {
                System.out.println(UtilityTools.GREEN + "\nDi seguito sono rappresentati gli "
                        + "esemplari per ogni nave: " + UtilityTools.RESET);
                System.out.println("- Cacciatorpediniere " + UtilityTools.CYAN + "⊠⊠         "
                        + UtilityTools.RESET + "Esemplari: " + UtilityTools.CYAN + "4" + UtilityTools.RESET);
                System.out.println("- Incrociatore       " + UtilityTools.CYAN + "⊠⊠⊠       "
                        + UtilityTools.RESET + "Esemplari: " + UtilityTools.CYAN + "3" + UtilityTools.RESET);
                System.out.println("- Corazzata          " + UtilityTools.CYAN + "⊠⊠⊠⊠     "
                        + UtilityTools.RESET + "Esemplari: " + UtilityTools.CYAN + "2" + UtilityTools.RESET);
                System.out.println("- Portaerei          " + UtilityTools.CYAN + "⊠⊠⊠⊠⊠   "
                        + UtilityTools.RESET + "Esemplari: " + UtilityTools.CYAN + "1" + UtilityTools.RESET);
        }

        /** Metodo per visualizzare il livello di gioco e il numero massimo di tentativi falliti. */
        public void showLevel() {
                System.out.println("\nLivello di gioco: "
                + UtilityTools.GREEN + settings.getCurrentDifficulty() + UtilityTools.RESET);
                System.out.println("Numero massimo di tentativi falliti: "
                + UtilityTools.GREEN + settings.getCurrentDifficultyNumTry() + UtilityTools.RESET);
        }
}
