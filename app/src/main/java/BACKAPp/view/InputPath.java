package BACKAPp.view;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.jetbrains.annotations.NotNull;

import BACKAPp.presenter.InputPresenter;

public class InputPath extends Region implements InputView {
    final private TextField text1 = new TextField();
    final private TextField text2 = new TextField();
    final private Button addButton = new Button("Cambia Percorso");
    final private List<InputPresenter> presenters = new ArrayList<>();

    @NotNull
    private final Label error;


    public InputPath() {
        
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

        error = new Label();
        text1.setPrefColumnCount(25);
        text2.setPrefColumnCount(25);

        text1.setOnMouseClicked(eh -> {
            String path = getPath();
            if(!path.isBlank()) text1.setText(path);
        });
        text2.setOnMouseClicked(eh -> {
            String path = getPath();
            if(!path.isBlank()) text2.setText(path);
        });

        addButton.onActionProperty().set(
           e -> {
            for (InputPresenter ip : presenters) {
                ip.action(text1.getText(), text2.getText());
            }
           }
        );

        GridPane grid = new GridPane();
       

        Label title1 = new Label("%-40s".formatted("Imposta Percorso"));
        title1.setFont(Font.font("sans", 20));
        grid.add(title1, 0, 0);

        
        grid.setPadding(new Insets(10, 0, 0, 10));
        grid.add(new Label("Percorso Sorgente: "), 0, 1);
        grid.add(text1, 0, 2);

        grid.setPadding(new Insets(10, 0, 0, 10));
        grid.add(new Label("Percorso Destinazione: "), 0, 3);
        grid.add(text2, 0, 4);
        
        GridPane grid2 = new GridPane();
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.RIGHT); // Allinea il contenuto a destra
        grid2.getColumnConstraints().add(col);
        col.setHgrow(Priority.ALWAYS);
        GridPane.setHalignment(addButton, HPos.RIGHT);
        // Aggiungi il pulsante
        grid2.add(addButton, 1, 0);
        grid2.add(error, 0, 0);
        col.setHalignment(HPos.RIGHT);  // Allineamento a destra della colonna
        col.setHgrow(Priority.ALWAYS);
        // Aggiungi grid2 al grid principale
        grid.add(grid2, 0, 5);

        

        this.getChildren().add(grid);
    }

    private String getPath(){
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getParentFile().getPath();
        }else{
            return "";
        }
    }

    public void addHandlers(@NotNull InputPresenter presenter) {
        addButton.setOnAction(eh -> presenter.action(text1.getText(), text2.getText()));
    }

    @Override
    public void showError(@NotNull String s) {
        error.setText(s);
        setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5.0), Insets.EMPTY)));
    }

    @Override
    public void showSuccess() {
        error.setText("");
        text1.clear();
        text2.clear();
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), Insets.EMPTY)));
    }
}
