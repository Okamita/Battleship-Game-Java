package menu;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;

import game.Settings;
import grid.Grid;
import keyboardInput.ConfirmInput;
import utility.UtilityTools;

/** {@literal <<Control>>}
 * Classe che contiene tutti i comandi del menu di gioco.
 * La responsabilità della classe CommandsGame è quella di gestire i comandi del menu di gioco.
 * La classe fornisce metodi per uscire dal gioco, visualizzare le informazioni sulle navi e il numero di esemplari
 * per ogni nave, visualizzare il livello di gioco e il numero massimo di tentativi falliti, visualizzare la griglia
 * di gioco, visualizzare la griglia di gioco senza i colpi a vuoto e visualizzare la griglia di gioco con le navi
 * nemiche. */
public class CommandsGame extends Commands {

    /** Costruttore di default della classe, aggiunto per warning di Javadoc. */
    public CommandsGame() { };

    /** Utilizzo del Singleton per accedere a variabili condivise. */
    private Settings settings = Settings.getIstance();

    /** Esce dal menu di gioco e dal menu principale, facendoti chiudere l'applicazione. */
    public void exit() {
        final int gameMenuExitConfirm = 4;
        ConfirmInput confirmInput = new ConfirmInput();

        if (confirmInput.isConfirmed(gameMenuExitConfirm)) {
            System.out.println(UtilityTools.GREEN + "\nGrazie per aver giocato a Battleship, ti meriti un cornetto!"
            + UtilityTools.RESET);
            settings.setMainMenuLoop(false);
            settings.setGameMenuLoop(false);
        } else {
            settings.setMainMenuLoop(true);
            settings.setGameMenuLoop(true);
        }
    }

    /** Stampa una lista dei comandi utilizzabili dall'utente. */
    public void help() {
        System.out.println(UtilityTools.YELLOW + "\nPer interagire con l'applicazione, "
                + "vanno utilizzati i seguenti comandi:");
        System.out.println("- /help: " + UtilityTools.RESET + "Mostra l’help con l’elenco dei comandi");
        System.out.println(UtilityTools.YELLOW + "- /esci: " + UtilityTools.RESET + "Chiude il gioco");
        System.out.println(UtilityTools.YELLOW + "- /mostralivello: " + UtilityTools.RESET
                + "Mostra il livello di gioco e il numero massimo di tentativi falliti");
        System.out.println(UtilityTools.YELLOW + "- /mostranavi: " + UtilityTools.RESET
                + "Mostra i tipi di nave e la loro quantita'");
        System.out.println(UtilityTools.YELLOW + "- /mostratentativi: " + UtilityTools.RESET
                + "Mostra la griglia di gioco");
        System.out.println(UtilityTools.YELLOW + "- /svelagriglia: " + UtilityTools.RESET
                + "Mostra la griglia di gioco");
        System.out.println(UtilityTools.YELLOW + "- /mostragriglia: " + UtilityTools.RESET
                + "Mostra la griglia di gioco senza i colpi a vuoto");
        System.out.println(UtilityTools.YELLOW + "- /mostratempo: " + UtilityTools.RESET
                + "Mostra il tempo di gioco");
        System.out.println(UtilityTools.YELLOW + "- /abbandona: " + UtilityTools.RESET
                + "Permette di tornare al menu principale");
    }

    /** Al comando /svelagriglia, il metodo stampa la griglia di gioco.
     * @param grid griglia da stampare del menú di gioco */
    public void showGrid(final Grid grid) {
        System.out.println();
        grid.printGrid();
    }

    /** Al comando /mostragriglia, il metodo stampa la griglia di gioco
     * senza visualizzare le i punti a vuoto.
     * @param grid griglia da stampare del menú di gioco */
    public void showGridWithoutMiss(final Grid grid) {
        System.out.println();
        grid.printGridWithoutMiss();
    }

