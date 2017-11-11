package ohtu.ohtuvarasto;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private double tilavuus = 0;  // paljonko varastoon mahtuu,  > 0
    private double saldo = 0;     // paljonko varastossa on nyt, >= 0

    // --- konstruktorit: ---
    public Varasto(double tilavuus) {  // tilavuus on annettava
        if (tilavuus > 0.0) {
            this.tilavuus = tilavuus;
            // virheellinen, nollataan
        }
    }

    // kuormitetaan
    public Varasto(double tilavuus, double alkuSaldo) {
        if (tilavuus > 0.0) {
            this.tilavuus = tilavuus;
            // virheellinen, nollataan
        }
        
        if (alkuSaldo <= tilavuus) {
            this.saldo = alkuSaldo;
        } else {
            this.saldo = tilavuus;  // täyteen ja ylimäärä hukkaan!
        }
    }

    // --- ottavat aksessorit eli getterit: ---
    public double getSaldo() {
        return saldo;
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() {  // huom: ominaisuus voidaan myös laskea
        return tilavuus - saldo;        //  ei tarvita erillistä kenttää vielaTilaa tms.
    }

    // --- asettavat aksessorit eli setterit: ---
    public void lisaaVarastoon(double maara) {
        // virhetilanteessa voidaan tehdä 
        if (maara < 0) {
            return;       // tällainen pikapoistuminenkin!
        }
         // omia aksessoreita voi kutsua
        if (maara <= paljonkoMahtuu()) {
            saldo = saldo + maara;          // ihan suoraan sellaisinaan
        } else {
            saldo = tilavuus;  // täyteen ja ylimäärä hukkaan!
        }
    }

    public double otaVarastosta(double maara) {
        // virhetilanteessa voidaan tehdä 
        if (maara < 0) {
            return 0.0;   // tällainen pikapoistuminenkin!
        }
        if (maara > saldo) {          // annetaan mitä voidaan
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0;               // ja tyhjäksi menee
            return kaikkiMitaVoidaan;  // poistutaan saman tien
        }
        // jos tänne päästään, kaikki pyydetty voidaan antaa
        saldo = saldo - maara;  // vähennetään annettava saldosta
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}