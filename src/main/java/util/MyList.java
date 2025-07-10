package util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<E> implements List<E> {

    private Object[] myArray;
    private final Object NULLOBJECT = new Object();

    public MyList(){
        myArray = new Object[10];
        for(int i = 0; i < myArray.length; i++){
            myArray[i] = NULLOBJECT;
        }
    }

    @Override
    public int size() {
        for(int i = 0; i < myArray.length; i++) {
            if(myArray[i] == NULLOBJECT) return i;
        }
        return myArray.length;
        //throw new RuntimeException("Something went wrong!");
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < myArray.length; i++) {
            if(myArray[i] == o) return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        for(int i = 0; i < size(); i++) {array[i] = myArray[i];}
        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        for(int i = 0; i < size(); i++) {array[i] = (T) myArray[i];}
        return array;
    }

    @Override
    public boolean add(E e) {
        if(size() > myArray.length-3) {
            increaseSize();
        }
        myArray[size()] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if(index < 0) { throw new IndexOutOfBoundsException(); }
        if(index >= size()) { throw new IndexOutOfBoundsException(); }
        if(size() > myArray.length-3) {
            increaseSize();
        }

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o : c){
            if(!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E e : c) {add(e);};
        return !c.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for(Object o : c){
            changed = changed | remove(o);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        for(int i = 0; i < myArray.length; i++){
            myArray[i]=NULLOBJECT;
        }
    }

    @Override
    public E get(int index) {
        if(index < 0) { throw new IndexOutOfBoundsException(); }
        if(index >= size()) { throw new IndexOutOfBoundsException(); }
        return (E) myArray[index];
    }

    @Override
    public E set(int index, E element) {
        if(index < 0) { throw new IndexOutOfBoundsException(); }
        if(index >= size()) { throw new IndexOutOfBoundsException(); }
        E old = (E) myArray[index];
        myArray[index] = element;
        return old;
    }


    @Override
    public E remove(int index) {
        E old = (E) myArray[index];
        for(int i = index; i < size(); i++) {
            myArray[i] = myArray[i+1];
        }
        return old;
    }


    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if(index == -1) return false;
        remove(index);
        return true;
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size(); i++) {
            if(myArray[i] == o) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for(int i = 0; i < size(); i++) {
            if(myArray[i] == o) lastIndex = i;
        }
        return lastIndex;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return myArray[index] != NULLOBJECT;
            }

            @Override
            public E next() {
                index++;
                return (E)(myArray[index-1]);
            }
        };
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private  void increaseSize() {
        Object[] newArray = new Object[myArray.length + 10];
        for(int i = 0; i < myArray.length; i++) {newArray[i] = myArray[i];}
        for(int i = myArray.length; i < newArray.length; i++) {newArray[i] = NULLOBJECT;}
        myArray = newArray;
        System.out.println("new array size: " + myArray.length);
    }

}
