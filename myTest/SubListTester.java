package myTest;

import myAdapter.*;
import org.junit.*;

import static org.junit.Assert.*;


/**
 * Questa suite di test ha il compito di testare che la sottolista di un oggetto {@code ListAdapter} abbia una corretta
 * visione della lista padre. Si verifica inoltre la proprieta' di backing su questa sottolista per cui modifiche strutturali
 * e non strutturali devono avere ripercussioni sulla lista originale. Modifiche non strutturali alla lista padre devono avere
 * ripercussioni nella sottolista.<p>
 * Si vuole dimostrare che tutti i metodi definiti in {@code SubList} funzionino correttamente
 *
 * @author Formaggio Alberto
 *
 * @safe.des Per testare la classe nella sua interezza e' stato testato ciascun metodo fornendo in input parametri validi
 * e non validi in modo da testare il piu' ampio numero di casi possibili in cui la sottolista si puo' trovare.<p>
 * La documentazione di ciascun test case e' stata eseguita seguendo la colonna "homework" fornita nel file della consegna,
 * inoltre i metodi hanno tutti un nome che e' il piu' evocativo possibile.
 * @safe.pre Si assicura che i metodi definiti nella classe {@code ListAdapter} siano gia' stati testati.
 * Per tale motivo verranno testati i metodi ridefiniti in {@code SubList} e quelli che dipendono direttamente dagli indici from e to che
 * definiscono la porzione di lista visibile. Tutti gli altri se funzionano per {@code ListAdapter} funzioneranno sicuramente anche per
 * {@code SubList}.
 * @safe.post Si sono ottenuti i risultati dell'esecuzione di tutti i test in questa suite.
 * @safe.records Consultare il file <a href="..\..\Test suite execution records\Test Results - SubListTester.html">
 *     "Test Results - SubListTester.html"</a> nella cartella "Test suite execution records"
 * @safe.exec I test sono stati eseguiti utilizzando JUnit v4.13 e hamcrest v1.3.
 * Per poter lanciare i test e' necessario inserire i file .jar di questi framework all'interno del CLASSPATH settando tale
 * variabile di ambiente.
 *
 */
public class SubListTester
{
    private ListAdapter list;
    private HList sub;
    /**
     * Indice iniziale (incluso) della lista padre visibile dalla sottolista.
     */
    private final int fromIndex = 1;
    /**
     * Indice finale (escluso) della lista padre visibile dalla sottolista.
     */
    private final int toIndex = 4;


    /**
     * Inizializza un oggetto inserendo 3 valori in ordine 0,1,2. Al termine della creazione la lista su cui verrano eseguiti i test sara'
     * fatta nel seguente modo:
     *<pre>
     * list
     * | 0 | 1 | 2 | 3 | 4 |
     *</pre>
     * Crea inoltre una subList che va da 1 (incluso) a 4 (escluso), ovvero la sublist sara' fatta nel seguente modo:<pre>
     * | 1 | 2 | 3 |
     * </pre>
     */
    @Before
    public void Initialize()
    {
        list = new ListAdapter();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        sub = list.subList(fromIndex, toIndex);
    }


    /**
     * Test del metodo <code>public int size()</code>: Testo che dopo l'inizializzazione la sottolista abbia dimensione
     * pari alla differenza degli indici to e from.
     * @homework.des Testo che la dimensione dopo l'inizializzazione sia corretta.
     * @homework.pre Lista e sottolista  create dal metodo initialize
     * @homework.post la sottolista non e' stata modificata.
     * @homework.testDescr creo una nuova lista e una sua sottolista che va dalla posizione 1 inclusa alla posizione 4 esclusa.
     * La dimensione deve essere 3 perche' 4 - 1 = 3.
     * @homework.exp La lista ha dimensione 3.
     */
    @Test
    public void testSizeIsRight()
    {
        assertEquals(toIndex - fromIndex, sub.size());
    }

    /**
     * Test dell'inizializzazione di subList: testo che gli elementi abbiano una corrispondenza con la lista che l'ha generata.
     * @homework.des Accertamento che gli indici della sottolista siano corretti rispetto alla lista principale.
     * Ad esempio sub = list.subList(1,4). Crea la seguente lista:
     * sub: | 1 | 2 | 3 |.
     * L'elemento che sub ha in posizione 0 deve essere in posizione 1 nella lista principale (perche' la sottolista inizia dalla posizione 1)
     * L'elemento che sub ha in posizione 1 deve essere in posizione 2 nella lista principale
     * L'elemento che sub ha in posizione 2 deve essere in posizione 3 nella lista principale
     * Cioe' la posizione nella lista e' data da (posizioneInizialeSottolista) + (offsetSottolista)
     * @homework.pre Lista e sottolista inizializzate dal metodo initialize
     * @homework.post Lista e sottolista sono rimaste invariate.
     * @homework.testDescr Una volta creata lista e sottolista che parte dalla posizione 1, si inizia a scansionare la lista.
     * L'elemento in posizione i-esima della sottolista deve essere nella posizione i+1 (perche' la sottolista parte da 1).
     * @homework.exp Le posizione tra elementi della sottolista e lista principale corrispondono.
     */
    @Test
    public void testSubListElementCorrespondenceWithParent()
    {
        boolean isOk = true;

        for(int i = 0; i < sub.size(); i++)
            if(!sub.get(i).equals(list.get(i + fromIndex)))
            {
                isOk = false;
                break;
            }

        assertTrue(isOk);
    }

