/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net;

import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * An abstract base class to ease the implementation of the Map interface.
 * 
 * The base class provides three means of support:
 * 1) It provides an isEmpty implementation based upon the abstract size() method.
 * 2) It defines a protected MapEntry class as a concrete implementation of the
 *    entry interface
 * 3) It provides implemenations of the keySet and values methods, based upon use
 *    of a presumed implementation of the entrySet method.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class AbstractMap<K,V> implements Map<K,V> {

  /**
   * Tests whether the map is empty.
   * @return true if the map is empty, false otherwise
   */
  @Override
  public boolean isEmpty() { return size() == 0; }

  //---------------- nested MapEntry class ----------------
  /**
   * A concrete implementation of the Entry interface to be used
   * within a Map implementation.
   *
   * @param <K> the key type
   * @param <V> the value type
   */
  protected static class MapEntry<K,V> implements Entry<K,V> {
    
    /** The k. */
    private K k;  // key
    
    /** The v. */
    private V v;  // value

    /**
     * Instantiates a new map entry.
     *
     * @param key the key
     * @param value the value
     */
    public MapEntry(K key, V value) {
      k = key;
      v = value;
    }

    /* (non-Javadoc)
     * @see net.datastructures.Entry#getKey()
     */
    // public methods of the Entry interface
    public K getKey() { return k; }
    
    /* (non-Javadoc)
     * @see net.datastructures.Entry#getValue()
     */
    public V getValue() { return v; }

    /**
     * Sets the key.
     *
     * @param key the new key
     */
    // utilities not exposed as part of the Entry interface
    protected void setKey(K key) { k = key; }
    
    /**
     * Sets the value.
     *
     * @param value the value
     * @return the v
     */
    protected V setValue(V value) {
      V old = v;
      v = value;
      return old;
    }

    /**
     *  Returns string representation (for debugging only).
     *
     * @return the string
     */
    public String toString() { return "<" + k + ", " + v + ">"; }
  } //----------- end of nested MapEntry class -----------

  // Provides support for keySet() and values() methods, based upon
  // the entrySet() method that must be provided by subclasses

  /**
   * The Class KeyIterator.
   */
  //---------------- nested KeyIterator class ----------------
  private class KeyIterator implements Iterator<K> {
    
    /** The entries. */
    private Iterator<Entry<K,V>> entries = entrySet().iterator();   // reuse entrySet
    
    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext() { return entries.hasNext(); }
    
    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    public K next() { return entries.next().getKey(); }             // return key!
    
    /* (non-Javadoc)
     * @see java.util.Iterator#remove()
     */
    public void remove() { throw new UnsupportedOperationException("remove not supported"); }
  } //----------- end of nested KeyIterator class -----------

  /**
   * The Class KeyIterable.
   */
  //---------------- nested KeyIterable class ----------------
  private class KeyIterable implements Iterable<K> {
    
    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<K> iterator() { return new KeyIterator(); }
  } //----------- end of nested KeyIterable class -----------

  /**
   * Returns an iterable collection of the keys contained in the map.
   *
   * @return iterable collection of the map's keys
   */
  @Override
  public Iterable<K> keySet() { return new KeyIterable(); }

  /**
   * The Class ValueIterator.
   */
  //---------------- nested ValueIterator class ----------------
  private class ValueIterator implements Iterator<V> {
    
    /** The entries. */
    private Iterator<Entry<K,V>> entries = entrySet().iterator();   // reuse entrySet
    
    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext() { return entries.hasNext(); }
    
    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    public V next() { return entries.next().getValue(); }           // return value!
    
    /* (non-Javadoc)
     * @see java.util.Iterator#remove()
     */
    public void remove() { throw new UnsupportedOperationException("remove not supported"); }
  } //----------- end of nested ValueIterator class -----------

  /**
   * The Class ValueIterable.
   */
  //---------------- nested ValueIterable class ----------------
  private class ValueIterable implements Iterable<V> {
    
    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<V> iterator() { return new ValueIterator(); }
  } //----------- end of nested ValueIterable class -----------

  /**
   * Returns an iterable collection of the values contained in the map.
   * Note that the same value will be given multiple times in the result
   * if it is associated with multiple keys.
   *
   * @return iterable collection of the map's values
   */
  @Override
  public Iterable<V> values() { return new ValueIterable(); }
}
