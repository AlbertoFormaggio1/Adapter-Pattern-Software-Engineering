package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Questa suite di test ha il compito di testare tutte le funzionalita' delle entry ritornate dalla mappa inserite all'interno dell'entrySet definite
 * nella classe {@code MapAdapter.MyEntry}<p>
 * In questa suite verranno testati i metodi della classe <code>MapAdapter.MyEntry</code>, la quale implementa i metodi definiti dall'interfaccia
 * HMap.HEntry.<p>
 * In questa suite verranno testate pure le funzionalita' di backing legate alle singole entries definite dal metodo {@code setValue}.
 *
 * @safe.des Per testare la classe nella sua interezza e' stato testato ciascun metodo fornendo in input parametri validi
 * e non validi cosi' da testare il piu' ampio numero di casi possibili.<p>
 * La documentazione di ciascun test case e' stata eseguita seguendo la colonna "homework" fornita nel file della consegna,
 * inoltre i metodi hanno tutti un nome che e' il piu' evocativo possibile. <p>
 * @safe.pre Si suppone corretto il comportamento di entrySet e dell'iteratore definito per esso. E' gia' stato testato
 * all'interno di un'altra suite.
 * @safe.post Si sono ottenuti i risultati dell'esecuzione di tutti i test in questa suite.
 * @safe.records Consultare il file <a href="..\..\Test suite execution records\Test Results - EntryTester.html">
 *     "Test Results - EntryTester.html"</a> nella cartella "Test suite execution records"
 * @safe.exec I test sono stati eseguiti utilizzando JUnit v4.13 e hamcrest v1.3.
 * Per poter lanciare i test e' necessario inserire i file .jar di questi framework all'interno del CLASSPATH settando tale
 * variabile di ambiente.
 *
 * @note <b>ATTENZIONE!</b> Per testare le entry si e' utilizzato un entrySet ritornato dall'oggetto MapAdapter in quanto, dalla documentazione
 * di Java J2SE 1.4.2: "The <i>only</i> way to obtain a reference to a map entry is from the iterator of this collection-view.", per cui
 * un'entry non puo' esistere senza fare riferimento ad una HMap di appoggio.
 *
 * @author Formaggio Alberto
 */
public class EntryTester
{
    private HSet entrySet;
    private MapAdapter map;

    /**
     * Crea un oggetto MapAdapter contenente le seguenti coppie chiave valore:
     *
     * | 0 |  "zero" |
     * | 1 |  "uno"  |
     * | 2 |  "due"  |
     *
     * Crea poi l'entrySet associato a tale mappa.
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

    /**
     * Test del metodo <code>public Object getKey(Object o)</code>: Testo che la chiave ritornata da {@code getKey} sia effettivamente
     * la chiave dell'entry.
     * @homework.des Accertamento del funzionamento del metodo {@code getKey}
     * @homework.pre Mappa con una sola entry inserita pari ("key","value"). Entry assegnata ad un oggetto di tipo HEntry.
     * @homework.post La mappa e l'entry sono rimaste invariate.
     * @homework.testDescr Si inserisce la coppia ("key","value") all'interno di una mappa appena svuotata, che quindi conterra'
     * solo tale entry.
     * Mediante l'iteratore su un oggetto di tipo entrySet si recupera l'entry desiderata.
     * Su tale entry si ottiene la chiave usando il metodo {@code getKey}. Dal momento che all'interno della mappa e' presente solo 1 coppia,
     * la chiave deve essere quella dell'unica coppia precedentemente inserita.
     * @homework.exp {@code getKey} ritorna "key"
     */
    @Test
    public void testGetKey()
    {
        map.clear();
        map.put("key","value");
        HMap.HEntry e = (HMap.HEntry)map.entrySet().iterator().next();

        Object key = e.getKey();

        assertEquals("key",key);
    }

