public class ShengxiaoPiece implements Piece {

    private final String[] shengxiao = new String[]{
            "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

    private int index = -1;

    private boolean isNew = true;

    @Override
    public String toString() {
        if (index == -1) {
            return "_";
        }
        if (isNew) {
            isNew = false;
            return "*" + shengxiao[index % shengxiao.length];
        } else {
            return "" + shengxiao[index % shengxiao.length];
        }
    }

    @Override
    public int expand() {
        index++;
        return (int) Math.pow(2, index);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        ShengxiaoPiece b = (ShengxiaoPiece) obj;
        return this.index % shengxiao.length == b.index % shengxiao.length;
    }

    @Override
    public boolean isBlank() {
        return index == -1;
    }

    @Override
    public void setBlank() {
        this.index = -1;
    }

    @Override
    public void init() {
        this.index = 0;
    }

}
