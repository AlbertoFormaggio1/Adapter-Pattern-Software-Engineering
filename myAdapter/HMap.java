package myAdapter;

/**
 * <P>
 * An object that maps keys to values.  A map cannot contain duplicate keys;
 *  each key can map to at most one value.
 *
 *  <p>This interface takes the place of the <code>Dictionary</code> class, which
 *  was a totally abstract class rather than an interface.
 *
 *  <p>The <code>HMap</code> interface provides three <i>collection views</i>, which
 *  allow a map's contents to be viewed as a set of keys, collection of values,
 *  or set of key-value mappings.  The <i>order</i> of a map is defined as
 *  the order in which the iterators on the map's collection views return their
 *  elements.  Some map implementations, like the <code>TreeMap</code> class, make
 *  specific guarantees as to their order; others, like the <code>HashMap</code>
 *  class, do not.
 *
 *  <p>Note: great care must be exercised if mutable objects are used as map
 *  keys.  The behavior of a map is not specified if the value of an object is
 *  changed in a manner that affects equals comparisons while the object is a
 *  key in the map.  A special case of this prohibition is that it is not
 *  permissible for a map to contain itself as a key.  While it is permissible
 *  for a map to contain itself as a value, extreme caution is advised: the
 *  equals and hashCode methods are no longer well defined on a such a map.
 *
 *  <p>All general-purpose map implementation classes should provide two
 *  "standard" constructors: a void (no arguments) constructor which creates an
 *  empty map, and a constructor with a single argument of type <code>Map</code>,
 *  which creates a new map with the same key-value mappings as its argument.
 *  In effect, the latter constructor allows the user to copy any map,
 *  producing an equivalent map of the desired class.  There is no way to
 *  enforce this recommendation (as interfaces cannot contain constructors) but
 *  all of the general-purpose map implementations in the SDK comply.
 *
 *  <p>The "destructive" methods contained in this interface, that is, the
 *  methods that modify the map on which they operate, are specified to throw
 *  <code>UnsupportedOperationException</code> if this map does not support the
 *  operation.  If this is the case, these methods may, but are not required
 *  to, throw an <code>UnsupportedOperationException</code> if the invocation would
 *  have no effect on the map.  For example, invoking the <A HREF="HMap.html#putAll(myAdapter.HMap)"><CODE>putAll(HMap)</CODE></A>
 *  method on an unmodifiable map may, but is not required to, throw the
 *  exception if the map whose mappings are to be "superimposed" is empty.
 *
 *  <p>Some map implementations have restrictions on the keys and values they
 *  may contain.  For example, some implementations prohibit null keys and
 *  values, and some have restrictions on the types of their keys.  Attempting
 *  to insert an ineligible key or value throws an unchecked exception,
 *  typically <code>NullPointerException</code> or <code>ClassCastException</code>.
 *  Attempting to query the presence of an ineligible key or value may throw an
 *  exception, or it may simply return false; some implementations will exhibit
 *  the former behavior and some will exhibit the latter.  More generally,
 *  attempting an operation on an ineligible key or value whose completion
 *  would not result in the insertion of an ineligible element into the map may
 *  throw an exception or it may succeed, at the option of the implementation.
 *  Such exceptions are marked as "optional" in the specification for this
 *  interface.
 *
 *  */
public interface HMap
{
    /**
     * Returns the number of key-value mappings in this HMap.  If the
     * HMap contains more than <code>Integer.MAX_VALUE</code> elements, returns
     * <code>Integer.MAX_VALUE</code>.
     *
     * @return the number of key-value mappings in this HMap
     */
    int size();

