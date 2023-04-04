
package aula_03;
/**
 *
 * @author eber
 */
public enum API {
    
    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json", new ExtratorConteudoIMDB()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=RZpApaczBLqp8yw5s7lpRudWsdYqzC7ewPxPkJxS&start_date=2022-06-12&end_date=2022-06-14", new ExtratorConteudoNasa()),
    IMDB_TOP_SERIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json", new ExtratorConteudoIMDB());
    
    private String url;
    private ExtratorDeConteudo extrator;
    
    API(String url, ExtratorDeConteudo extrator){
        this.url = url; 
        this.extrator = extrator;
    }

    public String getUrl() {
        return url;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }

   
    
    
    
    
    
}
