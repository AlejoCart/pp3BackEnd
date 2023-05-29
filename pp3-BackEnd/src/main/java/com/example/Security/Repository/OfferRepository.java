package com.example.Security.Repository;

import com.example.Security.Entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<com.example.Security.Entities.Offer,Long> {

    @Override
    Optional<Offer> findById(Long aLong);

    /*@Override
    <S extends Offer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);*/

    /*@Query(nativeQuery = true,
            value = "SELECT * FROM offer WHERE title = ?")*/
    Optional<Offer> findByTitle(String title);

    Optional<Offer> findByCareer(String career);

    //Optional<Offer>

    @Query(value="SELECT * FROM offer"/*
            " join `user` on user_offer.id_user =`user`.id_user where " +
            "(offer.offer_career = ?1 and `user`.NAME = ?2);"*/,
            nativeQuery =true)
    List<Offer> findListOfferByCareer(String career,
                                      String username /*int offset,
                                      int upperLimit*/);
    @Query(value = "Select * from offer", nativeQuery = true)
    List<Offer> findOffer();
}
