package AVLTrees;

public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        tree.delete(40);
        tree.delete(5);

        tree.search(40);
        tree.search(50);
         
 
        System.out.println("Inorder: ");
        tree.inOrder();
        System.out.println("\nPreorder: ");
        tree.preOrder();
        System.out.println("\nPostorder: ");
        tree.postOrder();
      
    }//end of main method
}//end of class
