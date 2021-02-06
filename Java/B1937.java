import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] rooms;
	static int[][] memo;
	static int[] dx = {-1,1,0,0}; //상하좌우
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;
		int N = 0;

		//입력
		N = Integer.parseInt(in.readLine());
		rooms = new int[N+2][N+2];
		memo = new int[N+2][N+2];
		int max = 0, temp;

		for(int j = 1 ; j <= N ; j++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int k = 1; k <= N ; k++) {
				rooms[j][k] = Integer.parseInt(st.nextToken());
			}
		}

		//값 찾기
		for(int j = 1 ; j <= N ; j++) {
			for(int k = 1; k <= N ; k++) {
				if( 0 < memo[j][k]) continue;
				temp = search(j ,k);
				max = (max < temp )? temp: max;
			}
		}
		//출력
		System.out.println(max);

	}
	private static int search(int i, int j) {
		int deltaX, deltaY;
		int temp = 1;
		if(memo[i][j]!= 0) return memo[i][j]; //머선 129... 이거하나로..
		memo[i][j] = 1;
		for(int idx = 0 ; idx < 4 ; idx++) {
			deltaX = i+dx[idx];
			deltaY = j+dy[idx];
			if( rooms[i][j] < rooms[deltaX][deltaY]){
				temp = 1 + search(deltaX, deltaY);
				if(temp > memo[i][j]) memo[i][j] = temp;
			}
		}
		return memo[i][j];
	}
}
