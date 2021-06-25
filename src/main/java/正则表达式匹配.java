public class 正则表达式匹配 {
    public static void main(String[] args) {
        System.out.println(isMatch("a","a*"));
    }


    //动态规划
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        for(int i = 0;i<m + 1;i++){
            for(int j = 0;j<n + 1;j++){
                System.out.print(f[i][j]+"\t");
            }
            System.out.println();
        }
        return f[m][n];
    }
    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }





    public static boolean isMatch2(String s, String p) {

        for(int i =0;i<p.length();i++){
            for(int j =0;j<s.length();j++){
                if(p.charAt(i)==p.charAt(j)||p.charAt(i)=='.'){


                }
            }
        }
        return false;

    }
}
