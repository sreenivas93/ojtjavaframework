package DriverFactory;

import org.testng.annotations.Test;

public class AppTest 
{              
@Test
 public void startTest() throws Throwable
 {
	try
	{
	DriverScript ds= new DriverScript();
	ds.ERP_Account();
	}catch(Throwable t)
	{
		System.out.println(t.getMessage());
	}
 }
}
