package jgit.objects;

public enum GitObjectType {
    COMMIT,
    TREE,
    BLOB,
    TAG;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
