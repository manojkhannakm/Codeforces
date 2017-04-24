package betaround1;

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
        long n = Long.parseLong(stringTokenizer.nextToken()),
                m = Long.parseLong(stringTokenizer.nextToken()),
                a = Long.parseLong(stringTokenizer.nextToken());
        System.out.println(((long) (Math.ceil((double) n / a) * Math.ceil((double) m / a))));
    }

}
