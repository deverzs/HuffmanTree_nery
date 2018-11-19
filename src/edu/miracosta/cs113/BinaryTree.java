package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;

/**
 * BinaryTree : Class for a binary tree that stores objects of type E. Code derived from Koffman & Wolfgang's Data
 * Structures: Abstraction and Design Using Java (2nd).
 *
 * @param <E> Generic to hold any data type
 */
public class BinaryTree<E> implements Serializable {
    /** The root node of this tree. */
    protected Node<E> root;

    /**
     * Default constructor to build an empty BinaryTree.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Constructor to build a BinaryTree with the given Node shallow copied and stored as the root.
     *
     * @param root the root node of this tree
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Full constructor to build a BinaryTree from provided data (for root) and left + right children/subtrees.
     *
     * @param data datum stored for this tree's root node
     * @param leftTree left subtree of root
     * @param rightTree right subtree of root
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        // Set root to a new Node with the given data
        root = new Node<E>(data);

        // Set left subtree
        if (leftTree != null) {
            root.left = leftTree.root;
        }
        else {
            root.left = null;
        }

        // Set right subtree
        if (rightTree != null) {
            root.right = rightTree.root;
        }
        else {
            root.right = null;
        }
    }

    /**
     * Returns the left subtree.
     *
     * @return The left subtree, or null if either the root or left subtree is null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        }
        else {
            return null;
        }
    }

    /**
     * Returns the right subtree.
     *
     * @return The right subtree, or null if either the root or left subtree is null
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null){
            return new BinaryTree<E>(root.right);
        }
        else {
            return null;
        }
    }

    /**
     * Determines whether this tree is a leaf.
     *
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * Returns the data associated with the root Node.
     *
     * @return The data stored in the root
     */
    public E getData() {
        return root.data;
    }

    /**
     * Constructs a binary tree by reading its data with the given Scanner object.
     *
     * pre: The input consists of a pre-order traversal of the binary tree, with each Node on its own line.
     * The line "null" indicates a null tree.
     *
     * @param scan The Scanner attached to the input file
     * @return The binary tree constructed from the given input
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        // Read a line and trim leading and trailing spaces.
        if (!scan.hasNext()) {
            return null;
        }
        else {
            String data = scan.next();

            if (data.equals("null")) {
                return null;
            }
            else {
                BinaryTree<String> leftTree = readBinaryTree(scan);
                BinaryTree<String> rightTree = readBinaryTree(scan);

                return new BinaryTree<String>(data, leftTree, rightTree);
            }
        }
    }

    /**
     * Performs a pre-order traversal of this tree.
     *
     * @param node The local root
     * @param depth The current level in depth of this tree
     * @param sb The String buffer which accumulates the output
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        }
        else {
            sb.append(node.toString());
            sb.append("\n");

            preOrderTraverse(node.left, depth+1, sb);
            preOrderTraverse(node.right, depth+1, sb);
        }
    }

    @Override
    public String toString() {
        System.out.println("called tosting");
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * The inner class for the BinaryTree, a specialized node which may hold any data type.
     *
     * @param <E> Generic to hold any data type
     */
    protected static class Node<E> implements Serializable {
        /** The constituent data for this Node. */
        protected E data;
        /** The Node's left subtree. */
        protected Node<E> left;
        /** The Node's right subtree. */
        protected Node<E> right;

        /**
         * Constructor which stores the given data in this Node.
         *
         * @param data The data to hold within the node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }


        @Override
        public String toString() {
            return data.toString();
        }

    } // End of class Node

} // End of class BinaryTree