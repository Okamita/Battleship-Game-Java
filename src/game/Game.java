package game;

import java.util.Random;
import grid.Grid;
import keyboardInput.GameMenuInput;
import menu.CommandsGame;
import utility.UtilityTools;

/** {@literal <<Control>>}
 * Classe che si occupa della creazione o gestione della partita.
 * La classe Game ha la responsabilità di gestire la creazione e lo svolgimento di una partita del gioco.
 * Ecco le principali responsabilità della classe:
 * Creazione della griglia del giocatore e del nemico.
 * Inizializzazione della partita, inclusa la posizionamento casuale delle navi sulle griglie.
 * Gestione del menu di gioco, consentendo al giocatore di eseguire varie azioni e attacchi.
 * Piazzamento delle navi in modo casuale sulla griglia nemica.
 * Verifica se una nave può essere posizionata in una determinata posizione sulla griglia.
 * Effettua controlli sulla legalità di una posizione nella griglia, verificando la presenza di navi adiacenti.
 * Fornisce metodi di accesso per ottenere e modificare i valori dell'array shipsArray.
 * Utilizza un oggetto Random per generare numeri casuali nel posizionamento delle navi.
 * In generale, la classe Game coordina tutte le operazioni necessarie per avviare e gestire
 * una partita del gioco, inclusi il posizionamento delle navi e le interazioni con l'utente
 * attraverso il menu di gioco. */
public class Game {

    /**Oggetto per generare numeri randomici. */
    private Random random;

    /** Array contenente le navi e la loro lunghezza. */
    private final int[] shipsArray = {0, 0, 4, 3, 2, 1};

    /** Costante che indica la direzione orizzontale per il piazzamento randomico di navi. */
    private static final int HORIZONTAL = 0;

    /** Costante che indica la direzione verticale per il piazzamento randomico di navi. */
    private static final int VERTICAL = 1;

    /** Dimensione massima della riga. */
    private int dimRowMax;

    /** Dimensione massima della colonna. */
    private int dimColumnMax;

    /** Utilizzo del Singleton per accedere a variabili condivise. */
    private Settings settings = Settings.getIstance();

    /** Il costruttore della classe crea le griglie del nemico dove ha posizionato le navi e quella del giocatore.
     * @param fixedDimRow cardinalità delle righe della griglia
     * @param fixedDimColumn cardinalità delle colonne della griglia */
    public Game(final int fixedDimRow, final int fixedDimColumn) {
        dimRowMax = fixedDimRow;
        dimColumnMax = fixedDimColumn;

        settings.setEnemyGrid(new Grid(dimRowMax, dimColumnMax));
        settings.setPlayerGrid(new Grid(dimRowMax, dimColumnMax));
        settings.setFailures(0);

        random = new Random();
    }

    /** Metodo di accesso per accedere il valore di shipsArray.
     * @param index l'indice dal quale vogliamo prendere il valore
     * @return shipsArray[index] ovvero il valore presente nell'indice index dell'array */
    public int getShipsArrayValue(final int index) {
        return shipsArray[index];
    }

    /** Metodo di accesso per modificare il valore di shipsArray.
     * @param index l'indice dal quale vogliamo assegnare il valore
     * @param value il valore da assegnare */
    public void setShipsArrayValue(final int index, final int value) {
        shipsArray[index] = value;
    }

    /** Metodo che si occupa di inizializzare la partita, creando una griglia vuota e riempendola randomicamente.
     * Infine entra nel menú di gioco. */
    public void startNewGame() {
        System.out.println("La griglia e' stata inizializzata randomicamente premere "
                + UtilityTools.GREEN + "INVIO" + UtilityTools.RESET + " per iniziare la partita...");
        UtilityTools.SCANNER.nextLine();
        placeShipsRandom();
        gameMenu();
    }

    /** Metodo che rappresenta il menú di gioco, dove il giocatore puó utilizzare diversi comandi o attaccare. */
    private void gameMenu() {
        final int gameMenu = 3;
        CommandsGame commandsGame = new CommandsGame();
        GameMenuInput gameMenuInput = new GameMenuInput();

        while (settings.getGameMenuLoopValue()) {
            gameMenuInput.takeInput(gameMenu);
            switch (settings.getCurrentState()) {
                default -> System.out.println(UtilityTools.RED + "Nessun UserChoice trovato" + UtilityTools.RESET);
                case HELP -> commandsGame.help();
                case ESCI -> commandsGame.exit();
                case MOSTRANAVI -> commandsGame.printShipsSet();
                case MOSTRALIVELLO -> commandsGame.showLevel();
                case SVELAGRIGLIA -> commandsGame.showGrid(settings.getEnemyGrid());
                case MOSTRATEMPO -> commandsGame.showTime();
                case ATTACCA -> commandsGame.attack(settings.getEnemyGrid(), settings.getPlayerGrid(),
                gameMenuInput.getUserInput().toLowerCase(), true);
                case MOSTRAGRIGLIA -> commandsGame.showGridWithoutMiss(settings.getPlayerGrid());
                case ABBANDONA -> commandsGame.abandon();
                case MOSTRATENTATIVI -> commandsGame.showFailures();
                }

            if (settings.getGameMenuLoopValue()) {
                System.out.println("\nPremere " + UtilityTools.GREEN + "INVIO" + UtilityTools.RESET
                        + " per andare al menu..." + UtilityTools.RESET);
                UtilityTools.SCANNER.nextLine();
            }
        }
    }

