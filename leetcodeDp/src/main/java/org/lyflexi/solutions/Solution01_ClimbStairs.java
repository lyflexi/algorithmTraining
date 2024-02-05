package org.lyflexi.solutions;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/5 13:25
 */

/*爬楼梯: dp：  f(x) = f(x-1)+f(x-2),求f(x),   已知初始条件为f(1)=1，f(2)=2


假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

示例 1：
输入：n = 2
输出：2
解释：有两种方法可以爬到楼顶。
1. 1 阶 + 1 阶
2. 2 阶

示例 2：
输入：n = 3
输出：3
解释：有三种方法可以爬到楼顶。
1. 1 阶 + 1 阶 + 1 阶
2. 1 阶 + 2 阶
3. 2 阶 + 1 阶

*/
public class Solution01_ClimbStairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println(climbStairs(n));
    }

    public static int climbStairs(int n) {

        if (n==1){
            return 1;
        }

        if (n==2){
            return 2;
        }

        int x = 1,y=2,z=0;
        //前面已经计算过2次，总共计算1~n,因此再计算n-2次即可，i并没有实际意义
        for (int i = 0; i < n-2; i++) {

            z = x+y;
            x=y;
            y=z;
        }

        return z;
    }
}
