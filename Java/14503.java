package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int dr[] = {-1,0,1,0}; //북, 동, 남, 서
    static int dc[] = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean flag = true;
        int count = 0;
        st = new StringTokenizer(in.readLine()," "); // 로봇 청소기 위치
        int robotR = Integer.parseInt(st.nextToken());
        int robotC = Integer.parseInt(st.nextToken());
        int robotD = Integer.parseInt(st.nextToken());
        int [][] map = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(in.readLine()," ");
            for(int j = 0; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //처리
        while(flag){
            if(map[robotR][robotC] == 0){
                map[robotR][robotC] =2;
                count++;
            }
            int tempD = robotD;
            int i = 0;
            for( ; i< 4 ; i++){
                tempD = ((4 + tempD) -1) % 4;
                int newR = robotR + dr[tempD];
                int newC = robotC + dc[tempD];
                if(newR < 0 || N <= newR || newC < 0 || M <= newC ) continue;
                if( map[newR][newC] == 0){
                    robotR = newR;
                    robotC = newC;
                    robotD = tempD;
                    break;
                }
            }
            if(i == 4) {
                int tempD2 = (robotD + 2) % 4;
                int checkR = robotR + dr[tempD2];
                int checkC = robotC + dc[tempD2];
                if (checkR < 0 || N <= checkR || checkC < 0 || M <= checkC) {
                    flag = false;
                    break;
                }
                if (map[checkR][checkC] != 1) {
                    robotR = checkR;
                    robotC = checkC;
                } else flag = false;
            }
        }
        System.out.println(count);
    }
}