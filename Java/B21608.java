import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {-1, 0 , 0, 1}; // 위, 좌, 우, 아래
    static int[] dc = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(in.readLine());
        int mat[][] = new int[N][N];
        for(int i =0 ; i < N; i++ ){
            for(int j = 0 ; j < N ; j++){
                mat[i][j] = -1;
            }
        }
        HashSet<Integer> student[] = new HashSet[N*N];
        for(int i = 0 ; i < N*N ; i++) student[i] = new HashSet<>();
        int pos[][] = new int[N][2]; //0 = r, 1 = c
        int score = 0;

        for(int i = 0 ; i < N*N ; i++ ){
            st = new StringTokenizer(in.readLine(), " ");
            int target = Integer.parseInt(st.nextToken()) - 1;
            for(int j = 0 ; j < 4 ; j++)
                student[target].add(Integer.parseInt(st.nextToken()) - 1);
            // 맵 순회
            int count = 0, empty = 0, r = 0, c = 0;
            boolean first = true;
            for(int j = 0 ; j < N ; j++){
                for(int k = 0 ; k < N ; k++){
                    if(mat[j][k] != -1 ) continue; // 자리가 있는 경우는 건너뜀
                    if (first) {
                        r = j;
                        c = k;
                        first = false;
                    }
                    int cmpCount = 0;
                    int cmpEmpty = 0;
                    boolean change = false;
                    for(int ct =0 ; ct < 4 ; ct++){
                        int newR = j + dr[ct];
                        int newC = k + dc[ct];
                        if(newR < 0 || N <= newR ||newC < 0 || N <= newC ) continue;;
                        if(student[target].contains(mat[newR][newC])) cmpCount++;
                        else if(mat[newR][newC] == -1) cmpEmpty++;
                    }
                    if(cmpCount > count) change = true;//1번 조건
                    else if( cmpCount == count){
                        if( cmpEmpty > empty) change = true; // 2번 조건
                        else if( cmpCount == empty){
                            if(j < r) change = true; // 3-1 조건
                            else if(j == r){ // 3-2 조건
                                if( k < c) change = true;
                            }
                        }
                    }
                    if( change ){
                        empty = cmpEmpty;
                        count = cmpCount;
                        r = j;
                        c = k;
                    }
                }
                if( count == 4) break;
            }
            mat[r][c] = target;
//            System.out.println("mat"+ r + ", " + c + " = " + mat[r][c]);
        }

        // 만족도 합산
        for(int i = 0 ; i < N ; i++) {
            for (int j = 0; j < N; j++) {
                int curStudent = mat[i][j];
                int likeCount = 0;
                for(int ct = 0 ; ct < 4 ; ct++){
                    int newR = i + dr[ct];
                    int newC = j + dc[ct];
                    if(newR < 0 || N <= newR ||newC < 0 || N <= newC ) continue;
                    if(student[curStudent].contains(mat[newR][newC])) likeCount++;
                }
                switch (likeCount){
                    case 1 : {
                        score += 1;
                        break;
                    }
                    case 2 : {
                        score += 10;
                        break;
                    }
                    case 3 : {
                        score += 100;
                        break;
                    }
                    case 4 : {
                        score += 1000;
                        break;

                    }
                }
            }
        }

        System.out.println(score);
    }
}
