import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int house_num = Integer.parseInt(st.nextToken());
		int[][] inputs = new int[house_num][];
		int[][] min_costs = new int[house_num][];
		int[] last_house = new int[house_num];
		int R = 0, G = 1, B = 2;
		int min = Integer.MAX_VALUE;

		//입력받기
		for(int i = 0; i < house_num; i++) {
			String[] color_cost = (br.readLine()).split(" ");
			inputs[i] = new int[color_cost.length];
			min_costs[i] = new int[color_cost.length];

			int j = 0;
			for(String cost : color_cost) {
				inputs[i][j]= Integer.parseInt(cost);
				j++;
			}

			if(i == 0) {
				min_costs[0][R] = inputs[0][R];
				min_costs[0][G] = inputs[0][G];
				min_costs[0][B] = inputs[0][B];
			}
			else {
				min_costs[i][R] = inputs[i][R] + ((min_costs[i-1][G] < min_costs[i-1][B] )?
										min_costs[i-1][G] : min_costs[i-1][B]);
				min_costs[i][G] = inputs[i][G] + ((min_costs[i-1][R] < min_costs[i-1][B] )?
						min_costs[i-1][R] : min_costs[i-1][B]);
				min_costs[i][B] = inputs[i][B] + ((min_costs[i-1][G] < min_costs[i-1][R] )?
						min_costs[i-1][G] : min_costs[i-1][R]);
			}

		}
		//계산 및 출력
		for(int cost : min_costs[house_num-1])
			min = min < cost ? min : cost;

		bw.write(Integer.toString(min));
		bw.flush();
		bw.close();

	}

}
