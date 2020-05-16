package com.redhat.summit.gov;

import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.component.jackson.JacksonDataFormat;
import java.util.Random;


public class Sender extends RouteBuilder {

  
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		JacksonDataFormat jacksonDataFormat = new JacksonDataFormat();
    jacksonDataFormat.setUnmarshalType(SingalInput.class);
    
	
    from("timer:sender?period=3000")
	  //.setBody().method(this, "genRandoSingalInput()")
	  //.bean(beanType)
	  .setBody().simple("Type:[Virus] Genuses:[MERSvirus]")
      .marshal(jacksonDataFormat)
	  .setHeader("CE-Type", constant("dev.knative.humancontact"))
      .log("${body}")
      .to("knative:endpoint/virus-dispatcher");
		
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