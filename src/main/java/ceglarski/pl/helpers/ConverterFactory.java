package ceglarski.pl.helpers;

import java.io.File;

public class ConverterFactory {
    public IConverter CreateConverter(File file)
    {
        try {
            String fileExtension = getFileExtension(file);
            if (fileExtension.equalsIgnoreCase("txt") || fileExtension.equalsIgnoreCase("csv")) {
                return new ConverterTXT();
            } else if (fileExtension.equalsIgnoreCase("xml")) {
                return new ConverterXML();
            } else {
                throw new Exception();
            }

        }
        catch(Exception e){

        }

        return null;
    }

    private String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf(".")+1);
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }
}
