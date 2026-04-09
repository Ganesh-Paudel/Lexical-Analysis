package Lexer.Core;

import java.io.*;

/**
 * Low-level file reader with single-character lookahead capability.
 * 
 * This class provides buffered file reading with the ability to peek at the next
 * character without consuming it. It serves as the foundation for the lexical analyzer,
 * feeding raw characters to higher-level components.
 * 
 * Features:
 * <ul>
 *   <li>Buffered file reading for efficient I/O</li>
 *   <li>Single-character lookahead (peek) functionality</li>
 *   <li>EOF detection (returns -1 at end of file)</li>
 * </ul>
 * 
 * @author Ganesh
 * @version 1.0
 */
public class CharacterExtractor {
    /** Source file reference */
    private File file;
    /** Buffered reader for efficient file access */
    private BufferedReader reader;

    /** Buffered character for lookahead functionality */
    private int bufferedCharacter;
    /** Flag indicating if buffer contains valid data */
    private boolean hasBuffer;

    /**
     * Constructs a CharacterExtractor for the specified file.
     * 
     * @param file The source file to read from
     * @throws IOException If file cannot be opened or read
     */
    public CharacterExtractor(File file) throws IOException {
        this.file = file;
        openFile(this.file);
    }

    /**
     * Opens the file and initializes the buffered reader.
     * 
     * @param file The file to open
     * @throws IOException If the file cannot be opened
     */
    private void openFile(File file) throws IOException {
        reader = new BufferedReader(new FileReader(this.file));
    }

    /**
     * Closes the file reader and releases associated resources.
     * 
     * @param file The file to close (parameter for API consistency)
     * @throws IOException If the reader cannot be closed
     */
    public void closeFile(File file) throws IOException {
        reader.close();
    }

    /**
     * Reads and returns the next character from the file.
     * 
     * If a character was buffered by {@link #peek()}, this returns the buffered
     * character first. Otherwise, reads the next character from the file.
     * 
     * @return The next character code (0-65535), or -1 if end of file is reached
     * @throws IOException If a read error occurs
     */
    public int getNextCharacter() throws IOException {
        if (hasBuffer) {
            hasBuffer = false;
            return bufferedCharacter;
        }
        return this.reader.read();
    }

    /**
     * Returns the next character without consuming it (lookahead).
     * 
     * This allows the lexer to make decisions based on the next character
     * without advancing past it. Multiple calls return the same value until
     * {@link #getNextCharacter()} is called.
     * 
     * @return The next character code (0-65535), or -1 if end of file is reached
     * @throws IOException If a read error occurs
     */
    public int peek() throws IOException {
        if (!hasBuffer) {
            bufferedCharacter = this.reader.read();
            hasBuffer = true;
        }
        return bufferedCharacter;
    }
}
