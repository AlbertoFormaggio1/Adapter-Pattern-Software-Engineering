package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Oggetto ListAdapter: adatta le funzionalita' della classe Vector nella versione di Java J2ME CLDC 1.1 ad una Lista la cui interfaccia
 * e' definita in Java J2SE 1.4.2.
 *
 * L'inserimento di oggetti nulli e' consentito.
 * E' possibile inserire oggetti di tipo qualsiasi (anche diverso tra loro all'interno della lista), il controllo per tipi
 * errati deve essere eseguito a livello di applicazione.
 * Tutte le optional operations sono state implementate.
 *
 * @author Formaggio Alberto
 */
public class ListAdapter implements HList
{
    /**
     * Adaptee
     */
    protected Vector vec;
    /**
     * Starting position (included) in the Vector for this list
     */
    protected int from;
    /**
     * Ending position (excluded) in the Vector for this list
     */
    protected int to;

    /**
     * Construct an empty list.
     */
    public ListAdapter()
    {
        this(0,0);
        vec = new Vector();
    }

    /**
     * Constructs a list containing the elements of the specified collection, in the order they are returned by the collection's iterator.
     * @param c the collection whose elements are to be placed into this list.
     * @throws NullPointerException - if the specified collection is null
     */
    public ListAdapter(HCollection c) throws NullPointerException
    {
        this();
        addAll(c);
    }

    /**
     * Protected constructor used in order to use a specific portion of the list. In the case of the original list from is always 0 and to is the logical size
     * of the vector.
     * @param from_ Starting position in the vector (included) seen by this list
     * @param to_ Last position in the vector (excluded) seen by this list
     */
    protected ListAdapter(int from_, int to_) {
        from = from_;
        to = to_;
    }

    /**
     * Check whether the given index is valid for an insertion
     * @param index index to check
     * @throws IndexOutOfBoundsException if the given index is invalid
     */
    protected void checkIndexAdd(int index)
    {
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException();
    }

    /**
     * Check whether the given index is valid for an inspection
     * @param index index to check
     * @throws IndexOutOfBoundsException if the given index is invalid
     */
    protected void checkIndex(int index)
    {
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<code>index &lt; 0 || index &gt; size()</code>)
     */
    public void add(int index, Object element)
    {
        checkIndexAdd(index);
        vec.insertElementAt(element, index + from);
        to++;
    }

    public int size()
    {
        if(to - from >= 0 )
            return to - from;
        else
            return Integer.MAX_VALUE;
    }

    public boolean isEmpty()
    {
        return to == from;
    }

    /**
     * Returns <code>true</code> if this list contains the specified element.
     * More formally, returns <code>true</code> if and only if this list contains
     * at least one element <code>e</code> such that
     * <code>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</code>.
     *
     * @param o element whose presence in this list is to be tested
     * @return <code>true</code> if this list contains the specified element.
     */
    public boolean contains(Object o)
    {
        return indexOf(o) != -1;
    }

    public HIterator iterator()
    {
        return new ListIter();
    }

    public Object[] toArray()
    {
        Object[] res = new Object[size()];
        res = toArray(res);

        return res;
    }

