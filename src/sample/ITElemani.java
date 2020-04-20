package sample;

public class ITElemani extends TeknikEleman {

    private String uzmalik;

    public ITElemani(int tecrube, String alan, String uzmalik) {
        super(tecrube, alan);
        this.uzmalik = uzmalik;
    }

    public String getUzmalik() {
        return uzmalik;
    }

    public void setUzmalik(String uzmalik) {
        this.uzmalik = uzmalik;
    }
}
