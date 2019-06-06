package graph;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import serach.algurithms.EscapeByType;
import serach.algurithms.ISearch;

public class Controller {
    Graph graph;

    private static Controller controller = null;

    private Controller() {
        graph = new Graph();
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }

        return controller;
    }
    public Graph getGraph() {
    	return graph;
    }
    
	public boolean search(String from, String to, ISearch algorithm, LinkType type) {

		if (algorithm.getClass().equals(EscapeByType.class)) {
			((EscapeByType) algorithm).setType(type);
		}
		return graph.searchPath(from, to, algorithm);
	}

	public boolean search(String from, String to, ISearch algorithm) {

		return search(from, to, algorithm,null);
	}

    public void readingFile(File file) {
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            br = new BufferedReader(new FileReader(file));
            sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (sb.length() > 0) {
            try {
                makeGraphOfFileString(sb.toString());
            } catch (NumberFormatException e) {
                System.out.println("log");
            }

        }

    }

    private void makeGraphOfFileString(String fileString) {

        ArrayList<String[]> nodeOrLink = new ArrayList<String[]>();
        String[] lines = fileString.split(";");
        for (int i = 0; i < lines.length; i++) {
            nodeOrLink.add(lines[i].split(", "));
        }

        Comparator<String[]> comperator = (e1, e2) -> {
            int difference = Integer.compare(e1.length, e2.length);
            return difference;
        };
        if (!nodeOrLink.isEmpty()) {
        	for (String[] strArr : nodeOrLink) {
	            for (int i = 0; i < strArr.length; i++) {
	                strArr[i] = strArr[i].replaceAll("^\\s+|\\s+$", "");
	                strArr[i] = strArr[i].replaceAll("\r\n" + 
	                		"^\\\\r\\\\n\r\n" + 
	                		"/\r\n" + 
	                		"mg", "");
	
	            }
        	}
        }


        if (!nodeOrLink.isEmpty()) {
            graph.clearGraph();
            for (String[] strArr : nodeOrLink) {

                if (strArr.length == 5
                        && ((strArr[4].toLowerCase().equals("room")
                        ||strArr[4].toLowerCase().equals("transit")))) {
                    graph.addRoom(new Node(strArr[0],
                            (int)Double.parseDouble(strArr[1]),
                            (int)Double.parseDouble(strArr[2]),
                            (int)Double.parseDouble(strArr[3]),
                            NodeType.valueOf(strArr[4].toUpperCase())));
                } else if (strArr.length == 5
                        &&(strArr[2].toLowerCase().equals("walk")
                        ||strArr[2].toLowerCase().equals("climb")
                        ||strArr[2].toLowerCase().equals("lift"))) {
                    if (strArr[4].toLowerCase().equals("yes")) {
                        graph.addRoute(strArr[0],
                                strArr[1],
                                LinkType.valueOf(strArr[2].toUpperCase()),
                                (int)Double.parseDouble(strArr[3]),
                                true);
                    } else if (strArr[4].toLowerCase().equals("no")) {
                        graph.addRoute(strArr[0],
                                strArr[1],
                                LinkType.valueOf(strArr[2].toUpperCase()),
                                (int)Double.parseDouble(strArr[3]),
                                false);
                    }
                }
            }
        }

    }

	public ArrayList<Node> getPathNodes() {
		return graph.getPath();
	}
}
