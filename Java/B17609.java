
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 입력
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
        for(int i = 0 ; i< T ; i++){
            char input[] = in.readLine().toCharArray();
            int left = 0, right = input.length-1;
            int type = 0;
            while(left < right){
                if(input[left] != input[right]){
                    boolean leftCheck = removeCheck(input, left+1, right);
                    boolean rightCheck = removeCheck(input, left, right-1);
                    if( leftCheck || rightCheck) type = 1;
                    else type = 2;
                    break;
                }
                else{
                    left++;
                    right--;
                }
            }
            sb.append(type);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    private static boolean removeCheck(char[] input, int left, int right) {
        while( left < right){
            if( input[left] != input[right]) return false;
            left++;
            right--;
        }
        return true;
    }
}