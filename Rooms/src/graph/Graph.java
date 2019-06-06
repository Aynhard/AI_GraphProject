package graph;
import java.util.ArrayList;
import java.util.HashMap;

import serach.algurithms.ISearch;

public class Graph {
    private HashMap<String,Node> graph = new HashMap<>();
	private String pathInforamtion;
	private ArrayList<Node> path = new ArrayList<Node>();
	private String fullInformation;

    public void addRoom(Node room){
        if(room == null || graph.containsKey(room.getRoomName())){
            System.err.println("Note already exist!!!");
        }
        graph.put(room.getRoomName(),room);
    }
    public void addRoute(String from,String to, LinkType type ,int expens , boolean isTowWay){
        Link route = new Link(graph.get(to),type,expens,isTowWay);
        if(isTowWay){
            Link secondRoute = new Link(graph.get(from),type,expens,isTowWay);
            graph.get(from).addLink(route);
            graph.get(to).addLink(secondRoute);
        }else {
        	graph.get(from).addLink(route);
        }
    }
	public boolean searchPath(String from, String to, ISearch algorithm) {

		return algorithm.search(from, to);
	}
    public Node getRoom(String roomName){
        return graph.get(roomName);
    }
    public void clearGraph(){
        graph.clear();
    }
    public HashMap<String,Node> getMap(){
    	return graph;
    }
    public void resetGraph() {
    	graph.values().forEach(node->node.resetNode());
		pathInforamtion = "";
		path.clear();
		setFullInformation("");
    }
	public String getPathInforamtion() {
		return pathInforamtion;
	}

	public void setPathInforamtion(String pathInforamtion) {
		this.pathInforamtion = pathInforamtion;
	}

	public ArrayList<Node> getPath() {
		return path;
	}

	public void setPath(ArrayList<Node> path) {
		this.path = path;
	}

	public String getFullInformation() {
		return fullInformation;
	}

	public void setFullInformation(String fullInformation) {
		this.fullInformation = fullInformation;
	}
}
