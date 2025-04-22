package Heaps;

//This program tests out the heap tree structure classes I made.
public class HeapTest {
    public static void main(String[] args) {
        HeapTreeMax max = new HeapTreeMax();
        HeapTreeMin min = new HeapTreeMin();

        max.insert(13);
        max.insert(20);
        max.insert(10);
        max.insert(15);
        max.insert(8);

        System.out.println("Extracted Max: " + max.extractMax());
        System.out.println("Extracted Max: " + max.extractMax());

        min.insert(3);
        min.insert(5);
        min.insert(10);
        min.insert(8);
        min.insert(2);

        System.out.println("Extracted Max: " + min.extractMin());
        System.out.println("Extracted Max: " + min.extractMin());
    }
}
