import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static Edge[] edges;
    static long [] dist;
    static int INF = Integer.MAX_VALUE;
    static class Edge {
        int start, end, weight;

        public Edge (int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken()); // 도시의 수
        int M = Integer.parseInt(st.nextToken()); // 버스 노선의 수

        edges = new Edge[M];
        dist = new long[N];
        Arrays.fill(dist, INF); // 초기값 무한대

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(start-1, end-1, weight);
        }
        if( !bellmanFord(N,M)){
            sb.append(-1);
        }else {
            for(int i = 1 ; i < N; i++) {
                if( dist[i] == INF) dist[i] = -1; //갈 수 없어~
                sb.append(dist[i]);
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean bellmanFord(int N, int M) {
        // 시작점 최단거리 0 갱신
        dist[0] = 0;

        // v - 1번 돌면서 최단 거리를 갱신한다.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                Edge bus = edges[j];
                // 더 작은 최단거리 가 있는 경우 갱신
                if(dist[bus.start] != INF && dist[bus.end] > dist[bus.start] + bus.weight){
                    dist[bus.end] = dist[bus.start] + bus.weight;
                }
            }
        }

        // 싸이클 확인
        for(int i = 0; i < M; i++){
            Edge bus = edges[i];
            if(dist[bus.start] != INF &&dist[bus.end] > dist[bus.start] + bus.weight)
                return false;
        }

        return true;
    }

}



