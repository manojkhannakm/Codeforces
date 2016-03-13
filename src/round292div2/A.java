package round292div2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class A {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        long a = Math.abs(Long.parseLong(stringTokenizer.nextToken())),
                b = Math.abs(Long.parseLong(stringTokenizer.nextToken())),
                s = Long.parseLong(stringTokenizer.nextToken());
        long l = s - (a + b);
        System.out.println(l >= 0 && l % 2 == 0 ? "Yes" : "No");
    }

}
