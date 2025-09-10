/**
 * @file BinarySearchTree.java
 * @author HyuckJoon Kwon (Brian Kwon)
 * @version 1.0
 * @date 2025-09-10
 * @brief Implementation of a Binary Search Tree (BST) in Java.
 */

public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T> {

    protected BinaryNode<T> root;

    /**
     * This is no-arg constructor. Initializes an empty tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Recursively inserts the supplied newNode into the supplied tree 
     * or subtree using the binary search tree insert technique.
     * This method performs nothing if the supplied subtree is null. 
     */
    protected void insertHelper(BinaryNode<T> newNode, BinaryNode<T> subtree) {
        if (subtree == null) {
            // If the insert() below is called normally, it will not reach to here...
            return;
        }

        // Compare new node's data with the subtree root's data.
        // Duplicates needs to insert into the left subtree.
        if (newNode.getData().compareTo(subtree.getData()) <= 0) {
            // If the left child is null, insert the new node here.
            if (subtree.getLeft() == null) {
                subtree.setLeft(newNode);
                newNode.setParent(subtree);
            } else {
                // Otherwise, recurse down the left subtree.
                insertHelper(newNode, subtree.getLeft());
            }
        } else {
            // If the right child is null, insert the new node here.
            if (subtree.getRight() == null) {
                subtree.setRight(newNode);
                newNode.setParent(subtree);
            } else {
                // Otherwise, recurse down the right subtree.
                insertHelper(newNode, subtree.getRight());
            }
        }
    }

    @Override
    public void insert(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("You cannot insert null data into the tree...");
        }
        BinaryNode<T> newNode = new BinaryNode<>(data);
        if (this.root == null) {
            // If the tree is empty, the new node becomes the root.
            this.root = newNode;
        } else {
            // If it's not, let's use the 'Helper' method to get this correct position.
            insertHelper(newNode, this.root);
        }
    }

    @Override
    public boolean contains(Comparable<T> data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Cannot check for null data in the tree...");
        }
        BinaryNode<T> current = this.root;
        while (current != null) {
            int comp = data.compareTo(current.getData());
            if (comp == 0) {
                return true; // Data found
            } else if (comp < 0) {
                current = current.getLeft(); // Go left
            } else {
                current = current.getRight(); // Go right
            }
        }
        return false; // Data not found(Data missing)
    }
    
    /**
     * Private sizeHelper method to calculate the size of the tree.
     * @param node : The current node to count from.
     * @return The number of nodes in the subtree rooted at 'node'.
     */
    private int sizeHelper(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        // The size is 1 (for the current node) + size of left subtree + size of right subtree.
        return 1 + sizeHelper(node.getLeft()) + sizeHelper(node.getRight());
    }

    @Override
    public int size() {
        return sizeHelper(this.root);
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    // TESTING METHODS

    /**
     * Insert multiple Integer values, checking size, contains, clear, and isEmpty.
     * Balanced tree shape (Regular shape).
     * @return true: if all tests pass, false: otherwise.
     */
    public boolean test1() {
        try {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            // Step 1: isEmpty on a new tree
            if (!tree.isEmpty() || tree.size() != 0) return false;

            // Step 2: Insert multiple values
            tree.insert(20);
            tree.insert(10);
            tree.insert(30);
            tree.insert(5);
            tree.insert(15);
            tree.insert(25);
            tree.insert(35);
            
            // Step 3: Check size
            if (tree.size() != 7) return false;
            
            // Step 4: Check contains for root, internal, and leaf nodes
            if (!tree.contains(20) || !tree.contains(5) || !tree.contains(35) || !tree.contains(15)) return false;

            // Step 5: Check contains for a value not in the tree
            if (tree.contains(99)) return false;

            // Step 6: Clear the tree and check if it's empty
            tree.clear();
            if (!tree.isEmpty() || tree.size() != 0) return false;
            
        } catch (Exception e) {
            return false; // Any exception is a failure
        }
        return true; // All tests passed
    }

    /**
     * Tests inserting multiple String values to create a right-skewed tree.
     * Checks size and contains.
     * @return true: if all tests pass, false: otherwise.
     */
    public boolean test2() {
         try {
            BinarySearchTree<String> tree = new BinarySearchTree<>();
            tree.insert("Badgers");
            tree.insert("Brewers");
            tree.insert("Bucks");
            tree.insert("Packers");

            // Step 1: Check size
            if (tree.size() != 4) return false;

            // Step 2: Check contains for all inserted values
            if (!tree.contains("Badgers") || !tree.contains("Brewers") || !tree.contains("Bucks") || !tree.contains("Packers")) return false;
            
            // Step 3: Check structure of the tree (right-skewed)
            // Packers -> Brewers -> Bucks -> Badgers
            if (!tree.root.getData().equals("Badgers")) return false;
            if (!tree.root.getRight().getData().equals("Brewers")) return false;
            if (!tree.root.getRight().getRight().getData().equals("Bucks")) return false;
            
            // Step 4: Check contains for non-existent value
            if (tree.contains("Bears")) return false;

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests duplicate value(same value) insertion (It goes to the left).
     * @return true: if all tests pass, false: otherwise.
     */
    public boolean test3() {
        try {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            tree.insert(100);
            tree.insert(50);
            tree.insert(150);
            tree.insert(50); // Duplicate value

            // Step 1: Check size (It should include duplicate)
            if (tree.size() != 4) return false;

            // Step 2: Check structure for duplicate handling
            if (tree.root.getLeft().getData() != 50) return false;
            if (tree.root.getLeft().getLeft().getData() != 50) return false;
            
            // Check using in-order traversal string
            // Expected: [ 50, 50, 100, 150 ]
            if (!tree.root.toInOrderString().equals("[ 50, 50, 100, 150 ]")) return false;

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Main method: runs all tests and prints results.
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> checker = new BinarySearchTree<>();
        System.out.println("Test1 (Integer, Balanced tree): " + (checker.test1() ? "PASSED" : "FAILED"));
        System.out.println("Test2 (String, Right-skewed tree): " + (checker.test2() ? "PASSED" : "FAILED"));
        System.out.println("Test3 (Integer, Duplicate Left tree): " + (checker.test3() ? "PASSED" : "FAILED"));
    }
}