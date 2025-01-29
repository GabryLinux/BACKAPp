package BACKAPp.presenter;

import BACKAPp.model.Model;
import BACKAPp.model.Observer;
import BACKAPp.view.BackupView;

public class BackupPresenter implements Observer, InputPresenter {
    private Model model = null;
    private BackupView view = null;
    public BackupPresenter(Model m, BackupView v) {
        // DIFFICOLTà : ★ //
        /**
         * Devi assegnare a this.Model il model passato come parametro
         * Devi assegnare a this.view la BackupView passata come parametro
         * 
         */

        model.addObserver(this);
        view.addHandlers(this);
    }

    @Override
    public void update(Model model) {
        // DIFFICOLTà : ★★★ //
        /**
         * Devi scrivere un metodo che aggiorni la view con i backup presenti nella directory,
         * cioè prenda i primi tre backup (se ce ne sono) e li mostri nella view, nella seguente maniera:
         * 
         * List<FileTime> backups = model.getBackupsFromDirectory(<estensione del file>);
         * view.set(0, backups.get(0).toString());
         * view.set(1, backups.get(1).toString());
         * view.set(2, backups.get(2).toString());
         * 
         * Se backups ha meno di 3 elementi, le stringhe restanti devi impostarle come vuote:
         * view.set(2, "");
         */

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void action(String text, String text1) {
        // DIFFICOLTà : ★★★★★ //
        /**
         * Quando si chiama questo metodo, devi fare in modo che il modello crei un backup,
         * cioè dev'essere chiamato il metodo statico ScriptExecutor.executeTask(model.getConfigPath()).
         * Se il task è stato eseguito correttamente (cioè se il metodo statico restituisce 0), devi aggiornare la view con i nuovi backup
         * e infine chiamare il metodo view.showSuccess() .
         * Altrimenti, devi mostrare un messaggio di errore nella view invocando il metodo view.showError("Impossibile eseguire il task").
         */
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'action'");
    }
    
}
