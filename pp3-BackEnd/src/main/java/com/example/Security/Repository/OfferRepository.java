package com.example.Security.Repository;

import com.example.Security.Entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer,Long> {

    @Override
    Optional<Offer> findById(Long aLong);

    /*@Override
    <S extends Offer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);*/

    /*@Query(nativeQuery = true,
            value = "SELECT * FROM offer WHERE title = ?")*/
    Optional<Offer> findByTitle(String title);

    Optional<Offer> findByCareer(String career);

    //Optional<Offer>

    @Query(value="SELECT title,company_id,description,offer_career,text," +
            "modality" +
            " " +
            "FROM offer JOIN user_offer ON offer.id  = user_offer.Offer_ID  " +
            "join " +
            "`user`" +
            " on user_offer.user_ID =user.ID \n" +
            "where (offer_career = ?1 and user.NAME = ?2);",
            nativeQuery =
                    true)
    /*SELECT * FROM Offer JOIN Customers ON Orders" +
            ".CustomerID=Customers.CustomerID where career = ?1 and  limit ?2,?3*/
        /**/
    List<Offer> findListOfferByCareer(String career,
                                      String username /*int offset,
                                      int upperLimit*/);
}
