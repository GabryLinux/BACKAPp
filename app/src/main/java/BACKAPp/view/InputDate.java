package BACKAPp.view;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jetbrains.annotations.NotNull;

import BACKAPp.presenter.InputPresenter;

public class InputDate extends Region implements InputView {
    final TextField text = new TextField();
    final Button addButton = new Button("Cambia");

    @NotNull
    private final Label error;


    public InputDate() {
        
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

        error = new Label();
        


        GridPane grid = new GridPane();
       

        Label title1 = new Label("%-40s".formatted("Imposta Orario"));
        title1.setFont(Font.font("sans", 20));
        grid.add(title1, 0, 0);

        
        grid.setPadding(new Insets(10, 0, 0, 10));
        grid.add(text, 0, 1);
        
        Button b = new Button("Cambia Orario");
        GridPane hb = new GridPane();
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.RIGHT);  // Allineamento a destra della colonna
        col.setHgrow(Priority.ALWAYS);
        RowConstraints row = new RowConstraints();
        row.setValignment(VPos.BOTTOM);
        row.setVgrow(Priority.ALWAYS);

        hb.getColumnConstraints().add(col);
        hb.getRowConstraints().add(row);        
        hb.add(b, 1, 0);
        hb.add(error, 0, 0);
        grid.add(hb, 0, grid.getRowCount());

        this.getChildren().add(grid);
    }
    public void addHandlers(@NotNull InputPresenter presenter) {
        addButton.setOnAction(eh -> presenter.action(text.getText(), null));
    }

    @Override
    public void showError(@NotNull String s) {
        error.setText(s);
        setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5.0), Insets.EMPTY)));
    }

    @Override
    public void showSuccess() {
        error.setText("");
        text.clear();
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), Insets.EMPTY)));
    }
}

