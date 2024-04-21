package ma.enset.hospital.utlis;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Utils {
    public static String slugify(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String ascii = pattern.matcher(normalized).replaceAll("");
        return ascii.toLowerCase().replaceAll("[^a-z0-9\\-]", "-").replaceAll("-{2,}", "-").replaceAll("^-|-$", "");
    }
}
