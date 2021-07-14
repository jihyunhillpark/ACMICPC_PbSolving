package BOJ2159;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char S[] = in.readLine().toCharArray();
        char P[] = in.readLine().toCharArray();
        int i,j,ans = 0;
        int lastPIdx = P.length-1;
        char last = P[lastPIdx];
        int lastIdx[] = new int[26];
        Arrays.fill(lastIdx, -1);

        for(i = 0 ; i <= lastPIdx ; i++)
            lastIdx[P[i] - 'a'] = i;

        for(i = lastPIdx ; i < S.length; ){
            for(j = lastPIdx  ; 0 <= j ; j--){
                char currSChar = S[i - (lastPIdx - j)];
                if(P[j] != currSChar) {
                    int jump = lastIdx[currSChar - 'a'];
                    if (0 <= jump) {
                        i += lastPIdx - jump;
                    }
                    else i++;
                    break;
                }
            }
            if( j < 0 ) {
                ans = 1;
                break;
            }
        }
        System.out.println(ans);
    }
}
