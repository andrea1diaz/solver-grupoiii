package com.company;

public class EquationSolver {
	String equation;
	HashMap<Integer, Double> numbers = new HashMap<Integer, Double>();
	HashMap<Character, Double> variables = new HashMap<Character, Double>();
	HashMap<Integer, Integer> brackets = new HashMap<Integer, Integer>();
	Set<Character> operators = new HashSet<Character>{'+', '-', '*', '/', '^'};
	operator.add('+');
	operator.add('-');
	operator.add('*');
	operator.add('/');
	operator.add('^');

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
}
