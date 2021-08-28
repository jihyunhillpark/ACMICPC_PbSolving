import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        int caseN = 0;

        while(flag){
            HashSet<Integer> root = new HashSet<Integer>();
            HashSet<Integer> children = new HashSet<Integer>();
            int total = 0, u, v;
            boolean isTree = true;
            while(0 <= caseN){
                u = sc.nextInt();
                v = sc.nextInt();
                if(u ==-1 && v == -1) {
                    flag = false;
                    break;
                }
                if(u == 0 && v == 0 ) {
                    // tree 판별
                    caseN++;
                    if(1 < root.size() || (total+1) != root.size() + children.size() )
                        isTree = false;
                    if( isTree || total== 0) sb.append("Case "+caseN + " is a tree.\n");
                    else sb.append("Case "+caseN + " is not a tree.\n");
                    break;
                }
                else if(isTree) {
                    total++;
                    if(!children.contains(u)) root.add(u); //root은 일단 넣고 들어오는 노드가 될 때 빼면 됨.
                    if(children.contains(v)) isTree = false; // 들어 오는게 두 개이상..
                    else{
                        if(root.contains(v)) root.remove(v);
                        children.add(v);
                    }
                }
            }
        }
        System.out.println(sb);
    }
}


