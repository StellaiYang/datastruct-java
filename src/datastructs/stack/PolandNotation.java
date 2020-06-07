package datastructs.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        //完成将一个中缀表达式转为后缀表达式的功能
        //1. 1+((2+3)x4)-5 => 转换成 1 2 3 + 4 x + 5 -
        //2. 因为直接对字符串进行操作不方便，先将字符串 "1+((2+3)x4)-5" 转为 list
        //即 "1+((2+3)x4)-5" => ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]

        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list = " + list);

        //3.将得到的中缀表达式对应的list => 后缀表达式对应的list
        //即 ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]  => ArrayList[1,2,3,+,4,*,+,5,-]
        List<String> suffixExpressionList = parseSuffixExpressionList(list);
        System.out.println("后缀表达式对应的list = " + suffixExpressionList);

        System.out.printf("expression = %d",calculate(suffixExpressionList));

/*
        //先定义一个逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        //数字与符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //1.先将 "3 4 + 5 * 6 -" 放入ArrayList之中
        //2.将ArrayList 传递给一个方法，遍历ArrayList 配合栈完成计算

       List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList = " + rpnList);

        int result = calculate(rpnList);
        System.out.println("result = " + result);

        //4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        String suffixExpression2 = "4 5 * 8 - 60 + 8 2 / +";
        List<String> rpnList2 = getListString(suffixExpression2);
        System.out.println("rpnList2 = " + rpnList2);

        int result2 = calculate(rpnList2);
        System.out.println("result = " + result2);


        */

    }

    //即 ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]  => ArrayList[1,2,3,+,4,*,+,5,-]
    //将得到的中缀表达式对应的list => 后缀表达式对应的list
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两栈
        Stack<String> stack1 = new Stack<>();//符号栈
        //因为stack2在转化过程中并没有pop操作，所以直接使用list来替代
//        Stack<Stack> stack2 = new Stack<>();//存放中间结果栈
        List<String> stack2 = new ArrayList<>();

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入到s2
            if(item.matches("\\d+")){
                stack2.add(item);
            }else if(item.equals("(")){
                stack1.push(item);
            }else if(item.equals(")")){
                while (!stack1.peek().equals("(")){
                    stack2.add(stack1.pop());
                }
                stack1.pop();//将 ( 弹出s1栈，消除小括号
            }else {
                //当item的优先级小于等于栈顶运算符的优先时，将s1栈顶的运算符弹出并加入到s2中，再次与栈顶元素比较
                //缺少比较优先级高低的方法
                while (stack1.size() != 0 && Operation.getValue(stack1.peek()) >= Operation.getValue(item)){
                    stack2.add(stack1.pop());
                }
                //需要将item加入到
                stack1.push(item);
            }
        }

        //将stack1中的元素加入至stack2中
        while (stack1.size() != 0){
            stack2.add(stack1.pop());
        }
        return stack2;//因为存放在list，因此直接输出的结果就是逆波兰表达式
    }

    //方法将中缀表达式转为对应的list
    public static List<String> toInfixExpressionList(String s){
        //定义一个list，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0;//用于遍历中缀表达式字符串
        String str;//做对多位数的拼接
        char c;//每遍历到一个字符，就放入到c
        do{
            //如果c是一个非数字，就需要加入到ls
            if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                ls.add(Character.toString(c));
                i++;//后移
            }else {//是数字的话，考虑多位数的问题
                str = "";//先将str置成空串
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }

        }while (i < s.length());

        return ls;
    }

    //将逆波兰表达式，依次将数据和运算符放入ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        return Arrays.asList(split);
    }

    //完成对逆波兰表达式的计算
    public static int calculate(List<String> list) {
        //创建一个栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : list) {
            //这里使用正则表达式来取数
            if (item.matches("\\d+")) { //匹配多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //把结果入栈
                stack.push(String.valueOf(res));
            }
        }
        //最后留在stack中的就是结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个了类Operation可以返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.printf("不存在该%s运算符\n",operation);
                break;
        }
        return result;
    }
}