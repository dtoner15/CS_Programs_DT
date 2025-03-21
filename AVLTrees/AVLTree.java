package AVLTrees;

public class AVLTree {
    Node root;//root of the tree

    //returns the height of current node or 0 if there is no node
    private int height(Node currNode){
        if(currNode == null)
           return 0; 
        else
        return currNode.height;
    }

    //calculates the balance factor
    private int balanceFactor(Node currNode){
        if(currNode == null)
            return 0;
        else
        return height(currNode.leftNode) - height(currNode.rightNode);
    }

    private Node leftRotate(Node x){
        Node y = x.rightNode; //y is the current right subtree of x
        Node t2 = y.leftNode; //t2 is currently the left child of y's leftNode

        y.leftNode = x;//move y to the head or root node by making x the left child of y
        x.rightNode = t2;//move t2 over to the left subtree on the right side by making it the child of x

        //update the heights of each side of the tree and the tree overall
        x.height = 1 + Math.max(height(x.leftNode), height(x.rightNode));
        y.height = 1 + Math.max(height(y.leftNode), height(y.rightNode));

        //return the new root
        return y;
    }

    private Node rightRotate(Node y){
        Node x = y.leftNode;// x is currently the left child of y 
        Node t2 = x.rightNode;// t2 is currently the right child of x

        x.rightNode = y;//move x to the head or root by making y the right child of x
        y.leftNode = t2;//move t2 over to the right tree on the left side by making it the child of y

        //update the heights of each side of the tree and the tree overall
        x.height = 1 + Math.max(height(x.leftNode), height(x.rightNode));
        y.height = 1 + Math.max(height(y.leftNode), height(y.rightNode));

        //return the new root
        return x;
    }

    public void insert(int data){
        //Set root to helper method so that the data gets inputed into the tree
        root = insertHelper(root, data);
    }

    //Main logic for inserting nodes
    private Node insertHelper(Node currNode, int data){
        if(currNode == null){
            return new Node(data);
        }else if(data < currNode.data){
            currNode.leftNode = insertHelper(currNode.leftNode, data);
        }else if(data > currNode.data){
            currNode.rightNode = insertHelper(currNode.rightNode, data);
        }else{
        return currNode;
        }

        //AVL implementation starts here

        //update height of ancestor node
        currNode.height = 1 + Math.max(height(currNode.leftNode), height(currNode.rightNode));

        //get balance factor
        int balance = balanceFactor(currNode);

        //Left Left case
        if(balance > 1 && data < currNode.leftNode.data ){
            return rightRotate(currNode);
        //right right case
        }else if(balance < -1 && data > currNode.rightNode.data){
            return leftRotate(currNode);
        //left right case 
        }else if(balance > 1 && data > currNode.leftNode.data){
            currNode.leftNode = leftRotate(currNode.leftNode);
            return rightRotate(currNode);
        //Right Left case
        }else if(balance < -1 && data < currNode.rightNode.data){
            currNode.rightNode = rightRotate(currNode.rightNode);
            return leftRotate(currNode);
        }
        return currNode;//updates the tree if an avl rotation is needed
    }

    //search for a node in a tree 
    public boolean search(int data){
        if(searchHelper(root, data)){
            System.out.println(data + " was found");
        return true;
        }else{
            System.out.println(data + " could not be found");
            return false;
        }
        
    }

    //Main logic for searching for a node in a tree
    private boolean searchHelper(Node currNode, int data){
        if(currNode == null){
            return false;
        }else if(currNode.data == data){
            return true;
        }else if(data < currNode.data){//compare the root node with the current node if less than search the left subtree
            return searchHelper(currNode.leftNode, data);
        }else{
            return searchHelper(currNode.rightNode, data);
        }
    }

    //main method for deleting nodes
    public void delete(int data){
        if(searchHelper(root, data)){
            deleteHelper(root, data);
            System.out.println(data + " has been deleted");
        }else{
            System.out.println(data  + " could not be found");
        }
    }

