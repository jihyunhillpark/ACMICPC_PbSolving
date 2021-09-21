import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(in.readLine());
        int N = Integer.parseInt(in.readLine());
        HashSet<Integer> broken = new HashSet<>();
        if(0 < N ) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int i = 0; i < N; i++) broken.add(Integer.parseInt(st.nextToken()));
        }

        //처리
        int sub = Math.abs(target-100);//1. 100에서 얼마나 떨어졌는지 저장
        int val = target;
        int num = 1;
        int temp = 0, sub2 = 0;
        boolean Ndone = true;
        while(Ndone) {
            int count = 0;
            do {
                // 2-1. 한번에 target에 가장 가까운 수로 이동하기
                int digit = val % 10;
                int upper = digit + 1;
                int down = digit - 1;
                if (broken.contains(digit)) { // 고장난 숫자일 경우 가장 가까운 수 찾기
                    while (true) {
                        if (!broken.contains(upper % 10)) break;
                        upper++;
                    }
                    while (0 <= down) {
                        if (!broken.contains((down + 10) % 10)) break;
                        down--;
                    }
                    int tempS = upper - digit;
                    int tempS2 = digit - down;
                    digit = (tempS < tempS2) ? upper % 10 : (down + 10) % 10;
                }
                temp += (digit * num);
                sub2++;
                val /= 10;
                num *= 10;
            } while (0 < val);
        }

        sub2 += Math.abs(temp-target);
        // 2-2. 차이
        int result = 0;
        if( temp == 0) result = sub;
        else {
            result = (sub < sub2)? sub : sub2;
        }
        System.out.println(result);
    }
}