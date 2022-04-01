package myTest;

import myAdapter.*;
import org.junit.*;


import static org.junit.Assert.*;

/**
 * Questa suite di test ha il compito di testare tutte le funzionalita' base della classe {@code ListAdapter}. <p> Il corretto comportamento
 * di backing della sottolista e le funzionalita' dell'iteratore verranno testate in una diversa suite. <p>
 * Si vuole dimostrare che tutti i metodi definiti in ListAdapter funzionino correttamente
 *
 * @author Formaggio Alberto
 *
 * @safe.des Per testare la classe nella sua interezza e' stato testato ciascun metodo fornendo in input parametri validi
 * e non validi in modo da testare il piu' ampio numero di casi possibili in cui la collezione si puo' trovare.<p>
 * La documentazione di ciascun test case e' stata eseguita seguendo la colonna "homework" fornita nel file della consegna,
 * inoltre i metodi hanno tutti un nome che e' il piu' evocativo possibile.
 * @safe.pre Si assicura che i metodi della classe Vector di Java J2ME CLDC 1.1 siano gia' stati esaustivamente testati
 * al rilascio di Java J2ME CLDC 1.1.
 * @safe.post Si sono ottenuti i risultati dell'esecuzione di tutti i test in questa suite.
 * @safe.records Consultare il file <a href="..\..\Test suite execution records\Test Results - ListAdapterTester.html">
 *     "Test Results - ListAdapterTester.html"</a> nella cartella "Test suite execution records"
 * @safe.exec I test sono stati eseguiti utilizzando JUnit v4.13 e hamcrest v1.3.
 * Per poter lanciare i test e' necessario inserire i file .jar di questi framework all'interno del CLASSPATH settando tale
 * variabile di ambiente.
 *
 * @note
 * Si fa notare che non e' presente il test dell'eccezione ArrayStoreException del metodo toArray(Object[]) in quanto gestito
 * da Java a tempo di runtime.
 *
 */
public class ListAdapterTester
{
    private ListAdapter list;


    /**
     * Inizializza un oggetto inserendo 3 valori in ordine 0,1,2. Al termine della creazione la lista su cui verrano eseguiti i test sara'
     * fatta nel seguente modo: <pre>
     * list
     * | 0 | 1 | 2 |
     * </pre>
     */
    @Before
    public void Initialize()
    {
        list = new ListAdapter();
        list.add(0);
        list.add(1);
        list.add(2);
    }


    /**
     * Test del metodo <code>public int size()</code>: Testo che dopo l'inizializzazione la lista abbia dimensione nulla
     * @homework.des Testo che la dimensione dopo l'inizializzazione sia zero per verificare che l'assegnazione
     * di from e to sia corretta.
     * @homework.pre la lista e' stata inizializzata come nuovo oggetto.
     * @homework.post la lista non e' stata modificata.
     * @homework.testDescr creo una nuova lista. La dimensione deve essere 0 perche' alla creazione nessun elemento
     * deve essere presente all'interno della lista
     * @homework.exp La lista ha dimensione 0.
     */
    @Test
    public void testSizeIsZeroWhenInitialized()
    {
        list = new ListAdapter();

        int size = list.size();

        assertEquals(0,size);
    }


    /**
     * Test del metodo <code>public int size()</code>: Testo che dopo l'invocazione di initialize() e l'inserimento di n = 3 valori la lista abbia dimensione 3.
     * @homework.des Testo che la dimensione sia 3 per accertarmi che il valore ritornato da size() sia coerente
     * con il numero di elementi inseriti all'interno della lista.
     * @homework.pre Lista creata e inizializzata da initialize()
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr controllo che dopo l'inserimento di 3 elementi la dimensione sia esattamente 3. Testo che
     * il valore ritornato da size() non sia sempre lo stesso e che cambi correttamente quando faccio operazioni sulla lista
     * dopo la sua inizializzazione.
     * @homework.exp La lista ha dimensione 3.
     */
    @Test
    public void testSizeIsCorrect()
    {
        int size = list.size();

        assertEquals(3, size);
    }


    /**
     * Test del metodo <code>public boolean isEmpty()</code>: Testo che appena creata una lista, questa risulti vuota.
     * @homework.des Testo che il metodo isEmpty() ritorni true quando la lista e' stata inizializzata per essere sicuro che quando la
     * dimensione della lista e' 0, isEmpty() abbia un comportamento corretto.
     * @homework.pre la lista e' stata inizializzata come nuovo oggetto.
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una nuova lista, dopo averla creata controllo che la lista sia vuota invocando il metodo isEmpty()
     * @homework.exp isEmpty() ritorna true.
     */
    @Test
    public void testIsEmptyWhenEmpty()
    {
        list = new ListAdapter();

        boolean isEmpty = list.isEmpty();

        assertTrue(isEmpty);
    }

    /**
     * Test del metodo <code>public boolean isEmpty()</code>: Testo che quando la lista contiene degli elementi, questa non risulti vuota.
     * @homework.des Testo che il metodo isEmpty() ritorni false quando la lista contiene degli elementi.
     * @homework.pre Lista creata e inizializzata con initialize()
     * @homework.post la lista ha dimensione 0.
     * @homework.testDescr Una volta inizializzata la lista con il metodo initialize() si controlla che questa
     * non sia vuota chiamando il metodo isEmpty(). Testo che isEmpty() ritorni correttamente false perche' la lista
     * non e' vuota.
     * @homework.exp isEmpty() ritorna false.
     */
    @Test
    public void testIsEmptyWhenNotEmpty()
    {
        boolean isEmpty = list.isEmpty();

        assertFalse(isEmpty);
    }


    /**
     * Test del metodo <code>public void clear()</code>: Testo che lo svuotamento di una lista precedentemente riempita faccia diventare la lista vuota.
     * @homework.des Testo che il metodo clear() rimuova tutti gli elementi dalla lista, facendola diventare vuota.
     * Si controlla cioe' che la variabile to sia pari a from.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' stata svuotata.
     * @homework.testDescr svuoto la lista usando il metodo clear(), dopo averla svuotata controllo che la lista sia vuota
     * invocando il metodo isEmpty()
     * @homework.exp isEmpty() ritorna true.
     */
    @Test
    public void testClear()
    {
        list.clear();

        assertTrue(list.isEmpty());
    }


    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, gli elementi precedentemente inseriti
     * siano presenti all'interno della lista utilizzando il metodo contains().
     * @homework.des Accertamento che determinati oggetti siano presenti all'interno della lista una volta inseriti.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr cerco l'elemento 2 precedentemente inserito dal metodo initialize() usando il metodo contains(). Siccome
     * 2 e' stato sicuramente inserito e la lista non e' stata modificata, l'elemento e' presente.
     * @homework.exp contains(2) ritorna true
     */
    @Test
    public void testContainsWhenElementIsContained()
    {
        boolean isContained = list.contains(2);

        assertTrue(isContained);
    }

    /**
     * Test del metodo <code>public boolean contains(Object o)</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, elementi che NON siano stati precedentemente inseriti
     * NON siano presenti all'interno della lista. Il metodo contains() non deve trovare l'elemento.
     * @homework.des Accertamento che determinati oggetti non siano presenti all'interno della lista se non inseriti.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr cerco l'elemento 6 che non e' stato mai inserito all'interno della lista. Siccome
     * 6 e' non e' mai stato inserito e la lista non e' stata modificata, l'elemento non e' presente.
     * @homework.exp contains(6) ritorna false.
     */
    @Test
    public void testContainsWhenElementIsNotContained()
    {
        boolean isContained = list.contains(6);

        assertFalse(isContained);
    }


    /**
     * Test del metodo <code>public int indexOf(Object o)</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, conoscendo la disposizione degli elementi
     * all'interno della lista, questa disposizione sia coerente con quanto ritornato dal metodo indexOf().
     * @homework.des Accertamento che il metodo indexOf() ritorni la posizione corretta dell'oggetto quando questo e' presente nella lista.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr cerco la posizione dell'elemento 0 precedentemente inserito dal metodo initialize() nella posizione 0. Tale ricerca
     * viene effettuata usando il metodo indexOf() che deve ritornare 0, essendo l'elemento 0 presente nella lista solo alla posizione 0.
     * @homework.exp indexOf(0) ritorna 0.
     */
    @Test
    public void testIndexOfSucceeds()
    {
        int result = list.indexOf(0);

        assertEquals(0, result);
    }

    /**
     * Test del metodo <code>public int indexOf(Object o)</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, conoscendo la disposizione degli elementi
     * all'interno della lista, l'indice ritornato dal metodo indexOf() sia l'indice della prima occorrenza dell'elemento all'interno della lista.
     * @homework.des Accertamento che il metodo indexOf() ritorni la prima occorrenza di un certo elemento quando di questo sono inseriti dei
     * duplicati all'interno della lista.
     * @homework.pre Lista inizializzata dal metodo initialize(). Inseriti nella lista dei duplicati dell'elemento da ricercare.
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Inserisco alla fine della lista (in posizione 3) e successivamente in posizione 2 l'elemento 1.
     * La lista risulta cosi' dopo l'inserimento dei valori:<pre>
     *
     *   0   1   2   3   4
     * | 0 | 1 | 1 | 2 | 1 |
     *</pre>
     * Si ricerca poi l'indice della prima occorrenza di 1 che, come e' possibile vedere dalla rappresentazione riportata, e' proprio 1.
     * @homework.exp indexOf(1) ritorna 1.
     */
    @Test
    public void testIndexOfReturnsFirstOccurrence()
    {
        list.add(1);
        list.add(2,1);

        int result = list.indexOf(1);

        assertEquals(1, result);
    }


    /**
     * Test del metodo <code>public int indexOf(Object o)</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, conoscendo la disposizione degli elementi
     * all'interno della lista, questa disposizione sia coerente con quanto ritornato dal metodo indexOf().
     * @homework.des Accertamento che il metodo indexOf(o) non trovi l'elemento o e ritorni -1 nel caso in cui l'elemento non sia presente
     * all'interno della lista.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr cerco la posizione dell'elemento 6 che non e' mai stato inserito all'interno della lista, siccome l'elemento
     * non e' mai stato inserito, questo non puo' nemmeno essere trovato, indexOf() deve quindi ritornare -1.
     * @homework.exp indexOf(6) ritorna -1.
     */
    @Test
    public void testIndexOfFails()
    {
        int result = list.indexOf(6);

        assertEquals(-1,result);
    }

