
package aula_01;

import aula_02.GeradoraDeFigurinhas;
import aula_03.API;
import aula_03.ClienteHttp;
import aula_03.Conteudo;
import aula_03.ExtratorDeConteudo;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 *
 * @author eber
 */
public class App {

    public static void main(String[] args) throws Exception {

        API api = API.NASA;

        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.titulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println();
        }
    }
}