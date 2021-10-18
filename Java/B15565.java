import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class B15565 {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine(), " ");
        int input[] = new int[N];
        for(int i  = 0 ; i < N ; i ++)
            input[i] = Integer.parseInt(st.nextToken());

        // 처리
        int lionPos[] = new int[N];
        int right = 0, left = 0;
        int ans = 1000001;
        for(int i  = 0 ; i < N ; i ++){
            if(input[i] == 1) {
                lionPos[right] = i;
                if(right-left == K-1) {
                    int abs = lionPos[right] - lionPos[left] + 1;
                    ans = (abs < ans)? abs : ans;
                    left++;
                }
                right++;
            }
        }
        if(ans == 1000001) ans = -1;
        System.out.println(ans);
    }

}