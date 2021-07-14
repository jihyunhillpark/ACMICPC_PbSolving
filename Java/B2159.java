import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2159 {
    static int[] dx = { 0, 0, -1, 1, 0}; // 상,하,좌,우,정
    static int[] dy = { 1, -1, 0, 0, 0};
    public static void main(String[] args) throws IOException {
        // 입력  DP - 1.중복해? 2.부분최적합!
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st;

        // 공간 차지 - 700,000
        long[][] dist = new long[N+1][5];
        int[][] input = new int[N+1][2];

        for(int i = 0 ; i <= N ; i++){
            st = new StringTokenizer(in.readLine()," ");
            input[i][0] = Integer.parseInt(st.nextToken()); //x
            input[i][1] = Integer.parseInt(st.nextToken()); //y
        }

        // 처리
        for(int i = 1 ; i <= N ; i++){
            for(int j = 0 ; j < 5 ; j++){
                int currX = input[i][0] + dx[j];
                int currY = input[i][1] + dy[j];
                if( i == 1 ){
                    dist[i][j] = Math.abs(currX - input[0][0]) + Math.abs(currY - input[0][1]);
                }
                else { //일반적인 점화식
                    // min( dist[i-j] + x-x + y-y)
                    long min = Long.MAX_VALUE;
                    for(int k = 0; k < 5; k++){
                        int preX = input[i-1][0] + dx[k];
                        int preY = input[i-1][1] + dy[k];
                        long currMinDist= dist[i-1][k] + Math.abs(currX- preX) + Math.abs(currY- preY);
                        min = (min > currMinDist)? currMinDist : min;
                    }
                    dist[i][j] = min;
                }
            }
        }
        long result = Long.MAX_VALUE;
        for(long a : dist[N]){
            result = (result > a )? a : result;
        }
        System.out.println(result);
    }
}
