package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite di test per l'esecuzione di tutte le funzionalita' definite nella classe {@code MapAdapter}.
 * Ha il solo scopo di poter eseguire piu' suite di test in una sola volta.
 *
 * @safe.des Richiama le suite di test definite da {@code MapAdapterTester, EntrySetAbstractCollectionTester, KeySetValuesTester,
 * EntryTester,SetCollectionIteratorTester}.
 * @safe.post Sono stati ottenuti tutti i risultati delle suite sopra elencate.
 * @safe.records Consultare il file <a href="..\..\Test suite execution records\Test Results - TestSuiteMap.html">
 *    "Test Results - TestSuiteMap.html"</a> nella cartella "Test suite execution records"
 * @safe.exec I test sono stati eseguiti utilizzando JUnit v4.13 e hamcrest v1.3.
 * Per poter lanciare i test e' necessario inserire i file .jar di questi framework all'interno del CLASSPATH settando tale
 * variabile di ambiente.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MapAdapterTester.class,
        EntrySetAbstractCollectionTester.class,
        KeySetValuesTester.class,
        EntryTester.class,
        SetCollectionIteratorTester.class})
public class TestSuiteMap
{
}
