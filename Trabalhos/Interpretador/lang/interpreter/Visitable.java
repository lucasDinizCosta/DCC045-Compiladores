package lang.interpreter;

public interface Visitable {
    public void accept (Visitor v);
}