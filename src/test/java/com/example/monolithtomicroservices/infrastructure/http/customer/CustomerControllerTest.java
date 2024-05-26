package com.example.monolithtomicroservices.infrastructure.http.customer;

import com.example.monolithtomicroservices.application.customer.FakeRegisterCustomerUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@Import({
        FakeRegisterCustomerUseCase.class
})
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FakeRegisterCustomerUseCase fakeRegisterCustomerUseCase;

    @Test
    void shouldRegisterCustomer() throws Exception {
        mockMvc.perform(post("/customers/register")
                        .contentType("application/json")
                        .content("""
                                {
                                    "firstName": "John",
                                    "lastName": "Doe",
                                    "email": "john.doe@test.com",
                                    "address": {
                                        "street": "Main Street",
                                        "houseNumber": "1",
                                        "city": "Springfield",
                                        "zipCode": "12345",
                                        "country": "USA"
                                    }
                                }
                                """))
                .andExpect(status().isOk());
        assertThat(fakeRegisterCustomerUseCase.getCustomer()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidRegisterCustomerValues")
    void shouldReturnBadRequestWhenCustomerRequestBodyIsInvalid(String firstName, String lastName, String email, String street,
            String houseNumber, String city, String zipCode, String country) throws Exception {
        final var json = """
                {
                    "firstName": "%s",
                    "lastName": "%s",
                    "email": "%s",
                    "address": {
                        "street": "%s",
                        "houseNumber": "%s",
                        "city": "%s",
                        "zipCode": "%s",
                        "country": "%s"
                    }
                }
                """.formatted(firstName, lastName, email, street, houseNumber, city, zipCode, country);

        mockMvc.perform(post("/customers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
        assertThat(fakeRegisterCustomerUseCase.getCustomer()).isNull();
    }

    private static Stream<Arguments> provideInvalidRegisterCustomerValues() {
        return Stream.of(
                Arguments.of(
                        "", // Invalid firstName (empty)
                        "", // Invalid lastName (empty)
                        null, // Invalid email (null)
                        "", // Invalid street (empty)
                        "", // Invalid houseNumber (empty)
                        "", // Invalid zipCode (empty)
                        "", // Invalid city (empty)
                        "" // Invalid country (empty)
                ),
                Arguments.of(
                        null, // Invalid firstName (null)
                        null, // Invalid lastName (null)
                        null, // Invalid email (null)
                        null, // Invalid street (null)
                        null, // Invalid houseNumber (null)
                        null, // Invalid zipCode (null)
                        null, // Invalid city (null)
                        null // Invalid country (null)
                ),
                Arguments.of(
                        "J", // Invalid firstName (too short)
                        "D", // Invalid lastName (too short)
                        "invalidemail.com", // Invalid email (invalid format)
                        "St", // Invalid street (too short)
                        "H".repeat(31), // Invalid houseNumber (too long)
                        "Z".repeat(11), // Invalid zipCode (too long)
                        "C".repeat(2), // Invalid city (too short)
                        "C".repeat(2) // Invalid country (too short)
                ),
                Arguments.of(
                        "J".repeat(31), // Invalid firstName (too long)
                        "D".repeat(31), // Invalid lastName (too long)
                        "user@.com", // Invalid email (invalid format)
                        "S".repeat(31), // Invalid street (too long)
                        "1", // Valid houseNumber
                        "1000", // Valid zipCode
                        "Brussels", // Valid city
                        "C".repeat(31) // Invalid country (too long)
                )
        );
    }
}