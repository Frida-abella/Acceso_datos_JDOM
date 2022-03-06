package main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import beans.Concierto;
import beans.Participante;

public class ObjectToXML {

	public static void main(String[] args) {
		
		
		JAXBContext contexto;
		try {
			
			contexto = JAXBContext.newInstance(Concierto.class);//inyeccion de dependecia
		} catch (JAXBException e) {
			System.out.println("Error creando el contexto");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}

		Marshaller m;
		try {
			/*
			 serializamos el objeto y lo convertimos en XML
			 */
			m = contexto.createMarshaller();
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			//añadimos los participantes al concierto

			Concierto concierto = new Concierto();
			concierto.setFecha("22-OCT-2022");
			concierto.setHora("22:00");	
			concierto.getParticipante().add(new Participante("22:30","Green Day"));
			concierto.getParticipante().add(new Participante("23:15","The Offspring"));
			concierto.getParticipante().add(new Participante("00:00","Simple Plan"));
			concierto.getParticipante().add(new Participante("00:45","Coldplay"));
			concierto.getParticipante().add(new Participante("01:15","U2"));
			
			//Convertimos un objeto a xml y lo imprimimos por pantalla
			m.marshal(concierto, System.out);
			//tambien podemos crear un fichero
			m.marshal(concierto, new File("conciertos.xml"));
			System.out.println("Se ha creado con éxito el fichero");
		} catch (JAXBException e) {
			System.out.println("Error convertiendo el objeto a formato XML");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
