package myTest;

import myAdapter.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Questa suite di test ha il compito di testare tutte le funzionalita' base della classe {@code AbstractCollection} e
 * {@code EntrySet}.<p>
 * Dal momento che {@code AbstractCollection} e', come dice il nome stesso, una classe astratta, non e' possibile testare
 * direttamente le funzionalita' definite al suo interno ma e' necessario testare una classe concreta che la estende.
 * Si e' deciso dunque di utilizzare a questo scopo la classe {@code EntrySet}.<p>
 * <p> Il corretto comportamento dell'iteratore della classe {@code EntrySet} e' testato in una suite separata. <p>
 * Si vuole dimostrare che tutti i metodi definiti in EntrySet e AbastractCollection funzionino correttamente.
 *
 *@author Formaggio Alberto
 *
 * @safe.des Per testare la classe nella sua interezza e' stato testato ciascun metodo fornendo in input parametri validi
 * e non validi in modo da testare il piu' ampio numero di casi possibili in cui la collezione si puo' trovare.<p>
 * <b>ATTENZIONE!!</b>
 * Qui verranno testati una volta per tutte <b>tutti</b> i metodi definiti in {@code AbstractCollection}. Non ha senso
 * effettuare test anche in tutte le altri classi che la estendono in quanto il corretto funzionamento dei metodi definiti
 * in essa dipende solo dal corretto funzionamento degli iteratori e dei metodi {@code remove} e {@code contains}. Tali funzionalita'
 * verranno invece testate esaustivamente:
 *    <ul>
 *        <li>in un'altra suite per quanto riguarda {@code ValueCollection e KeySet}</li>
 *        <li>in questa suite per quanto riguarda {@code EntrySet}).</li>
 *    </ul><p>
 * La documentazione di ciascun test case e' stata eseguita seguendo la colonna "homework" fornita nel file della consegna,
 * inoltre i metodi hanno tutti un nome che e' il piu' evocativo possibile.
 * @safe.pre Si assicura che i metodi qui di seguito indicati siano gia' stati testati esaustivamente dal tester della classe
 * MapAdapter:
 * <ul>
 *     <li>size</li>
 *     <li>isEmpty</li>
 * </ul>
 * <p>Dal momento che in {@code AbstractCollection} sono semplicemente dei metodi wrapper che utilizzano funzionalita'
 * della classe {@code MapAdapter}, tali metodi non verranno dunque testati in questa suite.
 * @safe.post Si sono ottenuti i risultati dell'esecuzione di tutti i test in questa suite.
 * @safe.records Consultare il file <a href="..\..\Test suite execution records\Test Results - EntrySetAbstractCollectionTester.html">
 *    "Test Results - EntrySetAbstractCollectionTester.html"</a> nella cartella "Test suite execution records"
 * @safe.exec I test sono stati eseguiti utilizzando JUnit v4.13 e hamcrest v1.3.
 * Per poter lanciare i test e' necessario inserire i file .jar di questi framework all'interno del CLASSPATH settando tale
 * variabile di ambiente.
 *
 * @note
 * Non si controlla che l'entrySet possa contenere degli elementi nulli (o chiavi/valori non validi) in quanto l'unico modo per ottenere
 * un'entry e' attraverso l'entrySet il quale, facendo riferimento alla classe MapAdapter, non puo' contenere ne' elementi ne' chiavi nulle.
 */
public class EntrySetAbstractCollectionTester
{
    private HSet entrySet;
    private MapAdapter map;

    /**
     * Crea un oggetto MapAdapter contenente le seguenti coppie chiave valore:
     * <pre>
     * | 0 |  "zero" |
     * | 1 |  "uno"  |
     * | 2 |  "due"  |
     * </pre>
     * Crea poi l'entrySet contenente tali entries.
     */
    @Before
    public void initialize()
    {
        map = new MapAdapter();
        map.put(0,"zero");
        map.put(1,"uno");
        map.put(2,"due");
        entrySet = map.entrySet();
    }

    //TEST ABSTRACTCOLLECTION METHODS