    /**
     * Test del metodo <code>public int lastIndexOf(Object o)</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, conoscendo la disposizione degli elementi
     * all'interno della lista, questa disposizione sia coerente con quanto ritornato dal metodo lastIndexOf().
     * @homework.des Accertamento che il metodo lastIndexOf() ritorni la posizione corretta dell'oggetto quando questo e' presente nella lista.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr cerco la posizione dell'elemento 0 precedentemente inserito dal metodo initialize() nella posizione 0. Tale ricerca
     * viene effettuata usando il metodo lastIndexOf() che deve ritornare 0, essendo l'elemento 0 presente nella lista solo alla posizione 0.
     * @homework.exp lastIndexOf(0) ritorna 0.
     */
    @Test
    public void testLastIndexOfSucceeds()
    {
        int result = list.lastIndexOf(0);

        assertEquals(0, result);
    }

    /**
     * Test del metodo <code>public int lastIndexOf(Object o)</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, conoscendo la disposizione degli elementi
     * all'interno della lista, l'indice ritornato dal metodo lastIndexOf() sia l'indice dell'ultima occorrenza dell'elemento all'interno della lista.
     * @homework.des Accertamento che il metodo lastIndexOf() ritorni l'ultima occorrenza di un certo elemento quando di questo sono inseriti dei
     * duplicati all'interno della lista.
     * @homework.pre Lista inizializzata dal metodo initialize(). Inseriti nella lista dei duplicati dell'elemento da ricercare.
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Inserisco alla fine della lista (in posizione 3) e successivamente in posizione 2 l'elemento 1.
     * La lista risulta cosi' dopo l'inserimento dei valori: <pre>
     *
     *   0   1   2   3   4
     * | 0 | 1 | 1 | 2 | 1 |
     *</pre>
     * Si ricerca poi l'indice dell'ultima occorrenza di 1 che, come e' possibile vedere dalla rappresentazione riportata, e' 4.
     * @homework.exp indexOf(1) ritorna 4.
     */
    @Test
    public void testLastIndexOfReturnsLastOccurrence()
    {
        list.add(1);
        list.add(2,1);

        int result = list.lastIndexOf(1);

        assertEquals(4, result);
    }

    /**
     * Test del metodo <code>public int lastIndexOf(Object o)</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, conoscendo la disposizione degli elementi
     * all'interno della lista, questa disposizione sia coerente con quanto ritornato dal metodo lastIndexOf().
     * @homework.des Accertamento che il metodo indexOf(o) non trovi l'elemento o e ritorni -1 nel caso in cui l'elemento non sia presente
     * all'interno della lista.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr cerco la posizione dell'elemento 6 che non e' mai stato inserito all'interno della lista, siccome l'elemento
     * non e' mai stato inserito, questo non puo' nemmeno essere trovato, lastIndexOf() deve quindi ritornare -1.
     * @homework.exp indexOf(6) ritorna -1.
     */
    @Test
    public void testLastIndexOfFails()
    {
        int result = list.lastIndexOf(6);

        assertEquals(-1,result);
    }


    /**
     * Test del metodo <code>public Object[] toArray()</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, conoscendo la disposizione degli elementi
     * all'interno della lista, il metodo toArray() ritorni un array con gli elementi aspettati. Gli elementi devono essere gli stessi,
     * nello stesso ordine ed array e lista devono avere dunque stessa dimensione.
     * @homework.des Accertamento che il metodo toArray() istanzi un array in memoria un array e che lo riempia correttamente.
     * @homework.pre la lista e' stata inizializzata da Initialize()
     * @homework.post la lista e' rimasta invariata. L'array contiene gli elementi della lista.
     * @homework.testDescr Si crea un array mustBeEqualTo contenente gli elementi della lista, disposti nell'ordine corretto.
     * Si crea poi l'array actual invocando il metodo toArray() di list. mustBeEqualTo e actual devono essere uguali come sopra descritto.
     * @homework.exp L'array ritornato deve essere uguale ad un array con gli elementi inseriti da initialize(). L'uguaglianza viene
     * accertata mediante il metodo assertArrayEquals del framework JUnit.
     */
    @Test
    public void testToArrayWithElementsInsideList()
    {
        Object[] mustBeEqualTo = {0, 1, 2};

        Object[] actual = list.toArray();

        assertArrayEquals(mustBeEqualTo, actual);
    }


    /**
     * Test del metodo <code>public Object[] toArray()</code>: Testo che, una volta svuotata la lista, il metodo toArray() ritorni un array con nessun elemento al suo interno, ovvero un
     * array vuoto di dimensione 0. La lista e l'array devono entrambi non contenere nulla.
     * @homework.des Accertamento che il metodo toArray() istanzi un array in memoria e che lo lasci vuoto correttamente.
     * @homework.pre la lista e' stata svuotata dal metodo clear()
     * @homework.post la lista e' rimasta invariata. L'array ritornato ha lunghezza 0.
     * @homework.testDescr Si crea un array mustBeEqualTo e viene lasciato vuoto. La lista e' poi svuotata.
     * Si crea poi l'array actual invocando il metodo toArray() di list. mustBeEqualTo e actual devono essere uguali come sopra descritto.
     * @homework.exp L'array ritornato deve essere uguale ad un array vuoto. L'uguaglianza viene accertata mediante il metodo assertArrayEquals
     * del framework JUnit.
     */
    @Test
    public void testToArrayEmptyList()
    {
        Object[] mustBeEqualTo = new Object[0];
        list.clear();

        Object[] actual = list.toArray();

        assertArrayEquals(mustBeEqualTo, actual);
    }


    /**
     * Test del metodo <code>public Object[] toArray()</code>: Testo che anche se l'array ritornato viene modificato, le modifiche non hanno nessuna ripercussione sull'array originale.
     * @homework.des Accertamento che il metodo toArray() ritorni un array che non e' in alcun modo vincolato con gli elementi
     * inseriti all'interno della lista. Una lista ai suoi elementi non deve generare nessuna modifica alla lista.
     * @homework.pre Lista inizializzata dal metodo initialize()
     * @homework.post La lista e' rimasta invariata. L'array ritornato ha gli stessi elementi della lista ad eccezione dell'elemento
     * nella posizione 0.
     * @homework.testDescr Si ottiene l'array corrispondente alla lista chiamando il metodo toArray().
     * Si modifica poi l'elemento in posizione 0 dell'array inserendo la stringa "ContainedInArrayButNotInList" che sara' contenuta solo
     * nell'array ma non nella lista.
     * Si testa poi con il metodo contains che la suddetta stringa non sia presente all'interno della lista.
     * @homework.exp contains("ContainedInArrayButNotInList") ritorna false.
     */
    @Test
    public void testToArraySafeChanges()
    {
        Object[] result = list.toArray();

        result[0] = "ContainedInArrayButNotInList";

        assertFalse(list.contains("ContainedInArrayButNotInList"));
    }


    /**
     * Testa il metodo <code>public Object[] toArray(Object[])</code>: fornendo un array con abbastanza posti, ritorni l'array precedentemente fornito senza creare una nuova
     * istanza. Il controllo viene effettuato controllando gli indirizzi di memoria degli array.
     * @homework.des Accertamento che il metodo toArray() non crei un nuovo array ma ritorni quello fornito come parametro con gli elementi
     * inseriti al suo interno.
     * @homework.pre lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata. Gli elementi della lista sono stati inseriti nell'array fornito come parametro.
     * @homework.testDescr Si crea un nuovo array arr con la dimensione esattamente pari agli elementi presenti all'interno della lista.
     * Si ottiene poi l'array returned chiamando il metodo <code>toArray(Object[])</code> di ListAdapter.
     * L'indirizzo di arr e returned deve essere lo stesso. L'uguaglianza e' verificata grazie all'operatore == che su tipi riferimento
     * funziona confrontando gli indirizzi. (nel caso di JUnit tale operazione si ottiene usando il metodo <code>assertSame()</code>)
     * @homework.exp L'uguaglianza tra indirizzi ritorna <code>true</code>
     */
    @Test
    public void testToArrayGivenArraySameInstance()
    {
        Object[] arr = new Object[list.size()];

        Object[] returned = list.toArray(arr);

        assertSame(arr,returned);
    }

    /**
     * Testa il metodo <code>public Object[] toArray(Object[])</code>: fornendo un array che non abbia abbastanza posti, non ritorni l'array precedentemente fornito
     * bensi' una nuova istanza di <code>Object[]</code>. Il controllo viene effettuato controllando gli indirizzi di memoria degli array.
     * @homework.des Accertamento che il metodo toArray() crei un nuovo array e non ritorni quello fornito come parametro.
     * @homework.pre lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata. Gli elementi della lista sono stati inseriti in un nuovo array.
     * @homework.testDescr Si crea un nuovo array arr con la dimensione esattamente pari agli elementi presenti all'interno della lista.
     * Si ottiene poi l'array returned chiamando il metodo <code>toArray(Object[])</code> di ListAdapter.
     * L'indirizzo di arr e returned deve essere diverso. La disuguaglianza e' verificata grazie all'operatore == che su tipi riferimento
     * funziona confrontando gli indirizzi. (nel caso di JUnit tale operazione si ottiene usando il metodo <code>assertSame()</code>)
     * @homework.exp L'uguaglianza tra indirizzi ritorna <code>false</code>
     */
    @Test
    public void testToArrayGivenArrayNewInstance()
    {
        Object[] arr = new Object[list.size() - 1];

        Object[] returned = list.toArray(arr);

        assertNotSame(arr,returned);
    }


    /**
     * Testa il metodo <code>public Object[] toArray(Object[])</code>: fornendo un array con abbastanza posti, ritorni l'array precedentemente fornito
     * aggiungendo <code>null</code> nei posti inutilizzati. Controlla inoltre che gli elementi siano esattamente quelli della lista.
     * @homework.des Accertamento che il metodo <code>toArray(Object[])</code> inserisca <code>null</code> e che inoltre tutti gli elementi siano corretti.
     * @homework.pre lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata. Gli elementi della lista sono stati inseriti nell'array fornito come parametro. Null
     * e' stato inserito nei posti aggiuntivi della lista, se presenti.
     * @homework.testDescr Si crea un array che abbia dimensione della lista piu' un numero di posti aggiuntivo pari a <code>expectedNullElements</code>.
     * Si invoca poi il metodo <code>toArray(Object[])</code> passando come parametro tale array.
     * A partire dalla posizione pari a list.size() si contano nella variabile <code>actualNullElements</code> il numero di elementi
     * posti effettivamente a null nell'array. Devono essere tutti null, cioe' <code>actualNullElements == expectedNullElements</code>.
     * @homework.exp L'uguaglianza tra i due valori ritorna <code>true</code>
     */
    @Test
    public void testToArrayGivenArrayNullAfterElements()
    {
        int expectedNullElements = 5;
        Object[] arr = new Object[list.size() + expectedNullElements];


        arr = list.toArray(arr);


        int actualNullElements = 0;
        for(int i = list.size(); i < arr.length; i++)
            if (arr[i] == null)
                actualNullElements++;

        assertEquals(expectedNullElements,actualNullElements);
    }

