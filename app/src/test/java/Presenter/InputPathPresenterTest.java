package Presenter;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import BACKAPp.model.Config;
import BACKAPp.model.Model;
import BACKAPp.presenter.InputPathPresenter;
import BACKAPp.view.InputPath;
import javafx.scene.control.TextField;

public class InputPathPresenterTest {
    
    @Test
    public void initPathPresenterTest(){
        Model model = mock();
        InputPath inputPathView = mock();
        InputPathPresenter inputPathPresenter = new InputPathPresenter(model,inputPathView);
        
        verify(inputPathView).addHandlers(inputPathPresenter);
    }

    @Test
    public void actionTest(){
        Model model = mock();
        InputPath inputPathView = mock();
        InputPathPresenter inputPathPresenter = new InputPathPresenter(model,inputPathView);
        
        inputPathPresenter.action("./","./");
        verify(model).setConfig(new Config("A", "B", model.getConfig().date()));
        verify(inputPathView).showSuccess();

        inputPathPresenter.action("A","B");
        verify(model, times(0)).setConfig(new Config("A", "B", model.getConfig().date()));
        verify(inputPathView).showError(anyString());
    }
}
