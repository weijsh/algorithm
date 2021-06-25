import java.util.*;

/*
给定一个字符串,请你找出其中不含有重复字符的最长子串的长度
*/
public class 无重复字符的最长子串 {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring4(s));
    }




    /*符的 ASCII 码值作为数组的下标, 数组存储该字符所在字符串的位置。适用于字符集比较小的情况,
     因为我们会直接开辟和字符集等大的数组。*/
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1; // (下标 + 1) 代表 i 要移动的下个位置
        }
        return ans;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1); // 下标 + 1 代表 i 要移动的下个位置
        }
        return ans;
    }

    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
    public static int lengthOfLongestSubstring4(String s) {
        if(null == s || "".equals(s)){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        String[] strArr = new String[len];

        strArr[0] = chars[0] + "";
        for(int i = 1; i < len; i++){
            if(strArr[i - 1].contains(chars[i] + "")){
                strArr[i] = strArr[i - 1].substring(strArr[i - 1].indexOf(chars[i] + "") + 1) + chars[i] + "";
            }else{
                strArr[i] = strArr[i - 1] + chars[i];
            }
        }
        int maxLen = 0;

        for(String tmp : strArr){
            if(tmp.length() > maxLen){
                maxLen = tmp.length();
            }
        }
        return maxLen;
    }

}
