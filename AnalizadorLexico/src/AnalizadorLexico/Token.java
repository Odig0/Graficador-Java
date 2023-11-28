/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalizadorLexico;

/**
 *
 * @author netgo
 */
public class Token {
    private int tipoToken; // operador = 1 operando = 2 pa = 3 pc = 4 funcion trigonometrica = 5
    private int tipoOperador; // suma = 1 resta = 2 multiplicacion = 3 division = 4 potencia = 5  sen = 6  cos = 7 tan = 8
    private double valor;
    private int prioridad;

    public Token(int tipoToken, int tipoOperador, double valor, int prioridad) {
        this.tipoToken = tipoToken;
        this.tipoOperador = tipoOperador;
        this.valor = valor;
        this.prioridad = prioridad;  //+ - = 1 * y / = 2  potencia ^ = 3 sen cos tan = 4
    }


    public int getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(int tipoToken) {
        this.tipoToken = tipoToken;
    }

    public int getTipoOperador() {
        return tipoOperador;
    }

    public void setTipoOperador(int tipoOperador) {
        this.tipoOperador = tipoOperador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
}
