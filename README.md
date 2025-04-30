# Text-Analyser-Streams

This project implements text analysis utilities using Java 8 Streams API to process text files efficiently. The program
provides various text analysis functions implemented as single chains of stream operations.

---

## Overview

The **Text-Analyser-Streams** project is a Java-based tool designed to efficiently analyze text files using the **Java 8
Streams API**. It processes text data in a functional style, enabling concise, memory-efficient, and scalable operations
for tasks like word counting, frequency analysis, and extracting insights from the text.

---

## Features

- **Word Count**: Computes the total number of words in a text file.
- **Line Count**: Calculates the total number of lines in the file.
- **Character Count**: Determines the total number of characters, including or excluding whitespace.
- **Unique Words**: Identifies and lists all unique words in the text.
- **Top N Frequent Words**: Displays the most common words based on their frequency.
- **Keyword Search**: Finds occurrences of specific keywords and locates their positions.
- **Text Transformation**: Applies transformations like lowercasing, filtering by length, or removing punctuation.
- **Statistical Insights**: Provides advanced metrics such as average word length, sentence length, and word
  distribution.

---

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 8** or higher is required to support the Streams API.
- Any IDE like **IntelliJ IDEA** or **Eclipse** is suggested for easier development and debugging.

### Installation

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-repository/Text-Analyser-Streams.git
   ```

2. Navigate to the project directory:
   ```bash
   cd Text-Analyser-Streams
   ```

3. Compile the source code using the following command:
   ```bash
   javac -d out src/*.java
   ```

4. Run the application:
   ```bash
   java -cp out TextAnalyserMain
   ```

---
