package serach.algurithms;

import java.util.ArrayList;

import graph.Graph;
import graph.Link;
import graph.LinkType;
import graph.Node;

public class EscapeByType extends ASearch {
	LinkType type;
	

	public LinkType getType() {
		return type;
	}

	public void setType(LinkType type) {
		this.type = type;
	}

	public EscapeByType(Graph graph) {
		super(graph);
	}

	@Override
	public boolean search(String from, String to) {
		myGraph.resetGraph();
		if (!myMap.containsKey(from) || !myMap.containsKey(to)) {
			return false;
		}
		Node fromNode = myMap.get(from);
		ArrayList<Node> queue = new ArrayList<Node>();
		queue.add(fromNode);
		while (!queue.isEmpty()) {
			Node temp = queue.get(0);
			queue.remove(0);
			myGraph.setPathInforamtion(myGraph.getPathInforamtion() + temp.getRoomName() + ",");
			myGraph.setFullInformation(myGraph.getFullInformation() + temp.getRoomName() + " : " + "| Parant : "
					+ (temp.getParent() != null ? temp.getParent().getRoomName() : "Null") + " | Expens : "
					+ temp.getExpense() + " | \n");

			if (temp.getRoomName().equals(to)) {
				myGraph.setPath(parentPrintPath(myMap.get(to), myMap.get(from)));
				return true;
			}
			temp.setTested(true);
			for (Link l : temp.getLinks()) {
				if (!l.getToRoom().isTested() && !queue.contains(l.getToRoom()) && !l.getRoadType().equals(type)) {
					queue.add(l.getToRoom());
					l.getToRoom().setParent(temp);
				}
			}
			temp.setExpanded(true);
		}

		return false;
	}

}
