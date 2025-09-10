# BinarySearchTree


This project is part of P101.BinarySearchTree.
## Java Binary Search Tree Implementation
This repository contains a single file, BinarySearchTree.java, which is a complete Java implementation of a generic Binary Search Tree (BST). This class provides a foundational way to store and manage comparable elements in a sorted manner.

**Please note: This file has two dependencies, SortedCollection.java and BinaryNode.java, which are not included in this repository. You will need to provide your own implementations of these files for the code to compile and run. Descriptions of these required files are provided below.**

## ✨ Features
Generic Implementation: The tree can store any object type that implements the Comparable<T> interface.

Core BST Operations:

insert(T data): Adds a new element while maintaining the BST property.

contains(Comparable<T> data): Efficiently checks for the existence of an element.

size(): Returns the total number of nodes in the tree.

isEmpty(): Checks if the tree is empty.

clear(): Removes all elements from the tree.

Duplicate Handling: Duplicate values are supported and are inserted into the left subtree of their equivalent node.

Self-Testing: The BinarySearchTree.java file contains a main method with a suite of test cases to verify its correctness.

## 📂 Required Dependencies
To use BinarySearchTree.java, you must create the following two files in the same directory.

1. SortedCollection.java
This is an interface that defines the public methods for a sorted collection. The BinarySearchTree class implements this interface. It should include the following method signatures:


>public interface SortedCollection<T extends Comparable<T>> {
    public void insert(T data) throws NullPointerException;
    public boolean contains(Comparable<T> data) throws NullPointerException;
    public int size();
    public boolean isEmpty();
    public void clear();
}
2. BinaryNode.java
This is a class that represents a single node in the tree. It should be a generic class that holds the data and references to its parent and children. A minimal implementation would include:

A constructor: public BinaryNode(T data)

Instance variables for data, parent, left child, and right child.

Getter and setter methods for the instance variables (e.g., getData(), getLeft(), setLeft(BinaryNode<T> node)).

## 🚀 How to Run
Create the Dependency Files: First, create and save SortedCollection.java and BinaryNode.java in the same directory as BinarySearchTree.java.

Compile the Code: Open a terminal in the directory containing the three files and run the Java compiler.

Bash

javac BinarySearchTree.java
Execute the Tests: Run the main method, which will execute the built-in test suite.

Bash

java BinarySearchTree
Expected Output: The program will print the results of the test cases, confirming the implementation is working correctly.

>Running test1 (Integer, balanced tree): PASSED

>Running test2 (String, skewed tree):    PASSED

>Running test3 (Integer, with duplicates): PASSED

## ✅ Included Tests
The built-in test suite covers three key scenarios to ensure the implementation is robust:

* test1(): Balanced Tree & Core Functionality

  Tests basic operations (insert, size, contains, clear) using integers to create a well-balanced tree.

* test2(): Skewed Tree & String Data

  Tests the tree's behavior when string values are inserted in ascending order, creating a right-skewed structure.

* test3(): Duplicate Value Handling

  Tests the specific logic for handling duplicate integer values, ensuring they are correctly placed in the left subtree and the tree size is updated properly.
