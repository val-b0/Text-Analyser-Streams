package main.java.com.text_analyser.stream_functions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Functionality class provides various utility methods for analyzing and processing text data
 * from files using the Java Streams API. It supports filtering lines, extracting words, calculating
 * averages, and grouping data alphabetically.
 */
public class Functionality {


    /**
     * Finds the first line in the file that contains the specified text.
     *
     * @param inFile The path to the input file to read.
     * @param text   The text to search for in the file.
     * @return An Optional containing the first line with the specified text, or empty if not found.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static Optional<String> findLine(String inFile, String text) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(inFile))) {
            return lines.filter(e -> e.contains(text)).findFirst();
        }
    }

    /**
     * Finds all lines in the file that contain the specified text.
     *
     * @param inFile The path to the input file to read.
     * @param text   The text to search for in the file.
     * @return A list of all lines containing the specified text.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static List<String> findLines(String inFile, String text) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(inFile))) {
            return lines.filter(e -> e.contains(text)).toList();
        }
    }

    /**
     * Writes all non-empty lines from the input file to the output file.
     *
     * @param inFile  The path to the input file to read.
     * @param outFile The path to the output file to write to.
     * @throws IOException If an I/O error occurs while reading or writing files.
     */
    public static void writeNoEmptyLines(String inFile, String outFile) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(inFile))) {
            Files.write(Paths.get(outFile), lines.filter(e -> !e.isEmpty()).toList(), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        }
    }

    /**
     * Creates a stream of distinct, sorted words from the input file.
     * Words are extracted after splitting lines based on common delimiters and filtering out duplicates or numbers.
     *
     * @param inFile The path to the input file to read.
     * @return A Stream of unique, sorted words in lowercase.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static Stream<String> wordStream(String inFile) throws IOException {
        return Files.lines(Paths.get(inFile))
                .map(s -> s.split("[ .,;?!.:()-]"))
                .flatMap(Arrays::stream)
                .filter(e -> !e.isEmpty())
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .filter(e -> !Character.isDigit(e.charAt(0)));
    }

    /**
     * Returns a list of distinct, sorted words from the input file.
     *
     * @param inFile The path to the input file to read.
     * @return A list of unique, sorted words in lowercase.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static List<String> words(String inFile) throws IOException {
        return wordStream(inFile).toList();
    }

    /**
     * Calculates the average length of lines in the input file.
     *
     * @param inFile The path to the input file to read.
     * @return The average line length as a double.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static double averageLineLength(String inFile) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(inFile))) {
            return lines.collect(Collectors.averagingDouble(String::length));
        }
    }

    /**
     * Calculates the average number of words per line in the input file.
     *
     * @param inFile The path to the input file to read.
     * @return The average number of words per line as a double.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static double averageWordsInLine(String inFile) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(inFile))) {
            return lines.map(s -> s.split("[ .,;?!.:()-]"))
                    .collect(Collectors.averagingDouble(value -> value.length));
        }
    }

    /**
     * Groups words from the input file by their starting character alphabetically.
     *
     * @param inFile The path to the input file to read.
     * @return A map where keys are the first characters of words, and values are lists of words starting with that character.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static Map<Character, List<String>> alphaGrouping(String inFile) throws IOException {
        return wordStream(inFile).collect(Collectors.groupingBy(e -> e.charAt(0)));
    }
}
