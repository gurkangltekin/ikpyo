package sample;

import java.awt.*;

public class MuhendisController extends PersonelContoller {
    private TextField uzmanlik;

    public MuhendisController(TextField uzmanlik) {
        this.uzmanlik = uzmanlik;
    }

    public TextField getUzmanlik() {
        return uzmanlik;
    }

    public void setUzmanlik(TextField uzmanlik) {
        this.uzmanlik = uzmanlik;
    }
}
