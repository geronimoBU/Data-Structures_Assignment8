
package hw8;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class IntLinkedBinaryTree extends LinkedBinaryTree<Integer>{

	// define necessary instance variables and methods, including a constructor(s)
        private int size;
        public int size(){ return size;}
        
        public IntLinkedBinaryTree() {
            root = null;
            size = 0;
        }
    
	/**
	 * Add a new node with e to the tree rooted at position p
	 * @param p The root of the tree to which new node is added
	 * @param e The element of the new node
	 * @return If a node with e does not exist, a new node with e is added and 
	 *   reference to the node is returned. If a node with e exists, null is returned.
	 */
	public Position<Integer> add(Position<Integer> p, Integer e){
            
            if (p == null){
                 Position<Integer> temp = addRoot(e);
                 size = 1;
                 return temp;
            }
            Node<Integer> x = validate(p);
            Node<Integer> y = x;
            
            while (x != null){
                
                if (x.getElement() == e){return null;}
                
                else if (x.getElement() > e){
                    y = x;
                    x = x.getLeft();
                }
                
                else {
                    y = x;
                    x = x.getRight();
                }
            }
            
            Node<Integer> newNode = new Node<>(e, null, null, null);
            
            if (y.getElement() > e){
                y.setLeft(newNode);
                newNode.setParent(y);
            }
            else{
                y.setRight(newNode);
                newNode.setParent(y);
            }
            
            size++;
            System.out.println("Key added successfully.");
            return newNode;	
	}
        
        public Node <Integer> delete(Position<Integer> p, Integer e){
            
            if (p == null){
                return null;
            }
            Node <Integer> node = validate(p);
            if(node.getElement() > e){
                delete(node.getLeft(), e);
            }
            else if (node.getElement() < e){
                delete(node.getRight(), e);
            }
            else{
                if (node == root){
                    root = null;
                    return null;
                }
                Node <Integer> parent = node.getParent();
                if (node.getRight() == null && node.getLeft() == null){
                    if (parent.getElement() < node.getElement()){
                        parent.setRight(null);
                        node = null;
                    }
                    else{
                        parent.setLeft(null);
                        node = null;
                    }
                }
                else if (node.getLeft() == null && node.getRight() != null){
                    Node <Integer> right_node = node.getRight();
                    if (parent.getElement() < node.getElement()){
                        parent.setRight(right_node);
                        node = null;
                    }
                    else{
                        parent.setLeft(right_node);
                        node = null;
                    }
                }
                
                else if (node.getLeft() != null && node.getRight() == null){
                    Node <Integer> left_node = node.getRight();
                    if (parent.getElement() < node.getElement()){
                        parent.setRight(left_node);
                        node = null;
                    }
                    else{
                        parent.setLeft(left_node);
                        node = null;
                    }
                }
                
                else{
                    Node <Integer> temp = node;
                    Node <Integer> minR = minValue(temp.getRight());
                    node.setElement(minR.getElement());
                    delete(node.getRight(), minR.getElement());
                }
                
                
                }
            return node;
        }
        
        /**
         * Input tree, and integer. Check if integer is unique inside of Binary tree. Return false if not unique
         * @param tree (Int Linked Binary Tree) 
         * @param num (int)
         */
        public static boolean isUnique(IntLinkedBinaryTree tree, int num){
            Node<Integer> x = tree.root;
            if (x == null){
                return true;
            }
            Node<Integer> y = x;
            
            while (x != null){
                
                if (x.getElement() == num){
                    return false;
                }
                
                else if (x.getElement() > num){
                    y = x;
                    x = x.getLeft();
                }
                
                else {
                    y = x;
                    x = x.getRight();
                }
            }
            return true;
        }
        
        /**
         * Get the height of the binary tree.  
         * @param Node of the root of the tree. 
         * @return height of the treesd
         */
        public static int findHeight(Node<Integer> dopeNode){
            if (dopeNode == null){
                return -1;
            }
            
            int left_height = findHeight(dopeNode.getLeft());
            int right_height = findHeight(dopeNode.getRight());
            
            if(left_height > right_height){
                return left_height + 1;
            }
            else{
                return right_height + 1;
            }
        }
        
         /**
     * Check if input is integer, catch exception, wait for correct input before returning to main
     * @param in
     * @return 
     */
    public static int checkMenuInput(Scanner in){
        String menu_in = in.next();
        try {
            int menu_int = Integer.parseInt(menu_in);
            return menu_int;
        }
        
        catch (NumberFormatException ex) {
            System.out.println("Please enter '1', '2', '3', or '4' to exit." + "\n");}
            System.out.println("Choose an Option:\n"+ "1. Add a key\n"
            + "2. Remove a key\n"+ "3. Print the tree\n"+ "4. Exit\n");
            int menu_int_again = checkMenuInput(in);
            return menu_int_again; 
    }
    
        /**
     * Check if input is integer, catch exception, wait for correct input before returning to main
     * @param in
     * @return 
     */
    public static int checkIntInput(Scanner in){
        String menu_in = in.next();
        try {
            int menu_int = Integer.parseInt(menu_in);
            return menu_int;
        }
        
        catch (NumberFormatException ex) {
            System.out.println("Please enter an integer value." + "\n");}
            int menu_int_again = checkMenuInput(in);
            return menu_int_again; 
    }
    
    public static Node <Integer> minValue (Node <Integer> n){
        if (n.getLeft() == null){
            return n;
        }
        else{
            return minValue(n.getLeft());
        }
    }
    
	public static void main(String[] args) {
		
		// create a new binary tree instance
		IntLinkedBinaryTree t =   new IntLinkedBinaryTree();
		
                
                // Create menu abd handle input
                boolean menu = true;
                while (menu == true){
                    Scanner menu_in = new Scanner(System.in);
                    System.out.println("Choose an Option:\n"+ "1. Add a key\n"
                    + "2. Remove a key\n"+ "3. Print the tree\n"+ "4. Exit\n");
                    int menu_option = checkMenuInput(menu_in);
                    
                    // Menu 1: Add menu input to tree
                    if (menu_option == 1){
                        Scanner int_in = new Scanner(System.in);
                        System.out.println("Please enter an integer value." + "\n");
                        int int_add = checkIntInput(int_in);
                        Position<Integer> new_int = t.add(t.root, int_add);
                        if (new_int == null){
                            System.out.println("Integer " + int_add + " already exists, returning to main menu...");
                        }
                        else{
                            System.out.println("Integer " + int_add + " added to tree.");
                        }
                    }
                    
                    else if (menu_option == 2){
                        Scanner int_in = new Scanner(System.in);
                        System.out.println("Please enter an integer value." + "\n");
                        int int_del = checkIntInput(int_in);
                        t.delete(t.root, int_del);
                        System.out.println("Integer " + int_del + " removed. Returning to menu...\n");
                                
                    }
                    
                    else if (menu_option == 3){
                        
                        if (t.root == null){
                            System.out.println("Tree is empty, returning to menu");
                        }
                        else{
                            System.out.println("Current Integer(s) inside of tree:");
                            Iterator<Position<Integer>> it = t.inorder().iterator();
                            while (it.hasNext()){
                                System.out.println(it.next().getElement());
                            }
                        System.out.println("");   
                        }
                        
                    }
                    
                    else if (menu_option == 4){
                        menu = false;
                    }
                    
                }
                
                System.out.println("Good Bye");
	}
}
