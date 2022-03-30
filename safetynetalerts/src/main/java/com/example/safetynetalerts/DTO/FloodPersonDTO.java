package com.example.safetynetalerts.DTO;

import java.util.List;

public class FloodPersonDTO {

        private String lastName;
        private Integer age;
        private String phone;

        private List<String> medications;
        private List<String> allergies;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Integer getBirthdate() {
            return age;
        }

        public void setBirthdate(Integer age) {
            this.age = age;
        }

        public List<String> getMedications() {
            return medications;
        }

        public void setMedications(List<String> medications) {
            this.medications = medications;
        }

        public List<String> getAllergies() {
            return allergies;
        }

        public void setAllergies(List<String> allergies) {
            this.allergies = allergies;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
}

