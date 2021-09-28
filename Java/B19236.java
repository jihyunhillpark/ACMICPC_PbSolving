import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class B19236 {
    static int dr[] = {-1,-1,0,1,1,1,0,-1};
    static int dc[] = {0,-1,-1,-1,0,1,1,1};
    static class Fish{
        int dir;
        int no;
        Fish(int no, int dir){
            this.dir = dir;
            this.no = no;
        }
    }
    static int MAX = 0;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Fish [][]map = new Fish[4][4];
        for(int i = 0 ; i < 4 ; i++){
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < 4 ;j++){
                int fNo = Integer.parseInt(st.nextToken());
                int fDir = Integer.parseInt(st.nextToken())-1;
                map[i][j] = new Fish(fNo,fDir);
            }
        }

        MAX += map[0][0].no;
        map[0][0].no = -1;
        dfs(MAX, map, 0,0); // 상어는 0
        //처리
        System.out.println(MAX);
    }

    private static void dfs(int tempSum, Fish[][] map, int sharkR,int sharkC) {

        Map fishMap = new HashMap<Integer, Integer>(); // 물고기 넘버, row*10 + col
        for(int i = 0 ; i < 4 ;i++){
            for(int j = 0; j < 4 ; j++){
                if(map[i][j].no != 0) fishMap.put(map[i][j].no, 10*i + j);
            }
        }
        for(int ct = 1 ; ct <= 16 ; ct++){
            if( fishMap.containsKey(ct)){
                int val= (Integer)fishMap.get(ct);
                int r= val/10;
                int c =val%10;
                for(int rot = 0 ; rot<8 ; rot++) {
                    int tempDir =  (map[r][c].dir+rot) %8; //현재 방향
                    int newR = r + dr[tempDir]; //대상
                    int newC = c + dc[tempDir];
                    if (0 <= newR && newR < 4
                            && 0 <= newC && newC < 4
                            && map[newR][newC].no != -1) { //바꿀 수 있는 경우
                        int targetNo = map[newR][newC].no;
                        int targetDir = map[newR][newC].dir;
                        map[newR][newC].no = map[r][c].no;
                        map[newR][newC].dir = tempDir;
                        map[r][c].no = targetNo;
                        map[r][c].dir = targetDir;
                        fishMap.put(ct, newR * 10 + newC);
                        fishMap.put(targetNo, r * 10 + c);
                        break;
                    }
                }
            }
        }

        //상어 이동
        boolean noMove = true;
        for(int s = 1 ; s <= 3 ; s++){
            int newSR = sharkR + dr[map[sharkR][sharkC].dir] * s;
            int newSC = sharkC + dc[map[sharkR][sharkC].dir] * s;
//            System.out.println(newSR + " " + newSC);
            if( newSR < 0 || 4 <= newSR || newSC < 0 || 4<=newSC) continue;
            int val = map[newSR][newSC].no;
            if( val > 0) {
                map[sharkR][sharkC].no = 0;
                int temp = map[sharkR][sharkC].dir;
                map[sharkR][sharkC].dir = -1; // 빈칸으로 만들기
                map[newSR][newSC].no = -1;
                noMove = false;
                Fish tempMap[][] = new Fish[4][4]; // 배열복사
                for(int index = 0 ; index < 4 ; index++){
                    for(int jind = 0 ; jind < 4 ; jind++){
                        tempMap[index][jind] = new Fish(map[index][jind].no,map[index][jind].dir);
                    }
                }
                dfs(tempSum + val, tempMap, newSR, newSC);
                map[newSR][newSC].no = val;
                map[sharkR][sharkC].no = -1;
                map[sharkR][sharkC].dir = temp; // 빈칸으로 만들기
            }
        }
        if(noMove) { // 상어 갈데 없음
            MAX = (MAX < tempSum)? tempSum:MAX;
//            for(Fish[] e: map){
//                for(Fish a: e){
//                    System.out.print(a.no + " " + a.dir + ", " );
//                }
//                System.out.println();
//            }
//            System.out.println("tempSum" + tempSum);
//            System.out.println("====");
        }
        return;
    }

}