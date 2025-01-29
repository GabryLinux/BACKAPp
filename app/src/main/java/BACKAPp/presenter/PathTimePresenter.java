package BACKAPp.presenter;

import BACKAPp.model.Model;
import BACKAPp.model.Observer;
import BACKAPp.view.PathTimeView;

public class PathTimePresenter implements Observer, InputPresenter {

    private Model model = null;
    private PathTimeView view = null;

    public PathTimePresenter(Model m, PathTimeView v) {
        // DIFFICOLTà : ★ //
        /**
         * Devi assegnare i parametri ai campi della classe
         */
        model.addObserver(this);
        view.addHandlers(this);
    }

    @Override
    public void action(String text, String text1) {
        // DIFFICOLTà : ★★★ //
        /**
         * In questo metodo devi eseguire il comando di impostare il task periodico.
         * Per farlo devi chiamare il metodo:
         * ScriptExecutor.registerPeriodicTask(model.getConfigPath(), model.getConfig().sourcePath(), model.getConfig().destinationPath(), model.getConfig().date());
         * se tale metodo restituisce 0 allora devi chiamare il metodo view.showSuccess();
         * altrimenti devi chiamare il metodo view.showError("Impossibile impostare il task periodico");
         */
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'action'");
    }

    @Override
    public void update(Model model) {
        // DIFFICOLTà : ★★★ //
        /**
         * Devi aggiornare la view con i parametri del modello, nella seguente maniera
         * es: view.set(0, model.getConfig().sourcePath());
         */
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
