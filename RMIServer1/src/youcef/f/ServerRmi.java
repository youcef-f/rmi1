package youcef.f;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerRmi {
	public static void main(String[] args) {
		try {

			// Cr�ation de l�objet Serveur.
			Tache tache = new Tache();

			// Enregistrement de l�objet dans le registry

			System.out.println(tache.toString());
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://127.0.0.1:1099/Addition", tache);
			System.out.println("Serveur pr�t");

		}

		catch (Exception e) {

			System.err.println("Erreur: " + e.getMessage());

		}

	}
}
