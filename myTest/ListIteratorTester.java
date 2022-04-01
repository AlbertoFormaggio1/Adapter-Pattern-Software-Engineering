package myTest;

import myAdapter.*;
import org.junit.*;

import java.util.NoSuchElementException;
import static org.junit.Assert.*;

/**
 * Questa suite di test ha il compito di testare tutte le funzionalita' dell'iteratore che opera su un oggetto di tipo {@code ListAdapter}.<p>
 * In questa suite verranno testati i metodi della classe <code>ListIter</code>, la quale implementa i metodi definiti dall'interfaccia
 * HListIterator (che estende l'interfaccia HIterator, quindi anche i metodi dell'iteratore di base sono testati qui di seguito).<p>
 * Il corretto comportamento di backing della sottolista e le funzionalita' di base dell'oggetto ListAdapter sono testate in una diversa suite.
 * Si vuole dimostrare che tutti i metodi definiti in {@code ListIter} funzionino correttamente.<p>
 *
 * @safe.des Per testare la classe nella sua interezza e' stato testato ciascun metodo fornendo in input parametri validi
 * e non validi oltre a testare le varie configurazioni tra metodi cosi' da testare il piu' ampio numero di casi possibili in cui
 * l'iteratore puo' trovarsi.<p>
 * La documentazione di ciascun test case e' stata eseguita seguendo la colonna "homework" fornita nel file della consegna,
 * inoltre i metodi hanno tutti un nome che e' il piu' evocativo possibile.
 * @safe.pre Si suppone che i metodi della classe ListAdapter siano gia' stati testati all'interno di un'altra suite.
 * @safe.post Si sono ottenuti i risultati dell'esecuzione di tutti i test in questa suite.
 * @safe.records Consultare il file <a href="..\..\Test suite execution records\Test Results - ListIteratorTester.html">
 *     "Test Results - ListIteratorTester.html"</a> nella cartella "Test suite execution records"
 * @safe.exec I test sono stati eseguiti utilizzando JUnit v4.13 e hamcrest v1.3.
 * Per poter lanciare i test e' necessario inserire i file .jar di questi framework all'interno del CLASSPATH settando tale
 * variabile di ambiente.
 *
 * @author Formaggio Alberto
 */
public class ListIteratorTester
{
    private ListAdapter list;
    private HListIterator lit;

    /**
     * Inizializza un oggetto inserendo 3 valori in ordine 0,1,2. Al termine della creazione la lista su cui verrano eseguiti i test sara'
     * fatta nel seguente modo: <pre>
     * list
     * | 0 | 1 | 2 |
     *</pre>
     * Crea l'iteratore di tipo HListIterator per tale lista.
     */
    @Before
    public void Initialize()
    {
        list = new ListAdapter();
        list.add(0);
        list.add(1);
        list.add(2);
        lit = list.listIterator();
    }

    //-----------------------------------------TEST ITERATOR GENERICO--------------------

    /**
     * Test del metodo <code>public Object next()</code>: Testa che tutti gli elementi siano nello stesso ordine definito dalla lista.
     * @homework.des Accertamento che ci sia corrispondenza tra gli elementi della lista e i valori ritornati dalle successive invocazioni
     * di <code>next()</code>.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento. Iteratore
     * creato.
     * @homework.post L'iteratore e' arrivato alla fine della lista. La lista e' rimasta invariata.
     * @homework.testDescr Si crea un nuovo iteratore e si testa che gli elementi ottenuti da <code>next</code> siano gli stessi nella posizione i-esima della
     * lista usando il metodo <code>get</code>. Si usa una variabile booleana isOk che viene settata a <code>false</code> se un'operazione di controllo
     * ha un riscontro fallimentare.
     * @homework.exp isOk deve essere <code>true</code>, ovvero l'ordine dato dall'iteratore deve essere esattamente quello della lista.
     */
    @Test
    public void testNextElementsAreInOrder()
    {
        boolean isOk = true;
        for(int i = 0; i < list.size(); i++)
            if(!(list.get(i)==null ? lit.next()==null : list.get(i).equals(lit.next())))
                isOk = false;

        assertTrue(isOk);
    }


    /**
     * Test del metodo <code>public boolean hasNext()</code>: Testa HasNext quando l'iteratore non ha ispezionato tutti gli elementi
     * @homework.des accertamento del funzionamento di hasNext in una posizione qualsiasi della lista
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo Initialize(). L'iteratore *non* sta puntando all'ultimo elemento
     * @homework.post La lista ha creato un HIterator. La lista non ha subito modifiche.
     * @homework.testDescr Presa una lista con almeno un elemento rimasto da ispezionare, si testa se l'iteratore ha un successivo
     * @homework.exp Il test deve ritornare <code>true</code>
     */
    @Test
    public void testHasNextWhenThereIsNext()
    {
        boolean actual = lit.hasNext();

        assertTrue(actual);
    }


