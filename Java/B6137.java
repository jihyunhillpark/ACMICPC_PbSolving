import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine());
        char input[] = new char[N];

        for(int i = 0 ;i < N ; i++){
            input[i] = in.readLine().charAt(0);
        }

        // 처리
        int first = 0, last = N-1; // 현재 문자열 S의 첫문자와 마지막 문자의 인덱스 기록
        for(int i = 0 ; i < N ; i++){
            // System.out.println("current =" +  first + " " + last);
            if( input[first] < input[last] ) sb.append(input[first++]); //첫문자가 사전순으로 더 앞서는 경우
            else if(input[first] > input[last]) sb.append(input[last--]); //마지막 문자가 사전순으로 더 앞서는 경우
            else { // 비교하는 두 문자가 같은 경우 A == A ->
                int former = first; //first부터 두 글자가 달라질 때까지 앞에서부터 진행(순방향)
                int latter = last; //last부터 두 글자가 달라질 때까지 뒤에서부터 진행(역방향)
                while( former < latter && input[former] == input[latter]){ // 이부분 논의 필요
                    former++;
                    latter--;
                }
                if(former >= latter ) { //안에 있는 문자열이 대칭일 때
                    if( first == former ){ // S에 글자 하나만 남은 경우
                        sb.append(input[former]);
                        break;
                    }
                    else sb.append(input[first++]);
                }
                else { // 중간에 다른 문자를 발견했을 때
                    if (input[former] < input[latter]) sb.append(input[first++]);
                    else if (input[former] > input[latter]) sb.append(input[last--]);
                }
                //System.out.println(former + " and " + latter);
            }
            //System.out.println("next " + first " + input[first] + "last" + input[last]);
            if(i%80 == 79) sb.append("\n"); //80번째바다 개행
        }
        System.out.println(sb);
    }
}
