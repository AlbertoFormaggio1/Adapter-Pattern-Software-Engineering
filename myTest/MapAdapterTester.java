package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Questa suite di test ha il compito di testare tutte le funzionalita' base della classe {@code MapAdapter}. <p> Il corretto comportamento
 * delle classi che sono strettamente correlate a questa (ovvero {@code EntrySet, KeySet, ValueCollection}) sono testate in una
 * suite separata.<p>
 * Si vuole dimostrare che tutti i metodi definiti in MapAdapter funzionino correttamente
 *
 * @author Formaggio Alberto
 *
 * @safe.des Per testare la classe nella sua interezza e' stato testato ciascun metodo fornendo in input parametri validi
 * e non validi in modo da testare il piu' ampio numero di casi possibili in cui la collezione si puo' trovare.<p>
 * La documentazione di ciascun test case e' stata eseguita seguendo la colonna "homework" fornita nel file della consegna,
 * inoltre i metodi hanno tutti un nome che e' il piu' evocativo possibile.
 * @safe.pre Si assicura che i metodi della classe Hashtable di Java J2ME CLDC 1.1 siano gia' stati esaustivamente testati
 * al rilascio di Java J2ME CLDC 1.1. Per tale motivo i metodi qui di seguito elencati non verranno testati in quanto semplici
 * metodi wrapper che richiamano un metodo della classe Hashtable definita in CLDC 1.1 (con le stesse eccezioni definite in
 * Java J2SE 1.4.2 e le stesse funzionalita'):
 * <ul>
 *     <li>size</li>
 *     <li>isEmpty</li>
 *     <li>containsValue</li>
 *     <li>put</li>
 *     <li>clear</li>
 * </ul>
 * @safe.post Si sono ottenuti i risultati dell'esecuzione di tutti i test in questa suite.
 * @safe.records Consultare il file <a href="..\..\Test suite execution records\Test Results - MapAdapterTester.html">
 *     "Test Results - MapAdapterTester.html"</a> nella cartella "Test suite execution records"
 * @safe.exec I test sono stati eseguiti utilizzando JUnit v4.13 e hamcrest v1.3.
 * Per poter lanciare i test e' necessario inserire i file .jar di questi framework all'interno del CLASSPATH settando tale
 * variabile di ambiente.
 *
 * @note
 * Si fa notare che non e' presente il test dell'eccezione ArrayStoreException del metodo toArray(Object[]) in quanto gestito
 * da Java a tempo di runtime.
 *
 */
public class MapAdapterTester
{
    private MapAdapter map;

    /**
     * Crea un oggetto MapAdapter contenente le seguenti coppie chiave valore:
     * <pre>
     * | 0 |  "zero" |
     * | 1 |  "uno"  |
     * | 2 |  "due"  |
     * | 3 |  "tre"  |
     * | 4 |"quattro"|
     * </pre>
     */
    @Before
    public void initialize()
    {
        map = new MapAdapter();
        map.put(1,"uno");
        map.put(2,"due");
        map.put(3,"tre");
        map.put(4,"quattro");
    }

    /**
     * Test del metodo <code>public Object add(Object key)</code>: testa che il metodo <code>add</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la chiave passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>add</code> qualora la chiave non sia valida.
     * @homework.pre Mappa inizializzata con il metodo initialize()
     * @homework.post La mappa e' rimasta invariata.
     * @homework.testDescr Si prova ad interrogare la manda chiedendo se la chiave nulla e' presente. Essendo null un elemento non valido per la mappa,
     * deve essere sollevata eccezione: l'ispezione non puo' andare a buon fine.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testContainsKeyExceptionIfKeyIsNull()
    {
        Object key = null;

        assertThrows(NullPointerException.class, () -> map.containsKey(key));
    }

    /**
     * Test del metodo <code>public Object get(Object key)</code>: testa che il metodo <code>get</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la chiave passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>get</code> qualora la chiave non sia valida.
     * @homework.pre Mappa inizializzata con il metodo initialize()
     * @homework.post La mappa e' rimasta invariata.
     * @homework.testDescr Si prova ad ottenere il valore associato alla chiave nulla. Essendo null un elemento non valido per la mappa,
     * deve essere sollevata eccezione: l'ispezione non puo' andare a buon fine.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testGetExceptionIfKeyIsNull()
    {
        Object key = null;

        assertThrows(NullPointerException.class, () -> map.get(key));
    }

    /**
     * Test del metodo <code>public Object remove(Object key)</code>: testa che il metodo <code>remove</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la chiave passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>remove</code> qualora la chiave non sia valida.
     * @homework.pre Mappa inizializzata con il metodo initialize()
     * @homework.post La mappa e' rimasta invariata.
     * @homework.testDescr Si prova a rimuovere il valore associato alla chiave nulla. Essendo null un elemento non valido per la mappa,
     * deve essere sollevata eccezione: la rimozione non puo' andare a buon fine.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testRemoveExceptionIfKeyIsNull()
    {
        Object key = null;

        assertThrows(NullPointerException.class, () -> map.remove(key));
    }

    /**
     * Test del metodo <code>public Object putAll(HMap t)</code>: testa che il metodo <code>putAll</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui la mappa passata sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>putAll</code> qualora la mappa passata non sia valida.
     * @homework.pre Mappa inizializzata con il metodo initialize()
     * @homework.post La mappa e' rimasta invariata.
     * @homework.testDescr Si prova ad inserire nella mappa una mappa HMap che pero' non fa riferimento ad alcuna collezione.
     * L'operazione deve fallire perche' <code>null</code> non e' una mappa valida.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testPutAllExceptionIfMapIsNull()
    {
        HMap m = null;

        assertThrows(NullPointerException.class, () -> map.remove(m));
    }


    /**
     * Test del metodo <code>public boolean putAll(HMap t)</code>: testa che il metodo <code>putAll</code> aggiunga gli elementi
     * nella mappa passata all'interno della mappa corrente.
     * @homework.des Accertamento del corretto funzionamento di {@code putAll} quando viene fornita una mappa valida in input
     * @homework.pre La mappa e' stata inizializzata da intialize()
     * @homework.post Gli elementi sono stati aggiunti alla lista. La dimensione della lista e' aumentata di 3.
     * @homework.testDescr Si crea un nuovo mapAdapter in cui si inseriscono delle entry che prima non erano presenti all'interno della mappa
     * in cui si va a fare l'inserimento.
     * Si inseriscono gli elementi e si verifica con il metodo {@code containsAll} di entrySet che la mappa contenga tutti gli elementi della mappa
     * passata
     * @homework.exp {@code containsAll} deve ritornare {@code true}
     */
    @Test
    public void testPutAllInsertsAllElementInGivenMap()
    {
        MapAdapter map2 = new MapAdapter();
        map2.put(3,"tre");
        map2.put(4,"quattro");
        map2.put(5,"cinque");

        map.putAll(map2);

        assertTrue(map.entrySet().containsAll(map2.entrySet()));
    }

    //--------------------------------BACKING METHODS----------------------------

    /**
     * Test del metodo {@code public Object put(Object key, Object value)}: testa che il metodo {@code put} aggiunga gli elementi
     * in un entrySet creato prima dell'inserimento.
     * @homework.des Accertamento delle funzionalita' di backing dell'EntrySet: quando si inserisce un elemento nella mappa, l'inserimento
     * deve avvenire pure nell'EntrySet ritornato in precedenza.
     * @homework.pre entrySet di supporto alla mappa ottenuto prima dell'inserimento.
     * @homework.post La coppia e' stata inserita sia nella mappa che nell'entrySet.
     * @homework.testDescr Si ottiene l'entrySet associato ad una mappa e in seguito si effettua l'inserimento in tale mappa.
     * Si crea in seguito una seconda mappa che ha il solo scopo di inserire la stessa coppia chiave-valore e di ritornare l'entry dall'entrySet
     * (in questo caso l'entrySet e' stato creato dopo l'inserimento, per cui l'entry e' sicuramente presente).
     * Si testa quindi se tale entry e' presente all'interno dell'entrySet relativo alla mappa originale mediante il metodo {@code contains}
     * @homework.exp {@code contains} ritorna {@code true}.
     */
    @Test
    public void testBackingEntrySetPut()
    {
        HSet entrySet = map.entrySet();
        Object key = "newKey";
        Object val = "newValue";

        map.put(key,val);

        MapAdapter map2 = new MapAdapter();
        map2.put(key,val);
        HMap.HEntry e = (HMap.HEntry) map2.entrySet().iterator().next();
        assertTrue(entrySet.contains(e));
    }

    /**
     * Test del metodo {@code public Object put(Object key, Object value)}: testa che il metodo {@code put} aggiunga gli elementi
     * in un keySet creato prima dell'inserimento.
     * @homework.des Accertamento delle funzionalita' di backing del KeySet: quando si inserisce un elemento nella mappa, l'inserimento
     * deve avvenire pure nel keySet ritornato in precedenza.
     * @homework.pre keySet di supporto alla mappa ottenuto prima dell'inserimento.
     * @homework.post La coppia e' stata inserita sia nella mappa. La chiave di tale coppia e' stata inserita nel keySet.
     * @homework.testDescr Si ottiene il keyset associato ad una mappa e in seguito si effettua l'inserimento in tale mappa.
     * Si testa quindi se la chiave della coppia inserita e' presente all'interno del keySet relativo alla mappa mediante il metodo {@code contains}
     * @homework.exp {@code contains} ritorna {@code true}.
     */
    @Test
    public void testBackingKeySetPut()
    {
        HSet keySet = map.keySet();

        map.put("newKey","newValue");

        assertTrue(keySet.contains("newKey"));
    }

    /**
     * Test del metodo {@code public Object put(Object key, Object value)}: testa che il metodo {@code put} aggiunga gli elementi
     * in una collection di values creata prima dell'inserimento.
     * @homework.des Accertamento delle funzionalita' di backing della collezione di values: quando si inserisce un elemento nella mappa, l'inserimento
     * deve avvenire pure nella collezione ritornata in precedenza.
     * @homework.pre Collezione di supporto alla mappa ottenuto prima dell'inserimento.
     * @homework.post La coppia e' stata inserita sia nella mappa. Il valore di tale coppia e' stato inserito nella collezione.
     * @homework.testDescr Si ottiene la collezione di valori associata ad una mappa e in seguito si effettua l'inserimento in tale mappa.
     * Si testa quindi se il valore della coppia inserita e' presente all'interno della collezione relativa alla mappa mediante il metodo {@code contains}
     * @homework.exp {@code contains} ritorna {@code true}.
     */
    @Test
    public void testBackingValuesPut()
    {
        HCollection values = map.values();

        map.put("newKey","newValue");

        assertTrue(values.contains("newValue"));
    }

    /**
     * Test del metodo {@code public void clear()}: testa che il metodo {@code clear} invocato sull'oggetto MapAdapter rimuova tutti
     * gli elementi da un keySet creato prima dell'invocazione del metodo.
     * @homework.des Accertamento delle funzionalita' di backing del KeySet: quando si svuota un oggetto MapAdapter, deve essere svuotato
     * pure il keySet ritornato in precedenza.
     * @homework.pre keySet di supporto alla mappa ottenuto prima dell'inserimento.
     * @homework.post La mappa e il keySet sono stati svuotati.
     * @homework.testDescr Si ottiene il keyset associato ad una mappa e in seguito si effettua la cancellazione di tutti gli elementi
     * nella mappa invocando il metodo clear. Ora la mappa e' vuota e pure il set deve esserlo.
     * Si testa quindi che il set ritornato in precedenza sia vuoto usando il metodo {@code isEmpty}
     * @homework.exp {@code isEmpty} ritorna {@code true}.
     */
    @Test
    public void testBackingEntrySetClear()
    {
        HSet entrySet = map.entrySet();

        map.clear();

        assertTrue(entrySet.isEmpty());
    }

    /**
     * Test del metodo {@code public void clear()}: testa che il metodo {@code clear} invocato sull'oggetto MapAdapter rimuova tutti
     * gli elementi da un keySet creato prima dell'invocazione del metodo.
     * @homework.des Accertamento delle funzionalita' di backing del KeySet: quando si svuota un oggetto MapAdapter, deve essere svuotato
     * pure il keySet ritornato in precedenza.
     * @homework.pre keySet di supporto alla mappa ottenuto prima dell'inserimento.
     * @homework.post La mappa e il keySet sono stati svuotati.
     * @homework.testDescr Si ottiene il keyset associato ad una mappa e in seguito si effettua la cancellazione di tutti gli elementi
     * nella mappa invocando il metodo clear. Ora la mappa e' vuota e pure il set deve esserlo.
     * Si testa quindi che il set ritornato in precedenza sia vuoto usando il metodo {@code isEmpty}
     * @homework.exp {@code isEmpty} ritorna {@code true}.
     */
    @Test
    public void testBackingKeySetClear()
    {
        HSet keySet = map.keySet();

        map.clear();

        assertTrue(keySet.isEmpty());
    }

    /**
     * Test del metodo {@code public void clear()}: testa che il metodo {@code clear} invocato sull'oggetto MapAdapter rimuova tutti
     * gli elementi da una collezione di values creata prima dell'invocazione del metodo.
     * @homework.des Accertamento delle funzionalita' di backing della collezione di values: quando si svuota un oggetto MapAdapter, deve essere svuotata
     * pure la collezione ritornata in precedenza.
     * @homework.pre keySet di supporto alla mappa ottenuto prima dell'inserimento.
     * @homework.post La mappa e la collezione sono state svuotate.
     * @homework.testDescr Si ottiene la collezione di values associata ad una mappa e in seguito si effettua la cancellazione di tutti gli elementi
     * nella mappa invocando il metodo clear. Ora la mappa e' vuota e pure la collezione deve esserlo.
     * Si testa quindi che la collezizone ritornata in precedenza sia vuota usando il metodo {@code isEmpty}
     * @homework.exp {@code isEmpty} ritorna {@code true}.
     */
    @Test
    public void testBackingValuesClear()
    {
        HCollection values = map.values();

        map.clear();

        assertTrue(values.isEmpty());
    }

    /**
     * Test del metodo {@code public Object remove()}: testa che il metodo {@code remove} invocato sull'oggetto MapAdapter rimuova l'entry associata
     * alla chiave passata anche da un entrySet creato prima dell'invocazione del metodo.
     * @homework.des Accertamento delle funzionalita' di backing dell'entrySet: quando si rimuove una coppia da un oggetto MapAdapter, deve essere rimossa
     * la relativa entry pure dall'entrySet ritornato in precedenza.
     * @homework.pre entryset di supporto alla mappa ottenuto prima dell'inserimento.
     * @homework.post La mappa e l'entryset hanno un elemento in meno.
     * @homework.testDescr Si ottiene l'entrySet associato ad una mappa e in seguito si effettua la rimozione della coppia di chiave 0 dall'oggetto
     * MapAdapter invocando il metodo {@code remove} (coppia che e' (0,"zero")). Ora la mappa non contiene piu' la coppia associata alla chiave 0
     * (e quindi anche l'entrySet). Si testa quindi che il set ritornato in precedenza non contenga tale entry usando il metodo {@code contains}
     * @homework.exp {@code contains} ritorna {@code false}.
     */
    @Test
    public void testBackingEntrySetRemove()
    {
        HSet entrySet = map.entrySet();

        map.remove(0);

        MapAdapter map2 = new MapAdapter();
        map2.put(0,"zero");
        HMap.HEntry e = (HMap.HEntry) map2.entrySet().iterator().next();
        assertFalse(entrySet.contains(e));
    }

    /**
     * Test del metodo {@code public Object remove()}: testa che il metodo {@code remove} invocato sull'oggetto MapAdapter rimuova la chiave passata
     * anche da un keySet creato prima dell'invocazione del metodo.
     * @homework.des Accertamento delle funzionalita' di backing del KeySet: quando si rimuove una coppia da un oggetto MapAdapter, deve essere rimossa
     * la relativa chiave pure dal keySet ritornato in precedenza.
     * @homework.pre keySet di supporto alla mappa ottenuto prima dell'inserimento.
     * @homework.post La mappa e il keySet hanno un elemento in meno.
     * @homework.testDescr Si ottiene il keyset associato ad una mappa e in seguito si effettua la rimozione della coppia di chiave 0 dall'oggetto
     * MapAdapter invocando il metodo {@code remove}. La mappa non contiene piu' la chiave 0 (e quindi nemmeno il keySet).
     * Si testa quindi che il set ritornato in precedenza non contenga tale chiave usando il metodo {@code contains}
     * @homework.exp {@code contains} ritorna {@code false}.
     */
    @Test
    public void testBackingKeySetRemove()
    {
        HSet keySet = map.keySet();

        map.remove(0);

        assertFalse(keySet.contains(0));
    }

    /**
     * Test del metodo {@code public Object remove()}: testa che il metodo {@code remove} invocato sull'oggetto MapAdapter rimuova il valore
     * associato alla chiave passata anche da una collezione contenete i valori della mappa creata prima dell'invocazione del metodo.
     * @homework.des Accertamento delle funzionalita' di backing della collection di values: quando si rimuove una coppia da un oggetto MapAdapter,
     * deve essere rimossa il relativo valore pure dalla collection ritornata in precedenza.
     * @homework.pre collection di supporto alla mappa ottenuto prima dell'inserimento.
     * @homework.post La mappa e la collezione hanno un elemento in meno.
     * @homework.testDescr Si ottiene la collezione di valori associato ad una mappa e in seguito si effettua la rimozione della coppia di chiave 0
     * dall'oggetto MapAdapter invocando il metodo {@code remove}. Ora la mappa non contiene piu' la coppia associata alla chiave 0, quindi pure il valore
     * ad essa associato ("zero") non deve piu' essere presente nella collezione.
     * Si testa quindi che il set ritornato in precedenza non contenga tale valore usando il metodo {@code contains}
     * @homework.exp {@code contains} ritorna {@code false}.
     */
    @Test
    public void testBackingValuesRemove()
    {
        HCollection values = map.values();

        map.remove(0);

        assertFalse(values.contains("zero"));
    }

}