    /**
     * Test del metodo <code>public boolean hasNext()</code>: Testa HasNext quando l'iteratore e' arrivato alla fine della scansione
     * @homework.des Accertamento del funzionamento di hasNext alla fine della lista
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo Initialize(). L'iteratore sta puntando all'ultimo elemento
     * @homework.post L'iteratore si e' spostato di una posizione. La lista non ha subito modifiche.
     * @homework.testDescr Presa una lista con nessun un elemento rimasto da ispezionare, si testa se l'iteratore ha un successivo
     * @homework.exp Il test deve ritornare <code>false</code>
     */
    @Test
    public void testHasNextWhenThereIsNotNext()
    {
        for (int i = 0; i < list.size(); i++)
            lit.next();

        boolean actual = lit.hasNext();

        assertFalse(actual);
    }

    /**
     * Test del metodo <code>public Object next()</code>: Testa <code>next</code> quando l'iteratore e' appena stato creato.
     * L'elemento ritornato deve essere il primo elemento della lista.
     * @homework.des Accertamento che l'inizializzazione di un iteratore sia corretta: alla creazione deve puntare al primo elemento della lista.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento. Iteratore
     * creato.
     * @homework.post L'iteratore si e' spostato in avanti di una posizione. La lista non ha subito modifiche.
     * @homework.testDescr Si crea un nuovo iteratore e si testa se il primo elemento ritornato dall'invocazione di next e' uguale al primo elemento
     * della lista (ovvero lo 0).
     * @homework.exp 0 deve essere uguale all'elemento ritornato dall'iteratore.
     */
    @Test
    public void testNextAtBeginning()
    {
        Object actual = lit.next();

        assertEquals(0, actual);
    }

    /**
     * Test del metodo <code>public Object next()</code>: Testa che <code>next()</code> ritorni l'elemento corretto anche dopo che next() e' gia' stato invocato una volta, ovvero testo
     * che l'elemento ispezionato sia corretto anche all'interno della lista e non solo all'inizio.
     * @homework.des Accertamento che lo spostamento dell'iteratore dopo un'invocazione sia corretto, ovvero che avanzi e non punti sempre
     * alla stessa posizione.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento. Iteratore
     * creato e spostato avanti di una posizione.
     * @homework.post L'iteratore si e' spostato in avanti di un'ulteriore posizione. La lista non ha subito modifiche.
     * @homework.testDescr Si crea un nuovo iteratore e lo si sposta in avanti di una posizione. Si testa se la seconda invocazione di next()
     * ritorna il secondo elemento della lista (ovvero l'1)
     * @homework.exp 1 deve essere uguale all'elemento ritornato dall'iteratore.
     */
    @Test
    public void testNextInMiddle()
    {
        lit.next();

        Object actual = lit.next();

        assertEquals(1, actual);
    }

    /**
     * Test del metodo <code>public Object next()</code>: testa che il metodo <code>next</code> lanci eccezione
     * <code>NoSuchElementException</code> nel caso in cui si provi ad ottenere un elemento con next quando l'iteratore ha gia'
     * raggiunto la fine della lista.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando non ci sono elementi da restituire.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando a dopo l'ultimo elemento.
     * @homework.post L'iteratore non ha subito modifiche. La lista non ha subito modifiche.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Si sposta tale iteratore fino alla fine della lista, in modo che it.hasNext() ritorni false.
     * Si prova ora ad ottenere un nuovo elemento. Tale operazione non puo' avere successo perche' la lista non ha altri elementi da ritornare.
     * @homework.exp L'eccezione <code>NoSuchElementException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testNextExceptionIfThereIsNotNext()
    {
        while (lit.hasNext())
            lit.next();

        assertThrows(NoSuchElementException.class, lit::next);
    }

    /**
     * Test del metodo <code>public void remove()</code>: testa che il metodo <code>remove</code> rimuova l'elemento dalla lista
     * @homework.des Accertamento del corretto comportamento dell'iteratore quando si rimuove un elemento appena scansionato da next.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando ad un elemento qualsiasi nella lista.
     * @homework.post L'iteratore si e' posizionato correttamente tra il precedente e il successivo dell'elemento rimosso.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Si sposta tale iteratore di 2 posizioni e si prende l'ultimo elemento
     * ritornato dal meotodo next in una variabile chiamata <code>removed</code>.
     * Si invoca poi il metodo <code>remove</code>.
     * L'elemento precedentemente salvato non deve piu' essere presente all'interno della lista. Si controlla cio' mediante il metodo contains() della lista.
     * Siccome l'elemento e' stato ottenuto dal metodo next prima era sicuramente all'interno della lista.
     * @homework.exp <code>contains(removed)</code> deve ritornare <code>false</code>, l'elemento e' stato rimosso dalla lista.
     */
    @Test
    public void testRemoveRemovesFromList()
    {
        lit.next();
        Object removed = lit.next();

        lit.remove();

        assertFalse(list.contains(removed));
    }


