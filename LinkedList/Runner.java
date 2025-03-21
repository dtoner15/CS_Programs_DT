package Chapter20.LinkedList;


//This class is for running the linked list logic.
public class Runner {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        //All the different operations for linkedList.
        list.insert(18);
        list.insert(45);
        list.insert(12);
        list.insertAtStart(25);
        list.insertAt(0,55);
        list.deleteAt(2);

        //Show the results
        list.show();

    }
}
