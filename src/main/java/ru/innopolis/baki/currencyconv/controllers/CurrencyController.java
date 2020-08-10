package ru.innopolis.baki.currencyconv.controllers;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.baki.currencyconv.models.History;
import ru.innopolis.baki.currencyconv.models.Valute;
import ru.innopolis.baki.currencyconv.services.HistoryService;
import ru.innopolis.baki.currencyconv.services.ValuteService;


import java.util.List;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    ValuteService valuteService;

    @Autowired
    HistoryService historyService;

    @GetMapping
    public String convertCurrency(Model model, History history) {
        List<Valute> valuteList = valuteService.findAll();
        List <History> histories =  historyService.findAll();
        model.addAttribute("currencyList", valuteList);
        model.addAttribute("historyList", histories);
        if(history.getId() != null) {
            History hist = historyService.saveHistory(history);
            model.addAttribute("history", history);
        }
        return "currency";
    }


}
