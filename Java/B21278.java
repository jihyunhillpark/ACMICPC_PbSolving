

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
/* 플로이드 워샬 */
public class B21278 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 입력
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int building[][] = new int[N][N];
        int []minDist = new int[N];
        for(int i = 0 ; i < N ; i++){
            Arrays.fill(building[i], 1000000); //maxvalue 위험!!
            building[i][i] = 0;
        }
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            building[a][b] = 2;
            building[b][a] = 2;
        }

        // 처리 - 플로이드 워샬로 일단 최단 거리 다 구해놓기
        for( int k = 0 ; k < N ; k++){
            for(int i = 0 ; i < N ; i++){
                for(int j = 0; j < N ; j++){
                    building[i][j] = Math.min(building[i][j], building[i][k] +  building[k][j]);
                }
            }
        }

        // 건물 두개
        int min = 100*100;
        int a = 0,b = 0;
        for(int i = 0 ; i < N ; i++){
            for(int j = i+1; j < N ; j++){
                int temp = 0;
                for(int idx = 0 ; idx < N ; idx++){
                    temp += Math.min(building[i][idx],building[j][idx]);
                }
                if (temp< min){
                    min = temp;
                    a = i+1;
                    b = j+1;
                }
            }
        }
        System.out.println(a+ " " + b+ " " + min);
    }


}