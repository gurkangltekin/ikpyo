package sample;

import javax.swing.text.html.ListView;

public class TeknikElemanController extends PersonelContoller {
    private ListView tecrube;
    private ListView alan;

    public TeknikElemanController(ListView tecrube, ListView alan) {
        this.tecrube = tecrube;
        this.alan = alan;
    }

    public ListView getTecrube() {
        return tecrube;
    }

    public void setTecrube(ListView tecrube) {
        this.tecrube = tecrube;
    }

    public ListView getAlan() {
        return alan;
    }

    public void setAlan(ListView alan) {
        this.alan = alan;
    }
}
