import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PostFixWithBracket {
	static int p1 = 1, p2 = 2;
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] input = in.readLine().toCharArray();
		Stack<Character> stack = new Stack<Character>(); //연산자를 담을 스택
		String ret = "";

		for(int i=0,size = input.length ; i < size ; i++) {
			char ch = input[i];
			char pre = 0;
			switch(ch) {
			case '+' : case '-':
				if(!stack.isEmpty()) {
					while(!stack.isEmpty() && (pre=stack.peek()) != '(' ) {
						ret += stack.pop();
					}
				}
				stack.add(ch);
				break;
			case '*' : case '/':
				if(!stack.isEmpty()) {
					while(!stack.isEmpty() && (pre = stack.peek())!= '(' && (pre == '/' || pre == '*')) {
						ret += stack.pop();
					}
				}
				stack.add(ch);
				break;
			case '(':
				stack.add(ch);
				break;
			case ')':
				char temp = 0;
				while((temp = stack.pop()) != '(')
					ret += temp;
				break;
			default : ret += input[i];
			}
		}

		while(!stack.isEmpty())
			ret += stack.pop();
		System.out.println(ret);
	}
}
