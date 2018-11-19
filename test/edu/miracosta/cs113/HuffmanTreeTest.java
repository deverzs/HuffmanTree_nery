package edu.miracosta.cs113;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * HuffmanTreeTest : Tester class for Huffman tree implementation.
 *
 * @author King
 * @version 1.0
 */
public class HuffmanTreeTest {

    /** Original and expected decoded values. */
    private static final String[] DECODED = {"Hello World!!! Ready for Spring 2019?",
                                        "the\tquick\tbrown\tfox\tjumps\tover\tthe\tlazy\tdog\t\t?!\n\n",
                                        "while walking wearily home...\ni wondered where wally was.\n",
                                        "Mike made mellow music with his nice new Neumann microphone."};

    /** Encoded values based on their own Huffman tree.
     * TEST UPDATED BY DIANOVICS 11_18_18
     * */
    private static final String[] ENCODED = {"100110111110111011100101000011100111111010110111011101110101010010111010110110100011010001111001111101000000011011110100000100001111010010110000100100101000010",
            "110101001010000111110110110101001101111111111011010000010111010011011011101101011111000010011111101011011011011110000111011110101110100000100111010100101000011001111010100000111001001000111110001100101110011000001100011000",

            "10011111101000001100110010110000101111010110110101100011000111011010010100000101000111111111101110010111100110011001101010100011001111011011111010110100011111010011001111101101000110011001011000000010100011001011111000110011010",
            "10001000110100101111011101001110001101111011100111010110101000101001101110100101000000100001100100001101000010111001010011000011011110010000011110111101101001101011110111001011101001111111111110111000100001011010001101100010100011111011101110"
    };

    /** A HuffmanTree to be built for each new String value. */
    HuffmanTree tree;

    @Test
    public void testDecodedValues() {
        for (int i = 0; i < DECODED.length; i++) {
            tree = new HuffmanTree(DECODED[i]);
            assertEquals("", ENCODED[i], tree.encode(DECODED[i]));
        }
    }

    @Test
    public void testEncodedValues() {
        for (int i = 0; i < DECODED.length; i++) {
            tree = new HuffmanTree(DECODED[i]);
            assertEquals("", DECODED[i], tree.decode(ENCODED[i]));
        }
    }

} // End of class HuffmanTreeTest