    /**
     * Test del metodo <code>public boolean containsAll(HCollection c)</code>: Testo che, una volta inizializzato il set con delle entries al suo interno,
     * le entries precedentemente inserite siano presenti all'interno del set utilizzando il metodo containsAll().
     * @homework.des Accertamento che determinati oggetti siano presenti all'interno del set una volta inseriti.
     * @homework.pre Set inizializzato dal metodo initialize(). La collection contiene alcune entries dell'entrySet
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Si crea un entrySet con il metodo {@code getSomeEntriesInMap()} che contiene alcune entries che sono all'interno
     * dell'entrySet. Gli elementi erano stati precedentemente inseriti dal metodo initialize(), quindi sono sicuramente presenti.
     * Si invoca quindi il metodo <code>containsAll</code> passando come parametro la collezione appena creata.
     * @homework.exp <code>containsAll(coll)</code> ritorna <code>true</code>
     */
    @Test
    public void testContainsAllSucceeds()
    {
        HCollection coll = getSetSomeEntriesInMap();

        boolean result = entrySet.containsAll(coll);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean containsAll(HCollection c)</code>: Testo che, una volta inizializzato il set con degli elementi al suo interno,
     * elementi che NON siano stati precedentemente inseriti NON siano presenti all'interno del set.
     * Il metodo <code>containsAll()</code> non deve trovare l'elemento.
     * @homework.des Accertamento che determinati oggetti non siano presenti all'interno del set se non inseriti.
     * Si prende il caso generale in cui la collezione contiene sia elementi presenti nel set che non.
     * @homework.pre Set inizializzatp dal metodo initialize(). La collezione in input contiene elementi misti tra presenti nel set e non presenti.
     * @homework.post il set e' rimasto invariato.
     * @homework.testDescr Si crea una collezione coll di tipo HCollection richiamando il metodo {@code getSetMixedEntries} che ritorna alcune
     * entries all'interno del set e altre non presenti (in particolare, 2 presenti nel set e 4 non presenti).
     * Si invoca quindi il metodo <code>containsAll</code> passando come parametro la collezione appena creata.
     * @homework.exp <code>containsAll(coll)</code> ritorna <code>false</code>.
     */
    @Test
    public void testContainsAllFails()
    {
        HCollection coll = getSetMixedEntries();

        boolean result = entrySet.containsAll(coll);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public Object containsAll(HCollection coll)</code>: testa che il metodo <code>containsAll</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la collezione passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>containAll</code> qualora la collezione passata non sia valida.
     * @homework.pre Set inizializzato con il metodo initialize(). La HCollection fa riferimento a null
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Si prova a controllare che il set contenga una HCollection coll che pero' non fa riferimento ad alcuna collezione.
     * L'operazione deve fallire perche' <code>null</code> non e' una collezione valida.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testContainsAllExceptionNullCollection()
    {
        HCollection coll = null;

        assertThrows(NullPointerException.class, () -> entrySet.containsAll(coll));
    }

    /**
     * Test del metodo <code>public boolean containsAll(HCollection c)</code>: Testo il caso limite in cui, nel caso in cui il set contenga degli
     * elementi, la collezione vuota (cioe' con nessun elemento) sia contenuta all'interno del set.
     * @homework.des Accertamento che il metodo funzioni anche nel caso limite di collezione vuota e non lanci eccezioni.
     * Si ricorda che il metodo containsAll ritorna <code>false</code> nel caso in cui almeno 1 elemento della collezione non e' presente nel set,
     * siccome la collezione non ha elementi, il metodo deve ritornare <code>true</code>.
     * @homework.pre Set inizializzato dal metodo initialize(). Collezione in input svuotata.
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Si crea una collezione coll di tipo HCollection che viene svuotata subito dopo l'inizializzazione per essere
     * sicuri che non contenga elementi.
     * Si invoca quindi il metodo <code>containsAll</code> passando come parametro la collezione appena creata.
     * @homework.exp <code>containsAll(coll)</code> ritorna <code>true</code>
     */
    @Test
    public void testContainsAllEmptyCollection()
    {
        HCollection coll = new ListAdapter();
        coll.clear();

        boolean result = entrySet.containsAll(coll);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean containsAll(HCollection c)</code>: Testo il caso limite in cui, nel caso in cui il set sia vuoto,
     * una collezione con degli elementi validi al suo interno NON sia contenuta nel set.
     * @homework.des Accertamento che il metodo funzioni anche nel caso limite di set vuoto e non lanci eccezioni.
     * Si ricorda che il metodo containsAll ritorna <code>false</code> nel caso in cui almeno 1 elemento della collezione non e' presente nel set,
     * siccome il set non ha elementi, il metodo deve ritornare <code>false</code>.
     * @homework.pre Set svuotato contenente zero elementi.
     * @homework.post il set e' rimasto invariato.
     * @homework.testDescr Creo una collezione coll di tipo HCollection che viene riempita usando il metodo createColleciton(). Svuoto poi
     * il set in modo che questo non contenga elementi.
     * Si invoca quindi il metodo <code>containsAll</code> passando come parametro la collezione appena creata. il set non contiene
     * elementi per cui non puo' nemmeno contenere la collezione passata.
     * @homework.exp <code>containsAll(coll)</code> ritorna <code>false</code>
     */
    @Test
    public void testContainsAllEmptySet()
    {
        HCollection coll = getSetSomeEntriesInMap();
        entrySet.clear();

        boolean result = entrySet.containsAll(coll);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public boolean containsAll(HCollection c)</code>: Testo il caso limite in cui sia la collezione che il set siano
     * vuoti.
     * @homework.des Accertamento che il metodo funzioni anche nel caso limite di collezione e set vuoto e non lanci eccezioni.
     * Siccome sia il set che la collezione non contengono elementi, non ci sono elementi della collezione che non son presenti nel set
     * originale, per cui <code>containsAll</code> deve ritornare {@code false}.
     * @homework.pre Set inizializzato dal metodo initialize(). Entryset contiene zero elementi, HCollection contiene zero elementi.
     * @homework.post Il set e' rimasta invariato.
     * @homework.testDescr Creo una collezione coll di tipo HCollection che viene subito svuotata. Svuoto poi il set in modo che questo
     * non contenga elementi.
     * Si invoca quindi il metodo <code>containsAll</code> passando come parametro la collezione appena creata. Il set non contiene
     * elementi e nemmeno la collezione per cui il set contiene la collezione.
     * @homework.exp <code>containsAll(coll)</code> ritorna <code>true</code>
     */
    @Test
    public void testContainsAllBothEmpty()
    {
        HCollection coll = new ListAdapter();
        coll.clear();
        entrySet.clear();

        boolean result = entrySet.containsAll(coll);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean removeAll(HCollection coll)</code>: testo che il metodo <code>removeAll</code> rimuova
     * tutti gli elementi presenti all'interno della collezione dal set.
     * @homework.des Testo che la rimozione degli elementi da una collezione rimuova correttamente tutti gli elementi all'interno dell'entrySet.
     * @homework.pre Set inizializzato dal metodo initialize(). Collection coll correttamente inizializzata all'entrySet della mappa da cui
     * andare a rimuovere gli elementi.
     * @homework.post il set e' vuoto.
     * @homework.testDescr Si crea una collezione con gli stessi elementi inseriti all'interno del set dal metodo initialize(). Per farlo
     * si richiama nuovamento il metodo entrySet di mapAdapter.
     * Siccome la collezione contiene tutti gli elementi del set, removeAll deve lasciare il set vuoto, si testa
     * richiamando il metodo <code>size()</code>
     * @homework.exp <code>size()</code> deve ritornare 0.
     */
    @Test
    public void testRemoveAllRemovesAllElements()
    {
        HCollection coll = map.entrySet();

        entrySet.removeAll(coll);

        assertEquals(0, entrySet.size());
    }

    /**
     * Test del metodo <code>public boolean removeAll(HCollection c)</code>: testa che il metodo <code>removeAll</code>
     * ritorni <code>true</code> nel caso in cui la rimozione abbia avuto successo e abbia modificato il set.
     * @homework.des Accertamento del corretto valore di ritorno
     * Dal momento che si richiama l'operazione <code>remove</code> per la rimozione del singolo elemento, si assicura che la dimensione del set
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre il set e' stata inizializzato da intialize(). La collezione in input contiene alcune entries dell'entryset
     * @homework.post Gli elementi sono stati aggiunti alil set. La dimensione del set e' diminuita di 2.
     * @homework.testDescr Si crea una collezione e si inseriscono al suo interno degli elementi presenti all'interno del set
     * (1,2). Si rimuovono poi gli elementi dalil set.
     * Siccome la collezione non e' vuota, deve aver rimosso gli elementi dalil set, questa deve risultare modificata.
     * @homework.exp <code>list.removeAll(coll)</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRemoveAllReturnsTrueIfSucceeds()
    {
        HCollection coll = getSetSomeEntriesInMap();

        boolean result = entrySet.removeAll(coll);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean removeAll(HCollection c)</code>: testa che il metodo <code>removeAll</code>
     * ritorni <code>false</code> nel caso in cui la collezione che si vuole rimuovere dalil set non abbia nessun elemento elemento in
     * comune con il set stessa (quindi il set non viene modificata).
     * @homework.des Accertamento del corretto valore di ritorno
     * Dal momento che si richiama l'operazione <code>remove</code> per la rimozione del singolo elemento, si assicura che la dimensione del set
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre il set e' stata inizializzata da intialize(). La collezione in input contiene alcune entries NON presenti all'interno della mappa.
     * @homework.post il set e' rimasta invariato.
     * @homework.testDescr Si crea una collezione contenete alcune entries non presenti all'interno della mappa richiamando il metodo
     * {@code getSetEntriesNotInMap}. Si rimuovono poi gli elementi dal set.
     * Siccome la collezione non contiene elementi in comune con il set, non sono stati rimossi elementi dal set e quindi
     * il set non e' stata modificato.
     * @homework.exp <code>removeAll(coll)</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRemoveAllReturnsFalseIfFails()
    {
        HCollection coll = getSetEntriesNotInMap();

        boolean result = entrySet.removeAll(coll);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public boolean removeAll(HCollection coll)</code>: testo che il metodo <code>removeAll</code> rimuova
     * tutti gli elementi presenti all'interno della collezione dal set. Verranno rimossi solo gli elementi che sono presenti sia
     * nella collezione che nel set.
     * @homework.des Testo che la rimozione degli elementi da una collezione rimuova correttamente tutti gli elementi anche nel caso
     * in cui la collezione contenga elementi non presenti all'interno del set.
     * @homework.pre Set inizializzato dal metodo initialize(). Collection coll correttamente inizializzata con entries miste.
     * @homework.post il set non contiene piu le entries all'interno della collection fornita in input.
     * @homework.testDescr Si crea una collezione contenete entries miste (ovvero un po' presenti nel set e un po' no) richiamando il
     * metodo {@code getSetMixedEntries}. Si rimuovono poi gli elementi dal set.
     * Siccome la collezione contiene solo 2 elementi in comune con il set, removeAll deve rimuovere le entries (0,"zero) e (2,"due").
     * Si testa che dopo la rimozione gli elementi rimossi non siano piu' presenti nel set usando il metodo {@code contains}.
     *
     * (NOTA: Non si usa containsAll perche' ritorna false anche nel caso in cui solo 1 elemento dei due non sia stato rimosso, io voglio testare
     * che entrambi non siano presenti).
     * @homework.exp <code>contains</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRemoveAllMixedElements()
    {
        HCollection coll = getSetMixedEntries();

        entrySet.removeAll(coll);

        HIterator it = getSetSomeEntriesInMap().iterator();
        while(it.hasNext())
            assertFalse(entrySet.contains(it.next()));
    }

    /**
     * Test del metodo <code>public Object removeAll(HCollection coll)</code>: testa che il metodo <code>removeAll</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la collezione passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>containAll</code> qualora la collezione passata non sia valida.
     * @homework.pre Set inizializzato con il metodo initialize(). HCollection che fa riferimento a null
     * @homework.post il set e' rimasto invariato.
     * @homework.testDescr Si prova a rimuovere una collezione HCollection coll che pero' non fa riferimento ad alcuna collezione.
     * L'operazione deve fallire perche' <code>null</code> non e' una collezione valida.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveAllExceptionIfCollectionIsNull()
    {
        HCollection coll = null;

        assertThrows(NullPointerException.class, () -> entrySet.removeAll(coll));
    }

    /**
     * Test del metodo <code>public boolean retainAll(HCollection c)</code>: testa che il metodo <code>retainAll</code>
     * ritorni <code>true</code> nel caso in cui la rimozione abbia avuto successo e abbia modificato il set.
     * @homework.des Accertamento del corretto valore di ritorno
     * @homework.pre il set e' stato inizializzato da intialize(). Collezione correttamente inizializzata con entries miste.
     * @homework.post Gli elementi sono stati rimossi dal set. La dimensione del set e' 2 (numero di elementi in comune tra la collezione e
     * il set).
     * @homework.testDescr Si crea una collezione contenete entries miste (ovvero un po' presenti nel set e un po' no) richiamando il
     * metodo {@code getSetMixedEntries}. Le entries in comune sono (0,"zero") e (2,"due") ==> saranno mantenute.
     * Si rimuovono poi gli elementi dal set mantenendo solo le due entries sopra citate.
     * Siccome la collezione non e' vuota, deve aver rimosso gli elementi dal set, questa deve risultare modificata.
     * @homework.exp <code>list.retainAll(coll)</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testRetainAllReturnsTrueIfChanged()
    {
        HCollection coll = getSetMixedEntries();

        boolean result = entrySet.retainAll(coll);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean retainAll(HCollection c)</code>: testa che il metodo <code>retainAll</code>
     * lasci 2 elementi all'interno del set quando tra il set e la collezione sono presenti 2 elementi in comune.
     * @homework.des Accertamento del corretto funzionamento del metodo {@code retainAll}.
     * @homework.pre il set e' stato inizializzato da intialize(). Collezione inizializzata con solo entries che sono presenti
     * all'interno della mappa.
     * @homework.post Son stati rimossi tutti gli elementi ad eccezione delle entries in comune (ovvero (0,"zero") e (2,"due")).
     * @homework.testDescr Si crea una collezione contenete soltanto entries presenti nella mappa usando il metodo {@code getSetSomeEntriesInMap}.
     * Si rimuovono poi gli elementi dal set invocando retainAll.
     * Siccome la collezione contiene 2 elementi in comune con la collezione passata, la dimensione finale deve essere pari a 2.
     * @homework.exp {@code entrySet.size} ritorna la stessa dimensione della collezione fornita in input.
     */
    @Test
    public void testRetainAllSucceeds()
    {
        HCollection coll = getSetSomeEntriesInMap();

        entrySet.retainAll(coll);

        assertEquals(coll.size(), entrySet.size());
    }


    /**
     * Test del metodo <code>public boolean retainAll(HCollection c)</code>: testa che il metodo <code>retainAll</code>
     * non modifichi il set nel caso in cui tutti gli elementi siano in comune tra set e collezione.
     * @homework.des Accertamento del corretto funzionamento del metodo.
     * @homework.pre il set e' stato inizializzato da intialize(). La collezione contiene le stesse entries del set.
     * @homework.post il set e' rimasto invariato.
     * @homework.testDescr Si crea una collezione inserendo tutti gli elementi inseriti all'interno del set da parte
     * del metodo initialize(). Si mantengono nel set solo gli elementi presenti all'interno della collezione.
     * Siccome la collezione coincide con il set, non sono stati rimossi elementi dal set e quindi
     * il set non e' stato modificato. Si controlla cio' verificando che la dimensione del set (ottenuta con size()) rimanga invariata.
     * @homework.exp La dimensione del set e' 3. (pari a quella prima della chiamata al metodo)
     */
    @Test
    public void testRetainAllDoesNotModifySet()
    {
        HCollection coll = map.entrySet();

        entrySet.retainAll(coll);

        assertEquals(3, entrySet.size());
    }

    /**
     * Test del metodo <code>public boolean retainAll(HCollection c)</code>: testa che il metodo <code>retainAll</code>
     * ritorni <code>false</code> nel caso in cui la collezione che si vuole mantenere nel set abbia tutti gli elementi in
     * comune con il set originale (quindi il set non viene modificato).
     * @homework.des Accertamento del corretto valore di ritorno
     * Dal momento che si richiama l'operazione <code>remove</code> per l'inserimento del singolo elemento, si assicura che la dimensione del set
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre il set e' stato inizializzato da intialize(). La collezione contiene le stesse entries del set.
     * @homework.post il set e' rimasto invariato.
     * @homework.testDescr Si crea una collezione inserendo tutti gli elementi inseriti all'interno del set da parte
     * del metodo initialize(). Si mantengono nel set solo gli elementi presenti all'interno della collezione.
     * Siccome la collezione coincide con il set, non sono stati rimossi elementi dal set e quindi
     * il set non e' stato modificato.
     * @homework.exp <code>retainAll(coll)</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRetainAllReturnsFalseIfSetNotChanged()
    {
        HCollection coll = map.entrySet();

        boolean result = entrySet.retainAll(coll);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public Object retainAll(HCollection coll)</code>: testa che il metodo <code>retainAll</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la collezione passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>containAll</code> qualora la collezione passata non sia valida.
     * @homework.pre Set inizializzato con il metodo initialize(). Collection che fa riferimento a null.
     * @homework.post il set e' rimasto invariato.
     * @homework.testDescr Si prova a mantenere all'interno del set una collezione HCollection coll che pero' non fa riferimento ad alcuna collezione.
     * L'operazione deve fallire perche' <code>null</code> non e' una collezione valida.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRetainAllExceptionIfCollectionIsNull()
    {
        HCollection coll = null;

        assertThrows(NullPointerException.class, () -> entrySet.retainAll(coll));
    }

    /**
     * Test del metodo <code>public Object[] toArray()</code>: Testo che, una volta svuotato il set, il metodo toArray() ritorni un array
     * con nessun elemento al suo interno, ovvero un array vuoto di dimensione 0. Il set e l'array devono entrambi non contenere nulla.
     * @homework.des Accertamento che il metodo toArray() istanzi un array in memoria e che lo lasci vuoto correttamente.
     * @homework.pre il set e' stata svuotata dal metodo clear()
     * @homework.post il set e' rimasto invariato. L'array ritornato ha lunghezza 0.
     * @homework.testDescr Si crea un array expected e viene lasciato vuoto. Il set e' poi svuotato.
     * Si crea poi l'array actual invocando il metodo toArray() sull'entrySet. expected e actual devono essere uguali come sopra descritto.
     * @homework.exp L'array ritornato deve essere uguale ad un array vuoto.
     *
     */
    @Test
    public void testToArrayEmptySet()
    {
        Object[] expected = new Object[0];
        entrySet.clear();

        Object[] actual = entrySet.toArray();

        assertArrayEquals(expected,actual);
    }


    /**
     * Test del metodo <code>public Object[] toArray()</code>: Testo che anche se l'array ritornato viene modificato, le modifiche non hanno nessuna ripercussione sull'array originale.
     * @homework.des Accertamento che il metodo toArray() ritorni un array che non e' in alcun modo vincolato con gli elementi
     * inseriti all'interno del set. Una modifica ai suoi elementi non deve generare nessuna modifica alil set.
     * @homework.pre Set inizializzato dal metodo initialize(). Entry e non contenuta nel set originale.
     * @homework.post il set e' rimasto invariato. L'array ritornato ha gli stessi elementi del set ad eccezione dell'elemento
     * nella posizione 0 che e' diventato la HEntry e.
     * @homework.testDescr Si ottiene l'array corrispondente alil set chiamando il metodo toArray().
     * Si modifica poi l'elemento in posizione 0 dell'array inserendo una HEntry e non presente nel set che quindi sara' contenuta solo
     * nell'array ma non nel set.
     * Si testa poi con il metodo contains che la suddetta entry non sia presente all'interno del set.
     * @homework.exp {@code contains} ritorna {@code false}.
     */
    @Test
    public void testToArraySafeChanges()
    {
        Object[] result = entrySet.toArray();
        HCollection coll = getSetEntriesNeitherKeyNorValueInMap();
        HMap.HEntry e = (HMap.HEntry) coll.iterator().next();

        result[0] = e;

        assertFalse(entrySet.contains(e));
    }


    /**
     * Testa il metodo <code>public Object[] toArray(Object[])</code>: fornendo un array con abbastanza posti, ritorni l'array precedentemente fornito
     * senza creare una nuova istanza. Il controllo viene effettuato controllando gli indirizzi di memoria degli array.
     * @homework.des Accertamento che il metodo toArray() non crei un nuovo array ma ritorni quello fornito come parametro con gli elementi
     * inseriti al suo interno.
     * @homework.pre set inizializzato con il metodo initialize()
     * @homework.post il set e' rimasto invariato. Gli elementi del set sono stati inseriti nell'array fornito come parametro.
     * @homework.testDescr Si crea un nuovo array arr con la dimensione esattamente pari agli elementi presenti all'interno del set.
     * Si ottiene poi l'array returned chiamando il metodo <code>toArray(Object[])</code> dell'entrySet.
     * L'indirizzo di arr e returned deve essere lo stesso. L'uguaglianza e' verificata grazie all'operatore == che su tipi riferimento
     * funziona confrontando gli indirizzi. (nel caso di JUnit tale operazione si ottiene usando il metodo <code>assertSame()</code>)
     * @homework.exp L'uguaglianza tra indirizzi ritorna <code>true</code>
     */
    @Test
    public void testToArrayGivenArraySameInstance()
    {
        Object[] arr = new Object[entrySet.size()];

        Object[] returned = entrySet.toArray(arr);

        assertSame(arr,returned);
    }

    /**
     * Testa il metodo <code>public Object[] toArray(Object[])</code>: fornendo un array che non abbia abbastanza posti, non ritorni l'array precedentemente fornito
     * bensi' una nuova istanza di <code>Object[]</code>. Il controllo viene effettuato controllando gli indirizzi di memoria degli array.
     * @homework.des Accertamento che il metodo toArray() crei un nuovo array e non ritorni quello fornito come parametro.
     * @homework.pre set inizializzato con il metodo initialize()
     * @homework.post il set e' rimasto invariato. Gli elementi del set sono stati inseriti in un nuovo array.
     * @homework.testDescr Si crea un nuovo array arr con la dimensione esattamente pari agli elementi presenti all'interno del set.
     * Si ottiene poi l'array returned chiamando il metodo <code>toArray(Object[])</code>.
     * L'indirizzo di arr e returned deve essere diverso. La disuguaglianza e' verificata grazie all'operatore == che su tipi riferimento
     * funziona confrontando gli indirizzi. (nel caso di JUnit tale operazione si ottiene usando il metodo <code>assertSame()</code>)
     * @homework.exp L'uguaglianza tra indirizzi ritorna <code>false</code>
     */
    @Test
    public void testToArrayGivenArrayNewInstance()
    {
        Object[] arr = new Object[entrySet.size() - 1];

        Object[] returned = entrySet.toArray(arr);

        assertNotSame(arr,returned);
    }


    /**
     * Testa il metodo <code>public Object[] toArray(Object[])</code>: fornendo un array con abbastanza posti, ritorni l'array precedentemente fornito
     * aggiungendo <code>null</code> nei posti inutilizzati. Controlla inoltre che gli elementi siano esattamente quelli del set.
     * @homework.des Accertamento che il metodo <code>toArray(Object[])</code> inserisca <code>null</code> e che inoltre tutti gli elementi siano corretti.
     * @homework.pre set inizializzato con il metodo initialize()
     * @homework.post il set e' rimasto invariato. Gli elementi del set sono stati inseriti nell'array fornito come parametro. Null
     * e' stato inserito nei posti aggiuntivi del set, se presenti.
     * @homework.testDescr Si crea un array che abbia dimensione del set piu' un numero di posti aggiuntivo pari a <code>expectedNullElements</code>.
     * Si invoca poi il metodo <code>toArray(Object[])</code> passando come parametro tale array.
     * A partire dalla posizione pari a list.size() si contano nella variabile <code>actualNullElements</code> il numero di elementi
     * posti effettivamente a null nell'array. Devono essere tutti null, cioe' <code>actualNullElements == expectedNullElements</code>.
     * @homework.exp L'uguaglianza tra i due valori ritorna <code>true</code>
     */
    @Test
    public void testToArrayGivenArrayNullAfterElements()
    {
        int expectedNullElements = 5;
        Object[] arr = new Object[entrySet.size() + expectedNullElements];


        arr = entrySet.toArray(arr);


        int actualNullElements = 0;
        for(int i = entrySet.size(); i < arr.length; i++)
            if (arr[i] == null)
                actualNullElements++;

        assertEquals(expectedNullElements,actualNullElements);
    }

    /**
     * Testa il metodo <code>public Object[] toArray(Object[])</code>: fornendo un array con abbastanza posti, si ottiene l'array precedentemente fornito
     * con che gli elementi che devono essere esattamente quelli del set e nello stesso ordine.
     * @homework.des Accertamento che il metodo <code>toArray(Object[])</code> inserisca tutti gli elementi correttamente nel caso in cui
     * l'array di ritorno e' lo stesso fornito in precedenza.
     * L'uguaglianza tra gli elementi del set e dell'array viene accertata mediante il metodo arrayEqualsAnyOrder: non e' possibile usare
     * assertArrayEquals in quanto arrayEquals prevede l'uguaglianza non solo degli elementi, ma anche delle posizioni relative agli elementi.
     * Tuttavia, per l'array ritornato da entrySet non e' data nessuna garanzia in merito all'ordinamento degli elementi, non e' possibile
     * dunque fare supposizioni in merito.
     * Il confronto viene eseguito usando il metodo {@code arrayEqualsAnyOrder} che controlla se gli elementi dei due array sono uguali
     * tralasciando la loro posizione nell'array.
     * Si usano due array separati per keys e values in quanto MyEntry e' una classe privata di MapAdapter e non usabile qui.
     * @homework.pre set inizializzato con il metodo initialize()
     * @homework.post il set e' rimasto invariato. Gli elementi del set sono stati inseriti nell'array fornito come parametro. Null
     * e' stato inserito nei posti aggiuntivi del set, se presenti.
     * @homework.testDescr Si invoca il metodo <code>toArray()</code> per ottenere l'array associato alil set (si e' gia' testato
     * in un'altra istanza che tale metodo e' corretto).
     * Si crea un array che abbia dimensione del set piu' 2 posti (per avere un caso generale).
     * Si invoca poi il metodo <code>toArray(Object[])</code> passando come parametro tale array.
     * Si controlla poi che gli elementi siano gli stessi anche in posizioni diverse usando il metodo sopra citato.
     * @homework.exp <code>IsOk == true</code> alla fine del test.
     */
    @Test
    public void testToArrayGivenArraySameInstanceCorrectness()
    {
        Object[] expectedKeys = new Object[]{0,1,2};
        Object[] expectedValues = new Object[]{"zero","uno","due"};
        Object[] arr = new Object[entrySet.size() + 2];

        Object[] actual = entrySet.toArray(arr);

        assertTrue(arrayEqualsAnyOrder(expectedKeys,expectedValues,actual,2));
    }

    /**
     * Testa il metodo <code>public Object[] toArray(Object[])</code>: fornendo un array che non abbia abbastanza posti, si ottiene un nuovo
     * array che gli elementi che devono essere esattamente quelli del set e nello stesso ordine.
     * @homework.des Accertamento che il metodo <code>toArray(Object[])</code> inserisca tutti gli elementi correttamente nel caso in cui
     * l'array di ritorno e' diverso da quello fornito come parametro.
     * L'uguaglianza tra gli elementi del set e dell'array viene accertata mediante il metodo arrayEqualsAnyOrder: non e' possibile usare
     * assertArrayEquals in quanto arrayEquals prevede l'uguaglianza non solo degli elementi, ma anche delle posizioni relative agli elementi.
     * Tuttavia, per l'array ritornato da entrySet non e' data nessuna garanzia in merito all'ordinamento degli elementi, non e' possibile
     * dunque fare supposizioni in merito.
     * Il confronto viene eseguito usando il metodo {@code arrayEqualsAnyOrder} che controlla se gli elementi dei due array sono uguali
     * tralasciando la loro posizione nell'array.
     * Si usano due array separati per keys e values in quanto MyEntry e' una classe privata di MapAdapter e non usabile qui.
     * @homework.pre set inizializzato con il metodo initialize(). I due array contenenti chiavi e valori inizializzati correttamente.
     * @homework.post il set e' rimasto invariato. Gli elementi del set sono stati inseriti nell'array fornito come parametro.
     * @homework.testDescr Si invoca il metodo <code>toArray()</code> per ottenere l'array associato alil set (si e' gia' testato
     * in un'altra istanza che tale metodo e' corretto).
     * Si crea un array che abbia dimensione del set meno 2 posti (per avere un caso generale).
     * Si invoca poi il metodo <code>toArray(Object[])</code> passando come parametro tale array.
     * Si controlla poi che gli elementi siano gli stessi anche in posizioni diverse usando il metodo sopra citato.
     * @homework.exp L'uguaglianza tra array deve ritornare <code>true</code>
     */
    @Test
    public void testToArrayGivenArrayNewInstanceCorrectness()
    {
        Object[] expectedKeys = new Object[]{0,1,2};
        Object[] expectedValues = new Object[]{"zero","uno","due"};
        Object[] arr = new Object[entrySet.size() - 2];

        Object[] actual = entrySet.toArray(arr);

        assertTrue(arrayEqualsAnyOrder(expectedKeys,expectedValues,actual,0));
    }

    /**
     * Test del metodo <code>public Object add(Object o)</code>: testa che il metodo <code>add</code> lanci eccezione
     * <code>UnsupportedOperationException</code> nel caso in cui si provi ad invocarlo. Tale funzionalita'
     * non e' infatti supportata dai Set ritornati dalla mappa
     * @homework.des Accertamento del corretto funzionamento di <code>add</code>.
     * @homework.pre Set inizializzato con il metodo initialize()
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Si prova ad invocare il metodo add. L'operazione non puo' andare a buon fine perche'
     * add non e' definito nel Set ritornato.
     * @homework.exp L'eccezione <code>UnsupportedOperationException</code> deve essere lanciata.
     * Si controlla con il metodo <code>assertThrows()</code> fornito dal framework JUnit.
     */
    @Test
    public void testAddExceptionNotSupported()
    {
        Object val = null;

        assertThrows(UnsupportedOperationException.class, () -> entrySet.add(val));
    }


    //----------------------------------TEST ENTRYSET------------------------------------------------

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la rimozione di una entry
     * che era presente all'interno dell'entrySet venga ritornato <code>true</code>.
     * @homework.des Accertamento del funzionamento di {@code remove} quando la entry passata si trova nell'entrySet
     * @homework.pre entrySet inizializzato dal metodo initialize()
     * @homework.post E' stato rimosso una entry dal set, la dimensione e' diminuita di 1. L'entry e' quella passata come parametro.
     * @homework.testDescr Si crea un entrySet con il metodo {@code getSomeEntriesInMap()} che contiene alcune entries che sono
     * all'interno dell'entrySet. Si rimuove una di queste entry e si testa se il metodo <code>remove(Object)</code> ha ritornato <code>true</code>.
     * Siccome l'entry e' presente nella mappa e la mappa non e' stata modificata, la rimozione deve avere successo.
     * @homework.exp <code>remove</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testRemoveReturnsTrueIfEntryWasInSet()
    {
        HCollection coll = getSetSomeEntriesInMap();
        HMap.HEntry e = (HMap.HEntry) coll.iterator().next();

        boolean result = entrySet.remove(e);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la rimozione di una entry che non era presente
     * all'interno del set venga ritornato <code>false</code>.
     * @homework.des Accertamento del funzionamento di {@code remove} quando la entry passata non si trova nell'entrySet
     * In particolare, in questo caso si accerta il funzionamento corretto quando la chiave e' presente in una delle entry ma il valore e'
     * diverso. (La entry complessivamente e' dunque diversa).
     * @homework.pre entrySet inizializzato dal metodo initialize()
     * @homework.post Il set e' rimasto invariato
     * @homework.testDescr Si ottiene un set contenente entry che hanno la stessa chiave degli elementi nell'entryset ma valore diverso.
     * Si prende una di queste entry e la si prova a rimuovere dalla mappa. Siccome tale entry non era stata precedentemente inserita nel set, questa
     * non era presente e quindi {@code remove} deve ritornare {@code false}.
     *
     * Si testa il percorso di quando la chiave ha un valore associato nella mappa ma il valore inserito e' diverso per cui l'equals di entries fallisce.
     * @homework.exp <code>remove</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRemoveReturnsFalseIfKeyIsInSetButValueIsNot()
    {
        HCollection coll = getSetEntriesKeyInMapButDifferentValue();
        HMap.HEntry e = (HMap.HEntry) coll.iterator().next();

        boolean result = entrySet.remove(e);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la rimozione di un elemento che non era presente
     * all'interno del set venga ritornato <code>false</code>.
     * @homework.des Accertamento del funzionamento di {@code remove} quando la entry passata non si trova nell'entrySet
     * In particolare, in questo caso si accerta il funzionamento corretto quando sia il valore che la chiave non sono presenti all'interno del set.
     * @homework.pre Set inizializzato dal metodo initialize()
     * @homework.post Il set e' rimasto invariato
     * @homework.testDescr Si ottiene un set contenente entry che hanno chiave e valore diverso da quello nell'entrySet.
     * Si prende una di queste entry e la si prova a rimuovere dalla mappa. Siccome tale entry non era stata precedentemente inserita nel set, questa
     * non era presente e quindi {@code remove} deve ritornare {@code false}.
     *
     * Si testa il percorso di quando la chiave non e' presente nella mappa e l'invocazione di get ritorna null.
     * @homework.exp <code>remove</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRemoveReturnsFalseIfBothKeyAndValueNotInSet()
    {
        HCollection coll = getSetEntriesNeitherKeyNorValueInMap();
        HMap.HEntry e = (HMap.HEntry) coll.iterator().next();

        boolean result = entrySet.remove(e);

        assertFalse(result);
    }

    /**
     * Test del metodo {@code public void boolean remove(Object o)}: Testo che l'invocazione di {@code remove} passando una entry all'interno del set,
     * rimuova effettivamente l'elemento dal set. Dopo la rimozione l'elemento non deve essere piu' presente.
     * @homework.des Accertamento del corretto funzionamento di {@code remove} con rimozione dell'elemento dal set a cui apparteneva.
     * @homework.pre entrySet creato dal metodo {@code initialize}
     * @homework.post L'entry passata al metodo remove e' stata rimossa dal set. La dimensione e' diminuita di 1.
     * @homework.testDescr Si crea un entrySet con il metodo {@code getSomeEntriesInMap()} che contiene alcune entries che sono
     * all'interno dell'entrySet. Si rimuove una di queste entry.
     * Siccome l'entry era presente nel set, dopo aver invocato {@code remove} tale entry non deve piu' essere presente.
     * Si chiama il metodo contains passando l'entry rimossa per verificare che l'entry non sia piu' presente
     * @homework.exp {@code contains} ritorna {@code false}
     */
    @Test
    public void testRemoveRemovesEntryFromSet()
    {
        HCollection coll = getSetSomeEntriesInMap();
        HMap.HEntry e = (HMap.HEntry) coll.iterator().next();

        entrySet.remove(e);

        assertFalse(entrySet.contains(e));
    }

    /**
     * Test del metodo <code>public Object remove(Object o)</code>: testa che il metodo <code>remove</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui il valore passato sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>remove</code> qualora il valore non sia valido.
     * @homework.pre Set inizializzato con il metodo initialize()
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Si prova a rimuovere il valore nullo. Essendo null un elemento non valido per il set,
     * deve essere sollevata eccezione: la rimozione non puo' andare a buon fine.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveExceptionIfEntryIsNull()
    {
        HMap.HEntry e = null;

        assertThrows(NullPointerException.class, () -> entrySet.remove(e));
    }

    /**
     * Test del metodo <code>public Object remove(Object o)</code>: testa che il metodo <code>remove</code> lanci eccezione
     * <code>ClassCastException</code> nel caso in cui il valore passato non sia una Entry.
     * @homework.des Accertamento del corretto funzionamento di <code>remove</code> qualora il valore fornito non sia valido.
     * @homework.pre Set inizializzato con il metodo initialize()
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Si prova a rimuovere un valore che non sia una entry.
     * Dal momento che il set contiene entries, non deve essere possibile rimuovere elementi che non siano entry, quindi il parametro non e' valido.
     * Essendo il parametro non valido bisogna lanciare eccezione: la rimozione non puo' andare a buon fine.
     * @homework.exp L'eccezione <code>ClassCastException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveThrowsExceptionIfNotEntry()
    {
        assertThrows(ClassCastException.class, () -> entrySet.contains(2));
    }

    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: testa che il metodo <code>contains</code> ritorni <code>true</code>
     * quando si cerca un'entry che e' presente all'interno del set.
     * @homework.des Accertamento del funzionamento di {@code contains} quando la entry passata si trova nell'entrySet
     * @homework.pre entrySet inizializzato dal metodo initialize().
     * @homework.post Il set e' rimasto invariato
     * @homework.testDescr Si crea un entrySet con il metodo {@code getSomeEntriesInMap()} che contiene alcune entries che sono
     * all'interno dell'entrySet. Si prende una di queste entry che viene passata al metodo {@code contains}.
     * L'entry deve essere presente all'interno del set.
     *
     * Si testa il percorso di quando la chiave ha un valore associato nella mappa e il valore inserito e' uguale per cui l'equals di entries
     * ha successo.
     * @homework.exp <code>contains</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testContainsTrueIfEntryIsInMap()
    {
        HCollection coll = getSetSomeEntriesInMap();
        HMap.HEntry e = (HMap.HEntry) coll.iterator().next();

        boolean result = entrySet.contains(e);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: testa che il metodo <code>contains</code> ritorni <code>false</code>
     * quando si cerca un'entry che non e' presente all'interno del set.
     * @homework.des Accertamento del funzionamento di {@code contains} quando la entry passata non si trova nell'entrySet
     * In particolare, in questo caso si accerta il funzionamento corretto quando la chiave e' presente in una delle entry ma il valore e'
     * diverso. (La entry complessivamente e' dunque diversa)
     * @homework.pre entrySet inizializzato dal metodo initialize()
     * @homework.post Il set e' rimasto invariato
     * @homework.testDescr Si ottiene un set contenente entry che hanno la stessa chiave degli elementi nell'entryset ma valore diverso.
     * Si prende una di queste entry e la si prova a rimuovere dalla mappa. Siccome tale entry non era stata precedentemente inserita nel set, questa
     * non era presente e quindi {@code contains} deve ritornare {@code false}.
     *
     * Si testa il percorso di quando la chiave ha un valore associato nella mappa ma il valore inserito e' diverso per cui l'equals di entries fallisce.
     * @homework.exp <code>contains</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testContainsFalseIfKeyIsInMapButValueIsNot()
    {
        HCollection coll = getSetEntriesKeyInMapButDifferentValue();
        HMap.HEntry e = (HMap.HEntry) coll.iterator().next();

        boolean result = entrySet.contains(e);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public boolean contains (Object o)</code>: testa che il metodo <code>contains</code> ritorni <code>false</code>
     * quando si cerca un'entry che non e' presente all'interno del set.
     * @homework.des Accertamento del funzionamento di {@code contains} quando la entry passata non si trova nell'entrySet
     * In particolare, in questo caso si accerta il funzionamento corretto quando sia il valore che la chiave non sono presenti all'interno del set.
     * @homework.pre Set inizializzato dal metodo initialize()
     * @homework.post Il set e' rimasto invariato
     * @homework.testDescr Si ottiene un set contenente entry che hanno chiave e valore diverso da quello nell'entrySet.
     * Si prende una di queste entry e si prova a vedere se e' presente all'interno del set con il metodo contains.
     * Siccome tale entry non era stata precedentemente inserita nel set, questa non puo' essere presente.
     *
     * Si testa il percorso di quando la chiave non e' presente nella mappa e l'invocazione di get ritorna null.
     * @homework.exp <code>contains</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testContainsReturnsFalseIfBothKeyAndValueNotInSet()
    {
        HCollection coll = getSetEntriesNeitherKeyNorValueInMap();
        HMap.HEntry e = (HMap.HEntry) coll.iterator().next();

        boolean result = entrySet.contains(e);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public Object contains(Object o)</code>: testa che il metodo <code>contains</code> lanci eccezione
     * <code>ClassCastException</code> nel caso in cui il valore passato non sia una Entry.
     * @homework.des Accertamento del corretto funzionamento di <code>contains</code> qualora il valore fornito non sia valido.
     * @homework.pre Set inizializzato con il metodo initialize()
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Si prova a vedere se un valore che non e' una entry e' presente all'interno del set.
     * Dal momento che il set contiene entries, non deve essere possibile ispezionare elementi che non siano entry, quindi il parametro non e' valido.
     * Essendo il parametro non valido bisogna lanciare eccezione: l'ispezione non puo' andare a buon fine.
     * @homework.exp L'eccezione <code>ClassCastException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testContainsExceptionIfNotEntry()
    {
        assertThrows(ClassCastException.class, () -> entrySet.contains(2));
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
        assertThrows(NullPointerException.class, () -> entrySet.contains(null));
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 entrySet con gli stessi elementi
     * siano uguali usando il metodo <code>equals</code>.
     * @homework.des Accertamento del funzionmento del metodo <code>equals</code> per cui quando si hanno 2 set uguali venga ritornato true.
     * @homework.pre Set creato dal metodo initialize(). EntrySet inizializzato con i valori ritornati dall'iteratore di entryset
     * @homework.post Nessuno dei 2 set e' stato modificato.
     * @homework.testDescr Si crea una nuova mappa con gli stessi elementi della mappa originale.
     * Per quanto dichiarato dalla documentazione, i due entryset ritornati dalle 2 mappe sono uguali. Il controllo viene fatto usando equals()
     * @homework.exp il metodo {@code equals} ritorna <code>true</code>
     */
    @Test
    public void testEqualsSameEntries()
    {
        MapAdapter map2 = new MapAdapter();
        HIterator it = entrySet.iterator();
        while(it.hasNext())
        {
            HMap.HEntry e = (HMap.HEntry) it.next();
            map2.put(e.getKey(),e.getValue());
        }

        boolean areEqual = entrySet.equals(map2.entrySet());

        assertTrue(areEqual);
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 entryset con un numero di elementi diversi non siano uguali.
     * @homework.des Accertamento del funzionmento del metodo <code>equals</code> per cui quando si hanno 2 entrySet diversi venga ritornato
     * {@code false}.
     * @homework.pre Set creato dal metodo initialize(). Secondo entrySet contenente una parte degli elementi della mappa originale.
     * @homework.post Nessuno dei due set e' stato modificato.
     * @homework.testDescr Si crea una nuova mappa con solo una parte degli elementi della mappa originale.
     * Per quanto dichiarato dalla documentazione, i due entryset ritornati dalle 2 mappe sono diversi. Il controllo viene fatto usando equals()
     * @homework.exp il metodo {@code equals} ritorna <code>false</code>
     */
    @Test
    public void testEqualsDifferentSize()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(0,"zero");
        map2.put(1,"uno");

        boolean areEqual = entrySet.equals(map2.entrySet());

        assertFalse(areEqual);
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 entryset con lo stesso numero di elementi ma con
     * almeno un'entry diversa siano diversi.
     * @homework.des Accertamento del funzionmento del metodo <code>equals</code> per cui quando si hanno 2 entrySet diversi venga ritornato
     * {@code false}. Si testa il caso in cui un valore e' diverso, ma le 3 casistiche (stessa chiave valore diverso, stesso valore chiave diversa,
     * sia chiave che valori diversi) sono uguali tra loro per come e' stato scritto il codice del metodo.
     * @homework.pre Set creato dal metodo initialize(). Secondo entrySet contenente una parte degli elementi della mappa originale.
     * @homework.post Nessuno dei due set e' stato modificato.
     * @homework.testDescr Si crea una nuova mappa con 2 entries uguali a quelle della mappa originale e una che differisce
     * per il valore.
     * Per quanto dichiarato dalla documentazione, i due entryset ritornati dalle 2 mappe sono diversi. Il controllo viene fatto usando equals()
     * @homework.exp il metodo {@code equals} ritorna <code>false</code>
     */
    @Test
    public void testEqualsDifferentEntries()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(2,"anyValue");
        map2.put(1,"uno");
        map2.put(0,"zero");

        boolean areEqual = entrySet.equals(map2.entrySet());

        assertFalse(areEqual);
    }

    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che se l'argomento passato e' <code>null</code> il metodo
     * ritorni false.
     * @homework.des Accertamento del funzionmento del metodo <code>equals</code> per cui quando il parametro passato e' nullo venga ritornato
     * false. Si verifica che non sia lanciata eccezione erroneamente.
     * @homework.pre Set creato dal metodo initialize(). Collezione inizializzata a null.
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Si crea una nuova collection e la si inizializza con il valore null.
     * Per quanto dichiarato dalla documentazione, i due set non sono uguali. Il controllo viene fatto usando <code>equals</code>
     * @homework.exp il metodo <code>equals</code> ritorna <code>false</code>
     */
    @Test
    public void testEqualsFailIsNull()
    {
        boolean areEqual = entrySet.equals(null);

        assertFalse(areEqual);
    }

    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che 2 entrySet uguali abbiano lo stesso hashCode
     * @homework.des due entryset uguali hanno lo stesso codice hash.
     * @homework.pre 2 entryset opportunamente creati uguali tra loro secondo i parametri definiti da equals (stesse coppie
     * chiave valori nelle entries).
     * @homework.post i set sono rimasti invariati
     * @homework.testDescr Creazione di un nuovo oggetto MapAdapter. Ad entrambe le mappe esistenti, aggiungo gli stessi elementi definiti dal
     * metodo initialize(). Dalle due mappe ottengo gli entrySet e ne calcolo l'hashCode
     * @homework.exp I due entrySet hanno lo stesso hashCode.
     */
    @Test
    public void testHashCodeSameSets()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(2,"due");
        map2.put(1,"uno");
        map2.put(0,"zero");

        int hash1 = map.entrySet().hashCode();
        int hash2 = map2.entrySet().hashCode();

        assertEquals(hash1,hash2);
    }

    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che 2 set diversi, anche parzialmente, abbiano due codici
     * hash diversi.
     * @homework.des due set diversi hanno codici hash diversi. Si testa inserendo nel set elementi comuni e non
     * @homework.pre 2 set con entries almeno in parte diverse per quanto definito da equals.
     * @homework.post i set sono rimasti invariati
     * @homework.testDescr Creazione di un nuovo oggetto MapAdapter. Ad entrambe le mappe esistenti, aggiungo gli stessi elementi definiti dal
     * metodo initialize(). Dalle due mappe ottengo gli entrySet e ne calcolo l'hashCode
     * @homework.exp i due entrySet NON hanno lo stesso hashCode.
     */
    @Test
    public void testHashCodeDifferentSets()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(2,"anyValue");
        map2.put("anyKey","uno");
        map2.put(0,"zero");

        int hash1 = map.entrySet().hashCode();
        int hash2 = map2.entrySet().hashCode();

        assertNotEquals(hash1,hash2);
    }

    //-------------------------------------BACKING METHODS:------------------------------------------------------


    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la tentata rimozione di un elemento
     * che non era presente all'interno del set abbia lasciato invariata la dimensione del set.
     * Dal momento che la size() ritornata da set e' la stessa che viene ritornata da map (e' un metodo wrapper) considero quella di map.
     * @homework.des Accertamento del funzionamento di {@code remove} quando la entry passata non si trova nell'entrySet
     * Si vuole che quando l'elemento non sia presente nel set(quindi nella mappa) la rimozione non abbia successo e che quindi la dimensione sia la
     * stessa (ad esempio sono sicuro che non venga rimosso un elemento a caso).
     * @homework.pre Set inizializzato dal metodo initialize()
     * @homework.post Il set e' rimasto invariato. La dimensione e' rimasta invariata.
     * @homework.testDescr Dopo aver salvato la dimensione della mappa, si ottiene un set contente entry che non sono presenti nell'entryset.
     * Si prende una di queste entry e la si prova a rimuovere dalla mappa. Siccome tale entry non era stata precedentemente inserita nel set, questa
     * non era presente e quindi remove non deve far nulla se non ritornare {@code false}.
     * Testo dunque mediante il metodo {@code size} che la dimensione sia rimasta invariata.
     * @homework.exp {@code size} deve ritornare la dimensione precedentemente salvata.
     */
    @Test
    public void testRemoveFailsSameSizeBacking()
    {
        int previousSize = map.size();
        HCollection coll = getSetEntriesKeyInMapButDifferentValue();
        HMap.HEntry e = (HMap.HEntry) coll.iterator().next();

        entrySet.remove(e);

        assertEquals(previousSize, map.size());
    }

    /**
     * Test del metodo <code>public void clear()</code>: testo che facendo il clear sull'entrySet, nella mappa siano stati
     * cancellati tutti gli elementi.
     * @homework.des Accertamento che clear cancelli gli elementi della mappa quando invocato su un entrySet.
     * @homework.pre entrySet inizializzato dal metodo initialize()
     * @homework.post La mappa e il set sono vuoti
     * @homework.testDescr Prendo l'entrySet generato dalla mappa e su di esso invoco il metodo clear. La mappa deve essere vuota,
     * testo cio' invocando il metodo isEmpty di map
     * @homework.exp {@code isEmpty} ritorna {@code true}
     */
    @Test
    public void testClearBacking()
    {
        entrySet.clear();

        assertTrue(map.isEmpty());
    }
    

    /**
     * Test del metodo <code>public Object setValue(Object o)</code>: Testo che se un valore viene modificato usando il metodo {@code setValue}
     * definito nella classe Entry, questo venga modificato anche nella mappa che contiene tale entry.
     * @homework.des Accertamento della proprieta' di backing quando si esegue il set in un'entry.
     * @homework.pre EntrySet inzializzato dal metodo initialize()
     * @homework.post E' stato sostituito un elemento sia nell'entry che nella mappa.
     * @homework.testDescr Si ottiene un'entry dall'entrySet.
     * Si sostituisce il valore della entry usando il metodo {@code setValue}
     * Per verificare che la modifica abbia avuto ripercussioni si chiama il metodo containsValue passando il valore appena inserito.
     * @homework.exp <code>containsValue</code> deve ritornare {@code true}
     */
    @Test
    public void testEntrySetValueBacking()
    {
        HMap.HEntry e = (HMap.HEntry) entrySet.iterator().next();

        e.setValue("newValue");

        assertTrue(map.containsValue("newValue"));
    }


    /**
     * Test del metodo {@code public void boolean remove(Object o)}: Testo che l'invocazione di {@code remove} passando una entry all'interno del set,
     * rimuova l'elemento dalla mappa oltre che dal set.
     * @homework.des Accertamento del corretto comportamento di backing di {@code remove} con rimozione dell'elemento dalla mappa a cui apparteneva.
     * @homework.pre entrySet creato dal metodo {@code initialize}
     * @homework.post L'entry passata al metodo remove e' stata rimossa dalla mappa.
     * @homework.testDescr Si crea un entrySet con il metodo {@code getSomeEntriesInMap()} che contiene alcune entries che sono
     * all'interno dell'entrySet. Si rimuove una di queste entry.
     * Siccome l'entry era presente nel set, dopo aver invocato {@code remove} tale entry non deve piu' essere presente.
     * Per la proprieta' di backing, siccome l'elemento e' stato rimosso dal set, questo deve essere stato rimosso anche dalla mappa.
     * Si chiama il metodo contains passando l'entry rimossa per verificare che l'entry non sia piu' presente.
     * @homework.exp {@code contains} ritorna {@code false}
     */
    @Test
    public void testRemoveRemovesEntryFromMapBacking()
    {
        HCollection coll = getSetSomeEntriesInMap();
        HMap.HEntry e = (HMap.HEntry) coll.iterator().next();

        entrySet.remove(e);

        assertFalse(map.containsKey(e.getKey()));
        assertFalse(map.containsValue(e.getValue()));
    }


    /**
     * Test del metodo {@code public void boolean removeAll(Object o)}: Testo che l'invocazione di {@code removeAll} passando un entrySet contenente
     * alcune entries che sono presenti all'interno della mappa, rimuova tali entries dalla mappa stessa.
     * @homework.des Accertamento del corretto comportamento di backing di {@code removeAll} con rimozione degli elementi dalla mappa a cui apparteneva.
     * Si verifica solo quando entries vengono rimosse: si e' gia' verificato in precedenza che removeAll funziona con entries non
     * contenute nella mappa.
     * @homework.pre entrySet creato dal metodo {@code initialize}
     * @homework.post Le entries contenute nel set sono state rimosse dalla mappa.
     * @homework.testDescr Si crea un entrySet con il metodo {@code getSomeEntriesInMap()} che contiene alcune entries che sono
     * all'interno dell'entrySet. Si rimuovono queste entry.
     * Siccome le entries erano presenti nel set, dopo aver invocato {@code removeAll} tali entry non devono piu' essere presenti.
     * Per la proprieta' di backing, siccome gli elementi sono stati rimossi dal set, questi devono essere stati rimossi anche dalla mappa.
     * Per ciascuna entry eliminata si verifica che sia il valore (inseriti appositamente in modo unico) che la chiave siano stati rimossi
     * dalla mappa.
     * @homework.exp {@code contains} ritorna {@code false} per tutte le chiavi e tutti i valori.
     */
    @Test
    public void testRemoveAllBackingWhenElementsAreRemoved()
    {
        HCollection coll = getSetSomeEntriesInMap();

        entrySet.removeAll(coll);

        boolean isOk = true;
        HIterator it = coll.iterator();
        while(it.hasNext())
        {
            HMap.HEntry e = (HMap.HEntry) it.next();
            if(map.containsKey(e.getKey()) || map.containsValue(e.getValue()))
            {
                isOk = false;
                break;
            }
        }
        assertTrue(isOk);
    }


    /**
     * Test del metodo {@code public void boolean retainAll(Object o)}: Testo che l'invocazione di {@code retainAll} passando un entrySet contenente
     * alcune entries che sono presenti all'interno della mappa, rimuova le entries non presenti nel set dalla mappa.
     * @homework.des Accertamento del corretto comportamento di backing di {@code retainAll} con rimozione degli elementi dalla mappa.
     * Si verifica solo quando entries vengono rimosse: si e' gia' verificato in precedenza che retainAll funziona con entries non
     * contenute nella mappa.
     * @homework.pre entrySet creato dal metodo {@code initialize}
     * @homework.post Le entries contenute nel set sono state mantenute nella mappa.
     * @homework.testDescr Si crea un entrySet con il metodo {@code getSomeEntriesInMap()} che contiene alcune entries che sono
     * all'interno dell'entrySet. Si mantengono queste entry.
     * Siccome le entries erano presenti nel set, dopo aver invocato {@code retainAll} tali entry devono essere le uniche presenti.
     * Per la proprieta' di backing, siccome gli elementi sono stati rimossi dal set, questi devono essere stati rimossi anche dalla mappa.
     * Per ciascuna entry eliminata si verifica che sia il valore (inseriti appositamente in modo unico) che la chiave devono essere presenti
     * nella mappa.
     * @homework.exp {@code contains} ritorna {@code false} per tutte le chiavi e tutti i valori.
     */
    @Test
    public void testRetainAllBackingWhenElementsAreRemoved()
    {
        HCollection coll = getSetSomeEntriesInMap();

        entrySet.retainAll(coll);

        boolean isOk = true;
        HIterator it = coll.iterator();
        while(it.hasNext())
        {
            HMap.HEntry e = (HMap.HEntry) it.next();
            if(!(map.containsKey(e.getKey()) && map.containsValue(e.getValue())))
            {
                isOk = false;
                break;
            }
        }
        assertTrue(isOk);
    }


    //SUPPORT METHODS

    /**
     * Ritorna un entrySet con alcune Entry presenti all'interno dell'oggetto MapAdapter usato per effettuare i test.
     * @return un entrySet come sopra specificato
     */
    private HSet getSetSomeEntriesInMap()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(0,"zero");
        map2.put(2,"due");

        return map2.entrySet();
    }

    /**
     * Ritorna un entrySet con alcune Entry presenti all'interno dell'oggetto MapAdapter usato per effettuare i test e altre
     * che invece non sono presenti.<p>
     * Le entry non presenti possono differiscono solo per chiave, solo per valore o entrambi.
     * @return un entrySet come sopra specificato
     */
    private HSet getSetMixedEntries()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(0,"zero");
        map2.put(2,"due");
        map2.put(1,"ValueNotRelatedTo1InsideMap");
        map2.put("keyNotInsideMap","AnyValue");
        map2.put("keyNotInsideMap2",2);

        return map2.entrySet();
    }

    /**
     * Ritorna un entrySet con delle Entry che non sono presenti nell'oggetto MapAdapter usato per sviluppare i test.
     * Le entry differiscono per il valore. Le chiavi sono presenti nell'oggetto MapAdapter.
     * @return un entrySet come sopra specificato
     */
    private HSet getSetEntriesKeyInMapButDifferentValue()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(0,"ValueNotRelatedTo0InsideMap");
        map2.put(1,"AnyValue");

        return map2.entrySet();
    }

    /**
     * Ritorna un entrySet con delle Entry che non sono presenti nell'oggetto MapAdapter usato per sviluppare i test.
     * Le entry differiscono sia per valore che per chiave.
     * @return un entrySet come sopra specificato
     */
    private HSet getSetEntriesNeitherKeyNorValueInMap()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put("zero","ValueNotRelatedTo0InsideMap");
        map2.put("anyKey","AnyValue");

        return map2.entrySet();
    }

