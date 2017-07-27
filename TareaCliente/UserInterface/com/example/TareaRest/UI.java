package com.example.TareaRest;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import com.example.TareaRest.Olimpiada;
import com.example.TareaRest.SedeJJOO;

public class UI {

	public UI () {toDisplay();}
	
	private int auxiliar = -1;
	private int anoViejo = -1;
	private ArrayList<Olimpiada> toShow;
	private ArrayList<SedeJJOO> toShowView;
	private ArrayList<SedeJJOO> toShowModify;
	private ArrayList<SedeJJOO> toShowDelete;
	
	public void toDisplay()
	{
		toShow = WebPetition.getOlimpiadas();
		
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setSize (1024, 780);
		Monitor primary = display.getPrimaryMonitor ();
		Rectangle bounds = primary.getBounds ();
		Rectangle rect = shell.getBounds ();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation (x, y);
		
		/*RowLayout rowLayout = new RowLayout(2);
        rowLayout.wrap = false;
        rowLayout.pack = false;
        rowLayout.justify = true;
        rowLayout.type = SWT.VERTICAL;
        rowLayout.marginLeft = 5;
        rowLayout.marginTop = 5;
        rowLayout.marginRight = 5;
        rowLayout.marginBottom = 5;
        rowLayout.spacing = 0;
        shell.setLayout(rowLayout);*/
		shell.setLayout(new GridLayout());
		
		
		//Disposicion de los elementos sobre una tabla de una sola columna
		Table table = new Table (shell, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);
		TableColumn column = new TableColumn (table, SWT.NONE);
		column.setText ("Historía de las olimpiadas por países.");
		
		for (int i = 0; i< toShow.size(); i++)
		{
			TableItem item = new TableItem (table, SWT.NONE);
			item.setText (0, toShow.get(i).toString());
		}
		column.pack();
        
        /* Disposicion por etiquetas sobre el shell
         * 
         * for (int i = 0; i< toShow.size(); i++)
		{
			System.out.println(toShow.get(i).toString());
			
			Label label = new Label (shell, SWT.LEFT);
			label.setText (toShow.get(i).toString());
		}*/
        
        Button button = new Button(shell, SWT.PUSH);
        button.setText("Sede Juegos olimpicos");
        button.pack();
        button.addSelectionListener(new SelectionListener() {
        	 
        	   @Override
        	   public void widgetSelected(SelectionEvent arg0) {
        		   Shell shell2 = new Shell (shell);
        		   shell2.setSize (1024, 780);
        		   //Posicion en monitor y tamaño
        		   Monitor primary = display.getPrimaryMonitor ();
        		   Rectangle bounds = primary.getBounds ();
        		   Rectangle rect = shell2.getBounds ();
        		   int x = bounds.x + (bounds.width - rect.width) / 2;
        		   int y = bounds.y + (bounds.height - rect.height) / 2;
        		   shell2.setLocation (x+80, y+80);
        		   toDisplayShell2(shell2);
        		   //shell2.pack();
        		   shell2.open ();
        			while (!shell2.isDisposed ()) {
        			   if (!display.readAndDispatch ()) display.sleep ();
        			}
        	   }
        	 
        	   @Override
        	   public void widgetDefaultSelected(SelectionEvent arg0) {
        	   }
        });
        
		shell.open ();
		while (!shell.isDisposed ()) {
		   if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
	
	public void toDisplayShell2(Shell sh)
	{
		toShowView = WebPetition.getSedes();
		
		final TabFolder tabFolder = new TabFolder (sh, SWT.BORDER);
		Rectangle clientArea = sh.getClientArea ();
		tabFolder.setSize(1024 - 23, 760 - 25);
		tabFolder.setLocation (clientArea.x, clientArea.y);
		tabFolder.setLayout(new GridLayout());
		
		//Añadimos pestañanas a la nueva ventana
		TabItem itemView = new TabItem (tabFolder, SWT.NONE);
		itemView.setText ("Ver todo ");
		//Lista de pestaña ver
		final List list = new List (tabFolder, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		for (int i = 0; i< toShowView.size(); i++)
		{
			list.add (toShowView.get(i).toString());
		}
		itemView.setControl(list);
		
		TabItem itemNew = new TabItem (tabFolder, SWT.NONE);
		itemNew.setText ("Añadir nuevo");
		openNewForm(sh, tabFolder,itemNew);
		
		TabItem itemModify = new TabItem (tabFolder, SWT.NONE);
		itemModify.setText ("Modificar existente");
		openModifyForm(sh, tabFolder,itemModify);
		
		TabItem itemDelete = new TabItem (tabFolder, SWT.NONE);
		itemDelete.setText ("Borrar uno");
		openDeleteForm(sh, tabFolder,itemDelete);		
		//tabFolder.pack ();
		
		tabFolder.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
						
				//toShowModify = WebPetition.getSedes();
				
				//toShowDelete = WebPetition.getSedes();
				toShowView = WebPetition.getSedes();
				
				openModifyForm(sh, tabFolder, itemModify);
				openDeleteForm(sh, tabFolder, itemDelete);
				
				list.removeAll();
				
				for (int i = 0; i< toShowView.size(); i++)
				{
					list.add (toShowView.get(i).toString());
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void openNewForm(Shell sh, TabFolder tabFolder,TabItem item)
	{
		ArrayList<ArrayList<String>> toShow = WebPetition.getPaises();
		
		String[] datosPais = new String[toShow.size()];
		String[] datosIDPais = new String[toShow.size()];
		String[] data  = {"Invierno", "Verano"};
		
		Label label1,label2;
		Text ano;
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 1;
	    composite.setLayout(compositeLayout);
		
		label1 = new Label(composite, SWT.NULL);
		label1.setText("Introduzca el nuevo año: ");
		
		ano = new Text(composite, 0);
		ano.setText("");
		
		Label separator1 = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	    separator1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		  
	    label2=new Label(composite, SWT.NULL);
		label2.setText("Seleccione un país: ");
	    
		final Combo paises = new Combo(composite, SWT.READ_ONLY);
	    paises.setBounds(50, 50, 150, 65);
	    for (int i = 0; i < toShow.size(); i++)
	    {
	    	datosIDPais[i] = toShow.get(i).get(0);
	    	datosPais[i] = toShow.get(i).get(1);
	    }
	    for (int z = 0; z < datosPais.length; z++)
	    {
	    	System.out.println(datosPais[z]);
	    }
	    paises.setItems(datosPais);
		  
		Label separator2 = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	    separator2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
	    label2=new Label(composite, SWT.NULL);
		label2.setText("Seleccione una época: ");
	    
		final Combo epoca = new Combo(composite, SWT.READ_ONLY );
	    epoca.setBounds(50, 50, 150, 65);
	    epoca.setItems(data);
		
		Label separator3 = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	    separator3.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		Button button=new Button(composite,SWT.PUSH | SWT.HORIZONTAL);
		button.setText("Crear nuevo");
		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {				  
				if(ano.getText() == "" | Integer.parseInt(ano.getText()) < 0 | Integer.parseInt(ano.getText()) > 2050 ) { 
					MessageBox messageBox = new MessageBox(sh, SWT.OK | SWT.ICON_WARNING |SWT.CANCEL);
					messageBox.setMessage("Introduzca un año correcto");
					messageBox.open();
				}
				
				else {
					if(paises.getText() == "" | epoca.getText() == ""){
						MessageBox messageBox = new MessageBox(sh, SWT.OK |SWT.ICON_WARNING |SWT.CANCEL);
						messageBox.setMessage("Haga una selección correcta de país y época");
						messageBox.open();
					}
					else{
						int sede = Integer.parseInt(datosIDPais[paises.getSelectionIndex()]);
						int tipo = 0;
						if(epoca.getText() == "Invierno")
							tipo = 1;
						else
							tipo = 2;
						
						System.out.println(ano.getText() + "troll" + sede + " ;" + tipo);
						if (WebPetition.newSede(Integer.parseInt(ano.getText()), sede, tipo))
						{
							MessageBox messageBox=new MessageBox(sh, SWT.OK|SWT.CANCEL);
						    messageBox.setMessage("Transacción correcta");
							messageBox.open();
						}
						
						else 
						{
							MessageBox messageBox=new MessageBox(sh, SWT.ICON_INFORMATION | SWT.OK|SWT.CANCEL);
						    messageBox.setMessage("Transacción incorrecta. Año ya existente");
							messageBox.open();
						}
						
						paises.deselectAll();
						ano.setText("");
						epoca.deselectAll();
					}
				}
			}
		});
		
		ano.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
		item.setControl(composite);
	}
	
	
	private void openModifyForm(Shell sh, TabFolder tabFolder,TabItem item)
	{
		toShowModify = WebPetition.getSedes();
		
		String[] datosPais = new String[toShowModify.size()];
		String[] data  = {"Invierno", "Verano"};
		
		Label label1,label2;
		Text ano;
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 1;
	    composite.setLayout(compositeLayout);
		
		label1 = new Label(composite, SWT.NULL);
		label1.setText("Seleccione el país a modificar: ");
		
		auxiliar = -1;
		
		final Combo paises = new Combo(composite, SWT.READ_ONLY);
	    paises.setBounds(50, 50, 150, 65);
	    for (int i = 0; i < toShowModify.size(); i++)
	    {
	    	datosPais[i] = (toShowModify.get(i).getCiudad() + "; " + toShowModify.get(i).getAno() );
				    	
	    }
	    paises.setItems(datosPais);
	    
	    Label separator1 = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	    separator1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
	    label2=new Label(composite, SWT.NULL);
		label2.setText("Seleccione un año: ");
	    
		ano = new Text(composite, 0);
		ano.setText("");
		ano.setEnabled(false);

		Label separator2 = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	    separator2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
	    label2=new Label(composite, SWT.NULL);
		label2.setText("Seleccione nueva época:");
	    label2.setEnabled(false);
	    
		final Combo epoca = new Combo(composite, SWT.READ_ONLY );
	    epoca.setBounds(50, 50, 150, 65);
	    epoca.setItems(data);
	    epoca.setEnabled(false);
		
		Label separator3 = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	    separator3.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    
		Button button=new Button(composite,SWT.PUSH | SWT.HORIZONTAL);
		button.setText("Modificar");
		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {				  
				if(ano.getText() == "" | Integer.parseInt(ano.getText()) < 0 | Integer.parseInt(ano.getText()) > 2050 ) { 
					MessageBox messageBox = new MessageBox(sh, SWT.OK | SWT.ICON_WARNING |SWT.CANCEL);
					messageBox.setMessage("Introduzca un año correcto");
					messageBox.open();
				} else {
					if(paises.getText() == "" | epoca.getText() == ""){
						MessageBox messageBox = new MessageBox(sh, SWT.OK |SWT.ICON_WARNING |SWT.CANCEL);
						messageBox.setMessage("Haga una selección correcta de país y época");
						messageBox.open();
					}
					else{
						//int sede = toShowModify.get(auxiliar).getSede();
						int tipo = 0;
						if(epoca.getText() == "Invierno")
							tipo = 1;
						else
							tipo = 2;
						
						auxiliar = -1;
						
						if (WebPetition.modifySede(anoViejo, Integer.parseInt(ano.getText()), tipo))
						{
							MessageBox messageBox=new MessageBox(sh, SWT.OK|SWT.CANCEL);
						    messageBox.setMessage("Transacción correcta");
							messageBox.open();
						}
						
						else 
						{
							MessageBox messageBox=new MessageBox(sh, SWT.ICON_INFORMATION | SWT.OK|SWT.CANCEL);
						    messageBox.setMessage("Transacción incorrecta. Año ya existente");
							messageBox.open();
						}
						
						paises.deselectAll();
						ano.setText("");
						epoca.deselectAll();
						
						//toShowModify = WebPetition.getSedes();
					}
				}
			}
		});
		button.setEnabled(false);
		
		paises.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				auxiliar = paises.getSelectionIndex();
				ano.setText(String.valueOf(toShowModify.get(auxiliar).getAno()));
				anoViejo = toShowModify.get(auxiliar).getAno();
				if(toShowModify.get(auxiliar).getId_tipo_jjoo() == 1)
					epoca.select(0);
				else
					epoca.select(1);
		

				ano.setEnabled(true);
				epoca.setEnabled(true);
				button.setEnabled(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
						
			}
		});
		
