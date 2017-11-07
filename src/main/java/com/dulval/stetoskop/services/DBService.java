package com.dulval.stetoskop.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dulval.stetoskop.domain.City;
import com.dulval.stetoskop.domain.State;
import com.dulval.stetoskop.repositories.UserRepository;
import com.dulval.stetoskop.repositories.CityRepository;
import com.dulval.stetoskop.repositories.StateoRepository;
import com.dulval.stetoskop.repositories.MedicamentRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private StateoRepository estadoRepository;
	@Autowired
	private CityRepository cidadeRepository;
	@Autowired
	private UserRepository clienteRepository;
	@Autowired
	private MedicamentRepository enderecoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);
		
		est1.getCitys().addAll(Arrays.asList(c1));
		est2.getCitys().addAll(Arrays.asList(c2, c3));

		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
	}
}
