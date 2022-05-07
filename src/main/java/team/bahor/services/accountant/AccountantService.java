package team.bahor.services.accountant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import team.bahor.dto.accountant.Accountant;
import team.bahor.repositories.exam.ExamRepository;
import team.bahor.services.base.BaseGenericService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountantService implements BaseGenericService {
    private String[] HEADERs = {"Course Name", "Count", "Amount"};
    private String SHEET = "boom.academy";

    private final ExamRepository repository;

    public ByteArrayInputStream accountant() {
        try {
            return this.tutorialsToExcel(new ObjectMapper().readValue(repository.accountant(), new TypeReference<List<Accountant>>() {
            }));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ByteArrayInputStream tutorialsToExcel(List<Accountant> accountants) {
        try {
            Workbook workBook = new XSSFWorkbook();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Sheet sheet = workBook.createSheet(SHEET);
            Row rowHeader = sheet.createRow(0);
            for (int i = 0; i < HEADERs.length; i++) {
                Cell cell = rowHeader.createCell(i);
                cell.setCellValue(HEADERs[i]);
            }
            int rowIdx = 1;
            for (Accountant accountant : accountants) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(accountant.getCourseName());
                row.createCell(1).setCellValue(accountant.getCount());
                row.createCell(2).setCellValue(accountant.getSum());
            }
            workBook.write(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
