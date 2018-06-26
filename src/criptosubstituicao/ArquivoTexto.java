/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptosubstituicao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author willi
 */
public class ArquivoTexto 
{
    private String msgErro;
    private final String nomeArquivo;
    private FreqLetras[] freqLetras;
    private int totalLetras;
    
    public ArquivoTexto(String nomeArquivo)
    {
        this.nomeArquivo = nomeArquivo;
        msgErro = "";
        totalLetras = 0;
        
        // monta lista de frequência das 26 letras do alfabeto
        freqLetras = new FreqLetras[26];
        for(int i = 0; i < 26; ++i)
        {
            freqLetras[i] = new FreqLetras((char)('a' + i));
        }
    }
    
    public String getErro()
    {
        return msgErro;
    }
    
    public String[] getFrequenciaLetras()
    {
        // cria lista de frequencia de letras
        ArrayList<FreqLetras> list = new ArrayList<FreqLetras>();
        for(int i = 0; i < 26; ++i)
            list.add(freqLetras[i]);
        
        // ordena lista
        Collections.sort(list);

        // copia lista de frequência em um vetor de string
        String[] freq = new String[26];
        for(int i = 0; i < 26; ++i)
        {
            freq[i] = String.format("%c: %.2f%%",
                    list.get(i).getLetra(),
                    (list.get(i).getTotal() * 100.0)/totalLetras);
        }
        
        // ordenar a lista
        return freq;
    }
    
    public String lerArquivo()
    {
        File arquivo = new File(nomeArquivo);
        if(!arquivo.exists())
        {
            msgErro = "Arquivo não existe";
            return null;
        }
        
        String linha;
        String conteudo = "";
        try
        {
            //faz a leitura do arquivo
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            // leitura dos dados (enquanto houver linhas)
            char c;
            while(br.ready())
            {
                //ler a proxima linha, convertendo para minúsculo
                linha = br.readLine().toLowerCase();
                
                // adicionar linha no conteúdo lido do arquivo
                if(conteudo.isEmpty() == false)
                    conteudo += "\n";
                
                conteudo += linha;
                
                // adicionar frequência de letras
                for (int i = 0; i < linha.length(); i++)
                {
                   c = linha.charAt(i);
                   if (Character.isLetter(c))
                   {
                       ++totalLetras;
                       freqLetras[(int)(c - 'a')].add();
                   }
                }                
            }

            // fecha arquivo
            br.close();
            fr.close();
            
            return conteudo;

        } catch (IOException ex) {
            msgErro = "ERRO: leitura do arquivo de dados";
            return null;
        }
    }

    public boolean gravarArquivo(String conteudo)
    {
        File arquivo = new File(nomeArquivo);

        try
        {
            // cria o arquivo (caso ele não exista)
            arquivo.createNewFile();

            // variáveis para manipulação do arquivo
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);

            // grava dados
            bw.write(conteudo);

            bw.close();
            fw.close();
            
            return true;
        } catch (IOException ex) {
            msgErro = "ERRO: gravação do arquivo de dados";
            return false;
        }    
    }
}
