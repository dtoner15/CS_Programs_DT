package Chapter20.LinkedList;

//We are trying to create our own linkedlist class so that we understand everything under the hood.
public class LinkedList {
    Node head;//Create the head of the linkedList

    //This method inserts elements at the end of the linkedList
    public void insert(int data){
        Node node = new Node();//Create a node object 
        node.data = data;//Assign the data to the new nodes data field
        node.next = null;//Setting the node's 'next' pointer to null because it is good practice to in java to clarify the default value.

        if(head == null){
            head = node;
        }else{
            Node n = head;//Creates a reference 'n' that points to the head node.
            //Traverse through the linkedlist until the last node whose 'next' pointer is null
            while(n.next != null){
                n = n.next;//continue to jump to next node. 
            }
            n.next = node;//Link the current last node ('n') to the new node, making the new node the new last node.


        }
    }

    //This method obviously is for inserting a node at the start of a linkedList.
    public void insertAtStart(int data){
        Node node = new Node();
        node.data = data;
        node.next = null;//Just putting the default setting for node.next in just for clarification. 
        node.next = head;//since head is the first value we want to assign head to node.next before assigning node to head.
        head = node;//Assign node to head
    }

    public void insertAt(int index, int data){
        Node node = new Node();
        node.data = data;
        node.next = null;

        if(index == 0){//Index is 0 so insertAtStart the index 0 so that we can put a piece of data at begining of linkedList
            insertAtStart(data);//insert the data you entered at the start.
            return;//return so that it stops after the insertAtStart and avoids the other indicies
        //We use the else loop so that it doesn't print the index twice if it's at 0. Because it will insert it once and then again when it runs
        }else{//through the for loop.
        Node n = head;
        //Traverse the list to the node before the desired insertion index.
        //Iterate up to index-1 to reach the node preceding the insertion point.
        for(int i = 0; i < index-1; i++){
            n = n.next;//start from head and loop through linked list
        }
        node.next = n.next;//Set the new node's 'next' pointer to the node that was originally after 'n'.
        n.next = node;//Set the new node's 'next' pointer to the node that was originally after 'n'.
        } 
    }

    public void deleteAt(int index){
 
        if(index == 0){//This is what you do when you want to delete the first element
            head = head.next;
        }else{
            Node n = head;// Create a temporary reference 'n' pointing to the head node.
            Node n1 = null;//just so we can reference it.
            // Traverse to the node before the node to be deleted (index - 1)."
            for(int i = 0; i < index - 1; i++){
                n = n.next;//continue to jump to next node. 
            }

            n1 = n.next;//n.next equals n1 which means it will take the node after head.
            //Ex. This takes the third node techincally or two nodes after head and makes it equal to the next node so the fourth and assigns the 
            //second node to the fourth node skipping the third node so that the third is deleted.
            // 'n1' now refers to the node to be deleted." <- To put it simply.
            n.next = n1.next; 
            
            System.out.println("delete N1: " + n1.data);
        
            
        }
    }

    //This method is just to print out the results of the linkedList
    public void show(){
        Node node = head;//start from the head/ create new node.
        
        while(node.next != null){//suppose to print the node.
            System.out.println(node.data);
            node = node.next;
        }
        System.out.println(node.data);//The last value would not print because once we reach the last value it is null so it wouldn't print.
    
    }

}
