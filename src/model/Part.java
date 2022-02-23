package model;

public class Part {
    private int start;
    private int num;
    private TopologyType type;

    public Part( TopologyType type,int start, int num ) {
        this.start = start;
        this.num = num;
        this.type = type;
    }

    public int getStart() {
        return start;
    }

    public int getNum() {
        return num;
    }

    public TopologyType getType() {
        return type;
    }
}
