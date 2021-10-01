package com.stripewebhooks.pullEventsfromSQS;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Hello world!
 *
 */
public class PullEventsFromSQS implements RequestHandler<Object, Object> 
{

	public Object handleRequest(Object input, Context context) {
		// TODO Auto-generated method stub
		
		System.out.println("the events are "+input);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
	      MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
	    map.add("event", input.toString());
	    
	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
	    RestTemplate restTemplate = new RestTemplate();
		   restTemplate.postForEntity("http://3.218.67.161:8092/kontrakt/webhook",
		    		request , String.class );
		      System.out.println("the event is forwrdd to kontrakt");
		      return 200;
		      
	}
    
}
