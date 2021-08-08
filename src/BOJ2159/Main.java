package BOJ2159;

import com.sun.tools.javadoc.Start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int people[] = new int[size];
        int pos[] = new int[size];

        for(int i = 0 ; i < size ; i++)
            people[i] = Integer.parseInt(st.nextToken());

        // 처리
        for(int i = 0; i < size ; i++){
            int ct = people[i];
            for(int j = 0; j < size ; j++){
                if(pos[j] != 0 ) continue;
                else{
                    if(ct == 0) {
                        pos[j] = i+1;
                        break;
                    }
                    else ct--;
                }
            }
        }
        for(int e : pos) System.out.print(e + " ");
        System.out.println();
    }
}
