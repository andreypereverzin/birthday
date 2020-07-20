package org.llamaland.birthday.service;

import org.llamaland.birthday.data.Citizen;
import org.llamaland.birthday.parser.CitizenParser;
import org.llamaland.birthday.parser.ParsingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CitizensReader {
    private final CitizenParser citizenParser = new CitizenParser();

    public List<Citizen> getCitizens(Reader reader) throws IOException {
        List<Citizen> citizens = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    citizens.add(citizenParser.parseLine(line));
                } catch (ParsingException ex) {
                    // just skip invalid line
                }
            }
        }

        return citizens;
    }
}
