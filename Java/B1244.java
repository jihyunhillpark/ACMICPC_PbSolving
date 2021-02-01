import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class HwBoJ1244 {

	static int[] switches;

	private static void girlSwitch(int idx, int offset) {
		//base case
		if((idx - offset) < 0 || (idx + offset) >= switches.length ) return;
		//inductive case
		if(switches[idx-offset] == switches[idx+offset]) {
			switches[idx-offset] = switches[idx+offset] = 1 - switches[idx+offset];
			girlSwitch(idx, offset+1);
		}
		else return;
	}
	private static void boySwitch(int idx, int mul) {
		//base case
		if(switches.length < mul*idx) return;
		//inductive case
		switches[mul*idx-1] = 1 - switches[mul*idx-1];
		boySwitch(idx, mul+1);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int length = Integer.parseInt(in.readLine());
		switches = new int[length];

		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		for(int i = 0; i < length ; i++) {
			switches[i] = st.nextToken().charAt(0)-'0';
		}

		int studentNum = Integer.parseInt(in.readLine());
		int gender,idx;

		for(int i = 0; i < studentNum ; i++) {
			st = new StringTokenizer(in.readLine()," ");
			gender  = st.nextToken().charAt(0) - '0';
			idx  = Integer.parseInt(st.nextToken());

			if(gender == 1 ) boySwitch(idx,1);
			else girlSwitch(idx-1,0);

		}

		for(int i = 0 ; i < switches.length ; i++) {
			System.out.print(switches[i] + " ");
			if(i%20 == 19)  System.out.println();
		}
	}

}