    /**
     * Testa il metodo <code>public Object[] toArray(Object[])</code>: fornendo un array con abbastanza posti, si ottiene l'array precedentemente fornito
     * con che gli elementi che devono essere esattamente quelli della lista e nello stesso ordine.
     * @homework.des Accertamento che il metodo <code>toArray(Object[])</code> inserisca tutti gli elementi correttamente nel caso in cui
     * l'array di ritorno e' lo stesso fornito in precedenza.
     * @homework.pre lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata. Gli elementi della lista sono stati inseriti nell'array fornito come parametro. Null
     * e' stato inserito nei posti aggiuntivi della lista, se presenti.
     * @homework.testDescr Si invoca il metodo <code>toArray()</code> per ottenere l'array associato alla lista (si e' gia' testato
     * in un'altra istanza che tale metodo e' corretto).
     * Si crea un array che abbia dimensione della lista piu' 2 posti (per avere un caso generale).
     * Si invoca poi il metodo <code>toArray(Object[])</code> passando come parametro tale array.
     * Si controlla poi che tutti gli elementi siano uguali e nelle stesse posizioni. Si tiene traccia del procedimento mediante una
     * variabile boolean <code>isOk</code> che viene messa a <code>false</code> qualora qualcosa non dovesse essere corretto.
     * Il controllo viene fatto con un ciclo perche' l'array expected e' piu' piccolo dell'array arr che contiene anche gli elementi
     * posti a null.
     * @homework.exp <code>IsOk == true</code> alla fine del test.
     */
    @Test
    public void testToArrayGivenArraySameInstanceCorrectness()
    {
        Object[] expected = list.toArray();
        Object[] arr = new Object[list.size() + 2];


        Object[] actual = list.toArray(arr);


        boolean isOk = true;
        for(int i = 0; i < list.size(); i++)
            if(expected[i] != actual[i])
            {
                isOk = false;
                break;
            }

        assertTrue(isOk);
    }

    /**
     * Testa il metodo <code>public Object[] toArray(Object[])</code>: fornendo un array che non abbia abbastanza posti, si ottiene un nuovo
     * array che gli elementi che devono essere esattamente quelli della lista e nello stesso ordine.
     * @homework.des Accertamento che il metodo <code>toArray(Object[])</code> inserisca tutti gli elementi correttamente nel caso in cui
     * l'array di ritorno e' diverso da quello fornito come parametro.
     * @homework.pre lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata. Gli elementi della lista sono stati inseriti nell'array fornito come parametro.
     * @homework.testDescr Si invoca il metodo <code>toArray()</code> per ottenere l'array associato alla lista (si e' gia' testato
     * in un'altra istanza che tale metodo e' corretto).
     * Si crea un array che abbia dimensione della lista meno 2 posti (per avere un caso generale).
     * Si invoca poi il metodo <code>toArray(Object[])</code> passando come parametro tale array.
     * Si controlla poi che tutti gli elementi siano uguali e nelle stesse posizioni, cioe' che i due array siano uguali.
     * Tale uguaglianza viene fatta con il metodo <code>assertArrayEquals</code> di JUnit
     * @homework.exp L'uguaglianza tra array deve ritornare <code>true</code>
     */
    @Test
    public void testToArrayGivenArrayNewInstanceCorrectness()
    {
        Object[] expected = list.toArray();
        Object[] arr = new Object[list.size() - 2];

        Object[] actual = list.toArray(arr);

        assertArrayEquals(expected,actual);
    }

    /**
     * Test del metodo <code>public boolean add(Object o)</code>: testa che il metodo <code>add</code> inserisca uno e un solo
     * elemento all'interno della lista pari all'elemento passato.
     * @homework.des Corretto inserimento dell'elemento all'interno della lista. Controllo che ad esempio non sia inserito erroneamente
     * piu' volte lo stesso elemento con una sola chiamata di <code>add</code>
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista contiene 1 solo elemento in piu' alla fine della lista uguale all'elemento passato in input al metodo.
     * @homework.testDescr Si inserisce un elemento che non e' gia' presente all'interno della lista (in questo caso, 4).
     * Si controlla poi che sia stata inserita una sola istanza dell'elemento all'interno della lista. Questo viene
     * controllato ponendo <code>indexOf(4) == lastIndexOf(4)</code>, ovvero controllando che l'indice della prima e ultima
     * occorrenza dell'elemento inserito (che e' stato inserito UNA SOLA VOLTA) siano uguali.
     * @homework.exp <code>indexOf(4) == lastIndexOf(4)</code> deve ritornare <code>true</code>
     */
    @Test
    public void testAddInsertsJustOneElement()
    {
        list.add(4);

        assertSame(list.indexOf(4),list.lastIndexOf(4));
    }

    /**
     * Test del metodo <code>public boolean add(Object o)</code>: testa che il metodo <code>add</code> aumenti di 1 la dimensione
     * del contenitore dopo aver effettuato un inserimento.
     * @homework.des Corretto inserimento dell'elemento all'interno della lista. Controllo che la dimensione aumenti esattamente di 1
     * unita' e non piu' di 1 anche se l'elemento inserito e' uno soltanto.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista contiene 1 solo elemento in piu' alla fine della lista uguale all'elemento passato in input al metodo.
     * @homework.testDescr Si inserisce un elemento che non e' gia' presente all'interno della lista (in questo caso, 4).
     * Si controlla poi che la dimensione della lista dopo l'inserimento sia pari alla dimensione della lista prima dell'inserimento + 1.
     * @homework.exp La dimensione della lista deve essere aumentata di 1.
     */
    @Test
    public void testAddIncreasesSizeByOne()
    {
        int expectedSize = list.size() + 1;

        list.add(4);

        assertEquals(expectedSize, list.size());
    }

    /**Test del metodo <code>public boolean add(Object o)</code>: testa che il metodo <code>add</code> inserisca esattamente alla fine
     * del vettore e non in altre posizioni.
     * @homework.des Corretto inserimento dell'elemento all'interno della lista nella posizione aspettata (ovvero la fine della lista)
     * per avere la certezza che <code>add(o)</code> e <code>add(list.size(),o)</code> diano lo stesso risultato.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista contiene 1 solo elemento in piu' alla fine della lista uguale all'elemento passato in input al metodo.
     * @homework.testDescr Si inserisce un elemento che non e' gia' presente all'interno della lista (in questo caso, 4).
     * Si controlla poi che la prima occorrenza dell'elemento appena inserito sia nella posizione subito prima della fine della lista.
     * (se la prima occorrenza e' alla fine della lista, sono sicuro che non sia stato inserito in posizioni intermedie).
     * @homework.exp La dimensione della lista deve essere aumentata di 1 durante l'inserimento.
     */
    @Test
    public void testAddInsertsAtEndOfVector()
    {
        list.add(4);

        assertEquals(list.size() - 1, list.indexOf(4));
    }


    /**
     * Test del metodo <code>public boolean add(Object o)</code>: testa che il metodo <code>add</code> dopo l'inserimento dell'elemento
     * (che nelle condizioni di questa lista ha sempre successo) venga ritornato <code>true</code>.
     * @homework.pre Lista inizializzata dal metodo initialize()
     * @homework.post La lista contiene 1 solo elemento in piu' alla fine della lista uguale all'elemento passato in input al metodo.
     * @homework.testDescr Si aggiunge un valore all'interno della lista e si testa se il metodo <code>add(Object)</code> ha ritornato <code>true</code>
     * @homework.exp <code>add</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testAddReturnsTrue()
    {
        Object toAdd = 3;

        boolean actual = list.add(toAdd);

        assertTrue(actual);
    }


    /**Test del metodo <code>public boolean add(int index, Object o)</code>: testa che il metodo <code>add</code> inserisca nella posizione
     * indicata come parametro e non da altre parti.
     * @homework.des Corretto inserimento dell'elemento all'interno della lista nella posizione aspettata.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista contiene 1 solo elemento in piu' nella posizione indicata uguale all'elemento passato in input al metodo.
     * @homework.testDescr Si inserisce un elemento che non e' gia' presente all'interno della lista (in questo caso, <code>toAdd</code>) all'indice indicato
     * dalla variabile <code>index</code>.
     * Si controlla poi che la prima occorrenza dell'elemento appena inserito sia nella posizione <code>index</code> passata al
     * metodo <code>add</code> utilizzand il metodo <code>indexOf</code>.
     * @homework.exp <code>index</code> deve essere uguale al valore ritornato da <code>indexOf</code>
     */
    @Test
    public void testAddIndexAddsAtRightPlace()
    {
        int index = 2;
        Object toAdd = 4;

        list.add(index, toAdd);

        assertEquals(index, list.indexOf(toAdd));
    }

    /**
     * Test del metodo <code>public boolean add(int index, Object o)</code>: testa che il metodo <code>add</code> inserisca uno e un solo
     * elemento all'interno della lista pari all'elemento passato.
     * @homework.des Corretto inserimento dell'elemento all'interno della lista. Controllo che ad esempio non sia inserito erroneamente
     * piu' volte lo stesso elemento con una sola chiamata di <code>add</code>
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista contiene 1 solo elemento in piu' nella posizione indicata uguale all'elemento passato in input al metodo.
     * @homework.testDescr Si inserisce un elemento che non e' gia' presente all'interno della lista (in questo caso, "added").
     * Si controlla poi che sia stata inserita una sola istanza dell'elemento all'interno della lista. Questo viene
     * controllato ponendo <code>indexOf("added") == lastIndexOf("added")</code>, ovvero controllando che l'indice della prima e ultima
     * occorrenza dell'elemento inserito (che e' stato inserito UNA SOLA VOLTA) siano uguali.
     * @homework.exp <code>indexOf("added") == lastIndexOf("added")</code> deve ritornare <code>true</code>
     */
    @Test
    public void testAddIndexAddsJustOneElement()
    {
        list.add(2,"added");

        assertSame(list.indexOf("added"),list.lastIndexOf("added"));
        assertEquals(2,list.indexOf("added"));
    }


    /**
     * Test del metodo <code>public boolean add(int index, Object o)</code>: testa che il metodo <code>add</code> aumenti di 1 la dimensione
     * del contenitore dopo aver effettuato un inserimento.
     * @homework.des Corretto inserimento dell'elemento all'interno della lista. Controllo che la dimensione aumenti esattamente di 1
     * unita' e non piu' di 1 anche se l'elemento inserito e' uno soltanto.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista contiene 1 solo elemento in piu' nella posizione indicata della lista uguale all'elemento passato in input al metodo.
     * @homework.testDescr Si inserisce un elemento che non e' gia' presente all'interno della lista (in questo caso, "added").
     * Si controlla poi che la dimensione della lista dopo l'inserimento sia pari alla dimensione della lista prima dell'inserimento + 1.
     * @homework.exp La dimensione della lista deve essere aumentata di 1 durante l'inserimento.
     */
    @Test
    public void testAddIndexIncreasesSizeByOne()
    {
        int expectedSize = list.size() + 1;

        list.add(2, "added");

        assertEquals(expectedSize, list.size());
    }


