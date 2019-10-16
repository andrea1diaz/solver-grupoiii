package com.company;

public class EquationSolver {
    public double operate(){
        map_variables();
        simplify_sign();
        format_equation();
        map_brackets();
        return solve(0, equation.length() - 1);
    }
}
