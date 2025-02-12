package BACKAPp.view;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import BACKAPp.presenter.InputPresenter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PathTimeView extends Region implements OutputView, InputView {
    @NotNull private final Label[] rows;
    @NotNull private final List<InputPresenter> presenters = new ArrayList<>();
    @NotNull private final Label errorLabel = new Label();
    public PathTimeView(){
        String title = "Configurazione Attuale";
        this.rows = new Label[3];
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        Label title1 = new Label("%-40s".formatted(title));
        title1.setFont(Font.font("sans", 20));
        grid.add(title1, 0, 0);


        this.rows[0] = new Label("Percorso Sorgente: ");
        this.rows[0].setPadding(new Insets(10, 10, 10, 10));
        this.rows[0].setFont(Font.font("monospace", 14));
        grid.add(this.rows[0], 0, 1);


        this.rows[1] = new Label("Percorso Destinazione: ");
        this.rows[1].setPadding(new Insets(10, 10, 10, 10));
        this.rows[1].setFont(Font.font("monospace", 14));
        grid.add(this.rows[1], 0, 2);

        this.rows[2] = new Label("Ora Backup: ");
        this.rows[2].setPadding(new Insets(10, 10, 10, 10));
        this.rows[2].setFont(Font.font("monospace", 14));
        grid.add(this.rows[2], 0, 3);

        
        //errorLabel.setPadding(new Insets(5, 0, 10, 10));
        //errorLabel.setFont(Font.font("monospace", 10));
        //grid.add(errorLabel, 0, 4);
        
        Button b = new Button("Imposta Task");
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

    @Override
    public void set(int i, @NotNull String s) {
        if(i < 0 || i >= rows.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        if(s == null) {
            throw new IllegalArgumentException("Invalid string");
        }
        rows[i].setText(s);
    }

    @Override
    public @NotNull String get(int i) {
        if(i < 0 || i >= rows.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        return rows[i].getText();
    }

    @Override
    public int size() {
        return rows.length;
    }

    @Override
    public void addHandlers(@NotNull InputPresenter presenter) {
        presenters.add(presenter);
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
