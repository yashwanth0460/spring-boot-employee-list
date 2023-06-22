package com.example.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

@WebMvcTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTests {

        @Autowired
        private MockMvc mockMvc;

        Employee employee1 = new Employee(1, "John Doe", "johndoe@example.com", "Marketing");
        Employee employee2 = new Employee(2, "Jane Smith", "janesmith@example.com", "Human Resources");
        Employee employee3 = new Employee(3, "Bob Johnson", "bjohnson@example.com", "Sales");
        Employee employee4 = new Employee(4, "Alice Lee", "alee@example.com", "IT");
        Employee employee5 = new Employee(5, "Mike Brown", "mbrown@example.com", "Finance");
        Employee employee6 = new Employee(6, "Sara Lee", "slee@example.com", "Operations");
        Employee employeepost = new Employee(7, "Peter Parker", "peterparker@example.com", "IT");
        Employee employeeput = new Employee(2, "Steve Smith", "stevesmith@example.com", "Human Resources");

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer();

        @Test
        @Order(1)
        public void testGetEmployees() throws Exception {
                mockMvc.perform(get("/employees"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(6)))
                                .andExpect(jsonPath("$[0].employeeId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].employeeName",
                                                Matchers.equalToIgnoringCase(employee1.getEmployeeName())))
                                .andExpect(jsonPath("$[0].email", Matchers.equalToIgnoringCase(employee1.getEmail())))
                                .andExpect(jsonPath("$[0].department",
                                                Matchers.equalToIgnoringCase(employee1.getDepartment())))
                                .andExpect(jsonPath("$[1].employeeId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].employeeName",
                                                Matchers.equalToIgnoringCase(employee2.getEmployeeName())))
                                .andExpect(jsonPath("$[1].email", Matchers.equalToIgnoringCase(employee2.getEmail())))
                                .andExpect(jsonPath("$[1].department",
                                                Matchers.equalToIgnoringCase(employee2.getDepartment())))
                                .andExpect(jsonPath("$[2].employeeId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].employeeName",
                                                Matchers.equalToIgnoringCase(employee3.getEmployeeName())))
                                .andExpect(jsonPath("$[2].email", Matchers.equalToIgnoringCase(employee3.getEmail())))
                                .andExpect(jsonPath("$[2].department",
                                                Matchers.equalToIgnoringCase(employee3.getDepartment())))
                                .andExpect(jsonPath("$[3].employeeId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$[3].employeeName",
                                                Matchers.equalToIgnoringCase(employee4.getEmployeeName())))
                                .andExpect(jsonPath("$[3].email", Matchers.equalToIgnoringCase(employee4.getEmail())))
                                .andExpect(jsonPath("$[3].department",
                                                Matchers.equalToIgnoringCase(employee4.getDepartment())))
                                .andExpect(jsonPath("$[4].employeeId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$[4].employeeName",
                                                Matchers.equalToIgnoringCase(employee5.getEmployeeName())))
                                .andExpect(jsonPath("$[4].email", Matchers.equalToIgnoringCase(employee5.getEmail())))
                                .andExpect(jsonPath("$[4].department",
                                                Matchers.equalToIgnoringCase(employee5.getDepartment())))
                                .andExpect(jsonPath("$[5].employeeId", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$[5].employeeName",
                                                Matchers.equalToIgnoringCase(employee6.getEmployeeName())))
                                .andExpect(jsonPath("$[5].email", Matchers.equalToIgnoringCase(employee6.getEmail())))
                                .andExpect(jsonPath("$[5].department",
                                                Matchers.equalToIgnoringCase(employee6.getDepartment())));
        }

        @Test
        @Order(2)
        public void testGetNotfound() throws Exception {
                mockMvc.perform(get("/employees/16"))
                                .andExpect(status().isNotFound());

        }

        @Test
        @Order(3)
        public void testGetEmployeeById() throws Exception {

                mockMvc.perform(get("/employees/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.employeeName",
                                                Matchers.equalToIgnoringCase(employee1.getEmployeeName())))
                                .andExpect(jsonPath("$.email", Matchers.equalToIgnoringCase(employee1.getEmail())))
                                .andExpect(jsonPath("$.department",
                                                Matchers.equalToIgnoringCase(employee1.getDepartment())));

                mockMvc.perform(get("/employees/2"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.employeeName",
                                                Matchers.equalToIgnoringCase(employee2.getEmployeeName())))
                                .andExpect(jsonPath("$.email", Matchers.equalToIgnoringCase(employee2.getEmail())))
                                .andExpect(jsonPath("$.department",
                                                Matchers.equalToIgnoringCase(employee2.getDepartment())));

                mockMvc.perform(get("/employees/3"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.employeeName",
                                                Matchers.equalToIgnoringCase(employee3.getEmployeeName())))
                                .andExpect(jsonPath("$.email", Matchers.equalToIgnoringCase(employee3.getEmail())))
                                .andExpect(jsonPath("$.department",
                                                Matchers.equalToIgnoringCase(employee3.getDepartment())));

                mockMvc.perform(get("/employees/4"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.employeeName",
                                                Matchers.equalToIgnoringCase(employee4.getEmployeeName())))
                                .andExpect(jsonPath("$.email", Matchers.equalToIgnoringCase(employee4.getEmail())))
                                .andExpect(jsonPath("$.department",
                                                Matchers.equalToIgnoringCase(employee4.getDepartment())));

                mockMvc.perform(get("/employees/5"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.employeeName",
                                                Matchers.equalToIgnoringCase(employee5.getEmployeeName())))
                                .andExpect(jsonPath("$.email", Matchers.equalToIgnoringCase(employee5.getEmail())))
                                .andExpect(jsonPath("$.department",
                                                Matchers.equalToIgnoringCase(employee5.getDepartment())));

                mockMvc.perform(get("/employees/6"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$.employeeName",
                                                Matchers.equalToIgnoringCase(employee6.getEmployeeName())))
                                .andExpect(jsonPath("$.email", Matchers.equalToIgnoringCase(employee6.getEmail())))
                                .andExpect(jsonPath("$.department",
                                                Matchers.equalToIgnoringCase(employee6.getDepartment())));
        }

        @Test
        @Order(4)
        public void testPost() throws Exception {

                String content = objectWriter.writeValueAsString(employeepost);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/employees")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);
                mockMvc.perform(mockRequest)
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(7)))
                                .andExpect(jsonPath("$.employeeName", Matchers.equalToIgnoringCase("peter parker")))
                                .andExpect(jsonPath("$.email", Matchers.equalToIgnoringCase("peterparker@example.com")))
                                .andExpect(jsonPath("$.department", Matchers.equalToIgnoringCase("IT")));

        }

        @Test
        @Order(5)
        public void testAfterPost() throws Exception {
                mockMvc.perform(get("/employees/7"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(7)))
                                .andExpect(jsonPath("$.employeeName",
                                                Matchers.equalToIgnoringCase(employeepost.getEmployeeName())))
                                .andExpect(jsonPath("$.email", Matchers.equalToIgnoringCase(employeepost.getEmail())))
                                .andExpect(jsonPath("$.department",
                                                Matchers.equalToIgnoringCase(employeepost.getDepartment())));

        }

        @Test
        @Order(6)
        public void testPutNotfound() throws Exception {
                Employee employee = new Employee(2, "Steve Smith", "stevesmith@example.com", "Human Resources");
                String content = objectWriter.writeValueAsString(employee);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/employees/33")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);
                mockMvc.perform(mockRequest)
                                .andExpect(status().isNotFound());

        }

        @Test
        @Order(7)
        public void testPut() throws Exception {

                String content = objectWriter.writeValueAsString(employeeput);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/employees/2")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);
                mockMvc.perform(mockRequest)
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.employeeName", Matchers.equalToIgnoringCase("Steve Smith")))
                                .andExpect(jsonPath("$.email", Matchers.equalToIgnoringCase("stevesmith@example.com")))
                                .andExpect(jsonPath("$.department", Matchers.equalToIgnoringCase("Human Resources")));

        }

        @Test
        @Order(8)
        public void testAfterPut() throws Exception {

                mockMvc.perform(get("/employees/2"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.employeeName",
                                                Matchers.equalToIgnoringCase(employeeput.getEmployeeName())))
                                .andExpect(jsonPath("$.email", Matchers.equalToIgnoringCase(employeeput.getEmail())))
                                .andExpect(jsonPath("$.department",
                                                Matchers.equalToIgnoringCase(employeeput.getDepartment())));

        }

        @Test
        @Order(9)
        public void testDeleteNotfound() throws Exception {

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/employees/74");
                mockMvc.perform(mockRequest).andExpect(status().isNotFound());

        }

        @Test
        @Order(10)
        public void testDelete() throws Exception {

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/employees/7");

                mockMvc.perform(mockRequest).andExpect(status().isNoContent());

        }

        @Test
        @Order(11)
        public void testAfterDelete() throws Exception {

                mockMvc.perform(get("/employees"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(6)))
                                .andExpect(jsonPath("$[0].employeeId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].employeeName",
                                                Matchers.equalToIgnoringCase(employee1.getEmployeeName())))
                                .andExpect(jsonPath("$[0].email", Matchers.equalToIgnoringCase(employee1.getEmail())))
                                .andExpect(jsonPath("$[0].department",
                                                Matchers.equalToIgnoringCase(employee1.getDepartment())))
                                .andExpect(jsonPath("$[1].employeeId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].employeeName",
                                                Matchers.equalToIgnoringCase(employeeput.getEmployeeName())))
                                .andExpect(jsonPath("$[1].email", Matchers.equalToIgnoringCase(employeeput.getEmail())))
                                .andExpect(jsonPath("$[1].department",
                                                Matchers.equalToIgnoringCase(employeeput.getDepartment())))
                                .andExpect(jsonPath("$[2].employeeId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].employeeName",
                                                Matchers.equalToIgnoringCase(employee3.getEmployeeName())))
                                .andExpect(jsonPath("$[2].email", Matchers.equalToIgnoringCase(employee3.getEmail())))
                                .andExpect(jsonPath("$[2].department",
                                                Matchers.equalToIgnoringCase(employee3.getDepartment())))
                                .andExpect(jsonPath("$[3].employeeId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$[3].employeeName",
                                                Matchers.equalToIgnoringCase(employee4.getEmployeeName())))
                                .andExpect(jsonPath("$[3].email", Matchers.equalToIgnoringCase(employee4.getEmail())))
                                .andExpect(jsonPath("$[3].department",
                                                Matchers.equalToIgnoringCase(employee4.getDepartment())))
                                .andExpect(jsonPath("$[4].employeeId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$[4].employeeName",
                                                Matchers.equalToIgnoringCase(employee5.getEmployeeName())))
                                .andExpect(jsonPath("$[4].email", Matchers.equalToIgnoringCase(employee5.getEmail())))
                                .andExpect(jsonPath("$[4].department",
                                                Matchers.equalToIgnoringCase(employee5.getDepartment())))
                                .andExpect(jsonPath("$[5].employeeId", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$[5].employeeName",
                                                Matchers.equalToIgnoringCase(employee6.getEmployeeName())))
                                .andExpect(jsonPath("$[5].email", Matchers.equalToIgnoringCase(employee6.getEmail())))
                                .andExpect(jsonPath("$[5].department",
                                                Matchers.equalToIgnoringCase(employee6.getDepartment())));

                mockMvc.perform(get("/employees/7")).andExpect(status().isNotFound());

        }

}
