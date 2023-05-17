package com.example.Security.Service;

import com.example.Security.Entities.Offer;
import com.example.Security.Repository.OfferRepository;
import com.example.Security.Request.OfferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public List findAll(){
        return
        offerRepository.findAll();
    }

    public ResponseEntity<Offer> findByID(Long id){
        if(id>0 && id<offerRepository.count())
        return ResponseEntity.of(offerRepository.findById(id));
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List> findOfferByCareer(OfferRequest offerRequest){
        var career=offerRequest.getCareer();
        var offset=offerRequest.getOffset();
        var limit=offerRequest.getLimit();
        //Pageable ofertasEnPagina = PageRequest.of(offset,offset+limit);
        List<Offer> listaOfertas;
        try{
        listaOfertas = offerRepository.findListOfferByCareer(
            career, offset,offset+limit
            );}
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        Optional<List> aux= Optional.ofNullable(listaOfertas);
        return ResponseEntity.of(aux);
    }

}
