package acmicpcneerc16;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class H {

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
                    m = in.nextInt();

            char[][] s = new char[n][];

            for (int i = 0; i < n; i++) {
                s[i] = in.nextLine().toCharArray();
            }

            int[] a = new int[m];

            for (int i = 0; i < m; i++) {
                a[i] = in.nextInt() - 1;
            }

            for (int i = 1; i < m; i++) {
                if (s[a[i]].length != s[a[0]].length) {
                    out.println("No");
                    return;
                }
            }

            int l = s[a[0]].length;
            char[] p = new char[l];

            for (int i = 0; i < l; i++) {
                p[i] = s[a[0]][i];
            }

            for (int i = 0; i < l; i++) {
                for (int j = 1; j < m; j++) {
                    if (s[a[j]][i] != p[i]) {
                        p[i] = '?';
                        break;
                    }
                }
            }

            ArrayList<Integer> list = new ArrayList<>();

            for (int ai : a) {
                list.add(ai);
            }

            for (int i = 0; i < n; i++) {
                if (!list.contains(i) &&
                        s[i].length == p.length) {
                    boolean f = true;

                    for (int j = 0; j < l; j++) {
                        if (p[j] != '?' && s[i][j] != p[j]) {
                            f = false;
                            break;
                        }
                    }

                    if (f) {
                        out.println("No");
                        return;
                    }
                }
            }

            out.println("Yes");
            out.println(p);
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
