package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Oggetto MapAdapter: adatta le funzionalita' della classe Hashtable nella versione di Java J2ME CLDC 1.1 ad una Mappa la cui interfaccia
 * e' definita in Java J2SE 1.4.2.
 *
 * L'inserimento di chiavi e valori nulli non e' consentito.
 * E' possibile inserire oggetti di tipo qualsiasi (anche diverso tra loro all'interno della lista), il controllo per tipi
 * errati deve essere eseguito a livello di applicazione.
 * Tutte le optional operations sono state implementate.
 *
 * @author Formaggio Alberto
 */
public class MapAdapter implements HMap
{
    /**
     * Hashtable used as adaptee for this MapAdapter
     */
    private Hashtable ht;

    /**
     * Construct an empty map
     */
    public MapAdapter()
    {
        ht = new Hashtable();
    }

    /**
     * Constructs a map containing the elements of the specified map.
     * @param m the map whose elements are placed in this map
     * @throws NullPointerException if the given map is null
     */
    public MapAdapter(HMap m)
    {
        this();
        putAll(m);
    }

    public int size()
    {
        return ht.size();
    }

    public boolean isEmpty()
    {
        return ht.isEmpty();
    }

    /**
     * Returns <code>true</code> if this HMap contains a mapping for the specified
     * key.  More formally, returns <code>true</code> if and only if
     * this HMap contains a mapping for a key <code>k</code> such that
     * <code>(key==null ? k==null : key.equals(k))</code>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this HMap is to be tested
     * @return <code>true</code> if this HMap contains a mapping for the specified
     *         key
     * @throws NullPointerException if the specified key is null and this HMap
     *         does not permit null keys
     */
    public boolean containsKey(Object key)
    {
        if(key == null)
            throw new NullPointerException();
        return ht.containsKey(key);
    }

    /**
     * Returns <code>true</code> if this HMap HMaps one or more keys to the
     * specified value.  More formally, returns <code>true</code> if and only if
     * this HMap contains at least one mapping to a value <code>v</code> such that
     * <code>(value==null ? v==null : value.equals(v))</code>.  This operation
     * will probably require time linear in the HMap size for most
     * implementations of the <code>HMap</code> interface.
     *
     * @param value value whose presence in this HMap is to be tested
     * @return <code>true</code> if this HMap HMaps one or more keys to the
     *         specified value
     * @throws NullPointerException if the specified value is null and this
     *         HMap does not permit null values
     */
    public boolean containsValue(Object value)
    {
        return ht.contains(value);
    }

    /**
     * Returns the value to which the specified key is HMapped,
     * or {@code null} if this HMap contains no mapping for the key.
     *
     * <p>More formally, if this HMap contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>If this HMap permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the HMap
     * contains no mapping for the key; it's also possible that the HMap
     * explicitly HMaps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is HMapped, or
     *         {@code null} if this HMap contains no mapping for the key
     * @throws NullPointerException if the specified key is null and this HMap
     *         does not permit null keys
     */
    public Object get(Object key)
    {
        if(key == null)
          throw new NullPointerException();
        return ht.get(key);
    }

    /**
     * Associates the specified value with the specified key in this HMap
     * (optional operation).  If the HMap previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A HMap
     * <code>m</code> is said to contain a mapping for a key <code>k</code> if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * <code>true</code>.)
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <code>key</code>, or
     *         <code>null</code> if there was no mapping for <code>key</code>.
     *         (A <code>null</code> return can also indicate that the HMap
     *         previously associated <code>null</code> with <code>key</code>,
     *         if the implementation supports <code>null</code> values.)
     * @throws NullPointerException if the specified key or value is null
     *         and this HMap does not permit null keys or values
     */
    public Object put(Object key, Object value)
    {
        return ht.put(key, value);
    }

