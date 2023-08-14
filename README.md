# Battleship game in Java

Durante l'esame di Ingegneria del Software nel corso di informatica dell'Universitá Aldo Moro di Bari, ci é stato chiesto di creare un piccolo gioco in Java riguardante la Battaglia Navale ad un giocatore.  Al momento vi é solo una versione del gioco, utilizzabile tramite la CLI, ma continueró a lavorarci per poter rilasciare una versione avente la GUI.

## Come giocare

Una volta scaricato e avviato il programma verrà presentata nel terminale di avvio la seguente schermata:

<p align="center">
  <img src="img/Manuale utente 1.png">
</p>


Tramite questa applicazione è possibile giocare una partita contro il computer a Battaglia Navale. 
La partita non si svolgerà a turni in quanto vi è solo un giocare; 
l'obbiettivo del gioco sarà, come nel regolamento originale, 
affondare tutte le navi sulla griglia.

Per interagire con l'applicazione andranno utilizzati i seguenti comandi:

- `/gioca`: permette di iniziare una nuova partita;
- `/help`: mostra l’help con l’elenco dei comandi;
- `/esci`: chiude il gioco;
- `/facile`: imposta il livello di gioco in facile dando 50 tentativi falliti massimi;
- `/facile` *`numero`*: imposta il livello di gioco in facile dando *numero* tentativi falliti massimi;
- `/medio`: imposta il livello di gioco in medio dando 30 tentativi falliti massimi;
- `/medio` *`numero`*: imposta il livello di gioco in medio dando *numero* tentativi falliti massimi;
- `/difficile`: imposta il livello di gioco in difficile dando 10 tentativi falliti massimi;
- `/difficile` *`numero`*: imposta il livello di gioco in difficile dando *numero* tentativi falliti massimi;
- `/mostralivello`: mostra il livello di gioco impostato e il numero massimo di tentativi falliti;
- `/tentativi` *`numero`*: imposta a numero il *numero* massimo di tentativi falliti;
- `/mostranavi`: mostra i tipi di nave e la loro quantità;
- `/standard`: imposta a 10x10 la dimensione della griglia (default);
- `/large`: imposta a 18x18 la dimensione della griglia;
- `/extralarge`: imposta a 26x26 la dimensione della griglia;
- `/svelagriglia`: visualizza una griglia 10x10, con le righe numerate da 1 a 10 e le colonne numerate da A a J, e tutte le navi posizionate;
- `/mostragriglia`: visualizza una griglia con le righe numerate a partire da 1 e le colonne numerate a partire da A, con le navi affondate e le sole parti già colpite delle navi non affondate;
- `/abbandona`: permette di abbandonare una partita già iniziata;
- `B-3`: esempio di coppia di caratteri inserita durante la partita per lanciare un colpo;
- `/tempo` *`numero`*: imposta a *numero* il numero minuti a disposizione per giocare;
- `/mostratempo`: visualizza il numero di minuti trascordi nel gioco e il numero di minuti ancora disponibili;

Determinati comandi non sono usufruibili in base al menu in cui ci si trova, per esempio é impossibile cambiare la difficoltá o il numero di tentativi una volta iniziata una partita.


------------------