    /**
     * Test del metodo <code>public boolean add(int index, Object o)</code>: testa che il metodo <code>add</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia minore di zero. L'inserimento
     * non puo' completarsi con successo.
     * @homework.des Nessun dato modificato dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad inserire un elemento nella posizione -1. Essendo una posizione minore di zero l'inserimento non deve
     * andare a buon fine.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testAddIndexExceptionLessThanZero()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(4, -1));
    }

    /**
     * Test del metodo <code>public boolean add(int index, Object o)</code>: testa che il metodo <code>add</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia maggiore della dimensione della lista. L'inserimento
     * non puo' completarsi con successo.
     * @homework.des Nessun dato modificato dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad inserire un elemento nella posizione list.size() + 1. Essendo una posizione maggiore della dimensione,
     * l'inserimento non deve andare a buon fine.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testAddIndexExceptionGreaterThanSize()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(4, list.size() + 1));
    }

    /**
     * Test del metodo <code>public Object get(int index)</code>: testa che il metodo <code>get</code> ritorni correttamente l'elemento
     * associato all'indice passato.
     * @homework.des Accertamento del corretto valore di ritorno del metodo {@code get}.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad ottenere un elemento nella posizione 1. Per come e' stata inizializzata la lista, nella posizione 1
     * e' presente l'elemento 1.
     * @homework.exp {@code get} ritorna 1.
     */
    @Test
    public void testGetReturnsRightElement()
    {
        Object result = list.get(1);

        assertEquals(1,result);
    }

    /**
     * Test del metodo <code>public Object get(int index)</code>: testa che il metodo <code>get</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia minore di zero. L'ispezione
     * non puo' completarsi con successo.
     * @homework.des Nessun dato modificato dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad ottenere un elemento nella posizione -1. Essendo una posizione minore di zero l'operazione non deve
     * andare a buon fine.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testGetExceptionLessThanZero()
    {
        assertThrows(IndexOutOfBoundsException.class,() -> list.get(-1));
    }

    /**
     * Test del metodo <code>public Object get(int index)</code>: testa che il metodo <code>get</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia maggiore o uguale alla dimensione della lista. L'ispezione
     * non puo' completarsi con successo.
     * @homework.des Nessun dato modificato dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad ottenere un elemento nella posizione list.size(). Essendo una posizione pari alla dimensione della lista,
     * cioe' non valida, l'ispezione non deve andare a buon fine.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testGetExceptionGreaterEqualSize()
    {
        assertThrows(IndexOutOfBoundsException.class,() -> list.get(list.size()));
    }

    /**
     * Test del metodo <code>public Object set(int index, Object o)</code>: testa che il metodo <code>set</code> ritorni l'elemento appena sostituito fornendo
     * un indice corretto. Poiche' si conosce la disposizione degli elementi l'elemento deve essere quello atteso.
     * @homework.des Accertamento che il metodo ritorni l'elemento che e' appena stato sostituito. Quindi si controlla l'accesso alla posizione corretta
     * e che l'elemento ritornato sia quello presente nella lista prima della sostituzione.
     * @homework.pre lista inizializzata dal metodo initialize()
     * @homework.post La lista e' stata modificata nella posizione 2, posizione in cui e' stato inserito l'elemento "replaced". La dimensione
     * della lista non e' cambiata.
     * @homework.testDescr Si sa che nella posizione 2 l'elemento inserito e' 2, per cui si modifica l'elemento in tale posizione invocando il
     * metodo <code>set</code> e inserendo "replaced" in posizione 2. Il valore ritornato dal metodo deve essere quello atteso.
     * @homework.exp <code>set(2,"replaced")</code> ritorna 2.
     */
    @Test
    public void testSetRightElementReturned()
    {
        int index = 2;
        Object expected = 2;

        Object actual = list.set(index,"replaced");

        assertEquals(expected,actual);
    }

    /**
     * Test del metodo <code>public Object set(int index, Object o)</code>: testa che il metodo <code>set</code> sostituisca effettivamente l'elemento dalla lista.
     * Al termine dell'operazione l'elemento sostitutito non deve essere presente all'interno della lista.
     * @homework.des Accertamento che il metodo abbia rimosso l'elemento che e' appena stato sostituito.
     * @homework.pre lista inizializzata dal metodo initialize()
     * @homework.post La lista e' stata modificata nella posizione 2, posizione in cui e' stato inserito l'elemento "replaced". La dimensione
     * della lista non e' cambiata.
     * @homework.testDescr Si modifica l'elemento in nella posizione 2 invocando il
     * metodo <code>set</code> e inserendo "replaced" in posizione 2. Il valore ritornato dal metodo deve essere stato rimosso dalla lista.
     * Si controlla dunque che la lista non contenga piu' l'elemento usando il metodo contains.
     * @homework.exp <code>contains</code> ritorna <code>false</code>
     */
    @Test
    public void testSetReturnedElementIsRemoved()
    {
        int index = 2;

        Object removed = list.set(index,"replaced");

        assertFalse(list.contains(removed));
    }

    /**
     * Test del metodo <code>public Object set(int index, Object o)</code>: testa che il metodo <code>set</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia minore di zero. La modifica
     * non puo' completarsi con successo.
     * @homework.des Nessun dato modificato dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova a modificare un elemento nella posizione -1. Essendo una posizione minore di zero l'operazione non deve
     * andare a buon fine.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testSetExceptionLessThanZero()
    {
        assertThrows(IndexOutOfBoundsException.class,() -> list.set(-1,"replaced"));
    }

    /**
     * Test del metodo <code>public Object set(int index, Object o)</code>: testa che il metodo <code>set</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia maggiore o uguale alla dimensione della lista. La modifica
     * non puo' completarsi con successo.
     * @homework.des Nessun dato modificato dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova a modificare un elemento nella posizione list.size(). Essendo una posizione pari alla dimensione della lista,
     * cioe' non valida, la modifica non deve andare a buon fine.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testSetExceptionGreaterEqualSize()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(list.size(),"replaced"));
    }

    /**
     * Test del metodo <code>public Object remove(int index)</code>: testa che il metodo <code>remove</code> ritorni l'elemento appena rimosso fornendo
     * un indice corretto. Poiche' si conosce la disposizione degli elementi l'elemento deve essere quello atteso.
     * @homework.des Accertamento che il metodo ritorni l'elemento che e' appena stato rimosso. Quindi si controlla l'accesso alla posizione corretta
     * e che l'elemento ritornato sia quello presente nella lista prima della rimozione.
     * @homework.pre lista inizializzata dal metodo initialize()
     * @homework.post La lista e' stata modificata nella posizione 2, posizione in cui e' stato rimosso l'elemento 2. La dimensione
     * della lista e' diminuita di 1 unita'.
     * @homework.testDescr Si sa che nella posizione 2 l'elemento inserito e' 2, per cui si rimuove l'elemento in tale posizione invocando il
     * metodo <code>remove</code>. Il valore ritornato dal metodo deve essere quello atteso.
     * @homework.exp <code>remove(2)</code> ritorna 2.
     */
    @Test
    public void testRemoveReturnedRightElement()
    {
        int index = 2;
        Object expected = 2;

        Object actual = list.remove(index);

        assertEquals(expected,actual);
    }

    /**
     * Test del metodo <code>public boolean remove(int index)</code>: testa che il metodo <code>remove</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia minore di zero. La rimozione
     * non puo' completarsi con successo.
     * @homework.des Nessuna rimozione  dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad inserire un elemento nella posizione -1. Essendo una posizione minore di zero la rimozione non deve
     * andare a buon fine.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveExceptionLessThanZero()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    /**
     * Test del metodo <code>public Object remove(int index)</code>: testa che il metodo <code>remove</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia maggiore o uguale alla dimensione della lista. La rimozione
     * non puo' completarsi con successo.
     * @homework.des Nessun dato modificato dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova a modificare un elemento nella posizione list.size(). Essendo una posizione pari alla dimensione della lista,
     * cioe' non valida, la rimozione non deve andare a buon fine.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveExceptionGreaterEqualSize()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la rimozione di un elemento che era presente
     * all'interno della lista venga ritornato <code>true</code>.
     * @homework.des Accertamento del funzionamento di {@code remove} quando l'elemento passato non si trova nella lista.
     * Si vuole che quando l'elemento non sia presente nella lista la rimozione non abbia successo e venga
     * ritornato il corretto valore booleano.
     * @homework.pre Lista inizializzata dal metodo initialize()
     * @homework.post E' stato rimosso un elemento dalla lista. L'elemento e' quello passato come parametro.
     * @homework.testDescr Si rimuove un valore tra quelli che era stato precedentemente inserito all'interno della lista e si testa se il metodo
     * <code>remove(Object)</code> ha ritornato <code>true</code>
     * @homework.exp <code>remove</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testRemoveTrueIfValueWasInList()
    {
        Object toRemove = 1;

        boolean wasRemoved = list.remove(toRemove);

        assertTrue(wasRemoved);
    }

    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la rimozione di un elemento che non era presente
     * all'interno della lista venga ritornato <code>false</code>.
     * @homework.des Accertamento del funzionamento di {@code remove} quando il valore passato non si trova nella lista.
     * Si vuole che quando l'elemento non sia presente nella lista la rimozione non abbia successo e venga ritornato il corretto valore booleano.
     * @homework.pre lista inizializzata dal metodo initialize()
     * @homework.post La lista e' rimasta invariata
     * @homework.testDescr Si prova a rimuovere un valore che non era stato precedentemente inserito nella lista. Siccome l'elemento
     * non era presente {@code remove} deve ritornare {@code false}.
     * @homework.exp <code>remove</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRemoveFalseIfValueWasNotInList()
    {
        Object toRemove = 6;

        boolean wasRemoved = list.remove(toRemove);

        assertFalse(wasRemoved);
    }


    /**
     * Test del metodo <code>public boolean remove(Object o)</code>: testa che il metodo <code>remove</code> dopo la tentata rimozione di un valore
     * che non era presente all'interno della lista abbia lasciato invariata la dimensione della lista.
     * @homework.des Accertamento del funzionamento di {@code remove} quando la chiave passata non si trova nella lista
     * Si vuole che quando l'elemento non sia presente nella lista la rimozione non abbia successo e che quindi
     * la dimensione sia la stessa (ad esempio sono sicuro che non venga rimosso un elemento a caso).
     * @homework.pre lista inizializzata dal metodo initialize()
     * @homework.post La lista e' rimasta invariata. La dimensione e' rimasta invariata.
     * @homework.testDescr Dopo aver ottenuto la lista, si salva la sua dimensione prima di andare ad invocare il metodo remove.
     * Si invoca il metodo remove passando un valore che non e' presente all'interno della lista.
     * @homework.exp {@code size} deve ritornare la dimensione precedentemente salvata.
     */
    @Test
    public void testRemoveFailsSameSize()
    {
        int previousSize = list.size();
        Object toRemove = 6;

        list.remove(toRemove);

        assertEquals(previousSize, list.size());
    }

