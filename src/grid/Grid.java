package grid;

import utility.UtilityTools;

/** {@literal <<Entity>>}
 * La classe Grid permette la creazione e la visualizzazione della griglia sulla quale andra giocata la partita.
 * La classe Grid è responsabile della creazione e della visualizzazione di una griglia di gioco.
 * Essa contiene un array bidimensionale di caratteri (char) che rappresenta la griglia stessa.
 * La classe fornisce metodi per accedere e modificare i valori delle singole celle della griglia,
 * nonché per reimpostare la griglia a un valore predefinito. Inoltre, la classe offre due metodi per
 * la stampa della griglia a schermo, uno che visualizza tutti i valori delle celle e un altro che nasconde
 * le celle contenenti il carattere 'O'. In generale, la classe Grid fornisce un'interfaccia per la gestione
 * della griglia di gioco all'interno di un'applicazione. */
public class Grid {

    /** La griglia é una matrice a due dimensioni di caratteri. */
    private char[][] grid;

    /** Attributo che definisce la cardinalità delle righe della griglia. */
    private int dimRow;

    /** Attributo che definisce la cardinalità delle colonne della griglia. */
    private int dimColumn;

    /** Il costruttore definisce la cardinalità delle righe e delle colonne della griglia
     * e inizializza la griglia inserendo in ogni cella il carattere '-' associato alla cella vuota
     * richiamando il metodo resetGrid.
     * @param fixedDimRow cardinalità delle righe della griglia
     * @param fixedDimColumn cardinalità delle colonne della griglia */
    public Grid(final int fixedDimRow, final int fixedDimColumn) {
        grid = new char[fixedDimRow][fixedDimColumn];
        dimRow = fixedDimRow;
        dimColumn = fixedDimColumn;
        resetGrid();
    }

    /** Questo costruttore crea un oggetto copiando le caratteristiche di un altro.
     * (Utilizzato per esigenze di Spotbugs)
     * @param gridCopy oggetto da copiare */
    public Grid(final Grid gridCopy) {
        dimRow = gridCopy.getRowLength();
        dimColumn = gridCopy.getColumnLength();
        grid = gridCopy.getGrid();
    }

    /** Restituisce la griglia stessa, la utilizziamo per poter copiare l'oggetto griglia.
     * @return grid Griglia */
    public char[][] getGrid() {
        return copy2DArray(grid);
    }

    /** Metodo privato per la copia di un array di caratteri a 2 dimensioni.
     * @param arrayToCopy array da copiare
     * @return newMatrix copia dell'array
    */
    private char[][] copy2DArray(final char[][] arrayToCopy) {
        char[][] newMatrix = arrayToCopy;
        return newMatrix;
    }

    /** Metodo che inserisce un valore all'interno di una specifica cella della griglia di gioco.
     * @param rowPos indice della riga sulla quale andra effettuato il metodo
     * @param columnPos indice della colonna sulla quale andra effettuato il metodo
     * @param value carattere che andra inserito nella cella alla riga rowPos e colonna columnPos */
    public void setGridValue(final int rowPos, final int columnPos, final char value) {
        grid[rowPos][columnPos] = value;
    }

    /** Metodo che ritorna il valore presente in una determinata cella della griglia.
     * @param rowPos indice della riga sulla quale andra effettuato il metodo
     * @param columnPos indice della colonna sulla quale andra effettuato il metodo
     * @return grid[rowPos][columnPos] ritorna il carattere presente
     * alla griglia nella cella alla riga rowPos e colonna columnPos */
    public char getGridValue(final int rowPos, final int columnPos) {
        return grid[rowPos][columnPos];
    }

    /** Metodo che ritorna la cardinalità delle colonne della griglia.
     * @return dimColumn cardinalità delle colonne della griglia */
    public int getColumnLength() {
        return dimColumn;
    }

    /** Metodo che ritorna la cardinalità delle righe della griglia.
     * @return dimRow cardinalità delle colonne della griglia */
    public int getRowLength() {
        return dimRow;
    }

