
# RMI

## Architecture

- Les interfaces sont le coeur de RMI.
- L�architecture RMI est bas� sur un principe important :
    - La d�finition du comportement et l�ex�cution de ce comportement sont des concepts s�par�s.
    - La d�finition d�un service distant est cod� en utilisant une interface Java.
    - L�impl�mentation de ce service distant est cod�e dans une classe


![socket tcp](doc/ArchitectureRMI.jpg)


## Impl�mentation Serveur

- Cr�er les interfaces des objets distants
- Cr�er les impl�mentation des objets distants
- G�n�rer les stubs et skeletons
- Cr�er le serveur RMI
- Cr�er le client RMI
- D�ploiement Lancement
    - Lancer l�annuaire **RMIREGISTRY**
    - Lancer le serveur
    - Lancer le client



### Cr�ation des interfaces

- La premi�re �tape consiste � cr�er une interface distante qui d�crit les m�thodes que le client pourra invoquer � distance.
- Pour que ses m�thodes soient accessibles par le client, cette interface doit h�riter de l'interface **Remote**.
- Toutes les m�thodes utilisables � distance doivent pouvoir lever les exceptions de type **RemoteException** qui sont sp�cifiques � l'appel distant.  ( On pourra utiliser la Class **UncicastRemoteObject** )
- Cette interface devra �tre plac�e sur les deux machines (serveur et client). Seules les interfaces doivent �tre sur le clients ( pas les impl�mentations ).


```java
package youcef.f;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITache extends Remote {
	public int addition(int a, int b) throws RemoteException;
}
```

### Impl�mentations des interfaces

```java
package youcef.f;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Tache extends UnicastRemoteObject implements ITache {

	protected Tache() throws RemoteException {
		super();
	}
	
	@Override
	public int addition(int a, int b) throws RemoteException {
		return a+b;
	}
}
```

### Cr�ation application server

```java
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
```

## Impl�mentation client

Importer Toutes interfaces impl�mentant la Class **Remote** sur le client soit manuellement en conservant le nom du package de l'interface soit depuis un export sous forme d'un fichier jar de toutes les interfaces n�cessaires.

Export d'un fichier jar depuis le server

![socket tcp](doc/exportDesInterfaces.jpg)


Import d'un fichier jar sur le client

![socket tcp](doc/importDesInterfaces.jpg)



Interface **ITache**


```java
package youcef.f;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITache extends Remote {
	public int addition(int a, int b) throws RemoteException;
}
```

L'application Client.

```java
import java.rmi.*;

import youcef.f.ITache;


public class ClientRmi {

	static int resultat;

	public static void main(String[] args) {

		// Le client recherche, dans le registry, la r�f�rence de la m�thode par son nom
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
```
## R�sultat

#### server side

```bash
Tache[UnicastServerRef [liveRef: [endpoint:[192.168.80.1:65170](local),objID:[3f0bf10f:171326b5e69:-7fff, 2563548728438886730]]]]
Serveur pr�t
```

#### Client side

```bash
15
```