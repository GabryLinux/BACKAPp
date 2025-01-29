package BACKAPp.presenter;

import BACKAPp.model.Model;
import BACKAPp.view.InputDate;

public class InputDatePresenter implements InputPresenter{
    private Model model = null;
    private InputDate inputDateView = null;
    public InputDatePresenter(Model model, InputDate inputDateView) {
        // DIFFICOLTà : ★ //
        /**
         * Devi assegnare i parametri ai campi della classe
         */

        inputDateView.addHandlers(this);
    }

    @Override
    public void action(String text, String text1) {
        // DIFFICOLTà : ★★★ //
        /**
         * Devi creare un oggetto Config con i parametri del modello, dove text è il sourcePath e text1 è il destinationPath.
         * L'ora del nuovo Config deve essere uguale al precedente Config (model.getConfig().date()).
         * Devi poi eseguire il metodo model.setConfig(<nuovo Config>).
         */
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'action'");
    }
    
}
