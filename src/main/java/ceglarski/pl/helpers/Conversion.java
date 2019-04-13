package ceglarski.pl.helpers;

import java.io.File;

public class Conversion {

    public void convert(File file){

        //System.out.println("File " + file.getName());

        ConverterFactory converterFactory = new ConverterFactory();
        IConverter converter = converterFactory.CreateConverter(file);


        converter.convertCustomers(file);
    }

}
