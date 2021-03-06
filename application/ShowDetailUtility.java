package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShowDetailUtility {
	private ArrayList<Salarier> sal;
	public ShowDetailUtility(int matricule) {
		sal = new ArrayList<Salarier>();
		try{  			
			PreparedStatement ps=Connexion.getCn().prepareStatement("select * from entreprise where matriculeE=?");
			ps.setInt(1, matricule);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())  
				if(rs.getString(8).matches("Employe")) {
					Employe a = new Employe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6));
					a.setSalaire(rs.getDouble(4));
					sal.add(a);
				}
				else {
					Vendeur v = new Vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6));
					v.setSalaire(rs.getDouble(4));
					sal.add(v);
				}
		}catch(Exception e){ System.out.println(e);} 
	}
	
	public List<Salarier> getImportlist() { return sal;}
	String name = null;
	String name2 = "Employe";
	boolean isEmp = false;
	public String getClassName() {
		for (Salarier sale : sal) {
			name = sale.getClass().getName().substring(12);
		}
		return name;
	}
	public boolean isss(String a) {
		if(a==null) return false;
		else if(a.compareTo(name2)==0)return true; 
		else return false;
		
	}
}
