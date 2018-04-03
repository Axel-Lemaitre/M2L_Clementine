package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Activite;
import model.Seance;
import model.Senior;
/**
 * Classe de persistance des objets dans une base SQL
 * @author xavier
 *
 */
public abstract class Persistance {
	
	/**
	 * Méthode d'INSERT d'un nouveau sénior
	 * @param nom le nom du nouveau sénior
	 * @param numSecu le numéro de sécurité sociale du nouveau sénior
	 * @throws SQLException l'exception SQL levée
	 */
	public static void insertSenior(String nom, String numSecu) throws SQLException{
		Connection cn = Persistance.connection();
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Senior.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		try {
			// Création d'un client
			Senior c = new Senior(numSecu, nom);
			// Enregistrement dans la BDD
			session.save(c);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
			
//		Statement stmt;
//		try{
//			 stmt = cn.createStatement();
//			 stmt.executeUpdate("INSERT INTO senior (numSecu,nom) VALUES ('"+numSecu+"','"+nom+"')");
//		}catch (SQLException e){
//			throw e;
//		}
		finally{
			Persistance.closeConnection(cn);
		}
	}
	
	public static void associeSeniorActivite(ArrayList<Seance> seances, String numSecuSenior ) throws SQLException{
		Connection cn = Persistance.connection();
		Statement stmt;
//		Statement stmt2;
//		ResultSet rs = null;
		
		for(Seance seance : seances)
			System.out.println(seance.getCode());
		
		try{
			stmt = cn.createStatement();
//			stmt2 = cn.createStatement();
			for(Seance laSeance : seances){
				stmt.executeUpdate("INSERT INTO participer VALUES('"+numSecuSenior+"',"+laSeance.getCode()+")");
//				System.out.println(laSeance.getDateSeance());
			}
//			rs = stmt.executeQuery("SELECT code FROM seance WHERE idact="+idSeance);
//			while (rs.next()){
//				if(stmt2.executeQuery("SELECT * FROM participer WHERE numsecu='"+numSecuSenior+"' AND codeseance="+rs.getString(1))== null){
//					stmt2.executeUpdate("INSERT INTO participer VALUES('"+numSecuSenior+"',"+rs.getString(1)+")");
//				}else{
//					//Faire remonter une erreur
//				}
//			}
		}catch(SQLException e){
			throw e;
		}
		finally{
			Persistance.closeConnection(cn);
		}
	}
	
	/**
	 * Méthode de SELECT des séniors
	 * @return un objet ArrayList contenant tous les objets Senior
	 * @throws SQLException l'exception SQL levée
	 */	
	public static ArrayList<Senior> selectSenior() throws SQLException{
		Connection cn = Persistance.connection();
		Statement stmt;
		ResultSet rs = null;
		ArrayList<Senior>liste =new ArrayList<Senior>();
		try{
			stmt= cn.createStatement();
	    	//Définition de la requete pour construire le jeu d'enregistrement
			rs = stmt.executeQuery("SELECT * FROM senior");
			 while (rs.next()) 
		        {
				liste.add(new Senior(rs.getString(1),rs.getString(2)));
		        }
		}
		catch(SQLException e) 
		{
			throw e;
		}
	    finally{
	    	Persistance.closeConnection(cn);
	    }
		return liste;
	}
	
	
	/**
	 * Méthode de SELECT des activités
	 * @return un bjet ArrayList contenant tous les objets Activités
	 * @throws SQLException l'exception SQL levée
	 */
	public static ArrayList<Activite> selectActivite() throws SQLException{
		Connection cn = Persistance.connection();
		Statement stmt;
		ResultSet rs = null;
		ArrayList<Activite>liste =new ArrayList<Activite>();
		try{
			stmt= cn.createStatement();
	    	//Définition de la requete pour construire le jeu d'enregistrement
			rs = stmt.executeQuery("SELECT * FROM activite");
			 while (rs.next()) 
		        {
				liste.add(new Activite(rs.getInt(1),rs.getString(2),rs.getInt(3)));
		        }
		}
		catch(SQLException e) 
		{
			throw e;
		}
	    finally{
	    	Persistance.closeConnection(cn);
	    }
		return liste;		
	}
	
	public static ArrayList<Seance> selectSeance(String designation) throws SQLException{
		Connection cn = Persistance.connection();
		Statement stmt;
		ResultSet rs = null;
		ArrayList<Seance>liste =new ArrayList<Seance>();
		try{
			stmt= cn.createStatement();
	    	//Définition de la requete pour construire le jeu d'enregistrement
			rs = stmt.executeQuery("SELECT * FROM seance INNER JOIN activite ON idact=identifiant WHERE designation='"+designation+"'");
			 while (rs.next()) 
		        {
				liste.add(new Seance(rs.getInt(1),rs.getDate(2)));
		        }
		}
		catch(SQLException e) 
		{
			throw e;
		}
	    finally{
	    	Persistance.closeConnection(cn);
	    }
		return liste;
	}
	/**
	 * Méthode de SELECT des séniors
	 * @return un tableau contenant tous les objets Senior
	 * @throws SQLException l'exception SQL levée
	 */	
	public static Senior[] selectSeniorT() throws SQLException{
		Connection cn = Persistance.connection();
		Statement stmt;
		ResultSet rs = null;
		Senior[] liste=null;
		try{
			stmt= cn.createStatement();
	    	//Définition de la requete pour construire le jeu d'enregistrement
	    	rs = stmt.executeQuery("SELECT count(*) FROM senior");
			//Récupération du nombre de lignes du jeu d'enregistrement
	    	rs.next();
			int rowCount=rs.getInt(1);
			liste =new Senior[rowCount];
			rowCount=0;
	    	//Définition de la requete pour construire le jeu d'enregistrement
			rs = stmt.executeQuery("SELECT * FROM senior");
			 while (rs.next()) 
		        {
				liste[rowCount]=new Senior(rs.getString(1),rs.getString(2));
				rowCount++;
		        }
		}
		catch(SQLException e) 
		{
			throw e;
		}
	    finally{
	    	Persistance.closeConnection(cn);
	    }
		return liste;
	}
	
	/**
	 * Méthode de connexion à la BD
	 * @return une connexion active sur la BD
	 * @throws SQLException l'exception SQL levée
	 */
	private static Connection connection() throws SQLException{
//		String host = "192.168.222.86:5432";
//		String base = "M2L";
//		String user = "groupe3";
//		String passwd = "groupe3";
//		Connection conn = null;
//		try
//		{
//			String connectionString ="jdbc:postgresql://"+host+"/"+base+"?user="+user+"&password="+passwd;
//			conn = DriverManager.getConnection(connectionString);
//		}
//		catch (SQLException e) 
//		{
//			throw e;
//		}
//		return conn; 
		
		String jdbcURL = "jdbc:postgresql://192.168.222.86:5432/M2L";
		String user = "groupe3";
		String password = "groupe3";
		
		Connection myConn;
		
		try {
		System.out.println("Connexion à la BDD " + jdbcURL);
		myConn = DriverManager.getConnection(jdbcURL,user,password);
		System.out.println("Connexion réussie !!!");
		
		return myConn;
		}
		catch(Exception e) {
		e.printStackTrace();
		return null;
		}
		
		
	}
	
	/**
	 * Méthode de clôture de connexion
	 * @param conn la connexion à fermer
	 * @throws SQLException l'exception SQL levée
	 */
	private static void closeConnection(Connection conn) throws SQLException{
		try {
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
	}

}
