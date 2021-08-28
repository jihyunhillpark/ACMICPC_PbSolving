package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine()); //도시
        int M = Integer.parseInt(in.readLine()); //버스

        boolean visit[] = new boolean[N+1]; //방문
        long costs[] = new long[N+1];
        int adj[][] = new int[N+1][N+1]; //인접행렬
        ArrayDeque<Integer> paths[] = new ArrayDeque[N+1];
        for(int i = 0; i <= N ;i++) paths[i] = new ArrayDeque<>();
        int count = 0;

        for(int i = 0 ; i <= N ; i++) Arrays.fill(adj[i], 100000);
        for(int i = 0 ;i < M ; i++){
            StringTokenizer st = new StringTokenizer(in.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if( adj[start][end] > cost) adj[start][end] = cost;
        }
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        //처리
        //초기값
        visit[0] = visit[start] = true;
        count += 2;
        for(int i = 0 ; i <= N ; i++ ){
            if( adj[start][i] <100000 ) {
                costs[i] = adj[start][i];
                paths[i].add(start);
            }
            else costs[i] = Long.MAX_VALUE;
        }
        //시작
        while( count <= N){
            int min_idx = 0;
            for(int j = 0; j <= N  ; j++){
                if( !visit[j] && costs[j] < costs[min_idx] ) min_idx = j;
            }
            visit[min_idx] = true;
            paths[min_idx].add(min_idx);
            for(int j = 1; j <= N  ; j++){
                if( !visit[j] && adj[min_idx][j] < 100000){
                    if(costs[j] > costs[min_idx] + adj[min_idx][j] ) {
                        paths[j] = paths[min_idx].clone();
                    }
                    costs[j] = Math.min(costs[j], costs[min_idx] + adj[min_idx][j] );
                }
            }
            if(min_idx == dest) break;
            count++;
        }
        sb.append(costs[dest] + "\n");
        sb.append(paths[dest].size() + "\n");
        for(int e : paths[dest]) sb.append(e + " ");
        System.out.println(sb);
    }
}
