import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        for(int i = 0 ; i < T ; i++){
            Long N = Long.parseLong(in.readLine());
            int num = (int) ((Math.sqrt(1+8*N) - 1)/2);
            sb.append(num);
            sb.append("\n");
        }
        System.out.println(sb);
    }


}
