package ru.innopolis.baki.currencyconv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.baki.currencyconv.models.Valute;

public interface ValuteRepository extends JpaRepository<Valute, String> {
}

