package graph;
import java.util.ArrayList;
import java.util.List;

public class Node {
    private String roomName;
    private int x;
    private int y;
    private int floorNumber;
    private NodeType roomType;
	private boolean isExpanded;
	private boolean isTested;
	private Node parent = null;
	private int roomCount;
	private int expense;
	private double distanceToEnd=0;
	private int depth;
	private ArrayList<Link> links = new ArrayList<>();

    public Node() {
    }
    public Node(String roomName, int x, int y, int floorNumber, NodeType roomType) {
        this.roomName = roomName;
        this.x = x;
        this.y = y;
        this.floorNumber = floorNumber;
        this.roomType = roomType;
    }
    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public NodeType getRoomType() {
        return roomType;
    }

    public void setRoomType(NodeType roomType) {
        this.roomType = roomType;
    }
    public void addLink(Link link){
        this.links.add(link);
    }
    public void removeLink(Link link){
        this.links.remove(link);
    }
	public boolean isExpanded() {
		return isExpanded;
	}
	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}
	public boolean isTested() {
		return isTested;
	}
	public void setTested(boolean isTested) {
		this.isTested = isTested;
	}
    public void resetNode() {
    	this.setTested(false);
    	this.setExpanded(false);
    	this.setRoomCount(0);
    	this.setExpense(Integer.MAX_VALUE);
    	distanceToEnd=0;
    	depth=-1;
    }
	public double getDistanceToEnd() {
		return distanceToEnd;
	}
	public void setDistanceToEnd(double distanceToEnd) {
		this.distanceToEnd = distanceToEnd;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(int roomCoun) {
		this.roomCount = roomCoun;
	}
    public int getExpense() {
		return expense;
	}
	public void setExpense(int expense) {
		this.expense = expense;
	}
	public int getDepth() {
		return this.depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}

}
