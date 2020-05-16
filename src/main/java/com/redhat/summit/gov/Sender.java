package com.redhat.summit.gov;

import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.component.jackson.JacksonDataFormat;
import java.util.Random;


public class Sender extends RouteBuilder {

  
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		//JacksonDataFormat jacksonDataFormat = new JacksonDataFormat();
    	//jacksonDataFormat.setUnmarshalType(SingalInput.class);
		
	
    from("timer:sender?period=6000")
	  .setBody(method(this, "genRandoSingalInput().toString()"))
	  //.marshal(jacksonDataFormat)
	  //.setBody().simple("Type:[Virus] Genuses:[MERSvirus]")
	  .setHeader("CE-Type", constant("dev.knative.humancontact"))
      .log("${body}")
	.to("kafka:my-topic?brokers=my-cluster-kafka-bootstrap.demo-saude-digital-streams.svc:9092");
	
	
		
	}

	public static SingalInput genRandoSingalInput(){

		SingalInput input = new SingalInput();
		Random generator = new Random();
		String[] genuses = {"Alphacoronavirus","Betacoronavirus","MERSvirus","Novalvirus"};
		//
		int randomIndex = generator.nextInt(genuses.length);
  
		input.setType("Virus");
		input.setGenuses(genuses[randomIndex]);
		 
		return input;
	}
	public static class SingalInput {
  
	  String type;
	  String genuses;
  
	  public String getType(){
		return genuses;
	  }
	  public String getGenuses(){
		return genuses;
	  }
  
	  public void setType(String type){
		this.type = type;
	  }
  
	  public void setGenuses(String genuses){
		this.genuses = genuses;
	  }
  
	  @Override
	  public String toString(){
		  return "Type:["+type+"] Genuses:["+genuses+"]";
	  }
	}
}