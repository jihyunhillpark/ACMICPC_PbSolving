import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class B2118 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 입력
        int N = Integer.parseInt(in.readLine());
        int point[] = new int[N];
        int max = 0;
        int totalLength = 0;
        for(int i = 0 ; i < N ; i++){
            point[i] = Integer.parseInt(in.readLine());
            totalLength += point[i];
        }
        for(int i = 0 ; i < N-1 ; i++){
            int temp1 = point[i];
            int temp2 = totalLength - temp1;
            for(int j= i+1; j < N ; j++){
                int tempMax = (temp1 < temp2)? temp1: temp2;
                max = (tempMax < max)?max:tempMax;
                temp1 += point[j];
                temp2 -= point[j];
            }
        }
        System.out.println(max);
    }

}