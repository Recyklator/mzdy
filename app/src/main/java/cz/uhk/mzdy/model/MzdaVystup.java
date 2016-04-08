package cz.uhk.mzdy.model;

import java.io.Serializable;

/**
 * Created by klapato1 on 11.3.2016.
 */
public class MzdaVystup implements Serializable {
    private int cista;
    private int dan;
    private int soc;
    private int zdr;

    public MzdaVystup(int cista, int dan, int soc, int zdr) {
        this.zdr = zdr;
        this.cista = cista;
        this.dan = dan;
        this.soc = soc;
    }

    public MzdaVystup() {
    }

    public int getCista() {
        return cista;
    }

    public void setCista(int cista) {
        this.cista = cista;
    }

    public int getDan() {
        return dan;
    }

    public void setDan(int dan) {
        this.dan = dan;
    }

    public int getSoc() {
        return soc;
    }

    public void setSoc(int soc) {
        this.soc = soc;
    }

    public int getZdr() {
        return zdr;
    }

    public void setZdr(int zdr) {
        this.zdr = zdr;
    }
}
