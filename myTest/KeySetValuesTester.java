package myTest;

import org.junit.Before;
import org.junit.Test;

import myAdapter.*;

import static org.junit.Assert.*;


/**
 * Questa suite di test ha il compito di testare tutte le funzionalita' delle classi {@code KeySet} e {@code ValueCollection}.<p>
 * Il corretto comportamento degli iteratori e' testato in una suite separata. <p>
 * Si vuole dimostrare che tutti i metodi definiti in {@code KeySet} e {@code ValueCollection} funzionino correttamente.
 *
 * @author Formaggio Alberto
 *
 * @safe.des Per testare la classe nella sua interezza e' stato testato ciascun metodo fornendo in input parametri validi
 * e non validi in modo da testare il piu' ampio numero di casi possibili in cui la collezione si puo' trovare.<p>
 * La documentazione di ciascun test case e' stata eseguita seguendo la colonna "homework" fornita nel file della consegna,
 * inoltre i metodi hanno tutti un nome che e' il piu' evocativo possibile.
 * @safe.pre Tutti i metodi definiti nella classe {@code AbstractCollection} sono gia' stati testati esaustivamente nella suite
 * <a href="EntrySetAbstractCollectionTester.html">EntrySetAbstractCollectionTester</a>. Tali metodi non verranno dunque testati
 * nuovamente qui dal momento che sono esattamente gli stessi.
 * @safe.post Si sono ottenuti i risultati dell'esecuzione di tutti i test in questa suite.
 * @safe.records Consultare il file <a href="..\..\Test suite execution records\Test Results - KeySetValuesTester.html">
 *    "Test Results - KeySetValuesTester.html"</a> nella cartella "Test suite execution records"
 * @safe.exec I test sono stati eseguiti utilizzando JUnit v4.13 e hamcrest v1.3.
 * Per poter lanciare i test e' necessario inserire i file .jar di questi framework all'interno del CLASSPATH settando tale
 * variabile di ambiente.
 */
public class KeySetValuesTester
{
    private MapAdapter map;
    private HSet keys;
    private HCollection values;

    /**
     * Crea un oggetto MapAdapter contenente le seguenti coppie chiave valore:
     * <pre>
     * | 0 |  "zero" |
     * | 1 |  "uno"  |
     * | 2 |  "due"  |
     * </pre>
     * Crea poi il keySet e la values Collection contenenti tali chiavi e valori.
     */
    @Before
    public void initialize()
    {
        map = new MapAdapter();
        map.put(0, "zero");
        map.put(1, "uno");
        map.put(2, "due");

        keys = map.keySet();
        values = map.values();
    }

