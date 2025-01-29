package model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import BACKAPp.model.Config;
import BACKAPp.model.Model;
import BACKAPp.model.Observer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.List;

public class ModelTest {

    @Test
    public void readFileFromPathTest() throws IOException{

        //Verifica che il metodo getBackupsFromDirectory restituisca i file presenti nella directory specificata

        Path path1 = mock();
        Path path2 = mock();
        Path parent = mock();
        when(parent.iterator()).thenReturn(List.of(path1, path2).iterator());

        try (
            var utilities = mockStatic(Path.class); 
            var files = mockStatic(Files.class)
        ) {
            utilities.when(() -> Paths.get(any(String.class))).thenReturn(parent);
            Model m = new Model("");
            FileTime p1 = mock();
            FileTime p2 = mock();
            files.when(() -> Files.getLastModifiedTime(path1)).thenReturn(p1);
            files.when(() -> Files.getLastModifiedTime(path2)).thenReturn(p2);

            assertThat(m.getBackupsFromDirectory("")).containsExactly(p1, p2);
        }
    }

    @Test
    public void InvalidInputExtensionsTest() throws IOException{
        /**
         * Verifico che all'invocazione del metodo con null o con ".txt"
         * vengano lanciate le opportune eccezioni
         * con "throw new <eccezione da lanciare>()";
         */
        Model model = new Model("");
        assertThatThrownBy(() -> model.getBackupsFromDirectory(null))
                .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> model.getBackupsFromDirectory(".txt"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void sortedFilesFromPathTest() throws IOException{

        //Verifica che i file vengano restituiti in ordine di data decrescente di ultima modifica

        Path path1 = mock();
        Path path2 = mock();
        Path parent = mock();
        when(parent.iterator()).thenReturn(List.of(path1, path2).iterator());

        try (
            var utilities = mockStatic(Path.class); 
            var files = mockStatic(Files.class)
        ) {
            utilities.when(() -> Paths.get(any(String.class))).thenReturn(parent);
            Model m = new Model("");
            FileTime p1 = mock();
            FileTime p2 = mock();
            files.when(() -> Files.getLastModifiedTime(path1)).thenReturn(p1);
            files.when(() -> Files.getLastModifiedTime(path2)).thenReturn(p2);

            when(p1.compareTo(p2)).thenReturn(-1);
            when(p2.compareTo(p1)).thenReturn(1);
            assertThat(m.getBackupsFromDirectory("")).containsExactly(p2, p1);
        }
    }

    @Test
    public void filesFromPathWithExtensionTest() throws IOException {

        //Verifica che vengano restituiti solo i file con l'estensione specificata

        Path path1 = mock();
        when(path1.toString()).thenReturn("file1.txt");
        Path path2 = mock();
        when(path2.toString()).thenReturn("file2.mdb");
        Path parent = mock();
        when(parent.iterator()).thenReturn(List.of(path1, path2).iterator());
        
        try (
            var utilities = mockStatic(Path.class); 
            var files = mockStatic(Files.class)
        ) {
            utilities.when(() -> Paths.get(any(String.class))).thenReturn(parent);
            Model m = new Model("");
            FileTime p1 = mock();
            files.when(() -> Files.getLastModifiedTime(path1)).thenReturn(p1);

            assertThat(m.getBackupsFromDirectory("")).containsExactly(p1);
        }
    }

    @Test
    public void XMLMapperExample() throws JsonMappingException, JsonProcessingException {

        //Esempio di come si usa XMLMapper per leggere un XML

        String xml = "<config><sourcePath>MyApp</sourcePath><destinationPath>1.0</destinationPath><date>11:05</date></config>";

        XmlMapper xmlMapper = new XmlMapper();
        Config config = xmlMapper.readValue(xml, Config.class);

        assertThat(config.sourcePath()).isEqualTo("MyApp");
        assertThat(config.destinationPath()).isEqualTo("1.0");
        assertThat(config.date()).isEqualTo("11:05");
    }

    @Test
    public void XMLConfigFileReadingTest() throws IOException{

        //Leggi il file di configurazione e verifica che il metodo readValue venga chiamato con la stessa stringa dell'XML

        try (MockedConstruction<BufferedReader> mocked = mockConstruction(BufferedReader.class,
                (mock, context) -> when(mock.readLine()).thenReturn(
                        "<config><sourcePath>MyApp</sourcePath><destinationPath>1.0</destinationPath><date>11:05</date></config>"))) {
            try(MockedConstruction<XmlMapper> mockedMapper = mockConstruction(XmlMapper.class)){
                Model model = new Model("");
                verify(mockedMapper.constructed().get(0)
                .readValue("<config><sourcePath>MyApp</sourcePath><destinationPath>1.0</destinationPath><date>11:05</date></config>", 
                Config.class)
                );
            }
        }
    }

    @Test
    public void loadConfigFileTest() throws JacksonException, IOException {

        //Verifica che il metodo getConfig() restituisca l'oggetto Config con i valori corretti

        try(MockedConstruction<XmlMapper> mockedMapper = mockConstruction(XmlMapper.class,
        (mocked, context) -> when(mocked.readValue(any(String.class),Config.class)).thenReturn(new Config("MyApp", "1.0", "11:05")))){
            Model model = new Model("");
            assertThat(model.getConfig()).isEqualTo(new Config("MyApp", "1.0", "11:05"));
        }
    }

    @Test
    public void setNewConfigTest() throws JsonProcessingException, IOException {

        //Verifica che il metodo setConfig() scriva correttamente l'oggetto Config su file

        try(var path = mockStatic(Path.class)){
            try(MockedConstruction<XmlMapper> mockedMapper = mockConstruction(XmlMapper.class)){
                Model model = new Model("");
                model.setConfig(new Config("MyApp", "1.0", "11:05"));
                verify(mockedMapper.constructed().get(0).writeValueAsString(new Config("MyApp", "1.0", "11:05")));
                String xml = "<config><sourcePath>MyApp</sourcePath><destinationPath>1.0</destinationPath><date>11:05</date></config>";
                verify(Files.write(Paths.get(""), xml.getBytes()));
            }
        }
    }

    @Test
    public void notifyListenersTest() throws IOException {
        
        //Verifica che il metodo notifyObservers() chiami il metodo update() di tutti gli Observer

        Observer observer1 = mock(Observer.class);
        Observer observer2 = mock(Observer.class);
        Model model = new Model("");
        model.addObserver(observer1);
        model.addObserver(observer2);
        model.notifyObservers();
        verify(observer1).update(model);
        verify(observer2).update(model);
    }

    @Test
    public void getConfigPathTest() throws IOException {

        //Verifica che il metodo getConfigPath() restituisca il percorso del file di configurazione

        Model model = new Model("");
        assertThat(model.getConfigPath()).isEqualTo(Paths.get(""));
    }

    
}
