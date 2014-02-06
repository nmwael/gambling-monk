package com.williamhill;

import static org.hamcrest.Matchers.containsString;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class BaseTests {
	
	
	private InputStream oldIn;
	private PrintStream oldOut;
	private PipedOutputStream pipe;
	private PipedInputStream testIn;
	private PrintStream mockOut;
	private Mockery context;
	
	


	@Before
	public void setup() throws IOException
	{
		 context = new Mockery() {{
		        setImposteriser(ClassImposteriser.INSTANCE);
		    }};
		    
	    oldIn = System.in;
	    oldOut = System.out;
	    pipe = new PipedOutputStream();
	    testIn = new PipedInputStream(pipe);
	    mockOut = context.mock(PrintStream.class);
	    System.setOut(mockOut);
	    System.setIn(testIn);

	}
	


	@After
	public void resetAndCheckIfOk()
	{
		 context.assertIsSatisfied();
	    System.setIn(oldIn);
	    System.setOut(oldOut);
	}



	@Test
	public void test3ParametersWorking() {
		
	    Expectations exp = new Expectations()
	    {
	        {
	        	
	            oneOf(mockOut).println(with(org.hamcrest.text.StringContains.containsString("testFolders\\findme.txt")));
	            oneOf(mockOut).println(with(org.hamcrest.text.StringContains.containsString("testFolders\\folderA\\findme.txt")));
	            oneOf(mockOut).println(with(org.hamcrest.text.StringContains.containsString("testFolders\\folderA\\folderB\\findme.txt")));
	            
	            
	        }
	    };
	    context.checking(exp);	
	String[] arguments=new String[]{"-f","findme.txt","testFolders"}; 
		App.main(arguments);
	}
	
	@Test
	public void test3ParametersBogusDirectory() {
		
	    Expectations exp = new Expectations()
	    {
	        {
	        	
	            oneOf(mockOut).println(with(org.hamcrest.text.StringContains.containsString(App.NOT_EXISTING_DIRECTORY_HELP)));
	            
	            
	        }
	    };
	    context.checking(exp);	
	String[] arguments=new String[]{"-f","findme.txt","testFalders"}; 
		App.main(arguments);
	}
	@Test
	public void test3ParametersDirectoryIsAFile() {
		
	    Expectations exp = new Expectations()
	    {
	        {
	        	
	            oneOf(mockOut).println(with(org.hamcrest.text.StringContains.containsString(App.NOT_A_VALID_DIRECTORY_ERROR)));
	            
	            
	        }
	    };
	    context.checking(exp);	
	String[] arguments=new String[]{"-f","findme.txt","testFolders\\findme.txt"}; 
		App.main(arguments);
	}

	
	
	
	@Test
	public void test2Parameters() {
	    Expectations exp = new Expectations()
	    {
	        {
	        	
	            oneOf(mockOut).println(with(org.hamcrest.text.StringContains.containsString(App.VALID_ARGUMENTS_HELP)));
	            oneOf(mockOut).println(with(org.hamcrest.text.StringContains.containsString(App.CURRENT_VALID_ARGUMENTS_HELP)));	            
	            
	        }
	    };
	    context.checking(exp);
		
	String[] arguments=new String[]{"-f","pom.xml"}; 
		App.main(arguments);
	}


}