    /**
     * Returns <code>true</code> if this HMap contains no key-value mappings.
     *
     * @return <code>true</code> if this HMap contains no key-value mappings
     */
    boolean isEmpty();

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
 * @throws ClassCastException if the key is of an inappropriate type for
 *         this HMap
 *         (<a href="Collection.html#optional-restrictions">optional</a>)
 * @throws NullPointerException if the specified key is null and this HMap
 *         does not permit null keys
 *         (<a href="Collection.html#optional-restrictions">optional</a>)
 */
    boolean containsKey(Object key);

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
     * @throws ClassCastException if the value is of an inappropriate type for
     *         this HMap
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified value is null and this
     *         HMap does not permit null values
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    boolean containsValue(Object value);

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
     * @throws ClassCastException if the key is of an inappropriate type for
     *         this HMap
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this HMap
     *         does not permit null keys
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    Object get(Object key);

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
     * @throws UnsupportedOperationException if the <code>put</code> operation
     *         is not supported by this HMap
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this HMap
     * @throws NullPointerException if the specified key or value is null
     *         and this HMap does not permit null keys or values
     * @throws IllegalArgumentException if some property of the specified key
     *         or value prevents it from being stored in this HMap
     */
    Object put(Object key, Object value);

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
     * @throws UnsupportedOperationException if the <code>remove</code> operation
     *         is not supported by this HMap
     * @throws ClassCastException if the key is of an inappropriate type for
     *         this HMap
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this
     *         HMap does not permit null keys
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    Object remove(Object key);

    /**
     * Copies all of the mappings from the specified HMap to this HMap
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object,Object) put(k, v)} on this HMap once
     * for each mapping from key <code>k</code> to value <code>v</code> in the
     * specified HMap.  The behavior of this operation is undefined if the
     * specified HMap is modified while the operation is in progress.
     *
     * @param t mappings to be stored in this HMap
     * @throws UnsupportedOperationException if the <code>putAll</code> operation
     *         is not supported by this HMap
     * @throws ClassCastException if the class of a key or value in the
     *         specified HMap prevents it from being stored in this HMap
     * @throws NullPointerException if the specified HMap is null, or if
     *         this HMap does not permit null keys or values, and the
     *         specified HMap contains null keys or values
     * @throws IllegalArgumentException if some property of a key or value in
     *         the specified HMap prevents it from being stored in this HMap
     */
    void putAll(HMap t);

    /**
     * Removes all of the mappings from this HMap (optional operation).
     * The HMap will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <code>clear</code> operation
     *         is not supported by this HMap
     */
    void clear();

    /**
     * Returns a {@link HSet} view of the keys contained in this HMap.
     * The set is backed by the HMap, so changes to the HMap are
     * reflected in the set, and vice-versa.  If the HMap is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <code>remove</code> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the HMap, via the
     * <code>Iterator.remove</code>, <code>Set.remove</code>,
     * <code>removeAll</code>, <code>retainAll</code>, and <code>clear</code>
     * operations.  It does not support the <code>add</code> or <code>addAll</code>
     * operations.
     *
     * @return a set view of the keys contained in this HMap
     */
    HSet keySet();

    /**
     * Returns a {@link HCollection} view of the values contained in this HMap.
     * The collection is backed by the HMap, so changes to the HMap are
     * reflected in the collection, and vice-versa.  If the HMap is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <code>remove</code> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the HMap, via the <code>Iterator.remove</code>,
     * <code>Collection.remove</code>, <code>removeAll</code>,
     * <code>retainAll</code> and <code>clear</code> operations.  It does not
     * support the <code>add</code> or <code>addAll</code> operations.
     *
     * @return a collection view of the values contained in this HMap
     */
    HCollection values();

    /**
     * Returns a {@link HSet} view of the mappings contained in this HMap.
     * The set is backed by the HMap, so changes to the HMap are
     * reflected in the set, and vice-versa.  If the HMap is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <code>remove</code> operation, or through the
     * <code>setValue</code> operation on a HMap entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the HMap, via the <code>Iterator.remove</code>,
     * <code>Set.remove</code>, <code>removeAll</code>, <code>retainAll</code> and
     * <code>clear</code> operations.  It does not support the
     * <code>add</code> or <code>addAll</code> operations.
     *
     * @return a set view of the mappings contained in this HMap
     */
    HSet entrySet();