    public Object[] toArray(Object[] a)
    {
        if(a == null)
            throw new NullPointerException();

        //Java lancia eccezione nel caso di ArrayStoreException in automatico
        if(a.length >= size())
            //Ho spazio a sufficienza, metto a null gli elementi dopo la fine della collezione
            for(int i = size(); i < a.length; i++)
                a[i] = null;
        else
            //Non ho abbastanza spazio in a, creo un nuovo array da riempire
            a = new Object[size()];

        for(int i = 0; i < size(); i++)
        {
            a[i] = get(i);
        }

        return a;
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param o element to be appended to this list
     * @return <code>true</code> (as specified by {@link HCollection#add})
     * */
    public boolean add(Object o)
    {
        add(size(),o);
        //Ritorno sempre true in quanto, dalla documentazione di CLDC1.1, l'add di Vector ha sempre successo
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index <code>i</code> such that
     * <code>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</code>
     * (if such an element exists).
     *
     * @param o element to be removed from this list, if present
     * @return <code>true</code> if this list contained the specified element
     */
    public boolean remove(Object o)
    {
        int index = indexOf(o);
        if(index != -1){
            remove(index);
            return true;
        }
        else
            return false;
    }

    /**
     * Returns <code>true</code> if this list contains all of the elements of the
     * specified collection.
     *
     * @param  c collection to be checked for containment in this list
     * @return <code>true</code> if this list contains all of the elements of the
     *         specified collection
     * @throws NullPointerException if the specified collection is null
     * @see #contains(Object)
     */
    public boolean containsAll(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        boolean containsAll = true;

        HIterator it = c.iterator();
        while (it.hasNext())
        {
            Object element = it.next();
            if (!contains(element))
            {
                //Se almeno un elemento non e' contenuto esco e ritorno subito false
                containsAll = false;
                break;
            }
        }
        return containsAll;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator.  The behavior of this
     * operation is undefined if the specified collection is modified while
     * the operation is in progress.  (Note that this will occur if the
     * specified collection is this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return <code>true</code> if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     * @see #add(Object)
     */
    public boolean addAll(HCollection c)
    {
        return addAll(size(),c);
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.)
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c collection containing elements to be added to this list
     * @return <code>true</code> if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     * @throws IndexOutOfBoundsException if the index is out of range
     * (<code>index &lt; 0 || index &gt; size()</code>)
     * */
    public boolean addAll(int index, HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        checkIndexAdd(index);

        int previousSize = size();
        int i = index;
        HIterator it = c.iterator();
        while(it.hasNext())
        {
            add(i,it.next());
            i++;
        }
        return previousSize != size();
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     *
     * @param c collection containing elements to be removed from this list
     * @return <code>true</code> if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean removeAll(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        HIterator it = c.iterator();
        int previousSize = size();
        while(it.hasNext())
        {
            Object elem = it.next();
            //Rimuovo l'elemento della collezione e tutti i suoi duplicati
            while(remove(elem));
        }
        return size() != previousSize;
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return <code>true</code> if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean retainAll(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        HCollection toRemove = new ListAdapter();
        int previousSize = size();

        for(int i = 0; i < size(); i++)
        {
            if(!c.contains(get(i)))
                toRemove.add(get(i));
        }

        removeAll(toRemove);

        return size() != previousSize;
    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     */
    public void clear()
    {
        while(!isEmpty())
            remove(0);
    }

    public Object get(int index)
    {
        checkIndex(index);
        return vec.elementAt(index + from);
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<code>index &lt; 0 || index &gt;= size()</code>)
     */
    public Object set(int index, Object element)
    {
        checkIndex(index);
        Object removed = get(index);
        vec.setElementAt(element, index + from);
        return removed;
    }

    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<code>index &lt; 0 || index &gt;= size()</code>)
     */
    public Object remove(int index)
    {
        checkIndex(index);
        Object removed = get(index);
        //Se le sottoliste iniziano tutte dalla posizione 1, la posizione di rimozione e'
        //from - parent.from.
        vec.remove(index + from);
        to--;
        return removed;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <code>i</code> such that
     * <code>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</code>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int indexOf(Object o)
    {
        int index;
        index = vec.indexOf(o, from);

        if(index >= to || index == -1)
            return -1;
        else
            return index - from;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <code>i</code> such that
     * <code>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</code>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int lastIndexOf(Object o)
    {
        int index;
        index = vec.lastIndexOf(o, to - 1);

        if(index < from || index == -1)
            return -1;
        else
            return index - from;
    }

    public HListIterator listIterator()
    {
        return new ListIter();
    }

    public HListIterator listIterator(int index)
    {
        checkIndexAdd(index);

        return new ListIter(index);
    }

    public HList subList(int fromIndex, int toIndex)
    {
        //Creo un nuovo listAdapter che utilizza come vector quello di questa lista cambiando semplicemente gli
        //indici iniziali e finali
        if(fromIndex < 0 || toIndex > size() || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();

        ListAdapter la = new SubList(this, vec,fromIndex + from, toIndex + from);
        return la;
    }

    public int hashCode()
    {
        int hashCode = 1;
        HIterator i = iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }

        return hashCode;
    }

    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        HCollection coll;
        if (o instanceof HList)
            coll = (HList) o;
        else
            return false;
        if (coll.size() != size())
            return false;
        HIterator it = coll.iterator();
        for (int i = 0; i < size(); i++)
            if (!get(from + i).equals(it.next()))
                return false;
        return true;
    }

    /**
     * Oggetto ListIter: implementa tutte le funzionalita' di HListIterator (quindi anche di HIterator).
     * Tutte le optional operations sono state implementate.
     *
     * La documentazione di J2SE v1.4.2 afferma che se avvengono modifiche strutturali alla lista non utilizzando l'iteratore, il
     * comportamento di un iteratore precedentemente generato e' indefinito.
     * Si afferma qui dunque che se si effettuano modifiche strutturali alla lista senza usare l'iteratore, il corretto comportamento
     * di questa iteratore non e' piu' assicurato. (Non verranno sollevate eccezioni o altri comportamenti di segnalazione di errore).
     *
     * @note
     * <pre>
     * NOTA SUL FUNZIONAMENTO INTERNO DI QUESTO ITERATORE.
     *
     *               0   1   2                       0   1   2
     *             | 0 | 1 | 2 |       ===>        | 0 | 1 | 2 |
     *                   ^                                 ^
     *                  cur                               cur
     *
     *              Se forward = true, next() e' stato l'ultimo metodo di ispezione chiamato. La precedente chiamata a next() ha ritornato 1
     *                0   1   2
     *              | 0 | 1 | 2 |
     *                  ^
     *                 cur
     *
     *              Se forward = false, previous() e' stato l'ultimo metodo di ispezione chiamato. La precedente chiamata a previous() ha ritornato 2
     *                0   1   2
     *              | 0 | 1 | 2 |
     *                          ^
     *                         cur
     * </pre>
     *
     * @author Formaggio Alberto
     */
    private class ListIter implements HListIterator
    {
        /**
         * Puntatore all'indice precedente dell'elemento che verra' ritornato ad una successiva
         * invocazione di next() (es. cur = -1 ====> iter.next() = vec[0])
         */
        private int cur;

        /**
         * Permette di capire se l'iteratore e' in una condizione valida o meno
         */
        private boolean valid;

        /**
         * Permette di capire in quale direzione si sta scorrendo la lista
         */
        private boolean forward;

        /**
         * Construct a new iterator placed before the first element
         */
        public ListIter()
        {
            this(0);
        }

        /**
         * Construct a new iterator placed before the element in the position defined by the given index
         *
         * @param starting_pos index of the first element returned by an invocation of {@code next}
         */
        public ListIter(int starting_pos)
        {
            cur = starting_pos - 1;
            valid = false;
            forward = true;
        }

        public boolean hasNext()
        {
            return cur < size() - 1;
        }

        public Object next()
        {
            if(!hasNext())
                throw new NoSuchElementException();

            forward = true;
            valid = true;
            return get(++cur);
        }

        /**
         * Removes from the underlying collection the last element returned by the iterator (optional operation).
         * This method can be called only once per call to next. The behavior of an iterator is unspecified if the
         * underlying collection is modified while the iteration is in progress in any way other than by calling this method.
         *
         * <pre>
         *      Spiegazione modifica del puntatore cur:
         *      | 0 | 1 | 2 |           ===>        | 0 | 1 | 2 |           cur = 1
         *            ^                                     ^
         *           cur                                   cur
         *      Se forward() = true viene rimosso il valore 1.   ===>   | 0 | 2 |           cur = 1, ma il cursore e' dopo il 2 nello scorrimento definito da forward! cur = cur - 1
         *                                                                      ^
         *                                                                     cur
         *                                                       ===>   | 0 | 2 |           cur = 0 e il cursore e' tra 0 e 2: ORA E' CORRETTO
         *                                                                  ^
         *                                                                 cur
         *
         *      Se forward = false viene rimosso il valore 2     ===>   | 0 | 1 |           cur = 1 e il cursore e' prima dell'1 nello scorrimento definito da forward: ORA E' CORRETTO
         *                                                                      ^
         *                                                                     cur
         *</pre>
         *
         * @throws IllegalStateException if the {@code next} method has not
         * yet been called, or the {@code remove} method has already
         * been called after the last call to the {@code next}
         * method
         *
        **/
        public void remove()
        {
            if(!valid)
                throw new IllegalStateException();

            valid = false;

            if(forward)
            {
                //Visto che forward = true, devo eliminare l'elemento nella posizione cur (vedi schema a inizio classe per comprendere funzionamento)
                ListAdapter.this.remove(cur);
                cur--;
            }
            else
            {
                //Visto che forward = false, devo eliminare l'elemento nella posizione cur+1 (vedi schema a inizio classe per comprendere funzionamento)
                ListAdapter.this.remove(cur + 1);
            }
        }

        public boolean hasPrevious()
        {
            return cur > -1;
        }

        public Object previous()
        {
            if(!hasPrevious())
                throw new NoSuchElementException();

            forward = false;
            valid = true;
            return get(cur--);
        }

        public int nextIndex()
        {
            //Vedi schema a inizio classe
            return cur + 1;
        }

        public int previousIndex()
        {
            //Vedi schema a inizio classe
            return cur;
        }

        /**
         * Replaces the last element returned by <code>next</code> or
         * <code>previous</code> with the specified element (optional operation).
         * This call can be made only if neither <code>ListIterator.remove</code> nor
         * <code>ListIterator.add</code> have been called after the last call to
         * <code>next</code> or <code>previous</code>.
         *
         * @param o the element with which to replace the last element returned by
         *          <code>next</code> or <code>previous</code>.
         * @throws IllegalStateException if neither <code>next</code> nor
         *	          <code>previous</code> have been called, or <code>remove</code> or
         *		  <code>add</code> have been called after the last call to
         * 		  <code>next</code> or <code>previous</code>.
         */
        public void set(Object o)
        {
            if(!valid)
                throw new IllegalStateException();

            if(forward)
            {
                //Visto che forward = true, devo modificare l'elemento nella posizione cur (vedi schema a inizio classe per comprendere funzionamento)
                ListAdapter.this.set(cur,o);
            }
            else
            {
                //Visto che forward = false, devo modificare l'elemento nella posizione cur+1 (vedi schema a inizio classe per comprendere funzionamento)
                ListAdapter.this.set(cur + 1,o);
            }
        }

        /**
         * Inserts the specified element into the list (optional operation).  The
         * element is inserted immediately before the next element that would be
         * returned by <code>next</code>, if any, and after the next element that
         * would be returned by <code>previous</code>, if any.  (If the list contains
         * no elements, the new element becomes the sole element on the list.)
         * The new element is inserted before the implicit cursor: a subsequent
         * call to <code>next</code> would be unaffected, and a subsequent call to
         * <code>previous</code> would return the new element.  (This call increases
         * by one the value that would be returned by a call to <code>nextIndex</code>
         * or <code>previousIndex</code>.)
         *
         * @param o the element to insert.
         */
        public void add(Object o)
        {
            valid = false;
            ListAdapter.this.add(++cur,o);
        }
    }

    /**
     * Classe SubList.
     *
     * Questa classe estende la classe ListAdapter aggiungendo un campo parent che fa riferimento al genitore di questa lista.
     * Sono stati sovrascritti soltanto i metodi add e remove che hanno bisogno di informazioni relativi ai loro genitori per funzionare correttamente.<P>
     *
     * @note Si sottolinea la necessita' di usare il campo parent perche' una sottolista puo' essere figlia di altre sottoliste.
     * Se invocassi un metodo con la keyword "super" per richiamare il metodo di superclasse andrei a modificare solamente
     * la lista originale ma non anche tutte le sottoliste intermedie tra questa lista e quella originale.<br>
     * superlist ==> sublist1 ==> sublist2 ==> sublist3.<br>
     * Una modifica a sublist3 si deve ripercuotere su tutte le liste precedenti (con super modificherei solo superlist)<P>
     *
     * La documentazione di J2SE v1.4.2 afferma che se avvengono modifiche strutturali a una lista padre, nei figli il comportamento e'
     * indefinito.
     * Si afferma qui dunque che se si effettuano modifiche strutturali alla lista padre, il corretto comportamento di questa
     * sottolista non e' piu' assicurato. (Non verranno sollevate eccezioni o altro)
     *
     * @author Formaggio Alberto
     */
    private class SubList extends ListAdapter
    {
        /**
         * Genitore di questa sottolista
         */
        private final ListAdapter parent;

        /**
         * Genera una nuova sottolista in grado di vedere soltanto una porzione della lista genitore passata in input.
         * Tale porzione e' definita dagli indici from e to.
         * @param parent_ Lista genitore di questa sottolista
         * @param vec_ Riferimento al vettore della lista genitore
         * @param from_ Posizione iniziale (inclusa) della vista della lista genitore
         * @param to_ Posizione finale (esclusa) della vista della lista genitore
         */
        public SubList(ListAdapter parent_, Vector vec_, int from_, int to_)
        {
            super(from_,to_);
            vec = vec_;
            parent = parent_;
        }

        /**
         * {@inheritDoc}
         *
         <p>Spiegazione index + from - parent.from:  (si osserva che from >= parent.from sempre)

         <pre>
         Lista originale     ==>     Sub 1: from = 1, to = 3      ==>      Sub2: from = 1, to = 3
         | 0 | 1 | 2 |               | 1 | 2 |                             | 1 | 2 |
         </pre>
         <pre>
         Quando faccio sub2.add(1,"toAdd") Devo andare ad inserire l'elemento in posizione <b>1</b> in <b>sub2</b>.
         Poi devo inserire l'elemento in posizione 1 (1 + sub2.from - sub1.from) in sub1.
         Infine devo inserire l'elemento in posizione 2 (1 + sub1.from - originale.from) nella lista originale.
         </pre>
         */
        @Override
        public void add(int index, Object element)
        {
            checkIndexAdd(index);
            parent.add(index + from - parent.from, element);
            to++;
        }


        /**
         * {@inheritDoc}
         *
         <p>Spiegazione index + from - parent.from:  (si osserva che from >= parent.from sempre)

         <pre>
         Lista originale     ==>     Sub 1: from = 1, to = 3      ==>      Sub2: from = 1, to = 3
         | 0 | 1 | 2 |               | 1 | 2 |                             | 1 | 2 |
         </pre>
         <pre>
         Quando faccio sub2.remove(1) devo andare a rimuovere l'elemento in posizione <b>1</b> di <b>sub2</b>.
         Poi devo rimuovere l'elemento in posizione 1 (1 + sub2.from - sub1.from) di sub1.
         Infine devo rimuovere l'elemento in posizione 2 (1 + sub1.from - originale.from) della lista originale.
         </pre>
         */
        @Override
        public Object remove(int index)
        {
            checkIndex(index);
            Object removed = get(index);
            parent.remove(index + from - parent.from);
            to--;
            return removed;
        }
    }
}