    /** Metodo che stampa il tempo rimanente. */
    public void showTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(currentTime, settings.getEndTime());
        System.out.println();
        System.out.println("Tempo rimanente: " + UtilityTools.GREEN + duration.toMinutes()
                + UtilityTools.RESET + " minuti e " + UtilityTools.CYAN
                + duration.toSecondsPart() + UtilityTools.RESET + " secondi.");
    }

    /** Metodo che attacca la griglia avversaria.
     * @param gridEnemy Griglia avversaria
     * @param gridPlayer Griglia del giocatore
     * @param coordinate Coordinate in cui attaccare
     * @param testCondition Indica se testare le condizioni di vittoria o meno, utile per permettere
     * magari una modalitá senza queste restrizioni. */
    public void attack(final Grid gridEnemy, final Grid gridPlayer, final String coordinate,
    final boolean testCondition) {
        int column = 0;
        int row = 0;
        final int offSet = 97;
        String[] cord = coordinate.split("-");
        byte[] byteFirstCord = cord[0].getBytes(StandardCharsets.US_ASCII);
        column = byteFirstCord[0] - offSet;
        row = Integer.parseInt(cord[1]) - 1;
        writeEnemyGrid(gridEnemy, row, column);
        writePlayerGrid(gridEnemy, gridPlayer, row, column);

        if (testCondition) {
            winLoseCondition(gridEnemy);
        }
    }

    /** Metodo che controlla se hai vinto.
     * @param gridEnemy Griglia avversaria */
    private void winLoseCondition(final Grid gridEnemy) {

        if (isTheGameEnd(gridEnemy) == 1) {
            System.out.println(UtilityTools.RED + "\nIl tempo è finito, hai perso! "
                    + "\nStai per tornare al menu principale" + UtilityTools.RESET);
            UtilityTools.SCANNER.nextLine();
            settings.setGameMenuLoop(false);
        } else if (isTheGameEnd(gridEnemy) == 2) {
            System.out.println(UtilityTools.RED + "\nHai finito i tentativi disponibili, hai perso! "
                    + "\nStai per tornare al menu principale" + UtilityTools.RESET);
            UtilityTools.SCANNER.nextLine();
            settings.setGameMenuLoop(false);
        } else if (isTheGameEnd(gridEnemy) == 0) {
            UtilityTools.printWin();
            System.out.println(UtilityTools.GREEN + "\nHai vinto, stai per tornare al menu principale"
                    + UtilityTools.RESET);
            UtilityTools.SCANNER.nextLine();
            settings.setGameMenuLoop(false);
        }
    }

    /** Metodo che controlla se c'è qualche nave affondata.
     * @param gridEnemy Griglia avversaria
     * @param row Riga della nave affondata
     * @param column Colonna della nave affondata
     * @return true se c'è una nave affondata, false altrimenti */
    private boolean isSinked(final Grid gridEnemy, final int row, final int column) {
        boolean isSinked = true;
        int i = 0;

        //Controllo della direzione Nord quando si controllano determinate posizioni della griglia
        if (row == gridEnemy.getRowLength() - 1 && column == 0
            || row == gridEnemy.getRowLength() - 1 && column == gridEnemy.getColumnLength() - 1
            || column > 0 && column < gridEnemy.getColumnLength() - 1 && row == gridEnemy.getRowLength() - 1
            || row > 0 && row < gridEnemy.getRowLength() - 1 && column == gridEnemy.getColumnLength() - 1
            || row > 0 && row < gridEnemy.getRowLength() - 1 && column == 0) {

            while (row - i >= 0 && isSinked && gridEnemy.getGridValue(row - i, column) != '-'
            && gridEnemy.getGridValue(row - i, column) != 'O') {
                if (gridEnemy.getGridValue(row - i, column) == 'S') {
                    isSinked =  false;
                    return isSinked;
                }
                i++;
            }
        }

        //Controllo della direzione Sud quando si controllano determinate posizioni della griglia
        if (row == 0 && column == 0
            || row > 0 && row < gridEnemy.getRowLength() - 1 && column == gridEnemy.getColumnLength() - 1
            || column > 0 && column < gridEnemy.getColumnLength() - 1 && row == 0
            || column == gridEnemy.getColumnLength() - 1 && row == 0
            || row > 0 && row < gridEnemy.getRowLength() - 1 && column == 0) {

            i = 0;
            while (row + i < gridEnemy.getRowLength() && isSinked
            && gridEnemy.getGridValue(row + i, column) != '-' && gridEnemy.getGridValue(row + i, column) != 'O') {
                if (gridEnemy.getGridValue(row + i, column) == 'S') {
                    isSinked =  false;
                    return isSinked;
                }
                i++;
            }
        }

        //Controllo della direzione Ovest quando si controllano determinate posizioni della griglia
        if (column > 0 && column < gridEnemy.getColumnLength() - 1 && row == 0
            || column > 0 && column < gridEnemy.getColumnLength() - 1 && row == gridEnemy.getRowLength() - 1
            || column == gridEnemy.getColumnLength() - 1  && row == 0
            || row > 0 && row < gridEnemy.getRowLength() - 1 && column == gridEnemy.getColumnLength() - 1
            || row == gridEnemy.getRowLength() - 1 && column == gridEnemy.getColumnLength() - 1) {

            i = 0;
            while (column - i >= 0 && isSinked && gridEnemy.getGridValue(row, column - i) != '-'
            && gridEnemy.getGridValue(row, column - i) != 'O') {
                if (gridEnemy.getGridValue(row, column - i) == 'S') {
                    isSinked =  false;
                    return isSinked;
                }
                i++;
            }
        }

        //Controllo della direzione Est quando si controllano determinate posizioni della griglia
        if (row == 0 && column == 0
        || row > 0 && row < gridEnemy.getRowLength() - 1 && column == 0
        || row == gridEnemy.getRowLength() - 1 && column == 0
        || column > 0 && column < gridEnemy.getColumnLength() - 1 && row == 0
        || column > 0 && column < gridEnemy.getColumnLength() - 1 && row == gridEnemy.getRowLength() - 1) {
            i = 0;
            while (column + i < gridEnemy.getColumnLength() && isSinked
            && gridEnemy.getGridValue(row, column + i) != '-' && gridEnemy.getGridValue(row, column + i) != 'O') {
                if (gridEnemy.getGridValue(row, column + i) == 'S') {
                    isSinked =  false;
                    return isSinked;
                }
                i++;
            }
        }

        //Se non ci troviamo in nessun bordo, controlliaamo tutte e 4 le coordinate, NORD-SUD-EST-OVEST
        if (column > 0 && column < gridEnemy.getColumnLength() - 1 && row > 0 && row < gridEnemy.getRowLength() - 1) {
            i = 0;
            //Controllo direzione NORD
            while (row - i >= 0 && isSinked && gridEnemy.getGridValue(row - i, column) != '-'
            && gridEnemy.getGridValue(row - i, column) != 'O') {
                if (gridEnemy.getGridValue(row - i, column) == 'S') {
                    isSinked =  false;
                    return isSinked;
                }
                i++;
            }

            i = 0;
            //Controllo direzione OVEST
            while (column - i >= 0 && isSinked && gridEnemy.getGridValue(row, column - i) != '-'
            && gridEnemy.getGridValue(row, column - i) != 'O') {
                if (gridEnemy.getGridValue(row, column - i) == 'S') {
                    isSinked =  false;
                    return isSinked;
                }
                i++;
            }

            i = 0;
            //Controllo direzione EST
            while (column + i < gridEnemy.getColumnLength() && isSinked
            && gridEnemy.getGridValue(row, column + i) != '-' && gridEnemy.getGridValue(row, column + i) != 'O') {
                if (gridEnemy.getGridValue(row, column + i) == 'S') {
                    isSinked =  false;
                    return isSinked;
                }
                i++;
            }

            i = 0;
            //Controllo direzione SUD
            while (row + i < gridEnemy.getRowLength() && isSinked && gridEnemy.getGridValue(row + i, column) != '-'
            && gridEnemy.getGridValue(row + i, column) != 'O') {
                if (gridEnemy.getGridValue(row + i, column) == 'S') {
                    isSinked =  false;
                    return isSinked;
                }
                i++;
            }
        }
        return true;
    }

    /** Scrive le navi affondate nella griglia avversaria.
     * @param gridEnemy Griglia avversaria
     * @param row Riga della nave affondata
     * @param column Colonna della nave affondata */
    private void writeSinkedShips(final Grid gridEnemy, final int row, final int column) {
        int i = 0;

        //Se non ci troviamo in nessun bordo, scriviam in tutte e 4 le coordinate, NORD-SUD-EST-OVEST
        if (column > 0 && column < gridEnemy.getColumnLength() - 1 && row > 0 && row < gridEnemy.getRowLength() - 1) {

            i = 0;
            //Controllo direzione NORD
            while (row - i >= 0 && gridEnemy.getGridValue(row - i, column) != '-'
                                && gridEnemy.getGridValue(row - i, column) != 'O') {
                gridEnemy.setGridValue(row - i, column, 'X');
                i++;
            }

            i = 0;
            //Controllo direzione SUD
            while (row + i < gridEnemy.getRowLength() && gridEnemy.getGridValue(row + i, column) != '-'
                                                      && gridEnemy.getGridValue(row + i, column) != 'O') {
                gridEnemy.setGridValue(row + i, column, 'X');
                i++;
            }

            i = 0;
            //Controllo direzione EST
            while (column + i < gridEnemy.getColumnLength() && gridEnemy.getGridValue(row, column + i) != '-'
                                                            && gridEnemy.getGridValue(row, column + i) != 'O') {
                gridEnemy.setGridValue(row, column + i, 'X');
                i++;
            }

            //Controllo direzione OVEST
            i = 0;
            while (column - i >= 0 && gridEnemy.getGridValue(row, column - i) != '-'
                                  && gridEnemy.getGridValue(row, column - i) != 'O') {
                gridEnemy.setGridValue(row, column - i, 'X');
                i++;
            }
        }

        if (row == gridEnemy.getRowLength() - 1 && column == 0
                || row == gridEnemy.getRowLength() - 1 && column == gridEnemy.getColumnLength() - 1
                || column > 0 && column < gridEnemy.getColumnLength() - 1 && row == gridEnemy.getRowLength() - 1
                || row > 0 && row < gridEnemy.getRowLength() - 1 && column == gridEnemy.getColumnLength() - 1
                || row > 0 && row < gridEnemy.getRowLength() - 1 && column == 0) {
            i = 0;
            //Controllo direzione NORD
            while (row - i >= 0 && gridEnemy.getGridValue(row - i, column) != '-'
                                && gridEnemy.getGridValue(row - i, column) != 'O') {
                gridEnemy.setGridValue(row - i, column, 'X');
                i++;
            }
        }

        if (row == 0 && column == 0
                || row > 0 && row < gridEnemy.getRowLength() - 1 && column == gridEnemy.getColumnLength() - 1
                || column > 0 && column < gridEnemy.getColumnLength() - 1 && row == 0
                || column == gridEnemy.getColumnLength() - 1 && row == 0
                || row > 0 && row < gridEnemy.getRowLength() - 1 && column == 0) {
            i = 0;
            //Controllo direzione SUD
            while (row + i < gridEnemy.getRowLength() - 1 && gridEnemy.getGridValue(row + i, column) != '-'
                                                          && gridEnemy.getGridValue(row + i, column) != 'O') {
                gridEnemy.setGridValue(row + i, column, 'X');
                i++;
            }
        }

        if (column > 0 && column < gridEnemy.getColumnLength() - 1 && row == 0
                || column > 0 && column < gridEnemy.getColumnLength() - 1 && row == gridEnemy.getRowLength() - 1
                || column == gridEnemy.getColumnLength() - 1 && row == 0
                || row > 0 && row < gridEnemy.getRowLength() - 1 && column == gridEnemy.getColumnLength() - 1
                || row == gridEnemy.getRowLength() - 1 && column == gridEnemy.getColumnLength() - 1) {
            //Controllo direzione OVEST
            i = 0;
            while (column - i >= 0 && gridEnemy.getGridValue(row, column - i) != '-'
                                   && gridEnemy.getGridValue(row, column - i) != 'O') {
                gridEnemy.setGridValue(row, column - i, 'X');
                i++;
            }
        }

        if (row == 0 && column == 0
                || row > 0 && row < gridEnemy.getRowLength() - 1 && column == 0
                || row == gridEnemy.getRowLength() - 1 && column == 0
                || column > 0 && column < gridEnemy.getColumnLength() - 1 && row == 0
                || column > 0 && column < gridEnemy.getColumnLength() - 1 && row == gridEnemy.getRowLength() - 1) {
            i = 0;
            //Controllo direzione EST
            while (column + i < gridEnemy.getColumnLength() - 1 && gridEnemy.getGridValue(row, column + i) != '-'
                                                                && gridEnemy.getGridValue(row, column + i) != 'O') {
                gridEnemy.setGridValue(row, column + i, 'X');
                i++;
            }
        }
    }

    /** Metodo che colpisce la nave del giocatore.
     * @param gridEnemy Griglia avversaria
     * @param gridPlayer Griglia del giocatore
     * @param row Riga della griglia avversaria
     * @param column Colonna della griglia avversaria */
    private void writePlayerGrid(final Grid gridEnemy, final Grid gridPlayer, final int row, final int column) {
        if (gridEnemy.getGridValue(row, column) == 'X' || gridEnemy.getGridValue(row, column) == '$') {
            gridPlayer.setGridValue(row, column, '$');
        } else {
            gridPlayer.setGridValue(row, column, 'O');
        }

        if (isSinked(gridEnemy, row, column)) {
            writeSinkedShips(gridPlayer, row, column);
        }
    }

    /** Metodo che colpisce la nave dell'avversario.
     * @param gridEnemy Griglia avversaria
     * @param row Riga della griglia avversaria
     * @param column Colonna della griglia avversaria */
    private void writeEnemyGrid(final Grid gridEnemy, final int row, final int column) {

        if (gridEnemy.getGridValue(row, column) == 'S') {
            gridEnemy.setGridValue(row, column, '$');
            if (isSinked(gridEnemy, row, column)) {
                System.out.println(UtilityTools.PURPLE + "\nColpito e affondato" + UtilityTools.RESET);
                writeSinkedShips(gridEnemy, row, column);
            } else {
                System.out.println(UtilityTools.YELLOW + "\nColpito" + UtilityTools.RESET);
            }
        } else if (gridEnemy.getGridValue(row, column) == '-') {
            gridEnemy.setGridValue(row, column, 'O');
            System.out.println(UtilityTools.CYAN + "\nBuco nell'acqua" + UtilityTools.RESET);
            settings.setFailures(settings.getFailures() + 1);
        } else {
            System.out.println(UtilityTools.YELLOW + "\nPosizione gia' attaccata" + UtilityTools.RESET);
        }
    }

    /** Metodo che controlla se è finito il gioco.
     * @param mat Griglia avversaria
     * @return Zero se il gioco non è finito, Uno se il tempo è scaduto,
     * Due se il numero di tentativi è finito, Quattro se tutte le navi sono affondate. */
    private int isTheGameEnd(final Grid mat) {
        int isTheGameEnd = 0;
        final int isNotEnded = 3;
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(currentTime, settings.getEndTime());
        if (duration.getSeconds() <= 0) {
            return 1;
        } else if (settings.getFailures() == settings.getCurrentDifficultyNumTry()) {
            return 2;
        }

        for (int i = 0; i < mat.getRowLength() - 1; i++) {
            for (int j = 0; j < mat.getColumnLength() - 1; j++) {
                if (mat.getGridValue(i, j) == 'S') {
                    isTheGameEnd = isNotEnded;
                }
            }
        }
        return isTheGameEnd;
    }

    /** Metodo che permette di abbandonare la partita e tornare al menu principale. */
    public void abandon() {
        final int gameMenuAbandonConfirm = 5;
        ConfirmInput confirmInput = new ConfirmInput();

        if (confirmInput.isConfirmed(gameMenuAbandonConfirm)) {
            System.out.println();
            settings.getEnemyGrid().printGrid();
            settings.setGameMenuLoop(false);
        } else {
            settings.setGameMenuLoop(true);
        }
    }

    /** Metodo che permette di vedere quanti sono i tentativi totali, rimanenti e falliti. */
    public void showFailures() {
        System.out.println("\nTentativi totali: " + UtilityTools.PURPLE
                        + (settings.getCurrentDifficultyNumTry()) + UtilityTools.RESET);
        System.out.println("Tentativi rimasti: " + UtilityTools.GREEN
                        + (settings.getCurrentDifficultyNumTry() - settings.getFailures()) + UtilityTools.RESET);
        System.out.println("Tentativi falliti: " + UtilityTools.CYAN
                        + (settings.getFailures()) + UtilityTools.RESET);
    }
}
