import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine());
        int parent[] = new int[N+1];
        ArrayList<Integer> nodes[] = new ArrayList[N+1];
        for(int i = 1 ; i <= N ; i++ ) nodes[i] = new ArrayList<>();
        for(int i = 0 ; i < N-1 ; i++){
            st = new  StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from].add(to);
            nodes[to].add(from);
        }

        // 처리 - bfs 탐색하며 부모를 찾아간다.
        parent[1] = 1; //root 노드라서 부모를 자기 자신으로 한다.
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);

        while (!queue.isEmpty()){
            int directParent = queue.poll();
            for(int child : nodes[directParent]){
                if( parent[child] != 0 ) continue;
                parent[child] = directParent;
                queue.add(child);
            }
        }
        for(int i = 2 ; i <= N ; i++){
            sb.append(parent[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
