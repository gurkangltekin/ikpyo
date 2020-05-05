package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GirisCikisTakipController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
     Button geri;
    private Main main;

    public void geri(ActionEvent e) throws IOException {
        this.main.setTitle("Anasayfa");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
        this.anchorPane.getChildren().setAll(pane);
    }


    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
