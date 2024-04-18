package ReadingAndCheckingTest;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.fail;


public class ReadCSVFileFromArchive {

    @DisplayName("Чтение и проверка CSV файла из ZIP архива")
    @Test
    public void csvParsingTest() throws Exception {
        String archiveFilePath = "src/test/resources/setup.zip";
        String fileNameToRead = "rekogz.csv";
        String[] firstLine = {"Wood", "Stone"};
        String[] secondLine = {"Beef", "Chicken"};

        boolean isFirst = true;

        try (ZipFile zipFile = new ZipFile(new File(archiveFilePath))) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (isFirst) {
                    System.out.println("Файлы внутри архива: ");
                    isFirst = false;
                }
                System.out.println(entry.getName());
            }
            ZipEntry entry = zipFile.getEntry(fileNameToRead);
            if (entry != null) {
                try (InputStream is = zipFile.getInputStream(entry);
                     CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(firstLine, content.get(0));
                    Assertions.assertArrayEquals(secondLine, content.get(1));
                }
            } else {
                fail("Файл: " + fileNameToRead + " не найден в архиве");
            }
        }
    }
}
