package ru.vladislav117.javawriter.code;

/**
 * Код.
 */
public abstract class Code {
    /**
     * Сборка кода в строку.
     *
     * @param indentLevel Уровень отступа
     * @return Код как строка.
     */
    public abstract String build(int indentLevel);
}
