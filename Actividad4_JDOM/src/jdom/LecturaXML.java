package jdom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecturaXML {

	public static void main(String[] args) {
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		DocumentBuilder analizador;
		Document doc;
		Node raiz;
		
		try {
			analizador = fabrica.newDocumentBuilder();
			doc = analizador.parse("concierto.xml");
			raiz = doc.getDocumentElement();
			recorrerNodos(raiz);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void recorrerNodos(Node raiz) {
		//este metodo nos devuelve todos los nodos hijos Directos del elemento raiz "concierto"
		NodeList nodos = raiz.getChildNodes();

		// Dentro de esa lista de nodos, accedemos a los que se corresponden con los dos primeros, fecha y hora:
		Node fecha = nodos.item(0);
		Node hora = nodos.item(1);
		Node participantes = nodos.item(2);
		// Lo sacamos por pantalla
		System.out.println("Fecha y hora del concierto: " + fecha.getTextContent() + " " + hora.getTextContent());
		recorrerParticipantes(participantes);
	}
	
	// Con el siguiente método hacemos una iteración para ir recorriendo uno a uno los diferentes elementos del nodo participantes
	private static void recorrerParticipantes (Node participantes) {
		// En esta lista obtenemos el conjunto de los elementos "participante"
		NodeList nodos = participantes.getChildNodes();
		System.out.println("Participan los siguientes grupos: ");
		for (int i=0; i<nodos.getLength();i++) {
			// En este objeto almacenamos el contenido del "participante" que esté en la posición recorrida por el for
			Node participante = nodos.item(i);
			// Recorremos sólo los nodos de tipo elemento, para descartar los espacios en blanco que cuentan como texto
			if (participante.getNodeType() == Node.ELEMENT_NODE) {
				//dame los atriburos del nodo escala, y quiero el atributo 0, es decir
				//el prierm atributo, a continuacion de pido el valor de ese nodo atributo
				Node entrada = participante.getChildNodes().item(0);
				Node grupo = participante.getChildNodes().item(1);
				System.out.print(" " + entrada.getTextContent() + "\n");
				System.out.print(" " + grupo.getTextContent()+ "\n");
			}
		}
		System.out.println();
	}
}
