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
    private final String inFile;

    /**
     * Constructor to initialize the input file path.
     *
     * @param filePath The path to the input file to read.
     */
    public Functionality(String filePath) {
        this.inFile = filePath;
    }
    /**
     * Finds the first line in the file that contains the specified text.
     *
     * @param text   The text to search for in the file.
     * @return An Optional containing the first line with the specified text, or empty if not found.
     * @throws IOException If an I/O error occurs while reading the file.
     */

    public Optional<String> findLine(String text) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(this.inFile))) {
            return lines.filter(e -> e.contains(text)).findFirst();
        }
    }

    /**
     * Finds all lines in the file that contain the specified text.
     *
     * @param text   The text to search for in the file.
     * @return A list of all lines containing the specified text.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<String> findLines(String text) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(this.inFile))) {
            return lines.filter(e -> e.contains(text)).toList();
        }
    }

    /**
     * Writes all non-empty lines from the input file to the output file.
     *
     * @param outFile The path to the output file to write to.
     * @throws IOException If an I/O error occurs while reading or writing files.
     */
    public void writeNoEmptyLines(String outFile) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(this.inFile))) {
            Files.write(Paths.get(outFile), lines.filter(e -> !e.isEmpty()).toList(), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        }
    }

    /**
     * Creates a stream of distinct, sorted words from the input file.
     * Words are extracted after splitting lines based on common delimiters and filtering out duplicates or numbers.
     *
     * @return A Stream of unique, sorted words in lowercase.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public Stream<String> wordStream() throws IOException {
        return Files.lines(Paths.get(this.inFile))
                .map(s -> s.split("[ ,;?!.:()-]"))
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
     * @return A list of unique, sorted words in lowercase.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<String> words() throws IOException {
        return wordStream().toList();
    }

    /**
     * Calculates the average length of lines in the input file.
     *
     * @return The average line length as a double.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public double averageLineLength() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(this.inFile))) {
            return lines.collect(Collectors.averagingDouble(String::length));
        }
    }

    /**
     * Calculates the average number of words per line in the input file.
     *
     * @return The average number of words per line as a double.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public double averageWordsInLine() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(this.inFile))) {
            return lines.map(s -> s.split("[ ,;?!.:()-]"))
                    .collect(Collectors.averagingDouble(value -> value.length));
        }
    }

    /**
     * Groups words from the input file by their starting character alphabetically.
     *
     * @return A map where keys are the first characters of words, and values are lists of words starting with that character.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public Map<Character, List<String>> alphaGrouping() throws IOException {
        return wordStream().collect(Collectors.groupingBy(e -> e.charAt(0)));
    }
}
