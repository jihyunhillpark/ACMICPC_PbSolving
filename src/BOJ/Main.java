package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static char[][] patterns = {{'1','0','0','1'},{'0','1'}}; // pattern 0 = 0,1,2,3 , pattern 1 = 10, 11
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine());
        char[][] input = new char[N][];
        for(int i = 0 ; i < N ; i ++){
            input[i] = in.readLine().toCharArray();
        }

        // 처리
        for(int i = 0 ; i < N ; i ++){
            int state = -1;
            boolean flag = true;
            int j = 0;

            for( ; j < input[i].length ; j++){
                if(state == -1){ //start 상태인 경우
                    if(input[i][j] == '1') state = 0;
                    else state = 10;
                }
                //start 상태가 아닌 경우
                else {
                    switch(state){ //여기서 state는 이전 state
                        case 0 : { //이전 상태가 state 0일 경우
                            if(input[i][j] == '0' ) state = 1;
                            else flag = false;
                            break; }
                        case 1 : {
                            if(input[i][j] == '0' ) state = 2;
                            else flag = false;
                            break; }
                        case 2 : {
                            if(input[i][j] == '1') state = 3; //1000이라면~? 걸러야 한다
                            break; }
                        case 3 : {
                            if (input[i][j] == '1') state = 4;
                            else state = 10;
                            break; }
                        case 4: {
                            if(input[i][j] == '0') state = 5;
                            break;
                        }
                        case 5: {
                            if(input[i][j] == '0') state = 2;
                            else state = 11;
                            break;
                        }
                        case 10 : {
                            if(input[i][j] == '1') state = 11;
                            else flag = false;
                            break; }
                        case 11 : {
                            state = (input[i][j] == '0')? 10 : 0;
                            break; }
                    }
                }
                //System.out.println("now = " + input[i][j] + ", "+state + ", flag = " + flag + ", " + j);
                if(!flag) break;
            }
            System.out.println();
            sb.append((flag && (state == 3||state == 4 ||state == 11) )? "YES" : "NO");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
