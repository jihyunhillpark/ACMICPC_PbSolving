import java.util.Scanner;

public class MakeOne {

	public static void main(String[] args) {
		// input
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ret[][] = new int[n][3];

		// DP - bottom up
		// step1
		int cnt = 0;
		ret[0][0] = n;

		// step2 and over
		while(cnt < n && ret[cnt][0] !=1 && ret[cnt][1] !=1 && ret[cnt][2] !=1) {
			int min = Integer.MAX_VALUE;
			cnt++;
			for(int i = 0 ; i < 3 ; i++) {
				int pre = ret[cnt-1][i];
				if( pre != 0 ) {
					int curr = 0;
					// 1 - 3으로 나눠지는 경우 check
					if( pre%3 == 0) {
						curr = ret[cnt][0];
						if(curr == 0) ret[cnt][0] = pre/3;
						else ret[cnt][0] = (curr < pre/3)? curr : pre/3;
					}
					// 2 - 2로 나눠지는 경우 check
					if( pre%2 == 0) {
						curr = ret[cnt][1];
						if(curr == 0) ret[cnt][1] = pre/2;
						else ret[cnt][1] = (curr < pre/2)? curr : pre/2;
					}
					// 3 - -1하는 경우
					if(pre-1 < min ) min= pre-1;
				}
			}
			ret[cnt][2] = min;
		}
		// output
		System.out.println(cnt);
		sc.close();
	}
}
