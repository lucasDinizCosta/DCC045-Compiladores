package lang.semantic;

public class STyFun extends SType {
    private String id;          // Nome da função
    private SType ty[];
    private SType ret[];

    public STyFun(SType t[], SType retornos[]) {
        ty = t;
        ret = retornos;
    }

    public SType[] getTypes() {
        return ty;
    }

    public SType[] getReturnTypes() {
        return ret;
    }

    public boolean match(SType v) {
        boolean r = false;
        if (v instanceof STyFun) {
            if (((STyFun) v).getTypes().length == ty.length) {
                r = true;
                for (int i = 0; i < ty.length; i++) {
                    r = r && ty[i].match(((STyFun) v).getTypes()[i]);
                }
            }
        }
        return r;
    }

    @Override
    public String toString() {
        String s = "";
        if (ty.length > 0) {
            s = ty[0].toString();
            for (int i = 1; i < ty.length; i++) {
                s += "->" + ty[i].toString();
            }
        }
        return s;
    }
}
