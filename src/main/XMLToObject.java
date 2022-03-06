package main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import beans.Concierto;
import beans.Participante;

public class XMLToObject {

	public static void main(String[] args) {
		
		
		try {
			JAXBContext contexto = JAXBContext.newInstance(Concierto.class);
			
			//Esta vez creamos un objeto que nos permite pasar
			//de XML a Object, es decir deserializar
			
			Unmarshaller u = contexto.createUnmarshaller();
			File fichero = new File("conciertos.xml");
			if (fichero.exists()) {
				Concierto c = (Concierto)u.unmarshal(fichero);
				
				System.out.println(c.getParticipante());
				
			} else {
				System.out.println("Fichero XML Concierto.xml no encontrado");
			}

		} catch (JAXBException e) {
			System.out.println(e.getMessage());
		}

	}

}
