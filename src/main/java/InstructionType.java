import java.util.stream.Stream;

public enum InstructionType {
    ADD,
    SUBTRACT,
    DIVIDE,
    MULTIPLY,
    APPLY;

    public static Stream<InstructionType> stream() {
        return Stream.of(InstructionType.values());
    }
}
