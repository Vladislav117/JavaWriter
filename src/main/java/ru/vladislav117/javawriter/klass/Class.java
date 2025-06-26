package ru.vladislav117.javawriter.klass;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.javawriter.AccessModifier;
import ru.vladislav117.javawriter.Annotation;
import ru.vladislav117.javawriter.field.Field;
import ru.vladislav117.javawriter.method.Method;
import ru.vladislav117.javawriter.utils.IndentUtils;

import java.util.ArrayList;
import java.util.List;

public class Class {
    public static AccessModifier defaultAccessModifier = AccessModifier.NOTHING;
    public static ClassType defaultClassType = ClassType.CLASS;
    protected @Nullable ClassDocumentation documentation = null;
    protected List<Annotation> annotations = new ArrayList<>();
    protected AccessModifier accessModifier;
    protected boolean isStatic = false;
    protected boolean isFinal = false;
    protected final ClassType type;
    protected final String name;
    protected List<Field> fields = new ArrayList<>();
    protected List<Method> methods = new ArrayList<>();
    protected List<Class> subClasses = new ArrayList<>();

    public Class(String name) {
        this.name = name;
        type = defaultClassType;
        accessModifier = defaultAccessModifier;
    }

    public Class(String name, ClassType type) {
        this.name = name;
        this.type = type;
        accessModifier = defaultAccessModifier;
    }

    public @Nullable ClassDocumentation getDocumentation() {
        return documentation;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Class setDocumentation(@Nullable ClassDocumentation documentation) {
        this.documentation = documentation;
        return this;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Class addAnnotation(Annotation annotation) {
        annotations.add(annotation);
        return this;
    }

    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Class setAccessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
        return this;
    }

    public boolean isStatic() {
        return isStatic;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Class setStatic(boolean aStatic) {
        isStatic = aStatic;
        return this;
    }

    public boolean isFinal() {
        return isFinal;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Class setFinal(boolean aFinal) {
        isFinal = aFinal;
        return this;
    }

    public ClassType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<Field> getFields() {
        return fields;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Class addField(Field field) {
        fields.add(field);
        return this;
    }

    public List<Method> getMethods() {
        return methods;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Class addMethod(Method method) {
        methods.add(method);
        return this;
    }

    public List<Class> getSubClasses() {
        return subClasses;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Class addSubClass(Class subClass) {
        subClasses.add(subClass);
        return this;
    }

    public String build(int indentLevel) {
        String indent = IndentUtils.indentByLevel(indentLevel);
        StringBuilder builder = new StringBuilder();
        if (documentation != null) builder.append(documentation.build(indentLevel)).append("\n");
        for (Annotation annotation : annotations) {
            builder.append(indent).append(annotation.build()).append("\n");
        }
        builder.append(indent).append(accessModifier.getPrefix());
        if (isStatic) builder.append("static ");
        if (isFinal) builder.append("final ");
        builder.append(type.getPrefix());
        builder.append(name).append(" {\n");

        String fieldIndent = IndentUtils.indentByLevel(indentLevel + 1);
        for (Field field : fields) {
            builder.append(fieldIndent).append(field.build()).append("\n");
        }

        if (!fields.isEmpty() && (!methods.isEmpty() || !subClasses.isEmpty())) builder.append("\n");

        for (Method method : methods) {
            builder.append(method.build(indentLevel + 1)).append("\n\n");
        }

        if (!methods.isEmpty() && !subClasses.isEmpty()) builder.append("\n");

        for (Class subClass : subClasses) {
            builder.append(subClass.build(indentLevel + 1));
        }

        if (!methods.isEmpty() || !subClasses.isEmpty()) builder.deleteCharAt(builder.length() - 1);

        builder.append("}");
        return builder.toString();
    }
}
