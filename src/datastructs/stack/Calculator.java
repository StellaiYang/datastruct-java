package datastructs.stack;

/**
 * 模拟计算器
 */
public class Calculator {
    public static void main(String[] args) {
        //完成表达式的计算
        String expression = "30+2*6-21";
        //创建两个栈，一个是数栈，一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描表达式
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到char保存到ch中
        String keepNum = "";
        //开始while循环扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是数字还是符号
            if(operStack.isOper(ch)){//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较。如果当前的操作符的优先级小于或者等于栈顶的操作符
                    //需要从数栈中pop出两个数，符号栈pop出栈顶符号，进行运算，将得到结果，入数栈，当前符号
                    //入符号栈
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        //运算的结果
                        res = numStack.cal(num1, num2, oper);
                        //运算的结果入数栈
                        numStack.push(res);
                        //前符号入符号栈
                        operStack.push(ch);
                    }else {
                         //如果当前的操作符的优先级大于栈中的操作符，则直接入符号栈
                        operStack.push(ch);
                    }
                }else {
                    //如果为空，直接入符号栈
                    operStack.push(ch);// 1 + 3
                }
            }else {
                //如果为数，则直接入数栈
                //"1 + 3" => '1' != 1
//                numStack.push(ch - 48);//ASCII 表 '0'为48，'1'为49
                //1.当处理多位数时，不能发现是数就立即入栈
                //2.在处理数时，需要向expression的表达式的index后再看一位，如果是数就继续扫描，如果是符号才入栈
                //3.定义变量用于拼接多位数

                //处理多位数
                keepNum += ch;

                //如果 ch已经是expression的最后一位，就直接入栈
                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数就继续扫描，如果是符号才入栈
                    if(operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))){
                        //如果后一位是运算符，则将拼接的keepNum入栈 keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));

                        //重要！！！ 清空keepNum
                        keepNum = "";
                    }
                }
            }

            //让index + 1，判断是否扫描到expression最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }

        //扫描完毕后，就顺序的从数栈和符号栈pop出相应的数和结果，并运行
        while (true){
            //如果符号栈为空，则计算到最后的结果,数栈中就只有一个数字【结果】
            if(operStack.isEmpty()){
                break;
            }else {
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();
                //运算的结果
                res = numStack.cal(num1, num2, oper);
                numStack.push(res);//入栈
            }
        }
        //将数栈中最后的数字，也就是结果pop出来
        int result = numStack.pop();
        System.out.printf("表达式%s = %d",expression,result);
    }
}
//定义一个 ArrayStack2 表示栈结构
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//数组 容器
    private int top = -1;//top 表示栈顶，初始化为-1

    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int e){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = e;
    }

    //出栈，将栈顶的数据取出
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int val = stack[top];
        top--;
        return val;
    }
    //显示栈的情况【遍历栈】，遍历时需要从栈顶开始显示
    public void show(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        //从栈顶开始显示
        for (int i = top; i >= 0 ;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来定义，优先级使用数字表示，数字越大，则优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;//假定目前的表达式只有 + ,- , *, /
        }
    }
    //判断是否是运算符
    public boolean isOper(int val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //计算方法
    public int cal (int num1,int num2,int oper){
        int res = 0;//用于存放计算的结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //返回当前栈栈顶的值，不是pop出来
    public int peek() {
        return stack[top];
    }
}

