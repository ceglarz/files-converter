package ceglarski.pl;

import ceglarski.pl.helpers.ConverterTXT;
import ceglarski.pl.models.Type;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterTXTTest {

    @Test
    public void phoneValidation1(){
        ConverterTXT converter = new ConverterTXT();
        assertEquals(Type.phone, converter.valideType("654 765 765"));
    }

    @Test
    public void phoneValidation2(){
        ConverterTXT converter = new ConverterTXT();
        assertEquals(Type.phone, converter.valideType("123456789"));
    }

    @Test
    public void phoneValidation3(){
        ConverterTXT converter = new ConverterTXT();
        assertEquals(Type.phone, converter.valideType("+48123456789"));
    }

    @Test
    public void unknownValidation(){
        ConverterTXT converter = new ConverterTXT();
        assertEquals(Type.unknown, converter.valideType("12321"));
    }

    @Test
    public void emailValidation1(){
        ConverterTXT converter = new ConverterTXT();
        assertEquals(Type.email, converter.valideType("adres.email@test.pl"));
    }

    @Test
    public void emailValidation2(){
        ConverterTXT converter = new ConverterTXT();
        assertEquals(Type.unknown, converter.valideType("adres @test.pl"));
    }

    @Test
    public void jabberValidation(){
        ConverterTXT converter = new ConverterTXT();
        assertEquals(Type.jabber, converter.valideType("jbr"));
    }
}
