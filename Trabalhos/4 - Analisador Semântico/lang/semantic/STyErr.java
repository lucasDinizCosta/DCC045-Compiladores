package lang.semantic;

public class STyErr extends SType {

    private static STyErr st = new STyErr();

    private STyErr() {
    }

    public static STyErr newSTyErr() {
        return st;
    }

    @Override
    public boolean match(SType v) {
        return true;
    }

    @Override
    public String toString() {
        return "TyError";
    }

}
