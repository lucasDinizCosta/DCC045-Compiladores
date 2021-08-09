package lang.semantic;

public class STyArr extends SType{
    private SType a;

    public STyArr(SType t) {
        a = t;
    }

    public SType getArg() {
        return a;
    }

    @Override
    public boolean match(SType v) {
        // Verifica se é array e o tipo do array
        return (v instanceof STyErr) || (v instanceof STyArr) && (a.match(((STyArr) v).getArg()));
    }

    @Override
    public String toString() {
        return a.toString() + "[]";
    }
}