    //TEST DEL KEYSET

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la rimozione di un elemento che era presente
     * all'interno del set venga ritornato <code>true</code>.
     * @homework.des Accertamento del funzionamento di {@code remove} quando la chiave passata non si trova nel keySet
     * Si vuole che quando l'elemento non sia presente nel set(quindi nella mappa) la rimozione non abbia successo e venga
     * ritornato il corretto valore booleano.
     * @homework.pre Set inizializzato dal metodo initialize()
     * @homework.post E' stato rimosso un elemento dal set. L'elemento e' quello passato come parametro.
     * @homework.testDescr Si rimuove un valore tra quelli che era stato precedentemente inserito all'interno del set e si testa se il metodo
     * <code>remove(Object)</code> ha ritornato <code>true</code>
     * @homework.exp <code>remove</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testKeySetRemoveTrueIfKeyWasInSet()
    {
        boolean wasRemoved = keys.remove(1);

        assertTrue(wasRemoved);
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la rimozione di un elemento che non era presente
     * all'interno del set venga ritornato <code>false</code>.
     * @homework.des Accertamento del funzionamento di {@code remove} quando la chiave passata non si trova nel keySet
     * Si vuole che quando l'elemento non sia presente nel set(quindi nella mappa) la rimozione non abbia successo e venga
     * ritornato il corretto valore booleano.
     * @homework.pre Set inizializzato dal metodo initialize()
     * @homework.post Il set e' rimasto invariato
     * @homework.testDescr Si prova a rimuovere un valore che non era stato precedentemente inserito nel set. Siccome l'elemento non era presente
     * {@code remove} deve ritornare {@code false}.
     * @homework.exp <code>remove</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testKeySetRemoveFalseIfKeyWasNotInSet()
    {
        boolean wasRemoved = keys.remove(6);

        assertFalse(wasRemoved);
    }


    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la tentata rimozione di un elemento
     * che non era presente all'interno del set abbia lasciato invariata la dimensione del set.
     * @homework.des Accertamento del funzionamento di {@code remove} quando la chiave passata non si trova nel keySet
     * Si vuole che quando l'elemento non sia presente nel set(quindi nella mappa) la rimozione non abbia successo e che quindi la dimensione sia la
     * stessa (ad esempio sono sicuro che non venga rimosso un elemento a caso).
     * @homework.pre Set inizializzato dal metodo initialize()
     * @homework.post Il set e' rimasto invariato. La dimensione e' rimasta invariata.
     * @homework.testDescr Dopo aver ottenuto il set di chiavi, si salva la sua dimensione prima di andare ad invocare
     * il metodo remove.
     * Si invoca il metodo remove passando una chiave che non e' presente all'interno del keySet
     * @homework.exp {@code size} deve ritornare la dimensione precedentemente salvata.
     */
    @Test
    public void testKeySetRemoveFailsSameSize()
    {
        int previousSize = keys.size();

        keys.remove(6);

        assertEquals(previousSize, keys.size());
    }


    /**
     * Test del metodo {@code public void boolean remove(Object o)}: Testo che l'invocazione di {@code remove} passando una chiave all'interno del set,
     * rimuova effettivamente l'elemento dal set. Dopo la rimozione l'elemento non deve essere piu' presente.
     * @homework.des Accertamento del corretto funzionamento di {@code remove} con rimozione dell'elemento dal set a cui apparteneva.
     * @homework.pre keyset creato dal metodo {@code initialize}
     * @homework.post La chiave passata al metodo remove e' stata rimossa dal set. La dimensione e' diminuita di 1.
     * @homework.testDescr Si prova a rimuovere una chiave (1) che e' presente all'interno del set (e' stata inserita all'interno del set dal
     * metodo initialize).
     * Siccome 1 e' presente nel set, dopo la rimozione l'elemento non deve piu' essere presente. Si testa cio' usando il metodo
     * contains.
     * @homework.exp {@code contains} ritorna {@code false}
     */
    @Test
    public void testKeySetRemoveRemovesKeyFromSet()
    {
        keys.remove(1);

        assertFalse(keys.contains(1));
    }

    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: testa che il metodo <code>contains</code> ritorni <code>true</code>
     * quando si cerca una chiave che e' presente all'interno del set.
     * @homework.des Accertamento del funzionamento di {@code contains} quando la chiave passata si trova nel keyset
     * @homework.pre keyset inizializzato dal metodo initialize().
     * @homework.post Il set e' rimasto invariato
     * @homework.testDescr Si cerca una chiave presente all'interno del set con il metodo {@code contains}. Siccome la chiave e' stata
     * inserita al momento dell'inizializzazione e non e' mai stata rimossa, la chiave deve risultare presente.
     * @homework.exp <code>contains</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testContainsTrueIfKeyIsInMap()
    {
        boolean isContained = keys.contains(1);

        assertTrue(isContained);
    }


    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: testa che il metodo <code>contains</code> ritorni <code>false</code>
     * quando si cerca una chiave che non e' presente all'interno del set.
     * @homework.des Accertamento del funzionamento di {@code contains} quando la chiave passata non si trova nel keySet. Si accerta il corretto
     * valore di ritorno.
     * @homework.pre keyset inizializzato dal metodo initialize()
     * @homework.post Il set e' rimasto invariato
     * @homework.testDescr Si cerca all'interno del keyset una chiave che non e' mai stata inserita. Siccome la chiave non e' mai stata
     * inserita, bisogna che il metodo {@code contains} non trovi tale chiave.
     * @homework.exp <code>contains</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testContainsFalseIfKeyIsNotInMap()
    {
        boolean isContained = keys.contains(4);

        assertFalse(isContained);
    }


    /**
     * Test del metodo <code>public Object contains(Object o)</code>: testa che il metodo <code>contains</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui il valore passato sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>contains</code> qualora il valore non sia valido.
     * @homework.pre Set inizializzato con il metodo initialize()
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Si prova ad ispezionare il valore nullo. Essendo null un elemento non valido per il set,
     * deve essere sollevata eccezione: l'ispezione non puo' andare a buon fine.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testContainsExceptionIfNullParam()
    {
        assertThrows(NullPointerException.class, () -> keys.contains(null));
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 keySet con un numero di elementi diversi non siano uguali.
     * @homework.des Accertamento del funzionamento del metodo <code>equals</code> per cui quando si hanno 2 keySet diversi venga ritornato
     * {@code false}.
     * @homework.pre Set creato dal metodo initialize(). Secondo keyset contenente una parte degli elementi della mappa originale.
     * @homework.post Nessuno dei due set e' stato modificato.
     * @homework.testDescr Si crea una nuova mappa con solo una parte degli elementi della mappa originale.
     * Per quanto dichiarato dalla documentazione, i due keySet ritornati dalle 2 mappe sono diversi. Il controllo viene fatto usando equals()
     * @homework.exp il metodo {@code equals} ritorna <code>false</code>
     */
    @Test
    public void testKeySetEqualsDifferentSize()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(0,"zero");
        map2.put(1,"uno");

        boolean areEqual = keys.equals(map2.keySet());

        assertFalse(areEqual);
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 keyset con lo stesso numero di elementi ma con
     * almeno una chiave diversa siano diversi.
     * @homework.des Accertamento del funzionamento del metodo <code>equals</code> per cui quando si hanno 2 keySet diversi venga ritornato
     * {@code false}.
     * @homework.pre Set creato dal metodo initialize(). Secondo keyset contenente un numero di elementi pari alla mappa originale ma chiavi diverse.
     * @homework.post Nessuno dei due set e' stato modificato.
     * @homework.testDescr Si crea una nuova mappa con 2 entries uguali a quelle della mappa originale e una che differisce
     * per la chiave.
     * Per quanto dichiarato dalla documentazione, i due keySet ritornati dalle 2 mappe sono diversi in quanto almeno una chiave e' diversa.
     * Il controllo viene fatto usando equals()
     * @homework.exp il metodo {@code equals} ritorna <code>false</code>
     */
    @Test
    public void testKeySetEqualsDifferentKeysSameValues()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(5,"due");
        map2.put(1,"uno");
        map2.put(0,"zero");

        boolean areEqual = keys.equals(map2.keySet());

        assertFalse(areEqual);
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 keyset con lo stesso numero di elementi e stesse chiavi
     * siano uguali
     * @homework.des Accertamento del funzionamento del metodo <code>equals</code> per cui quando si hanno 2 keySet uguali venga ritornato
     * {@code true}. Il risultato non deve dipendere dal campo Value relativo all'entry a cui la chiave appartiene. Ovvero, anche se i valori sono
     * diversi ma le chiavi sono le stesse, equals deve avere successo.
     * @homework.pre Set creato dal metodo initialize(). Secondo keyset contenente un numero di elementi pari alla mappa originale con le stesse chiavi.
     * @homework.post Nessuno dei due set e' stato modificato.
     * @homework.testDescr Si crea una nuova mappa dove tutti le entries hanno la stessa chiave della mappa originale.
     * Per quanto dichiarato dalla documentazione, i due keySet ritornati dalle 2 mappe sono uguali in quanto tutte le chiavi sono uguali.
     * Il controllo viene fatto con {@code equals}
     * @homework.exp il metodo {@code equals} ritorna <code>true</code>
     */
    @Test
    public void testKeySetEqualsSameKeys()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(2,"anyValue");
        map2.put(1,"uno");
        map2.put(0,"zero");

        boolean areEqual = keys.equals(map2.keySet());

        assertTrue(areEqual);
    }

    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che 2 keyset uguali abbiano lo stesso hashCode
     * @homework.des due keyset uguali hanno lo stesso codice hash.
     * @homework.pre 2 keyset opportunamente creati uguali tra loro secondo i parametri definiti da equals (stesse chiavi nelle entries).
     * @homework.post i set sono rimasti invariati.
     * @homework.testDescr Creazione di un nuovo oggetto MapAdapter. Ad entrambe le mappe esistenti, aggiungo le stesse chiavi inserite dal
     * metodo initialize(). Dalle due mappe ottengo i keySet e ne calcolo l'hashCode
     * @homework.exp I due keyset hanno lo stesso hashCode.
     */
    @Test
    public void testKeySetHashCodeSameKeys()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(2,"qualsiasiValore");
        map2.put(1,"anyValue");
        map2.put(0,"zero");

