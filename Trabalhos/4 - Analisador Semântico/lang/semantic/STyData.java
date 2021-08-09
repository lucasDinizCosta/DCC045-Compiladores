package lang.semantic;

public class STyData extends SType{
    private String name;

    @Override
    public boolean match(SType v) {
        return (v instanceof STyErr) || (v instanceof STyData);
    }
    
    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public STyData(String name) {
        this.name = name;
    }
}
