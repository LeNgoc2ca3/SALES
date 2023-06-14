package com.ngoclbph26472.assignment.service.ipml;

import com.ngoclbph26472.assignment.dto.OrdersDetailsDTO;
import com.ngoclbph26472.assignment.entity.OrdersDetails;
import com.ngoclbph26472.assignment.repositories.OrderDetailRepository;
import com.ngoclbph26472.assignment.repositories.OrderRepository;
import com.ngoclbph26472.assignment.service.OrdersDetailsService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.RichTextString;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OrderDetailsIpml implements OrdersDetailsService {

    @Autowired
    OrderDetailRepository repo;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper mapper;

//    @Override
//    public ResponseEntity<EmployeeDTO> create(EmployeeDTO employeeDTO) {
//        Employee employee = mapper.map(employeeDTO, Employee.class);
//        if (!repo.existsById(employee.getId())) {
//            repo.save(employee);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @Override
//    public ResponseEntity<EmployeeDTO> update(EmployeeDTO employeeDTO) {
//        Employee employee = mapper.map(employeeDTO, Employee.class);
//        if (repo.existsById(employee.getId())) {
//            repo.save(employee);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @Override
//    public void delete(int id) {
//        repo.deleteById(id);
//    }
//
//    @Override
//    public ResponseEntity<Employee> searchDTO(Employee employeeDTO) {
//        Employee employee = mapper.map(employeeDTO, Employee.class);
//        repo.findByEmployee(employee.getId(),employee.getCode(),employee.getEmail(),employee.getName(),employee.getPhone());
//        return ResponseEntity.ok(employeeDTO);
//    }

    @Override
    public ResponseEntity<List<OrdersDetailsDTO>> getAll() {
        List<OrdersDetails> ordersDetailsList = repo.findAll();
        TypeToken<List<OrdersDetailsDTO>> typeToken = new TypeToken<>() {
        };
        List<OrdersDetailsDTO> ordersDetailsDTOList = mapper.map(ordersDetailsList, typeToken.getType());
        return ResponseEntity.ok(ordersDetailsDTOList);
    }

    @Override
    public void generateExcel(HttpServletResponse response) throws IOException {//gửi excel dưới dạng response
        //và khách hàng sẽ đc nhận
        List<OrdersDetails> ordersDetailsList = repo.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employee");
        HSSFRow row = sheet.createRow(0);//tạo dòng đầu cho tiêu đề
        row.createCell(0).setCellValue("STT");
        row.createCell(1).setCellValue("CODE");
        row.createCell(2).setCellValue("NAME");
        row.createCell(3).setCellValue("EMAIL");
        row.createCell(4).setCellValue("PHONE");

        int dataRowIndex = 1; // tạo cho dữ liệu bắt đầu từ 1
        int i = 0;
        for (OrdersDetails details : ordersDetailsList){
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(i++);
            dataRow.createCell(1).setCellValue(details.getProducts().getName());
            dataRow.createCell(2).setCellValue(details.getOrders().getCreateDate());
            dataRow.createCell(3).setCellValue(details.getQuantity());
            dataRow.createCell(4).setCellValue((RichTextString) details.getPrice());
            dataRowIndex++;//tăng dòng
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);//ghi dữ liệu vào outputStream
        workbook.close();
        ops.close();

    }

    @Override
    public OrdersDetails create(OrdersDetails ordersDetails) {
        System.out.println(ordersDetails);
        return repo.save(ordersDetails);
    }

    @Override
    public List<OrdersDetails> create(List<OrdersDetails> ordersDetails) {
        System.out.println(ordersDetails);
        repo.saveAll(ordersDetails);
        return ordersDetails;
    }
}
