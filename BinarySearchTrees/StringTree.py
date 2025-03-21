#This is a BST class that only accepts strings 
#I did this just because I wanted it seperated and neat

class Node: 
 def __init__(self, data):
  self.data = data#data variable for storing node elements
  self.leftNode = None#reference to left child node
  self.rightNode = None#reference to right child node

class BST:
 def __init__(self):
  self.root = None #defines the root or the topmost node of the tree

 def insert_String(self, data):
   if not isinstance(data, str):
     raise TypeError("Must be a string")
   else:
    self.root = self.insert_String_helper(self.root, data)

 def insert_String_helper(self, node, data):
   if node is None:
     return Node(data)
   elif len(data) < len(node.data):
     node.leftNode = self.insert_String_helper(node.leftNode, data)
   else:
     node.rightNode = self.insert_String_helper(node.rightNode, data)
    
   return node
 
 def search_String(self, data):
    if not isinstance(data, str):
     raise TypeError("Must be a string")
    else:
     result= self.search_String_Helper(self.root, data)
    
    if result:
      print(f'\n"{data}" was found')
    else:
      print(f'\n"{data} was not found"')

    return result  

 def search_String_Helper(self, node, data):
   if node is None:
     return None
   elif len(data) == len(node.data):
     return node
   elif len(data) < len(node.data):
     return self.search_String_Helper(node.leftNode, data)
   else:
     return self.search_String_Helper(node.rightNode, data)
   
 def delete_String(self, data):
   if not isinstance(data, str):
     raise TypeError("Must be a string")
   elif(self.search_String(data)):
     self.root = self.delete_String_Helper(self.root, data)
     print(f'"{data}" has been deleted')
   else:
     print(f'"{data} could not be found"')
    
     
   
 def delete_String_Helper(self, node, data):
   if node is None:
     return None
   elif len(data) < len(node.data):
     node.leftNode = self.delete_String_Helper(node.leftNode, data)
   elif len(data) > len(node.data):
     node.rightNode = self.delete_String_Helper(node.rightNode, data)
   else:
     if(node.leftNode is None and node.rightNode is None):
       return None
     elif node.rightNode is not None:
       node.data = self.successor(node)
       node.rightNode = self.delete_String_Helper(node.rightNode, node.data)
     elif node.leftNode is not None:
       node.data = self.predecessor(node)
       node.leftNode = self.delete_String_Helper(node.leftNode, node.data)
   return node
       
     
   
   
 def predecessor(self, node):
    node = node.leftNode
    while(node.rightNode is not None):
      node.rightNode = self.predecessor(node.rightNode)
    return node.data
 
 def successor(self, node):
    node = node.rightNode
    while(node.leftNode is not None):
     node.leftNode = self.successor(node.leftNode)
    return node.data

     
 
 def inOrder_String(self):
   if self.root is None:
     raise TypeError("No strings were entered")
   else:
     self.inOrder_String_Helper(self.root)
     

 def inOrder_String_Helper(self, node):
  if node is not None:
     self.inOrder_String_Helper(node.leftNode)
     if isinstance(node.data, str):
      print(node.data, end=" ")
     self.inOrder_String_Helper(node.rightNode)

     #main method for preorder traversal
 def preOrder_String(self):
    if self.root is None:
     raise TypeError("No strings were entered")
    else:
     self.preOrder_String_Helper(self.root)

#Recursively traverse the binary tree and visit root then left to right subtree
 def preOrder_String_Helper(self, node):
   if node is not None:
     if isinstance(node.data, str):
      print(node.data, end=" ")
     self.preOrder_String_Helper(node.leftNode)
     self.preOrder_String_Helper(node.rightNode)
 
#main method for postorder traversal
 def postOrder_String(self):
    if self.root is None:
     raise TypeError("No strings were entered")
    else:
     self.postOrder_String_Helper(self.root)

#Recursively traverse the binary tree and visit left to right subtree then root
 def postOrder_String_Helper(self, node):
   if node is not None:
     self.postOrder_String_Helper(node.leftNode)
     self.postOrder_String_Helper(node.rightNode)
     if isinstance(node.data, str):
      print(node.data, end=" ")

def main():
  #create an object of the BST class
  tree = BST()



  #insert Strings
  tree.insert_String('superduper')
  tree.insert_String('hello')
  tree.insert_String('hi')
  tree.insert_String('finest')
  tree.insert_String('ok')


  print('Strings in order: ')
  tree.inOrder_String()
  print('\nPreOrder: ')
  tree.preOrder_String()
  print('\nPostOrder')
  tree.postOrder_String()

  tree.search_String('hi')
  tree.search_String('superexpedious')

  tree.delete_String('hello')
  print('\nInorder: ') 
  tree.inOrder_String()
  

#convention in python that lets you run a main method
if __name__ == '__main__':
  main()

