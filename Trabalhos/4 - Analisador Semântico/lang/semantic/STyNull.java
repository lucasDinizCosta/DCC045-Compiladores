package lang.semantic;

public class STyNull extends SType{
    private static STyNull st = new STyNull();

    private STyNull() {
    }

    public static STyNull newSTyNull() {
        return st;
    }

    @Override
    public boolean match(SType v) {
        return (v instanceof STyErr) || (v instanceof STyNull);
    }

    @Override
    public String toString() {
        return "Null";
    }
}
