package com.example.TareaRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

@SpringBootApplication
public class Server {

	public static ConfigurableApplicationContext apc;
	
	public static void main(String[] args) {
		
		
		apc = SpringApplication.run(Server.class, args);
		
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setText("Server is Running");
		shell.setMinimumSize(0,0);
		shell.setLayout(new RowLayout());
		
		Button button = new Button(shell, SWT.PUSH);
        button.setText("RUN");
        button.pack();  
        
        Button button2 = new Button(shell, SWT.PUSH);
        button2.setText("STOP");
        button2.pack();
        
        button.addSelectionListener(new SelectionListener() {
       	 
     	   @Override
     	   public void widgetSelected(SelectionEvent arg0) {
     	   
     		   apc = SpringApplication.run(Server.class, args);
     		   button.setEnabled(false);
     		   button2.setEnabled(true);
     		   
     	   }
     	   @Override
     	   public void widgetDefaultSelected(SelectionEvent arg0) {
     	   }
     });
        
        button2.addSelectionListener(new SelectionListener() {
       	 
     	   @Override
     	   public void widgetSelected(SelectionEvent arg0) {
     	   
     		   apc.close();;
     		   button.setEnabled(true);
     		   button2.setEnabled(false);
     		   
     	   }
     	   @Override
     	   public void widgetDefaultSelected(SelectionEvent arg0) {
     	   }
     });
        
    button.setEnabled(false);
        
    shell.pack();    
    shell.open ();
	while (!shell.isDisposed ()) {
	   if (!display.readAndDispatch ()) display.sleep ();
	}
	display.dispose ();
        
	}
}
