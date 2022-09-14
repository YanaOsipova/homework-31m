package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Value {
    NAME("Яна"),
    NAME_LATIN("Yana"),
    LNAME("Осипова"),
    LNAME_LATIN("Osipova"),
    BLOG_NAME("Янка блогер"),
    BIRTHDAY("02.02.2002"),
    COMPANY("SIS"),
    JOB_TITLE("qa engineer");

    private final String value;
}
