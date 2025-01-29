package BACKAPp.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import BACKAPp.presenter.InputPresenter;

public class BackupView extends Region implements OutputView, InputView {
    @NotNull private final Label[] rows;
    @NotNull private final List<InputPresenter> presenters = new ArrayList<>();
    @NotNull private final Label errorLabel = new Label();

    public BackupView(int rows, @NotNull String title) {
        this.rows = new Label[rows];
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        Label title1 = new Label("%-40s".formatted(title));
        title1.setFont(Font.font("sans", 20));
        grid.add(title1, 0, 0);
        for (int i = 0; i < rows; i++) {
            this.rows[i] = new Label("Backup #" + i);
            this.rows[i].setPadding(new Insets(10, 10, 10, 10));
            this.rows[i].setFont(Font.font("monospace", 14));
            grid.add(this.rows[i], 0, i + 1);
        }

        Button b = new Button("Esegui Backup");
        b.onActionProperty().set(e -> {
            for (InputPresenter p : presenters) {
                p.action(null,null);
            }
        });
        GridPane hb = new GridPane();
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.RIGHT);  // Allineamento a destra della colonna
        col.setHgrow(Priority.ALWAYS);
        hb.getColumnConstraints().add(col);
        hb.add(b, 1, 0);
        hb.add(errorLabel, 0, 0);
        grid.add(hb, 0, grid.getRowCount());
        this.getChildren().add(grid);
    }

    public void set(int i, @NotNull String s) {
        rows[i].setText(s);
    }

    public @NotNull String get(int i) {
        return rows[i].getText();
    }

    @Override
    public int size() {
        return rows.length;
    }

    @Override
    public void addHandlers(@NotNull InputPresenter presenter) {
        this.presenters.add(presenter);
    }

    @Override
    public void showError(@NotNull String s) {
        errorLabel.setText(s);
        setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5.0), Insets.EMPTY)));
    }

    @Override
    public void showSuccess() {
        errorLabel.setText("");
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), Insets.EMPTY)));
    }
}
