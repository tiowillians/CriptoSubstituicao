/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptosubstituicao;

/**
 *
 * @author willi
 */
public class FreqLetras implements Comparable
{
    private final char letra;     // letra do alfabeto
    private int total;      // quantidade total de letras
    
    public FreqLetras(char letra)
    {
        this.letra = letra;
        total = 0;
    }
    
    public void add()
    {
        ++total;
    }
    
    public char getLetra()
    {
        return letra;
    }
    
    public int getTotal()
    {
        return total;
    }

    // ordenação de frequência de letras: ordem decrescente
    @Override
    public int compareTo(Object o)
    {
        FreqLetras f = (FreqLetras)o;
        return f.total - this.total;
    }
}