    /**
     * Removes the mapping for a key from this HMap if it is present
     * (optional operation).   More formally, if this HMap contains a mapping
     * from key <code>k</code> to value <code>v</code> such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The HMap can contain at most one such mapping.)
     *
     * <p>Returns the value to which this HMap previously associated the key,
     * or <code>null</code> if the HMap contained no mapping for the key.
     *
     * <p>If this HMap permits null values, then a return value of
     * <code>null</code> does not <i>necessarily</i> indicate that the HMap
     * contained no mapping for the key; it's also possible that the HMap
     * explicitly HMapped the key to <code>null</code>.
     *
     * <p>The HMap will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the HMap
     * @return the previous value associated with <code>key</code>, or
     *         <code>null</code> if there was no mapping for <code>key</code>.
     * @throws NullPointerException if the specified key is null and this
     *         HMap does not permit null keys
     */
    public Object remove(Object key)
    {
        if(key == null)
            throw new NullPointerException();
        return ht.remove(key);
    }

    /**
     * Copies all of the mappings from the specified HMap to this HMap
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object,Object) put(k, v)} on this HMap once
     * for each mapping from key <code>k</code> to value <code>v</code> in the
     * specified HMap.  The behavior of this operation is undefined if the
     * specified HMap is modified while the operation is in progress.
     *
     * @param t mappings to be stored in this HMap
     * @throws NullPointerException if the specified HMap is null, or if
     *         this HMap does not permit null keys or values, and the
     *         specified HMap contains null keys or values
     */
    public void putAll(HMap t)
    {
        if(t == null)
            throw new NullPointerException();

        HIterator it = t.entrySet().iterator();
        while(it.hasNext())
        {
            MyEntry next = (MyEntry)it.next();
            put(next.getKey(), next.getValue());
        }
    }

    /**
     * Removes all of the mappings from this HMap (optional operation).
     * The HMap will be empty after this call returns.
     *
     */
    public void clear()
    {
        ht.clear();
    }

    public HSet keySet()
    {
        return new KeySet();
    }

    public HCollection values()
    {
        return new ValueCollection();
    }

    public HSet entrySet()
    {
        return new EntrySet();
    }

    public int hashCode()
    {
        return entrySet().hashCode();
    }

    public boolean equals(Object o)
    {
        if(o == null)
            return false;

        if (!(o instanceof MapAdapter))
            return false;

        MapAdapter inputMap = (MapAdapter) o;

        return inputMap.entrySet().equals(entrySet());
    }

    /**
     * Classe MyAbstractCollection
     *
     * This abstract class provides a basic implementation of the methods defined in the {@code HCollection} interface in order to reduce
     * the effort required in order to implement this interface.<p>
     * Hence, the operations defined in this class do not depend on the actual type of the collection (EntrySet, KeySet or ValueCollection) and
     * are defined once and for all so there's no need to override them when implementing an EntrySet, KeySet or ValueCollection.
     * The programmer should generally provide a void (no argument) and <code>Collection</code> constructor, as per the recommendation in the
     * <code>Collection</code> interface specification.<p>
     *
     * @author Formaggio Alberto
     */
    private abstract class MyAbstractCollection implements HCollection
    {
        public int size(){
            return MapAdapter.this.size();
        }

        public boolean isEmpty() {return MapAdapter.this.isEmpty();}

        public Object[] toArray()
        {
            Object[] res = new Object[size()];
            res = toArray(res);

            return res;
        }

        public Object[] toArray(Object[] a)
        {
            //Java lancia eccezione nel caso di ArrayStoreException in automatico
            if(a.length >= size())
                //Ho spazio a sufficienza, metto a null gli elementi dopo la fine della collezione
                for(int i = size(); i < a.length; i++)
                    a[i] = null;
            else
                //Non ho abbastanza spazio in a, creo un nuovo array da riempire
                a = new Object[size()];

            int i = 0;
            HIterator it = iterator();
            while(it.hasNext())
            {
                a[i] = it.next();
                i++;
            }

            return a;
        }

        /**
         * {@inheritDoc} (NOT IMPLEMENTED)
         */
        public boolean add(Object o)
        {
            throw new UnsupportedOperationException();
        }

        public boolean containsAll(HCollection c)
        {
            if(c == null)
                throw new NullPointerException();

            HIterator it = c.iterator();
            while(it.hasNext())
            {
                if(!contains(it.next()))
                    return false;
            }

            return true;
        }

        /**
         * {@inheritDoc} (NOT IMPLEMENTED)
         */
        public boolean addAll(HCollection c)
        {
            throw new UnsupportedOperationException();
        }