    /** Metodo che riempie ogni cella della griglia con il carattere '-'. */
    public void resetGrid() {

        for (int i = 0; i < getRowLength(); i++) {
            for (int j = 0; j < getColumnLength(); j++) {
                setGridValue(i, j, '-');
            }
        }
    }

    /** Metodo che permette la visualizzazione a schermo della griglia di gioco
     * attraverso l'utilizzo di un primo for per la stampa delle lettere
     * che rappresentano le colonne della griglia
     * e due for per la stampa dei numeri che indicano le righe della griglia e
     * dei valori presenti all'interno di ogni cella
     * della griglia di gioco. */
    public void printGrid() {
        int x;
        char y;

        final int rowCheck = 9;
        System.out.print("   ");

        for (int i = 0; i < getRowLength(); i++) {
            x = 'A' + i;
            y = (char) x;
            System.out.print(UtilityTools.PURPLE + y + "  " + UtilityTools.RESET);
        }

        System.out.println();

        for (int i = 0; i < getRowLength(); i++) {
            if (i + 1 <= rowCheck) {
                System.out.print(UtilityTools.PURPLE + (i + 1) + "  " + UtilityTools.RESET);
            } else {
                System.out.print(UtilityTools.PURPLE + (i + 1) + " " + UtilityTools.RESET);
            }

            for (int j = 0; j < getColumnLength(); j++) {
                if (getGridValue(i, j) == 'S') {
                    System.out.print(UtilityTools.GREEN + getGridValue(i, j) + UtilityTools.RESET + "  ");
                } else if (getGridValue(i, j) == '$') {
                    System.out.print(UtilityTools.YELLOW + getGridValue(i, j) + UtilityTools.RESET + "  ");
                } else if (getGridValue(i, j) == 'X') {
                    System.out.print(UtilityTools.RED + getGridValue(i, j) + UtilityTools.RESET + "  ");
                } else if (getGridValue(i, j) == 'O') {
                    System.out.print(UtilityTools.CYAN + getGridValue(i, j) + UtilityTools.RESET + "  ");
                } else {
                    System.out.print(getGridValue(i, j) + "  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /** Metodo che permette la visualizzazione a schermo della griglia di gioco senza i O
     * attraverso l'utilizzo di un primo for per la stampa delle lettere
     * che rappresentano le colonne della griglia
     * e due for per la stampa dei numeri che indicano le righe della griglia e
     * dei valori presenti all'interno di ogni cella
     * della griglia di gioco. */
    public void printGridWithoutMiss() {
        int x;
        char y;

        final int rowCheck = 9;
        System.out.print("   ");

        for (int i = 0; i < getRowLength(); i++) {
            x = 'A' + i;
            y = (char) x;
            System.out.print(UtilityTools.CYAN + y + "  " + UtilityTools.RESET);
        }

        System.out.println();

        for (int i = 0; i < getRowLength(); i++) {
            if (i + 1 <= rowCheck) {
                System.out.print(UtilityTools.CYAN + (i + 1) + "  " + UtilityTools.RESET);
            } else {
                System.out.print(UtilityTools.CYAN + (i + 1) + " " + UtilityTools.RESET);
            }

            for (int j = 0; j < getColumnLength(); j++) {
                if (getGridValue(i, j) == 'S') {
                    System.out.print(UtilityTools.GREEN + getGridValue(i, j) + UtilityTools.RESET + "  ");
                } else if (getGridValue(i, j) == '$') {
                    System.out.print(UtilityTools.YELLOW + getGridValue(i, j) + UtilityTools.RESET + "  ");
                } else if (getGridValue(i, j) == 'X') {
                    System.out.print(UtilityTools.RED + getGridValue(i, j) + UtilityTools.RESET + "  ");
                } else if (getGridValue(i, j) == 'O') {
                    System.out.print("-  ");
                } else {
                    System.out.print(getGridValue(i, j) + "  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
