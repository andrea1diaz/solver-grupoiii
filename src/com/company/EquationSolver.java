package com.company;

public class EquationSolver {
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
      
    public void format_equation(){
        StringBuilder formated_equation = new StringBuilder();
        for(int i = 0; i<equation.size(); i++){
            if((equation.charAt(i) >= '0' && equation.charAt(i) <= '9') || equation.charAt(i) == '-' || equation.charAt(i) == '+') {
                map_number(i, formated_equation);
            }
            else{
                formated_equation.append(equation.charAt(i));
            }
        }
        equation = formated_equation.toString();
}
