package serach.algurithms;

import java.util.ArrayList;

import graph.Graph;
import graph.Link;
import graph.LinkType;
import graph.Node;

public class SearchByLift extends ASearch{

	public SearchByLift(Graph graph) {
		super(graph);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean search(String from, String to) {
		myGraph.resetGraph();
		if (!myMap.containsKey(from) || !myMap.containsKey(to)) {
			return false;
		}
		Node fromNode = myMap.get(from);
		fromNode.setExpense(0);
		ArrayList<Node> queue = new ArrayList<Node>();
		queue.add(fromNode);
		while (!queue.isEmpty()) {
			Node temp = queue.get(0);
			queue.remove(0);
			myGraph.setPathInforamtion(myGraph.getPathInforamtion() + temp.getRoomName() + ",");
			myGraph.setFullInformation(myGraph.getFullInformation() + temp.getRoomName() + " : " + "| Parant : "
					+ (temp.getParent() != null ? temp.getParent().getRoomName() : "Null") + " | Expens : "
					+ temp.getExpense() + " |\n");
			if (temp.getRoomName().equals(to)) {
				myGraph.setPath(parentPrintPath(myMap.get(to), myMap.get(from)));
				return true;
			}
			temp.setTested(true);
			Link climbLink = null;
			boolean haveLiftPath = false;
			int expense = 0;
			for (Link l : temp.getLinks()) {
				expense = temp.getExpense() + l.getExpens();
				if (!l.getToRoom().isTested() && !queue.contains(l.getToRoom())) {
					if(!l.getRoadType().equals(LinkType.CLIMB)) {
						queue.add(l.getToRoom());
						l.getToRoom().setExpense(expense);
						l.getToRoom().setParent(temp);
						if(l.getRoadType().equals(LinkType.LIFT)) {
							haveLiftPath = true;
						}
					}else {
						climbLink = l;
					}

				}
			}
			if(!haveLiftPath && climbLink != null) {
				queue.add(climbLink.getToRoom());
				climbLink.getToRoom().setExpense(expense + climbLink.getExpens());
				climbLink.getToRoom().setParent(temp);
			}
			temp.setExpanded(true);
		}

		return false;
	}

}
