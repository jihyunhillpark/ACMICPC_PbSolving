package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static Set<Integer> set = new HashSet<>();
    static int[] student;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 입력
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
        for(int i = 0 ; i < T ; i++){
            int num = Integer.parseInt(in.readLine());
            student = new int[num];
            st=new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < num ; j++){
                student[j] = Integer.parseInt(st.nextToken())-1;
            }
            for(int j = 0 ; j < num ; j++){
                if( student[j] == j) { //자기 자신일 경우
                    set.add(student[j]);
                }
                else {
                    if (!set.contains(j)) {
                        Set temp = new HashSet<Integer>();
                        temp.add(j);
                        dfs(j, student[j], temp);
                    }
                }
            }
            sb.append(num- set.size());
            sb.append("\n");
            set.clear();
        }
        System.out.println(sb);
    }

    private static void dfs(int start, int now, Set<Integer> temp) {
        if( student[now] == now) return;
        else{
            if( student[now] == start) {
                for(int a : temp){
                    set.add(a);
                }
                return;
            }
            temp.add(now);
            dfs(start, student[now], temp);
        }
    }
}