    /**
     * Test del metodo <code>public void clear()</code>: testo che facendo il clear della lista figlia, nella lista padre siano stati
     * cancellati SOLO gli elementi presenti all'interno della sottolista.
     * @homework.des Accertamento che clear cancelli gli elementi della sottolista dalla lista principale e non altri elementi.
     * @homework.pre Lista e sottolista create dal metodo initialize()
     * @homework.post La sottolista e' vuota
     * @homework.testDescr Ottengo la dimensione della lista prima della rimozione e quella della sottolista.
     * Dopo l'invocazione di clear la dimensione deve essere diminuita di una quantita' pari alla dimensione della sottolista.
     * Il metodo size e' usato per fare il controllo
     * @homework.exp La dimensione della lista dopo la rimozione e' dimListaPrimaRimozione - dimSottolistaPrimaRimozione.
     */
    @Test
    public void testSubListClear()
    {
        int sizeListBeforehand = list.size();
        int subSize = sub.size();

        sub.clear();

        assertEquals(sizeListBeforehand - subSize, list.size());
    }

    /**
     * Test del metodo <code>public boolean isEmpty()</code>: Testo che appena creata una sottolista se gli indici from e to sono uguali, questa risulti vuota.
     * @homework.des Testo che il metodo isEmpty() ritorni true quando la lista e' stata inizializzata per essere sicuro che quando gli indici passati sono uguali
     * la lista risulti vuota.
     * @homework.pre e' stata creata una lista dal metodo initialize(). Sottolista creata con indici validi uguali tra loro.
     * @homework.post la lista e la sottolista sono rimaste invariate.
     * @homework.testDescr Creo una nuova lista che va dalla posizione 1 inclusa alla posizione 1 esclusa, non deve quindi contenere nessun elemento.
     * Dopo averla creata controllo che la lista sia vuota invocando il metodo isEmpty().
     * @homework.exp <code>isEmpty</code> ritorna <code>false</code>.
     */
    @Test
    public void testIsEmptyTrueIfIndexesAreTheSame()
    {
        sub = list.subList(1,1);

        boolean isEmpty = sub.isEmpty();

        assertTrue(isEmpty);
    }

    /**
     * Test del metodo <code>public boolean isEmpty()</code>: Testo che appena creata una sottolista se gli indici from e to non sono uguali, questa non risulti vuota.
     * @homework.des Testo che il metodo isEmpty() ritorni false quando la lista e' stata inizializzata per essere sicuro che quando gli indici passati non sono uguali
     * la lista non risulti vuota.
     * @homework.pre e' stata creata una lista e una sottolista dal metodo initialize()
     * @homework.post la lista e la sottolista sono rimaste invariate.
     * @homework.testDescr Creo una nuova lista che va dalla posizione 1 inclusa alla posizione 4 esclusa, deve quindi contenere degli elementi.
     * Dopo averla creata controllo che la lista non sia vuota invocando il metodo isEmpty().
     * @homework.exp <code>isEmpty</code> ritorna <code>false</code>.
     */
    @Test
    public void testIsEmptyFalseIfIndexesAreNotTheSame()
    {
        boolean isEmpty = false;

        assertFalse(isEmpty);
    }

    /**
     * Test del metodo <code>public boolean add(int index, Object o)</code>: Testo che se un elemento viene inserito nella sottolista, questo venga
     * inserito anche nella lista principale nella posizione corretta.
     * L'inserimento avviene nella prima posizione della sottolista.
     * @homework.des Accertamento della proprieta' di backing quando si esegue un add nella sottolista.
     * @homework.pre Lista e sottolista inzializzte dal metodo initialize()
     * @homework.post E' stato inserito un elemento sia nella lista padre che nella sottolista.
     * @homework.testDescr Si inserisce un elemento non presente nella sottolista usando il metodo add.
     * L'elemento inserito nella sottolista e' in posizione 0, quindi in posizione 1 nella lista principale.
     * Si invoca il metodo get nella posizione 1 della lista principale per verificare che il l'add abbia avuto successo.
     * @homework.exp <code>list.get(1)</code> deve ritornare l'elemento appena inserito da add
     */
    @Test
    public void testAddIndexAtBeginningBacking()
    {
        sub.add(0, "aggiunto");

        assertEquals("aggiunto",list.get(fromIndex));
    }

