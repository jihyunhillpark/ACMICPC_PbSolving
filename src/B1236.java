import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1236 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int[] rows = new int[row]; 
		int[] cols = new int[col]; 
		String[] castle = new String[row];
		int row_sum = 0, col_sum = 0;
		
		for(int i = 0; i < row; i++) {
			castle[i] = br.readLine();
			for(int j = 0 ; j < col ; j++) {
				if(castle[i].charAt(j)=='X') {
					if( rows[i] == 0 ) {
						rows[i] = 1;
						row_sum++;
					}
					
					if( cols[j] == 0 ) {
						cols[j] = 1;
						col_sum++;	
					}
				}
				
			}
		}
		int rowsToFilled = row-row_sum;
		int colsToFilled = col-col_sum;
		int ret = (rowsToFilled > colsToFilled)?rowsToFilled:colsToFilled;
		
		bw.write(String.valueOf(ret));
		bw.flush();
		bw.close();
	}

}
