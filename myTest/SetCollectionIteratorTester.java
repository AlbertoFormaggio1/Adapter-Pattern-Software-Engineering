package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Questa suite di test ha il compito di testare tutte le funzionalita' degli iteratori che operano sulle collezioni ritornate dall'oggetto
 * {@code MapAdapter}. In questa suite si testano dunque le funzionalita' degli iteratori {@code EntrySetIterator,KeySetIterator,ValuesIterator}.<p>
 * Si testa quindi che i metodi definiti all'interno degli iteratori ritornino dei valori compatibili con la mappa e con la collezione su cui si
 * appoggiano.<p>
 * Sono inoltre testate le funzionalita' di backing sul MapAdapter originale dovute agli iteratori.<p>
 * Per una maggiore comprensibilita' il codice e' stato suddiviso in sezioni, una per ciascun tipo di iteratore.
 *
 * @safe.des Per testare la classe nella sua interezza e' stato testato ciascun metodo fornendo in input parametri validi
 * e non validi oltre a testare le varie configurazioni tra metodi cosi' da testare il piu' ampio numero di casi possibili in cui
 * l'iteratore puo' trovarsi.<p>
 * Dal momento che {@code KeySetIterator} estende la classe {@EntrySetIterator} e il comportamento dei metodi {@code hasNext} e
 * {@code remove} resta invariato. Per tale classe si testaranno solo le funzionalita' del metodo {@code next} visto che si e' gia' testato
 * in precedenza il funzionamento degli altri 2 metodi.<p>
 * Per la casse {@code ValuesIterator} il ragionamento e' analogo dal momento che i metodi {@code hasNext} e {@code remove} sono
 * semplicemente dei metodi wrapper che richiamano solamente i metodi rispettivi definiti in {@code EntrySetIterator} senza fare alcun
 * tipo di manipolazione.<p>
 * La documentazione di ciascun test case e' stata eseguita seguendo la colonna "homework" fornita nel file della consegna,
 * inoltre i metodi hanno tutti un nome che e' il piu' evocativo possibile.
 * @safe.pre Si suppongo tutte le funzionalita' delle collezioni gia' testate in un'altra suite.
 * @safe.post Si sono ottenuti i risultati dell'esecuzione di tutti i test in questa suite.
 * @safe.records Consultare il file <a href="..\..\Test suite execution records\Test Results - SetCollectionIteratorTester.html">
 *     "Test Results - SetCollectionIteratorTester.html"</a> nella cartella "Test suite execution records"
 * @safe.exec I test sono stati eseguiti utilizzando JUnit v4.13 e hamcrest v1.3.
 * Per poter lanciare i test e' necessario inserire i file .jar di questi framework all'interno del CLASSPATH settando tale
 * variabile di ambiente.
 *
 * @author Formaggio Alberto
 */

public class SetCollectionIteratorTester
{
    private MapAdapter map;
    private HSet entrySet;
    private HSet keys;
    private HCollection values;
    private HIterator entryIt;
    private HIterator keyIt;
    private HIterator valIt;


    /**
     * Crea un oggetto MapAdapter contenente le seguenti coppie chiave valore:
     * <pre>
     * | 0 |  "zero" |
     * | 1 |  "uno"  |
     * | 2 |  "due"  |
     * </pre>
     * Crea poi gli entrySet, keySet e valueCollection relativi con gli iteratori associati.
     */
    @Before
    public void initialize()
    {
        map = new MapAdapter();
        map.put(0,"zero");
        map.put(1,"uno");
        map.put(2,"due");
        entrySet = map.entrySet();
        entryIt = entrySet.iterator();

        keys = map.keySet();
        values = map.values();
        keyIt = keys.iterator();
        valIt = values.iterator();
    }

