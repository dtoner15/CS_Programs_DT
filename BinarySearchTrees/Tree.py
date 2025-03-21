#BST program that accepts integers
#Node class
class Node: 
 def __init__(self, data):
  self.data = data#data variable for storing node elements
  self.leftNode = None#reference to left child node
  self.rightNode = None#reference to right child node

class BST:
 def __init__(self):
  self.root = None #defines the root or the topmost node of the tree

 #method for inserting data into tree
 def insert(self, data):
  #set root to the helper method so that it knows the data is getting inserted into the tree
  self.root = self.insert_helper(self.root, data)
  
 #main logic for inserting node
 def insert_helper(self, node, data):
   #check if the current node your on is empty if it is return a node with data in it 
   if node is None:
    return Node(data)#return a new node
   elif data < node.data: 
    #traverse the left subtree recursively so that we know where to insert and element
    node.leftNode = self.insert_helper(node.leftNode, data)
   else:
    #traverse the right subtree recursively so that we know where to insert and element
    node.rightNode = self.insert_helper(node.rightNode, data)

   #return the element that we want inserted into the tree in the original tree
   return node
 
 #searching an element in the tree
 def search(self, data):
  if(self.search_Helper(self.root, data)):
   print(data, " was found")
   return True
  else:
    print(data, " could not be found")
    return False

#main logic for searching for a element/node
 def search_Helper(self, node, data):
   #current node is nothing so return false, because nothing is there
    if node is None:
     return False
   #if the element you inputed to search for is equal to a node element return true
    elif data == node.data:
     return True
    elif data < node.data:
     #recursively traverse the left subtree to find the node we are looking for 
     return self.search_Helper(node.leftNode, data)
    else:
     #recursively traverse the rightsubtree to find the node we are looking for
     return self.search_Helper(node.rightNode, data)

 
 #deletes an element in the tree 
 def delete(self, data):
   if(self.search(data)):
     self.root = self.delete_Helper(self.root, data)
     print(data, " is now deleted")
   else:
     print(data, " can not be found")

#main logic for deleting an element
 def delete_Helper(self, node, data):
   if node is None:
     return None
   #Recursively search the left subtree and assign the node found to the leftNode
   elif data < node.data: 
     node.leftNode = self.delete_Helper(node.leftNode, data)
   #Recursively search the right subtree and assign the node found to the rightNode
   elif data > node.data:
     node.rightNode = self.delete_Helper(node.rightNode, data)
   else:
     #node has no children, leaf node
     if(node.leftNode is None and node.rightNode is None):
       return None
     elif node.rightNode is not None:
      #get the data from the successor node, copy the value into the original node which replaces the original,
       #then go and recursively delete the original successor node from the tree
      node.data = self.successor(node)
      node.rightNode = self.delete_Helper(node.rightNode, node.data)
     else:
       #get the data from the predecessor node, copy the value into the original node which replaces the original,
       #then go and recursively delete the original predecessor node from the tree
       node.data = self.predecessor(node)
       node.leftNode = self.delete_Helper(node.leftNode, node.data)
     
     
   #returns the new current root of the subtree
   return node

#handles the logic for finding the smallest node  
#Go to the right subtree then search the left side of it go
#all the way to the furthest left node in the left side of the tree
 def successor(self, node):
     node = node.rightNode
     while(node.leftNode is not None):
        node = node.leftNode
     return node.data#return the farthest node of the right subtree on the left side
 
#Handles the logic for finding the largest node of the left subtree
 def predecessor(self, node):
   node = node.leftNode
   while(node.rightNode is not None):
      node = node.rightNode
   return node.data#return the farthest node of the left subtree on the right side
     

#main method for inorder traversal
 def inOrder(self):
     self.inOrder_Helper(self.root)
    
#Recursively traverses the binary tree and put it inorder smallest to greatest
 def inOrder_Helper(self, node):
    if node is not None:
     self.inOrder_Helper(node.leftNode)
     print(node.data, end=" ")
     self.inOrder_Helper(node.rightNode)

#main method for preorder traversal
 def preOrder(self):
   self.preOrder_Helper(self.root)

#Recursively traverse the binary tree and visit root then left to right subtree
 def preOrder_Helper(self, node):
   if node is not None:
     print(node.data, end=" ")
     self.preOrder_Helper(node.leftNode)
     self.preOrder_Helper(node.rightNode)
 
#main method for postorder traversal
 def postOrder(self):
   self.postOrder_Helper(self.root)

#Recursively traverse the binary tree and visit left to right subtree then root
 def postOrder_Helper(self, node):
   if node is not None:
     self.postOrder_Helper(node.leftNode)
     self.postOrder_Helper(node.rightNode)
     print(node.data, end=" ")


def main():
  #create an object of the BST class
  tree = BST()
  tree2 = BST()

  #inserts integers
  tree.insert(20)
  tree.insert(10)
  tree.insert(15)
  tree.insert(5)
  tree.insert(7)
  tree.insert(8)

  #insert Strings
  tree2.insert_String('superduper')
  tree2.insert_String('hello')
  tree2.insert_String('hi')
  tree2.insert_String('finest')
  tree2.insert_String('ok')
  print('Strings in order: ')
  tree2.inOrder_String()
  print('\nPreOrder: ')
  tree2.preOrder_String()
  print('\nPostOrder')
  tree2.postOrder_String()

  print("\nInorder: ")
  tree.inOrder()
  print("\nPreorder: ")
  tree.preOrder()
  print("\nPostorder: ")
  tree.postOrder()

  print()
  tree.search(5)
  print()
  #tree.delete(10)
  #print()
  tree.inOrder()
  print()

#convention in python that lets you run a main method
if __name__ == '__main__':
  main()