    /**
     * Test del metodo <code>public Object getValue(Object o)</code>: Testo che il valore ritornato da {@code getKey} sia effettivamente
     * il valore dell'entry.
     * @homework.des Accertamento del funzionamento del metodo {@code getValue}
     * @homework.pre Mappa con una sola entry inserita pari ("key","value"). Entry assegnata ad un oggetto di tipo HEntry.
     * @homework.post La mappa e l'entry sono rimaste invariate.
     * @homework.testDescr Si inserisce la coppia ("key","value") all'interno di una mappa appena svuotata, che quindi conterra'
     * solo tale entry.
     * Mediante l'iteratore su un oggetto di tipo entrySet si recupera l'entry desiderata.
     * Su tale entry si ottiene il valore usando il metodo {@code getValue}. Dal momento che all'interno della mappa e' presente solo 1 coppia,
     * il valore deve essere quello dell'unica coppia precedentemente inserita.
     * @homework.exp {@code getValue} ritorna "value"
     */
    @Test
    public void testGetValue()
    {
        map.clear();
        map.put("key","value");
        HMap.HEntry e = (HMap.HEntry)map.entrySet().iterator().next();

        Object val = e.getValue();

        assertEquals("value",val);
    }

    /**
     * Test del metodo <code>public Object setValue(Object o)</code>: Testo che il valore ritornato da setValue sia quello che precedentemente
     * era associato alla chiave della entry.
     * @homework.des Accertamento del funzionamento del metodo {@code setValue}
     * @homework.pre EntrySet inzializzato dal metodo initialize()
     * @homework.post E' stato sostituito un elemento sia nell'entry che nella mappa.
     * @homework.testDescr Si ottiene un'entry dall'entrySet, di cui si salva il valore.
     * Si sostituisce il valore della entry usando il metodo {@code setValue}
     * Si salva il valore ritornato: deve essere il valore salvato precedentemente
     * @homework.exp Il valore ritornato e' quello che precedentemente era associato alla entry.
     */
    @Test
    public void testSetValueReturnedIsRight()
    {
        HMap.HEntry e = (HMap.HEntry) entrySet.iterator().next();
        Object oldValue = e.getValue();

        Object actualValue = e.setValue("newValue");

        assertEquals(oldValue,actualValue);
    }


    /**
     * Test del metodo <code>public Object setValue(Object o)</code>: Testo che il set abbia modificato il valore salvato dentro l'entry.
     * @homework.des Accertamento del funzionamento del metodo {@code setValue}
     * @homework.pre EntrySet inzializzato dal metodo initialize()
     * @homework.post E' stato sostituito un elemento sia nell'entry che nella mappa.
     * @homework.testDescr Si ottiene un'entry dall'entrySet.
     * Si sostituisce il valore della entry usando il metodo {@code setValue} e inserendo "newValue".
     * Il valore nella entry deve essere stato sostituito.
     * @homework.exp {@code e.getValue} ritorna "newValue"
     */
    @Test
    public void testSetValueValueWasSetInEntry()
    {
        HMap.HEntry e = (HMap.HEntry) entrySet.iterator().next();

        e.setValue("newValue");

        assertEquals("newValue",e.getValue());
    }