    /**
     * Test del metodo <code>public boolean add(int index, Object o)</code>: Testo che se un elemento viene inserito nella sottolista, questo venga
     * inserito anche nella lista principale nella posizione corretta.
     * L'inserimento avviene in una posizione intermedia della sottolista.
     * @homework.des Accertamento della proprieta' di backing quando si esegue un add nella sottolista.
     * @homework.pre Lista e sottolista inzializzte dal metodo initialize()
     * @homework.post E' stato inserito un elemento sia nella lista padre che nella sottolista.
     * @homework.testDescr Si inserisce un elemento non presente nella sottolista usando il metodo add.
     * L'elemento inserito nella sottolista e' in posizione 1, quindi in posizione 2 nella lista principale.
     * Si invoca il metodo get nella posizione 2 della lista principale per verificare che il l'add abbia avuto successo.
     * @homework.exp <code>list.get(2)</code> deve ritornare l'elemento appena inserito da add
     */
    @Test
    public void testAddIndexInMiddleBacking()
    {
        int offset = 1;

        sub.add(offset, "aggiunto");

        assertEquals("aggiunto",list.get(fromIndex + offset));
    }

    /**
     * Test del metodo <code>public boolean add(int index, Object o)</code>: Testo che se un elemento viene inserito nella sottolista, questo venga
     * inserito anche nella lista principale nella posizione corretta.
     * L'inserimento avviene nell'ultima posizione della sottolista.
     * @homework.des Accertamento della proprieta' di backing quando si esegue un add nella sottolista. Siccome un add con l'indice uguale alla size e'
     * la stessa cosa di un add senza indice, uso l'add senza indice. Il risultato deve essere il medesimo.
     * @homework.pre Lista e sottolista inzializzte dal metodo initialize()
     * @homework.post E' stato inserito un elemento sia nella lista padre che nella sottolista.
     * @homework.testDescr Si inserisce un elemento non presente nella sottolista usando il metodo add.
     * L'elemento inserito nella sottolista e' in posizione sub.size() = 3, quindi in posizione 4 nella lista principale.
     * Si invoca il metodo get nella posizione 4 della lista principale per verificare che il l'add abbia avuto successo.
     * @homework.exp <code>list.get(4)</code> deve ritornare l'elemento appena inserito da add
     */
    @Test
    public void testAddIndexAtEnd()
    {
        int subSize = sub.size();

        sub.add("aggiunto");

        assertEquals("aggiunto",list.get(fromIndex + subSize));
    }

    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: Testo che, una volta inizializzata la sottolista con degli elementi al suo interno, gli elementi
     * NON presenti nella porzione di lista a lei visibile ma presenti nella lista padre non risultino presenti nella sottolista.
     * Si testa cio' con un elemento collocato PRIMA dell'inizio di questa lista.
     * @homework.des Accertamento che determinati oggetti presenti nella lista ma NON nella sottolista NON risultino presenti nella sottolista.
     * @homework.pre Lista e sottolista inizializzate dal metodo initialize().
     * @homework.post la lista e la sottolista sono rimaste invariate.
     * @homework.testDescr cerco l'elemento 0 precedentemente inserito dal metodo initialize() usando il metodo contains(). Siccome la sottolista NON vede
     * la cella in posizione 0 della lista originale (contenente l'elemento 0) e la lista originale non e' stata modificata, l'elemento non e' presente nella
     * sottolista ma nella lista padre.
     * 0 nella lista originale si trova a sinistra della sottolista.
     * @homework.exp <code>contains(0)</code> ritorna <code>false</code>.
     */
    @Test
    public void testContainsFalseIfOnlyInParentListBefore()
    {
        boolean isContained = sub.contains(0);

        assertFalse(isContained);
    }


    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: Testo che, una volta inizializzata la sottolista con degli elementi al suo interno, gli elementi
     * NON presenti nella porzione di lista a lei visibile ma presenti nella lista padre non risultino presenti nella sottolista.
     * Si testa cio' con un elemento collocato DOPO la fine di questa lista.
     * @homework.des Accertamento che determinati oggetti presenti nella lista ma NON nella sottolista NON risultino presenti nella sottolista.
     * @homework.pre Lista e sottolista inizializzate dal metodo initialize().
     * @homework.post la lista e la sottolista sono rimaste invariate.
     * @homework.testDescr cerco l'elemento 4 precedentemente inserito dal metodo initialize() usando il metodo contains(). Siccome la sottolista NON vede
     * la cella in posizione 4 della lista originale (contenente l'elemento 4) e la lista originale non e' stata modificata, l'elemento non e' presente nella
     * sottolista ma nella lista padre.
     * 4 nella lista originale si trova a destra della sottolista.
     * @homework.exp <code>contains(4)</code> ritorna <code>false</code>.
     */
    @Test
    public void testContainsFalseIfOnlyInParentListAfter()
    {
        boolean isContained = sub.contains(4);

        assertFalse(isContained);
    }

    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: Testo che, una volta inizializzata la sottolista con degli elementi al suo interno, gli elementi
     * presenti nella porzione di lista a lei visibile siano presenti all'interno della sottolista stessa.
     * @homework.des Accertamento che determinati oggetti presenti nella lista e nella sottolista risultino presenti nella sottolista.
     * @homework.pre Lista e sottolista inizializzate dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr cerco l'elemento 2 precedentemente inserito dal metodo initialize() usando il metodo contains(). Siccome la sottolista vede
     * la cella in posizione 2 della lista originale (contenente l'elemento 2) e la lista originale non e' stata modificata, l'elemento e' presente sia nella
     * sottolista che nella lista padre.
     * @homework.exp contains(2) ritorna true
     */
    @Test
    public void testContainsTrueIfPresent()
    {
        boolean isContained = sub.contains(2);

        assertTrue(isContained);
    }

    /**
     * Test del metodo <code>public int indexOf(Object o)</code>: Testo che, una volta inizializzata la sottolista con degli elementi
     * al suo interno, conoscendo la disposizione degli elementi all'interno della stessa, questa disposizione sia coerente con quanto ritornato
     * dal metodo indexOf().
     * @homework.des Accertamento che il metodo indexOf() ritorni la posizione corretta dell'oggetto quando questo e' presente nella sottolista.
     * Gli indici utilizzati all'interno del metodo indexOf devono essere corretti anche nel caso generale dove from != 0 e to != size().
     * @homework.pre Lista e sottolista inizializzate dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una sottolista che va dagli indici 1 incluso al 4 escluso.
     * La sottolista dopo la creazione e' fatta nel segunente modo: | 1 | 2 | 3 |.
     * Cercando l'elemento 1, essendo questo presente all'interno della sottolista, devo ottenere l'indice 0 che corrisponde alla prima posizione
     * della sottolista (e la seconda della lista padre). Tale ricerca viene fatta usando il metodo <code>indexOf</code>
     * @homework.exp <code>indexOf(1)</code> ritorna 0.
     */
    @Test
    public void testIndexOfWithElementInSubList()
    {
        int result = sub.indexOf(1);

        assertEquals(0,result);
    }

    /**
     * Test del metodo <code>public int indexOf(Object o)</code>: Testo che, una volta inizializzata la sottolista con degli elementi
     * al suo interno, gli elementi NON presenti nella porzione di lista a lei visibile ma presenti nella lista padre NON vengano trovati quando
     * si prova ad invocare il metodo indexOf nella sottolista.
     * Si testa cio' con un elemento collocato PRIMA dell'inizio di questa lista.
     * @homework.des Accertamento che il metodo indexOf() ritorni -1 se questo e' presente nella lista padre ma non in questa lista. Gli indici utilizzati
     * all'interno del metodo indexOf devono essere corretti anche nel caso generale dove from != 0 e to != size().
     * @homework.pre Lista e sottolista inizializzate dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una sottolista che va dagli indici 1 incluso al 4 escluso.
     * La sottolista dopo la creazione e' fatta nel segunente modo: | 1 | 2 | 3 |.
     * Cercando l'elemento 0, essendo questo non presente all'interno della sottolista ma solo all'interno della lista padre,
     * devo ottenere l'indice -1 che corrisponde ad un elemento non trovato. Tale ricerca viene fatta usando il metodo <code>indexOf</code>
     * @homework.exp <code>indexOf(0)</code> ritorna -1.
     */
    @Test
    public void testIndexOfWithElementInParentListBefore()
    {
        int result = sub.indexOf(0);

        assertEquals(-1, result);
    }

    /**
     * Test del metodo <code>public int indexOf(Object o)</code>: Testo che, una volta inizializzata la sottolista con degli elementi
     * al suo interno, gli elementi NON presenti nella porzione di lista a lei visibile ma presenti nella lista padre NON vengano trovati quando
     * si prova ad invocare il metodo indexOf nella sottolista.
     * Si testa cio' con un elemento collocato DOPO dell'inizio di questa lista.
     * @homework.des Accertamento che il metodo indexOf() ritorni -1 se questo e' presente nella lista padre ma non in questa lista. Gli indici utilizzati
     * all'interno del metodo indexOf devono essere corretti anche nel caso generale dove from != 0 e to != size().
     * @homework.pre Lista e sottolista inizializzate dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una sottolista che va dagli indici 1 incluso al 4 escluso.
     * La sottolista dopo la creazione e' fatta nel segunente modo: | 1 | 2 | 3 |.
     * Cercando l'elemento 4, essendo questo non presente all'interno della sottolista ma solo all'interno della lista padre,
     * devo ottenere l'indice -1 che corrisponde ad un elemento non trovato. Tale ricerca viene fatta usando il metodo <code>indexOf</code>
     * @homework.exp <code>indexOf(4)</code> ritorna -1.
     */
    @Test
    public void testIndexOfWithElementInParentListAfter()
    {
        int result = sub.indexOf(4);

        assertEquals(-1, result);
    }

    /**
     * Test del metodo <code>public int lastIndexOf(Object o)</code>: Testo che, una volta inizializzata la sottolista con degli elementi
     * al suo interno, conoscendo la disposizione degli elementi all'interno della stessa, questa disposizione sia coerente con quanto ritornato
     * dal metodo lastIndexOf().
     * @homework.des Accertamento che il metodo lastIndexOf() ritorni la posizione corretta dell'oggetto quando questo e' presente nella sottolista.
     * Gli indici utilizzati all'interno del metodo lastIndexOf devono essere corretti anche nel caso generale dove from != 0 e to != size().
     * @homework.pre Lista e sottolista inizializzate dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una sottolista che va dagli indici 1 incluso al 4 escluso.
     * La sottolista dopo la creazione e' fatta nel segunente modo: | 1 | 2 | 3 |.
     * Cercando l'elemento 1, essendo questo presente all'interno della sottolista, devo ottenere l'indice 0 che corrisponde alla prima posizione
     * della sottolista (e la seconda della lista padre). Tale ricerca viene fatta usando il metodo <code>lastIndexOf</code>
     * @homework.exp <code>lastIndexOf(1)</code> ritorna 0.
     */
    @Test
    public void testLastIndexOfWithElementInSubList()
    {
        int result = sub.lastIndexOf(1);

        assertEquals(0,result);
    }

    /**
     * Test del metodo <code>public int lastIndexOf(Object o)</code>: Testo che, una volta inizializzata la sottolista con degli elementi
     * al suo interno, gli elementi NON presenti nella porzione di lista a lei visibile ma presenti nella lista padre NON vengano trovati quando
     * si prova ad invocare il metodo lastIndexOf nella sottolista.
     * Si testa cio' con un elemento collocato PRIMA dell'inizio di questa lista.
     * @homework.des Accertamento che il metodo lastIndexOf() ritorni -1 se questo e' presente nella lista padre ma non in questa lista. Gli indici utilizzati
     * all'interno del metodo lastIndexOf devono essere corretti anche nel caso generale dove from != 0 e to != size().
     * @homework.pre Lista e sottolista inizializzate dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una sottolista che va dagli indici 1 incluso al 4 escluso.
     * La sottolista dopo la creazione e' fatta nel segunente modo: | 1 | 2 | 3 |.
     * Cercando l'elemento 0, essendo questo non presente all'interno della sottolista ma solo all'interno della lista padre,
     * devo ottenere l'indice -1 che corrisponde ad un elemento non trovato. Tale ricerca viene fatta usando il metodo <code>lastIndexOf</code>
     * @homework.exp <code>lastIndexOf(0)</code> ritorna -1.
     */
    @Test
    public void testLastIndexOfWithElementInParentListBefore()
    {
        int result = sub.lastIndexOf(0);

        assertEquals(-1, result);
    }

    /**
     * Test del metodo <code>public int lastIndexOf(Object o)</code>: Testo che, una volta inizializzata la sottolista con degli elementi
     * al suo interno, gli elementi NON presenti nella porzione di lista a lei visibile ma presenti nella lista padre NON vengano trovati quando
     * si prova ad invocare il metodo lastIndexOf nella sottolista.
     * Si testa cio' con un elemento collocato DOPO dell'inizio di questa lista.
     * @homework.des Accertamento che il metodo lastIndexOf() ritorni -1 se questo e' presente nella lista padre ma non in questa lista. Gli indici utilizzati
     * all'interno del metodo lastIndexOf devono essere corretti anche nel caso generale dove from != 0 e to != size().
     * @homework.pre Lista e sottolista inizializzate dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una sottolista che va dagli indici 1 incluso al 4 escluso.
     * La sottolista dopo la creazione e' fatta nel segunente modo: | 1 | 2 | 3 |.
     * Cercando l'elemento 4, essendo questo non presente all'interno della sottolista ma solo all'interno della lista padre,
     * devo ottenere l'indice -1 che corrisponde ad un elemento non trovato. Tale ricerca viene fatta usando il metodo <code>lastIndexOf</code>
     * @homework.exp <code>lastIndexOf(4)</code> ritorna -1.
     */
    @Test
    public void testLastIndexOfWithElementInParentListAfter()
    {
        int result = sub.lastIndexOf(4);

        assertEquals(-1, result);
    }

    /**
     * Test del metodo <code>public Object get(int index)</code>: testa che il metodo <code>get</code> ritorni correttamente l'elemento
     * associato all'indice passato anche quando si considera una sottolista.
     * @homework.des Accertamento del corretto valore di ritorno del metodo {@code get} quando questo viene invocato in una sottolista.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad ottenere un elemento nella posizione 1. Per come e' stata inizializzata la sottolista, nella posizione 1
     * e' presente l'elemento 2.
     * @homework.exp {@code get} ritorna 2.
     */
    @Test
    public void testGetReturnsRightElementInSublist()
    {
        Object result = sub.get(1);

        assertEquals(2,result);
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: Testo che se un elemento viene rimosso dalla sottolista, questo venga rimosso
     * anche dalla lista principale.
     * @homework.des Accertamento della proprieta' di backing quando si esegue il remove dalla sottolista.
     * @homework.pre Lista e sottolista inzializzte dal metodo initialize()
     * @homework.post E' stato rimosso un elemento sia dalla lista padre che dalla sottolista.
     * @homework.testDescr Si rimuove un elemento presente nella sottolista (2) usando il metodo remove. Siccome 2 e' presente nella sottolista
     * la rimozione ha successo e il metodo remove deve ritornare true.
     * La lista principale non deve contenere l'elemento appena rimosso. Si fa il test usando il metodo contains()
     * @homework.exp <code>list.contains(2)</code> deve ritornare <code>false</code> perche' l'elemento e' stato rimosso
     */
    @Test
    public void testRemoveSucceedsWithBacking()
    {
        Object toRemove = 2;

        assertTrue(sub.remove(toRemove));
        assertFalse(list.contains(toRemove));
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: Testo che se si prova a rimuovere un elemento dalla sottolista
     * che non e' presente nella lista principale, la rimozione non abbia successo.
     * @homework.des Accertamento che nell'operazione di remove la sottolista veda solo i suoi elementi e non quelli della lista padre.
     * @homework.pre Lista e sottolista inzializzte dal metodo initialize()
     * @homework.post La sottolista e la lista sono rimaste invariate.
     * @homework.testDescr Si prova a rimuovere un elemento presente nella lista che non e' presente nella sottolista (4) usando il metodo remove.
     * Siccome 4 non e' presente nella sottolista la rimozione non ha successo e il metodo remove deve ritornare false.
     * La lista principale continua a contenere l'elemento rimosso. Si fa il test usando il metodo contains()
     * @homework.exp <code>list.contains(4)</code> deve ritornare <code>true</code> perche' l'elemento non e' stato rimosso.
     * <code>remove</code> invocato sulla sottolista deve ritornare <code>false</code>.
     */
    @Test
    public void testRemoveFailsInSublist()
    {
        Object toRemove = 4;

        boolean wasRemoved = sub.remove(toRemove);

        assertFalse(wasRemoved);
    }

    /**
     * Test del metodo <code>public void set(int index, Object o)</code>: Testo che se un elemento viene modificato nella sottolista, questo venga
     * modificato anche nella lista principale nella posizione corretta.
     * @homework.des Accertamento della proprieta' di backing quando si esegue il set nella sottolista.
     * @homework.pre Lista e sottolista inzializzte dal metodo initialize()
     * @homework.post E' stato sostituito un elemento sia dalla lista padre che dalla sottolista.
     * @homework.testDescr Si sostituisce un elemento presente nella sottolista usando il metodo set.
     * L'elemento sostituito nella sottolista e' in posizione 2, quindi in posizione 3 nella lista principale.
     * Si invoca il metodo get nella posizione 3 della lista principale per verificare che il set abbia avuto successo.
     * @homework.exp <code>get(3)</code> deve ritornare l'elemento appena inserito da set
     */
    @Test
    public void testSetSucceedsWithBacking()
    {
        int offset = 2;

        sub.set(offset, "modified");

        assertEquals("modified", list.get(offset + fromIndex));
    }


    /**
     * Test del metodo <code>public boolean add(Object o)</code>: Testo che l'add su una sottolista di sottolista si ripercuota
     * su tutte le liste precedenti. La dimensione di tutte le sottoliste e della lista principale deve aumentare di 1.
     * @homework.des Accertamento del corretto comportamento della funzione add di cui si e' fatto l'override nella classe SubList. La dimensione
     * deve essere aggiornata coerentemente in modo ricorsivo.
     * @homework.pre create liste e sottoliste valide
     * @homework.post l'elemento e' stato inserito in tutte le sottoliste.
     * @homework.testDescr Si creano delle sottoliste e si inserisce un elemento alla fine dell'ultima delle sottoliste create.<pre>
     * list:                 ===>       sub:          ===>       sub1:       ===>    sub2:
     * | 0 | 1 | 2 | 3 | 4 |           | 1 | 2 | 3 |             | 1 | 2 |           | 2 |
     *</pre>
     * Aggiungo un elemento "aggiunto" in coda a sub2. Le liste sono diventate:<pre>
     * list:                       ===>          sub:                    ===>       sub1:                  ===>       sub2:
     * | 0 | 1 | 2 | "aggiunto" | 3 | 4 |        | 1 | 2 | "aggiunto" | 3 |         | 1 | 2 | "aggiunto" |            | 2 | "aggiunto" |
     *</pre>
     * La dimensione di tutte le sottoliste e' aumentata di 1. Si testa cio' invocando il metodo <code>size</code> su tutte le sottoliste.
     * @homework.exp Tutte le sottoliste hanno aumentato la loro dimensione di 1 unita'
     */
    @Test
    public void testAddRecursiveSubListSize()
    {
        HList sub1 = sub.subList(0,2);
        HList sub2 = sub1.subList(1,2);

        sub2.add("aggiunto");

        assertEquals(2,sub2.size());
        assertEquals(3, sub1.size());
        assertEquals(4, sub.size());
    }

    /**
     * Test del metodo <code>public boolean add(Object o)</code>: Testo che l'add su una sottolista di sottolista si ripercuota
     * su tutte le liste precedenti. L'elemento inserito deve essere presente in tutte le sottoliste e nella lista principale.
     * @homework.des Accertamento del corretto comportamento della funzione add di cui si e' fatto l'override nella classe SubList. L'elemento
     * deve essere correttamente inserito in modo ricorsivo.
     * @homework.pre create liste e sottoliste valide
     * @homework.post l'elemento e' stato inserito in tutte le sottoliste.
     * @homework.testDescr Si creano delle sottoliste e si inserisce un elemento alla fine dell'ultima delle sottoliste create.<pre>
     * list:                 ===>       sub:          ===>       sub1:       ===>    sub2:
     * | 0 | 1 | 2 | 3 | 4 |           | 1 | 2 | 3 |             | 1 | 2 |           | 2 |
     *</pre>
     * Aggiungo un elemento "aggiunto" in coda a sub2. Le liste sono diventate:<pre>
     * list:                       ===>          sub:                    ===>       sub1:                  ===>       sub2:
     * | 0 | 1 | 2 | "aggiunto" | 3 | 4 |        | 1 | 2 | "aggiunto" | 3 |         | 1 | 2 | "aggiunto" |            | 2 | "aggiunto" |
     *</pre>
     * Si cerca l'elemento "aggiunto" con il metodo contains in tutte le liste
     * @homework.exp <code>contains("aggiunto")</code> deve ritornare <code>true</code> per tutte le sottoliste.
     */
    @Test
    public void testAddRecursiveSubListContains()
    {
        HList sub1 = sub.subList(0,2);
        HList sub2 = sub1.subList(1,2);

        sub2.add("aggiunto");

        assertTrue(sub1.contains("aggiunto"));
        assertTrue(sub2.contains("aggiunto"));
        assertTrue(sub.contains("aggiunto"));
    }

    /**
     * Test del metodo <code>public boolean add(Object o)</code>: Testo che l'add su una sottolista di sottolista si ripercuota
     * su tutte le liste precedenti. L'elemento inserito deve essere presente in tutte le sottoliste e nella lista principale nella posizione corretta.
     * Faccio un controllo usando il get.
     * @homework.des Accertamento del corretto comportamento della funzione add di cui si e' fatto l'override nella classe SubList. L'elemento
     * deve essere correttamente inserito in modo ricorsivo usando l'indice corretto.
     * @homework.pre create liste e sottoliste valide
     * @homework.post l'elemento e' stato inserito in tutte le sottoliste.
     * @homework.testDescr Si creano delle sottoliste e si inserisce un elemento alla fine dell'ultima delle sottoliste create.<pre>
     * list:                 ===>       sub:          ===>       sub1:       ===>    sub2:
     * | 0 | 1 | 2 | 3 | 4 |           | 1 | 2 | 3 |             | 2 | 3 |           | 2 |
     *</pre>
     * Aggiungo un elemento "aggiunto" in cima a sub2. Le liste sono diventate:<pre>
     * list:                       ===>          sub:                    ===>       sub1:                  ===>       sub2:
     * | 0 | 1 | "aggiunto" | 2 | 3 | 4 |        | 1 | "aggiunto" | 2 | 3 |         | 1 | "aggiunto" | 2 |            | "aggiunto" | 2 |
     *</pre>
     * Si cerca l'elemento "aggiunto" nella posizione aspettata in tutte le sottoliste.
     * @homework.exp <code>get(i)</code> deve ritornare <code>"aggiunto"</code> per tutte le sottoliste. Dove i e' stato creato
     * in base al risultato aspettato.
     */
    @Test
    public void testAddRecursiveSubListGet()
    {
        Object toAdd = "aggiunto";
        int from1 = 1;
        int to1 = 3;
        int from2 = 0;
        int to2 = 1;
        HList sub1 = sub.subList(from1, to1);
        HList sub2 = sub1.subList(from2, to2);

        sub2.add(0,toAdd);

        assertEquals(toAdd, list.get(fromIndex + from1 + from2));
        assertEquals(toAdd, sub.get(from1 + from2));
        assertEquals(toAdd, sub1.get(from2));
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: Testo che il remove su una sottolista di sottolista si ripercuota
     * su tutte le liste precedenti. La dimensione di tutte le sottoliste e della lista principale deve diminuire di 1.
     * @homework.des Accertamento del corretto comportamento della funzione remmove di cui si e' fatto l'override nella classe SubList. La dimensione
     * deve essere aggiornata coerentemente in modo ricorsivo.
     * @homework.pre create liste e sottoliste valide
     * @homework.post l'elemento e' stato rimosso da tutte le sottoliste.
     * @homework.testDescr Si creano delle sottoliste:<pre>
     * list:                 ===>       sub:          ===>       sub1:       ===>    sub2:
     * | 0 | 1 | 2 | 3 | 4 |           | 1 | 2 | 3 |             | 1 | 2 |           | 2 |
     *</pre>
     * Rimuovo l'elemento in testa a sub2 (il suo unico elemento). Le liste sono diventate:<pre>
     * list:                       ===>          sub:                    ===>       sub1:                  ===>       sub2:
     * | 0 | 1 | 3 | 4 |                         | 1 | 3 |                          | 1 |                             |
     * </pre>
     * La dimensione di tutte le sottoliste e' diminuita di 1. Si testa cio' invocando il metodo <code>size</code> su tutte le sottoliste.
     * @homework.exp Tutte le sottoliste hanno diminuito la loro dimensione di 1 unita'
     */
    @Test
    public void testRemoveRecursiveSubListSize()
    {
        HList sub1 = sub.subList(0,2);
        HList sub2 = sub1.subList(1,2);
        Object removed = sub2.get(0);

        sub2.remove(removed);

        assertEquals(0,sub2.size());
        assertEquals(1, sub1.size());
        assertEquals(2, sub.size());
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: Testo che il remove su una sottolista di sottolista si ripercuota
     * su tutte le liste precedenti. L'elemento rimosso deve essere rimosso da tutte le sottoliste e dalla lista principale.
     * @homework.des Accertamento del corretto comportamento della funzione remove di cui si e' fatto l'override nella classe SubList. L'elemento
     * deve essere correttamente rimosso in modo ricorsivo.
     * @homework.pre create liste e sottoliste valide
     * @homework.post l'elemento e' stato rimosso in tutte le sottoliste.
     * @homework.testDescr Si creano delle sottoliste e si rimuove l'elemento dell'ultima sottolista creata.<pre>
     * list:                 ===>       sub:          ===>       sub1:       ===>    sub2:
     * | 0 | 1 | 2 | 3 | 4 |           | 1 | 2 | 3 |             | 1 | 2 |           | 2 |
     * </pre>
     * Rimuovo l'elemento in testa a sub2 (il suo unico elemento, ovvero 2). Le liste sono diventate:<pre>
     * list:                       ===>          sub:                    ===>       sub1:                  ===>       sub2:
     * | 0 | 1 | 3 | 4 |                         | 1 | 3 |                          | 1 |                             |
     *</pre>
     * Si cerca l'elemento rimosso (2) con il metodo contains in tutte le liste.
     * @homework.exp <code>contains(2)</code> deve ritornare <code>false</code> per tutte le sottoliste.
     */
    @Test
    public void testRemoveRecursiveSubListContains()
    {
        HList sub1 = sub.subList(0,2);
        HList sub2 = sub1.subList(1,2);
        Object removed = sub2.get(0);

        sub2.remove(removed);

        assertFalse(sub1.contains(removed));
        assertFalse(sub2.contains(removed));
        assertFalse(sub.contains(removed));
    }
}
