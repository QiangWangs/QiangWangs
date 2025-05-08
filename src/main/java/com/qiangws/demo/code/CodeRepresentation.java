package com.qiangws.demo.code;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : CodeRepresentation
 * @packageName : com.qiangws.demo.code
 * @createTime : 2025/4/3
 * @description :  TODO
 */
public class CodeRepresentation {

    /**
     * Number: 2
     * Binary (原码): 10
     * Binary (反码): 11111101
     * Binary (补码): 11111110
     *
     * Negative Number: -2
     * Binary (原码): 11111110
     * Binary (反码): 11111101
     * Binary (补码): 11111110
     * @param args
     *
     * 在 Java 中，整数是 32 位的，所以负数的补码、反码和原码会显示为 32 位二进制数，包括符号位。
     * 使用 Integer.toBinaryString 方法时，输出的二进制字符串不会包含前导零，所以对于正数，输出的二进制字符串可能不会显示完整的 32 位。
     * 为了得到完整的 32 位二进制字符串，可以使用 String.format 方法，例如：String.format("%32s", Integer.toBinaryString(number)).replace(' ', '0')。
     * 正数：从 0000（0）到 0111（7）
     * 负数：从 1000（-8）到 1111（-1）
     *
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

    public static void main(String[] args) {
        int number = 2; // 正数示例
        int negativeNumber = -2; // 负数示例

        System.out.println("Number: " + number);
        System.out.println("Binary (原码): " + Integer.toBinaryString(number));
        System.out.println("Binary (反码): " + Integer.toBinaryString(~number));
        System.out.println("Binary (补码): " + Integer.toBinaryString(~number + 1));

        System.out.println("\nNegative Number: " + negativeNumber);
        System.out.println("Binary (原码): " + Integer.toBinaryString(negativeNumber & 0xFF));
        System.out.println("Binary (反码): " + Integer.toBinaryString(~negativeNumber & 0xFF));
        System.out.println("Binary (补码): " + Integer.toBinaryString(negativeNumber & 0xFF));
    }
}
