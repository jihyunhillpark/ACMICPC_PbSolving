import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine()), i, j;
        Stack<Integer> stack = new Stack<>();
        for(i = 0 ; i < N ; i++){
            char input[] = in.readLine().toCharArray();
            for( j = 0 ; j < input.length; j++){
                if(input[j] == '(') stack.push(j);
                else{
                    if( stack.isEmpty()) break;
                    stack.pop();
                }
            }
            if(j < input.length ) sb.append("NO");
            else if( !stack.isEmpty()) sb.append("NO");
            else sb.append("YES");
            sb.append("\n");
            stack.removeAllElements();
        }
        System.out.println(sb);
    }
}
