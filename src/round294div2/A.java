package round294div2;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class A {

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
            HashMap<Character, Integer> weightMap = new HashMap<>();
            weightMap.put('q', 9);
            weightMap.put('r', 5);
            weightMap.put('b', 3);
            weightMap.put('n', 3);
            weightMap.put('p', 1);

            int white = 0, black = 0;
            for (int i = 0; i < 8; i++) {
                String line = in.nextLine();

                for (int j = 0; j < 8; j++) {
                    char c = line.charAt(j);

                    Integer weight = weightMap.get(Character.toLowerCase(c));
                    if (weight == null) {
                        continue;
                    }

                    if (Character.isUpperCase(c)) {
                        white += weight;
                    } else {
                        black += weight;
                    }
                }
            }

            if (white > black) {
                out.print("White");
            } else if (white < black) {
                out.print("Black");
            } else {
                out.print("Draw");
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
            stringTokenizer = null;

            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
