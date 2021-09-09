import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // 입력
        int N = Integer.parseInt(in.readLine());
        long fluid[] = new long[N];
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        for(int i = 0 ; i< N ; i++){
            fluid[i] = Long.parseLong(st.nextToken());
        }
        //처리
        Arrays.sort(fluid);

        long ret[] = new long[3];
        long min = Long.MAX_VALUE;
        for(int i = 0 ; i < N-2 ; i++){ // 포인터 1
            int j = i+1, k = N-1;
            long temp = 0;
            while(j < k){
                temp = fluid[i] + fluid[j] + fluid[k];
                if( Math.abs(temp) < min){
                    min =  Math.abs(temp);
                    ret[0] = fluid[i];
                    ret[1] = fluid[j];
                    ret[2] = fluid[k];
                }
                if( temp < 0) j++;
                else if (temp > 0) k--;
                else break;
            }
            if(min == 0 ) break;
        }
        System.out.println(ret[0] + " " + ret[1] + " " + ret[2]);
    }
}

