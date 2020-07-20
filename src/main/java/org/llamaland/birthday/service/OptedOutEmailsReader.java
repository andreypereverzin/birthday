package org.llamaland.birthday.service;

import org.llamaland.birthday.parser.EmailValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

public class OptedOutEmailsReader {
    private final EmailValidator emailValidator = new EmailValidator();

    public Set<String> getOptedOutEmails(Reader reader) throws IOException {
        Set<String> emails = new HashSet<>();

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                // just skip invalid line
                if (emailValidator.isValid(line))
                    emails.add(line);
            }
        }
        return emails;
    }
}
