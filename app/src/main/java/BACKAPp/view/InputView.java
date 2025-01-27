package BACKAPp.view;

import org.jetbrains.annotations.NotNull;

import BACKAPp.presenter.InputPresenter;

public interface InputView {
    void addHandlers(@NotNull InputPresenter presenter);

    void showError(@NotNull String s);

    void showSuccess();
}
