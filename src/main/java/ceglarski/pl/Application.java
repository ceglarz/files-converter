package ceglarski.pl;

import ceglarski.pl.helpers.Conversion;
import java.io.File;

public class Application {

    public static void main(String[] args) {

        File directory = new File("./data");
        File[] listOfFiles = directory.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                new Conversion().convert(listOfFiles[i]);
            }
        }

    }

}
