package util;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayList<T> implements List<T>{

    /**
     * Initial capacity of the ArrayList
     */
    private static final int INITIAL_SIZE = 10;

    /**
     * Empty array at Class scope
     */
    private Object[] storageArray;
    /**
     * ArrayList Size of what data is actually present
     */
    private int size;

    /**
     * Current size of Index that would need to be expanded
     * This value is the actual data spaces allocated for the ArrayList, abstracted value
     */
    private int REAL_SIZE;

    /**
     * ArrayList constructor with initial capacity provided
     */
    public ArrayList(int capacity){
        if(capacity > 0){
            this.storageArray = new Object[capacity];
            REAL_SIZE = capacity;
        } else if (capacity == 0){
            this.storageArray = new Object[]{};
            REAL_SIZE = 0;
        } else{
            throw new IllegalArgumentException("Invalid capacity : " + capacity);
        }
    }

    /**
     * Default constructor initialized to the default size value
     */
    public ArrayList(){
        this.storageArray = new Object[INITIAL_SIZE];
        REAL_SIZE = 10;

    }


    /**
     * Adds inputted Generic data into ArrayList
     * Checks for overflow and expands Array
     */
    public void add(T data){
        this.storageArray[size] = data;
        size++;

        //System.out.println((float)size/(float)REAL_SIZE);
        //System.out.println("Data added.");

        if((float)size/(float)REAL_SIZE > 0.7){
            doubleStorageArray();
        }

    }


    /**
     * Doubles the size of the storage array when it reaches 70% capacity
     * Copies over data
     */
    private void doubleStorageArray(){
        Object[] tempStorageArray = new Object[REAL_SIZE*2];
        for (int i = 0; i < this.storageArray.length; i++) {
            if(storageArray[i] != null){
                tempStorageArray[i] = storageArray[i];
            }
        }
        REAL_SIZE = REAL_SIZE*2;
        //System.out.println(REAL_SIZE);
        storageArray = tempStorageArray;

    }

    //when items get removed you'll need to refactor the array
    public void refactorStorageArray(){
        Object[] tempStorageArray = new Object[REAL_SIZE];
        int forIndex = size;
        int tempIndex = 0;


        for (int i = 0; i < forIndex; i++) {
            if(storageArray[i] != null){
                tempStorageArray[tempIndex] = storageArray[i];
                tempIndex++;
            }
            else{
                size--;
            }
        }
        storageArray = tempStorageArray;

    }

    /**
     * Retrieves data at a certain index
     */
    public T get(int index){
        //System.out.println("Data was retrieved.");
        return (T) this.storageArray[index];

    }

    /**
     * Checks if the ArrayList contains the inputted data
     */
    public boolean contains(T data){
        Boolean check = false;
        for(Object val : storageArray){
            if(val != null && val.equals(data)){
                check = true;
            }
        }
        System.out.println("Data container checked.");
        return check;
    }

    /**
     * Returns size of the ArrayList, static value of the ArrayList class
     */
    public int size(){
        return size;
    }

    public boolean remove(T data){
        boolean check = false;
        for (int i = 0; i < size; i++) {
            if(storageArray[i].equals(data)){
                storageArray[i] = null;
                check = true;
            }
        }
        refactorStorageArray();
        return check;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            T[] arrayList = (T[])storageArray;
            int index = 0;

            @Override
            public boolean hasNext() {
                return size != 0;
            }

            @Override
            public T next() {

                T data = null;

                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }

                data = arrayList[index];
                index++;


                return data;
            }
        };
    }




}
