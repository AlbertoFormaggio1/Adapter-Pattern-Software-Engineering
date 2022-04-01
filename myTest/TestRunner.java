package myTest;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;

/**
 * @author Formaggio Alberto
 */
public class TestRunner
{
    /**
     * Main TestRunner.
     * Per eseguire una suite qualsiasi:
     * java myTest/TestRunner.java
     * Per eseguire le suite "suite1" e "suite2" (sostituire con i nomi desiderati):
     * java myTest/TestRunner.java myTest.suite1 myTest.suite2
     *
     * <b>MAGGIORI INFORMAZIONI NEL FILE "NOTE IMPORTANTI"</b> nella root del progetto.
     * @param args Eventuali suite specifiche da eseguire
     */
    public static void main(String[] args)
    {
        Result res = null;
        String[] classesArray;
        if(args.length == 0)
        {
            classesArray = new String[]{"myTest.TestSuiteList","myTest.TestSuiteMap"};
        }
        else
            classesArray = args;

        for(String s : classesArray)
        {
            printTestSuiteTitle(s);
            Class clazz = null;
            try
            {
                clazz = Class.forName(s);
            }
            catch(ClassNotFoundException cnfe)
            {
                cnfe.printStackTrace();
                System.exit(-1);
            }
            res = new JUnitCore().runClasses(clazz);
            printResults(res);
        }
    }

    /**
     * Prints the title of the suite being tested in a nice way
     * @param toPrint Name of the suite to print
     */
    private static void printTestSuiteTitle(String toPrint){
        System.out.println("=============================================================");

        System.out.print("\t");
        for(int i = 0; i < toPrint.length(); i++)
            System.out.print((toPrint.charAt(i)) + " ");
        System.out.println();

        System.out.println("=============================================================");
    }

    /**
     * Prints the results of the test suite
     * @param res Result object containing infos about the suite which has just been executed
     */
    private static void printResults(Result res)
    {
        int successes = res.getRunCount() - res.getFailureCount() - res.getIgnoreCount();
        System.out.print("TEST RUN: " + res.getRunCount() + "     ");
        System.out.print("SUCCESSES: " + successes + "     ");
        System.out.print("FAILURES: " + res.getFailureCount() + "     ");
        System.out.println("IGNORED: " + res.getIgnoreCount());
        System.out.println("Test was " + ((res.wasSuccessful()) ? "" : "not ") + "successful");
        if(!res.wasSuccessful())
        {
            System.out.println("\nFailed test cases (one per line):");
            for (Failure failure : res.getFailures())
                System.out.println(failure.getDescription());
        }
        System.out.println("\n");
    }
}