    /**
     * Test del metodo <code>public Object next()</code>: Testa che il numero di elementi scansionati dall'iteratore sia pari alla dimensione del set.
     * Testo inoltre che tutti gli elementi scansionati dall'iteratore siano presenti all'interno del set.
     * @homework.des Accertamento che ci sia corrispondenza tra gli elementi del set e i valori ritornati dalle successive invocazioni
     * di <code>next()</code>.
     * @homework.pre set inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento. Iteratore
     * creato.
     * @homework.post L'iteratore e' arrivato alla fine del set. Il set e' rimasto invariato.
     * @homework.testDescr Si crea un nuovo iteratore e si scorre il set inserendo tutte le entries all'interno di una hashMap usando come chiave
     * l'entry stessa e come valore un oggetto dummy.
     * Siccome le chiavi in una hashtable non possono essere uguali, se tutte le entries ritornate sono diverse allora la dimensione della
     * hashtable alla fine deve essere pari alla dimensione del set.
     * @homework.exp la size della hashtable e' la stessa dell'entryset.
     */
    @Test
    public void testEntrySetNextScansAllElements()
    {
        Hashtable ht = new Hashtable();
        Object DUMMY = new Object();

        //Inserisco le entry nella hashTable con chiave la entry stessa e come valore il DUMMY. Siccome inserisco
        //Entries diverse, tutti gli hashCode devono essere diversi e quindi tutte le entry sono inserite nell'hashtable
        while(entryIt.hasNext())
            ht.put(entryIt.next(), DUMMY);

        assertEquals(entrySet.size(),ht.size());
    }


    /**
     * Test del metodo <code>public Object next()</code>: Testa che next scansioni tutti gli elementi e che le entries siano corrette.
     * @homework.des Accertamento che ci sia corrispondenza tra gli elementi della mappa e i valori ritornati dalle successive invocazioni
     * di <code>next()</code>. (Si verifica che ad esempio non si sia scambiato il campo key con value alla creazione dell'entry da ritornare).
     * @homework.pre set inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento. Iteratore
     * creato.
     * @homework.post L'iteratore e' arrivato alla fine del set. Il set e' rimasto invariato.
     * @homework.testDescr Si crea un iteratore e si scorre l'intero entrySet. Si usa inoltre una variabile booleana isOk che viene settata
     * a false qualora qualcosa sia errato.
     * Per ciascuna entry si invoca il metodo get sulla mappa passando la chiave: il valore ritornato deve essere lo stesso dell'entry.
     * Se una di queste fallisce, isOk viene posto a {@code false}
     * @homework.exp isOk deve essere <code>true</code>.
     */
    @Test
    public void testEntrySetNextEntriesAreCorrect()
    {
        boolean isOk = true;
        while(entryIt.hasNext())
        {
            HMap.HEntry e = (HMap.HEntry) entryIt.next();
            if(map.get(e.getKey()) != e.getValue())
            {
                isOk = false;
                break;
            }
        }

        assertTrue(isOk);
    }

    /**
     * Test del metodo <code>public boolean hasNext()</code>: Testa HasNext quando l'iteratore non ha ispezionato tutti gli elementi
     * @homework.des accertamento del funzionamento di hasNext in una posizione qualsiasi della set
     * @homework.pre Oggetto setdapter inizializzato dal metodo Initialize(). L'iteratore *non* sta puntando all'ultimo elemento
     * @homework.post La set ha creato un HIterator. La set non ha subito modifiche.
     * @homework.testDescr Presa una set con almeno un elemento rimasto da ispezionare, si testa se l'iteratore ha un successivo
     * @homework.exp Il test deve ritornare <code>true</code>
     */
    @Test
    public void testHasNextWhenThereIsNext()
    {
        boolean actual = entryIt.hasNext();

        assertTrue(actual);
    }


    /**
     * Test del metodo <code>public boolean hasNext()</code>: Testa HasNext quando l'iteratore e' arrivato alla fine della scansione
     * @homework.des Accertamento del funzionamento di hasNext alla fine della set
     * @homework.pre Oggetto setdapter inizializzato dal metodo Initialize(). L'iteratore sta puntando all'ultimo elemento
     * @homework.post L'iteratore si e' spostato di una posizione. La set non ha subito modifiche.
     * @homework.testDescr Presa una set con nessun un elemento rimasto da ispezionare, si testa se l'iteratore ha un successivo
     * @homework.exp Il test deve ritornare <code>false</code>
     */
    @Test
    public void testHasNextWhenThereIsNotNext()
    {
        for (int i = 0; i < entrySet.size(); i++)
            entryIt.next();

        boolean actual = entryIt.hasNext();

        assertFalse(actual);
    }

