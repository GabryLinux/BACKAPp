package BACKAPp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.IntPredicate;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Model {
    final static String CONFIG_FILE = "config.xml";
    final static String DEFAULT_CONFIG = "<config><sourcePath>MyApp</sourcePath><destinationPath>1.0</destinationPath><date>11:05</date></config>";
    private Config configurazione;
    private String configFile;
    private String configPath;
    final private List<Observer> observers = new ArrayList<>();
    //Il modello deve avere un campo Config configurazione da inizializzare nel costruttore

    /**
     * Costruttore del modello. Legge il file di configurazione col nome Model.CONFIG_FILE nella directory specificata
     * nella seguente maniera: 
     *  
     * @param configPath la directory in cui si trova il file di configurazione. Se è vuoto, verrà creato un file di configurazione di default.
     * @throws IOException se la directory non esiste o non è accessibile
     * @throws NullPointerException se il percorso è nullo
     */
    public Model(String configPath) throws IOException {
        // DIFFICOLTà : ★★★★★ //
        /**
         * Salva la stringa di configurazione Paths.get(configPath + "/" + Model.CONFIG_FILE) in configFile.
         * All'interno verrà chiamato Paths.get(configFile) che restituisce un oggetto Path del percorso specificato nella stringa.
         *          
         * Bisogna anche leggere il file di configurazione e inizializzare il campo Config con l'XML letto.
         * Per vedere come fare, guarda il test XMLConfigFileReadingTest in ModelTest.java .
         * 
         * Per leggere il file di configurazione, puoi usare la classe BufferedReader così:
         * BufferedReader reader = Files.newBufferedReader(Paths.get(configFile));
         */
        
        if(configPath.isBlank()){
            Files.write(Paths.get(CONFIG_FILE), DEFAULT_CONFIG.getBytes());
            XmlMapper xmlMapper = new XmlMapper();
            configurazione = xmlMapper.readValue(DEFAULT_CONFIG, Config.class);
            configFile = configPath + CONFIG_FILE;
            this.configPath = configPath; 
        }else{
            //TODO Auto-generated constructor stub
            // Gestisci il caso in cui il configPath non è vuoto
        }
        
    }

    /**
     * Restituisce la lista delle date dei file presenti nella directory di destinazione letta all'interno del
     * del file di configurazione, ossia la destinationPath. 
     * La lista delle date dei file deve essere ordinata in maniera decrescente e filtrata con l'estensione specificata.
     * @param estensione l'estensione dei file da cercare. Se è vuoto, restituisce tutti i file.
     * @return La lista dei file presenti nella directory di destinazione.
     * @throws NullPointerException se l'estensione è nulla
     * @throws IllegalArgumentException se l'estensione contiene il punto
     */
    public List<FileTime> getBackupsFromDirectory(String estensione) {
        // DIFFICOLTà : ★★★★★ //
        /**
         * Devi aggiungere dei controlli e lanciare le eccezioni appropriate.
         * es: if(estensione == null) throw new NullPointerException();
         * 
         * prima fai un filtro su tutti i file che hanno l'estensione specificata. Per avere il nome dei file
         * usa il metodo getFileName() della classe Path.
         * 
         * e poi raccogli le date dei file filtrati con Model.lastModifiedTimeOf(Paths.get(<i nomi dei file che hai raccolto prima>)) 
         * in un arrayList e ordina i file in base a queste date. Puoi effettuare l'ordinamento di un arrayList 
         * con il metodo Collections.sort(<arrayList>);
         */
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFileListFromDirectory'");
    }

    /**
     * Restituisce la configurazione letta dal file XML letto dal costruttore.
     * @return la configurazione.
     */
    public Config getConfig() {
        // DIFFICOLTà : ★ //
        // restituisci this.configurazione
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConfig'");
    }

    /**
     * Salva la configurazione passata come parametro nel file di configurazione.
     * @param config la nuova configurazione da salvare.
     */
    public void setConfig(Config config) {
        // DIFFICOLTà : ★★ //
        notifyObservers();
        // Assegna a this.configurazione il parametro config
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setConfig'");
        
    }

    /**
     * Aggiunge un osservatore alla lista degli osservatori del modello.
     * @param observer l'osservatore da aggiungere.
     * @throws NullPointerException se l'osservatore è nullo
     */
    public void addObserver(Observer observer) {
        // NON SCRIVERE NULLA
        observers.add(Objects.requireNonNull(observer, "Observer cannot be null"));
    }

    /**
     * Notifica tutti gli osservatori di un update del modello.
     */
    public void notifyObservers(){
        // NON SCRIVERE NULLA
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    /**
     * Restituisce l'ultima data di modifica del file specificato.
     * @param path il percorso del file di cui si vuole sapere la data di modifica.
     * @return la data di modifica del file.
     * @throws IOException se il file non esiste o non è accessibile
     */
    public static FileTime lastModifiedTimeOf(Path path) throws IOException {
        // NON SCRIVERE NULLA
        return Files.getLastModifiedTime(path);
    }

    /**
     * Restituisce il percorso del file di configurazione.
     * @return il percorso del file di configurazione.
     */
    public Path getConfigPath() {
        // DIFFICOLTà : ★ //
        // devi restituire configPath
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConfigPath'");
    }
}
