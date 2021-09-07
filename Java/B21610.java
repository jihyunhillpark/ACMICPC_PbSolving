import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int dr[] = {0,-1,-1,-1,0,1,1,1};
    static int dc[] = {-1,-1,0,1,1,1,0,-1};
    static class cloud {
        int r;
        int c;
        int val;
        public cloud(int r, int c, int val){
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int grid[][] = new int[N][N];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < N ; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<cloud> clouds = new ArrayList<>();
        clouds.add(new cloud(N-1,0, grid[N-1][0]));
        clouds.add(new cloud(N-1,1, grid[N-1][1]));
        clouds.add(new cloud(N-2,0, grid[N-2][0]));
        clouds.add(new cloud(N-2,1, grid[N-2][1]));

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(in.readLine(), " ");
            int d = Integer.parseInt(st.nextToken())-1; //방향
            int s = Integer.parseInt(st.nextToken()); //얼만큼 이동
            // 1번 구름 이동

            for(int j = 0 ; j < clouds.size() ; j++){
                int R = clouds.get(j).r;
                int C = clouds.get(j).c;
                int newR = (s * N + R + dr[d] * s) %N;
                int newC = (s * N + C + dc[d] * s) %N;
                clouds.get(j).r = newR;
                clouds.get(j).c = newC;
                clouds.get(j).val = grid[newR][newC];
            }
            // 2번 비내리기
            Queue<cloud> queue = new ArrayDeque<>();
            for(int j = 0 ; j < clouds.size() ; j++){
                int R = clouds.get(j).r;
                int C = clouds.get(j).c;
                grid[R][C] = ++clouds.get(j).val;
            }

            for(int j = 0 ; j < clouds.size() ; j++) {
                int R = clouds.get(j).r;
                int C = clouds.get(j).c;
                int count = 0;
                for (int k = 1; k <= 4; k++) { // 물의 양 저장
                    int diagonalR = dr[k * 2 - 1] + R;
                    int diagonalC = dc[k * 2 - 1] + C;
                    if (diagonalR < 0 || N <= diagonalR || diagonalC < 0 || N <= diagonalC) continue;
                    if (grid[diagonalR][diagonalC] > 0) count++;
                }
                queue.offer(new cloud(R, C, count)); // if 조건 없앰...
            }
            // 3번 구름 없애기
            clouds.clear();

            // 4번 구름자리에 대각선에 따라 물의 양 반영
            HashSet<Integer> set = new HashSet<>();
            while(!queue.isEmpty()){
                cloud cl = queue.poll();
                grid[cl.r][cl.c] += cl.val;
                set.add(N*cl.r + cl.c);
            }

            // 5번 구름을 제외한 위치에 구름 생성
            for(int j = 0 ; j < N ; j++) {
                for(int k = 0 ; k < N ; k++){
                    if(set.contains(j*N + k)) continue;
                    if(2 <= grid[j][k]) {
                        grid[j][k]-= 2;
                        clouds.add(new cloud(j,k,grid[j][k]));
                    }

                }
            }
        }
        // 결과 출력
        int ret = 0;
        for(int[] row: grid){
            for(int e : row){
                ret += e;
            }
        }
        System.out.println(ret);
    }

//    public static void print(int[][] grid){
//        for(int[] row : grid){
//            for(int e : row){
//                System.out.print(e + " ");
//            }
//            System.out.println();
//        }
//    }
}

