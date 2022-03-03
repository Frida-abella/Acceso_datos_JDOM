package jdom;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Principal {
	
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder analizador;
		Document doc;
		
		try {
			analizador = factory.newDocumentBuilder();
			// Creamos nuevo documento
			doc = analizador.newDocument();
			
			// Añadimos elemento raiz
			Element concierto = doc.createElement("concierto");
			doc.appendChild(concierto);
			
			// Añadimos todo los datos al elemento raíz concierto:
			agregarDatos(concierto, doc);
			
			// Guardamos en disco el nuevo documento XML.
			guardar(doc);
			
			System.out.println("El archivo se ha creado con éxito");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void agregarDatos (Element concierto, Document doc) {
		
		// Agregamos el primer nodo de texto y su contenido, y lo hacemos añadimos como nodo hijo del nodo raíz concierto
		Element fecha = doc.createElement("fecha");
		fecha.appendChild(doc.createTextNode("20-oct-2018"));
		concierto.appendChild(fecha);	
		
		// Creamos el nodo de texto de "hora"
		Element hora = doc.createElement("hora");
		hora.appendChild(doc.createTextNode("21:30"));
		concierto.appendChild(hora);
		
		
		// Agregamos el nodo-elemento de participantes, que contendrá varios
		Element participantes = doc.createElement("participantes");
		concierto.appendChild(participantes);
		
		// Creamos el primer nodo-elemento participante
		Element participante = doc.createElement("participante");
		participantes.appendChild(participante);
		
		// Creamos el nodo de texto de "entrada" dentro del primer participante
		Element entrada = doc.createElement("entrada");
		entrada.appendChild(doc.createTextNode("21:30"));
		participante.appendChild(entrada);
		// Creamos el nodo de texto de "grupo" dentro del primer participante
		Element grupo = doc.createElement("grupo");
		grupo.appendChild(doc.createTextNode("Las ardillas de Dakota"));
		participante.appendChild(grupo);
		
		// Creamos el segundo participante
		participante = doc.createElement("participante");
		participantes.appendChild(participante);
				
		// Creamos el nodo de texto de "entrada" dentro del segundo participante
		entrada = doc.createElement("entrada");
		entrada.appendChild(doc.createTextNode("22:15"));
		participante.appendChild(entrada);
		// Creamos el nodo de texto de "grupo" dentro del segundo participante
		grupo = doc.createElement("grupo");
		grupo.appendChild(doc.createTextNode("Fito y Fitipaldis"));
		participante.appendChild(grupo);
		
		// Repetimos el proceso para el tercer participante
		participante = doc.createElement("participante");
		participantes.appendChild(participante);
		entrada = doc.createElement("entrada");
		entrada.appendChild(doc.createTextNode("23:00"));
		participante.appendChild(entrada);
		grupo = doc.createElement("grupo");
		grupo.appendChild(doc.createTextNode("Coldplay"));
		participante.appendChild(grupo);
				
	}
	
	private static void guardar(Document doc) throws TransformerException {
		
		// Creamos la fabrica de objetos para crear objeto Transformer 
		TransformerFactory fabricaConversor = TransformerFactory.newInstance();
		//creamos el objeto Transfomer, que nos permitira serializar el arbol dom a un fichero
		Transformer conversor = fabricaConversor.newTransformer();
		//creamos la fuente de la cual sacaremos el arbol dom
		DOMSource fuente = new DOMSource(doc); 
		//Creamos el flujo de salida, al fichero que queremos 
		StreamResult resultado = new StreamResult(new File("concierto.xml"));
		//por ultimo, serializamos los datos
		conversor.transform(fuente, resultado);
	}
}
