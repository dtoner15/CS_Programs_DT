package Heaps;

import java.util.*;

//Program for testing the built in methods for heaps using priority queues.
public class HeapMain {
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        Scanner input = new Scanner(System.in);

        minHeap.add(5);
        minHeap.add(3);
        minHeap.add(10);
        minHeap.add(8);
        minHeap.add(4);

        System.out.println(minHeap);
        System.out.printf("Smallest Element in the heap: %d%n", minHeap.peek());

        ArrayList<Object> storage = new ArrayList<>();//create a arraylist for storing the removed elements
        while(!minHeap.isEmpty()){
           int removedElements = minHeap.poll();//store the removed elements in a variable. 
           storage.add(removedElements);
        }
        System.out.println("The removed elements are: " + storage);


        System.out.println("Enter a number to put into the heap: ");
        int inputNumber = input.nextInt();//get user input
        minHeap.add(inputNumber);
        System.out.println("Heap after user insertion: " + minHeap);

        minHeap.remove();
        System.out.println("Elements removed from the heap: " + inputNumber);//display the inputed number that will be deleted.
        

        




        input.close();
    }
}
