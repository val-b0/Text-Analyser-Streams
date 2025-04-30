package main.java.com.text_analyser;

import main.java.com.text_analyser.stream_functions.Functionality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        // Insert the path of the .txt file to be analyzed as a string argument when initializing Functionality
        Functionality functionality = new Functionality("");


        // Provide the text string to search for in the argument
        logger.info(String.valueOf(functionality.findLine("")));
        // Provide the text string to search for in the argument
        logger.info(String.valueOf(functionality.findLines("")));


        // Writing all non-empty lines from the input file into the specified output file
        functionality.writeNoEmptyLines("");


        // Streaming distinct, sorted words from the file for further processing or logging
        functionality.wordStream().forEach(e -> logger.info("{}, ", e));


        logger.info("List of all words: {}", functionality.words() + "\n");
        logger.info("Average line length: {}\n", functionality.averageLineLength());
        logger.info("Average words in line: {}\n", functionality.averageWordsInLine());


        // Finds first line containing inserted string
        logger.info(String.valueOf(functionality.findLine("")));

        //Finds all lines containing inserted string
        logger.info(String.valueOf(functionality.findLines("")));


        // Grouping unique words alphabetically and logging the results for analysis
        functionality.alphaGrouping().forEach((key, value) -> logger.info("'{}' {}", key, value));

    }
}

