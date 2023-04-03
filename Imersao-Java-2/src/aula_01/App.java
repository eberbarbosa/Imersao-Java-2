
package aula_01;

/**
 *
 * @author eber
 */
import aula_02.GeradoraDeFigurinhas;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
         var diretorio = new File("saida/");
        diretorio.mkdir();    
        

        // exibir e manipular os dados 
        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 3; i++) {
            Map<String, String> filme = listaDeFilmes.get(i); 
            
            String urlImagem     = filme.get("image");
            String urlImagemMaior = urlImagem.replaceFirst("(@?\\.)([0-9A-Z,_]+).jpg$","$1.jpg");
            String titulo        = filme.get("title");              
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            
            String textoFigurinha;
            InputStream imagemEber;
            if(classificacao >= 8.0) {
                textoFigurinha = "TOPZERA";
                imagemEber = new FileInputStream(new File("sobreposicao/bom.jpg"));
                
            } else {
                textoFigurinha = "!! FRACO !!";
                imagemEber = new FileInputStream(new File("sobreposicao/ruim.jpg"));
            }
            
            InputStream inputStream = new URL(urlImagemMaior).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";            
            
            geradora.cria(inputStream, nomeArquivo, textoFigurinha, imagemEber);
                     
            System.out.println("\033[1m\033[42m \u001b[37mTítulo: \033[0m\033[32m" + filme.get("title"));
            System.out.println("\033[1m\033[44m \u001b[37mURL da Imagem:\033[0m\033[36" + filme.get("image"));
            System.out.println("\033[1m\033[45mClassificação:\033[0m\033[35m"  + filme.get("imDbRating"));
            //double classificacao = Double.parseDouble(filme.get("imDbRating")); 
            int estrelas = (int)classificacao;
            for (int j = 0; j < estrelas; j++) {                
                System.out.print( "\u001b[33m ⭐ \u001b[0m");                
            }
            
            System.out.println("\n");
            
        }
    }
}