        /**
         * Removes all of this collection's elements that are also contained in the
         * specified collection (optional operation).  After this call returns,
         * this collection will contain no elements in common with the specified
         * collection.
         *
         * @param c collection containing elements to be removed from this collection
         * @return <code>true</code> if this collection changed as a result of the
         *         call
         * @throws ClassCastException if the types of one or more elements
         *         in this collection are incompatible with the specified
         *         collection
         *         (<a href="Collection.html#optional-restrictions">optional</a>)
         * @throws NullPointerException if this collection contains one or more
         *         null elements and the specified collection does not support
         *         null elements
         *         (<a href="Collection.html#optional-restrictions">optional</a>)
         *         or if the specified collection is null
         * @see #remove(Object)
         * @see #contains(Object)
         */
        public boolean removeAll(HCollection c)
        {
            if(c == null)
                throw new NullPointerException();

            int previousSize = size();
            HIterator it = c.iterator();
            while(it.hasNext())
            {
                remove(it.next());
            }

            return size() != previousSize;
        }

        /**
         * Retains only the elements in this collection that are contained in the
         * specified collection (optional operation).  In other words, removes from
         * this collection all of its elements that are not contained in the
         * specified collection.
         *
         * @param c collection containing elements to be retained in this collection
         * @return <code>true</code> if this collection changed as a result of the call
         * @throws ClassCastException if the types of one or more elements
         *         in this collection are incompatible with the specified
         *         collection
         * @throws NullPointerException if this collection contains one or more
         *         null elements and the specified collection does not permit null
         *         elements or if the specified collection is null
         * @see #remove(Object)
         * @see #contains(Object)
         */
        public boolean retainAll(HCollection c)
        {
            if(c == null)
                throw new NullPointerException();

            int previousSize = size();
            HIterator it = iterator();
            while(it.hasNext())
            {
                Object next = it.next();
                if (!c.contains(next))
                    remove(next);
            }

            return size() != previousSize;
        }

        /**
         * Removes all of the elements from this collection (optional operation).
         * The collection will be empty after this method returns.
         */
        public void clear()
        {
            MapAdapter.this.clear();
        }

        public int hashCode()
        {
            HIterator it = iterator();
            int code = 0;
            while(it.hasNext())
                code += it.next().hashCode();

            return code;
        }
    }

    /**
     * Class which extends the AbstractCollection implementing an HSet in order to realize the set needed for the {@code entrySet}
     * method of the MapAdapter class.
     *
     * @author Formaggio Alberto
     */
    private class EntrySet extends MyAbstractCollection implements HSet
    {
        /**
         * Removes the specified element from this set if it is present
         * (optional operation).  More formally, removes an element <code>e</code>
         * such that
         * <code>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</code>, if
         * this set contains such an element.  Returns <code>true</code> if this set
         * contained the element (or equivalently, if this set changed as a
         * result of the call).  (This set will not contain the element once the
         * call returns.)
         *
         * @param o object to be removed from this set, if present
         * @return <code>true</code> if this set contained the specified element
         * @throws ClassCastException if the type of the specified element
         *         is incompatible with this set
         * @throws NullPointerException if the specified element is null and this
         *         set does not permit null elements
         */
        public boolean remove(Object o)
        {
            //Contains lancia eccezione se e' null o non e' una entry.
            if(!contains(o))
                return false;

            HEntry e = (HEntry)o;
            MapAdapter.this.remove(e.getKey());
            return true;
        }


        public boolean contains(Object o)
        {
            if(o == null)
                throw new NullPointerException();

            if(!(o instanceof HEntry))
                throw new ClassCastException();

            HEntry e = (HEntry) o;
            Object valueInMapRelatedToKey = MapAdapter.this.get(e.getKey());
            if(valueInMapRelatedToKey == null)
                return false;

            HEntry entryInMap = new MyEntry(MapAdapter.this, e.getKey(), valueInMapRelatedToKey);
            return entryInMap.equals(e);
        }

        public HIterator iterator()
        {
            return new EntrySetIterator();
        }

        public boolean equals(Object o)
        {
            if(o == null)
                return false;

            if (!(o instanceof EntrySet))
                return false;

            EntrySet coll = (EntrySet) o;
            if (coll.size() != size())
                return false;

            boolean areEqual = true;
            HIterator it = iterator();
            while(it.hasNext())
                if(!coll.contains(it.next()))
                {
                    areEqual = false;
                    break;
                }

            return areEqual;
        }

