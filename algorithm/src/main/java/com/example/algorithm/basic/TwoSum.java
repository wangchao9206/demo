package com.example.algorithm.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: chao1.wang
 * @Date: 17:01 2022/1/28
 * @Desc: 输入一个整数型数组num[]和一个整数target,输出两个和为target的整数。
 */
public class TwoSum {

    private static Logger logger = LoggerFactory.getLogger(TwoSum.class);

    /**
     * 暴力破解，两次for循环，时间复杂度为O(n^2)
     * @param arr
     * @param target
     * @return
     */
    public static int[] bruteForce(int[] arr, int target) {
        long start = System.currentTimeMillis();
        if (null == arr || arr.length < 2) {
            return new int[0];
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int targetFirst = arr[i];
            for (int j = 0; j < arr.length - 1; j++) {
                int targetSecond = arr[j];
                if (targetFirst + targetSecond == target) {
                    long end = System.currentTimeMillis();
                    logger.info("bruteForce consume time:{},array value:{} and {}", end - start, targetFirst, targetSecond);
                    return new int[] {targetFirst, targetSecond};
                }
            }
        }

        return new int[0];
    }

    /**
     * 用HashSet记住当下数字num，采用api判断是否有target-num的值存在，存在即返回，不存在，依次判断
     * 时间复杂度：一次for循环O(n),set.contains是O(1),set.add是O(1)
     * 空间复杂度：可能Set一遍都没有匹配的值，消耗了O(n)的空间
     * @param arr
     * @param target
     * @return
     */
    public static int[] hashSet(int[] arr, int target) {
        long start = System.currentTimeMillis();
        if (null == arr || arr.length < 2) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<Integer>();
        for (int num:arr) {
            int point = target - num;
            if (set.contains(point)) {
                long end = System.currentTimeMillis();
                logger.info("hashSet consume time:{},array value:{} and {}", end - start, num, point);
                return new int[] {num, point};
            }
            set.add(num);
        }
        return new int[0];
    }

    /**
     * 采用指针，通过移动左右指针来减少空间消耗
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     * 缺点：改变了原数组的位置
     * @param nums
     * @param target
     * @return
     */
    public static int[] sort(int[] nums, int target) {
        long start = System.currentTimeMillis();
        if (null == nums || nums.length < 2) {
            return new int[0];
        }
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int num = nums[right] + nums[left];
            if (num == target) {
                long end = System.currentTimeMillis();
                logger.info("sort consume time:{},array value:{} and {}", end - start, nums[left], nums[right]);
                return new int[] {left, right};
            }
            if (num > target) {
                // 当左右指针的值之和大于目标整数
                right--;
            }
            if (num < target) {
                left++;
            }
        }
        return new int[0];
    }

    /**
     * 输出角标
     * @param nums
     * @param target
     * @return
     */
    public static int[] getIndex(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i=0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[] {hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = {2,4,5,7,9,2,3,4,5,6,1,2,5,5,3,1,3,4,0,1,4,1,24,24,1,4,1};
        int target = 24;
        int[] sort = sort(nums, target);
        System.out.println(sort.toString());
        int[] ints = bruteForce(nums, target);
        System.out.println(ints.toString());
        int[] ints1 = hashSet(nums, target);
        System.out.println(ints1.toString());
    }
}
