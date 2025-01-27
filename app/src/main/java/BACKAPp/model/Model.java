package BACKAPp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.IntPredicate;

public class Model {
    //Il modello deve avere un campo Config configurazione da inizializzare nel costruttore
    public Model(String configPath) throws IOException {
        /**
         * Puoi costruire un Model nella seguente maniera:
         * Model m = new Model("path/to/directory");
         * All'interno verrà chiamato Paths.get("path/to/directory") che restituisce un oggetto Path del percorso specificato nella stringa.
         * La stringa indica il percorso del file di configurazione
         * 
         * Bisogna anche leggere il file di configurazione e inizializzare il campo Config con l'XML letto.
         * Per vedere come fare, guarda il test XMLConfigFileReadingTest.
         * Per leggere il file di configurazione, puoi usare la classe BufferedReader così:
         * BufferedReader reader = Files.newBufferedReader(Paths.get("path/to/file"));
         */
        //TODO Auto-generated constructor stub
    }

    public List<String> getBackupsFromDirectory(String estensione) {
        /**
         * Questo metodo prende in input un'estensione nella seguente maniera:
         * m.getFilesListFromDirectory("txt");
         * 
         * Devono essere restituiti i nomi di tutti i file presenti nella directory specificata nel costruttore 
         * che hanno l'estensione specificata come parametro
         * e ordinati in maniera alfabetica decrescente.
         * 
         * L'estensione non deve contenere il punto e non può essere null.
         * Devi aggiungere dei controlli e lanciare le eccezioni appropriate.
         * es: if(estensione == null) throw new NullPointerException();
         * 
         * Se la stringa passata è vuota, allora vengono restituiti tutti i file presenti nella directory.
         */
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFileListFromDirectory'");
    }

    public Config getConfig() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConfig'");
    }

    public void setConfig(Config config) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setConfig'");
    }


}
