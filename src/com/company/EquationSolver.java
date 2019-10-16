package com.company;

public class EquationSolver {

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
}
