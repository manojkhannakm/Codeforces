import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class R374C {

    private static InputReader in;
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        if (Arrays.asList(args).contains("-local")) {
            try {
                in = new InputReader(new FileInputStream("in.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            in = new InputReader(System.in);
        }

        new Solution().solve();

        out.close();
    }

    private static class Solution {

        public void solve() {
            int n = in.nextInt(),
                    m = in.nextInt(),
                    t = in.nextInt();

            int[] u = new int[m],
                    v = new int[m],
                    w = new int[m];

            for (int i = 0; i < m; i++) {
                u[i] = in.nextInt();
                v[i] = in.nextInt();
                w[i] = in.nextInt();
            }

            Graph graph = new Graph(n, m, u, v, w);
            graph.dfs(t);
            graph.print();

            ArrayList<Graph.Vertex> list = graph.path();

            out.println(list.size());

            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    out.print(" ");
                }

                out.print(list.get(i).v);
            }
        }

        private class Graph {

            private Vertex startVertex, endVertex;

            public Graph(int n, int m, int[] u, int[] v, int[] w) {
                HashMap<Integer, Vertex> map = new HashMap<>();

                for (int i = 1; i <= n; i++) {
                    map.put(i, new Vertex(i));
                }

                for (int i = 0; i < m; i++) {
                    map.get(u[i]).adjacentEdgeList.add(new Edge(map.get(v[i]), w[i]));
                }

                startVertex = map.get(1);
                endVertex = map.get(n);

                endVertex.pathVertexList = new ArrayList<>();
                endVertex.pathVertexList.add(endVertex);
            }

            private boolean dfs(int t, Vertex vertex, long weight) {
                if (vertex == endVertex) {
                    return true;
                }

                if (vertex.visited) {
                    return vertex.pathVertexList != null;
                }

                vertex.visited = true;

                for (Edge edge : vertex.adjacentEdgeList) {
                    if (dfs(t, edge.vertex, weight + edge.w)) {
                        if (edge.vertex == endVertex) {
                            edge.vertex.weight = edge.w;
                        }

                        if (weight + edge.vertex.weight <= t
                                && (vertex.pathVertexList == null
                                || vertex.pathVertexList.size() - 1 <= edge.vertex.pathVertexList.size())) {
                            vertex.pathVertexList = new ArrayList<>();
                            vertex.pathVertexList.add(vertex);
                            vertex.pathVertexList.addAll(edge.vertex.pathVertexList);

                            if (edge.vertex == endVertex) {
                                edge.vertex.weight = 0;
                            }

                            vertex.weight = edge.w + edge.vertex.weight;
                        }

                        if (edge.vertex == endVertex) {
                            edge.vertex.weight = 0;
                        }
                    }
                }

                return vertex.pathVertexList != null;
            }

            private void print(Vertex vertex, int h) {
                for (Edge edge : vertex.adjacentEdgeList) {
                    for (int i = 0; i < h * 4; i++) {
                        out.print(" ");
                    }

                    out.println(edge.vertex + " (" + edge.w + ") -> " + edge.vertex.pathVertexList + " - " + edge.vertex.weight);

                    print(edge.vertex, h + 1);
                }
            }

            public void dfs(int t) {
                dfs(t, startVertex, 0);
            }

            public ArrayList<Vertex> path() {
                return startVertex.pathVertexList;
            }

            public void print() {
                out.println(startVertex + " -> " + startVertex.pathVertexList + " - " + startVertex.weight);

                print(startVertex, 1);
            }

            private class Vertex {

                private int v;

                private ArrayList<Edge> adjacentEdgeList = new ArrayList<>();
                private boolean visited;
                private ArrayList<Vertex> pathVertexList;
                private long weight;

                public Vertex(int v) {
                    this.v = v;
                }

                @Override
                public String toString() {
                    return String.valueOf(v);
                }

            }

            private class Edge {

                private Vertex vertex;
                private int w;

                public Edge(Vertex vertex, int w) {
                    this.vertex = vertex;
                    this.w = w;
                }

            }

        }

    }

    @SuppressWarnings("UnusedDeclaration")
    private static class InputReader {

        private BufferedReader bufferedReader;
        private StringTokenizer stringTokenizer;

        public InputReader(InputStream inputStream) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }

        public String next() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return stringTokenizer.nextToken();
        }

        public char nextChar() {
            return next().charAt(0);
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            if (stringTokenizer != null && stringTokenizer.hasMoreTokens()) {
                return stringTokenizer.nextToken("");
            }

            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
