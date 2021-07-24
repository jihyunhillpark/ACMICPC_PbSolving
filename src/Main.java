import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine()), x , y, count = 0, pre=0;
        StringTokenizer st;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(in.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            if (!stack.isEmpty()) {
                if(y < stack.peek()){
                    count++;
                    stack.pop();
                    if(!stack.isEmpty() && stack.peek() == y ) continue;
                    if( y > 0) stack.push(y);
                }
                else if( y > stack.peek()) stack.push(y);
            }
            else if( 0 < y ) stack.push(y);
        }
        while(!stack.isEmpty()){
            stack.pop();
            count++;
        }
        System.out.println(count);
    }
}
