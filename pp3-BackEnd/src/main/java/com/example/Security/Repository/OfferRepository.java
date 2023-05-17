package com.example.Security.Repository;

import com.example.Security.Entities.Offer;
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
    @Query(nativeQuery = true, value =
            "SELECT * FROM Offer  WHERE Career = ? ORDER BY id DESC " +
                    "LIMIT ? OFFSET ?")
    /*"SELECT * FROM Offer  WHERE Career = ?1career ORDER BY id DESC " +
                    "LIMIT ?1limit OFFSET ?1offset")*/
    List<Offer> findListOfferByCareer(@Param("career") String career,
                                      @Param("offset") int offset,
                                      @Param("limit") int limit,
                                      Pageable pageable);

}
