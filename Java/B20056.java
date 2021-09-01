import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int dr[] = {-1,-1,0,1,1,1,0,-1}; //방향
    static int dc[] = {0,1,1,1,0,-1,-1,-1};
    public static class FireBall{
        int r = 0, c = 0, m = 0,s = 0,d = 0;

        public FireBall( int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public FireBall(){

        }
    }
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Queue<FireBall> grid[][] = new Queue[N][N];
        for(int r = 0 ; r < N ; r++){
            for(int c = 0; c < N ; c++){
                grid[r][c] = new ArrayDeque<>();
            }
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(in.readLine(), " ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            grid[r][c].offer(new FireBall(r,c,m,s,d));
        }

        for(int i = 0; i < K ; i++){ //K번 반복
            int r = 0,c = 0;
            ArrayDeque<FireBall> cache = new ArrayDeque<>();
            for(r = 0 ; r < N; r++){ // 이동하는 곳 표시 1. 중복 위험으로 임시 저장소에 보관
                for(c = 0 ; c < N ; c++){
                    while( !grid[r][c].isEmpty() ){ //있다!
                        FireBall fb = grid[r][c].poll();
                        int nextR = ( r + dr[fb.d]* fb.s % N + N) % N;
                        int nextC = ( c + dc[fb.d]* fb.s % N + N) % N;
                        cache.offer(new FireBall(nextR,nextC,fb.m,fb.s,fb.d));
                    }
                }
            }
            // 실제 move를 하는 부분
            while(!cache.isEmpty()){
                FireBall newFireBall = cache.poll();
                int newR = newFireBall.r;
                int newC = newFireBall.c;
                grid[newR][newC].offer(newFireBall);
            }
            // 이동이 끝난 뒤...
            for(r = 0 ; r < N; r++){ // 뭉쳐있는거 나눠서 update 하는 부분
                for(c = 0 ; c < N ; c++){
                    if( 1 < grid[r][c].size()){ //한 칸에 두 개 이상.
                        boolean even = false, odd = false;
                        int mSum = 0;
                        int sSum = 0;
                        int count = grid[r][c].size();
                        while(!grid[r][c].isEmpty()){
                            FireBall fb = grid[r][c].poll();
                            // System.out.println("row & col "+ fb.r + ", " + fb.c + " ," + fb.m);
                            mSum += fb.m;
                            sSum += fb.s;
                            if( fb.d % 2 == 1) odd = true;
                            else even = true;
                        }
                        mSum /= 5;
                        sSum /= count;
                        int start = 0; //홀짝 어느 한쪽만 있음 2,4,6,8
                        if(mSum > 0 ){
                            if( even && odd ) start = 1; // 홀짝 둘다 있는 상태로  1,3,5,7
                            for(int n = start ; n < 8 ; n+=2){
                                // System.out.println("ININ == " + r + ", " + c + "mSum" + mSum + "," + n);
                                cache.offer(new FireBall( r,c, mSum, sSum, n)); //이 때도 겹치지 않도록 임시 저장소 이용
                            }
                        }
                    }
                }
            }
            // 실제 반영 부분
            while(!cache.isEmpty()){
                FireBall newFireBall = cache.poll();
                int curR = newFireBall.r;
                int curC = newFireBall.c;
//                int nextR = ( curR + dr[newFireBall.d]* newFireBall.s % N + N) % N;
//                int nextC = ( curC + dc[newFireBall.d]* newFireBall.s % N + N) % N;
                grid[curR][curC].offer(new FireBall(curR,curC, newFireBall.m , newFireBall.s, newFireBall.d));
            }
        }
        // 질량의 합 계산 및 출력 - 한 자리에 여러개가 있을 수 있자나~~!!
        int total = 0;
        for(int r = 0 ; r < N ;r++){
            for(int c = 0; c < N ; c++){
                while ( !grid[r][c].isEmpty()) {
                    total += grid[r][c].poll().m;
                }
            }
        }
        System.out.println(total);
    }


}
