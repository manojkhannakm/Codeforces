package round292div2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class B {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));

        int t = 1;
//		int t = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken()),
                    m = Integer.parseInt(stringTokenizer.nextToken());

            int[] x = new int[n],
                    y = new int[m];

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = 0; j < b; j++) {
                x[j] = 1;
            }

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int g = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = 0; j < g; j++) {
                y[j] = 1;
            }

            if ((n < m && m % n != 0) || (n > m && n % m != 0)) {
                if (b > 0 || g > 0) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }

                continue;
            }

            int min = Math.min(n, m),
                    max = Math.max(n, m);
            int[] xy = new int[min];
            for (int j = 0; j < max; j++) {
                if (x[j % n] + y[j % m] > 0) {
                    x[j % n] = y[j % m] = 1;
                }

                xy[j % min] += x[j % n] + y[j % m];
            }

            boolean flag = true;
            for (int j = 0; j < min; j++) {
                if (xy[j] == 0) {
                    flag = false;
                }
            }

            System.out.println(flag ? "Yes" : "No");
        }
    }

}
