package com.qiangws.demo.code;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : ComplementExample
 * @packageName : com.qiangws.demo.code
 * @createTime : 2025/4/3
 * @description :  TODO
 * 原码：最高位是符号位，其余位表示数值。直观但加法运算复杂。
 * 反码：正数反码与原码相同，负数反码是原码除符号位外所有位取反。简化了加法运算，但存在两个零。
 * 补码：正数补码与原码相同，负数补码是反码加 1。简化了加法运算，只存在一个零，是计算机中最常用的表示方法。
 *
 * 正数：从 0000（0）到 0111（7）
 * 负数：从 1000（-8）到 1111（-1）
 * 正数的表示
 * 对于正数，原码、反码和补码都是相同的。
 *
 * 2 的原码、反码、补码：0010
 * 负数的表示
 * 对于负数，原码、反码和补码有不同的表示方法。
 *
 * -2 的原码：1010
 * -2 的反码：1101（原码除符号位外所有位取反）
 * -2 的补码：1110（反码加 1）
 */
public class ComplementExample {

    public static void main(String[] args) {
        int number = -2;
        int complement = ~number + 1; // 补码计算：反码加 1
        System.out.println("Number: " + number);
        System.out.println("Complement: " + complement);
    }

}
