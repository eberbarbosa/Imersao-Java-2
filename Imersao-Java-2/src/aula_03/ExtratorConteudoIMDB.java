
package aula_03;

import aula_01.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eber
 */
public class ExtratorConteudoIMDB implements ExtratorDeConteudo{
    
    public List<Conteudo> extraiConteudos(String json) {
        
        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);
        
        return listaDeAtributos.stream()
                .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("image").replaceAll("(@?\\.)([0-9A-Z,_]+).jpg$","$1.jpg"))) 
                .toList();     
      
        
    }
        
}
