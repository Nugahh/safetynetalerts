package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.repository.PersonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.FileInputStream;
import java.io.IOException;

import static com.google.inject.matcher.Matchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class JSONReaderServiceTest {

    @Value("${dataSourceJson}")
    private String filePath;

    @Mock
    private ObjectMapper objectMapper;

    @Autowired
    private JSONReaderService jsonReaderService;

 /*   @Test
    void loadPersonsTest() {

        String jsonString = "{ \"id\" : \n" +
                "      {\n" +
                "         \"firstName\": \"something\",\n" +
                "         \"lastName\" : \"something\"\n" +
                "      }\n" +
                "}";
    }
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(jsonString);
    }*/


 /*   @AfterEach
    void cleanUp() {
        personList.clear();
    }

    @Test
    void loadPersonsTest() throws IOException {
        JsonNode node = objectMapper.readTree(new FileInputStream(filePath));
        assertNotNull(jsonReaderService.loadPersons(node));
    }

    @Test
    void loadFireStationsTest(){
        assertNotNull(jsonReaderService.loadPersons())
    }

    @Test
    void loadMedicalTest(){
        assertNotNull(jsonReaderService.loadPersons())
    }*/
}
