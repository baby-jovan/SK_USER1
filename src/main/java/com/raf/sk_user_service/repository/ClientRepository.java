package com.raf.sk_user_service.repository;

import com.raf.sk_user_service.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Ovde obezbadjujemo CRUD operacije, u <> zagradama se definise: 1. mesto je tabela na koju se odnosi, 2. mesto je tip koji odgovara id-u
@Repository/// ovo sluzi da bi spring instancirao objekat pomocu kog ce da izvrsava CRUD operacije nad tabelom product
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByUsernameAndPassword(String username, String password); // Convention over configuration, umesto rucno da se implementira
    // ovde se tekstom naglasava sta tacno zelimo da radi metoda, Optional je tip koji obezbedjuje da ukoliko se nista ne vrati da ovaj client moze da bude prazn
}
