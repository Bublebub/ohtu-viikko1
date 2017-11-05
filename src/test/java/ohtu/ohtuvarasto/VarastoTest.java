package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    //Uudet testit
    
    @Test
    public void liikaTavaraTäyttääVaraston() {
        //täytetään varasto
        varasto.lisaaVarastoon(20);
        
        //varaston pitäisi olla täysi
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaLiikaa() {
        
        //lisätään varastoon
        varasto.lisaaVarastoon(2);
        
        //varaston pitäisi palauttaa vain 2
        assertEquals(2, varasto.otaVarastosta(5), vertailuTarkkuus);
        
    }
    
    @Test
    public void virheellinenAlustusLuoTyhjänVaraston() {
        varasto = new Varasto(0.0);
        
        //varaston tilavuudeen tulisi olla tyhjä
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellinenTuplaAlustusLuoTyhjänVaraston() {
        varasto = new Varasto(0.0, 0);
        
        //varaston tilavuudeen tulisi olla tyhjä
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellinenNegatiivinenSaldonAlustusLuoTyhjänVaraston() {
        varasto = new Varasto(1, -4);
        
        //varaston tilavuudeen tulisi olla tyhjä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaSaldoTäyttääVarastonAlustuksessa() {
        varasto = new Varasto(1, 7);
        
        //varaston tulisi olla täysi
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisäysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-4);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenNostoPalauttaaNollan() {
        
        assertEquals(0, varasto.otaVarastosta(-5), vertailuTarkkuus);
    }

    @Test
    public void toStringToimii() {
        
        //tulisi tulostaa oikea merkkijono
        assertEquals("saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu(), varasto.toString());
    }
    
}