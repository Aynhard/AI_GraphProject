package graph;
public class Link {
    private Node toRoom;
    private LinkType roadType;
    private int expens;
    private boolean isTowWay;

    public Link() {
    }

    public Link(Node toRoom, LinkType roadType, int expens, boolean isTowWay) {
        this.toRoom = toRoom;
        this.roadType = roadType;
        this.expens = expens;
        this.isTowWay = isTowWay;
    }

    public Node getToRoom() {
        return toRoom;
    }

    public void setToRoom(Node toRoom) {
        this.toRoom = toRoom;
    }

    public LinkType getRoadType() {
        return roadType;
    }

    public void setRoadType(LinkType roadType) {
        this.roadType = roadType;
    }

    public int getExpens() {
        return expens;
    }

    public void setExpens(int expens) {
        this.expens = expens;
    }

    public boolean isTowWay() {
        return isTowWay;
    }

    public void setTowWay(boolean towWay) {
        isTowWay = towWay;
    }
}