    /**
     * Test del metodo <code>public void remove()</code>: testa che il metodo <code>remove</code> lanci eccezione
     * <code>IllegalStateException</code> nel caso in cui si provi a rimuovere un elemento senza che nessuno sia mai stato invocato in precedenza.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando non ci sono elementi da restituire.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento.
     * @homework.post L'iteratore non ha subito modifiche. La lista non ha subito modifiche.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Si prova a rimuovere un elemento.
     * Tale operazione non puo' avere successo perche' non e' stato ispezionato nessun elemento.
     * @homework.exp L'eccezione <code>IllegalStateException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveExceptionIfNextWasNotInvoked()
    {
        assertThrows(IllegalStateException.class, lit::remove);
    }

    /**
     * Test del metodo <code>public void remove()</code>: testa che il metodo <code>remove</code> lanci eccezione
     * <code>IllegalStateException</code> nel caso in cui si provi a chiamare il metodo remove due volte di fila senza aver chiamato next
     * prima della seconda rimozione.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando l'iteratore non e' in uno stato valido.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento.
     * @homework.post L'iteratore non e' stato modificato. La lista non ha subito modifiche.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Si ottiene un elemento con next che poi viene rimosso.
     * Tale rimozione ha successo perche' prima ho invocato il metodo next.
     * Una successiva invocazione deve sollevare eccezione perche' non ho scansionato un ulteriore elemento.
     * @homework.exp L'eccezione <code>IllegalStateException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveExceptionCallingRemoveTwice()
    {
        lit.next();

        lit.remove();

        assertThrows(IllegalStateException.class, lit::remove);
    }

    /**
     * Test del metodo <code>public void remove()</code>: testa che il metodo <code>remove</code> lasci l'iteratore in uno stato corretto.
     * Una volta rimosso l'elemento da una posizione, una successiva invocazione di next deve ritornare quello che ora e' andato al posto
     * dell'elemento rimosso dalla lista.
     * @homework.des Accertamento del corretto comportamento dell'iteratore quando ci si sposta con next dopo avere fatto una rimozione.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando ad un elemento qualsiasi nella lista.
     * @homework.post L'elemento si e' spostato avanti di una posizione.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Si sposta tale iteratore di una posizione e si rimuove un elemento.
     * Senza l'elemento nella prima posizione, l'elemento che prima era nella seconda posizione (1) va all'inizio della lista.
     * Un'invocazione di <code>next</code> deve ritornare il nuovo primo elemento della lista.
     * @homework.exp <code>next</code> deve ritornare 1 (il nuovo primo elemento della lista).
     */
    @Test
    public void testNextWorksFineAfterRemove()
    {
        lit.next();
        lit.remove();

        Object actual = lit.next();

        assertEquals(1, actual);
    }


    //-----------------------------------------TEST SPECIFICI LIST ITERATOR-------------------


    /**
     * Test del metodo <code>public Object previous()</code>: Testa che tutti gli elementi siano nello stesso ordine definito dalla lista.
     * Partendo dalla fine e facendo una serie di chiamate successive a previous.
     * @homework.des Accertamento che ci sia corrispondenza tra gli elementi della lista e i valori ritornati dalle successive invocazioni
     * di <code>previous()</code>.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando all'ultimo elemento. Iteratore
     * creato.
     * @homework.post L'iteratore e' arrivato all'inizio della lista. La lista e' rimasta invariata.
     * @homework.testDescr Si crea un nuovo iteratore e si testa che gli elementi ottenuti da <code>previous</code> siano gli stessi nella posizione i-esima della
     * lista usando il metodo <code>get</code>. Si usa una variabile booleana isOk che viene settata a <code>false</code> se un'operazione di controllo
     * ha un riscontro fallimentare.
     * @homework.exp isOk deve essere <code>true</code>, ovvero l'ordine dato dall'iteratore deve essere esattamente quello della lista.
     */
    @Test
    public void testPreviousScanInReverseOrder()
    {
        HListIterator lit = list.listIterator(list.size());
        boolean isOk = true;
        for(int i = list.size() - 1; i >= 0; i--)
            if(!(list.get(i)==null ? lit.previous()==null : list.get(i).equals(lit.previous())))
                isOk = false;

        assertTrue(isOk);
    }