    /**
     * Ritorna un entrySet con delle Entry che non sono presenti nell'oggetto MapAdapter usato per sviluppare i test.
     * Le entry differiscono per valore, chiave o entrambi.
     * @return un entrySet come sopra specificato
     */
    private HSet getSetEntriesNotInMap()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(0,"ValueNotRelatedTo0InsideMap");
        map2.put("keyNotInsideMap","AnyValue");
        map2.put("keyNotInsideMap2",2);

        return map2.entrySet();
    }

    /**
     * Controlla che due array siano uguali in qualsiasi ordine.
     * Se alla fine dell'array sono presenti elementi non validi, questa informazione viene passata al metodo
     * usando il parametro invalidElementsInEntries: tali valori non verranno considerati per determinare l'uguaglianza.
     */
    private boolean arrayEqualsAnyOrder(Object[] keys,Object[] values, Object[] entries, int invalidElementsInEntries)
    {
        if(keys.length + invalidElementsInEntries != entries.length)
            return false;

        boolean isOk = true;
        for (int i = 0; i < keys.length; i++)
        {
            boolean found = false;
            for (Object entry : entries)
            {
                HMap.HEntry e = (HMap.HEntry) entry;
                if (e.getKey().equals(keys[i]) && e.getValue().equals(values[i]))
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                isOk = false;
                break;
            }
        }

        return isOk;
    }

}
