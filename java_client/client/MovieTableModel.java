package br.com.app.client;

import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class MovieTableModel extends AbstractTableModel{
		 
		   private final List<Movie> movies;
		 
		   public MovieTableModel(List<Movie> movies) {
		     this.movies = movies;
		   }
		 
		   @Override
		   public int getColumnCount() {
		     return 5;
		   }
		 
		   @Override
		   public int getRowCount() {
		     return movies.size();
		   }
		   
		   @Override
		   public String getColumnName(int column) {
		     switch (column) {
		     case 0:
		        return "TITLE";
		     case 1:
		        return "YEAR";
		     case 2:
		        return "STUDIO";
		     case 3:
		        return "DUR";
		     case 4:
		        return "GENDER";
		     }
		     return "";
		   }
		 
		   @Override
		   public Object getValueAt(int rowIndex, int columnIndex) {
			   
		     Movie m = movies.get(rowIndex);
		     
		     switch (columnIndex) {
		     case 0:
		       return m.getTitle();
		     case 1:
		    	return m.getYear();
		     case 2:		       
		       return m.getEsp().getStudio();		       
		     case 3:
			   return m.getDuration();
		     case 4:
			   return m.getEsp().getGender();			   
		     }
		     return null;
		   }

}
