public class 整数反转 {
    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
        System.out.println(reverse2(-2147483647));
        System.out.println((2<<30)-1);
        System.out.println((-2<<30)-1);
    }
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }
    public static int reverse2(int x) {
        if (x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) {
            return 0;
        }
        String s = "";
        if(x<0){
            x = x*(-1);
            s = x+"-";
        }else{
            s = x+"";
        }

        char[] chars = s.toCharArray();
        StringBuilder ret = new StringBuilder();
        for(int i = s.length()-1;i>=0;i--){
            ret.append(chars[i]);
        }
        if (Long.parseLong(ret.toString()) < Integer.MIN_VALUE
                || Long.parseLong(ret.toString()) > Integer.MAX_VALUE) {
            return 0;
        }

        return Integer.parseInt(ret.toString());
    }
}