    /**
     * Test del metodo {@code public void boolean remove(Object o)}: Testo che l'invocazione di {@code remove} passando un valore all'interno
     * della lista, rimuova effettivamente l'elemento dalla lista. Dopo la rimozione l'elemento non deve essere piu' presente.
     * @homework.des Accertamento del corretto funzionamento di {@code remove} con rimozione dell'elemento dalla lista a cui apparteneva.
     * @homework.pre Lista creata dal metodo {@code initialize}
     * @homework.post Il valore passato al metodo remove e' stato rimosso dalla lista. La dimensione e' diminuita di 1.
     * @homework.testDescr Si prova a rimuovere un valore (1) che e' presente all'interno della lista (e' stato inserita all'interno
     * della lista dal metodo initialize).
     * Siccome 1 e' presente nel set, dopo la rimozione l'elemento non deve piu' essere presente. Si testa cio' usando il metodo
     * contains.
     * @homework.exp {@code contains} ritorna {@code false}
     */
    @Test
    public void testRemoveRemovesElementFromList()
    {
        Object toRemove = 1;

        list.remove(toRemove);

        assertFalse(list.contains(toRemove));
    }

    /**
     * Test del metodo {@code public void boolean remove(Object o)}: Testo che l'invocazione di {@code remove} passando un valore all'interno
     * della lista, rimuova effettivamente l'elemento dalla lista. L'elemento rimosso deve essere solo la prima occorrenza. Deve esser stato
     * rimosso solo 1 elemento.
     * @homework.des Accertamento del corretto funzionamento di {@code remove} con rimozione dell'elemento dalla lista a cui apparteneva.
     * @homework.pre Lista creata dal metodo {@code initialize}
     * @homework.post Il valore passato al metodo remove e' stato rimosso dalla lista. La dimensione e' diminuita di 1.
     * @homework.testDescr Si inseriscono alla fine della lista dei duplicati dell'elemento 1.
     * Si salva l'indice della prima occorrenza di tale elemento: deve essere quella ad essere rimossa.
     * Si prova a rimuovere tale valore che e' presente all'interno della lista (e' stato inserita all'interno
     * della lista dal metodo initialize).
     * Si rimuove l'elemento e si controlla se nella posizione della prima occorrenza questo e' ancora presente o e' stato sostituito dall'elemento
     * successivo.
     * In posizione 0 ora dovrebbe esserci 1.
     * @homework.exp {@code contains} ritorna {@code false}
     */
    @Test
    public void testRemoveRemovesFirstOccurrence()
    {
        list.add(0);
        list.add(2);
        list.add(0);
        list.add(0);
        int firstIndex = list.indexOf(0);
        Object toRemove = 0;

        list.remove(toRemove);

        assertNotEquals(0,list.get(firstIndex));
        assertEquals(1,list.get(firstIndex));
    }

