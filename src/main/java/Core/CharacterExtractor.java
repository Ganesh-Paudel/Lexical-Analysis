package Core;

import java.io.*;

public class CharacterExtractor {
    private File file;
    private BufferedReader reader;

    private int bufferedCharacter;
    private boolean hasBuffer;



    public CharacterExtractor(File file) throws  IOException{
        this.file = file;
        openFile(this.file);
    }

    private void openFile(File file) throws IOException {
        reader = new BufferedReader(new FileReader(this.file));
    }

    public void closeFile(File file) throws IOException {
        reader.close();
    }

    public int getNextCharacter() throws IOException{
        if (hasBuffer){
            hasBuffer = false;
            return bufferedCharacter;
        }
        return this.reader.read();
    }

    public int peek() throws IOException{
        if(!hasBuffer){
            bufferedCharacter = this.reader.read();
            hasBuffer = true;
        }
        return bufferedCharacter;
    }
}
