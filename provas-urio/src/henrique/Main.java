package henrique;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scaner = new Scanner(System.in);
		
		double num1,num2,resultado =0;
		String operador;
		
		System.out.println("valor1:");
		num1 = scaner.nextDouble();
		
		System.out.println("valor2:");
		num2 = scaner.nextDouble();
		
		System.out.println("operador");
		operador = scaner.next();
		
		if(operador.equals("+")) {
			resultado = num1 + num2;
		}
		
		System.out.println("resultado" + resultado);
		
		scaner.close();
	}
	
}