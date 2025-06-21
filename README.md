# JavaWriter

JavaWriter - java-библиотека для генерации несложного java-кода.

## Добавление зависимости

Для добавления зависимости добавьте следующее в ваш `build.grale`:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Vladislav117:JavaWriter:v0.1.4'
}
```

## Использование

Пример использования JavaWriter для создания класса `SomeClass` с конструктором:

```java
Class c = new Class("SomeClass") {{
    setDocumentation(new ClassDocumentation() {{
        addLine("Первая строка документации SomeClass.");
        addLine("Вторая строка документации SomeClass.");
    }});
    setAccessModifier(AccessModifier.PUBLIC);
    addMethod(new Method("SomeClass") {{
        setDocumentation(new MethodDocumentation() {{
            addLine("Создание объекта.");
            addSeeLink("SomeClass");
        }});
        setConstructor(true);
        addArgument(new Argument("@Nullable Object", "value") {{
            setDescription("Значение объекта");
        }});
        setCode(new StatementBasedCode() {{
            addStatement("set(value)");
        }});
    }});
}};
```
