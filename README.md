# CS113-HW09-HuffmanTree
## HW #9 for CS113 - Huffman Tree Encoding + URL Request

>One of the first forms of data compression was through morse code, by encoding more frequent letters through shorter messages, like e being a single dot, allowing the transmission of information over the wire. A later version of data compression arose with the rise of the internet in the 1970’s, Huffman encoding, being dynamically generated on input data was very efficient until the invention of the LZ77 algorithm in 1977.

## Huffman Tree Encoder
You will be given some code that will retrieve text from a given website and output it to a file (see table at end for limited chars), as well as the code for a Binary Tree. You are to use Java’s built in `PriorityQueue`, in conjunction with the given `BinaryTree` to build a `HuffmanTree` which will encode the retrieved text file. Your `HuffmanTree` implementation will provide a lossless compression of text which can then be sent (along with the Huffman tree) and decompressed with the same Huffman tree.

```
Valid chars left over after cleaning webpage and storing into file:
A-Z (upper case letter) characters
a-z (lower case letter) characters
0-9 (digit) characters
only allowed symbols: space ( ), tab (\t), newline (\n), exclamation mark (!), period (.), question mark (?)
```

Provide a driver to demonstrate your `HuffmanTree` implementation works by:
 1. taking a URL and output text file name to create the ***original file***, this file is used to create the Huffman tree
 2. using the built Huffman tree with the *original file*'s website text to create an ***encoded file***
 3. using the built Huffman tree with the *encoded file* to create a ***decoded file***
 4. output the number of bits for each file, make sure to use 16 bits per character for the *original* and *decoded* files ( for *encoded* file simply count the number of 0's and 1's for the number of bits).   Note that *original* and *decoded* files should have the exact same number of bits!
 5. output the percentage of compression (number of bits in *original* file / number of bits in *encoded* file).

> ***NOTE:***
> - **DO NOT USE BINARY FILES FOR THIS PROJECT**.  You will encode by outputting the 1’s and 0’s to a text file, you do not need to work with actual binary files!
> - You cannot use any data structure we have not covered in the course so far (i.e., HashTables are not allowed).
> - `BufferedReader` and its `read()` method can be especially helpful...
> 
> ***PRO-TIP:*** A thorough explanation of a Huffman tree can be found on p. 299 of your textbook. **Make sure to use the count of each character in the file as the weights for the most efficient compression!**