    /**
     * Test del metodo <code>public boolean containsAll(HCollection c)</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, gli elementi precedentemente inseriti
     * siano presenti all'interno della lista utilizzando il metodo containsAll().
     * @homework.des Accertamento che determinati oggetti siano presenti all'interno della lista una volta inseriti.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una collezione coll di tipo HCollection in cui inserisco 2 elementi (1 e 2). Gli elementi erano stati precedentemente inseriti
     * dal metodo initialize(), quindi sono sicuramente presenti.
     * Si invoca quindi il metodo <code>containsAll</code> passando come parametro la collezione appena creata.
     * @homework.exp <code>containsAll(coll)</code> ritorna <code>true</code>
     */
    @Test
    public void testContainsAllSucceeds()
    {
        HCollection coll = new ListAdapter();
        coll.add(1);
        coll.add(2);

        boolean result = list.containsAll(coll);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean containsAll(HCollection c)</code>: Testo che, una volta inizializzata la lista con degli elementi al suo interno, elementi che NON siano stati precedentemente inseriti
     * NON siano presenti all'interno della lista. Il metodo <code>containsAll()</code> non deve trovare l'elemento. Si prende il caso generale
     * in cui la collezione contiene sia elementi presenti nella lista che non.
     * @homework.des Accertamento che determinati oggetti non siano presenti all'interno della lista se non inseriti.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una collezione coll di tipo HCollection in cui inserisco 3 elementi (1,2,3). Gli elementi 1 e 2 erano stati precedentemente inseriti
     * dal metodo initialize(), quindi sono sicuramente presenti. L'elemento 3 invece non e' mai stato inserito nella lista e questa non e' stata modificata,
     * per cui l'elemento non e' presente.
     * Si invoca quindi il metodo <code>containsAll</code> passando come parametro la collezione appena creata.
     * @homework.exp <code>containsAll(coll)</code> ritorna <code>false</code>.
     */
    @Test
    public void testContainsAllFails()
    {
        HCollection coll = new ListAdapter();
        coll.add(1);
        coll.add(2);
        coll.add(3);

        boolean result = list.containsAll(coll);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public Object containsAll(HCollection coll)</code>: testa che il metodo <code>containsAll</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la collezione passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>containAll</code> qualora la collezione passata non sia valida.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova a controllare che la lista contenga una HCollection coll che pero' non fa riferimento ad alcuna collezione.
     * L'operazione deve fallire perche' <code>null</code> non e' una collezione valida.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testContainsAllExceptionNullCollection()
    {
        HCollection coll = null;

        assertThrows(NullPointerException.class, () -> list.containsAll(coll));
    }

    /**
     * Test del metodo <code>public boolean containsAll(HCollection c)</code>: Testo il caso limite in cui, nel caso in cui la lista sia gia'
     * stata riempita, la collezione vuota (cioe' con nessun elemento) sia contenuta all'interno della lista.
     * @homework.des Accertamento che il metodo funzioni anche nel caso limite di collezione vuota e non lanci eccezioni.
     * Si ricorda che il metodo containsAll ritorna <code>false</code> nel caso in cui almeno 1 elemento della collezione non e' presente nella lista,
     * siccome la collezione non ha elementi, il metodo deve ritornare <code>true</code>.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una collezione coll di tipo HCollection che viene svuotata subito dopo l'inizializzazione per essere
     * sicuri che non contenga elementi.
     * Si invoca quindi il metodo <code>containsAll</code> passando come parametro la collezione appena creata.
     * @homework.exp <code>containsAll(coll)</code> ritorna <code>true</code>
     */
    @Test
    public void testContainsAllEmptyCollection()
    {
        HCollection coll = new ListAdapter();
        coll.clear();

        boolean result = list.containsAll(coll);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean containsAll(HCollection c)</code>: Testo il caso limite in cui, nel caso in cui la lista sia gia'
     * vuota, una collezione con degli elementi validi al suo interno NON sia contenuta nella lista principale.
     * @homework.des Accertamento che il metodo funzioni anche nel caso limite di lista vuota e non lanci eccezioni.
     * Si ricorda che il metodo containsAll ritorna <code>false</code> nel caso in cui almeno 1 elemento della collezione non e' presente nella lista,
     * siccome la lista non ha elementi, il metodo deve ritornare <code>false</code>.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una collezione coll di tipo HCollection che viene riempita usando il metodo createColleciton(). Svuoto poi
     * la lista in modo che questa non contenga elementi.
     * Si invoca quindi il metodo <code>containsAll</code> passando come parametro la collezione appena creata. La lista non contiene
     * elementi per cui non puo' nemmeno contenere la collezione passata.
     * @homework.exp <code>containsAll(coll)</code> ritorna <code>false</code>
     */
    @Test
    public void testContainsAllEmptyList()
    {
        HCollection coll = createCollection();
        list.clear();

        boolean result = list.containsAll(coll);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public boolean containsAll(HCollection c)</code>: Testo il caso limite in cui sia la collezione che la lista siano
     * vuote.
     * @homework.des Accertamento che il metodo funzioni anche nel caso limite di collezione e lista vuota e non lanci eccezioni.
     * Siccome sia la lista che la collezione non contengono elementi non ci sono elementi della collezione che non son presenti nella lista
     * originale, per cui <code>containsAll</code> deve ritornare false.
     * @homework.pre Lista inizializzata dal metodo initialize().
     * @homework.post la lista e' rimasta invariata.
     * @homework.testDescr Creo una collezione coll di tipo HCollection che viene subito svuotata. Svuoto poi
     * la lista in modo che questa non contenga elementi.
     * Si invoca quindi il metodo <code>containsAll</code> passando come parametro la collezione appena creata. La lista non contiene
     * elementi e nemmeno la collezione per cui la lista contiene la collezione.
     * @homework.exp <code>containsAll(coll)</code> ritorna <code>false</code>
     */
    @Test
    public void testContainsAllBothEmpty()
    {
        HCollection coll = new ListAdapter();
        coll.clear();
        list.clear();

        boolean result = list.containsAll(coll);

        assertTrue(result);
    }


    /**
     * Test del metodo <code>public boolean addAll(HCollection c)</code>: testa che il metodo <code>addAll</code> aggiunga gli elementi
     * alla fine della lista.
     * @homework.des Dal momento che il metodo <code>addAll(HCollection c)</code>
     * richiama semplicemente il metodo <code>addAll(int index, HCollection c)</code> ritornando il valore ritornato da quest'ultimo
     * senza effettuare nessuna modifica, si controlla semplicemente che gli elementi siano stati inseriti correttamente alla fine della lista.
     * Si testano in seguito le funzionalita' di addAll con l'indice.
     * Dal momento che si richiama l'operazione <code>add</code> per l'inserimento del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize()
     * @homework.post Gli elementi sono stati aggiunti alla lista. La dimensione della lista e' aumentata di 3 (dimensione della collezione ritornata
     * da <code>createCollection</code>).
     * @homework.testDescr Si crea una collezione con la solita funzione, si creano poi i parametri da passare alla funzione <code>checkAddAll</code>
     * che ha lo scopo di controllare che gli elementi alla fine dell'addAll siano tutti nella posizione corretta: sia quelli che si trovano
     * prima della posizione in cui e' avvenuto l'inserimento, quelli appena inseriti e quelli dopo la posizione di inserimento.
     * Per verificare che sia corretto si passa al metodo di controllo la posizione di inserimento pari alla dimensione della lista (cioe' l'ultima posizione
     * valida per poter effettuare l'inserimento).
     * Si inserisce la collezione nella lista.
     * Si controlla usando la funzione checkAddAll che ritorna <code>true</code> nel caso in cui l'inserimento sia avvenuto correttamente.
     * @homework.exp <code>checkAddAll</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testAddAllAppendCorrectly()
    {
        HCollection coll = createCollection();
        Object[] values = list.toArray();
        int startingPos = list.size();

        list.addAll(coll);

        assertTrue(checkAddAll(values,startingPos,coll));
    }

    /**
     * Test del metodo <code>public boolean addAll(int index, HCollection c)</code>: testa che il metodo <code>addAll</code> con indice
     * aggiunga gli elementi all'inizio della lista
     * @homework.des Inserimento corretto degli elementi nella posizione corretta.
     * Dal momento che si richiama l'operazione <code>add</code> per l'inserimento del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize()
     * @homework.post Gli elementi sono stati aggiunti alla lista. La dimensione della lista e' aumentata di 3 (dimensione della collezione ritornata
     * da <code>createCollection</code>).
     * @homework.testDescr Si crea una collezione con la solita funzione, si creano poi i parametri da passare alla funzione <code>checkAddAll</code>
     * che ha lo scopo di controllare che gli elementi alla fine dell'addAll siano tutti nella posizione corretta: sia quelli che si trovano
     * prima della posizione in cui e' avvenuto l'inserimento, quelli appena inseriti e quelli dopo la posizione di inserimento.
     * Per verificare che sia corretto si passa al metodo di controllo la posizione di inserimento pari alla posizione 0 (la prima valida nella lista).
     * Si inserisce la collezione nella lista alla posizione 0.
     * Si controlla usando la funzione checkAddAll che ritorna <code>true</code> nel caso in cui l'inserimento sia avvenuto correttamente.
     * @homework.exp <code>checkAddAll</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testAddAllIndexFront()
    {
        HCollection coll = createCollection();
        int startingPos = 0;
        Object[] values = list.toArray();

        list.addAll(startingPos,coll);

        assertTrue(checkAddAll(values,startingPos,coll));
    }

    /**
     * Test del metodo <code>public boolean addAll(int index, HCollection c)</code>: testa che il metodo <code>addAll</code> con indice
     * aggiunga gli elementi in una posizione intermedia della lista.
     * @homework.des Inserimento corretto degli elementi nella posizione corretta.
     * Dal momento che si richiama l'operazione <code>add</code> per l'inserimento del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize()
     * @homework.post Gli elementi sono stati aggiunti alla lista. La dimensione della lista e' aumentata di 3 (dimensione della collezione ritornata
     * da <code>createCollection</code>).
     * @homework.testDescr Si crea una collezione con la solita funzione, si creano poi i parametri da passare alla funzione <code>checkAddAll</code>
     * che ha lo scopo di controllare che gli elementi alla fine dell'addAll siano tutti nella posizione corretta: sia quelli che si trovano
     * prima della posizione in cui e' avvenuto l'inserimento, quelli appena inseriti e quelli dopo la posizione di inserimento.
     * Per verificare che sia corretto si passa al metodo di controllo la posizione di inserimento pari alla posizione 1 (una qualsiasi posizione
     * interna alla lista).
     * Si inserisce la collezione nella lista all posizione 1 (caso generale).
     * Si controlla usando la funzione checkAddAll che ritorna <code>true</code> nel caso in cui l'inserimento sia avvenuto correttamente.
     * @homework.exp <code>checkAddAll</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testAddAllIndexMiddle()
    {
        HCollection coll = createCollection();
        int startingPos = 1;
        Object[] values = list.toArray();

        list.addAll(startingPos,coll);

        assertTrue(checkAddAll(values,startingPos,coll));
    }

    /**
     * Test del metodo <code>public boolean addAll(int index, HCollection c)</code>: testa che il metodo <code>addAll</code> con indice
     * aggiunga gli elementi alla fine della lista.
     * @homework.des Inserimento corretto degli elementi nella posizione corretta.
     * Dal momento che si richiama l'operazione <code>add</code> per l'inserimento del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo. Testo che l'indice pari alla dimensione della lista sia corretto e non sollevi
     * eccezione.
     * @homework.pre La lista e' stata inizializzata da intialize()
     * @homework.post Gli elementi sono stati aggiunti alla lista. La dimensione della lista e' aumentata di 3 (dimensione della collezione ritornata
     * da <code>createCollection</code>).
     * @homework.testDescr Si crea una collezione con la solita funzione, si creano poi i parametri da passare alla funzione <code>checkAddAll</code>
     * che ha lo scopo di controllare che gli elementi alla fine dell'addAll siano tutti nella posizione corretta: sia quelli che si trovano
     * prima della posizione in cui e' avvenuto l'inserimento, quelli appena inseriti e quelli dopo la posizione di inserimento.
     * Per verificare che sia corretto si passa al metodo di controllo la posizione di inserimento pari alla dimensione della lista (cioe' l'ultima posizione
     * valida per poter effettuare l'inserimento).
     * Si inserisce la collezione nella lista alla posizione pari a list.size().
     * Si controlla usando la funzione checkAddAll che ritorna <code>true</code> nel caso in cui l'inserimento sia avvenuto correttamente.
     * @homework.exp <code>checkAddAll</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testAddAllIndexBack()
    {
        HCollection coll = createCollection();
        int startingPos = list.size();
        Object[] values = list.toArray();

        list.addAll(startingPos,coll);

        assertTrue(checkAddAll(values, startingPos, coll));
    }

    /**
     * Test del metodo <code>public boolean addAll(int index, HCollection c)</code>: testa che il metodo <code>addAll</code> con indice
     * ritorni <code>true</code> nel caso in cui l'inserimento abbia avuto successo e abbia modificato la lista.
     * @homework.des Accertamento del corretto valore di ritorno
     * Dal momento che si richiama l'operazione <code>add</code> per l'inserimento del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize()
     * @homework.post Gli elementi sono stati aggiunti alla lista. La dimensione della lista e' aumentata di 3 (dimensione della collezione ritornata
     * da <code>createCollection</code>).
     * @homework.testDescr Si crea una collezione con la funzione <code>createCollection</code>. Si inseriscono poi gli elementi all'interno
     * della lista nella posizione 1 (una posizione qualsiasi, si e' gia' verificato che l'inserimento ha successo in qualsiasi posizione della lista).
     * Siccome la collezione non e' vuota, deve aver inserito elementi all'interno della lista e quindi modificato la lista.
     * @homework.exp <code>list.addAll(1,coll)</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testAddAllIndexReturnsTrueIfModified()
    {
        HCollection coll = createCollection();

        boolean insertionSuccessful = list.addAll(1,coll);

        assertTrue(insertionSuccessful);
    }

    /**
     * Test del metodo <code>public boolean addAll(int index, HCollection c)</code>: testa che il metodo <code>addAll</code> con indice
     * ritorni <code>false</code> nel caso in cui la collezione inserita nella lista non abbia nessun elemento (quindi la lista
     * non e' stata modificata).
     * @homework.des Accertamento del corretto valore di ritorno
     * Dal momento che si richiama l'operazione <code>add</code> per l'inserimento del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si crea una collezione vuota. Si inseriscono poi gli elementi all'interno
     * della lista nella posizione 1 (una posizione qualsiasi, si e' gia' verificato che l'inserimento ha successo in qualsiasi posizione della lista).
     * Siccome la collezione e' vuota, non sono stati inseriti elementi all'interno della lista e quindi la lista non e' stata modificata.
     * @homework.exp <code>list.addAll(1,coll)</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testAddAllIndexReturnsFalseIfEmptyCollection()
    {
        HCollection coll = new ListAdapter();
        coll.clear();

        boolean insertionSuccessful = list.addAll(1,coll);

        assertFalse(insertionSuccessful);
    }

    /**
     * Test del metodo <code>public Object addAll(int index, HCollection coll)</code>: testa che il metodo <code>addAll</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la collezione passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>addAll</code> qualora la collezione passata non sia valida.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad inserire nella lista una collezione HCollection coll che pero' non fa riferimento ad alcuna collezione.
     * L'operazione deve fallire perche' <code>null</code> non e' una collezione valida.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testAddAllIndexExceptionIfCollectionIsNull()
    {
        HCollection coll = null;

        assertThrows(NullPointerException.class, () -> list.addAll(1, coll));
    }

    /**
     * Test del metodo <code>public boolean addAll(int index, HCollection c)</code>: testa che il metodo <code>addAll</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia minore di zero. L'inserimento
     * non puo' completarsi con successo.
     * @homework.des Nessun dato modificato dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad inserire una collezione di elementi nella posizione -1. Essendo una posizione minore di zero l'inserimento non deve
     * andare a buon fine.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testAddAllExceptionIndexLessThanZero()
    {
        HCollection coll = new ListAdapter();

        assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(-1,coll));
    }

    /**
     * Test del metodo <code>public boolean addAll(int index, HCollection c)</code>: testa che il metodo <code>addAll</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia maggiore della dimensione della lista. L'inserimento
     * non puo' completarsi con successo.
     * @homework.des Nessun dato modificato dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad inserire una collezione di elementi nella posizione list.size() + 1. Essendo una posizione maggiore della dimensione,
     * l'inserimento non deve andare a buon fine.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testAddAllExceptionIndexGreaterSize()
    {
        HCollection coll = new ListAdapter();

        assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(list.size() + 1, coll));
    }

    /**
     * Test del metodo <code>public boolean removeAll(HCollection coll)</code>: testo che il metodo <code>removeAll</code> rimuova
     * tutti gli elementi presenti all'interno della collezione dalla lista.
     * @homework.des Testo che la rimozione degli elementi da una collezione (anche se questi sono in ordine diverso dagli elementi presenti nella
     * lista) rimuova correttamente tutti gli elementi.
     * <b>ATTENZIONE:</b> Si rimuovono TUTTE le occorrenze di un elemento dall'interno della lista. Quindi nel caso in cui un elemento
     * sia presente 3 volte nella lista ma solo 1 volta nella collezione, tutte e 3 le occorrenze all'interno della lista saranno rimosse.
     * @homework.pre Lista inizializzata dal metodo initialize(). Collection coll correttamente inizializzata.
     * @homework.post La lista e' vuota.
     * @homework.testDescr Si crea una collezione con gli stessi elementi inseriti all'interno della lista dal metodo initialize().
     * Siccome la collezione contiene tutti gli elementi della lista (anche se in ordine sparso), removeAll deve lasciare la lista vuota, si testa
     * richiamando il metodo <code>size()</code>
     * @homework.exp <code>size()</code> deve ritornare 0.
     */
    @Test
    public void testRemoveAllRemovesAllElements()
    {
        HCollection coll = new ListAdapter();
        coll.add(1);
        coll.add(0);
        coll.add(2);

        list.removeAll(coll);

        assertEquals(0,list.size());
    }

    /**
     * Test del metodo <code>public boolean removeAll(HCollection c)</code>: testa che il metodo <code>removeAll</code>
     * ritorni <code>true</code> nel caso in cui la rimozione abbia avuto successo e abbia modificato la lista.
     * @homework.des Accertamento del corretto valore di ritorno
     * Dal momento che si richiama l'operazione <code>remove</code> per la rimozione del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize()
     * @homework.post Gli elementi sono stati aggiunti alla lista. La dimensione della lista e' diminuita di 2.
     * @homework.testDescr Si crea una collezione e si inseriscono al suo interno degli elementi presenti all'interno della lista
     * (1,2). Si rimuovono poi gli elementi dalla lista.
     * Siccome la collezione non e' vuota, deve aver rimosso gli elementi dalla lista, questa deve risultare modificata.
     * @homework.exp <code>list.removeAll(coll)</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRemoveAllReturnsTrueIfSucceeds()
    {
        HCollection coll = new ListAdapter();
        coll.add(1);
        coll.add(2);

        boolean result = list.removeAll(coll);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean removeAll(HCollection c)</code>: testa che il metodo <code>removeAll</code>
     * ritorni <code>false</code> nel caso in cui la collezione che si vuole rimuovere dalla lista non abbia nessun elemento elemento in
     * comune con la lista stessa (quindi la lista non viene modificata).
     * @homework.des Accertamento del corretto valore di ritorno
     * Dal momento che si richiama l'operazione <code>remove</code> per la rimozione del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si crea una collezione con il metodo <code>createCollection</code>. Si rimuovono poi gli elementi
     * dalla lista.
     * Siccome la collezione non contiene elementi in comune con la lista, non sono stati rimossi elementi dalla lista e quindi
     * la lista non e' stata modificata.
     * @homework.exp <code>list.removeAll(coll)</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRemoveAllReturnsFalseIfFails()
    {
        HCollection coll = createCollection();

        boolean result = list.removeAll(coll);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public boolean removeAll(HCollection coll)</code>: testo che il metodo <code>removeAll</code> rimuova
     * tutti gli elementi presenti all'interno della collezione dalla lista. Verranno rimossi solo gli elementi che sono presenti sia
     * nella collezione che nella lista.
     * @homework.des Testo che la rimozione degli elementi da una collezione (anche se questi sono in ordine diverso dagli elementi presenti nella
     * lista) rimuova correttamente tutti gli elementi anche nel caso in cui la collezione contenga elementi non presenti all'interno della lista.
     * <b>ATTENZIONE:</b> Si rimuovono TUTTE le occorrenze di un elemento dall'interno della lista. Quindi nel caso in cui un elemento
     * sia presente 3 volte nella lista ma solo 1 volta nella collezione, tutte e 3 le occorrenze all'interno della lista saranno rimosse.
     * @homework.pre Lista inizializzata dal metodo initialize(). Collection coll correttamente inizializzata.
     * @homework.post La lista e' vuota.
     * @homework.testDescr Si crea una collezione con il metodo <code>createCollection</code> e si inserisce un elemento presente anche all'interno
     * della lista (0).
     * Siccome la collezione contiene solo 1 elemento in comune con la lista, removeAll deve rimuovere soltanto 1 elemento, ovvero lo 0.
     * Si testa che dopo la rimozione l'elemento rimosso non sia piu' presente con il metodo contains()
     * @homework.exp <code>contains(0)</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRemoveAllMixedElements()
    {
        HCollection coll = createCollection();
        coll.add(0);

        list.removeAll(coll);

        assertFalse(list.contains(0));
    }


    /**
     * Test del metodo <code>public boolean removeAll(HCollection coll)</code>: testo che il metodo <code>removeAll</code> rimuova
     * tutti gli elementi presenti all'interno della collezione dalla lista. Devono essere rimossi pure tutti i duplicati.
     * @homework.des Testo che la rimozione degli elementi da una collezione (anche se questi sono in ordine diverso dagli elementi presenti nella
     * lista) rimuova correttamente tutti gli elementi anche nel caso di duplicati.
     * <b>ATTENZIONE:</b> Si rimuovono TUTTE le occorrenze di un elemento dall'interno della lista. Quindi nel caso in cui un elemento
     * sia presente 3 volte nella lista ma solo 1 volta nella collezione, tutte e 3 le occorrenze all'interno della lista saranno rimosse.
     * @homework.pre Lista inizializzata dal metodo initialize(). Collection coll correttamente inizializzata.
     * @homework.post La lista e' vuota.
     * @homework.testDescr Si crea una collezione uguale alla lista originale e che si inserisce piu' volte all'interno della lista originale
     * cosi' da creare tante copie della lista di partenza ed avere elementi duplicati.
     * Alla fine degli inserimenti i due oggetti saranno fatti nel seguente modo:<pre>
     * list:
     * | 0 | 1 | 2 | 0 | 1 | 2 | 0 | 1 | 2 | 0 | 1 | 2 |
     *
     * coll:
     * | 0 | 1 | 2 |
     *</pre>
     * Si rimuove coll da list: tutti gli elementi di list devono essere rimossi. Si controlla che la rimozione sia avvenuta controllando che la dimensione sia 0.
     *
     * @homework.exp la dimensione della lista e' 0.
     */
    @Test
    public void testRemoveAllRemovesDuplicates()
    {
        HCollection coll = new ListAdapter(list);
        list.addAll(coll);
        list.addAll(coll);
        list.addAll(coll);

        list.removeAll(coll);

        assertEquals(0, list.size());
    }


    /**
     * Test del metodo <code>public Object removeAll(HCollection coll)</code>: testa che il metodo <code>removeAll</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la collezione passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>containAll</code> qualora la collezione passata non sia valida.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova a rimuovere una collezione HCollection coll che pero' non fa riferimento ad alcuna collezione.
     * L'operazione deve fallire perche' <code>null</code> non e' una collezione valida.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveAllExceptionIfCollectionIsNull()
    {
        HCollection coll = null;

        assertThrows(NullPointerException.class, () -> list.removeAll(coll));
    }

    /**
     * Test del metodo <code>public boolean retainAll(HCollection c)</code>: testa che il metodo <code>retainAll</code>
     * ritorni <code>true</code> nel caso in cui la rimozione abbia avuto successo e abbia modificato la lista.
     * @homework.des Accertamento del corretto valore di ritorno
     * Dal momento che si richiama l'operazione <code>remove</code> per la rimozione del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize(). Collezione correttamente inizializzata.
     * @homework.post Gli elementi sono stati rimossi alla lista. La dimensione della lista e' 1 (numero di elementi in comune tra la collezione e
     * la lista).
     * @homework.testDescr Si crea una collezione con la funzione <code>createCollection</code> e si aggiunge il valore 0 che e' presente
     * all'interno della lista, si ottiene quindi una lista con un mix di elementi tra presenti e non presenti.
     * Si rimuovono poi gli elementi dalla lista mantenendo solo lo 0.
     * Siccome la collezione non e' vuota, deve aver rimosso gli elementi dalla lista, questa deve risultare modificata.
     * @homework.exp <code>list.retainAll(coll)</code> deve ritornare <code>true</code>.
     */
    @Test
    public void testRetainAllReturnsTrueIfChanged()
    {
        HCollection coll = createCollection();
        coll.add(0);

        boolean result = list.retainAll(coll);

        assertTrue(result);
    }

    /**
     * Test del metodo <code>public boolean retainAll(HCollection c)</code>: testa che il metodo <code>retainAll</code>
     * lasci un solo elemento all'interno della lista quando tra la lista e la collezione e' presente solo un elemento in comune.
     * @homework.des Accertamento del corretto funzionamento del metodo.
     * Dal momento che si richiama l'operazione <code>remove</code> per la rimozione del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize(). Collezione correttamente inizializzata.
     * @homework.post Son stati rimossi tutti gli elementi ad eccezione dello 0, la dimensione della lista e' 1.
     * @homework.testDescr Si crea una collezione con la funzione <code>createCollection</code> e si aggiunge il valore 0 che e' presente
     * all'interno della lista, si ottiene quindi una lista con un mix di elementi tra presenti e non presenti.
     * Si rimuovono poi gli elementi dalla lista.
     * Siccome la collezione contiene solo un elemento in comune con la lista, la lista deve avere solo un elemento, ovvero lo 0.
     * @homework.exp dimensione della lista pari a 1 e l'elemento in posizione 0 deve essere proprio 0 (l'elemento in comune tra lista e collezione)
     */
    @Test
    public void testRetainAllSucceeds()
    {
        HCollection coll = createCollection();
        coll.add(0);

        list.retainAll(coll);

        assertTrue(1 == list.size() && list.get(0).equals(0));
    }


    /**
     * Test del metodo <code>public boolean retainAll(HCollection c)</code>: testa che il metodo <code>retainAll</code>
     * non modifichi la lista nel caso in cui tutti gli elementi siano in comune tra lista e collezione.
     * @homework.des Accertamento del corretto funzionamento del metodo.
     * Dal momento che si richiama l'operazione <code>remove</code> per la rimozione del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si crea una collezione inserendo tutti gli elementi inseriti all'interno della lista da parte
     * del metodo initialize(). Si mantengono nella lista solo gli elementi presenti all'interno della collezione.
     * Siccome la collezione coincide con la lista, non sono stati rimossi elementi dalla lista e quindi
     * la lista non e' stata modificata. Si controlla cio' verificando che la dimensione della lista (ottenuta con size()) rimanga invariata.
     * @homework.exp La dimensione della lista e' 3. (pari a quella prima della chiamata al metodo)
     */
    @Test
    public void testRetainAllDoesNotModifyList()
    {
        HCollection coll = new ListAdapter(list);

        list.retainAll(coll);

        assertEquals(3, list.size());
    }

    /**
     * Test del metodo <code>public boolean retainAll(HCollection c)</code>: testa che il metodo <code>retainAll</code>
     * ritorni <code>false</code> nel caso in cui la collezione che si vuole mantenere nella lista abbia tutti gli elementi in
     * comune con la lista originale (quindi la lista non viene modificata).
     * @homework.des Accertamento del corretto valore di ritorno
     * Dal momento che si richiama l'operazione <code>remove</code> per la rimozione del singolo elemento, si assicura che la dimensione della lista
     * venga modificata correttamente anche senza controllarlo.
     * @homework.pre La lista e' stata inizializzata da intialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si crea una collezione inserendo tutti gli elementi inseriti all'interno della lista da parte
     * del metodo initialize(). Si mantengono nella lista solo gli elementi presenti all'interno della collezione.
     * Siccome la collezione coincide con la lista, non sono stati rimossi elementi dalla lista e quindi
     * la lista non e' stata modificata.
     * @homework.exp <code>list.retainAll(coll)</code> deve ritornare <code>false</code>.
     */
    @Test
    public void testRetainAllReturnsFalseIfListNotChanged()
    {
        HCollection coll = new ListAdapter();
        coll.add(0);
        coll.add(1);
        coll.add(2);

        list.retainAll(coll);

        assertFalse(list.retainAll(coll));
    }

    /**
     * Test del metodo <code>public Object retainAll(HCollection coll)</code>: testa che il metodo <code>retainAll</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la collezione passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>containAll</code> qualora la collezione passata non sia valida.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova a mantenere all'interno della lista una collezione HCollection coll che pero' non fa riferimento ad alcuna collezione.
     * L'operazione deve fallire perche' <code>null</code> non e' una collezione valida.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRetainAllExceptionIfCollectionIsNull()
    {
        HCollection coll = null;

        assertThrows(NullPointerException.class, () -> list.retainAll(coll));
    }

    /**
     * Test del metodo <code>public boolean subList(int from, int to)</code>: testa che il metodo <code>subList</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione from sia maggiore di to. La creazione della nuova
     * sottolista non puo' completarsi con successo.
     * @homework.des Nessuna lista valida e' stata ritornata.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad ottenere una lista passando una posizione iniziale che e' maggiore di quella finale, la lista pertanto
     * non puo' essere valida.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testSubListExceptionIfFromGreaterThanTo()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(2,1));
    }

    /**
     * Test del metodo <code>public boolean subList(int from, int to)</code>: testa che il metodo <code>subList</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione from sia minore di zero. La creazione della nuova
     * sottolista non puo' completarsi con successo.
     * @homework.des Nessuna lista valida e' stata ritornata.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad ottenere una lista passando una posizione iniziale negativa, non corrisponde ad una lista valida.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testSubListExceptionIfFromLessThanZero()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-1,1));
    }

    /**
     * Test del metodo <code>public boolean subList(int from, int to)</code>: testa che il metodo <code>subList</code> lanci eccezione
     * <code>IndexOutOfBoundsException</code> nel caso in cui la posizione passata sia maggiore della dimensione della lista. La creazione della nuova
     * sottolista non puo' completarsi con successo.
     * @homework.des Nessun dato modificato dalla lista che pertanto rimarra' come prima dell'invocazione nel caso di accesso a posizioni incorrette.
     * Si testa il corretto controllo degli indici from e to validi.
     * @homework.pre Lista inizializzata con il metodo initialize()
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si prova ad ottenere una lista passando una posizione finale superiore alla dimensione della lista, non corrisponde ad una lista valida.
     * @homework.exp L'eccezione IndexOutOfBoundsException deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testSubListExceptionIfToGreaterThanSize()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(2,list.size() + 1));
    }

    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che 2 liste uguali abbiano lo stesso hashCode
     * @homework.des due liste uguali hanno lo stesso codice hash
     * @homework.pre 2 liste opportunamente create uguali tra loro secondo i parametri definiti da equals (stessi elementi
     * nello stesso ordine).
     * @homework.post le liste sono rimaste invariate
     * @homework.testDescr Creazione di un nuovo oggetto ListAdapter. Ad entrambe le liste esistenti, aggiungo gli stessi elementi definiti dal
     * metodo initialize(). Controllo che le due liste abbiano lo stesso hashCode.
     * @homework.exp le due liste hanno lo stesso hashCode.
     */
    @Test
    public void testHashCodeSucceeds()
    {
        ListAdapter la= new ListAdapter();
        la.add(0);
        la.add(1);
        la.add(2);

        int hash1 = list.hashCode();
        int hash2 = la.hashCode();

        assertEquals(hash1,hash2);
    }

    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che 2 liste diverse anche solo nell'ordine degli elementi abbiano
     * 2 hashCode diversi
     * @homework.des due liste diverse hanno codici hash diversi. Si testa semplicemente con l'ordine diverso. Se 2 liste con elementi
     * in ordine diverso sono diverse, allora anche 2 liste con elementi diversi saranno sicuramente diverse.
     * @homework.pre 2 liste diverse secondo quanto definito da equals.
     * @homework.post le liste sono rimaste invariate
     * @homework.testDescr Creazione di un nuovo oggetto ListAdapter. Ad entrambe le liste esistenti, aggiungo gli stessi elementi definiti dal
     * metodo initialize() ma in ordine diverso. Controllo che le due liste abbiano lo stesso hashCode.
     * @homework.exp le due liste NON hanno lo stesso hashCode.
     */
    @Test
    public void testHashCodeFails()
    {
        ListAdapter la= new ListAdapter();
        la.add(1);
        la.add(2);
        la.add(0);

        int hash1 = la.hashCode();
        int hash2 = list.hashCode();

        assertNotEquals(hash1,hash2);
    }

    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che una lista e una sua sottolista con gli stessi elementi e nello stesso ordine
     * abbiano 2 hashCode uguali.
     * @homework.des due liste uguali hanno lo stesso codice hash
     * @homework.pre Una lista e una sua sottolista coincidente con la lista principale.
     * @homework.post La lista e' rimasta invariata
     * @homework.testDescr Creazione di una sottolista della lista principale con indici from = 0, to = list.size(). La subList cosi' ottenuta
     * ha gli stessi elementi della lista principale. Le due liste devono quindi avere lo stesso codice hash.
     * @homework.exp le due liste hanno lo stesso hashCode.
     */
    @Test
    public void testHashCodeSucceedsWithSublist()
    {
        HCollection coll = list.subList(0,list.size());

        int hash1 = coll.hashCode();
        int hash2 = list.hashCode();

        assertEquals(hash1,hash2);
    }

    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che una lista e una sua sottolista con elementi in meno rispetto alla lista originale
     * abbiano 2 hashCode diversi.
     * @homework.des due liste diverse hanno lo codici hash diversi.
     * @homework.pre Una lista e una sua sottolista non conincidente con la lista principale.
     * @homework.post La lista e' rimasta invariata
     * @homework.testDescr Creazione di una sottolista della lista principale con indici from = 0, to = list.size() - 1. La subList cosi' ottenuta
     * ha gli stessi elementi della lista principale ad eccezione dell'ultimo, che e' assente. Le due liste non possono avere lo stesso codice hash.
     * @homework.exp le due liste non hanno lo stesso hashCode.
     */
    @Test
    public void testHashCodeFailsWithDifferentSubList()
    {
        HCollection coll = list.subList(0,list.size() - 1);

        int hash1 = coll.hashCode();
        int hash2 = list.hashCode();

        assertNotEquals(hash1,hash2);
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 liste con gli stessi elementi nelle stesse posizioni
     * siano uguali secondo la definizione del metodo <code>equals</code>.
     * @homework.des Accertamento del funzionmento del metodo <code>equals</code> per cui quando si hanno 2 liste uguali venga ritornato true.
     * @homework.pre Lista creata dal metodo initialize(). Collezione inizializzata correttamente
     * @homework.post Nessuna delle 2 liste e' stata modificata.
     * @homework.testDescr Si crea una nuova lista con gli stessi elementi della lista originale e disposti nello stesso ordine.
     * Per quanto dichiarato dalla documentazione, le due liste sono uguali. Il controllo viene fatto usando equals()
     * @homework.exp il metodo equals() ritorna <code>true</code>
     */
    @Test
    public void testEqualsSucceeds()
    {
        HCollection coll= new ListAdapter();
        coll.add(0);
        coll.add(1);
        coll.add(2);

        boolean areEqual = coll.equals(list);

        assertTrue(areEqual);
    }

    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 liste con gli stessi elementi ma non nelle stesse posizioni
     * siano diverse secondo la definizione del metodo <code>equals</code>.
     * @homework.des Accertamento del funzionmento del metodo <code>equals</code> per cui quando si hanno 2 liste uguali venga ritornato false.
     * @homework.pre Lista creata dal metodo initialize(). Collezione inizializzata correttamente
     * @homework.post Nessuna delle 2 liste e' stata modificata.
     * @homework.testDescr Si crea una nuova lista con gli stessi elementi della lista originale e ma disposti in ordine diverso dalla
     * lista originale.
     * Per quanto dichiarato dalla documentazione, le due liste non sono uguali. Il controllo viene fatto usando <code>equals</code>
     * @homework.exp il metodo <code>equals</code> ritorna <code>false</code>
     */
    @Test
    public void testEqualsFailsOrder()
    {
        HCollection coll= new ListAdapter();
        coll.add(1);
        coll.add(2);
        coll.add(0);

        boolean areEqual = coll.equals(list);

        assertFalse(areEqual);
    }

    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che se l'argomento passato e' <code>null</code> il metodo
     * ritorni false.
     * @homework.des Accertamento del funzionmento del metodo <code>equals</code> per cui quando il parametro passato e' nullo venga ritornato
     * false. Si verifica che non sia lanciata eccezione erroneamente.
     * @homework.pre Lista creata dal metodo initialize(). Collezione inizializzata a null.
     * @homework.post La lista e' rimasta invariata.
     * @homework.testDescr Si crea una nuova lista e la si inizializza con il valore null.
     * Per quanto dichiarato dalla documentazione, le due liste non sono uguali. Il controllo viene fatto usando <code>equals</code>
     * @homework.exp il metodo <code>equals</code> ritorna <code>false</code>
     */
    @Test
    public void testEqualsFailsNull()
    {
        HList coll= null;

        boolean areEqual = list.equals(coll);

        assertFalse(areEqual);
    }

    //Helper methods

    /*
    Contolla che addAll abbia preservato gli elementi della lista prima e dopo la posizione di inserimento
    Gli elementi della collezione sono stati inseriti tutti in ordine
     */
    private boolean checkAddAll(Object[] values, int startingPos, HCollection coll)
    {
        boolean isOk = true;
        //Testo la prima parte della lista che deve essere invariata
        for(int j = 0; j < startingPos; j++)
            if(!values[j].equals(list.get(j)))
            {
                isOk = false;
                break;
            }

        //Testo che gli elementi siano nella posizione corretta
        int i = 0;
        HIterator it = coll.iterator();
        while(it.hasNext())
        {
            //Controllo gli elementi in ordine a partire dalla fine della lista: devono essere uguali
            if(!it.next().equals(list.get(startingPos + i)))
            {
                isOk = false;
                break;
            }
            i++;
        }

        for(int j = startingPos + coll.size(); j < list.size(); j++)
            if(!values[j - coll.size()].equals(list.get(j)))
            {
                isOk = false;
                break;
            }

        return isOk;
    }

    /*
    Ritorna una collezione con elementi non contenuti nella lista inizializzata da initialize()
     */
    private HCollection createCollection()
    {
        HCollection coll = new ListAdapter();
        coll.add(3);
        coll.add(4);
        coll.add(5);

        return coll;
    }

}
