package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static HashSet<Integer> set = new HashSet<>();
    static int map[][] = new int [5][5];
    static int dx[] = { -1, 0, 1, 0}; // 위, 우, 하, 좌
    static int dy[] = { 0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0 ; i < 5 ; i ++){
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < 5 ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS
        for(int i = 0 ; i < 5 ; i ++){
            for(int j = 0 ; j < 5 ; j++){
                dfs(i, j, 1, map[i][j]);
            }
        }
        System.out.println( set.size());
    }
    static void dfs(int r, int c, int level, Integer sum){
        if( level == 6 ) {
            if(!set.contains(sum)) set.add(sum);
            return;
        }
        for( int i = 0 ; i < 4 ; i++ ){
            int nextR = r + dx[i];
            int nextC = c + dy[i];
            if( nextR < 0 || 5 <= nextR || nextC < 0 || 5 <= nextC) continue;
            dfs(nextR, nextC, level+1, sum*10 + map[nextR][nextC] );
        }

    }
}
