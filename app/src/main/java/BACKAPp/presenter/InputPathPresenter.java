package BACKAPp.presenter;

import BACKAPp.model.Model;
import BACKAPp.view.InputPath;

public class InputPathPresenter implements InputPresenter{
    private Model model = null;
    private InputPath inputPathView = null;
    public InputPathPresenter(Model model, InputPath inputPathView) {
        // DIFFICOLTà : ★ //
        //TODO Auto-generated constructor stub
        /**
         * Devi assegnare i parametri ai campi della classe
         */
        inputPathView.addHandlers(this);
    }

    @Override
    public void action(String text, String text1) {
        // DIFFICOLTà : ★★★ //
        /**
         * Devi creare un oggetto Config con i parametri del modello, dove text è il date e text1 dev'essere null, non devi considerarlo proprio.
         * Il sourcePath e il destinationPath del nuovo Config devono essere uguali a quelli del modello (model.getConfig().srcPath(), model.getConfig().destPath()).
         * Devi poi eseguire il metodo model.setConfig(<nuovo Config>).
         */
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'action'");
    }
    
}
