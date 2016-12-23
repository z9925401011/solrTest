package solr.test;

import java.io.IOException;










import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {

	@Test
	public void addTest() throws Exception{
		//this.getClass().getClassLoader().loadClass("org.apache.solr.client.solrj.SolrServer");
		SolrServer solrServer = new HttpSolrServer("http://192.168.188.128:8080/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test2");
		document.addField("item_title", "测试数据1");
		document.addField("item_price", 1232133);
		//qq
		solrServer.add(document);
		
		solrServer.commit();
	}
	
	@Test
	public void deleteDocument() throws Exception{
		
		SolrServer solrServer = new HttpSolrServer("http://192.168.188.128:8080/solr");
		solrServer.deleteById("test1");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
	
	@Test
	public void queryDocument() throws SolrServerException{
		SolrServer solrServer = new HttpSolrServer("http://192.168.188.128:8080/solr");
		SolrQuery query = new SolrQuery();
		query.setStart(0);
		query.setRows(2);
		query.setQuery("*:*");
		QueryResponse response = solrServer.query(query);
		
		SolrDocumentList results = response.getResults();
		long numFound = results.getNumFound();
		System.out.println(numFound);
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("item_title"));
		}
	}
}
