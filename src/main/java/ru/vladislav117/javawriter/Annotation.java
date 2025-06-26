package ru.vladislav117.javawriter;

public class Annotation {
    protected final String initializer;

    public Annotation(String annotation) {
        this.initializer = annotation;
    }

    public String getInitializer() {
        return initializer;
    }

    public String build() {
        return initializer;
    }
}
