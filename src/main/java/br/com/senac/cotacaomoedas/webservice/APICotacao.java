
package br.com.senac.cotacaomoedas.webservice;

import br.com.senac.cotacaomoedas.view.Tela;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author biancamarques
 */
public class APICotacao {
    HttpEntity entidade = null;
    
    private HttpEntity conectar() {
        
        try {
            URI uri = new URI("https://economia.awesomeapi.com.br/json/last/USD-BRL,EUR-BRL,BTC-BRL");
            CloseableHttpClient cliente = HttpClients.createDefault();
            HttpGet requisicao = new HttpGet(uri);
            CloseableHttpResponse resposta = cliente.execute(requisicao);
            entidade = resposta.getEntity();
        } catch (Exception ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entidade;
    }
    
    public String cotarDolar() throws IOException{
       entidade = conectar();
       String USD = null;
       if (entidade != null) {
                String endereco = EntityUtils.toString(entidade);
                JSONObject objetoJson = new JSONObject(endereco);
                USD = objetoJson.getJSONObject("USDBRL").getString("bid");     
       }
       return USD;
    }
    
    
    public String cotarEuro() throws IOException{
       entidade = conectar();
       String EUR = null;
       if (entidade != null) {
                String endereco = EntityUtils.toString(entidade);
                JSONObject objetoJson = new JSONObject(endereco);
                EUR = objetoJson.getJSONObject("EURBRL").getString("bid");     
       }
       return EUR;
    }
    
     public String cotarBTC() throws IOException{
       entidade = conectar();
       String BTC = null;
       if (entidade != null) {
                String endereco = EntityUtils.toString(entidade);
                JSONObject objetoJson = new JSONObject(endereco);
                BTC = objetoJson.getJSONObject("BTCBRL").getString("bid");     
       }
       return BTC;
    }  
}
 