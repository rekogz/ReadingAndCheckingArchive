import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.*;
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


public class ReadXLSXFileFromArchive {

    @DisplayName("Чтение и проверка XLSX файла из ZIP архива")
    @Test
    public void xlsxParsingTest() throws Exception {
        String archiveFilePath = "src/test/resources/setup.zip";
        String fileNameToRead = "setup.xlsx";
        String expectedFirstCell = "Наименование";
        String expectedSecondCell = "DeepL";

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
                    Workbook workbook = WorkbookFactory.create(is);
                    Sheet sheet = workbook.getSheetAt(0);
                    Row firstRow = sheet.getRow(0);
                    Row secondRow = sheet.getRow(1);
                    Cell firstCell = firstRow.getCell(0);
                    Cell firstRowCell = secondRow.getCell(0);
                    System.out.println("Содержимое первой ячейки: " + firstCell.toString());
                    System.out.println("Содержимое первой ячейки второй строки: " + firstRowCell.toString());
                    Assertions.assertEquals(expectedFirstCell, firstCell.toString());
                    Assertions.assertEquals(expectedSecondCell, firstRowCell.toString());
                }
            } else {
                fail("Файл: " + fileNameToRead + " не найден в архиве");
            }
        }
    }
}
