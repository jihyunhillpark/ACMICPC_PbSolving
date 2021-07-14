import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine()); // 좌석의 개수
        int M = Integer.parseInt(in.readLine());// 고정석의 개수
        int[] vips= new int[M];
        int[] dp = new int[N];

        for(int i = 0; i < M ; i++){
            int temp = Integer.parseInt(in.readLine()) - 1;
            vips[i] = temp;
        }

        //처리 - bottom up 방식
        for(int i = 0 ; i < N; i++){
            // i - #번재 차례
            switch(i){
                case 0: dp[i] = 1; break;
                case 1: dp[i] = 2; //1,2 ... 2,1 .. dp = 총 경우의수
                default: {
                    for( int j = 0 ; j < M ; j++) { // vips 다 순회
                        if (i == vips[j] || (i - 1) == vips[j]) { // i번쨰,
                            dp[i] -= (i == 1)? 1: dp[i-2];break;
                        }
                    }
                    if( 1 < i) dp[i] += (dp[i-2] + dp[i-1]);
                }
            }
        }
        System.out.println(dp[N-1]);
    }
}
