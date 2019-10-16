package com.company;

public class EquationSolver {
    
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
}
