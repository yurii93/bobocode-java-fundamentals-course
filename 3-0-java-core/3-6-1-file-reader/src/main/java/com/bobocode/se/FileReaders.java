package com.bobocode.se;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.stream.Collectors.joining;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        URL fileUrl = FileReaders.class.getClassLoader().getResource(fileName);
        try {
            return Files.lines(Paths.get(fileUrl.toURI()), StandardCharsets.UTF_8).collect(joining("\n"));
        } catch (IOException | URISyntaxException e) {
            throw new FileReaderException("Can't find, open or read file", e);
        }
    }
}
