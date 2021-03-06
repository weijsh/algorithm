import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*
给定一个整数数组nums和一个整数目标值target，请你在该数组中找出和为目标值target的那两个整数，
并返回它们的数组下标。
*/

public class 两数之和 {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    public static void main(String[] args) {
        int []nums = {2,7,11,15};
        int target = 22;
        System.out.println(Arrays.toString(twoSum(nums,target)));
    }
}