    /**
     * Test del metodo <code>public Object previous()</code> e <code>public Object next()</code>: Testa che gli elementi vengano ritornati
     * nell'ordine corretto scansionando la lista da inizio a fine e poi da fine a inizio.
     * @homework.des Accertamento che l'arrivo alla fine della lista in un verso non invalidi l'iteratore e renda scorretto lo scorrimento nel senso opposto
     * subito successivo.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando all'ultimo elemento. Iteratore
     * creato.
     * @homework.post L'iteratore e' tornato all'inizio della lista. La lista e' rimasta invariata.
     * @homework.testDescr Si crea un nuovo iteratore e si testa che gli elementi ottenuti da <code>next</code> siano gli stessi nella posizione i-esima della
     * lista usando il metodo <code>get</code>. Una volta arrivati alla fine della lista si fa la stessa operazione
     * in senso contrario. Si usa una variabile booleana isOk che viene settata a <code>false</code> se un'operazione di controllo
     * ha un riscontro fallimentare.
     * @homework.exp isOk deve essere <code>true</code>, ovvero l'ordine dato dall'iteratore deve essere esattamente quello della lista.
     */
    @Test
    public void testPreviousNextCorrectOrder()
    {
        boolean isOk = true;

        for(int i = 0; i < list.size(); i++)
            if(lit.next() != list.get(i))
                isOk = false;

        for(int i = list.size() - 1; i >= 0; i--)
            if(lit.previous() != list.get(i))
                isOk = false;

        assertTrue(isOk);
    }

    /**
     * Test del metodo <code>public Object previous()</code>: Si testa il comportamento di previous quando si va avanti di una posizione e poi si torna indietro.
     * @homework.des Testo che andando avanti e indietro l'elemento scansionato in entrambi i casi deve essere lo stesso in quanto sono andato avanti e indietro di 1 posizione.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento. Iteratore
     * creato.
     * @homework.post L'iteratore e' tornato alla prima posizione. La lista non ha subito modifiche.
     * @homework.testDescr Si crea un nuovo iteratore e lo si sposta avanti di una posizione usando il metodo next.
     * Si torna poi indietro di una posizione invocando previous. Avendo scansionato lo stesso elemento, i valori ritornati
     * da next e previous devono essere gli stessi.
     * @homework.exp I due elementi sono uguali
     */
    @Test
    public void testPreviousGetSameElementAsNext()
    {
        Object expected = lit.next();

        Object actual = lit.previous();

        assertEquals(actual, expected);
    }

    /**
     * Test del metodo <code>public Object previous()</code>: testa che il metodo <code>previous</code> lanci eccezione
     * <code>NoSuchElementException</code> nel caso in cui si provi ad ottenere un elemento con previous quando l'iteratore ha gia'
     * raggiunto l'inizio della lista.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando non ci sono elementi da restituire.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando a prima del primo elemento.
     * @homework.post L'iteratore non ha subito modifiche. La lista non ha subito modifiche.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Siccome siamo all'inizio della lista, <code>it.hasNext()</code> ritorna <code>false</code>.
     * Si prova ora ad ottenere un nuovo elemento. Tale operazione non puo' avere successo perche' la lista non ha altri elementi da ritornare.
     * @homework.exp L'eccezione <code>NoSuchElementException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testPreviousExceptionIfThereIsNotPrevious()
    {
        assertThrows(NoSuchElementException.class, lit::previous);
    }

    /**
     * Test del metodo <code>public void remove()</code>: testa che il metodo <code>remove</code> rimuova l'elemento dalla lista appena
     * restituito dal metodo previous.
     * @homework.des Accertamento del corretto comportamento dell'iteratore quando si rimuove un elemento appena scansionato da previous.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando ad un elemento qualsiasi nella lista.
     * @homework.post L'iteratore si e' posizionato correttamente tra il precedente e il successivo dell'elemento rimosso.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore posizionato prima della posizione 2. Si invoca il metodo
     * previous e si salva il valore ritornat in una variabile chiamata <code>removed</code>.
     * Si invoca poi il metodo <code>remove</code>.
     * L'elemento precedentemente salvato non deve piu' essere presente all'interno della lista. Si controlla cio' mediante il metodo contains() della lista.
     * Siccome l'elemento e' stato ottenuto dal metodo next prima era sicuramente all'interno della lista.
     * @homework.exp <code>contains(removed)</code> deve ritornare <code>false</code>, l'elemento e' stato rimosso dalla lista.
     */
    @Test
    public void testRemoveRemovesFromListAfterPrevious()
    {
        lit = list.listIterator(2);
        Object removed = lit.previous();

        lit.remove();

        assertFalse(list.contains(removed));
    }


    /**
     * Test del metodo <code>public void remove()</code>: testa che il metodo <code>remove</code> lasci l'iteratore in uno stato corretto.
     * Una volta rimosso l'elemento da una posizione, una successiva invocazione di previous deve ritornare quello precedente a quello che ora e' andato al posto
     * dell'elemento rimosso dalla lista.
     * @homework.des Accertamento del corretto comportamento dell'iteratore quando ci si sposta con previous dopo avere fatto una rimozione.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando ad un elemento qualsiasi nella lista.
     * @homework.post L'iteratore e' tornato all'inizio della lista.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Si sposta tale iteratore di una posizione e si salva tale elemento
     * in una variabile chiamata <code>expected</code>.
     * Si rimuove poi il secondo elemento chiamando next e poi remove su di esso.
     * Senza l'elemento nella seconda posizione, l'elemento che prima era nella terza posizione (2) va nella seconda posizione
     * ma l'elemento nella posizione iniziale deve restare invariato.
     * Un'invocazione di <code>previous</code> deve ritornare il primo elemento della lista (che e' rimasto invariato) salvato
     * all'interno della variabile <code>expected</code>.
     * @homework.exp <code>previous</code> deve ritornare il valore contenuto in <code>expected</code> (il primo elemento della lista che non e' mai stato modificato).
     */
    @Test
    public void testPreviousWorksFineAfterRemove()
    {
        Object expected = lit.next();
        lit.next();
        lit.remove();

        Object actual = lit.previous();

        assertEquals(expected, actual);
    }

