package ru.innopolis.baki.currencyconv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.baki.currencyconv.XMLParce;
import ru.innopolis.baki.currencyconv.models.Valute;
import ru.innopolis.baki.currencyconv.repositories.ValuteRepository;

import java.util.Date;
import java.util.List;

@Service
public class ValuteService {
    @Autowired
    private ValuteRepository valuteRepository;

    public Valute saveValute(Valute valute){
        return valuteRepository.save(valute);
    }

    public void deleteAllValues(){
        valuteRepository.deleteAll();
    }

    public Valute findById(String id){
        return valuteRepository.getOne(id);
    }

    public List<Valute> findAll() {
        List<Valute> valutes = valuteRepository.findAll();
        if (valutes.size() == 0 || valutes.get(0).getDate().getDay() < (new Date().getDay())) {
            XMLParce xmlParce = new XMLParce();
            List<Valute> res = xmlParce.xmlParcer();
            for (Valute valute : res){
                saveValute(valute);
            }
            return valutes;
        } else {
        return valutes;
        }
    }
}

