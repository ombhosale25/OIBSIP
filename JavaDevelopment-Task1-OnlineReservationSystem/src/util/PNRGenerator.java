package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PNRGenerator {

    public static String generatePNR() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return "PNR" + LocalDateTime.now().format(formatter);

    }

}