    //main logic for deleting nodes
    private Node deleteHelper(Node currNode, int data){
        if(currNode == null) {
			return new Node(data);//currentNode is null so we want to return that null value
		}
		else if(data < currNode.data) {
			currNode.leftNode = deleteHelper(currNode.leftNode, data);
		}
		else if(data > currNode.data) {
			currNode.rightNode = deleteHelper(currNode.rightNode, data);
		}
		else { // node found
			if(currNode.leftNode == null && currNode.rightNode == null) {
				currNode = null;
			}
			else if(currNode.rightNode != null) { //find a successor to replace this node
                //make the currentNode = to the successor(currNode) so that we have the currentNode ready to be searched for the successor
				currNode.data = successor(currNode);
                //deletes the node found from the successor method
				currNode.rightNode = deleteHelper(currNode.rightNode, currNode.data);
			}
			else if(currNode.leftNode != null) { //find a predecessor to replace this node
                //make the currentNode = to the predecessor(currNode) so that we have the currentNode ready to be searched for the predecessor
				currNode.data = predecessor(currNode);
                //deletes the node found from the predecessor method
				currNode.leftNode = deleteHelper(currNode.leftNode, currNode.data);
			}else{

            }
            return currNode;
		}

        //AVL implementation starts here

        //update the height of the ancestor node
        currNode.height = 1 + Math.max(height(currNode.leftNode), height(currNode.rightNode));

        //get balance factor
        int balance = balanceFactor(currNode);

        //Left Left case
        if(balance > 1 && data < currNode.leftNode.data ){
            return rightRotate(currNode);
        //right right case
        }else if(balance < -1 && data > currNode.rightNode.data){
            return leftRotate(currNode);
        //left right case 
        }else if(balance > 1 && data > currNode.leftNode.data){
            //rotate the left subtree using left rotation
            currNode.leftNode = leftRotate(currNode.leftNode);
            //then use a right rotation
            return rightRotate(currNode);
        //Right Left case
        }else if(balance < -1 && data > currNode.rightNode.data){
            //rotate the right subtree using right rotation
            currNode.rightNode = rightRotate(currNode.rightNode);
            //then rotate it left
            return leftRotate(currNode);
        }
        return currNode;//updates the tree if an avl rotation is needed
    }

    //find least value below the right child of this root node
    //take the current right subtree, and then branch to the left side and search 
    //all the way to the bottom of it till a leaf node.
    private int successor(Node currNode) { 
		currNode = currNode.rightNode;
		while(currNode.leftNode != null){
			currNode = currNode.leftNode;
		}
		return currNode.data;
	}

    //find greatest value below the left child of this root node
    //take the current left subtree, and then branch to the right side and search 
    //all the way to the bottom till a leaf node.
	private int predecessor(Node currNode) {
		currNode = currNode.leftNode;
		while(currNode.rightNode != null){
			currNode = currNode.rightNode;
		}
		return currNode.data;
	}

    public void inOrder(){
        inOrderHelper(root);
    }

    private void inOrderHelper(Node currNode){
        if(currNode != null){
            inOrderHelper(currNode.leftNode);
            System.out.print(currNode.data + " ");
            inOrderHelper(currNode.rightNode);
        }
    }

    public void preOrder(){
        preOrderHelper(root);
    }

    private void preOrderHelper(Node currNode){
        if(currNode != null){
            System.out.print(currNode.data + " ");
            preOrderHelper(currNode.leftNode);
            preOrderHelper(currNode.rightNode);
        }
    }

    public void postOrder(){
        postOrderHelper(root);
    }
    
    private  void postOrderHelper(Node root){
        if(root != null){
            postOrderHelper(root.leftNode);
            postOrderHelper(root.rightNode);
            System.out.print(root.data + " ");
        }
    }

    public static class Node{
        protected int data;//Create data variable for storing data in node
        protected Node rightNode;//reference to the right child node 
        protected Node leftNode;//reference to the left child node
        protected int height = 1;
    
        //This constructor assigns the parameter data to the instance variable data, constructor to intialize new node with data.
        public Node(int data){
            this.data = data;
        }
    }//end of node class
}//end of BST class