    /**
     * Test del metodo <code>public Object setValue(Object o)</code>: testa che il metodo <code>setValue</code> lanci eccezione
     * <code>NullPointerException</code> nel caso in cui il valore passato sia <code>null</code>.
     * @homework.des Accertamento del corretto funzionamento di <code>setValue</code> qualora il valore passato non sia valido.
     * @homework.pre Set inizializzato con il metodo initialize(). Entry ottenuta dall'entrySet.
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Si prova a cambiare il valore di un'entry ottenuta dall'entryset passando al metodo setValue
     * il valore null. Poiche' null non e' un valore valido per la mappa di riferimento, deve essere lanciata l'eccezione
     * NullPointerException.
     * @homework.exp L'eccezione <code>NullPointerException</code> deve essere lanciata. Si controlla con il metodo <code>assertThrows()</code> fornito
     * dal framework JUnit.
     */
    @Test
    public void testSetValueExceptionIfNull()
    {
        HMap.HEntry e = (HMap.HEntry) entrySet.iterator().next();

        assertThrows(NullPointerException.class, () -> e.setValue(null));
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 entry con la stessa coppia chiave valore siano uguali.
     * @homework.des Accertamento del funzionmento del metodo <code>equals</code> per cui quando si hanno 2 entries uguali venga ritornato true.
     * @homework.pre Entryset creato dal metodo initialize(). Entry inizializzate correttamente con i valori ritornati dall'iteratore di entryset
     * @homework.post Le 2 entry sono rimaste invariate.
     * @homework.testDescr Si ottiene un'Entry e1 dall'entrySet usando il suo iteratore.
     * Si crea poi un nuovo MapAdapter e si inserisce al suo interno la coppia chiave valore definita da e1.
     * Mediante l'iteratore si ottiene l'entry e2 che, essendo l'unica entry presente in map2, deve essere uguale ad e1.
     * @homework.exp il metodo {@code equals} ritorna <code>true</code>
     */
    @Test
    public void testEntryEqualsSameEntry()
    {
        HMap.HEntry e1 = (HMap.HEntry) entrySet.iterator().next();
        MapAdapter map2 = new MapAdapter();
        map2.put(e1.getKey(),e1.getValue());
        HMap.HEntry e2 = (HMap.HEntry) map2.entrySet().iterator().next();

        boolean result = e1.equals(e2);

        assertTrue(result);
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 entry con lo stesso valore ma 2 chiavi diverse siano
     * diverse.
     * @homework.des Accertamento del corretto funzionmento del metodo <code>equals</code>
     * @homework.pre Entryset creato dal metodo initialize(). Entry inizializzate correttamente con i valori ritornati dall'iteratore di entryset
     * @homework.post Le 2 entry sono rimaste invariate.
     * @homework.testDescr Si ottiene un'Entry e1 dall'entrySet usando il suo iteratore.
     * Si crea poi un nuovo MapAdapter e si inserisce al suo interno il valore di e1 e una chiave diversa.
     * Mediante l'iteratore si ottiene l'entry e2 che, essendo l'unica entry presente in map2, deve essere uguale a quella appena inserita.
     * @homework.exp il metodo {@code equals} ritorna <code>false</code>
     */
    @Test
    public void testEntryEqualsDifferentKey()
    {
        HMap.HEntry e1 = (HMap.HEntry) entrySet.iterator().next();
        MapAdapter map2 = new MapAdapter();
        map2.put("anyKey",e1.getValue());
        HMap.HEntry e2 = (HMap.HEntry) map2.entrySet().iterator().next();

        boolean result = e1.equals(e2);

        assertFalse(result);
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 entry con la stessa chiave ma 2 valori diversi siano
     * diverse.
     * @homework.des Accertamento del corretto funzionmento del metodo <code>equals</code>
     * @homework.pre Entryset creato dal metodo initialize(). Entry inizializzata con i valori ritornati dall'iteratore di entryset
     * @homework.post Le 2 entry sono rimaste invariate.
     * @homework.testDescr Si ottiene un'Entry e1 dall'entrySet usando il suo iteratore.
     * Si crea poi un nuovo MapAdapter e si inserisce al suo interno la chiave di e1 e un valore diverso.
     * Mediante l'iteratore si ottiene l'entry e2 che, essendo l'unica entry presente in map2, deve essere uguale a quella appena inserita.
     * @homework.exp il metodo {@code equals} ritorna <code>false</code>
     */
    @Test
    public void testEntryEqualsDifferentValue()
    {
        MapAdapter map2 = new MapAdapter();
        HMap.HEntry e1 = (HMap.HEntry) entrySet.iterator().next();
        map2.put(e1.getKey(),"anyValue");
        HMap.HEntry e2 = (HMap.HEntry) map2.entrySet().iterator().next();

        boolean result = e1.equals(e2);

        assertFalse(result);
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che 2 entry con sia chiave che valore diverso
     * @homework.des Accertamento del corretto funzionmento del metodo <code>equals</code>
     * @homework.pre Entryset creato dal metodo initialize(). Entry inizializzata con i valori ritornati dall'iteratore di entryset
     * @homework.post Le 2 entry sono rimaste invariate.
     * @homework.testDescr Si ottiene un'Entry e1 dall'entrySet usando il suo iteratore.
     * Si crea poi un nuovo MapAdapter e si inserisce al suo interno chiave e valori che siano diversi da quelle di e1
     * Mediante l'iteratore si ottiene l'entry e2 che, essendo l'unica entry presente in map2, deve essere uguale a quella appena inserita.
     * @homework.exp il metodo {@code equals} ritorna <code>false</code>
     */
    @Test
    public void testEntryEqualsBothDifferent()
    {
        MapAdapter map2 = new MapAdapter();
        HMap.HEntry e1 = (HMap.HEntry) entrySet.iterator().next();
        map2.put(e1.getValue(),e1.getKey());
        HMap.HEntry e2 = (HMap.HEntry) map2.entrySet().iterator().next();

        boolean result = e1.equals(e2);

        assertFalse(result);
    }


    /**
     * Test del metodo <code>public boolean equals(Object o)</code>: testo che se l'argomento passato e' <code>null</code> il metodo
     * ritorni false.
     * @homework.des Accertamento del funzionmento del metodo <code>equals</code> per cui quando il parametro passato e' nullo venga ritornato
     * false. Si verifica che non sia lanciata eccezione erroneamente.
     * @homework.pre Set creato dal metodo initialize().
     * @homework.post Il set e' rimasto invariato.
     * @homework.testDescr Invoco il metodo equals passando un'entry e il valore null.
     * Per quanto dichiarato dalla documentazione, le due entry non sono uguali. Il controllo viene fatto usando <code>equals</code>
     * @homework.exp il metodo <code>equals</code> ritorna <code>false</code>
     */
    @Test
    public void testEntryEqualsNull()
    {
        HMap.HEntry e1 = (HMap.HEntry) entrySet.iterator().next();

        boolean result = e1.equals(null);

        assertFalse(result);
    }

    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che 2 oggetti che implementano HEntry uguali abbiano
     * lo stesso hashCode. Affinche' l'uguaglianza sia verificata e' necessario che sia chiave che valore siano uguali.
     * @homework.des due entryset uguali hanno lo stesso codice hash.
     * @homework.pre 2 entries opportunamente create uguali tra loro secondo i parametri definiti da equals (stesse coppie chiave
     * valore)
     * @homework.post le entries sono rimaste invariate
     * @homework.testDescr Si ottiene un'Entry e1 dall'entrySet usando il suo iteratore.
     * Si crea poi un nuovo MapAdapter e si inserisce al suo interno la coppia chiave valore definita da e1.
     * Mediante l'iteratore si ottiene l'entry e2 che, essendo l'unica entry presente in map2, deve essere uguale ad e1.
     * @homework.exp e1 ed e2 hanno lo stesso codice hash.
     */
    @Test
    public void testEntryHashCodeSameEntry()
    {
        HMap.HEntry e1 = (HMap.HEntry) entrySet.iterator().next();
        MapAdapter map2 = new MapAdapter();
        map2.put(e1.getKey(),e1.getValue());
        HMap.HEntry e2 = (HMap.HEntry) map2.entrySet().iterator().next();

        int hash1 = e1.hashCode();
        int hash2 = e2.hashCode();

        assertEquals(hash1,hash2);
    }


    /**
     * Test del metodo <code>public int hashCode()</code>: Si testa che 2 HEntry diverse abbiano codici hash diversi
     * @homework.des due entryset diverse hanno lo codici hash diversi.
     * @homework.pre 2 entries opportunamente create diverse tra loro secondo i parametri definiti da equals (uno tra chiave o
     * valore diverso o entrambi diversi).
     * @homework.post le entries sono rimaste invariate
     * @homework.testDescr Si ottiene un'Entry e1 dall'entrySet usando il suo iteratore.
     * Si crea poi un nuovo MapAdapter e si inserisce al suo interno una coppia chiave valore diversa da e1 (basta inserire
     * una coppia che non era presente nel set originale).
     * Mediante l'iteratore si ottiene l'entry e2 che, essendo l'unica entry presente in map2, deve essere quella appena inserita
     * (diversa da e1).
     * @homework.exp e1 ed e2 hanno due codici hash diversi.
     */
    @Test
    public void testEntryHashCodeDifferentEntry()
    {
        HMap.HEntry e1 = (HMap.HEntry) entrySet.iterator().next();
        MapAdapter map2 = new MapAdapter();
        map2.put("anyKey","anyValue");
        HMap.HEntry e2 = (HMap.HEntry) map2.entrySet().iterator().next();

        int hash1 = e1.hashCode();
        int hash2 = e2.hashCode();

        assertNotSame(hash1,hash2);
    }
}
