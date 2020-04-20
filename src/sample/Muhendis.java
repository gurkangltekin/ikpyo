package sample;

public class Muhendis extends TeknikEleman {
    private String uzmanlik;

    public Muhendis(int tecrube, String alan, String uzmanlik) {
        super(tecrube, alan);
        this.uzmanlik = uzmanlik;
    }

    public String getUzmanlik() {
        return uzmanlik;
    }

    public void setUzmanlik(String uzmanlik) {
        this.uzmanlik = uzmanlik;
    }
}
