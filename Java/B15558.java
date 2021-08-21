import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 방문처리 안하면 터짐..
public class Main {
    static class Pair {
        int idx;
        int dir;
        public Pair(int idx, int dir){
            this.idx = idx;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int pass = 0;

        char input[][] = new char[2][];
        input[0] = in.readLine().toCharArray(); // 왼쪽
        input[1] = in.readLine().toCharArray(); // 오른쪽

        boolean visit[][] = new boolean[2][];
        visit[0] = new boolean[N];
        visit[1] = new boolean[N];

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(0,0));
        visit[0][0] = true;
        int level = 0;

        while(!queue.isEmpty() && pass == 0 ){
            int size = queue.size();
            for(int i = 0 ; i < size ; i++){
                Pair el = queue.poll();
                if( el.idx < level ) continue;
                int can1 = el.idx + K;
                int can2 = el.idx + 1;
                int can3 = el.idx - 1;
                if( N <=  can1 || N <= can2 || N <= can3 ){
                    pass = 1; break;
                }
                //case 1 반대편 +k
                int opp = (el.dir == 0)? 1: 0;
                if( input[opp][can1] == '1' && !visit[opp][can1] ){
                    visit[opp][can1] = true;
                    queue.add(new Pair(can1 , opp));
                }
                //case 2 앞으로
                if( input[el.dir][can2] == '1' && !visit[el.dir][can2]) {
                    queue.add(new Pair(can2,el.dir ));
                    visit[el.dir][can2] = true;
                }
                //case 3 뒤로
                if( level < can3 && input[el.dir][can3] == '1' && !visit[el.dir][can3]) {
                    queue.add(new Pair(can3, el.dir));
                    visit[el.dir][can3] = true;
                }
            }
            level++;
        }
        System.out.println(pass);
    }
}