    /**
     * Test del metodo <code>public void remove()</code>: testa che il metodo <code>remove</code> lanci eccezione
     * <code>IllegalStateException</code> nel caso in cui si provi a chiamare il metodo <code>remove</code> subito dopo aver invocato il metodo <code>add</code>
     * senza aver chiamato next.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando l'iteratore non e' in uno stato valido.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore e' stato fatto avanzare di una posizione. E' stato inserito
     * un elemento con il metodo add.
     * @homework.post L'iteratore non e' stato modificato. La lista non ha subito modifiche.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Si sposta in avanti l'iteratore usando next. Si prova ad inserire un elemento
     * e subito dopo si esegue una rimozione.
     * Tale rimozione non ha successo perche' prima non e' stato invocato il metodo next.
     * @homework.exp L'eccezione <code>IllegalStateException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveThrowsExceptionAfterAdd()
    {
        lit.next();
        lit.add(3);

        assertThrows(IllegalStateException.class, () -> lit.remove());
    }

    /**
     * Test del metodo <code>public void add(Object o)</code>: testa che il metodo add inserisca nella prima posizione della lista
     * subito dopo aver creato l'iteratore.
     * @homework.des Ci si assicura che l'inizializzazione dell'iteratore e' avvenuta correttamente e che il metodo add tratti gli indici
     * nel modo corretto.
     * @homework.pre lista creata e inizializzata con il metodo initialize. Iteratore creato e punta alla prima posizione della lista.
     * @homework.post Un elemento e' stato inserito all'interno della lista. Gli indici dell'iteratore sono stati aggiornati opportunamente.
     * @homework.testDescr Si inserisce un elemento che precedentemente non era presente nella lista nella prima posizione. Una successiva
     * invocazione di indexOf deve ritornare 0, ovvero l'indice da cui parte la lista.
     * @homework.exp <code>indexOf</code> ritorna 0.
     */
    @Test
    public void testAddAtBeginning()
    {
        Object toAdd = 4;
        int expectedIndex = 0;

        lit.add(toAdd);

        assertEquals(expectedIndex, list.lastIndexOf(toAdd));
    }

    /**
     * Test del metodo <code>public void add(Object o)</code>: testa che il metodo add inserisca correttamente in una posizione intermedia della
     * lista. Si testa inoltre che e' stata inserita una sola occorrenza dell'elemento.
     * @homework.des Accertamento che il metodo add inserisca nella posizione corretta una sola volta l'oggetto passato.
     * @homework.pre lista creata e inizializzata con il metodo initialize. Iteratore creato e punta ad una posizione successiva alla prima.
     * @homework.post Un elemento e' stato inserito all'interno della lista. Gli indici dell'iteratore sono stati aggiornati opportunamente.
     * @homework.testDescr Ci si sposta avanti di una posizione con il metodo next per non puntare alla prima posizione della lista.
     * Si inserisce un elemento che precedentemente non era presente nella lista con il metodo add. Una successiva
     * invocazione di indexOf deve ritornare 1 perche' l'iteratore era avanzato di una sola posizione.
     * Se gli indici ritornati da <code>indexOf</code> e <code>lastIndexOf</code> sono uguali, allora l'elemento inserito e' unico.
     * @homework.exp <code>indexOf</code> e <code>lastIndexOf</code> ritornano 1.
     */
    @Test
    public void testAddInMiddle()
    {
        lit.next();
        int expectedIndex = 1;
        Object toAdd = 4;

        lit.add(toAdd);

        boolean isOk = false;
        int firstIndex = list.indexOf(toAdd);
        int lastIndex = list.lastIndexOf(toAdd);
        if(firstIndex != -1 && firstIndex == lastIndex && expectedIndex == firstIndex)
            isOk = true;
        assertTrue(isOk);
    }


