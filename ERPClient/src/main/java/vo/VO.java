package main.java.vo;

public class VO{

    protected String ID; //编号

    protected boolean visible = true; //是否可见

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
