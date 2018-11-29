package com.example.admin.algorithm_1;
import java.util.Stack;
//将中缀表达式转为后缀的类
public class InToPo{
    private Stack<Character> stack;
    //中缀表达式
    private String infix;
    //后缀表达式
    private String postfix;
    //构造函数
    public InToPo(String infix){
        this.infix=infix;
        this.postfix="";
        //初始化栈
        stack=new Stack<Character>();
    }

    //将中缀表达式转换为后缀
    public void translation()
    {
        int len = infix.length();
        char c;
        stack.push('#');
        boolean flag=false;
        for (int i = 0; i < len - 1; i++)
        {

            //如果是数字，直接输入文件
            if (infix.charAt(i) <= '9'&&infix.charAt(i) >= '0')
            {
                postfix+= infix.charAt(i);
                flag=true;
            }

            //如果是右括号，输出栈顶元素直到遇到左括号
            else if (infix.charAt(i) == ')')
            {
                if(flag){
                    postfix+= ',';
                    flag=false;
                }
                c =stack.pop();
                for (; !stack.empty() && c != '('; c = stack.pop())
                {
                    postfix+=c;
                }
            }
            //如果是其他运算符号，比较此运算符与栈顶元素的优先级
            //如果小于，弹出栈顶运算符，直到大于，将此运算符入栈
            else
            {
                //输入逗号作为数字间分隔符
                if(flag){
                    postfix+= ',';
                    flag=false;
                }
                c = stack.pop();
                for (; icp(infix.charAt(i)) <= isp(c); c = stack.pop())
                {
                    postfix+= c;
                }
                stack.push(c);
                stack.push(infix.charAt(i));
            }
        }

        //最后检查栈是否为空，若不为空，输出全部剩余运算符
        while (!stack.empty())
        {
            c = stack.pop();
            if (c != '#')
            {
                postfix+= c;
            }
        }
        postfix+= '=';
    }

    //将表达式存入文件
    /*public void save(){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            //打开文件
            out=openFileOutput("postfix.txt",Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            //将后缀表达式存入
            writer.write(postfix);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //将文件中的表达式读入
    public String read(){
        FileInputStream in=null;
        BufferedReader reader=null;
        try{
            in=openFileInput("postfix.txt",Context.MODE_PRIVATE);
            reader=new BufferedReader(in);

        }
    }*/
    //计算表达式结果
    public int comp()
    {
        Stack <Integer> stack;
        stack=new Stack<Integer>();
        int k=0, m=0;
        //用于检测逗号分隔符
        Boolean flag = false;
        int len =postfix.length();
        for (int i = 0; i < len; i++)
        {
            if (postfix.charAt(i) == ',')
            {
                flag = false;
                continue;
            }

            //将字符型数字转换为整形
            if (postfix.charAt(i) <= '9'&&postfix.charAt(i) >= '0')
            {
                if (flag)
                {
                    m = stack.pop();
                    m = m * 10 + (int)(postfix.charAt(i) - 48);
                    stack.push(m);
                }
                else
                {
                    stack.push(postfix.charAt(i)-48);
                }
                flag = true;
            }

            //如果遇到运算符，弹出两个栈顶数字运算出结果
            else
            {
                switch (postfix.charAt(i))
                {
                    case '+':
                        k = stack.pop();
                        k = stack.pop() + k;
                        stack.push(k);
                        break;
                    case '-':
                        k = stack.pop();
                        k = stack.pop() - k;
                        stack.push(k);
                        break;
                    case '*':
                        k = stack.pop();
                        k = stack.pop()*k;
                        stack.push(k);
                        break;
                    case '/':
                        k = stack.pop();
                        k = stack.pop()/k;
                        stack.push(k);
                        break;
                    default:
                        break;
                }
            }
        }
        //返回最后的栈内元素
        return stack.pop();
    }


    //获取后缀表达式
    public String getResult(){
        return postfix;
    }
    //计算符号栈外优先级
    private int icp(char in)
    {
        int rt;
        switch (in)
        {
            case '(':
                rt = 7;
                break;
            case '*':
            case '/':
                rt = 4;
                break;
            case '+':
            case '-':
                rt = 2;
                break;
            case ')':
                rt = 1;
                break;
            case '#':
                rt = 0;
                break;
            default:
                rt = -1;
                break;
        }
        return rt;
    }


    //计算栈内优先级
    private int isp(char in)
    {
        int rt;
        switch (in)
        {
            case '(':
                rt = 1;
                break;
            case '*':
            case '/':
                rt = 5;
                break;
            case '+':
            case '-':
                rt = 3;
                break;
            case ')':
                rt = 7;
                break;
            case '#':
                rt = 0;
                break;
            default:
                rt = -1;
                break;
        }
        return rt;
    }
}
