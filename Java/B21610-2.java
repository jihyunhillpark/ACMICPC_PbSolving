import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B21610-2 {
    static int dr[] = {0,-1,-1,-1,0,1,1,1};
    static int dc[] = {-1,-1,0,1,1,1,0,-1};
    static class Cloud{
        int r;
        int c;
        int val;
        Cloud(int r, int c, int val){
            this.r = r;
            this.c = c;
            this.val = val;
        }
        public void setCloud(int r,int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int map[][] = new int[N][N];
        boolean visited[][] = new boolean[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0 ; j < N ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 처리
        List<Cloud> clouds = new ArrayList<Cloud>();
        clouds.add(new Cloud(N-1,0,map[N-1][0]));
        clouds.add(new Cloud(N-1,1,map[N-1][1]));
        clouds.add(new Cloud(N-2,0,map[N-2][0]));
        clouds.add(new Cloud(N-2,1,map[N-2][1]));

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(in.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int size = clouds.size();
            // 1번 2번 - 구름 이동 및 비내리기
            for(int j = 0 ; j < size ; j++) {
                int newR = (N*s + clouds.get(j).r + dr[d]*s)%N;
                int newC = (N*s + clouds.get(j).c + dc[d]*s)%N;
                int val = clouds.get(j).val;
                map[newR][newC]++;
                clouds.get(j).setCloud(newR,newC,map[newR][newC]);
                visited[newR][newC] = true;
            }

            // 4번 - 대각선을 토대로 물복사 버그 - 즉각 반영하면 다른 요소에 영향을 미쳐서 한꺼번에 반영해야함 - cache
            for(int j = 0 ; j < size ; j++) {
                int r = clouds.get(j).r;
                int c = clouds.get(j).c;
                int val = clouds.get(j).val;
                int count = 0;
                for(int diag = 1 ; diag < 8 ; diag+=2){
                    int tempR = r + dr[diag];
                    int tempC = c + dc[diag];
                    if(tempR < 0 || N <= tempR ||tempC < 0 || N <= tempC ) continue;
                    if( map[tempR][tempC] > 0) count++;
                }
                clouds.get(j).setCloud(r,c,val +count);
            }

            for(int j = 0 ; j < size ; j++) {
                int r = clouds.get(j).r;
                int c = clouds.get(j).c;
                map[r][c] = clouds.get(j).val;
            }

            // 5번 비가 내리지 않은 곳에서 바구니 2이상인 곳만 구름생성
            List<Cloud> newClouds = new ArrayList<Cloud>();
            for(int j = 0 ; j < N ;j++){
                for(int k = 0 ; k < N ; k++){
                    if(!visited[j][k] && 2<=map[j][k]){
                        map[j][k] -=2;
                        newClouds.add(new Cloud(j,k,map[j][k]));
                    }
                }
            }
            clouds = newClouds;
            for(int j = 0 ; j < N ;j++) Arrays.fill(visited[j],false);

        }
        // 마지막 물의 양 합구하기
        int result = 0;
        for(int[] cl : map){
            for(int c : cl) result += c;
        }
        System.out.println(result);
    }
}