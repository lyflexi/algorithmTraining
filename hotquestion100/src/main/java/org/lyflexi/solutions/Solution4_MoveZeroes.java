package org.lyflexi.solutions;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/1/11 10:23
 */
public class Solution4_MoveZeroes {
//    2024.1.11
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        moveZeroes(array);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }
//解法二：快慢指针一次遍历?不太理解
//    public void moveZeroes(int[] nums) {
//        //快慢指针，慢指针记录非0，快指针记录0
//        int slow=0,quick=0;
//
//        int n = nums.length;
//        //快指针也从0开始，因此可以覆盖到数组每一个元素
//        while(quick<n){
//            int temp = 0;
//            //右指针不等于0则左右交换，等于0直接跳过
//            if (nums[quick]!=0){
//                temp = nums[quick];
//                nums[quick] = nums[slow];
//                nums[slow] = temp;
//                slow++;
//            }
//            quick++;
//        }
//    }
//    解法一: 快慢指针两次遍历
    public static void moveZeroes(int[] nums) {
        //快慢指针，慢指针记录非0，快指针记录0
        int slow=0,quick=0;

        int n = nums.length;
        //右指针从0开始，因此可以覆盖到数组每一个元素
        while(quick<n){

            //右指针不等于0则覆盖左指针，等于0直接跳过
            if (nums[quick]!=0){
                nums[slow] = nums[quick];
                slow++;
            }
            quick++;
        }
        //从left开始，剩余赋0
        for (int i = slow; i < n; i++) {
            nums[i] = 0;
        }

    }



/*
//官方题解
public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }*/
}
