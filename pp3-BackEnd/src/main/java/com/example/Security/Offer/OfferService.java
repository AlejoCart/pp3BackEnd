package com.example.Security.Offer;

import com.example.Security.Entities.Modality;
import com.example.Security.Entities.Offer;
import com.example.Security.Entities.User;
import com.example.Security.Repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public ResponseEntity<List> findAll(){
        return
                (ResponseEntity<List>) offerRepository.findAll();
    }

    public ResponseEntity<com.example.Security.Entities.Offer> findByID(Long id){
        if(id>0 && id<offerRepository.count())
        return ResponseEntity.of(offerRepository.findById(id));

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List> findListOfferByCareer(String career,
                                                      String username){
        /*
        var offset=offerRequest.getOffset();
        var limit=offerRequest.getLimit();*/
        //Pageable ofertasEnPagina = PageRequest.of(offset,offset+limit);
        List<com.example.Security.Entities.Offer> listaOfertas;
        try{
        listaOfertas = offerRepository.findListOfferByCareer(
            career,username/*, 0,0+10*/
            );}
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        Optional<List> aux= Optional.ofNullable(listaOfertas);
        return ResponseEntity.of(aux);
    }

    public ResponseEntity<com.example.Security.Entities.Offer> create(Offer offerRequest) {

        if(Arrays.stream(offerRequest.getClass().getFields()).anyMatch(null))
            return ResponseEntity.badRequest().build();

        var aux= com.example.Security.Entities.Offer.builder()
                        .id_offer(null)
                        .title(offerRequest.getTitle())
                        .id_company(offerRequest.getId_company())
                        .description(offerRequest.getDescription())
                        .career(offerRequest.getCareer())
                        .text(offerRequest.getText())
                        .modality(offerRequest.getModality())
                        .build();
        offerRepository.save(aux);

        return ResponseEntity.ok(aux);
    }

    public ResponseEntity delete(Long id) {

        if(id<0 && id> offerRepository.count()){
            offerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<com.example.Security.Entities.Offer> update(Offer updateOfferRequest) {

        /*if(Arrays.stream(updateOfferRequest.getClass().getFields()).anyMatch(null))
            return */

        if(!(offerRepository.findById(updateOfferRequest.getId_offer()).isPresent()))
            return ResponseEntity.badRequest().build();

        if (!(updateOfferRequest.getModality().equals(Modality.class.getEnumConstants())))
            return ResponseEntity.badRequest().build();/*Offer.class.getEnumConstants()*/

        var aux= (com.example.Security.Entities.Offer) updateOfferRequest;
        offerRepository.save(aux);

        return ResponseEntity.ok(offerRepository.findById(updateOfferRequest.getId_offer()).get());

        //return null;
    }

    public ResponseEntity<List> findOfferFromUser(User user){


        return null;
    }
}