    /**
     * Test del metodo <code>public Object next()</code>: testa che il metodo <code>next</code> lanci eccezione
     * <code>NoSuchElementException</code> nel caso in cui si provi ad ottenere un elemento con next quando l'iteratore ha gia'
     * raggiunto la fine della set.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando non ci sono elementi da restituire.
     * @homework.pre Oggetto setdapter inizializzato dal metodo initialize(). L'iteratore sta puntando a dopo l'ultimo elemento.
     * @homework.post L'iteratore non ha subito modifiche. La set non ha subito modifiche.
     * @homework.testDescr Si crea una set e il suo rispettivo iteratore. Si sposta tale iteratore fino alla fine della set, in modo che it.hasNext() ritorni false.
     * Si prova ora ad ottenere un nuovo elemento. Tale operazione non puo' avere successo perche' la set non ha altri elementi da ritornare.
     * @homework.exp L'eccezione <code>NoSuchElementException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testNextExceptionIfThereIsNotNext()
    {
        while (entryIt.hasNext())
            entryIt.next();

        assertThrows(NoSuchElementException.class, entryIt::next);
    }

    /**
     * Test del metodo <code>public void remove()</code>: testa che il metodo <code>remove</code> rimuova l'elemento dal set
     * @homework.des Accertamento del corretto comportamento dell'iteratore quando si rimuove un elemento appena scansionato da next.
     * @homework.pre Oggetto setdapter inizializzato dal metodo initialize(). L'iteratore sta puntando ad un elemento qualsiasi nella set.
     * @homework.post L'iteratore si e' posizionato correttamente tra il precedente e il successivo dell'elemento rimosso.
     * @homework.testDescr Si crea una set e il suo rispettivo iteratore. Si sposta tale iteratore di 2 posizioni e si prende l'ultimo elemento
     * ritornato dal meotodo next in una variabile chiamata <code>removed</code>.
     * Si invoca poi il metodo <code>remove</code>.
     * L'elemento precedentemente salvato non deve piu' essere presente all'interno della set. Si controlla cio' mediante il metodo contains() della set.
     * Siccome l'elemento e' stato ottenuto dal metodo next prima era sicuramente all'interno della set.
     * @homework.exp <code>contains(removed)</code> deve ritornare <code>false</code>, l'elemento e' stato rimosso dal set.
     */
    @Test
    public void testRemoveRemovesFromSet()
    {
        entryIt.next();
        Object removed = entryIt.next();

        entryIt.remove();

        assertFalse(entrySet.contains(removed));
    }


    /**
     * Test del metodo <code>public void remove()</code>: testa che il metodo <code>remove</code> lanci eccezione
     * <code>IllegalStateException</code> nel caso in cui si provi a rimuovere un elemento senza che nessuno sia mai stato invocato in precedenza.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando non ci sono elementi da restituire.
     * @homework.pre Oggetto setdapter inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento.
     * @homework.post L'iteratore non ha subito modifiche. La set non ha subito modifiche.
     * @homework.testDescr Si crea una set e il suo rispettivo iteratore. Si prova a rimuovere un elemento.
     * Tale operazione non puo' avere successo perche' non e' stato ispezionato nessun elemento.
     * @homework.exp L'eccezione <code>IllegalStateException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveExceptionIfNextWasNotInvoked()
    {
        assertThrows(IllegalStateException.class, entryIt::remove);
    }

    /**
     * Test del metodo <code>public void remove()</code>: testa che il metodo <code>remove</code> lanci eccezione
     * <code>IllegalStateException</code> nel caso in cui si provi a chiamare il metodo remove due volte di fila senza aver chiamato next
     * prima della seconda rimozione.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando l'iteratore non e' in uno stato valido.
     * @homework.pre Oggetto setdapter inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento.
     * @homework.post L'iteratore non e' stato modificato. La set non ha subito modifiche.
     * @homework.testDescr Si crea una set e il suo rispettivo iteratore. Si ottiene un elemento con next che poi viene rimosso.
     * Tale rimozione ha successo perche' prima ho invocato il metodo next.
     * Una successiva invocazione deve sollevare eccezione perche' non ho scansionato un ulteriore elemento.
     * @homework.exp L'eccezione <code>IllegalStateException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveExceptionCallingRemoveTwice()
    {
        entryIt.next();
        entryIt.remove();

        assertThrows(IllegalStateException.class, entryIt::remove);
    }

    /**
     * Test del metodo {@code public void boolean remove(Object o)}: Testo che l'invocazione di {@code remove} su un iteratore rimuova l'entry
     * che era stata ritornata da una precedente invocazione di {@code next}
     * @homework.des Accertamento del corretto comportamento di backing di {@code remove} con rimozione dell'elemento dalla mappa a cui apparteneva.
     * @homework.pre entrySet creato dal metodo {@code initialize}. HIterator creato e spostato in avanti di una posizione
     * @homework.post L'ultima entry ritornata da next e' stata rimossa dalla mappa.
     * @homework.testDescr Dopo aver creato l'iteratore su un set si legge un'entry.
     * L'invocazione di remove dopo aver invocato next deve aver rimosso l'elemento appena ritornato.
     * Testo che la mappa non contenga ne' il valore ne' la chiave contenuti nell'entry ritornata.
     * @homework.exp {@code containsKey} e {@code containsValue} ritornano {@code false}
     */
    @Test
    public void testEntrySetRemoveBacking()
    {
        HMap.HEntry removed = (HMap.HEntry) entryIt.next();

        entryIt.remove();

        assertFalse(map.containsKey(removed.getKey()) || map.containsValue(removed.getValue()));
    }


