package game;

import java.time.LocalDateTime;
import grid.Grid;

/** {@literal <<Entity>>}
 * Classe Singleton contenente i settaggi del gioco e le variabili condivise nel programma.
 * La classe Settings è responsabile di gestire i settaggi del gioco e le variabili condivise nel programma.
 * È una classe singleton, il che significa che può essere istanziata solo una volta e offre
 * un metodo statico getIstance() per ottenere l'istanza corrente.
 * La classe contiene diverse variabili di stato, come enemyGrid e playerGrid, che rappresentano le griglie
 * usate nella partita, endTime che indica il tempo di gioco, failures che conta il numero di tentativi
 * sbagliati dell'utente e currentState che rappresenta il comando impostato dall'utente.
 * Inoltre, la classe gestisce le impostazioni di difficoltà del gioco, come il numero di tentativi
 * per ogni difficoltà, e fornisce metodi per accedere e modificare queste impostazioni.
 * La classe Settings fornisce anche metodi per gestire i loop dei menu di gioco, impostare il tempo
 * di gioco, ottenere e impostare le griglie di gioco, nonché altre funzionalità correlate ai settaggi del gioco.
 * In sintesi, la classe Settings si occupa di gestire le impostazioni globali del gioco e le variabili
 * condivise tra le diverse parti del programma. */
public final class Settings {

    /** Enumerazione che rappresenta gli stati dei comandi. */
    public enum CommandsState {

        /** Comando per richiedere aiuto.*/
        HELP,

        /** Comando per uscire dall'applicazione. */
        ESCI,

        /** Comando per mostrare le navi presenti.*/
        MOSTRANAVI,

        /** Comando per mostrare il livello attuale. */
        MOSTRALIVELLO,

        /** Comando per svelare l'intera griglia di gioco. */
        SVELAGRIGLIA,

        /** Comando per iniziare il gioco. */
        GIOCA,

        /** Comando per impostare il livello di difficoltà facile. */
        FACILE,

        /** Comando per impostare il livello di difficoltà medio. */
        MEDIO,

        /** Comando per impostare il livello di difficoltà difficile.*/
        DIFFICILE,

        /** Comando per impostare il tempo di gioco. */
        TEMPO,

        /** Comando per impostare le dimensioni della griglia come standard. */
        STANDARD,

        /** Comando per impostare le dimensioni della griglia come grandi. */
        LARGE,

        /** Comando per impostare le dimensioni della griglia come extralarge. */
        EXTRALARGE,

        /** Comando per mostrare il tempo rimanente. */
        MOSTRATEMPO,

        /** Comando per impostare il numero di tentativi consentiti in modalità facile. */
        FACILETENTATIVI,

        /** Comando per impostare il numero di tentativi consentiti in modalità media. */
        MEDIOTENTATIVI,

        /** Comando per impostare il numero di tentativi consentiti in modalità difficile. */
        DIFFICILETENTATIVI,

        /** Comando per impostare una griglia personalizzata. */
        PERSONALIZZATA,

        /** Comando per mostrare la griglia di gioco. */
        MOSTRAGRIGLIA,

        /** Comando per mostrare il numero di tentativi rimanenti. */
        MOSTRATENTATIVI,

        /** Comando per attaccare una cella nella griglia di gioco. */
        ATTACCA,

        /** Comando per abbandonare il gioco. */
        ABBANDONA
    }


    /** Griglie del nemico dove vengono posizionate le navi da colpire. */
    private Grid enemyGrid;

    /** Griglia del giocatore dove si cerca di colpire le navi del nemico. */
    private Grid playerGrid;

    /** Attributo utilizzato come timer all'inizio della partita. */
    private LocalDateTime endTime;

    /** Attributo utilizzato come contatatore dei tentativi sbagliati dell'utente. */
    private int failures;

    /** Attributo che indica lo stato (enum) attuale del menu. */
    private CommandsState currentState;

    /** Attributo per la gestione del loop nel menu. */
    private boolean mainMenuLoop = true;

    /** Attributo per la gestione del loop nel menu di gioco. */
    private boolean gameMenuLoop = true;

