package ru.vladislav117.javawriter.method;

import ru.vladislav117.javawriter.utils.IndentUtils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class MethodDocumentation {
    protected Method method;
    protected List<String> lines = new ArrayList<>();
    protected List<String> seeLinks = new ArrayList<>();
    protected List<AbstractMap.SimpleEntry<String, String>> throwsDescriptions = new ArrayList<>();

    public void assertMethod(Method method) {
        this.method = method;
    }

    public List<String> getLines() {
        return lines;
    }

    @SuppressWarnings("UnusedReturnValue")
    public MethodDocumentation addLine(String line) {
        lines.add(line);
        return this;
    }

    public List<String> getSeeLinks() {
        return seeLinks;
    }

    @SuppressWarnings("UnusedReturnValue")
    public MethodDocumentation addSeeLink(String seeLink) {
        seeLinks.add(seeLink);
        return this;
    }

    public List<AbstractMap.SimpleEntry<String, String>> getThrowsDescriptions() {
        return throwsDescriptions;
    }

    @SuppressWarnings("UnusedReturnValue")
    public MethodDocumentation addThrowDescription(String throwable, String description) {
        throwsDescriptions.add(new AbstractMap.SimpleEntry<>(throwable, description));
        return this;
    }

    public String build(int indentLevel) {
        String indent = IndentUtils.indentByLevel(indentLevel);
        StringBuilder builder = new StringBuilder();
        builder.append(indent).append("/**\n");
        for (String line : lines) {
            builder.append(indent).append(" * ").append(line).append("\n");
        }
        builder.append(indent).append(" *\n");
        for (Argument argument : method.getArguments()) {
            if (argument.getDescription() == null) continue;
            builder.append(indent).append(" * @param ").append(argument.getName()).append(" ").append(argument.getDescription()).append("\n");
        }
        if (method.getReturn() != null && method.getReturn().getDescription() != null) {
            builder.append(indent).append(" * @return ").append(method.getReturn().getDescription()).append("\n");
        }
        for (AbstractMap.SimpleEntry<String, String> throwDescription : throwsDescriptions) {
            builder.append(indent).append(" * @throws ").append(throwDescription.getKey()).append(" ").append(throwDescription.getValue()).append("\n");
        }
        for (String seeLink : seeLinks) {
            builder.append(indent).append(" * @see ").append(seeLink).append("\n");
        }
        builder.append(indent).append(" */");
        return builder.toString();
    }
}
