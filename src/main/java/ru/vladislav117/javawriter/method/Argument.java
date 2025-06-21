package ru.vladislav117.javawriter.method;

import org.jetbrains.annotations.Nullable;

public class Argument {
    protected final String type;
    protected final String name;
    protected @Nullable String description = null;

    public Argument(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public @Nullable String getDescription() {
        return description;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Argument setDescription(@Nullable String description) {
        this.description = description;
        return this;
    }

    public String build() {
        return type + " " + name;
    }
}
