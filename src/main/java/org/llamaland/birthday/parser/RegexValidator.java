package org.llamaland.birthday.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RegexValidator {
    public boolean isValid(String value) {
        if (value == null) {
            return false;
        }

        Matcher matcher = getPattern().matcher(value.trim());
        return matcher.matches();
    }

    protected abstract Pattern getPattern();
}
