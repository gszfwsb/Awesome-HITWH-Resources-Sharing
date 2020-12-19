import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

/**
 *
 */

/**
 * @author 李宝航
 *
 */
public class SyntaxAnanlyzer {

    private String temp1;
    private LinkedList<Character> list1 = new LinkedList<Character>();
    private Stack<Character> symbolstack = new Stack<Character>();
    private Stack<Integer> statestack = new Stack<>();
    private BufferedWriter output;
    private Production production = new Production();
    private String actions = "";
    private Vector<Integer> sequenceOfP = new Vector<Integer>();

    private String[][] table = {
            {"", "", "S4", "", "S5", "", "1", "2", "3"}, // 0
            {"S6", "", "", "", "", "accept", "", "", ""}, // 1
            {"r2", "S7", "", "r2", "", "r2", "", "", ""}, // 2
            {"r4", "r4", "", "r4", "", "r4", "", "", ""},// 3
            {"", "", "S4", "", "S5", "", "8", "2", "3"},// 4
            {"r6", "r6", "", "r6", "", "r6", "", "", ""},// 5
            {"", "", "S4", "", "S5", "", "", "9", "3"},// 6
            {"", "", "S4", "", "S5", "", "", "", "10"},// 7
            {"S6", "", "", "S11", "", "", "", "", ""},// 8
            {"r1", "S7", "", "r1", "", "r1", "", "", ""},// 9
            {"r3", "r3", "", "r3", "", "r3", "", "", ""},// 10
            {"r5", "r5", "", "r5", "", "r5", "", "", ""},// 11
    };

    public SyntaxAnanlyzer() {
        try {
            output = new BufferedWriter(new FileWriter("output.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getText() {
        char a[];
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("input.txt"));
            String lString;
            while ((lString = bufferedReader.readLine()) != null) {
                symbolstack.clear();
                list1.clear();
                statestack.clear();
                sequenceOfP.clear();
                temp1 = lString.trim();
                temp1.replaceAll("\\s+", "");// 去掉一个以上的空白符，用一个空白代替
                a = temp1.toCharArray();
                for (char _char : a) {
                    list1.offer(_char);
                }
                list1.offerLast('$');
                symbolstack.push('$');
                statestack.push(0);
                output.write("state\t symbol\t input\t action");
                output.newLine();
                boolean b = analysis();
                if (b) {
                    output.write("这个输入符合设定的SLR(1)文法");
                    output.newLine();
                    output.write("产生式顺序如下：(bottom-up)");
                    output.newLine();
                    for (int i = 0; i < sequenceOfP.size(); i++) {
                        Integer n = sequenceOfP.get(i);
                        output.write(production.getProduction(n.intValue()));
                        output.newLine();
                    }
                } else
                    output.write("这个输入不符合设定的SLR(1)文法");
                output.write("-----------------------------------------");
                output.newLine();
            }
        } catch (Exception e) {
        } finally { // 关闭资源
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (output != null)
                try {
                    output.close();
                } catch (Exception e2) {
                }
        }
    }

    public int getOrder(char c) {        //获取符号（终结符或非终结符）的编号（符号表中的横向顺序）
        if (c == '+')
            return 0;
        else if (c == '*')
            return 1;
        else if (c == '(')
            return 2;
        else if (c == ')')
            return 3;
        else if (c == 'i')
            return 4;
        else if (c == '$')
            return 5;
        else if (c == 'E')
            return 6;
        else if (c == 'T')
            return 7;
        else if (c == 'F')
            return 8;
        else
            return -1;
    }

    public void display() {          //读SymbolStack、StateStack和input里的所有字符，显示到输出文件
        String symbols = "";
        String states = "";
        String input = "";

        Object[] symbolObjects = symbolstack.toArray();
        for (int i = 0; i < symbolObjects.length; i++) {
            symbols += symbolObjects[i].toString();

        }

        Object[] stateObjects = statestack.toArray();
        for (int i = 0; i < statestack.size(); i++) {
            states += stateObjects[i].toString();
        }

        Object[] inputObjects = list1.toArray();
        for (int i = 0; i < list1.size(); i++) {
            input += inputObjects[i].toString();
        }
        try {
            output.write(states + "\t" + symbols + "\t" + input + "\t"
                    + actions);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean analysis() {
        while (true) {
            actions = "";
            char c = list1.peekFirst();
            int i = getOrder(c);
            if (i == -1)                //如果输入是除了规定的终结符和非终结符以外的符号，返回false
                return false;
            String string2 = table[statestack.peek()][i];
            if (string2.trim().equals("".trim()))
                return false;
            else if (string2.equals("accept")) {
                actions += "accept";
                display();
                return true;
            } else if (string2.charAt(0) == 'S') { // 移进
                String s = string2.substring(1); // 取S后面的状态数
                int n = Integer.parseInt(s);
                System.out.println("Shift " + s);
                actions += "Shift to state " + s;
                display();
                list1.pollFirst(); // 从输入带里弹出第一个字符，并把该字符送symbolStack,同时向StateStack压入取得的状态数
                statestack.push(n);
                symbolstack.push(c);

            } else if (string2.charAt(0) == 'r') { // 规约
                String s = string2.substring(1); // 取r后面的产生式编号
                int n = Integer.parseInt(s);
                System.out.println("Reduce " + s);
                actions += "Reduce by production " + s;
                display();
                sequenceOfP.addElement(n);

                int n2 = production.getNumOfP(n);// 取产生式右部的字符个数（应该在SymbolStack和StateStack中弹出来的个数）
                for (int i1 = 0; i1 < n2; i1++) {
                    symbolstack.pop();
                    statestack.pop();
                }

                char _char1 = production.getProduction(n).charAt(0);// 获取产生式左边的非终结符，压入SymbolStack
                symbolstack.push(_char1);
                String s1 = table[statestack.peek()][getOrder(_char1)];// 查找goto字表，找到该终结符和当前状态对应的编号，压入StateStack
                if (s1.trim().equals(""))
                    return false;
                else
                    statestack.push(Integer.parseInt(s1));
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SyntaxAnanlyzer ananlyzer = new SyntaxAnanlyzer();
        ananlyzer.getText();
    }

}
