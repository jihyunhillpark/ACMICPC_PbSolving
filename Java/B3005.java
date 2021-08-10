import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        String fast = "zzzzzzzzzzzzzzzzzzzz";
        char crossWord[][] = new char[R][C];

        for(int i = 0 ; i < R ; i++)
            crossWord[i] = in.readLine().toCharArray();

        // 행부터 차근차근
        for(int i = 0 ; i < R ; i++){
            String temp = "";
            for(int j = 0 ; j < C ; j++){
                if( crossWord[i][j] == '#' || (j == C-1) ){
                    if(j == C-1 && crossWord[i][j] != '#') temp += crossWord[i][j]; // #가 아닌 마지막 문자는 temp에 붙여줘야 함
                    if( 1 < temp.length() && temp.compareTo(fast) < 0) {
                        fast = temp;
                    }
                    temp = "";
                }
                else temp += crossWord[i][j];
            }
        }

        //열부터 차근차근
        for(int j = 0 ; j < C ; j++){
            String temp = "";
            for(int i = 0 ; i < R ; i++){
                if( crossWord[i][j] == '#' || (i == R-1) ){
                    if(i == R-1 && crossWord[i][j] != '#') temp += crossWord[i][j];
                    if( 1 < temp.length() && temp.compareTo(fast) < 0) {
                        fast = temp;
                    }
                    temp = "";
                }
                else temp += crossWord[i][j];
            }
        }
        System.out.println(fast);
    }
}
