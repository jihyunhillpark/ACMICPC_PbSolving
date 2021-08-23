import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int train[] = new int[N];
        HashSet<Integer> set = new HashSet<>();
        int mask = (1 << Integer.SIZE -1) >> (20-1);
        int mask2 = (1 << Integer.SIZE -1) >> Integer.SIZE -1; //11111...1111
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(in.readLine(), " ");
            int command = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken())-1;
            switch (command){
                case 1:{
                    train[target] |= 1 << ( Integer.SIZE - Integer.parseInt(st.nextToken()));
                    break;
                }
                case 2:{
                    int pos = Integer.parseInt(st.nextToken());
                    int mask3 = 1 << (Integer.SIZE - pos);
                    mask3 ^= mask2;
                    train[target] &= mask3;
                    break;
                }
                case 3:{ // 이부분 실제로는 길이 20
                    train[target] = (train[target] >>> 1) & mask;
                    break;
                }
                case 4:{
                    train[target] <<= 1;
                    break;
                }
            }
        }
        for(int i = 0 ; i < N ; i++) set.add(train[i]);
        System.out.println(set.size());

    }
}
