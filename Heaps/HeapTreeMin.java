package Heaps;

import java.util.*;
public class HeapTreeMin {
    private ArrayList<Integer> heap;
    
    public HeapTreeMin(){
        heap = new ArrayList<>();
    }
    //basic equation for getting the parent node.
    private int parentNode(int i){
        return (i - 1) / 2;
    }
        //basic equation for getting the right child
    private int rightChild(int i){
        return 2 * i + 2;
    }

    //basic equation for getting the left child
    private int leftChild(int i){
        return 2 * i + 1;
    }

    private void swap(int i, int j){
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(int value){
        heap.add(value);//add a new value at the end of the heap.

        //get the index of the newely added value.
        int currentIndex = heap.size() - 1;

        //compare the currentNode with it's current parent nodes current index.
        while(currentIndex > 0 && heap.get(currentIndex) < heap.get(parentNode(currentIndex))){
            //swap with parent if current value is less.
            swap(currentIndex, parentNode(currentIndex));
            //move up to the parentNodes index
            currentIndex = parentNode(currentIndex);
        }
         
    }

    //extracts and returns the minimum value from the heap
    public int extractMin(){
        if(heap.isEmpty()){
            throw new RuntimeException("The heap is empty.");
        }

        //The smallest value is at the root
        int min = heap.get(0);

        //Remove the last element in the heap.
        int lastElement = heap.remove(heap.size() - 1);

        if(!heap.isEmpty()){
            heap.set(0, lastElement);

            //bubble down to restore the heap property.
            int currentIndex = 0;
            while(true){
                int left = leftChild(currentIndex);
                int right = rightChild(currentIndex);

                int smallest = currentIndex;

                //Find the smallest value between current, left and right child.
                if(left < heap.size() && heap.get(left) < heap.get(smallest)){
                    smallest = left;
                }

                if(right < heap.size() && heap.get(right) < heap.get(smallest)){
                    smallest = right;
                }

                if(smallest == currentIndex){
                    break;
                }

                //swap the currentindex with the smallest
                swap(currentIndex, smallest);
            }
        }

        return min;
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }


}
