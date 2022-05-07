package team.bahor.exeptions.search;

import team.bahor.exeptions.ValidationException;

public class BadCredentialsSearch extends ValidationException {
    public BadCredentialsSearch(String message) {
        super(message);
    }

    public BadCredentialsSearch(String message, Throwable cause) {
        super(message, cause);
    }
}