    //---------------------------------------------TEST VALUES ITERATOR------------------------------------------


    /**
     * Test del metodo <code>public Object next()</code>: testa che il metodo <code>next</code> lanci eccezione
     * <code>NoSuchElementException</code> nel caso in cui si provi ad ottenere un elemento con next quando l'iteratore ha gia'
     * raggiunto la fine della collezione.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando non ci sono elementi da restituire.
     * @homework.pre Collezione di values inizializzato dal metodo initialize(). L'iteratore sta puntando a dopo l'ultimo elemento.
     * @homework.post L'iteratore non ha subito modifiche. La collezione non ha subito modifiche.
     * @homework.testDescr Si crea una collezione e il suo rispettivo iteratore. Si sposta tale iteratore fino alla fine della collezione, in modo che it.hasNext() ritorni false.
     * Si prova ora ad ottenere un nuovo elemento. Tale operazione non puo' avere successo perche' la collezione non ha altri elementi da ritornare.
     * @homework.exp L'eccezione <code>NoSuchElementException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testValuesNextExceptionIfThereIsNotNext()
    {
        while (keyIt.hasNext())
            keyIt.next();

        assertThrows(NoSuchElementException.class, keyIt::next);
    }


    /**
     * Test del metodo <code>public Object next()</code>: Testa che il numero di elementi scansionati dall'iteratore sia pari alla dimensione della
     * collezione.
     * @homework.des Accertamento che ci sia corrispondenza tra gli elementi della collezione e i valori ritornati dalle successive invocazioni
     * di <code>next()</code>. Il numero di valori deve essere lo stesso.
     * @homework.pre Collezione di values inizializzata dal metodo initialize(). L'iteratore sta puntando al primo elemento. Iteratore
     * creato.
     * @homework.post L'iteratore e' arrivato alla fine della collezione. La collezione e' rimasta invariata.
     * @homework.testDescr Si crea un nuovo iteratore e una variabile contatore con lo scopo di contare tutti i valori presenti nella collezione
     * di values. Si scansiona con l'iteratore fino ad arrivare alla fine della collezione. Ad ogni iterazione il contatore viene incrementato
     * di una unita'.
     * @homework.exp Il valore del contatore e' pari alla dimensione della collezione.
     */
    @Test
    public void testValuesIteratorNextSizeCorrect()
    {
        int count = 0;

        while(valIt.hasNext())
        {
            valIt.next();
            count++;
        }

        assertEquals(values.size(), count);
    }


