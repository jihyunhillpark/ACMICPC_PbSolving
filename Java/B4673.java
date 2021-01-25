import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int limit = 10000;
		int[] visited = new int[10001];
		
		for(int i = 1; i <= limit ; i++) {
			int dn = i;
			for(int j = dn; 0 < j ; j/=10) 
				dn += (j % 10);
			if(dn <= limit) visited[dn] = 1;
		}
		for(int i = 1; i <= limit ; i++) {
			if(visited[i] == 0) bw.write(Integer.toString(i)+"\n");
		}
		bw.flush();
		bw.close();
	}
}
