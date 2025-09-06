package ru.vladislav117.javawriter.method;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.javawriter.Annotation;

public class Return {
    protected @Nullable Annotation annotation = null;
    protected final String type;
    protected @Nullable String description = null;

    public Return(String type) {
        this.type = type;
    }

    public @Nullable Annotation getAnnotation() {
        return annotation;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Return setAnnotation(@Nullable Annotation annotation) {
        this.annotation = annotation;
        return this;
    }

    public String getType() {
        return type;
    }

    public @Nullable String getDescription() {
        return description;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Return setDescription(@Nullable String description) {
        this.description = description;
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        if (annotation != null) builder.append(annotation.build()).append(" ");
        builder.append(type);
        return builder.toString();
    }
}
