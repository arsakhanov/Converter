package ru.innopolis.baki.currencyconv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.innopolis.baki.currencyconv.models.History;
import ru.innopolis.baki.currencyconv.models.User;
import ru.innopolis.baki.currencyconv.models.Valute;
import ru.innopolis.baki.currencyconv.repositories.HistoryRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    ValuteService valuteService;

    public History findById(Long id){
        return historyRepository.getOne(id);
    }

    public List<History> findAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        long userId = user.getId();
        List<History> histories = historyRepository.findAllByUserID(userId);
        for (History history: histories) {
            history.setOriginalCurrencyName(valuteService.findById(history.getOriginalCurrency()).getName());
            if (!history.getTargerCurrency().equals("1")) {
                history.setTargetCurrencyName(valuteService.findById(history.getTargerCurrency()).getName());
            }else {history.setTargetCurrencyName("RUB(Российский рубль)");}
        }
        return histories;
    }

    public History saveHistory(History history) {
        Valute originalValute = valuteService.findById(history.getOriginalCurrency());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        long userId = user.getId();
        double result = Double.parseDouble(history.getInitialAmount()) * originalValute.getValue();
        if(!history.getTargerCurrency().equals("1")){
            Valute targetValute = valuteService.findById(history.getTargerCurrency());
            result = result / targetValute.getValue();
        }
        history.setUserID(userId);
        BigDecimal resultBD = BigDecimal.valueOf(result).setScale(4, RoundingMode.HALF_UP);
        history.setAmountReceived(String.valueOf(resultBD));
        history.setDate(LocalDate.now());
        historyRepository.save(history);
        return history;
    }

    public void deleteALl(){
        historyRepository.deleteAll();
    }

}
