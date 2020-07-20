package org.llamaland.birthday.parser;

import java.util.regex.Pattern;

public class EmailValidator extends RegexValidator {
    private final static String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    protected Pattern getPattern() {
        return pattern;
    }
}
