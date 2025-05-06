package ru.vladislav117.javawriter.method;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.javawriter.AccessModifier;
import ru.vladislav117.javawriter.Annotation;
import ru.vladislav117.javawriter.klass.ClassDocumentation;
import ru.vladislav117.javawriter.code.Code;
import ru.vladislav117.javawriter.utils.IndentUtils;

import java.util.ArrayList;
import java.util.List;

public class Method {
    public static AccessModifier defaultAccessModifier = AccessModifier.NOTHING;
    protected @Nullable MethodDocumentation documentation = null;
    protected List<Annotation> annotations = new ArrayList<>();
    protected AccessModifier accessModifier;
    protected boolean isStatic = false;
    protected boolean isFinal = false;
    protected boolean isAbstract = false;
    protected boolean isDefault = false;
    protected @Nullable Return aReturn = null;
    protected final String name;
    protected List<Argument> arguments = new ArrayList<>();
    protected @Nullable Code code = null;
    protected boolean isConstructor = false;

    public Method(String name) {
        this.name = name;
        accessModifier = defaultAccessModifier;
    }

    public @Nullable MethodDocumentation getDocumentation() {
        return documentation;
    }

    public Method setDocumentation(@Nullable MethodDocumentation documentation) {
        this.documentation = documentation;
        if (this.documentation != null) this.documentation.assertMethod(this);
        return this;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public Method addAnnotation(Annotation annotation) {
        annotations.add(annotation);
        return this;
    }

    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    public Method setAccessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
        return this;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public Method setStatic(boolean aStatic) {
        isStatic = aStatic;
        return this;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public Method setFinal(boolean aFinal) {
        isFinal = aFinal;
        return this;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public Method setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
        return this;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public Method setDefault(boolean aDefault) {
        isDefault = aDefault;
        return this;
    }

    public @Nullable Return getReturn() {
        return aReturn;
    }

    public Method setReturn(@Nullable Return aReturn) {
        this.aReturn = aReturn;
        return this;
    }

    public String getName() {
        return name;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public Method addArgument(Argument argument) {
        arguments.add(argument);
        return this;
    }

    public @Nullable Code getCode() {
        return code;
    }

    public Method setCode(@Nullable Code code) {
        this.code = code;
        return this;
    }

    public boolean isConstructor() {
        return isConstructor;
    }

    public Method setConstructor(boolean constructor) {
        isConstructor = constructor;
        return this;
    }

    public String build(int indentLevel) {
        String indent = IndentUtils.indentByLevel(indentLevel);
        StringBuilder builder = new StringBuilder();
        if (documentation != null) {
            builder.append(documentation.build(indentLevel)).append("\n");
        }
        for (Annotation annotation : annotations) {
            builder.append(indent).append(annotation.build()).append("\n");
        }
        builder.append(indent).append(accessModifier.getPrefix());
        if (!isConstructor) {
            if (isStatic) builder.append("static ");
            if (isFinal) builder.append("final ");
            if (isAbstract) builder.append("abstract ");
            if (isDefault) builder.append("default ");
            if (aReturn == null) {
                builder.append("void ");
            } else {
                builder.append(aReturn.build()).append(" ");
            }
        }
        builder.append(name).append("(");
        for (Argument argument : arguments) {
            builder.append(argument.build()).append(", ");
        }
        if (!arguments.isEmpty()) builder.deleteCharAt(builder.length() - 1).deleteCharAt(builder.length() - 1);
        builder.append(")");
        if (code == null) {
            builder.append(";");
        } else {
            builder.append(" {\n");
            builder.append(code.build(indentLevel + 1)).append("\n");
            builder.append(indent).append("}");
        }
        return builder.toString();
    }
}