        int hash1 = keys.hashCode();
        int hash2 = map2.keySet().hashCode();

        assertEquals(hash1,hash2);
    }

    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che 2 set diversi, anche parzialmente, abbiano due codici
     * hash diversi.
     * @homework.des due set diversi hanno codici hash diversi. Si testa inserendo nel set elementi comuni e non
     * @homework.pre 2 set con chiavi almeno in parte diverse per quanto definito da equals.
     * @homework.post i set sono rimasti invariati
     * @homework.testDescr Creazione di un nuovo oggetto MapAdapter. Ad entrambe le mappe esistenti, aggiungo le stesse chiavi inserite dal metodo
     * initialize ma almeno una chiave diversa. I due set devono avere codici hash diversi in quanto differiscono per almeno una chiave.
     * @homework.exp i due keyset NON hanno lo stesso hashCode.
     */
    @Test
    public void testKeySetHashCodeDifferentKeys()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(2,"due");
        map2.put("anyKey","uno");
        map2.put(0,"zero");

        int hash1 = keys.hashCode();
        int hash2 = map2.keySet().hashCode();

        assertNotEquals(hash1,hash2);
    }


    //TEST BACKING DEL KEYSET. Degli altri metodi e' gia' stata accertato il funzionamento in EntrySetTester
    /**
     * Test del metodo {@code public void boolean remove(Object o)}: Testo che l'invocazione di {@code remove} passando una chiave all'interno del set,
     * rimuova l'entry associata anche all'interno della mappa.
     * @homework.des Accertamento del corretto comportamento di backing di {@code remove} con rimozione dell'elemento dalla mappa a cui apparteneva.
     * @homework.pre keyset creato dal metodo {@code initialize}
     * @homework.post L'entry associata alla chiave passata al metodo remove e' stata rimossa dalla mappa.
     * @homework.testDescr Si prova a rimuovere una chiave che e' presente all'interno del keyset. La rimozione deve rimuovere l'entry anche dalla
     * mappa. Si verifica cie' usando il metodo {@code contains}
     * @homework.exp {@code contains} ritorna {@code false}
     */
    @Test
    public void testKeySetRemoveBacking()
    {
        keys.remove(1);

        assertFalse(map.containsKey(1));
    }




    //TEST VALUES

    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: testa che il metodo <code>contains</code> ritorni <code>true</code>
     * quando si cerca un valore che e' presente all'interno del set.
     * @homework.des Accertamento del funzionamento di {@code contains} quando il valore passato si trova nella collezione di values.
     * Si accerta il corretto valore di ritorno.
     * @homework.pre collezione di values inizializzata dal metodo initialize().
     * @homework.post La collezione e' rimasta invariata
     * @homework.testDescr Si cerca un valore presente all'interno della collezione con il metodo {@code contains}. Siccome il valore e' stato
     * inserita al momento dell'inizializzazione e non e' mai stato rimosso, il valore deve risultare presente.
     * @homework.exp <code>contains</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testContainsTrueIfValueIsInMap()
    {
        boolean isContained = values.contains("uno");

        assertTrue(isContained);
    }


    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: testa che il metodo <code>contains</code> ritorni <code>false</code>
     * quando si cerca un valore che non e' presente all'interno del set.
     * @homework.des Accertamento del funzionamento di {@code contains} il valore passato si trova nella collezione di values.
     * Si accerta il corretto valore di ritorno.
     * @homework.pre collezione di values inizializzata dal metodo initialize().
     * @homework.post La collezione e' rimasta invariata
     * @homework.testDescr Si cerca un valore all'interno della collezione che non e' mai stato inserito. Siccome il valore non e' mai stato
     * inserito, bisogna che il metodo {@code contains} non trovi tale valore.
     * @homework.exp <code>contains</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testContainsFalseIfValueIsNotInMap()
    {
        boolean isContained = values.contains("quattro");

        assertFalse(isContained);
    }



    /**
     * Test del metodo <code>public Object contains(Object o)</code>: testa che il metodo <code>contains</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui il valore passato sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>contains</code> qualora il valore non sia valido.
     * @homework.pre collezione di values inizializzata dal metodo initialize().
     * @homework.post La collezione e' rimasta invariata
     * @homework.testDescr Si prova ad ispezionare il valore nullo. Essendo null un elemento non valido per la collezione,
     * deve essere sollevata eccezione: l'ispezione non puo' andare a buon fine.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testValuesContainsExceptionIfNullParam()
    {
        assertThrows(NullPointerException.class, () -> values.contains(null));
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la rimozione di un elemento che era presente
     * all'interno della collezione di values venga ritornato <code>true</code>.
     * @homework.des Accertamento del funzionamento di {@code remove} quando il valore passato non si trova nella collezione.
     * Si vuole che quando l'elemento non sia presente nella collezione(quindi nella mappa) la rimozione non abbia successo e venga
     * ritornato il corretto valore booleano.
     * @homework.pre Collezione inizializzata dal metodo initialize()
     * @homework.post E' stato rimosso un elemento dalla collezione. L'elemento e' quello passato come parametro.
     * @homework.testDescr Si rimuove un valore tra quelli che era stato precedentemente inserito all'interno della collezione e si testa se il metodo
     * <code>remove(Object)</code> ha ritornato <code>true</code>
     * @homework.exp <code>remove</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testValuesRemoveTrueIfValueWasInSet()
    {
        boolean wasRemoved = values.remove("uno");

        assertTrue(wasRemoved);
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la rimozione di un elemento che non era presente
     * all'interno della collezione di values venga ritornato <code>false</code>.
     * @homework.des Accertamento del funzionamento di {@code remove} quando il valore passato non si trova nella collezione di valori.
     * Si vuole che quando l'elemento non sia presente nella collezione (quindi nemmeno nella mappa) la rimozione non abbia successo e venga
     * ritornato il corretto valore booleano.
     * @homework.pre Collezione di values inizializzata dal metodo initialize()
     * @homework.post La collezione e' rimasta invariata
     * @homework.testDescr Si prova a rimuovere un valore che non era stato precedentemente inserito nella collezione. Siccome l'elemento
     * non era presente {@code remove} deve ritornare {@code false}.
     * @homework.exp <code>remove</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testValuesRemoveFalseIfValueWasNotInSet()
    {
        boolean wasRemoved = values.remove("sei");

        assertFalse(wasRemoved);
    }


    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la tentata rimozione di un valore
     * che non era presente all'interno della collezione abbia lasciato invariata la dimensione della collezione.
     * @homework.des Accertamento del funzionamento di {@code remove} quando la chiave passata non si trova nella collezione di values
     * Si vuole che quando l'elemento non sia presente nella collezione(quindi nella mappa) la rimozione non abbia successo e che quindi
     * la dimensione sia la stessa (ad esempio sono sicuro che non venga rimosso un elemento a caso).
     * @homework.pre Collezione di values inizializzata dal metodo initialize()
     * @homework.post La collezione e' rimasta invariata. La dimensione e' rimasta invariata.
     * @homework.testDescr Dopo aver ottenuto la collection di values, si salva la sua dimensione prima di andare ad invocare
     * il metodo remove.
     * Si invoca il metodo remove passando un valore che non e' presente all'interno della collezione.
     * @homework.exp {@code size} deve ritornare la dimensione precedentemente salvata.
     */
    @Test
    public void testValuesRemoveFailsSameSize()
    {
        int previousSize = values.size();

        values.remove("sei");

        assertEquals(previousSize, values.size());
    }

    /**
     * Test del metodo {@code public void boolean remove(Object o)}: Testo che l'invocazione di {@code remove} passando un valore all'interno
     * della collezione, rimuova effettivamente l'elemento dalla collezione. Dopo la rimozione l'elemento non deve essere piu' presente.
     * @homework.des Accertamento del corretto funzionamento di {@code remove} con rimozione dell'elemento dalla collezione a cui apparteneva.
     * @homework.pre Collection di values creata dal metodo {@code initialize}
     * @homework.post Il valore passato al metodo remove e' stato rimosso dalla collection. La dimensione e' diminuita di 1.
     * @homework.testDescr Si prova a rimuovere un valore ("uno") che e' presente all'interno della collezione (e' stato inserita all'interno
     * della collezione dal metodo initialize).
     * Siccome "uno" e' presente nel set, dopo la rimozione l'elemento non deve piu' essere presente. Si testa cio' usando il metodo
     * contains.
     * @homework.exp {@code contains} ritorna {@code false}
     */
    @Test
    public void testValuesRemoveRemovesValueFromSet()
    {
        values.remove("uno");

        assertFalse(values.contains("uno"));
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 collezioni di values con gli stessi elementi, anche
     * duplicati, siano uguali nonostante le chiavi siano diverse. L'uguaglianza deve dipendere solo dai valori.
     * @homework.des Accertamento del funzionamento del metodo <code>equals</code> per cui quando si hanno 2 collezioni di values uguali venga ritornato
     * {@code true}. Il risultato non deve dipendere dal campo Key relativo all'entry a cui il valore appartiene. Ovvero, anche se le chiavi sono
     * diverse ma i valori sono gli stessi, equals deve avere successo.
     * @homework.pre Collezioni create dal metodo initialize() con l'aggiunta di duplicati.
     * Seconda collezione contenente un numero di elementi pari alla mappa originale con gli stessi valori ma chiavi diverse.
     * @homework.post Nessuna delle due collezioni e' stata modificata.
     * @homework.testDescr Si inseriscono dei duplicati di valori per avere un caso generale.
     * Si crea una nuova mappa dove tutte le entries hanno gli stessi valori della mappa originale ma chiavi diverse (0,5,10,15,20).
     * Per quanto dichiarato dalla documentazione, le 2 collezioni ritornate dalle 2 mappe sono uguali in quanto tutti i valori sono uguali.
     * Il controllo viene fatto con {@code equals}
     * @homework.exp il metodo {@code equals} ritorna <code>true</code>
     */
    @Test
    public void testValuesEqualsSameValuesDuplicatesAnyKeys()
    {
        map.put(3,"uno");
        map.put(4,"uno");
        HIterator valIt = values.iterator();
        MapAdapter map2 = new MapAdapter();
        int i = 0;
        while(valIt.hasNext())
        {
            Object val = valIt.next();
            map2.put(i,val);
            i+=5;
        }

        boolean areEqual = values.equals(map2.values());

        assertTrue(areEqual);
    }

    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 collezione di values con un numero di elementi diversi 
     * non siano uguali.
     * @homework.des Accertamento del funzionamento del metodo <code>equals</code> per cui quando si hanno 2 collezioni di valori diversi venga ritornato
     * {@code false}.
     * @homework.pre Collezione di values creata dal metodo initialize(). Seconda collezione contenente solo una parte degli elementi della mappa originale.
     * @homework.post Nessuna delle due collezioni e' stata modificata.
     * @homework.testDescr Si crea una nuova mappa con solo una parte degli elementi della mappa originale.
     * Per quanto dichiarato dalla documentazione, le due collection di values ritornate dalle 2 mappe sono diverse.
     * Il controllo viene fatto usando equals()
     * @homework.exp il metodo {@code equals} ritorna <code>false</code>
     */
    @Test
    public void testValuesEqualsDifferentSize()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(0,"zero");
        map2.put(1,"uno");
        map2.put(2,"due");
        map2.put(3,"uno");

        boolean areEqual = values.equals(map2.values());

        assertFalse(areEqual);
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 collezioni di values con lo stesso numero di elementi ma con
     * almeno un valore diverso siano diverse.
     * @homework.des Accertamento del funzionamento del metodo <code>equals</code> per cui quando si hanno 2 collezioni di values diverse venga ritornato
     * {@code false}.
     * @homework.pre Collezione di values creato dal metodo initialize(). Secondo keyset contenente un numero di elementi pari alla mappa originale ma chiavi diverse.
     * @homework.post Nessuna delle due collezioni e' stata modificata.
     * @homework.testDescr Si crea una nuova mappa con 2 entries uguali a quelle della mappa originale e una che differisce
     * per il valore.
     * Per quanto dichiarato dalla documentazione, le due collection ritornate dalle 2 mappe sono diverse in quanto almeno un valore e' diverso.
     * Il controllo viene fatto usando equals()
     * @homework.exp il metodo {@code equals} ritorna <code>false</code>
     */
    @Test
    public void testValuesEqualsDifferentValuesSameKeys()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(2,"val1");
        map2.put(1,"uno");
        map2.put(0,"zero");

        boolean areEqual = values.equals(map2.values());

        assertFalse(areEqual);
    }


    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che 2 collezioni di values uguali abbiano lo stesso hashCode
     * @homework.des due collezioni di values uguali hanno lo stesso codice hash.
     * @homework.pre 2 collezioni di values opportunamente create uguali tra loro secondo i parametri definiti da equals
     * (stessi valori nelle entries).
     * @homework.post i set sono rimasti invariati.
     * @homework.testDescr Creazione di un nuovo oggetto MapAdapter. Ad entrambe le mappe esistenti, aggiungo gli stessi valori inseriti dal
     * metodo initialize(). Dalle due mappe ottengo le collezioni di values e ne calcolo l'hashCode
     * @homework.exp Le due collezioni hanno lo stesso hashCode.
     */
    @Test
    public void testValuesHashCodeSameValues()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put("chiaveQualsiasi","due");
        map2.put(1,"uno");
        map2.put("anyKey","zero");

        int hash1 = values.hashCode();
        int hash2 = map2.values().hashCode();

        assertEquals(hash1,hash2);
    }

    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che 2 collection di values diverse, anche parzialmente, abbiano due codici
     * hash diversi.
     * @homework.des due collezioni diverse hanno codici hash diversi. Si testa inserendo nella collection elementi comuni e non
     * @homework.pre 2 collection di values con valori almeno in parte diverse per quanto definito da equals.
     * @homework.post le due collection sono rimaste invariate.
     * @homework.testDescr Creazione di un nuovo oggetto MapAdapter. Ad entrambe le mappe esistenti, aggiungo le stesse chiavi inserite dal metodo
     * initialize ma almeno un valore diverso. Le due collezioni devono avere codici hash diversi in quanto differiscono per almeno un valore.
     * @homework.exp Le due collezioni NON hanno lo stesso hashcode
     */
    @Test
    public void testValuesHashCodeDifferentValues()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(2,"valore");
        map2.put(1,"uno");
        map2.put(0,"anyVal");

        int hash1 = values.hashCode();
        int hash2 = map2.values().hashCode();

        assertNotEquals(hash1,hash2);
    }

    //BACKING VALUESET


    /**
     * Test del metodo {@code public void boolean remove(Object o)}: Testo che l'invocazione di {@code remove} passando un valore all'interno della collezione,
     * rimuova l'entry associata anche all'interno della mappa.
     * @homework.des Accertamento del corretto comportamento di backing di {@code remove} con rimozione dell'elemento dalla mappa a cui apparteneva.
     * <b>ATTENZIONE</b>: Si fa notare che, non essendo specificato dalla documentazione, la collezione ritornata <b>NON</b> e' un set, quindi
     * in questo caso ammette duplicati. Non essendo un set, quando si rimuove un valore viene rimossa solo un'entry a caso tra quelle
     * che hanno come valore il valore passato come parametro.
     * @homework.pre Collection di values creata dal metodo {@code initialize}
     * @homework.post Un'entry associata al valore passata al metodo remove e' stata rimossa dalla mappa. La dimensione della mappa e' diminuita di 1.
     * @homework.testDescr Si prova a rimuovere un valore che e' presente all'interno della collezione. La rimozione deve rimuovere un'entry
     * con tale valore anche dalla mappa. Si verifica cio' usando il metodo {@code contains}
     * @homework.exp {@code contains} ritorna {@code false}
     */
    @Test
    public void testValuesRemoveBacking()
    {
        values.remove("uno");

        assertFalse(map.containsKey(1));
        assertFalse(map.containsValue("uno"));
    }


    /**
     * Test del metodo {@code public void boolean remove(Object o)}: Testo che l'invocazione di {@code remove} passando un valore all'interno della collezione,
     * rimuova un'entry associata anche all'interno della mappa.
     * @homework.des Accertamento del corretto comportamento di backing di {@code remove} con rimozione dell'elemento dalla mappa a cui apparteneva.
     * <b>ATTENZIONE</b>: Si fa notare che, non essendo specificato dalla documentazione, la collezione ritornata <b>NON</b> e' un set, quindi
     * in questo caso ammette duplicati. Non essendo un set, quando si rimuove un valore viene rimossa solo un'entry a caso tra quelle
     * che hanno come valore il valore passato come parametro.
     * @homework.pre Collection di values creata dal metodo {@code initialize}
     * @homework.post Un'entry associata al valore passata al metodo remove e' stata rimossa dalla mappa.
     * @homework.testDescr Si inseriscono dei duplicati di un certo valore all'interno della mappa associati a chiavi diverse.
     * Si prova a rimuovere un valore che e' presente all'interno della collezione. La rimozione deve rimuovere soltanto un'entry
     * con tale valore anche dalla mappa. Si verifica cio' usando il metodo size
     * @homework.exp {@code size} ritorna la dimensione precedente alla rimozione -1.
     */
    @Test
    public void testValuesRemoveRemovesJustOneEntryBacking()
    {
        map.put(3,"uno");
        map.put(4,"uno");
        values = map.values();
        int previousSize = map.size();

        values.remove("uno");

        assertEquals(previousSize,map.size()+1);
    }
}
