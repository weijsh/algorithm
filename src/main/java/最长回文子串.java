/*
    https://www.cxyxiaowu.com/
*/

public class 最长回文子串 {
    public static void main(String[] args) {
        String s = "bababad";
        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome2(s));
        System.out.println(longestPalindrome3(s));
        System.out.println(longestPalindrome4(s));
    }


    //暴力解法
    public static String longestPalindrome(String s){
        int len = s.length();
        if(len<2){
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        //s.charAt(i)每次都会检查数组下标越界，因此先转换字符数组
        char[] chars = s.toCharArray();
        //枚举所有长度严格大于1 的子串
        for(int i=0;i<len-1;i++){
            for(int j= i+1;j<len;j++){
                if(j-i+1>maxLen&&palindromeSubstring(chars,i,j)){
                    maxLen = j-i+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);
    }
    public static boolean palindromeSubstring(char[] chars,int left,int right){
        while(left<right){
            if(chars[left]!=chars[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    //中心扩散
    public static String longestPalindrome2(String s){
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        String res = s.substring(0, 1);
        // 中心位置枚举到 len - 2 即可
        for (int i = 0; i < len - 1; i++) {
            String oddStr = centerSpread(s, i, i);
            String evenStr = centerSpread(s, i, i + 1);
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLenStr.length() > maxLen) {
                maxLen = maxLenStr.length();
                res = maxLenStr;
            }
        }
        return res;
    }
    private static String centerSpread(String s, int left, int right) {
        // left = right 的时候，此时回文中心是一个空隙，回文串的长度是奇数
        // right = left + 1 的时候，此时回文中心是任意一个字符，回文串的长度是偶数
        int len = s.length();
        int i = left;
        int j = right;
        while (i >= 0 && j < len) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        // 这里要小心，跳出 while 循环时，恰好满足 s.charAt(i) != s.charAt(j)，因此不能取 i，不能取 j
        return s.substring(i + 1, j);
    }
    //动态规划
    public static String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int start = 0;

        //dp[i][j]表示s[i..j]是否为回文串
        boolean [][] dp = new boolean[len][len];
        for(int i =0;i<len;i++){
            dp[i][i] = true;
        }
        char[] chars = s.toCharArray();
        for(int j =1;j<len;j++){
            for(int i = 0 ;i<j;i++){
                if(chars[i]!=chars[j]){
                    dp[i][j] = false;
                }else{
                    if(j-i<3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] =  dp[i+1][j-1];
                    }
                }
                //只要dp[i][j]==true成立,就表示子串s[i..j]是回文串，此时记录长度和起始位置
                if(dp[i][j] && j-i+1>maxLen){
                    maxLen = j-i+1;
                    start = i;
                }
            }
        }
        return s.substring(start,start+maxLen);
     }


    //Manacher 算法
    public static String longestPalindrome4(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        String str = addBoundaries(s, '#');
        int sLen = 2 * len + 1;
        int maxLen = 1;

        int start = 0;
        for (int i = 0; i < sLen; i++) {
            int curLen = centerSpread(str, i);
            if (curLen > maxLen) {
                maxLen = curLen;
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }
    private static int centerSpread(String s, int center) {
        // left = right 的时候，此时回文中心是一个空隙，回文串的长度是奇数
        // right = left + 1 的时候，此时回文中心是任意一个字符，回文串的长度是偶数
        int len = s.length();
        int i = center - 1;
        int j = center + 1;
        int step = 0;
        while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            step++;
        }
        return step;
    }
    /**
     * 创建预处理字符串
     *
     * @param s      原始字符串
     * @param divide 分隔字符
     * @return 使用分隔字符处理以后得到的字符串
     */
    private static String addBoundaries(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(divide);
            stringBuilder.append(s.charAt(i));
        }
        stringBuilder.append(divide);
        return stringBuilder.toString();
    }
}
