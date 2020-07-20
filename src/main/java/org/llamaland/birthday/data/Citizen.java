package org.llamaland.birthday.data;

import java.time.LocalDate;

public class Citizen {
    private final String lastName;
    private final String firstName;
    private final LocalDate birthDate;
    private final String email;

    public Citizen(String lastName, String firstName, LocalDate birthDate, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }
}
