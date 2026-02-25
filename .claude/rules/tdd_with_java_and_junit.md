# TDD with Java and JUnit 6 (Jupiter)

## Test File Creation
1. **Create test class** in `src/test/java/` mirroring the production package structure
2. **Name test classes** with `Test` suffix (e.g., `CalculatorTest`)
3. **Use JUnit 6 annotations** (`@Test`, `@Disabled`, `@DisplayName`)
4. **Follow TDD red-green-refactor** cycle
5. **Leverage Java's type checking** during development

## Running Tests - CRITICAL REQUIREMENTS

**🚨 ALWAYS use Maven goals defined in `pom.xml`**

### ✅ CORRECT - Use Maven goals:
```bash
mvn test                   # Run all tests
mvn test -pl <module>      # Run tests in a specific module
```

### ❌ WRONG - DO NOT use these:
```bash
java -jar junit-platform-standalone.jar   # Don't run JUnit directly
mvn exec:java -Dexec.mainClass=...        # Don't invoke test runner manually
```

### Why This Matters
- **Maven goals provide a consistent interface** for running tests
- **Configuration is managed centrally** in pom.xml (Surefire plugin, dependencies)
- **Consistency across development and CI** environments

### Test Goal Overview
- `mvn test` - Runs full test suite via Maven Surefire plugin

**IMPORTANT**: When TDD agents run tests, they MUST use `mvn test`, never invoke JUnit directly.

## Example Test Template
```java
// src/test/java/com/example/CalculatorTest.java
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    @Disabled("todo")
    void shouldHandleBasicOperations() {}

    @Test
    @Disabled("todo")
    void shouldValidateInputTypes() {}

    @Test
    @Disabled("todo")
    void shouldHandleEdgeCases() {}
}
```

## Assertions
Use **AssertJ** for fluent assertions (preferred over plain JUnit assertions):
```java
assertThat(result).isEqualTo(42);
assertThat(list).containsExactly(1, 2, 3);
assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
```

## pom.xml Dependencies
```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>6.0.3</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.assertj</groupId>
        <artifactId>assertj-core</artifactId>
        <version>3.26.3</version>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.3.1</version>
        </plugin>
    </plugins>
</build>
```
