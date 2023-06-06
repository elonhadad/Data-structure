package il.ac.telhai.ds.graph;

import il.ac.telhai.ds.misc.Person;
import il.ac.telhai.ds.misc.WeightedFriendship;

import java.util.LinkedList;
import java.util.TreeMap;

public class Graph<V extends Comparable<V>, E> implements IGraph<V, E> {
    private TreeMap<V, LinkedList<Edge>> graph;

    private class Edge {
        private V v1;
        private V v2;
        private E label;

        public Edge(V v1, V v2, E label) {
            this.v1 = v1;
            this.v2 = v2;
            this.label = label;
        }

        public V getV1() {
            return v1;
        }

        public void setV1(V v1) {
            this.v1 = v1;
        }

        public V getV2() {
            return v2;
        }

        public void setV2(V v2) {
            this.v2 = v2;
        }

        public E getLabel() {
            return label;
        }

        public void setLabel(E label) {
            this.label = label;
        }
    }

    public Graph() {
        this.graph = new TreeMap<>();
    }

    public void add(V v) {
        if(graph.get(v) == null) {
            LinkedList<Edge> ll = new LinkedList<>();
            graph.put(v, ll);
        }
    }

    public E getEdge(V u, V v) {
        if(graph.containsKey(u) && graph.containsKey(v)) {
            for(Edge e: graph.get(u)) {
                if(e.getV1() == u && e.getV2() == v) {
                    return e.getLabel();
                }
            }
        }
        return null;
    }

    public E putEdge(V u, V v, E edgeLabel) {
        if(!containsVertex(u)) {
            add(u);
        }
        if(!containsVertex(v)) {
            add(v);
        }
        if(getEdge(u, v) != null) {
            for(Edge edge: graph.get(u)) {
                if(edge.getV1() == u && edge.getV2() == v) {
                    edge.setLabel(edgeLabel);
                    return edgeLabel;
                }
            }
        }
        Edge e = new Edge(u, v, edgeLabel);
        graph.get(u).add(e);
        graph.get(v).add(e);
        return edgeLabel;
    }

    public boolean containsVertex(V v) {
        return graph.containsKey(v);
    }

    public void removeVertex(V v) {
        if(containsVertex(v)) {
            for(V u : graph.keySet()) {
                if(u != v) {
                    removeEdge(u, v);
                }
            }
            graph.remove(v);
        }
    }

    public E removeEdge(V u, V v) {
        if(!graph.containsKey(u) || !graph.containsKey(v)) {
            throw new IllegalArgumentException();
        }
        for(Edge e: graph.get(u)) {
            if(e.getV1() == u && e.getV2() == v) {
                LinkedList<Edge> ll = graph.get(u);
                ll.remove(e);
                break;
            }
        }
        for(Edge e: graph.get(v)) {
            if(e.getV1() == u && e.getV2() == v) {
                LinkedList<Edge> ll = graph.get(v);
                ll.remove(e);
                return e.getLabel();
            }
        }
        return null;
    }

    public double getWeight(V u, V v) {
        if(areAdjacent(u, v)) {
            for(Edge edge: graph.get(u)) {
                if(edge.getV1() == u && edge.getV2() == v || edge.getV1() == v && edge.getV2() == u) {
                    if(edge.getLabel() instanceof WeightedFriendship) {
                        return ((WeightedFriendship) edge.getLabel()).getWeight();
                    }
                    return (double) edge.getLabel();
                }
            }
        }
        return 0;
    }

    public String toStringExtended() {
        StringBuilder str = new StringBuilder();
        for(V v : graph.keySet()) {
            str.append(v).append(":");

            for(Edge e : graph.get(v)) {
                str.append("{");
                str.append(e.getV1()).append(",").append(e.getV2());
                str.append("}");
                if(!(v instanceof Person))
                    str.append("(").append(e.getLabel()).append("),");
            }
            if(!graph.get(v).isEmpty() && !(v instanceof Person)) {
                str.deleteCharAt(str.toString().length()-1);
            }

            str.append("\n");
        }
        if(!str.isEmpty()) {
            str.deleteCharAt(str.toString().length()-1);
        }
        return str.toString();
    }

    public boolean areAdjacent(V u, V v) {
        if(graph.get(u) != null && graph.get(v) != null) {
            for(Edge e: graph.get(u)) {
                if(e.getV1() == u && e.getV2() == v) {
                    return true;
                }
                else if(e.getV1() == v && e.getV2() == u) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(V v : graph.keySet()) {
            str.append(v);
            str.append(",");
        }
        if(!str.isEmpty()) {
            str.deleteCharAt(str.toString().length()-1);
        }
        return str.toString();
    }
}
