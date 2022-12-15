package com.dhashuk.example;

import com.dhashuk.example.dto.PersonBirthdayDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application-test.properties")
@Sql(value = {"/create-person-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-person-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void get_person_data_by_Id_test() throws Exception {

		PersonBirthdayDTO expectedPersonDTO = PersonBirthdayDTO.builder()
				.firstName("Ivan")
				.secondName("Ivanov")
				.age(36)
				.build();

		ObjectMapper objectMapper = new JsonMapper();
		String expectedJsonPerson = objectMapper.writeValueAsString(expectedPersonDTO);

		this.mockMvc.perform(get("/person/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJsonPerson));

	}

	@Test
	void person_not_found_status_test() throws Exception {
		this.mockMvc.perform(get("/person/2"))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

}