		ano.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
		item.setControl(composite);
	}
	
	private void openDeleteForm(Shell sh, TabFolder tabFolder,TabItem item)
	{
		toShowDelete = WebPetition.getSedes();
		
		String[] datosPais = new String[toShowDelete.size()];
				
		Label label1,label2;
		Text ano, epoca;
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 1;
	    composite.setLayout(compositeLayout);
		
		label1 = new Label(composite, SWT.NULL);
		label1.setText("Seleccione el país a modificar: ");
		
		auxiliar = -1;
		
		final Combo paises = new Combo(composite, SWT.READ_ONLY);
	    paises.setBounds(50, 50, 150, 65);
	    for (int i = 0; i < toShowDelete.size(); i++)
	    {
	    	datosPais[i] = (toShowDelete.get(i).getCiudad() + "; " + toShowDelete.get(i).getAno() );
				    	
	    }
	    paises.setItems(datosPais);
	    
	    Label separator1 = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	    separator1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
	    label2=new Label(composite, SWT.NULL);
		label2.setText("En el año: ");
	    
		ano = new Text(composite, SWT.READ_ONLY);
		ano.setText("");
		ano.setEnabled(false);

		Label separator2 = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	    separator2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
	    label2=new Label(composite, SWT.NULL);
		label2.setText("Seleccione nueva época:");
	    label2.setEnabled(false);
	    
	    epoca = new Text(composite, SWT.READ_ONLY);
	    epoca.setText("");
	    epoca.setEnabled(false);
		
		Label separator3 = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	    separator3.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    
		Button button=new Button(composite,SWT.PUSH | SWT.HORIZONTAL);
		button.setText("Borrar");
		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {				  
				
				auxiliar = -1;
				if (WebPetition.deleteSede(Integer.parseInt(ano.getText())))
				{
					MessageBox messageBox=new MessageBox(sh, SWT.ICON_INFORMATION | SWT.OK|SWT.CANCEL);
				    messageBox.setMessage("Transacción correcta");
				    messageBox.open();				    
				}
				
				else 
				{
					MessageBox messageBox=new MessageBox(sh, SWT.OK|SWT.CANCEL);
				    messageBox.setMessage("Transacción incorrecta.");
					messageBox.open();
				}
				
				openDeleteForm(sh, tabFolder, item);
			}
		});
		button.setEnabled(false);
		
		paises.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ano.setText(String.valueOf(toShowDelete.get(paises.getSelectionIndex()).getAno()));
				epoca.setText(String.valueOf(toShowDelete.get(paises.getSelectionIndex()).getEpoca()));
				ano.setEnabled(true);
				epoca.setEnabled(true);
				button.setEnabled(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
						
			}
		});
		
		ano.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
		item.setControl(composite);
	}
}

