package Presenter;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.MockedStatic;

import java.nio.file.attribute.FileTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import BACKAPp.model.Model;
import BACKAPp.presenter.BackupPresenter;
import BACKAPp.presenter.ScriptExecutor;
import BACKAPp.view.BackupView;

public class BackupPresenterTest {

    @Test
    public void initPresenter() {
        Model m = mock();
        BackupView v = mock();
        BackupPresenter bp = new BackupPresenter(m, v);

        verify(m).addObserver(bp);
        verify(v).addHandlers(bp);
    }

    @Test
    public void updateTest() {
        Model m = mock();
        BackupView v = mock();
        BackupPresenter bp = new BackupPresenter(m, v);

        FileTime f1 = mock();
        FileTime f2 = mock();
        when(f1.toString()).thenReturn("f1");
        when(f2.toString()).thenReturn("f2");
        when(m.getBackupsFromDirectory("")).thenReturn(List.of(f1, f2));
        bp.update(m);
        verify(v).set(anyInt(), eq("f2"));
        verify(v).set(anyInt(), eq("f1"));
    }

    @Test
    public void actionTest() {
        Model m = mock();
        BackupView v = mock();
        BackupPresenter bp = new BackupPresenter(m, v);

        try (MockedStatic<ScriptExecutor> mockStatic = Mockito.mockStatic(ScriptExecutor.class)) {
            mockStatic.when(() -> ScriptExecutor.executeTask(anyString())).thenReturn(0);
            bp.action(null, null);
            mockStatic.verify(() -> ScriptExecutor.executeTask(""), times(1));
            verify(m).getBackupsFromDirectory(anyString());
            verify(v).set(anyInt(), anyString());
        }

        try (MockedStatic<ScriptExecutor> mockStatic = Mockito.mockStatic(ScriptExecutor.class)) {
            mockStatic.when(() -> ScriptExecutor.executeTask(anyString())).thenReturn(0);
            bp.action(null, null);
            mockStatic.verify(() -> ScriptExecutor.executeTask(""), times(1));
            verify(m).getBackupsFromDirectory(anyString());
            verify(v).set(anyInt(), anyString());
        }
    }
}
