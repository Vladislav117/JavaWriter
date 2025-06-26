package ru.vladislav117.javawriter;

public class Annotation {
    protected final String initializer;

    public Annotation(String initializer) {
        this.initializer = initializer;
    }

    public String getInitializer() {
        return initializer;
    }

    public String build() {
        return initializer;
    }
}
