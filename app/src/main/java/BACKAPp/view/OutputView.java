package BACKAPp.view;

import org.jetbrains.annotations.NotNull;

public interface OutputView {
    void set(int i, @NotNull String s);

    @NotNull String get(int i);

    int size();
}
