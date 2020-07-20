package org.llamaland.birthday.service;

import org.llamaland.birthday.data.Citizen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class EmailsRemover {
    public List<Citizen> removeCitizensWithDuplicateAndOptedOutEmails(
            List<Citizen> citizens,
            Set<String> optedOutEmails
    ) {
        Set<String> emails = new HashSet<>();
        Set<String> duplicateEmails = citizens.stream()
                .map(Citizen::getEmail)
                .filter(email -> !emails.add(email))
                .collect(toSet());

        return citizens.stream()
                .filter(citizen -> !duplicateEmails.contains(citizen.getEmail()))
                .filter(citizen -> !optedOutEmails.contains(citizen.getEmail()))
                .collect(toList());
    }
}
