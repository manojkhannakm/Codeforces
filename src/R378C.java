import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class R378C {

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
            int n = in.nextInt();

            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            int k = in.nextInt();

            int[] b = new int[k];

            for (int i = 0; i < k; i++) {
                b[i] = in.nextInt();
            }

            LinkedList<Integer> aList = new LinkedList<>();

            for (int ai : a) {
                aList.add(ai);
            }

            LinkedList<Integer> bList = new LinkedList<>();

            for (int bi : b) {
                bList.add(bi);
            }

            ArrayList<String> list = new ArrayList<>();
            int r = 0;

            while (!aList.isEmpty() && !bList.isEmpty()) {
                if (aList.get(0).equals(bList.get(0))) {
                    aList.remove(0);
                    bList.remove(0);

                    r++;
                    continue;
                }

                if (aList.get(0) > bList.get(0) || aList.size() == 1) {
                    break;
                }

                if (aList.get(0) > aList.get(1)) {
                    if (aList.size() == 2 || aList.get(0) + aList.get(1) != aList.get(2)) {
                        aList.set(1, aList.get(0) + aList.get(1));
                        aList.remove(0);

                        list.add((r + 1) + " R");
                        continue;
                    }
                }


                if (aList.get(0) < aList.get(1)) {
                    aList.set(0, aList.get(1) + aList.get(0));
                    aList.remove(1);

                    list.add((r + 2) + " L");
                    continue;
                }

                int i = 1;

                while (i < aList.size() && aList.get(i - 1).equals(aList.get(i))) {
                    i++;
                }

                if (i == aList.size()) {
                    break;
                }

                if (aList.get(i - 1) > aList.get(i)) {
                    aList.set(i, aList.get(i - 1) + aList.get(i));
                    aList.remove(i - 1);

                    list.add((r + i) + " R");

                    i--;
                }

                for (; i > 0; i--) {
                    aList.set(i - 1, aList.get(i) + aList.get(i - 1));
                    aList.remove(i);

                    list.add((r + i + 1) + " L");
                }
            }

            if (aList.isEmpty() && bList.isEmpty()) {
                out.println("YES");

                for (String s : list) {
                    out.println(s);
                }
            } else {
                out.println("NO");
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
