package ru.vladislav117.javawriter.klass;

import ru.vladislav117.javawriter.utils.IndentUtils;

import java.util.ArrayList;
import java.util.List;

public class ClassDocumentation {
    protected List<String> lines = new ArrayList<>();
    protected List<String> seeLinks = new ArrayList<>();

    public List<String> getLines() {
        return lines;
    }

    @SuppressWarnings("UnusedReturnValue")
    public ClassDocumentation addLine(String line) {
        lines.add(line);
        return this;
    }

    public List<String> getSeeLinks() {
        return seeLinks;
    }

    @SuppressWarnings("UnusedReturnValue")
    public ClassDocumentation addSeeLink(String seeLink) {
        seeLinks.add(seeLink);
        return this;
    }

    public String build(int indentLevel) {
        String indent = IndentUtils.indentByLevel(indentLevel);
        StringBuilder builder = new StringBuilder();
        builder.append(indent).append("/**\n");
        for (String line : lines) {
            builder.append(indent).append(" * ").append(line).append("\n");
        }
        if (!lines.isEmpty() && !seeLinks.isEmpty()) {
            builder.append(indent).append(" *\n");
        }
        for (String seeLink : seeLinks) {
            builder.append(indent).append("@see ").append(seeLink).append("\n");
        }
        builder.append(indent).append(" */");
        return builder.toString();
    }
}
