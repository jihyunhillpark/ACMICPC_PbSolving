import java.io.Buffe
        String s = st.nextToken();
        A = new int[s.length()];
        for (int i = 0; i < s.length(); i++) A[i] = s.charAt(i) - '0';
        N = A.length;
        int B = Integer.parseInt(st.nextToken());

        // next permutation
        int max = -1;
        Arrays.sort(A);
        do{
            if(A[0] !=0){
                int temp=0;
                for(int i = 0; i < N; i++)
                    temp += A[i]*(int)Math.pow(10, N-1-i);
                if(temp < B) max = temp;
                else break;}
        }while(nextPermutation());
        System.out.println(max);
    }
    private static boolean nextPermutation() {
        int i = N-1;
        while( i > 0 && A[i-1] >= A[i]) --i;

        if(i ==0) return false;

        int j = N-1;
        while(A[i-1] >= A[j]) --j;
        swap(i-1,j);

        int k = N-1;
        while(i < k){
            swap(i++,k--);
        }
        return true;
    }

    private static void swap(int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}