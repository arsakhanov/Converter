package ru.innopolis.baki.currencyconv;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.innopolis.baki.currencyconv.models.User;
import ru.innopolis.baki.currencyconv.models.Valute;
import ru.innopolis.baki.currencyconv.repositories.UserRepository;
import ru.innopolis.baki.currencyconv.services.ValuteService;

import java.util.List;

@SpringBootTest
class CurrencyconvApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	ValuteService valuteService;

	@Test
	public void mainTest() {
		Valute valute = new Valute(123, "ACC", 1, "Российский рубль", 25.0);
		valuteService.saveValute(valute);
	}

	@Test
	public void xmlTest(){
		XMLParce xmlParce = new XMLParce();
		List<Valute> valutes = xmlParce.xmlParcer();
		for (Valute valute : valutes){
			valuteService.saveValute(valute);
		}
	}

	@Test
	public void deleteALL(){
		valuteService.deleteAllValues();
	}

	@Autowired
	private UserRepository userRepo;
	@Test
	public void testUser(){
		User u = new User("Nur", "12345");
		userRepo.save(u);
	}
}
