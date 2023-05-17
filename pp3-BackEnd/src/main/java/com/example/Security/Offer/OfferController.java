package com.example.Security.Offer;

import com.example.Security.Entities.Offer;
import com.example.Security.Request.OfferRequest;
import com.example.Security.Service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Offer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/findOfferByCareer")
    public ResponseEntity<List> findOfferByCareer(@RequestBody OfferRequest offerRequest){
        return offerService.findOfferByCareer(offerRequest);
    }

    @GetMapping("/test")
    public OfferRequest test(){
        return OfferRequest.builder().offset(0).limit(0).Career("Test").build();
    }


}
