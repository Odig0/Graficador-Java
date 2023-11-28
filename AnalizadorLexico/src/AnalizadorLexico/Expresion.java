package AnalizadorLexico;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author netgo
 */
public class Expresion {
    public ArrayList<Token> reconocerToken(String s, double valorx) throws Exception
    {
        ArrayList<Token> lista = new ArrayList<Token>();
        int estado = 0;
        String aux = "";
        s = s + "@";
        int i = 0;
        boolean error = false;
        boolean fin = false;
        while(i <= s.length() && !error && !fin)
        {
            switch (estado)
            {
                case 0:
                    if(s.charAt(i) == ' ')
                        i++;
                    else if(esDigito(s.charAt(i)))
                    {
                        estado = 1;
                        aux = aux + s.charAt(i);
                        i++;
                    }
                    else if(s.charAt(i) == '+')
                    {
                        estado = 4;
                        i++;
                    }
                    else if(s.charAt(i) == '-')
                    {
                        estado = 5;
                        i++;
                    }
                    else if(s.charAt(i) == '*')
                    {
                        estado = 6;
                        i++;
                    }
                    else if(s.charAt(i) == '/')
                    {
                        estado = 7;
                        i++;
                    }
                    else if(s.charAt(i) == '^')
                    {
                        estado = 8;
                        i++;
                    }
                    else if(s.charAt(i) == '(')
                    {
                        estado = 9;
                        i++;
                    }
                    else if(s.charAt(i) == ')')
                    {
                        estado = 10;
                        i++;
                    }
                    else if(s.charAt(i) == '@')
                    {
                        estado = 11;
                        i++;
                    }
                    else if(s.charAt(i) == 'x')
                    {
                        estado = 13;   
                        i++;
                    }
                    else if(s.substring(i, i + 3).equals("sen"))
                    {
                        estado = 14;
                        i += 3;
                    }
                    else if(s.substring(i, i + 3).equals("cos"))
                    {
                        estado = 15;
                        i += 3;
                    }
                    else if(s.substring(i, i + 3).equals("tan"))
                    {
                        estado = 16;
                        i += 3;
                    }
                    else
                    {
                        estado = 12;
                    }
                    break;
                case 1:
                    if(esDigito(s.charAt(i)))
                    {
                        aux = aux + s.charAt(i);
                        i++;
                    }
                    else if(s.charAt(i) == '.')
                    {
                        aux = aux + s.charAt(i);
                        i++;
                        estado = 2;
                    }
                    else
                    {
                        estado = 3;
                    }                    
                    break;
                case 2:
                    if(esDigito(s.charAt(i)))
                    {
                        aux = aux + s.charAt(i);
                        i++;                                
                    }
                    else
                    {
                        estado = 3;
                    }                    
                    break;
                case 3:
                    Token t1 = new Token(2, 0, Double.parseDouble(aux), 0); // numero
                    lista.add(t1);
                    aux = "";
                    estado = 0;
                    break;
                case 4:
                    Token t2 = new Token(1, 1, 0, 1); // suma
                    lista.add(t2);                          
                    estado = 0;                    
                    break;
                case 5:
                    Token t3 = new Token(1, 2, 0, 1); // resta
                    lista.add(t3);                          
                    estado = 0;    
                    break;
                case 6:
                    Token t4 = new Token(1, 3, 0, 2); // multiplicacion
                    lista.add(t4);                          
                    estado = 0; 
                    break;
                case 7:
                    Token t5 = new Token(1, 4, 0, 2); // division
                    lista.add(t5);                          
                    estado = 0; 
                    break;
                case 8:
                    Token t6 = new Token(1, 5, 0, 3); // potencia
                    lista.add(t6);                          
                    estado = 0;                     
                    break;
                case 9:
                    Token t7 = new Token(3, 0, 0, 0); // parentesis abierto pa
                    lista.add(t7);                          
                    estado = 0; 
                    break;
                case 10:
                    Token t8 = new Token(4, 0, 0, 0); // parentesis cerrado pc
                    lista.add(t8);                          
                    estado = 0; 
                    break;
                case 11:
                    fin = true;
                    break;
                case 12:
                    error = true;
                    break;
                case 13:
                    Token t9 = new Token(2, 0, valorx, 0); // numero
                    lista.add(t9);
                    estado = 0;
                    break;
                case 14: // Reconocer 'sen' como variable independiente
                    Token t14 = new Token(5, 6, 0, 4); // Crear un nuevo token para 'sen'
                    lista.add(t14); // Agregar el token a la lista
                    estado = 0; // Volver al estado inicial
                    break;

                case 15: // Reconocer 'cos' como variable independiente
                    Token t15 = new Token(5, 7, 0, 4); // Crear un nuevo token para 'cos'
                    lista.add(t15); // Agregar el token a la lista
                    estado = 0; // Volver al estado inicial
                    break;

                case 16: // Reconocer 'tan' como variable independiente
                    Token t16 = new Token(5, 8, 0, 4); // Crear un nuevo token para 'tan'
                    lista.add(t16); // Agregar el token a la lista
                    estado = 0; // Volver al estado inicial
                    break;
            }
        }
        if(error)
            throw new Exception("Error expresion no valida");
        return lista;
    }
    

