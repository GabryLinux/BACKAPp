package Presenter;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import BACKAPp.model.Config;
import BACKAPp.model.Model;
import BACKAPp.presenter.InputDatePresenter;
import BACKAPp.view.InputDate;

public class InputDatePresenterTest {
    
    @Test
    public void initInputDatePresenter(){
         Model model = mock();
         InputDate inputDateView = mock();
         InputDatePresenter inputDatePresenter = new InputDatePresenter(model,inputDateView);
        
         verify(inputDateView).addHandlers(inputDatePresenter);
    }

    @Test
    public void actionTest(){
        Model model = mock();
        InputDate inputDateView = mock();
        InputDatePresenter inputDatePresenter = new InputDatePresenter(model,inputDateView);
        
        inputDatePresenter.action("11:30",null);
        verify(model).setConfig(new Config(model.getConfig().sourcePath(), model.getConfig().destinationPath(), "11:30"));
        verify(inputDateView).showSuccess();

        inputDatePresenter.action("A", null);
        verify(model, times(0)).setConfig(new Config(model.getConfig().sourcePath(), model.getConfig().destinationPath(), "A"));
        verify(inputDateView).showError(anyString());
    }
}
