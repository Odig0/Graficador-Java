/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionconsolaanalex;

import AnalizadorLexico.Expresion;
import AnalizadorLexico.Token;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class AplicacionConsolaAnalex {

    public static void mostrar(ArrayList<Token> L) {
        for (int i = 0; i < L.size(); i++) {
            System.out.println("Detalles del token nro: " + (i + 1));
            System.out.println("Tipo de Token:" + L.get(i).getTipoToken());
            System.out.println("Tipo de Operador:" + L.get(i).getTipoOperador());
            System.out.println("Prioridad:" + L.get(i).getPrioridad());
            System.out.println("Valor:" + L.get(i).getValor());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Expresion objExpresion = new Expresion();
        Scanner scanner = new Scanner(System.in);

        try {
            // Pedir al usuario la expresión matemática
            System.out.print("Ingrese la expresion matematica apa: ");
            String expresion = scanner.nextLine();

            // Reconocer tokens y mostrar resultados
            ArrayList<Token> resultado = objExpresion.reconocerToken(expresion, 0); // Se utiliza 0 como valor por defecto
            System.out.println("\nDetalles de los TOKENS");
            mostrar(resultado);

            // Evaluar la expresión y mostrar el resultado
            //double resultadoEvaluar = objExpresion.evaluar(expresion, 0); // Se utiliza 0 como valor por defecto
            //System.out.println("\nEl resultado de evaluar la expresion es: " + resultadoEvaluar);

        } catch (Exception ex) {
            System.out.println("INGRESA DATOS VALIDOS!!!!");
        } finally {
            scanner.close();
        }
    }
}