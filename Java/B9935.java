import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] input = in.readLine().toCharArray(); // target string
        char[] cmp = in.readLine().toCharArray();
        int last = cmp.length-1;
        Stack<Character> mismatchedStack = new Stack<>();

        for(int i = 0; i < input.length ; i++){
            if(input[i] == cmp[last] && cmp.length-1 <= i) {
                int offset = 1;
                char[] tmp = new char[cmp.length];
                tmp[last] = cmp[last];
                for(; offset <= last && !mismatchedStack.isEmpty() ; offset++){ // 뒤에서부터 비교하는데 offset은 몇 칸 떨어졌는지에 대한 정보
                    if( mismatchedStack.peek() != cmp[last-offset]) break; // stack에 있는 바로 직전에 있는 다르다면 비교 중지
                    else{
                        tmp[last-offset] = mismatchedStack.pop(); //stack에서 pop하고, tmp 배열 끝에서 부터 매핑하여 저장한다.
                    }
                }
                if( --offset < last ) { //다른 경우 - 다시 스텍에 집어 넣는다..
                    for (; 0 <= offset; offset--) { // 앞에서부터 다시 stack에 차례대로 넣는다.
                        mismatchedStack.push(tmp[last - offset]);
                    }
                }
            } else mismatchedStack.push(input[i]);
        }

        if(0 < mismatchedStack.size() ){
            char[] ret = new char[mismatchedStack.size()];
            for(int i = ret.length -1 ; 0 <= i; i-- ){
                ret[i] = mismatchedStack.pop();
            }
            System.out.println(ret);
        }
        else System.out.println("FRULA");
    }
}