    /**
     * Test del metodo <code>public void add(Object o)</code>: testa che il metodo add inserisca correttamente alla fine della
     * lista.
     * @homework.des Accertamento che il metodo add inserisca nella posizione corretta in coda.
     * @homework.pre lista creata e inizializzata con il metodo initialize. Iteratore creato e punta alla fine della lista.
     * @homework.post Un elemento e' stato inserito all'interno della lista. Gli indici dell'iteratore sono stati aggiornati opportunamente.
     * @homework.testDescr Si crea un iteratore che punta all'ultima posizione della lista.
     * Si inserisce un elemento che precedentemente non era presente nella lista con il metodo add. Una successiva
     * invocazione di indexOf deve ritornare il valore della dimensione attuale della lista -1 perche' l'inserimento e' avvenuto in coda.
     * @homework.exp <code>indexOf</code> ritorna <code>list.size() - 1</code>.
     */
    @Test
    public void testAddAtEnd()
    {
        lit = list.listIterator(list.size());
        Object toAdd = 4;

        lit.add(toAdd);

        int expectedIndex = list.size() - 1;
        assertEquals(expectedIndex, list.indexOf(toAdd));
    }

    /**
     * Test del metodo <code>public void add(Object o)</code>: testa che il metodo add inserisca correttamente all'interno della lista
     * se la lista e' vuota.
     * @homework.des Accertamento che il metodo add inserisca nella posizione corretta anche se nessun elemento e' presente nella lista.
     * @homework.pre lista creata e inizializzata con il metodo initialize. Iteratore creato e punta all'inizio della lista.
     * @homework.post Un elemento e' stato inserito all'interno della lista. Gli indici dell'iteratore sono stati aggiornati opportunamente.
     * @homework.testDescr Si svuota la lista. Si crea un iteratore che punta alla prima e ultima posizione della lista.
     * Si inserisce un elemento nella lista con il metodo add. L'elemento appena inserito deve essere contenuto nella lista.
     * Si testa cio' con il metodo contains
     * @homework.exp <code>contains</code> ritorna <code>true</code>.
     */
    @Test
    public void testAddInEmptyList()
    {
        list.clear();
        Object toAdd = 4;

        lit.add(toAdd);

        assertTrue(list.contains(toAdd));
    }

    /**
     * Test del metodo <code>public void add(Object o)</code>: Testo che dopo una chiamata al metodo previous dopo l'inserimento ritorni
     * l'elemento appena inserito. Verifico quindi che add lasci l'iteratore nella posizione corretta dopo aver effettuato l'inserimento.
     * @homework.des Accertamento che il metodo add lasci in uno stato corretto l'iteratore dopo aver effettuato l'inserimento.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore e' stato fatto avanzare di una posizione. E' stato inserito
     * un elemento usando il metodo add.
     * @homework.post E' stato inserito un elemento all'interno della lista.
     * @homework.testDescr Ci si sposta in una posizione centrale della lista per avere un precedente. Si inserisce poi un elemento in tale posizione.
     * Successivamente si invoca previous per controllare qual e' il precedente
     * La lista cambia nel seguente modo da prima a dopo l'inserimento: <pre>
     * | 0 | 1 | 2 |    =====>        | 0 | 4 | 1 | 2 |
     *     ^                                   ^
     *    lit                                 lit
     *</pre>
     * @homework.exp Previous ritorna l'elemento appena inserito.
     */
    @Test
    public void testAddPreviousReturnElementJustInserted()
    {
        Object toAdd = 4;
        lit.next(); //Mi posiziono al centro della lista
        lit.add(toAdd);

        Object actual = lit.previous();

        assertEquals(toAdd, actual);
    }

    /**
     * Test del metodo <code>public void add(Object o)</code>: Testo che dopo una chiamata al metodo next dopo l'inserimento ritorni
     * l'elemento successivo a quello appena inserito. Verifico quindi che add lasci l'iteratore nella posizione corretta dopo
     * aver effettuato l'inserimento.
     * @homework.des Accertamento che il metodo add lasci in uno stato corretto l'iteratore dopo aver effettuato l'inserimento.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore e' stato fatto avanzare di una posizione. E' stato inserito
     * un elemento usando il metodo add.
     * @homework.post E' stato inserito un elemento all'interno della lista.
     * @homework.testDescr Ci si sposta in una posizione centrale della lista per avere un precedente.
     * Vedo qual e' l'elemento presente dopo la posizione in cui andro' ad inserire usando next. Si torna indietro usando il metodo
     * previous e poi si inserisce poi un elemento in tale posizione.
     * Successivamente si invoca next per controllare qual e' il successivo, deve essere quello che si era ottenuto prima.
     * La lista cambia nel seguente modo da prima a dopo l'inserimento: <pre>
     * | 0 | 1 | 2 |    =====>        | 0 | 4 | 1 | 2 |
     *     ^                                   ^
     *    lit                                 lit
     * </pre>
     * @homework.exp Next ritorna l'elemento successivo a quello appena inserito.
     */
    @Test
    public void testAddNextReturnNextElement()
    {
        lit.next(); //Mi posiziono al centro della lista
        Object expected = lit.next(); //Vedo quale elemento e' presente dopo la posizione dove andro' ad inserire
        lit.previous();  //Torno indietro
        lit.add(4); //Inserisco

        Object actual = lit.next();

        assertEquals(expected, actual);
    }


