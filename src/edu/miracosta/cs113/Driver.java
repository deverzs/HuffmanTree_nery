package edu.miracosta.cs113;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver {

    public static final int BITS_PER_CHAR = 16 ;
    public static void main (String args[]) {

        String input = "hello";
        String url = "http://www.zsusveganpantry.com/p/menu-planning.html";
        boolean done = false ;
        String outputTextFile = "text.txt"; // original file
        BufferedReader br ;
        String temp ;
        String cleanLine ;
        int originalBitCount = 0 ;
        int decodedBitCount = 0 ;
        int encodedBitCount = 0 ;

        TextFileGenerator tfg = new TextFileGenerator() ;
        StringBuilder sb = new StringBuilder() ;
        try {

            tfg.makeCleanFile(url,outputTextFile );
            originalBitCount = tfg.getNumChars(outputTextFile) * BITS_PER_CHAR ;
            br = new BufferedReader(new FileReader(outputTextFile)) ;
            while (!done) {
                temp = br.readLine();
                if (temp!=null) {
                    cleanLine = tfg.cleanString(temp);
                    sb.append(cleanLine) ;
                }

                if (br.readLine()==null ){
                    done = true ;
                }

            }
        }
        catch (IOException e) {
            System.out.println("try again");
        }

        HuffmanTree huffy = new HuffmanTree(sb.toString()); // using original website text

        // create encoded file of 0's and 1's

        // create decoded file of back to text, original

       // System.out.println( huffy.decode(input)) ;
        // Original bit count:
        System.out.println("Original bit count is: " + originalBitCount);

        // Decoded bit count, should be same as original
        decodedBitCount = tfg.getNumChars("decoded.txt") * BITS_PER_CHAR ;
        System.out.println("Decoded bit count (should be same as above): " + decodedBitCount);

        // Encoded bit count
        encodedBitCount = tfg.getNumChars("encoded.txt") ;
        System.out.println("Encoded bit count (should be smaller than above): " + encodedBitCount);

        // Percentage output
        System.out.println("Percentage of compression is: " + (originalBitCount/encodedBitCount * 100));

    }
}