    private boolean esDigito(char c) {
        return Character.isDigit(c);
    }
    
    public ArrayList<Token> convertirAPostFija(ArrayList<Token> lista) throws Exception
    {
        Stack<Token> pila = new Stack<>();
        ArrayList<Token> lp = new ArrayList<>();
        for(int i = 0 ; i< lista.size();i++)
        {
             Token t = lista.get(i);
             //System.out.println("Tipo de token nro "+i+" "+t.getTipoToken());
             if(t.getTipoToken()==2)//operando
             {
                 lp.add(t);
             }
             else
             {
                 if(t.getTipoToken()==3)//pa
                 {
                     pila.push(t);
                 }
                 else
                 {
                        if(t.getTipoToken()==1)//operador
                        {
                             if(pila.isEmpty())
                                 pila.push(t);
                             else
                             {
                                 if(t.getPrioridad()>pila.peek().getPrioridad())
                                     pila.push(t);
                                 else
                                 {
                                     // no es mayor la prioridad entonces remover hasta encontrar uno de menor
                                     while(!pila.isEmpty()&& t.getPrioridad()<=pila.peek().getPrioridad())
                                     {
                                         lp.add(pila.pop());
                                     }
                                     pila.push(t);
                                 }
                             }
                        }
                        else
                        {
                            //pcerrado
                            while(!pila.isEmpty()&&pila.peek().getTipoToken()!=3)
                            {
                                lp.add(pila.pop());
                            }
                            if(pila.isEmpty())
                                throw new Exception("Error, se esperaba parentesis abierto");
                            else
                                pila.pop();
                        }
                 }
             }
        }
        while(!pila.isEmpty())
        {
            if(pila.peek().getTipoToken()!=3)
                lp.add(pila.pop());
            else
                throw new Exception("Error, se esperaban parentesis cerrado");
        }
        return lp;
    }
    public double evaluar(String exp, double valorx) throws Exception {
ArrayList<Token> LT = reconocerToken(exp, valorx);
    ArrayList<Token> LP = convertirAPostFija(LT);
    Stack<Double> pila = new Stack<>();

    for (int i = 0; i < LP.size(); i++) {
        Token T = LP.get(i);

        if (T.getTipoToken() == 2) {
            pila.push(T.getValor());
        } else if (T.getTipoToken() == 5) {
            if (pila.isEmpty()) {
                throw new Exception("Expresión mal formada faltan operandos");
            }
            double a = Math.toRadians(pila.pop()); // Convertir a radianes
            double r = calcular(a, 0, T.getTipoOperador());
            pila.push(r);
        } else {
            if (pila.size() >= 2) {
                double b = pila.pop();
                double a = pila.pop();
                double r = calcular(a, b, T.getTipoOperador());
                pila.push(r);
            } else {
                throw new Exception("Expresión mal formada faltan operadores u operandos");
            }
        }
    }

    if (pila.size() == 1) {
        return pila.pop();
    } else {
        throw new Exception("Expresión mal formada faltan operadores");
    }
}


    private double calcular(double a, double b, int tipoOperador) {
        switch (tipoOperador) {
        case 1:
            return a + b;
        case 2:
            return a - b;
        case 3:
            return a * b;
        case 4:
            return a / b;
        case 5:
            return Math.pow(a, b);
        case 6:
            return Math.sin(a);
        case 7:
            return Math.cos(a);
        case 8:
            return Math.tan(a);
    }
    return 0;
    }
}