    /**
     * Test del metodo <code>public int nextIndex()</code>: testa che il valore ritornato da nextIndex sia l'indice dell'elemento ritornato
     * da una successiva chimata al metodo next.
     * @homework.des nextIndex() mostra l’indice corretto dell’oggetto che sarebbe ritornato da una chiamata a next().
     * @homework.pre lista inizializzata dal metodo initialize(). iteratore sta puntando all'elemento in prima posizione.
     * @homework.post la lista non e' stata modificata, l'iteratore e' avanzato di una posizione
     * @homework.testDescr Si ottiene l'indice del successivo invocando il metodo nextIndex. Si invoca poi il metodo next e si cerca l'indice
     * del valore da esso restituito all'interno della lista con il metodo indexOf.
     * @homework.exp Gli indici ritornati devono essere uguali.
     */
    @Test
    public void testNextIndexAtAnyPosition()
    {
        lit = list.listIterator(1);
        int expected = lit.nextIndex();
        Object next = lit.next();

        int actual = list.indexOf(next);

        assertEquals(expected,actual);
    }

    /**
     * Test del metodo <code>public int nextIndex()</code>: testa che se si e' alla fine della lista, il valore ritornato sia pari
     * alla dimensione della lista.
     * @homework.des nextIndex() mostra l’indice corretto dell’oggetto che sarebbe ritornato da una chiamata a next() o la dimensione della lista
     * se si e' alla fine di essa.
     * @homework.pre lista inizializzata dal metodo initialize(). iteratore sta puntando all'elemento in ultima posizione.
     * @homework.post la lista non e' stata modificata, l'iteratore e' rimasto invariato.
     * @homework.testDescr Si ottiene l'indice del successivo invocando il metodo nextIndex. Siccome si e' alla fine della lista, il valore
     * ritornato deve essere uguale alla dimensione della lista
     * @homework.exp <code>list.size()</code> e <code>nextIndex</code> devono ritornare lo stesso valore.
     */
    @Test
    public void testNextIndexAtEndOfList()
    {
        lit = list.listIterator(list.size());

        int actual = lit.nextIndex();

        assertEquals(list.size(), actual);
    }


    /**
     * Test del metodo <code>public int previousIndex()</code>: testa che il valore ritornato da nextIndex sia l'indice dell'elemento ritornato
     * da una successiva chimata al metodo previous.
     * @homework.des nextIndex() mostra l’indice corretto dell’oggetto che sarebbe ritornato da una chiamata a previous().
     * @homework.pre lista inizializzata dal metodo initialize(). L'iteratore ha letto il primo elemento.
     * @homework.post la lista non e' stata modificata, l'iteratore e' retrocesso di una posizione
     * @homework.testDescr Si ottiene l'indice del successivo invocando il metodo previousIndex. Si invoca poi il metodo previous e si cerca l'indice
     * del valore da esso restituito all'interno della lista con il metodo indexOf.
     * @homework.exp Gli indici ritornati devono essere uguali.
     */
    @Test
    public void testPreviousIndexInAnyPosition()
    {
        lit = list.listIterator(1);
        int expected = lit.previousIndex();
        Object prev = lit.previous();

        int actual = list.indexOf(prev);

        assertEquals(expected,actual);
    }

    /**
     * Test del metodo <code>public int nextIndex()</code>: testa che se si e' alla fine della lista, il valore ritornato sia -1
     * @homework.des nextIndex() mostra l’indice corretto dell’oggetto che sarebbe ritornato da una chiamata a previous() o -1 se si e' all'inizio
     * della lista stessa.
     * @homework.pre lista inizializzata dal metodo initialize(). L'iteratore ha letto il primo elemento.
     * @homework.post la lista non e' stata modificata, l'iteratore e' rimasto invariato.
     * @homework.testDescr Si ottiene l'indice del successivo invocando il metodo previousIndex. Siccome si e' all'inizio della lista, il valore
     * ritornato deve essere -1.
     * @homework.exp <code>previousIndex</code> deve essere pari a -1.
     */
    @Test
    public void testPreviousAtBeginningOfList()
    {
        int actual = lit.previousIndex();

        assertEquals(-1, actual);
    }

    /**
     * Test del metodo <code>public Object set(Object o)</code>: testa che il metodo <code>set</code> sostituisca l'elemento ritornato dall'ultima
     * invocazione del metodo next.
     * Al termine dell'operazione l'elemento sostitutito non deve essere presente all'interno della lista.
     * @homework.des Accertamento che il metodo abbia rimosso l'elemento che e' appena stato sostituito.
     * @homework.pre lista inizializzata dal metodo initialize(). Iteratore creato e ha appena letto l'elemento in prima posizione.
     * @homework.post L'ultimo elemento ritornato da next e' stato sostituito.
     * @homework.testDescr Si salva l'elemento ritornato da una prima invocazione di next. Si invoca poi il metodo next per andare
     * a sostituire l'elemento appena restituito con un oggetto diverso.
     * L'elemento ritornato da next deve essere stato sostituito dall'elemento passato.
     * Si controlla dunque che la lista non contenga piu' l'elemento usando il metodo contains.
     * @homework.exp <code>contains</code> ritorna <code>false</code>
     */
    @Test
    public void testSetAfterNext()
    {
        Object modified = lit.next();

        lit.set(4);

        assertFalse(list.contains(modified));
    }

