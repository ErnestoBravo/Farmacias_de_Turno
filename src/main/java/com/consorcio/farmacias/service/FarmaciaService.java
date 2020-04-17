package com.consorcio.farmacias.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.consorcio.farmacias.to.FarmaciaRequestTo;
import com.consorcio.farmacias.to.FarmaciaResponseTo;
import com.consorcio.farmacias.to.FarmaciaTo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FarmaciaService {

	public List<FarmaciaResponseTo> getFarmaciaTurno(FarmaciaRequestTo farmaciaRequest) {
		
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		List<FarmaciaResponseTo> response = new ArrayList<FarmaciaResponseTo>();

		try {

//		Se define la URL y se llama al servicio que trae las farmacias en turno, esto podria estar en un properties o ser sacado de una BD como mejora
		String uri = "https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion?id_region=7";
		String result = restTemplate.getForObject(uri, String.class);

//		Se pasa el resultado del servicio a una lista de tipo FarmaciaTo.
		List<FarmaciaTo> lista = Arrays.asList(mapper.readValue(result, FarmaciaTo[].class));
			
//		se recorre la lista de farmacias de turno filtrando por los parametros Comuna y nombre de local y se agrega a la lista de retorno de tipo farmaciaResponse.
			for (int i = 0; i < lista.size(); i++) {

				if (lista.get(i).getComuna_nombre().equalsIgnoreCase(farmaciaRequest.getComuna()) && 
					lista.get(i).getLocal_nombre().equalsIgnoreCase(farmaciaRequest.getNombreLocal())) {
					
					FarmaciaResponseTo farmaciaResponse = new FarmaciaResponseTo(); 
					
					farmaciaResponse.setDireccion(lista.get(i).getLocal_direccion());
					farmaciaResponse.setNombreLocal(lista.get(i).getLocal_nombre());
					farmaciaResponse.setTelefono(lista.get(i).getLocal_telefono());
					farmaciaResponse.setLatitus(lista.get(i).getLocal_lat());
					farmaciaResponse.setLongitud(lista.get(i).getLocal_lng());
					
					response.add(farmaciaResponse);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
}
