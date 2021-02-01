import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B17478 {

	static String[] chatBot = {"어느 한 컴퓨터공학과 학생이 유한 교수님을 찾아가 물었다.",
			"\"재귀함수가 뭔가요?\"",
			"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
			"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.",
			"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"",
			"\"재귀함수가 뭔가요?\"",
			"\"재귀함수는 자기 자신을 호출하는 함수라네\"",
			"라고 답변하였지."
			};

	private static void printChat(int rec, int curr, String added ) {
		if(rec == curr) {
			for(int i = 5; i < chatBot.length-1 ; i++)
				System.out.println( added + chatBot[i]);
		}
		else {
			if( curr == 0)
				System.out.println(chatBot[0]);
			for(int i = 1; i <= 4; i++) {
				System.out.println( added + chatBot[i]);
			}
			printChat(rec, curr+1, added+"____");
		}
		System.out.println( added + chatBot[chatBot.length-1]);

	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int rec = Integer.parseInt(in.readLine());
		printChat(rec,0,"");
	}
}
