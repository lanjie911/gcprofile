package cn.bj.brook.algorithm;

/**
 * 本类是负责罗马数字和阿拉伯数字的转换类
 */
public class RomaNumber {

    /**
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     *
     * 示例 1:
     *
     * 输入: 3
     * 输出: "III"
     * 示例 2:
     *
     * 输入: 4
     * 输出: "IV"
     * 示例 3:
     *
     * 输入: 9
     * 输出: "IX"
     * 示例 4:
     *
     * 输入: 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     * 示例 5:
     *
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/integer-to-roman
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        if(num <= 0 || num > 3999){
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int incre = 1;
        while(num > 0){
            int value = num % 10;
            switch(incre) {
                case 1:
                    handleUnits(value,sb);
                    break;
                case 2:
                    handleTens(value,sb);
                    break;
                case 3:
                    handleHundreds(value,sb);
                    break;
                case 4:
                    handleThousands(value,sb);
                    break;
                default:
                    break;
            }
            incre++;
            num = num / 10;
        }
        return sb.toString();
    }

    private void handleUnits(int value, StringBuilder sb){
        if(value < 4){
            for(int i=0;i<value;i++){
                sb.insert(0,"I");
            }
        }else if(value == 4){
            sb.insert(0,"IV");
        }else if(value == 5){
            sb.insert(0,"V");
        }else if(value > 4 && value < 9){
            int cyc = value - 5;
            for(int i=0;i<cyc;i++){
                sb.insert(0,"I");
            }
            sb.insert(0,"V");
        }else if(value == 9){
            sb.insert(0,"IX");
        }
    }

    private void handleTens(int value, StringBuilder sb){
        if(value < 4){
            for(int i=0;i<value;i++){
                sb.insert(0,"X");
            }
        }else if(value == 4){
            sb.insert(0,"XL");
        }else if(value == 5){
            sb.insert(0,"L");
        }else if(value > 4 && value < 9){
            int cyc = value - 5;
            for(int i=0;i<cyc;i++){
                sb.insert(0,"X");
            }
            sb.insert(0,"L");
        }else if(value == 9){
            sb.insert(0,"XC");
        }
    }

    private void handleHundreds(int value, StringBuilder sb){
        if(value < 4){
            for(int i=0;i<value;i++){
                sb.insert(0,"C");
            }
        }else if(value == 4){
            sb.insert(0,"CD");
        }else if(value == 5){
            sb.insert(0,"D");
        }else if(value > 4 && value < 9){
            int cyc = value - 5;
            for(int i=0;i<cyc;i++){
                sb.insert(0,"C");
            }
            sb.insert(0,"D");
        }else if(value == 9){
            sb.insert(0,"CM");
        }
    }

    private void handleThousands(int value, StringBuilder sb){
        if(value < 4){
            for(int i=0;i<value;i++){
                sb.insert(0,"M");
            }
        }else{
            throw new RuntimeException("Error over flow size.");
        }
    }

    /**
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     *
     * 示例 1:
     *
     * 输入: "III"
     * 输出: 3
     * 示例 2:
     *
     * 输入: "IV"
     * 输出: 4
     * 示例 3:
     *
     * 输入: "IX"
     * 输出: 9
     * 示例 4:
     *
     * 输入: "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 示例 5:
     *
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/roman-to-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        // 本题是intToRoman的逆运算
        // 因为不超过3999，所以可以使用一些截断的解题技巧
        // 比如循环到1-3999，哪个字符串相等就用哪个，这是一种变态的解题方法，但是可行，在复杂度3999的情况下是一个不错的选择
        // 但是更好的方法是逆运算就是拆解字符串
        if(s.equals("")){
            return 0;
        }
        int result = 0;
        for(int i=s.length()-1;i>=0;){
            char c = s.charAt(i);
            if(c == 'I'){
                result += 1;
                i--;
            }else if(c == 'V'){
                result += 5;
                while((--i)>=0 && (c=s.charAt(i)) == 'I'){
                    result--;
                }
            }else if(c == 'X'){
                result += 10;
                if((--i)>=0 && (c=s.charAt(i)) == 'I'){
                    result--;
                    i--;
                }
            }else if(c == 'L'){
                result += 50;
                if((--i)>=0 && (c=s.charAt(i)) == 'X'){
                    result -= 10;
                    i--;
                }
            }else if(c == 'C'){
                result += 100;
                if((--i)>=0 && (c=s.charAt(i)) == 'X'){
                    result -= 10;
                    i--;
                }
            }else if(c == 'D'){
                result += 500;
                if((--i)>=0 && (c=s.charAt(i)) == 'C'){
                    result -= 100;
                    i--;
                }
            }else if(c == 'M'){
                result += 1000;
                if((--i)>=0 && (c=s.charAt(i)) == 'C'){
                    result -= 100;
                    i--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        RomaNumber r = new RomaNumber();
        for(int i=0;i<1995;i++){
            String roman = r.intToRoman(i);
            int k = r.romanToInt(roman);
            System.out.println(roman+"=>"+k);
        }
    }
}