    /**
     * Test del metodo <code>public Object set(Object o)</code>: testa che il metodo <code>set</code> sostituisca l'elemento ritornato dall'ultima
     * invocazione del metodo previous.
     * Al termine dell'operazione l'elemento sostitutito non deve essere presente all'interno della lista.
     * @homework.des Accertamento che il metodo abbia rimosso l'elemento che e' appena stato sostituito.
     * @homework.pre lista inizializzata dal metodo initialize(). Iteratore creato e ha appena letto un elemento usando il metodo previous.
     * @homework.post L'ultimo elemento ritornato da previous e' stato sostituito.
     * @homework.testDescr Si crea un iteratore che inizi dalla fine della lista. Si salva l'elemento ritornato da una prima invocazione di previous.
     * Si invoca poi il metodo previous per andare a sostituire l'elemento appena restituito con un oggetto diverso.
     * L'elemento ritornato da previous deve essere stato sostituito dall'elemento passato.
     * Si controlla dunque che la lista non contenga piu' l'elemento usando il metodo contains.
     * @homework.exp <code>contains</code> ritorna <code>false</code>
     */
    @Test
    public void testSetAfterPrevious()
    {
        lit = list.listIterator(list.size());
        Object modified = lit.previous();

        lit.set(4);

        assertFalse(list.contains(modified));
    }

    /**
     * Test del metodo <code>public void set(Object o)</code>: testa che il metodo <code>set</code> non lanci eccezione
     * nel caso in cui si provi a settare 2 volte uno stesso elemento. Dopo una modifica l'iteratore continua ad essere valido.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando l'iteratore non e' in uno stato valido.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore ha letto il primo elemento.
     * @homework.post L'iteratore e' ancora in uno stato valido. L'elemento letto da next e' stato modificato.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Si ottiene un elemento con next che poi viene sostituito.
     * Tale sostituizione ha successo perche' prima ho invocato il metodo next.
     * Una successiva invocazione deve funzionare ancora perche' un set non invalida l'iteratore.
     * Si controlla con il metodo contains che il secondo settaggio abbia avuto successo.
     * @homework.exp <code>contains</code> ritorna <code>true</code>
     */
    @Test
    public void testSetMultipleSet()
    {
        Object expected = 4;
        lit.next();
        lit.set(3);

        lit.set(expected);

        assertTrue(list.contains(expected));
    }

    /**
     * Test del metodo <code>public void set()</code>: testa che il metodo <code>set</code> lanci eccezione
     * <code>IllegalStateException</code> nel caso in cui si provi a chiamare il metodo set subito dopo aver invocato il metodo add
     * senza una precedente invocazione del metodo next o previous.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando l'iteratore non e' in uno stato valido.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento. E' appena stato
     * inserito un elemento all'interno della lista.
     * @homework.post L'iteratore non e' stato modificato. La lista non ha subito modifiche.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Si aggiunge un elemento nella prima posizione della lista.
     * Una successiva invocazione del metodo set deve sollevare eccezione perche' non ho scansionato un ulteriore elemento dopo aver
     * eseguito l'inserimento.
     * @homework.exp L'eccezione <code>IllegalStateException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testSetExceptionAfterAdd()
    {
        lit.add(4);

        assertThrows(IllegalStateException.class, () -> lit.set(3));
    }

    /**
     * Test del metodo <code>public void set()</code>: testa che il metodo <code>set</code> lanci eccezione
     * <code>IllegalStateException</code> nel caso in cui si provi a chiamare il metodo set subito dopo aver invocato il metodo remove
     * senza una precedente invocazione del metodo next o previous.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando l'iteratore non e' in uno stato valido.
     * @homework.pre Oggetto ListAdapter inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento. E' appena stato
     * rimosso un elemento dalla lista.
     * @homework.post L'iteratore non e' stato modificato. La lista non ha subito modifiche.
     * @homework.testDescr Si crea una lista e il suo rispettivo iteratore. Si scansiona un elemento con next che poi viene rimosso.
     * Una successiva invocazione del metodo set deve sollevare eccezione perche' non ho scansionato un ulteriore elemento dopo aver
     * eseguito la cancellazione.
     * @homework.exp L'eccezione <code>IllegalStateException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testSetExceptionAfterRemove()
    {
        lit.next();
        lit.remove();

        assertThrows(IllegalStateException.class, () -> lit.set(3));
    }


}
