package jgit.object;

public class UnknownObjectTypeException extends Exception {
    public UnknownObjectTypeException(String typeName) {
        super("Uncknown object type: " + typeName);
    }
}