        /**
         * @author Formaggio Alberto
         *
         * Iterates over the entries in the set.
         *
         * @note Note for this class:
         * Keys and values enumerations are not scrolled through by the respective Enumeration (given by the hashtable) at the same time because there's no guarantee
         * in the documentation for what concerns the order of the returned element. Thus, only the key enumeration is used in this iterator.
         */
        protected class EntrySetIterator implements HIterator
        {
            /**
             * Iterates over the keys inside the hashtable.
             */
            protected Enumeration keys;
            /**
             * Key returned by the last call of the method {@code next}. Contains {@code null} if {@code next} has not been called yet after
             * the last call to {@code remove}.
             */
            protected Object lastReturnedKey;

            /**
             * Contructs a new entryset iterator
             */
            public EntrySetIterator()
            {
                keys = ht.keys();
                lastReturnedKey = null;
            }

            public boolean hasNext()
            {
                return keys.hasMoreElements();
            }

            public Object next()
            {
                lastReturnedKey = keys.nextElement();
                HEntry e = new MyEntry(MapAdapter.this, lastReturnedKey, get(lastReturnedKey));
                return e;
            }

            public void remove()
            {
                if(lastReturnedKey == null)
                    throw new IllegalStateException();

                MapAdapter.this.remove(lastReturnedKey);
                lastReturnedKey = null;
            }
        }
    }

    /**
     * Class which extends the EntrySet class overriding the methods depending on the type of the elements inside the set.
     * Keys are unique in the map so the set can use directly the methods defined by the map.
     *
     * @author Formaggio Alberto
     */
    private class KeySet extends EntrySet
    {
        /**
         * Returns <code>true</code> if this set contains the specified element.
         * More formally, returns <code>true</code> if and only if this set
         * contains an element <code>e</code> such that
         * <code>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</code>.
         *
         * @param o element whose presence in this set is to be tested
         * @return <code>true</code> if this set contains the specified element
         * @throws NullPointerException if the specified element is null and this
         *         set does not permit null elements
         */
        @Override
        public boolean contains(Object o)
        {
            return MapAdapter.this.containsKey(o);
        }

        /**
         * Removes the specified element from this set if it is present
         * (optional operation).  More formally, removes an element <code>e</code>
         * such that
         * <code>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</code>, if
         * this set contains such an element.  Returns <code>true</code> if this set
         * contained the element (or equivalently, if this set changed as a
         * result of the call).  (This set will not contain the element once the
         * call returns.)
         *
         * @param o object to be removed from this set, if present
         * @return <code>true</code> if this set contained the specified element
         * @throws NullPointerException if the specified element is null and this
         *         set does not permit null elements
         */
        @Override
        public boolean remove(Object o)
        {
            return MapAdapter.this.remove(o) != null;
        }

        @Override
        public HIterator iterator() { return new KeySetIterator(); }

        @Override
        public boolean equals(Object o)
        {
            if(o == null)
                return false;

            if (!(o instanceof KeySet))
                return false;

            KeySet coll = (KeySet) o;
            if (coll.size() != size())
                return false;

            boolean areEqual = true;
            HIterator it = iterator();
            while(it.hasNext())
                if(!coll.contains(it.next()))
                {
                    areEqual = false;
                    break;
                }

            return areEqual;
        }

        /**
         * Iterates over the set of keys.
         * All the methods but {@code next} are the same as the EntrySetIterator, therefore only next is overwritten.
         *
         * @author Alberto Formaggio
         */
        private class KeySetIterator extends EntrySetIterator
        {
            @Override
            public Object next()
            {
                lastReturnedKey = keys.nextElement();
                return lastReturnedKey;
            }
        }
    }

    /**
     * A collection containing the values in the map.
     * Extends the functionalities defined in MyAbstractCollection class in order to realize the {@code values} method defined
     * by MapAdapter
     *
     * @author Formaggio Alberto
     */
    private class ValueCollection extends MyAbstractCollection
    {
        public HIterator iterator() { return new ValuesIterator(); }

