package BACKAPp.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScriptExecutor {
    private ScriptExecutor() {}

    public final static String SCRIPT_PERIODICTASK_PATH = "C:\\Users\\Gabry\\Desktop\\projects\\BACKAPp\\app\\src\\main\\java\\resources\\periodicTaskScript.ps1";
    public final static String SCRIPT_TASK_PATH = "C:\\Users\\Gabry\\Desktop\\projects\\BACKAPp\\app\\src\\main\\java\\resources\\executeTask.ps1";
    /**
     * Questa chiamata è l'equivalente del comando powershell:
     * powershell -file <scriptPath> -StartTime <date> -srcPath <srcPath> -dstPath <dstPath>
     * Ossia registra il task periodico da eseguire.
     * @param scriptPath il percorso ASSOLUTO dello script da eseguire. Lo script in questione è periodicTaskScript.ps1
     * @param srcPath il percorso ASSOLUTO della cartella in cui si trova il file da copiare
     * @param dstPath il percorso ASSOLUTO della cartella in cui copiare il file
     * @param date l'ora in cui eseguire il task periodico. Deve presentarsi nella forma "HH:mm"
     * @throws NullPointerException se uno dei parametri è nullo
     * @throws IllegalArgumentException se la data non è nel formato corretto
     * @throws IllegalArgumentException se lo script non esiste o se i percorsi non sono corretti
     */
    public static int registerPeriodicTask(String scriptPath, String srcPath, String dstPath, String date) throws Exception{
        if(scriptPath == null || srcPath == null || dstPath == null || date == null) {
            throw new NullPointerException("I parametri non possono essere nulli");
        }
        if(!date.matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("La data deve essere nel formato HH:mm");
        }
        if(!Files.exists(Paths.get(scriptPath))) {
            throw new IllegalArgumentException("Lo script non esiste");
        }
        if(!Files.exists(Paths.get(srcPath)) || !Files.exists(Paths.get(dstPath))) {
            throw new IllegalArgumentException("I percorsi non sono corretti");
        }


        // Comando PowerShell da eseguire
        String command = "powershell.exe -file " + scriptPath + 
        " -StartTime " + date + 
        " -srcPath " + srcPath + 
        " -dstPath " + dstPath;

        // Costruisci il processo
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.redirectErrorStream(true); // Combina l'output di errore con l'output standard

        // Avvia il processo
        Process process = processBuilder.start();

        // Leggi l'output del comando PowerShell
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // Attendi la fine del processo
        return process.waitFor();
    }

    /**
     * Questo metodo esegue il task periodico registrato con registerPeriodicTask
     */
    public static int executeTask(String path) throws Exception{
        // Comando PowerShell da eseguire
        String command = "powershell.exe -file " + path;

        // Costruisci il processo
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.redirectErrorStream(true); // Combina l'output di errore con l'output standard

        // Avvia il processo
        Process process = processBuilder.start();

        // Leggi l'output del comando PowerShell
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        return process.waitFor();
    }
}