    /** Attributo che indica il tempo massimo di gioco in minuti. */
    private int time;

    /** Attributo che contiene il numero di tentativi attuali. */
    private int currentDifficultyNumTry;

    /** Attributo che contiene il numero di tentativi della modalitá facile. */
    private int easyNumTry;

    /** Attributo che contiene il numero di tentativi della modalitá media. */
    private int mediumNumTry;

    /** Attributo che contiene il numero di tentativi della modalitá difficile. */
    private int hardNumTry;

    /** Costante che indica il valore di default dei tentativi della modalitá facile. */
    private static final int DEFAULT_EASY_NUM_TRY = 50;

    /** Costante che indica il valore di default dei tentativi della modalitá medio. */
    private static final int DEFAULT_MEDIUM_NUM_TRY = 30;

    /** Costante che indica il valore di default dei tentativi della modalitá difficile. */
    private static final int DEFAULT_HARD_NUM_TRY = 10;

    /** Attributo che rappresenta la difficoltà del gioco. */
    private CommandsState currentDifficulty;

    /** Attributo che indica il tempo minimo di gioco. */
    private int minValueTime;

    /** Istanza della classe singleton. */
    private static Settings istance;

    /** Costruttore della classe che setta alcuni parametri di default del gioco. */
    private Settings() {
        final int timeParam = 30;
        final int minTime = 3;
        setTime(timeParam);

        setEasyNumTry(DEFAULT_EASY_NUM_TRY);
        setMediumNumTry(DEFAULT_MEDIUM_NUM_TRY);
        setHardNumTry(DEFAULT_HARD_NUM_TRY);
        setMinValueTime(minTime);

        setCurrentDifficulty(CommandsState.MEDIO);
        setCurrentDifficultyNumTry(getMediumNumTry());
    }

    /** Metodo di accesso per assicurarci di avere solo un istanza.
     * @return istance ovvero l'oggeto della classe presente in memoria */
    public static Settings getIstance() {

        //Il cuore del Singleton
        if (istance == null) {
            istance = new Settings();
        }
        return istance;
    }

    /** Metodo di accesso a currentDifficultyNumTry.
     * @return il valore di tentativi correnti selezionati */
    public int getCurrentDifficultyNumTry() {
        return currentDifficultyNumTry;
    }

    /** Metodo di accesso a currentDifficultyNumTry.
     * @param paramCurrentDifficultyNumTry il valore che modifica l'attributo privato */
    public void setCurrentDifficultyNumTry(final int paramCurrentDifficultyNumTry) {
        currentDifficultyNumTry = paramCurrentDifficultyNumTry;
    }

    /** Metodo di accesso a easyNumTry.
     * @return il valore di easyNumTry */
    public int getEasyNumTry() {
        return easyNumTry;
    }

    /** Metodo di accesso a easyNumTry.
     * @param paramEasyNumTry il valore che modifica l'attributo privato */
    public void setEasyNumTry(final int paramEasyNumTry) {
        easyNumTry = paramEasyNumTry;
    }

    /** Metodo di accesso a mediumNumTry.
     * @return il valore di mediumNumTry */
    public int getMediumNumTry() {
        return mediumNumTry;
    }

    /** Metodo di accesso a mediumNumTry.
     * @param paramMediumNumTry il valore che modifica l'attributo privato */
    public void setMediumNumTry(final int paramMediumNumTry) {
        mediumNumTry = paramMediumNumTry;
    }

    /** Metodo di accesso a hardNumTry.
     * @return il valore di hardNumTry */
    public int getHardNumTry() {
        return hardNumTry;
    }

    /** Metodo di accesso a mediumNumTry.
     * @param paramMediumNumTry il valore che modifica l'attributo privato */
    public void setHardNumTry(final int paramMediumNumTry) {
        hardNumTry = paramMediumNumTry;
    }

    /** Metodo di accesso a currentDifficulty.
     * @return difficulty ovvero ritorna il valore difficulty */
    public CommandsState getCurrentDifficulty() {
        return  currentDifficulty;
    }