    /**
     * Test del metodo <code>public Object next()</code>: Testa che next scansioni tutti i valori presenti nel set.
     * @homework.des Accertamento che ci sia corrispondenza tra gli elementi della mappa e i valori ritornati dalle successive invocazioni
     * di <code>next()</code>. (Si verifica che ad esempio non si sia scambiato il campo key con value quando si ritorna un elemento).
     * @homework.pre Collezione inizializzata dal metodo initialize(). L'iteratore sta puntando al primo elemento. Iteratore
     * creato.
     * @homework.post L'iteratore e' arrivato alla fine della collezione. La collezione e' rimasta invariata.
     * @homework.testDescr Si inseriscono dei duplicati nei valori per avere un caso generale.
     * Si crea un iteratore e un vettore in cui andare ad inserire gli elementi scansionati.
     * Con l'iteratore ottengo tutti gli elementi presenti all'interno della collezione di values e li inserisco all'interno di un vettore.
     * Una volta fatto cio' rimuovo gli elementi ottenuti dalla collezione di values.
     * Se l'iteratore e' stato fatto correttamente, vector contiene tutti gli elementi della collezione: quando faccio il remove di tali
     * elementi tutti gli elementi devono essere stati rimossi dalla mappa.
     * Alla fine la dimensione della mappa e' zero. Si testa cio' con isEmpty
     * @homework.exp {@code isEmpty} deve essere <code>true</code>.
     */
    @Test
    public void testValuesIteratorNextAllValuesOfMap()
    {
        map.put(3,"uno");
        map.put(4,"due");
        values = map.values();
        valIt = values.iterator();
        Vector vec = new Vector();

        while(valIt.hasNext())
            vec.add(valIt.next());
        for(Object o: vec)
            values.remove(o); //Rimuovo tutti gli elementi che prima ho scansionato con l'iteratore: Devono essere tutti gli elementi
        //Della mappa quindi alla fine la mappa ha dimensione zero.

        assertTrue(values.isEmpty());
    }


    //----------------------------------------------TEST KEYSET ITERATOR----------------------------------------------

    /**
     * Test del metodo <code>public Object next()</code>: Testa che il numero di elementi scansionati dall'iteratore sia pari alla dimensione del set.
     * Testo inoltre che tutti gli elementi scansionati dall'iteratore siano presenti all'interno del set.
     * @homework.des Accertamento che ci sia corrispondenza tra gli elementi del set e i valori ritornati dalle successive invocazioni
     * di <code>next()</code>.
     * @homework.pre set inizializzato dal metodo initialize(). L'iteratore sta puntando al primo elemento. Iteratore
     * creato.
     * @homework.post L'iteratore e' arrivato alla fine del set. Il set e' rimasto invariato.
     * @homework.testDescr Si crea un nuovo iteratore e si scorre il set inserendo tutte le chiavi all'interno di una hashMap usando come chiave
     * la chiave stessa e come valore un oggetto dummy.
     * Siccome le chiavi in una hashtable non possono essere uguali, se tutte le chiavi ritornate sono diverse allora la dimensione della
     * hashtable alla fine deve essere pari alla dimensione del set.
     * @homework.exp la size della hashtable e' la stessa del keyset.
     */
    @Test
    public void testKeySetIteratorNextAllKeysAreScanned()
    {
        Hashtable ht = new Hashtable();
        Object DUMMY = new Object();

        //Inserisco le entry nella hashTable con chiave la entry stessa e come valore il DUMMY. Siccome inserisco
        //Entries diverse, tutti gli hashCode devono essere diversi e quindi tutte le entry sono inserite nell'hashtable
        while(keyIt.hasNext())
            ht.put(keyIt.next(), DUMMY);

        assertEquals(keys.size(),ht.size());
    }

    /**
     * Test del metodo <code>public Object next()</code>: testa che il metodo <code>next</code> lanci eccezione
     * <code>NoSuchElementException</code> nel caso in cui si provi ad ottenere un elemento con next quando l'iteratore ha gia'
     * raggiunto la fine del set.
     * @homework.des Accertamento del corretto comportamento e sollevamento dell'eccezione quando non ci sono elementi da restituire.
     * @homework.pre keyset inizializzato dal metodo initialize(). L'iteratore sta puntando a dopo l'ultimo elemento.
     * @homework.post L'iteratore non ha subito modifiche. Il set non ha subito modifiche.
     * @homework.testDescr Si crea un keySet e il suo rispettivo iteratore. Si sposta tale iteratore fino alla fine del set, in modo che it.hasNext() ritorni false.
     * Si prova ora ad ottenere un nuovo elemento. Tale operazione non puo' avere successo perche' il set non ha altri elementi da ritornare.
     * @homework.exp L'eccezione <code>NoSuchElementException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testKeySetNextExceptionIfThereIsNotNext()
    {
        while (keyIt.hasNext())
            keyIt.next();

        assertThrows(NoSuchElementException.class, keyIt::next);
    }
}
