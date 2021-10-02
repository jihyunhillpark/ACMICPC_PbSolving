package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class B3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 입력
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int pass[] = new int[N];
        long max = 0; // 최대 소요시간 - long 필수
        for(int i = 0 ; i < N ; i++){
            pass[i] = Integer.parseInt(in.readLine());
            if(max < pass[i]) max =pass[i];
        }
        max *= M;
        long left = 1, right = max;
        long ans = right;
        while( left <= right){
            long count = 0;
            long mid = (left + right)/2;
            for(int i = 0 ; i < pass.length ; i++){
                count += (mid/pass[i]);
            }
            if(count >= M){
                right =mid-1;
                ans = mid;
            }
            else left = mid + 1;
        }
        System.out.println(ans);
    }
}