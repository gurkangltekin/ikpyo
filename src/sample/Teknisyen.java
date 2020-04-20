package sample;

public class Teknisyen extends TeknikEleman {
    private String departman;

    public Teknisyen(int tecrube, String alan, String departman) {
        super(tecrube, alan);
        this.departman = departman;
    }


    public String getDepartman() {
        return departman;
    }


    public void setDepartman(String departman) {
        this.departman = departman;
    }
}
