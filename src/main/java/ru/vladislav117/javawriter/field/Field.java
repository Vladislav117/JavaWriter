package ru.vladislav117.javawriter.field;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.javawriter.AccessModifier;
import ru.vladislav117.javawriter.Annotation;

public class Field {
    public static AccessModifier defaultAccessModifier = AccessModifier.NOTHING;
    protected AccessModifier accessModifier;
    protected boolean isStatic = false;
    protected boolean isFinal = false;
    protected @Nullable Annotation annotation = null;
    protected final String type;
    protected final String name;
    protected @Nullable String initializer = null;

    public Field(String type, String name) {
        this.type = type;
        this.name = name;
        accessModifier = defaultAccessModifier;
    }

    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Field setAccessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
        return this;
    }

    public boolean isStatic() {
        return isStatic;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Field setStatic(boolean isStatic) {
        this.isStatic = isStatic;
        return this;
    }

    public boolean isFinal() {
        return isFinal;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Field setFinal(boolean isFinal) {
        this.isFinal = isFinal;
        return this;
    }

    public @Nullable Annotation getAnnotation() {
        return annotation;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Field setAnnotation(@Nullable Annotation annotation) {
        this.annotation = annotation;
        return this;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public @Nullable String getInitializer() {
        return initializer;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Field setInitializer(@Nullable String initializer) {
        this.initializer = initializer;
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append(accessModifier.getPrefix());
        if (isStatic) builder.append("static ");
        if (isFinal) builder.append("final ");
        if (annotation != null) builder.append(annotation.build()).append(" ");
        builder.append(type).append(" ");
        builder.append(name);
        if (initializer == null) {
            builder.append(";");
        } else {
            builder.append(" = ").append(initializer).append(";");
        }
        return builder.toString();
    }
}
