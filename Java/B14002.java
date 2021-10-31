import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    /*10
     *1 2 3 2 3 4 5 2 6 10
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(in.readLine());
        int[] arr = new int[num];
        int[] dp = new int[num];
        String[] contents = new String[num];

        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        for(int i = 0 ; i < num ; i++) arr[i] = Integer.parseInt(st.nextToken());
        // 처리
        Arrays.fill(dp,1);
        contents[0] = "" + arr[0];
        int maxIdx = 0;
        for(int i = 1 ; i < num ; i++){
            contents[i] = "" + arr[i];
            for(int j = 0 ; j < i ; j++){
                if(arr[j] < arr[i] && dp[j] >= dp[i]) {
                    contents[i] = contents[j] + " " + arr[i];
                    dp[i] = dp[j]+1;
                }
            }
            if(dp[maxIdx] < dp[i]) maxIdx = i;
        }
        System.out.println(dp[maxIdx]);
        System.out.println(contents[maxIdx]);
    }

}