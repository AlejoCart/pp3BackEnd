package com.example.Security.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {
    private String Career;
    private int limit;//Cantidad de ofertas solicitadas
    private int offset;//num de pagina * cantidad de ofertas


}
