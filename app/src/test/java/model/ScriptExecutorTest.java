package model;

import java.io.IOError;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import BACKAPp.presenter.ScriptExecutor;

public class ScriptExecutorTest {
    
    
    @Test
    public void testExecuteScript() throws Exception {
        
        ScriptExecutor.registerPeriodicTask(
            ScriptExecutor.SCRIPT_PERIODICTASK_PATH,
            "C:\\Users\\Gabry\\Desktop\\projects\\BACKAPp\\app\\src\\main\\java\\resources\\src",
            "C:\\Users\\Gabry\\Desktop\\projects\\BACKAPp\\app\\src\\main\\java\\resources\\dest",
            "12:56"
        );

        ScriptExecutor.executeTask(ScriptExecutor.SCRIPT_TASK_PATH);
    }
}
