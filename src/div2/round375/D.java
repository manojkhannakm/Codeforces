package div2.round375;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class D {

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
                    k = in.nextInt();

            char[][] s = new char[n][];

            for (int i = 0; i < n; i++) {
                s[i] = in.nextLine().toCharArray();
            }

            Map map = new Map(n, m, s);

            out.println(map.fill(k));

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    out.print(s[i][j]);
                }

                out.println("");
            }
        }

        private class Map {

            private int n, m;
            private char[][] s;

            private boolean[][] v;

            public Map(int n, int m, char[][] s) {
                this.n = n;
                this.m = m;
                this.s = s;

                v = new boolean[n][m];
            }

            private boolean dfs(int x, int y, ArrayList<Point> list) {
                if (s[x][y] == '*' || v[x][y]) {
                    return true;
                }

                if (x == 0 || x == n - 1
                        || y == 0 || y == m - 1) {
                    return false;
                }

                v[x][y] = true;

                list.add(new Point(x, y));

                return (dfs(x - 1, y, list) ? 1 : 0)
                        + (dfs(x + 1, y, list) ? 1 : 0)
                        + (dfs(x, y - 1, list) ? 1 : 0)
                        + (dfs(x, y + 1, list) ? 1 : 0) == 4;
            }

            public int fill(int k) {
                ArrayList<ArrayList<Point>> listList = new ArrayList<>();

                for (int i = 1; i < n - 1; i++) {
                    for (int j = 1; j < m - 1; j++) {
                        ArrayList<Point> list = new ArrayList<>();

                        if (dfs(i, j, list)
                                && !list.isEmpty()) {
                            listList.add(list);
                        }
                    }
                }

                listList.sort((o1, o2) -> Integer.compare(o1.size(), o2.size()));

                int c = 0;

                for (int i = 0; i < listList.size() - k; i++) {
                    ArrayList<Point> list = listList.get(i);

                    for (Point point : list) {
                        s[point.x][point.y] = '*';
                    }

                    c += list.size();
                }

                return c;
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
