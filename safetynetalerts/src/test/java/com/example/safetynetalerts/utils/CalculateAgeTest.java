package com.example.safetynetalerts.utils;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = {CalculateAge.class})
@ExtendWith(SpringExtension.class)

@DisplayName("Calculate age and its errors")
class CalculateAgeTest {
    @Autowired
    private CalculateAge calculateAge;

    @Test
    @DisplayName("Given birthdate 03/06/1993, when calculateAge(), then return 28 years old")
    void calculateAgeTest() {
        // Given
        String birthdate = "03/06/1994";

        // When
        Integer age = calculateAge.calculateAge(birthdate);

        // Then
        assertEquals(28, age);
    }

    @Test
    @DisplayName("Given birthdate 03/06/2023, when calculateAge(), then throws DateTimeException")
    void calculateAgeInvalidBirthdateTest() {
        // Given
        String birthdate = "03/06/2023";

        // Then
        assertThrows(DateTimeException.class, () -> calculateAge.calculateAge(birthdate));
    }

    @Test
    @DisplayName("Given birthdate 033/06/1994, when calculateAge(), then throws DateTimeParseException")
    void calculateAgeInvalidFormatBirthdateTest() {
        // Given
        String birthdate = "038/06/1994";

        // Then
        assertThrows(DateTimeParseException.class, () -> calculateAge.calculateAge(birthdate));
    }

    @Test
    @DisplayName("Given birthdate is null, when calculateAge(), then throws NullPointerException")
    void calculateAgeNullBirthdateTest() {
        // Given
        String birthdate = null;

        // Then
        assertThrows(NullPointerException.class, () -> calculateAge.calculateAge(birthdate));
    }
}

