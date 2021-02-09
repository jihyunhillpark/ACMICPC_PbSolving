import java.util.Scanner;

class Main {

	static boolean[][] visited = new boolean[100][100];
	static int totalCnt;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int i = 1 ; i <= T ;i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int row = r +10;
			int col = c +10;
			for(int j = r ; j < row; j++) {
				for(int k = c; k < col; k++) {
					if(visited[j][k]) continue;
					totalCnt++;
					visited[j][k] = true;
				}
			}

		}
		System.out.print(totalCnt);
	}

}
