package ReadingAndCheckingTest;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.fail;


public class ReadPDFFileFromArchive {

    @DisplayName("Чтение и проверка PDF файла из ZIP архива")
    @Test
    public void pdfParsingTest() throws Exception {
        String archiveFilePath = "src/test/resources/setup.zip";
        String fileNameToRead = "Топ 10 фразовых глаголов.pdf";

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
                try (InputStream is = zipFile.getInputStream(entry)) {
                    PDF pdf = new PDF(is);
                    Assertions.assertTrue(pdf.text.contains("ТОП 10 ФРАЗОВЫХ ГЛАГОЛОВ ОТ НОСИТЕЛЕЙ"));
                }
            } else {
                fail("Файл: " + fileNameToRead + " не найден в архиве");
            }
        }
    }
}
