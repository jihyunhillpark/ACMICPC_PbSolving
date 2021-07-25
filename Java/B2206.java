import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1,1,0,0}; // 상, 하, 좌, 우
    static int[] dc = {0,0,-1,1}; // 상, 하, 좌, 우
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] mat = new int[N][M];
        int[][] visit = new int[N][M];
        Queue<Integer> queue = new LinkedList<>();

        int count = 0;
        boolean flag = false;
        // 입력
        for(int i = 0 ; i < N ; i++){
            String temp = in.readLine();
            for(int j = 0; j < M ; j++)
                mat[i][j] = temp.charAt(j) - '0';
        }

        //처리
        queue.offer(0);
        visit[0][0] = 1;

        while(!queue.isEmpty() && !flag){
            int level = queue.size();
            count++;
            for(int num = 0; num < level; num++){
                int el = queue.poll();
                int row = el/10000, col = el%10000;
                if(row == N-1 && col == M-1) {
                    flag = true;
                    break;
                }
                for(int i = 0 ; i < 4 ; i++){
                    int curRow = row + dr[i];
                    int curCol = col + dc[i];
                    if(curRow < 0 || N <= curRow || curCol < 0 || M <= curCol ) continue;
                    if(mat[curRow][curCol] == 0){ //벽이 아닌 경우
                        if( visit[curRow][curCol] == 0 ) { // 첫 방문일 때
                            visit[curRow][curCol] = visit[row][col];
                        }
                        else if( visit[curRow][curCol] > visit[row][col])  // 첫 방문은 아니나, 전에 들렀던게 벽을 부순적이 있고, 나는 없을 때
                            visit[curRow][curCol] = 1;
                        else continue;
                    }
                    else{ //벽인 경우
                        if( visit[curRow][curCol] == 0 && visit[row][col] != 2) visit[curRow][curCol] = 2 ;
                        else continue;
                    }
                    queue.offer(curRow*10000 + curCol);
                }
            }
        }
        count = (flag)? count : -1;
        System.out.println(count);
    }
}
