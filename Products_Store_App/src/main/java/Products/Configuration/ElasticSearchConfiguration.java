package Products.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Indicates that a class declares one or more @Bean methods
public class ElasticSearchConfiguration {
	
	@Bean  //Indicates that a method produces a bean to be managed by the Spring container. 
	public RestClient getRestClient() { //RestClient connects to an Elasticsearch cluster through HTTP. 

		RestClient restClient=RestClient.builder(new HttpHost("localhost",9200)).build(); 
		
		return restClient; 
	}
	
	@Bean
	public ElasticsearchTransport getElasticsearchTransport() {
		return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
	}
	
    @Bean
    public ElasticsearchClient getElasticsearchClient(){
        ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
        return client;
    }
}

/*
 * 1.getRestClient(): This method use to configure(set up) the URL and port on which Elasticsearch is running.
 * 
 * 2.getElasticsearchTransport(): It returns the Transport Object, whose purpose is it automatically 
 *                             map the our Model Class to JSON and integrates them with API Client.
 * 
 * 3.getElasticsearchClient(): It returns a bean of Elasticsearchclient, which we further use to perform 
 *                           all query operation(CRUD) with Elasticsearch.                    
 */