    /** Metodo che piazza tutte le navi presenti nell'array in modo randomico. */
    private void placeShipsRandom() {
        boolean isAllShipPlaced = false;
        final int maxFailAttemps = 400;
        final int numOfDirections = 2;

        while (!isAllShipPlaced) {
            int count = 0;
            settings.getEnemyGrid().resetGrid();

            for (int i = 2; i < shipsArray.length; i++) {
                for (int j = 1; j <= shipsArray[i]; j++) {

                    while (count < maxFailAttemps) {
                        int row = random.nextInt(dimRowMax);
                        int column = random.nextInt(dimRowMax);
                        int direction = random.nextInt(numOfDirections);

                        if (isShipPlaceable(i, settings.getEnemyGrid(), row, column, direction)) {
                            placeShip(i, settings.getEnemyGrid(), row, column, direction);
                            break;
                        }
                        count++;

                    }
                }
            }

            if (count < maxFailAttemps) {
                isAllShipPlaced = true;
            }
        }
    }

    /** Metodo che piazza una singola nave.
     * @param lengthShip usiamo la lunghezza della nave per capire quante caselle scrivere
     * @param grid la griglia in cui vogliamo scrivere
     * @param row la riga in cui vogliamo scrivere
     * @param column la colonna in cui vogliamo scrivere
     * @param direction usiamo la direzione per capire se piazzarla in verticale o orizzontale */
    private void placeShip(final int lengthShip, final Grid grid,
                          final int row, final int column, final int direction) {
        for (int i = 0; i < lengthShip; i++) {
            if (direction == HORIZONTAL) {
                grid.setGridValue(row, column + i, 'S');
            } else if (direction == VERTICAL) {
                grid.setGridValue(row  + i, column, 'S');
            }
        }
    }

    /** Metodo che controlla se la nave é piazzabile o meno.
     * @param lengthShip usiamo la lunghezza della nave per capire quante caselle controllare
     * @param grid la griglia che vogliamo controllare
     * @param row la riga in cui vogliamo controllare
     * @param column la colonna in cui vogliamo controllare
     * @param direction la direzione in cui vogliamo controllare
     * @return boolean vero se é possibile posizionare la nave, altrimenti falso */
    private boolean isShipPlaceable(final int lengthShip, final Grid grid,
                                    final int row, final int column, final int direction) {

        int count = 0;
        for (int i = 0; i < lengthShip; i++) {
            if (direction == HORIZONTAL) {
                if (isPositionLegal(grid, row, column + i)) {
                    count++;
                }
            } else if (direction == VERTICAL) {
                if (isPositionLegal(grid, row  + i, column)) {
                    count++;
                }
            }
        }

        if (count != lengthShip) {
            return false;
        }

        return true;
    }

    /** Fa i dovuti controlli per vedere se la casella ha delle navi attorno.
     * @param grid la griglia che vogliamo controllare
     * @param row la riga in cui vogliamo controllare
     * @param column la colonna in cui vogliamo controllare
     * @return boolean falso se trova non rispetta i vincoli o non trova il "mare"*/
    private boolean isPositionLegal(final Grid grid, final int row, final int column) {
        final int maxIndexRowColumn = dimRowMax - 1;

        if (row < 0 || row > maxIndexRowColumn || column < 0 || column > maxIndexRowColumn
                    || (grid.getGridValue(row, column) != '-')) {
            return false;
        }

        //Controlla posizione destra
        if (column < maxIndexRowColumn) {
            if (grid.getGridValue(row, column + 1) != '-') {
                return false;
            }
        }

        //Controlla posizione Bottom-right
        if (row < maxIndexRowColumn && column < maxIndexRowColumn) {
            if (grid.getGridValue(row + 1, column + 1) != '-') {
                return false;
            }
        }

        //Controlla posizione Top
        if (row > 0) {
            if (grid.getGridValue(row - 1, column) != '-') {
                return false;
            }
        }

        //Controlla posizione Sinistra*
        if (column > 0) {
            if (grid.getGridValue(row, column - 1) != '-') {
                return false;
            }
        }

        //Controlla posizione Top-Left*
        if (row > 0 && column > 0) {
            if (grid.getGridValue(row - 1, column - 1) != '-') {
                return false;
            }
        }

        //Controlla posizione Bottom-left
        if (row < maxIndexRowColumn  && column != 0) {
            if (grid.getGridValue(row + 1, column - 1) != '-') {
                return false;
            }
        }

        //Controlla posizione Top-right
        if (column != maxIndexRowColumn && row != 0) {
            if (grid.getGridValue(row - 1, column + 1) != '-') {
                return false;
            }
        }

        //Controlla posizione Bottom
        if (row != maxIndexRowColumn) {
            if (grid.getGridValue(row + 1, column) != '-') {
                return false;
            }
        }
        return true;
    }
}
