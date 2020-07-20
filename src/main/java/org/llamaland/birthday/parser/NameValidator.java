package org.llamaland.birthday.parser;

import java.util.regex.Pattern;

public class NameValidator extends RegexValidator {
    private final static String EMAIL_PATTERN = "^[a-zA-Z][a-zA-Z .'-]*$";
    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    protected Pattern getPattern() {
        return pattern;
    }
}
