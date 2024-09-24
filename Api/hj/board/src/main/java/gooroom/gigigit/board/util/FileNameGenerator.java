package gooroom.gigigit.board.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class FileNameGenerator {
    public static String generatorName(String fileName){
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmssSSS"));
        Random rd = new Random();
        int nonNegativeInt = Math.abs(rd.nextInt(1000000));
        return currentTime + "_" + nonNegativeInt + "_" +fileName;
    }
}