    /** Metodo di accesso a currentDifficulty.
     * @param difficultyParam il valore da assegnare a difficulty */
    public void setCurrentDifficulty(final CommandsState difficultyParam) {
        currentDifficulty = difficultyParam;

        if (currentDifficulty == CommandsState.FACILE) {
            setCurrentDifficultyNumTry(getEasyNumTry());
        } else if (currentDifficulty == CommandsState.MEDIO) {
            setCurrentDifficultyNumTry(getMediumNumTry());
        } else if (currentDifficulty == CommandsState.DIFFICILE) {
            setCurrentDifficultyNumTry(getHardNumTry());
        }
    }

    /** Accede all'attributo currentState.
     * @return currentState */
    public CommandsState getCurrentState() {
        return currentState;
    }

    /** Imposta CurrentState uguale al parametro passatogli come argomento.
     * @param paramCurrentState Utilizzato nell'assegnazione di CurrentState */
    public void setCurrentState(final CommandsState paramCurrentState) {
        currentState = paramCurrentState;
    }

    /** Metodo di accesso all'attributo mainMenuLoop.
     * @param value Utilizzato nell' assegnazione di menuLoop */
    public void setMainMenuLoop(final boolean value) {
        mainMenuLoop = value;
    }

    /** Metodo di accesso all'attributo mainMenuLoop.
     * @return MenuLoop ritorna sempre menuLoop */
    public boolean getMainMenuLoopValue() {
        return mainMenuLoop;
    }

    /** Metodo di accesso all'attributo gameMenuLoop.
     * @param value Utilizzato nell' assegnazione di gameMenuLoop */
    public void setGameMenuLoop(final boolean value) {
        gameMenuLoop = value;
    }

    /** Metodo di accesso all'attributo gameMenuLoop.
     * @return GameMenuLoop ritorna sempre gameMenuLoop */
    public boolean getGameMenuLoopValue() {
        return gameMenuLoop;
    }

    /** Metodo di accesso all'attributo time.
     * @return time */
    public int getTime() {
        return time;
    }

    /** Metodo per impostare il tempo di gioco.
     * @param timeParam il tempo di gioco */
    public void setTime(final int timeParam) {
        time = timeParam;
    }

    /** Metodo di accesso a minValueTime.
     * @return il valore di minValueTime */
    public int getMinValueTime() {
        return minValueTime;
    }

    /** Metodo di accesso a minValueTime.
     * @param paramMinValueTime il valore che modifica l'attributo privato */
    private void setMinValueTime(final int paramMinValueTime) {
        minValueTime = paramMinValueTime;
    }

    /** Metodo di accesso a endTime.
     * @param paramTime il valore che modifica l'attributo privato */
    public void setEndTime(final LocalDateTime paramTime) {
        endTime = paramTime;
    }

    /** Metodo che ritorna il tempo di gioco.
     * @return il tempo di gioco. */
    public LocalDateTime getEndTime() {
        return endTime;
    }


    /** Metodo che imposta i tentativi falliti.
     * @param fail il numero di tentativi falliti */
    public void setFailures(final int fail) {
        failures = fail;
    }

    /** Metodo che ottiene i tentativi falliti.
     * @return failures */
    public int getFailures() {
        return failures;
    }

    /** Metodo di accesso a enemyGrid.
     * @return enemyGrid */
    public Grid getEnemyGrid() {
        return new Grid(enemyGrid);
    }

    /** Metodo di accesso a enemyGrid.
     * @param objectGrid il valore che modifica l'attributo privato */
    public void setEnemyGrid(final Grid objectGrid) {
        enemyGrid = new Grid(objectGrid);
    }

    /** Metodo di accesso a playerGrid.
     * @return playerGrid */
    public Grid getPlayerGrid() {
        return new Grid(playerGrid);
    }

    /** Metodo di accesso a playerGrid.
     * @param objectGrid il valore che modifica l'attributo privato */
    public void setPlayerGrid(final Grid objectGrid) {
        playerGrid = new Grid(objectGrid);
    }
}
