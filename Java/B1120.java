import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1,1,0,0}; // 상, 하, 좌, 우
    static int[] dc = {0,0,-1,1}; // 상, 하, 좌, 우
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        char[] A = st.nextToken().toCharArray();
        char[] B = st.nextToken().toCharArray();
        int gap = B.length - A.length + 1, min = 50;

        for(int i = 0 ; i < gap ; i++){
            int temp = 0;
            for(int j = 0; j < A.length ; j++){
                if(A[j] != B[j+i]) temp++;
            }
            min = (temp < min)?temp: min;
        }
        System.out.println(min);
    }
}
