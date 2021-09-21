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
        int uSub = 0, dSub = 0;
        boolean uDone = false, dDone = false;
        int count1 = 0, count2 = 0;// 자릿수 확인
        //아래로 가면서 가장 가까운애 찾기
        int down = target;
        while(!dDone && 0 <= down){
            int val = down;
            do {
                // 2-1. 한번에 target에 가장 가까운 수로 이동하기
                int digit = val % 10;
                if (broken.contains(digit)) break;// 고장난 숫자일 경우 가장 가까운 수 찾기
                val /= 10;
            } while (0 < val);
            if(val == 0 && !broken.contains(down)) {
                count1 = getCount(down);
                dDone = true;
            }
            else {
                down--;
                if(0 <= down) dSub++;
            }
        }
        // 위로 가면서 가장 가까운 애 찾기
        int up = target;
        while(!uDone && up < 1000000){
            int val = up;
            do {
                // 2-1. 한번에 target에 가장 가까운 수로 이동하기
                int digit = val % 10;
                if (broken.contains(digit)) break;// 고장난 숫자일 경우 가장 가까운 수 찾기
                val /= 10;
            } while (0 < val);
            if(val == 0 && !broken.contains(up)){
                uDone = true;
                count2 = getCount(up);
            }
            else {
                up++;
                uSub++;
            }
        }
        // 2-2. 차이
        int sub2 = 1000000;
        if( uDone ) sub2 = uSub + count2;// 위에서 발견한 경우
        if(0 <= down && (dSub+count1) < sub2) sub2 = dSub + count1; // 아래에서 발견한 경우,
        int result = ( sub2 < sub)? sub2: sub;
        System.out.println(result);
    }

    private static int getCount(int target) {
        int num = 0;
        if(target == 0) return 1;
        while (0 < target){
            target/=10;
            num++;
        }
        return num;
    }
}