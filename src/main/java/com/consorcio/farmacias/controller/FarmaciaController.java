package com.consorcio.farmacias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.consorcio.farmacias.service.FarmaciaService;
import com.consorcio.farmacias.to.FarmaciaRequestTo;
import com.consorcio.farmacias.to.FarmaciaResponseTo;

@RestController
@RequestMapping("/getfarmaciasTurno")
public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<FarmaciaResponseTo> getFarmacasDeTurno(@RequestBody FarmaciaRequestTo farmaciaRequest) {

		List<FarmaciaResponseTo> result = farmaciaService.getFarmaciaTurno(farmaciaRequest);

		return result;
	}

}
