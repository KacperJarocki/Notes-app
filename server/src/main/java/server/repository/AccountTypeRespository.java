package server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.AccountType;

import java.util.Optional;

@Repository
public interface AccountTypeRespository extends JpaRepository <AccountType,Integer> {


    AccountType findByName(String name);


}
