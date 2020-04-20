package sample;

import java.awt.*;

public class ITElemaniController extends PersonelContoller{
    private TextField uzmalik;

    public ITElemaniController(TextField uzmalik) {
        this.uzmalik = uzmalik;
    }

    public TextField getUzmalik() {
        return uzmalik;
    }

    public void setUzmalik(TextField uzmalik) {
        this.uzmalik = uzmalik;
    }
}
