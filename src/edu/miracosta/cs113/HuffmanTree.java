package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Class to represent and build a Huffman tree. The Huffman Tree will accept a String to create the tree,
 * and use the tree to encode and decode the String used to create the tree.
 * @author  Dianovics
 */
public class HuffmanTree implements Serializable,  HuffmanInterface  {

    private BinaryTree<HuffNode> huffTree ; // root of the priority queue
    private int[] table ; // frequency table
    private HuffNode[] symbols ; // array of HufNodes
    private int arraySize ;
    private String[] codeTable ;
    private StringBuilder codes = new StringBuilder() ;
    private static final int MAX_ASCII_SYMBOLS = 256 ;

    /**
     * Constructor to create the tree
     * @param input Input is the String used to create the compression tree
     */
    public HuffmanTree(String  input) {
        // Creating frequency table
        table = new int[MAX_ASCII_SYMBOLS] ;
        // running through length of input string, adding to table count
        for (int i = 0  ; i < input.length() ; i++) {
            table[input.charAt(i)]++ ;
        }
        // counting how many table[] cells are occupied
        for (int i = 0 ; i < MAX_ASCII_SYMBOLS ; i++) {
            if (table[i] > 0) {
                arraySize++ ;
            }
        }
        // creating HuffNode[] for use in buildTree()
        symbols = new HuffNode[arraySize] ;
        int symbolCount = 0 ;
        for (int i = 0 ; i < MAX_ASCII_SYMBOLS ; i++) {
            if ( table[i] > 0) {
                symbols[symbolCount] = new HuffNode(table[i], (char) i) ;
                symbolCount++ ;
            }
        }
        // build the tree
        buildTree(symbols);

        // create the code table
        buildCodeTable(huffTree, "", 0);

        // Translate code table to searchable array
        setToTableCodes();
    }


    /**
     * Helper method to build the code table
     * @param tree The Binary Tree used to create the Huffman tree
     * @param s The string used to build the tree
     * @param arrayCount The count for the array to store the codes
     */
    private  void buildCodeTable(BinaryTree<HuffNode> tree, String s, int arrayCount) {

        String temp ;
        if (tree.isLeaf()) {
            temp = tree.getData().symbol.toString() + " " + s + "," ;
            codes.append(temp) ;
            return ;
        }
        else {
            buildCodeTable(tree.getLeftSubtree(), s + "0", arrayCount++);
            buildCodeTable(tree.getRightSubtree(), s + "1", arrayCount++);
        }
    }

    /**
     * Helper method that uses the codeString to generate the codeTable[]
     */
    private void setToTableCodes(){
         codeTable = new String[arraySize] ;

         String codeString = codes.toString() ;
         Scanner fromString = new Scanner(codeString).useDelimiter(",") ;
         for (int j = 0  ; j < arraySize ; j++) {
            if (fromString.hasNext()) {
                codeTable[j] = fromString.next();
            }
         }
    }

    /**
     * Builds the Huffman Tree using the HuffNode[] array for symbols
     * @param symbols The array of HuffNodes to build into a tree
     */
    public void buildTree(HuffNode[] symbols) {
        Queue<BinaryTree<HuffNode>> theQueue = new PriorityQueue<BinaryTree<HuffNode>>(arraySize, new CompareHuffmanTrees()) ;

        for (HuffNode nextSymbol : symbols) {
                BinaryTree<HuffNode> aBinaryTree = new BinaryTree<HuffNode>(nextSymbol, null, null) ;
                theQueue.offer(aBinaryTree) ;
        }

        while (theQueue.size() > 1) {
            BinaryTree<HuffNode> left = theQueue.poll() ;
            BinaryTree<HuffNode> right = theQueue.poll() ;
            double wl = left.getData().weight ;
            double wr = right.getData().weight ;
            HuffNode sum = new HuffNode(wl + wr, null) ;
            BinaryTree<HuffNode> newTree = new BinaryTree<HuffNode>(sum, left, right) ;
            theQueue.offer(newTree) ;
        }

        huffTree = theQueue.poll() ;
    }

    @Override
    public String decode(String codedMessage) {

        StringBuilder result = new StringBuilder() ;
        BinaryTree<HuffNode> currentTree = huffTree ;

        for (int i = 0  ;i < codedMessage.length() ; i++) {
            if (codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree() ;
            } else {
                currentTree = currentTree.getLeftSubtree() ;
            }
            if (currentTree.isLeaf()) {
                HuffNode theData = currentTree.getData() ;
                result.append(theData.symbol) ;
                currentTree = huffTree ;
            }
        }
        return result.toString() ;
    }

    @Override
    public String encode(String message) {
        StringBuilder sb = new StringBuilder() ;
        boolean found = false ;
        int count = 0 ;
        char target ;
        for (int i = 0 ; i < message.length(); i++) {
            target = message.charAt(i) ;
            count = 0 ;
            found = false ;
            while (!(found)) {
                if (codeTable[count].charAt(0)==target) {
                    found = true ;
                    sb.append(codeTable[count].substring(2)) ;
                }
                count++ ;
            }

        }
        return sb.toString() ;
    }

    ////////////////  COMPARATOR CLASS ////////////////////////////////
    /**
     * Class that implements the Comparator to create a compare(). The compare()
     * uses the weight of the node to prioritize
     */
    private static class CompareHuffmanTrees implements Comparator<BinaryTree<HuffNode>> {

        @Override
        public int compare(BinaryTree<HuffNode> treeLeft, BinaryTree<HuffNode> treeRight) {
            double wLeft = treeLeft.getData().weight ;
            double wRight = treeRight.getData().weight  ;
            return Double.compare(wLeft, wRight);
        }
    } // end Comparator


    ///////////////////    HUFFNODE CLASS //////////////////////////
    /**
     * Class of nodes to be used for Huffman Tree
     */
    public static class HuffNode implements  Serializable {
        private double weight ;
        private Character symbol ;

        /**
         * Constructor for HuffNodes
         * @param weight The frequency of the count for the symbol
         * @param symbol The char that is being counted
         */
        public HuffNode(double weight, Character symbol) {
            this.weight = weight ;
            this.symbol = symbol ;
        }

        @Override
        public String toString() {
            return " " + this.symbol ;
        }

    } // end HuffNode

}

