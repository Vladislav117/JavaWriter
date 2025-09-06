package ru.vladislav117.javawriter.code;

import ru.vladislav117.javawriter.utils.IndentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Код, основанный на выражениях.
 */
public class StatementBasedCode extends Code {
    protected List<AbstractStatement> statements = new ArrayList<>();

    public List<AbstractStatement> getStatements() {
        return statements;
    }

    @SuppressWarnings("UnusedReturnValue")
    public StatementBasedCode addStatement(AbstractStatement statement) {
        statements.add(statement);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public StatementBasedCode addStatement(String statement) {
        statements.add(new Statement(statement));
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public StatementBasedCode addIf(String condition, Code code) {
        statements.add(new IfStatement(condition, code));
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public StatementBasedCode addSimpleIf(String condition, String statement) {
        statements.add(new SimpleIfStatement(condition, statement));
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public StatementBasedCode addIfElse(String condition, Code code, Code elseCode) {
        statements.add(new IfElseStatement(condition, code, elseCode));
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public StatementBasedCode addFor(String statements, Code code) {
        this.statements.add(new ForStatement(statements, code));
        return this;
    }

    @Override
    public String build(int indentLevel) {
        StringBuilder builder = new StringBuilder();
        for (AbstractStatement statement : statements) {
            builder.append(statement.build(indentLevel)).append("\n");
        }
        if (!statements.isEmpty()) builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static abstract class AbstractStatement {
        public abstract String build(int indentLevel);
    }

    public static class Statement extends AbstractStatement {
        protected final String statement;

        public Statement(String statement) {
            if (!statement.endsWith(";")) statement += ";";
            this.statement = statement;
        }

        public String getStatement() {
            return statement;
        }

        @Override
        public String build(int indentLevel) {
            String indent = IndentUtils.indentByLevel(indentLevel);
            return indent + statement;
        }
    }

    public static class IfStatement extends AbstractStatement {
        protected final String condition;
        protected final Code code;

        public IfStatement(String condition, Code code) {
            this.condition = condition;
            this.code = code;
        }

        public String getCondition() {
            return condition;
        }

        public Code getCode() {
            return code;
        }

        @Override
        public String build(int indentLevel) {
            String indent = IndentUtils.indentByLevel(indentLevel);
            return indent + "if (" + condition + ") {\n" + code.build(indentLevel + 1) + "\n" + indent + "}";
        }
    }

    public static class SimpleIfStatement extends AbstractStatement {
        protected final String condition;
        protected final String statement;

        public SimpleIfStatement(String condition, String statement) {
            this.condition = condition;
            if (!statement.endsWith(";")) statement += ";";
            this.statement = statement;
        }

        public String getCondition() {
            return condition;
        }

        public String getStatement() {
            return statement;
        }

        @Override
        public String build(int indentLevel) {
            String indent = IndentUtils.indentByLevel(indentLevel);
            return indent + "if (" + condition + ") " + statement;
        }
    }

    public static class IfElseStatement extends AbstractStatement {
        protected final String condition;
        protected final Code code;
        protected final Code elseCode;

        public IfElseStatement(String condition, Code code, Code elseCode) {
            this.condition = condition;
            this.code = code;
            this.elseCode = elseCode;
        }

        public String getCondition() {
            return condition;
        }

        public Code getCode() {
            return code;
        }

        public Code getElseCode() {
            return elseCode;
        }

        @Override
        public String build(int indentLevel) {
            String indent = IndentUtils.indentByLevel(indentLevel);
            return indent + "if (" + condition + ") {\n" + code.build(indentLevel + 1) + "\n" + indent + "} else {\n" + elseCode.build(indentLevel + 1) + "\n" + indent + "}";
        }
    }

    public static class ForStatement extends AbstractStatement {
        protected final String statements;
        protected final Code code;

        public ForStatement(String statements, Code code) {
            this.statements = statements;
            this.code = code;
        }

        public String getStatements() {
            return statements;
        }

        public Code getCode() {
            return code;
        }

        @Override
        public String build(int indentLevel) {
            String indent = IndentUtils.indentByLevel(indentLevel);
            return indent + "for (" + statements + ") {\n" + code.build(indentLevel + 1) + "\n" + indent + "}";
        }
    }
}
