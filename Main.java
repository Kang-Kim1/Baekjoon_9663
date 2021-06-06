import java.io.*;
import java.util.*;

public class MyClass {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static void input() {
        N = scan.nextInt();
        col = new int[N + 1];
    }

    static int N, ans;
    static int[] col;


    // 공격 가능 여부 확인을 위한 메소드
    static boolean attackable(int r1, int c1, int r2, int c2) {
        if(r1 == r2) return true;
        if(c1 == c2) return true;
        if(c1 - r1 == c2 - r2) return true;
        if(c1 + r1 == c2 + r2) return true;        
        
        return false;
    }

    static void rec_func(int row) {
        // 전체 row를 확인했을 경우 count
        if (row == N + 1) {
            ans++;
        } else {
            // 각 column에 대한 for문
            for(int i = 1 ; i < N + 1; i++) {
                boolean attackable = false;
                // 비교대상 row에 대한 for문
                for(int r = 1; r < row; r++) {
                    // 공격 가능한 위치인지 이미 Queen을 위치한 row와 비교
                    if(attackable(row, i, r, col[r])) {
                        attackable = true;
                    }
                }
                // 공격이 불가한 경우
                if(!attackable) {
                    // Queen 위치 후, 재귀 호출
                    col[row] = i;
                    rec_func(row + 1);
                    col[row] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        input();
        rec_func(1);
        System.out.println(ans);
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