        /**
         * Removes a single instance of the specified element from this
         * collection, if it is present (optional operation).  More formally,
         * removes an element <code>e</code> such that
         * <code>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</code>, if
         * this collection contains one or more such elements.  Returns
         * <code>true</code> if this collection contained the specified element (or
         * equivalently, if this collection changed as a result of the call).
         *
         * <b>WARNING!</b>
         * Since the documentation doesn't say anything about how many values or which value should be removed, the remove method
         * just removes the one random entry in the map containing the given value, if present.
         *
         * @param o element to be removed from this collection, if present
         * @return <code>true</code> if an element was removed as a result of this call
         * @throws NullPointerException if the specified element is null and this
         *         collection does not permit null elements
         */
        @Override
        public boolean remove(Object o)
        {
            if(!contains(o))
                return false;

            Enumeration keys = ht.keys();
            while(keys.hasMoreElements())
            {
                Object key = keys.nextElement();
                //Tutti i valori e le chiavi sono diverse da null e tutte le chiavi ritornate da keys sono
                //Presenti nella mappa quindi get non puo' ritornare null
                if(MapAdapter.this.get(key).equals(o))
                {
                    MapAdapter.this.remove(key);
                    break;
                }
            }
            return true;
        }

        /**
         * Returns <code>true</code> if this collection contains the specified element.
         * More formally, returns <code>true</code> if and only if this collection
         * contains at least one element <code>e</code> such that
         * <code>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</code>.
         *
         * @param o element whose presence in this collection is to be tested
         * @return <code>true</code> if this collection contains the specified
         *         element
         * @throws NullPointerException if the specified element is null and this
         *         collection does not permit null elements
         */
        public boolean contains(Object o)
        {
            return MapAdapter.this.containsValue(o);
        }

        public boolean equals(Object o)
        {
            if(o == null)
                return false;

            if (!(o instanceof ValueCollection))
                return false;

            ValueCollection coll = (ValueCollection) o;
            if (coll.size() != size())
                return false;

            boolean areEqual = true;
            HIterator it = iterator();
            while(it.hasNext())
                if(!coll.contains(it.next()))
                {
                    areEqual = false;
                    break;
                }

            return areEqual;
        }

        /**
         * Iterates over the values.
         * In order to do so, this class uses an EntrySetIterator using its functionalities for hasNext and remove (here defined as wrapper methods).
         * The {@code next} method is instead different.
         */
        private class ValuesIterator implements HIterator
        {
            /**
             * Iterates over the entries of the map
             */
            private HIterator it = entrySet().iterator();

            public Object next()
            {
                MyEntry e = (MyEntry)it.next();
                return e.getValue();
            }

            public boolean hasNext()
            {
                return it.hasNext();
            }

            public void remove()
            {
                it.remove();
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @author Formaggio Alberto
     */
    private static class MyEntry implements HMap.HEntry
    {
        /**
         * Key of this entry. Defined final because the key cannot be modified during this Entry lifetime.
         */
        final private Object KEY;
        /**
         * The value associated to the given key
         */
        private Object val;
        /**
         * The map that owns this entry.
         */
        final private MapAdapter OWNER;

        /**
         * Creates a new entry with the given values and associates it to the map called the constructor.
         * @param owner_ The map which owns this entry
         * @param k key
         * @param v value
         */
        public MyEntry(MapAdapter owner_, Object k, Object v)
        {
            KEY = k;
            val = v;
            OWNER = owner_;
        }

        public Object getKey()
        {
            return KEY;
        }

        public Object getValue()
        {
            return val;
        }

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the HMap.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the HMap (by the iterator's <code>remove</code> operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws NullPointerException if the backing HMap does not permit
         *         null values, and the specified value is null
         */
        public Object setValue(Object value)
        {
            Object oldValue = val;
            OWNER.remove(getKey());
            OWNER.put(getKey(),value);
            val = value;
            return oldValue;
        }

        public boolean equals(Object o)
        {
            if(o == null)
                return false;

            if(!(o instanceof MyEntry))
                return false;
            MyEntry e = (MyEntry) o;

            //Non fallisce mai perche' le chiavi della mappa non possono essere nulle.
            return getKey().equals(e.getKey()) && getValue().equals(e.getValue());
        }

        public int hashCode()
        {
            return (getKey()==null ? 0 : getKey().hashCode()) ^ (getValue()==null ? 0 : getValue().hashCode());
        }
    }
}

