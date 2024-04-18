import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipFilesCheck {

    private final ClassLoader cl = ZipFilesCheck.class.getClassLoader();

    @Test
    void zipFilesCheck() throws Exception {
        try (InputStream is = cl.getResourceAsStream("setup.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            List<String> expectedFileNames = Arrays.asList(
                    "setup.xlsx",
                    "rekogz.csv",
                    "Топ 10 фразовых глаголов.pdf");
            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                Assertions.assertTrue(expectedFileNames.contains(fileName), "Файл: [" + fileName + "] не найден");
                System.out.println(fileName);
            }
        }
    }
}


