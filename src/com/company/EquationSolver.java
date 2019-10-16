package com.company;

import java.util.*;

public class EquationSolver {

    public double operate(){
        map_variables();
        simplify_sign();
        format_equation();
        map_brackets();
        return solve(0, equation.length() - 1);
    }
    private StringBuilder equation = new StringBuilder();

	 double solve (int l, int r) {
		if (brackets.containsKey(l) && brackets[l] == r) {
			return solve(l + 1, r - 1);
		}

		if(l == r){
			return numbers[l];
		}

		HashMap<Character, Integer> op_pos;
		for(int i = l; i <= r; i++){
			if(equation[i] == '('){
				i = brackets[i];
				continue;
			}
			if(operators.find(equation[i]) != operators.end()){
				op_pos[equation[i]] = i;
			}
		}

		if(op_pos.containsKey('+')){
			return solve(l, op_pos['+'] - 1) + solve(op_pos['+'] + 1, r);
		}
		if(op_pos.containsKey('-')){
			return solve(l, op_pos['-']-1) - solve(op_pos['-']+1, r);
		}
		if(op_pos.containsKey('*')){
			return solve(l, op_pos['*']-1) * solve(op_pos['*']+1, r);
		}
		if(op_pos.containsKey('/')){
			return solve(l, op_pos['/']-1) / solve(op_pos['/']+1, r);
		}
		if(op_pos.containsKey('^')){
			return pow(solve(l, op_pos['^']-1), solve(op_pos['^']+1, r));
		}

		return 0;
	}
  
  
      void simplify_sign () {
        StringBuilder simplified_equation = new StringBuilder();

        if(equation.charAt(0) == '+' || equation.charAt(0) == '-'){
            simplified_equation.append('0');
        }

        for(int i = 0; i<equation.length(); i++){
            if(equation.charAt(i) != '+' && equation.charAt(i) != '-'){
                simplified_equation.append(equation.charAt(i));
                continue;
            }

            int negcnt = 0, j;
            for(j=i; j < equation.length() && (equation.charAt(j) == '+' || equation.charAt(j) == '-'); j++){
                if(equation.charAt(j) == '-') negcnt++;
            }

            if(negcnt%2 == 0) simplified_equation.append('+');
            else simplified_equation.append('-');
            i = j-1;
        }
        equation = simplified_equation;
        
    }
      
    public void format_equation() {
        StringBuilder formated_equation = new StringBuilder();
        for (int i = 0; i < equation.size(); i++) {
            if ((equation.charAt(i) >= '0' && equation.charAt(i) <= '9') || equation.charAt(i) == '-'
                    || equation.charAt(i) == '+') {
                map_number(i, formated_equation);
            } else {
                formated_equation.append(equation.charAt(i));
            }
        }
        equation = formated_equation.toString();
    }

    public void map_number(int i, string formated_equation) {
        // void map_number(int &i, string &formated_equation)

        if (this.equation.charAt(i) == '+') {
            if (i - 1 >= 0 && !this.operators.contains(this.equation.charAt(i - 1))) {
                formated_equation += '+';
            }
            return;
        }

        int j = 0;
        if (this.equation.charAt(i) == '-') {
            if (i - 1 >= 0 && !this.operators.contains(this.equation.charAt(i - 1))) {
                formated_equation += '-';
                return ;
            }
            j++;
        }

        while (i + j < this.equation.length()
                && ((this.equation.charAt(i + j) >= '0' && this.equation.charAt(i + j) <= '9')
                        || this.equation.charAt(i + j) == '.'))
            j++;
        

        double number = Double.parseDouble(this.equation.substring(i, j));
        this.numbers.put(formated_equation.length(), number);

        formated_equation += '?';
        i += j - 1;
    }

    public void map_brackets() throws IllegalArgumentException {
     Stack bracket_stack = new Stack();
     for (int i = 0; i < this.equation.length(); i++) {
        char x = equation[i];
        if (x == '(') {
            bracket_stack.push(i);
        }
        else if (x == ')') {
            if(bracket_stack.empty()) {
                throw new IllegalArgumentException("Parenthesis of equation " + equation + " are not balanced");
            }
            brackets[new Integer(bracket_stack.peek())] = i;
            bracket_stack.pop();
        }
    }
    if (!bracket_stack.empty()) {
        throw new IllegalArgumentException("Parenthesis of equation " + equation + " are not balanced");
    }
  }    
}
