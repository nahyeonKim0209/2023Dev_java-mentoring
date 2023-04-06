package com.iKeeper.Day1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import static java.lang.System.exit;

import com.iKeeper.Day1.Calculate;

class Main {
    public static void main(String[] args) throws IOException {
        Calculate calc=new Calculate();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        //숫자와 기호에 대한 배열 생성
        List<Float> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        //입력 받는 부분
        input = reader.readLine();
        StringTokenizer split= new StringTokenizer(input, "+-*/",true);
        while (split.hasMoreTokens()) {
            String token = split.nextToken().trim();
            if (token.isEmpty()) {
                continue;
            }
            try {
                if (isNumber(token)) {
                    numbers.add(Float.parseFloat(token));
                } else {
                    if (token.matches("[+\\-*/]")) {
                        operators.add(token.charAt(0));
                    } else {
                        throw new IllegalArgumentException("잘못된 문자열입니다.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 형식입니다.");
                exit(0);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                exit(0);
            }
        }

        var result=numbers.get(0);

        for (int i=0;i<numbers.size();i++){
            Character op=operators.get(i);
            switch (op){
                case '+':
                    try{
                        result=calc.add(result,numbers.get(i+1));
                    }catch(ArithmeticException e){
                        System.out.println("오버플로우입니다.");
                        exit(0);
                    }
                    break;
                case '-':
                    try{
                        result=calc.sub(result,numbers.get(i+1));
                    }catch(ArithmeticException e){
                        System.out.println("오버플로우입니다.");
                        exit(0);
                    }
                    break;
                case '*':
                    try{
                        result=calc.mul(result,numbers.get(i+1));
                    }catch(ArithmeticException e){
                        System.out.println("오버플로우입니다.");
                        exit(0);
                    }
                    break;
                case '/':
                    if(numbers.get(i+1)==0){
                        System.out.println("0으로 나눌 수 없습니다.");
                    }
                    try{
                        result=calc.div(result,numbers.get(i+1));
                    }catch(ArithmeticException e){
                        System.out.println("오버플로우입니다.");
                        exit(0);
                    }
                    break;
            }
            if (i==operators.size()-1){
                break;
            }
        }

        System.out.println("Result: "+result);

    }
    public static boolean isNumber(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
