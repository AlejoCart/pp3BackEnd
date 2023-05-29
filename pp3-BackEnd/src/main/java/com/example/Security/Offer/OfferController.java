package com.example.Security.Offer;

import com.example.Security.Entities.Offer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*")
public class OfferController {

    private final OfferService offerService;

    /*@GetMapping("/myCareer/")
    public ResponseEntity<List> findOfferByCareer(@RequestParam /*(value=
            "1")String Career
            ,@RequestParam String username)
            @RequestBody
    FindOfferByCareerRequest offerRequest{
        System.out.println("Adentro del controlador");
        return offerService.findListOfferByCareer(Career,username);
    }*/

    @GetMapping("/test")
    public FindOfferByCareerRequest test(){
        return FindOfferByCareerRequest.builder().offset(0).limit(0).Career("Test").build();
    }

    @GetMapping(value = {"/","/findAll"})
    public ResponseEntity<List<Offer>> findAll(){
        System.out.println("findAll");
        return offerService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<com.example.Security.Entities.Offer> createOffer(@RequestBody Offer offerRequest){
        return offerService.create(offerRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOffer(@RequestParam Long id){
        return offerService.delete(id);
    }

    @PostMapping("/update")
    public ResponseEntity<Offer> updateOffer(@RequestBody Offer updateOfferRequest){
        return offerService.update(updateOfferRequest);
    }




}
