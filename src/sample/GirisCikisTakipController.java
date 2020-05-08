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
    private Button geri;
    private Main main;

    public void geri(ActionEvent e) throws IOException {
        this.getMain().setTitle("Anasayfa");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
        this.getAnchorPane().getChildren().setAll(pane);
    }


    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Main getMain() {
        if(this.main == null)
            this.main = new Main();
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Button getGeri() {
        return geri;
    }

    public void setGeri(Button geri) {
        this.geri = geri;
    }
}
