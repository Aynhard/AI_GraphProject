package serach.algurithms;

import java.util.ArrayList;
import java.util.Comparator;

import graph.Graph;
import graph.Link;
import graph.Node;

public class ShortestPath extends ASearch {

	public ShortestPath(Graph graph) {
		super(graph);

	}

	@Override
	public boolean search(String from, String to) {
		myGraph.resetGraph();
		if (!myMap.containsKey(from) || !myMap.containsKey(to)) {
			return false;
		}

		Node fromNode = myMap.get(from);
		fromNode.setDepth(0);
		calculateDirectLine(myMap.get(to));
		ArrayList<Node> queue = new ArrayList<Node>();
		queue.add(fromNode);

		while (!queue.isEmpty()) {
			Node temp = queue.get(0);
			queue.remove(0);
			setDepth(temp);
			myGraph.setPathInforamtion(myGraph.getPathInforamtion() + temp.getRoomName() + ",");
			myGraph.setFullInformation(myGraph.getFullInformation() + temp.getRoomName() + " : " + "| Parant : "
					+ (temp.getParent() != null ? temp.getParent().getRoomName() : "Null") + " | Expens : "
					+ temp.getExpense() + " | Depth : " + temp.getDepth() + " | \n");
			if (temp.getRoomName().equals(to)) {
				myGraph.setPath(printPath(temp));
				return true;
			}
			temp.setTested(true);

			ArrayList<Link> links = (ArrayList<Link>) temp.getLinks().clone();

			Comparator<Link> comperator = (l2,l1) -> {
				int difference = Double.compare(l1.getExpens(), l2.getExpens());
				if (difference == 0) {

					difference = Double.compare(l1.getToRoom().getDistanceToEnd(), l2.getToRoom().getDistanceToEnd());
				}

				return difference;
			};
			links.sort(comperator);
			for (Link l : links) {
				if (!l.getToRoom().isTested()) {
					queue.remove(l.getToRoom());
					queue.add(0,l.getToRoom());

				}
			}

			temp.setExpanded(true);
		}

		return false;
	}

	private void calculateDirectLine(Node end) {
		for (Node node : myMap.values()) {
			double directLine = Math
					.sqrt(Math.pow(end.getX() - node.getX(), 2) + Math.pow(end.getY() - node.getY(), 2));
			node.setDistanceToEnd(directLine);
		}
	}
	

}
