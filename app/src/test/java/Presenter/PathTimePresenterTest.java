package Presenter;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import BACKAPp.model.Model;
import BACKAPp.presenter.PathTimePresenter;
import BACKAPp.presenter.ScriptExecutor;
import BACKAPp.view.PathTimeView;

public class PathTimePresenterTest {
    
    @Test
    public void initPathTimePresenter(){
        Model m = mock();
        PathTimeView v = mock();
        PathTimePresenter ptp = new PathTimePresenter(m, v);

        verify(m).addObserver(ptp);
        verify(v).addHandlers(ptp);
    }

    @Test
    public void updateTest(){
        Model m = mock();
        PathTimeView v = mock();
        PathTimePresenter ptp = new PathTimePresenter(m, v);

        ptp.update(m);
        verify(v).set(0, m.getConfig().sourcePath());
        verify(v).set(1, m.getConfig().destinationPath());
        verify(v).set(2, m.getConfig().date());
    }

    @Test
    public void actionTest(){
        Model m = mock();
        PathTimeView v = mock();
        PathTimePresenter ptp = new PathTimePresenter(m, v);

        
        try(var mocked = Mockito.mockStatic(ScriptExecutor.class)){
            mocked.when(() -> ScriptExecutor.executeTask("")).thenReturn(0);
            ptp.action(null, null);
            mocked.verify(() -> ScriptExecutor.executeTask(""), Mockito.times(1));
            
            verify(v).set(0, m.getConfig().sourcePath());
            verify(v).set(1, m.getConfig().destinationPath());
            verify(v).set(2, m.getConfig().date());
            verify(v).showSuccess();
        }

        try(var mocked = Mockito.mockStatic(ScriptExecutor.class)){
            mocked.when(() -> ScriptExecutor.executeTask("")).thenReturn(1);
            ptp.action(null, null);
            mocked.verify(() -> ScriptExecutor.executeTask(""), Mockito.times(1));
            
            verify(v).showError(anyString());
        }
    }
}
