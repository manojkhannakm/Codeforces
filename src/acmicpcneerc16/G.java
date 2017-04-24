package acmicpcneerc16;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author Manoj Khanna
 */

public class G {

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

            long[] s = new long[n],
                    d = new long[n];

            for (int i = 0; i < n; i++) {
                s[i] = in.nextInt();
                d[i] = in.nextInt();
            }

            TreeSet<Interval<Long>> set = new TreeSet<>();
            ArrayList<Interval<Long>> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                Interval<Long> interval = new Interval<>(s[i], s[i] + d[i] - 1);
                boolean f = true;

                for (Interval<Long> tInterval : set) {
                    if (interval.overlap(tInterval)) {
                        f = false;
                        break;
                    }
                }

                if (f) {
                    set.add(interval);
                    list.add(interval);
                    continue;
                }

                Interval<Long> interval1 = new Interval<>(0L, 0L);

                for (Interval<Long> interval2 : set) {
                    if (interval2.l - interval1.r > d[i]) {
                        break;
                    }

                    interval1 = interval2;
                }

                interval.l = interval1.r + 1L;
                interval.r = interval.l + d[i] - 1L;

                set.add(interval);
                list.add(interval);
            }

            for (Interval<Long> interval : list) {
                out.println(interval.l + " " + interval.r);
            }
        }

        private static class Interval<E extends Number> implements Comparable<Interval<E>> {

            private E l, r;

            public Interval() {
            }

            public Interval(E l, E r) {
                this.l = l;
                this.r = r;
            }

            public Interval(Interval<E> interval) {
                this(interval.l, interval.r);
            }

            @Override
            public int hashCode() {
                return l.hashCode() * 31 + r.hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                return obj instanceof Interval
                        && l.equals(((Interval) obj).l)
                        && r.equals(((Interval) obj).r);
            }

            @Override
            public String toString() {
                return "[" + l + ", " + r + "]";
            }

            @Override
            public int compareTo(Interval<E> o) {
                int c = ((Comparable<E>) l).compareTo(o.l);

                if (c == 0) {
                    c = ((Comparable<E>) r).compareTo(o.r);
                }

                return c;
            }

            public boolean empty() {
                return ((Comparable<E>) l).compareTo(r) >= 0;
            }

            public boolean overlap(Interval<E> interval) {
                return ((Comparable<E>) r).compareTo(interval.l) != -1
                        && ((Comparable<E>) interval.r).compareTo(l) != -1;
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
