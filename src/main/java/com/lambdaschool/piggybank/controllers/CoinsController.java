package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinsController {

    @Autowired
    private CoinsRepository coinsRepository;

    //http://localhost:2019/coins/total
    

    @GetMapping(value = "/coins/total", produces = "application/json")
    public ResponseEntity<?> listCoinTotal(){
        List<Coin> coinList = new ArrayList<>();
        coinsRepository.findAll().iterator().forEachRemaining(coinList::add);

        double priceTotal = 0.0;
        for (Coin c: coinList){
            priceTotal += (c.getValue() * c.getQuantity());
        }

        for(Coin c: coinList){
            if(c.getQuantity() > 1){
                System.out.println(c.getQuantity() + " " + c.getNameplural());
            } else {
                System.out.println(c.getQuantity() + " " + c.getName());
            }
        }

        System.out.println("The piggy bank holds " + priceTotal);

        return new ResponseEntity<>(priceTotal, HttpStatus.OK);
    }

}
