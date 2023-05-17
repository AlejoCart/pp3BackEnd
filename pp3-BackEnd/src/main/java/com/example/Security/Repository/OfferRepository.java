package com.example.Security.Repository;

import com.example.Security.Entities.Offer;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query(value="SELECT * FROM Offer where career = ?1 limit ?2,?3",
            nativeQuery =
            true)
    /*WHERE
    career = ?1 ORDER BY Id DESC " +
                    "LIMIT ?3 OFFSET ?2"*/
    /*"SELECT * FROM Offer  WHERE Career = ?1career ORDER BY id DESC " +
                    "LIMIT ?1limit OFFSET ?1offset")*/
    List<Offer> findListOfferByCareer(String career,
                                      int offset,
                                      int upperLimit);

}
