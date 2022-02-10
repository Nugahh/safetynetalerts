package com.example.safetynetalerts.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;


/*@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonRepositoryTest {

    private PersonRepository personRepository;
    private ArrayList<Person> personList;
    private JSONReaderRepository jsonReaderRepository;

    @BeforeEach
    void setUp() throws IOException {
        personRepository = new PersonRepository(jsonReaderRepository);
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        personList = new ArrayList<>();
        personList.add(person);
        when(mockDataBaseDAO.getPersonList()).thenReturn(personList);
    }

    @AfterEach
    void cleanUp() {
        personList.clear();
    }

    @Test
    void getPersonsShouldCallGetPersonList() {
        personRepository.getAllPersons();
        verify(mockDataBaseDAO).getPersonList();
    }

    @Test
    void savePersonShouldReturnTrue() {
        Person person = new Person();
        assertTrue(personRepository.savePerson(person));
    }
    @Test
    void getPersonByIdShouldReturnAnOptionalNotNull() {
        assertNotNull(personRepository.getPerson(personList.get(0).getIdPerson()));

    }

    @Test
    void getPersonByFirstNameAndLastNameShouldReturnOptionalNotNull() {
        assertNotNull(personRepository.getPerson("Lo", "Frazier"));
    }

    @Test
    void deletePersonShouldReturnTrueIfPersonDeleted() {
        assertTrue(personRepository.deletePerson(personList.get(0)));
    }

    @Test
    void getPersonsTest(){

    }
}*/
