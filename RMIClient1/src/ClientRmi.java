import java.rmi.*;

import youcef.f.ITache;



public class ClientRmi {

	static int resultat;

	public static void main(String[] args) {

		// Le client recherche, dans le registry, la référence de la méthode par son nom
		// (Addition)
		try {
			ITache stub = (ITache) Naming.lookup("rmi://127.0.0.1:1099/Addition");
		   System.out.println(stub.addition(5, 10)); 
		   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}