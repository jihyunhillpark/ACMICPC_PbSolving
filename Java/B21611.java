import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int destroyR[] = {-1,1,0,0};
    static int destroyC[] = {0,0,-1,1};
    static int rotateR[] = {0,1,0,-1};
    static int rotateC[] = {-1,0,1,0};
    static int map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 입력
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0 ; i < N ;i++){
            st = new StringTokenizer(in.readLine()," ");
            for(int j = 0 ; j < N ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 처리
        for(int i = 0 ; i < M ;i++){
            st = new StringTokenizer(in.readLine()," ");
            int d = Integer.parseInt(st.nextToken())-1; //방향 - destroy
            int s = Integer.parseInt(st.nextToken()); //거리
            destroy(d,s);
            move();
            boolean bombCheck = true;
            while(bombCheck){
                bombCheck = bomb();
                if( !bombCheck) move();
            }
            grouping();
        }
    }

    private static void grouping() {
    }

    private static boolean bomb() {
        boolean hasBomb = false;
        return hasBomb;
    }

    private static void move() {
        int curR = map.length/2;
        int curC = map.length/2;
        int gap = 1, gapCnt = 1;
        boolean isDone= true;
        int dir = 0;
        while(isDone){
            for(int i = 0 ; i < gap ; i++){

                if(curR == 0 && curC == 0) isDone = false;
            }
            if(gapCnt == 2){
                gap++;
                gapCnt = 1;
            }
            else gapCnt++;
        }

    }

    private static void destroy(int d, int s) {
        for(int i = 1 ; i <= s ; i++){
            int newR = destroyR[d]*i;
            int newC = destroyC[d]*i;
            if( 0 <= newR && newR < map.length && 0 <= newC && newC < map.length)
                map[newR][newC] = 0;
        }
    }

}