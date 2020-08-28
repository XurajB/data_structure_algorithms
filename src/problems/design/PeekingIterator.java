package problems.design;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Given an Iterator class interface with methods: next() and hasNext(),
 * design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
 */
public class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private Integer peekedValue = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public Integer peek() {
        if (peekedValue == null) {
            // we are not told to do when empty or null, so better to throw exception
            if (!iterator.hasNext()) {
                throw new NoSuchElementException();
            }
            peekedValue = iterator.next();
        }
        return peekedValue;
    }

    @Override
    public boolean hasNext() {
        return peekedValue != null || iterator.hasNext();
    }

    @Override
    public Integer next() {
        if (peekedValue != null) {
            Integer toReturn = peekedValue;
            peekedValue = null; // next peek will be different
            return toReturn;
        }
        // check if we have next
        // this is per java iterator spec, throw no such element if empty
        if (!iterator.hasNext()) {
            throw new NoSuchElementException();
        }
        return iterator.next();
    }
}