    /**
     * Compares the specified object with this HMap for equality.  Returns
     * <code>true</code> if the given object is also a HMap and the two HMaps
     * represent the same mappings.  More formally, two HMaps <code>m1</code> and
     * <code>m2</code> represent the same mappings if
     * <code>m1.entrySet().equals(m2.entrySet())</code>.  This ensures that the
     * <code>equals</code> method works properly across different implementations
     * of the <code>HMap</code> interface.
     *
     * @param o object to be compared for equality with this HMap
     * @return <code>true</code> if the specified object is equal to this HMap
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this HMap.  The hash code of a HMap is
     * defined to be the sum of the hash codes of each entry in the HMap's
     * <code>entrySet()</code> view.  This ensures that <code>m1.equals(m2)</code>
     * implies that <code>m1.hashCode()==m2.hashCode()</code> for any two HMaps
     * <code>m1</code> and <code>m2</code>, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * @return the hash code value for this HMap
     * @see HMap.HEntry#hashCode()
     * @see Object#equals(Object)
     * @see #equals(Object)
     */
    int hashCode();

    /**
     * <P>
     * A map entry (key-value pair).  The <code>Map.entrySet</code> method returns
     *  a collection-view of the map, whose elements are of this class.  The
     *  <i>only</i> way to obtain a reference to a map entry is from the
     *  iterator of this collection-view.  These <code>Map.Entry</code> objects are
     *  valid <i>only</i> for the duration of the iteration; more formally,
     *  the behavior of a map entry is undefined if the backing map has been
     *  modified after the entry was returned by the iterator, except through
     *  the iterator's own <code>remove</code> operation, or through the
     *  <code>setValue</code> operation on a map entry returned by the iterator.
     * <P>
     */
    static interface HEntry
    {
        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         */
        Object getKey();

        /**
         * Returns the value corresponding to this entry.  If the mapping
         * has been removed from the backing HMap (by the iterator's
         * <code>remove</code> operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry
         */
        Object getValue();

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the HMap.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the HMap (by the iterator's <code>remove</code> operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws UnsupportedOperationException if the <code>put</code> operation
         *         is not supported by the backing HMap
         * @throws ClassCastException if the class of the specified value
         *         prevents it from being stored in the backing HMap
         * @throws NullPointerException if the backing HMap does not permit
         *         null values, and the specified value is null
         * @throws IllegalArgumentException if some property of this value
         *         prevents it from being stored in the backing HMap
         */
        Object setValue(Object value);

        /**
         * Compares the specified object with this entry for equality.
         * Returns <code>true</code> if the given object is also a HMap entry and
         * the two entries represent the same mapping.  More formally, two
         * entries <code>e1</code> and <code>e2</code> represent the same mapping
         * if<pre>
         *     (e1.getKey()==null ?
         *      e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &amp;&amp;
         *     (e1.getValue()==null ?
         *      e2.getValue()==null : e1.getValue().equals(e2.getValue()))
         * </pre>
         * This ensures that the <code>equals</code> method works properly across
         * different implementations of the <code>HMap.Entry</code> interface.
         *
         * @param o object to be compared for equality with this HMap entry
         * @return <code>true</code> if the specified object is equal to this HMap
         *         entry
         */
        boolean equals(Object o);

        /**
         * Returns the hash code value for this HMap entry.  The hash code
         * of a HMap entry <code>e</code> is defined to be: <pre>
         *     (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
         *     (e.getValue()==null ? 0 : e.getValue().hashCode())
         * </pre>
         * This ensures that <code>e1.equals(e2)</code> implies that
         * <code>e1.hashCode()==e2.hashCode()</code> for any two Entries
         * <code>e1</code> and <code>e2</code>, as required by the general
         * contract of <code>Object.hashCode</code>.
         *
         * @return the hash code value for this HMap entry
         * @see Object#hashCode()
         * @see Object#equals(Object)
         * @see #equals(Object)
         */
        int hashCode();
    }
}
