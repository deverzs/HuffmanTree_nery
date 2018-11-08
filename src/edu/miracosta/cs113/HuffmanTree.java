package edu.miracosta.cs113;

import java.io.Serializable;

/**
 * Class to represent and build a Huffman tree. Code derived from Koffman & Wolfgang's Data Structures: Abstraction
 * and Design Using Java (2nd).
 */
public class HuffmanTree implements Serializable {

    // TODO:
    // Build this class (found on p. 299), in addition to creating methods (such as overloading constructor,
    // encode and decode) for interaction with TextFileGenerator in your driver program.
    //
    // The JUnit tests expect a proper implementation of the following methods:

    /**
     * Constructor which builds a Huffman tree from the given message.
     *
     * @param message The message that this Huffman tree will be built upon
     */
    public HuffmanTree(String message) {
        // Builds a HuffmanTree based on the given message
    }

    /**
     * Decodes a message using the generated Huffman tree, where each character in the given message ('1's and '0's)
     * corresponds to traversals through said tree.
     *
     * @param codedMessage The compressed message based on this Huffman tree's encoding
     * @return The given message in its decoded form
     */
    public String decode(String codedMessage) {
        return "";
    }

    /**
     * Outputs the message encoded from the generated Huffman tree.
     * pre: the Huffman tree has been built using characters by which the message is only comprised.
     *
     * @param message The message to be decoded
     * @return The given message in its specific Huffman encoding of '1's and '0's
     */
    public String encode(String message) {
        return "";
    }

} // End of class HuffmanTree