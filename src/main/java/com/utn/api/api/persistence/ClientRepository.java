package com.utn.api.api.persistence;

import com.utn.api.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public interface ClientRepository extends JpaRepository<User, Long> {


   @Query(value = "Select * from user where fecha=:fecha",nativeQuery = true)
    public List<User> getByFecha(@Param("fecha") String fecha);


    @Query(value = "Select my_browser,my_system ,COUNT(*) from user group by my_browser,my_system order by COUNT(*) desc limit 0,1",nativeQuery = true)
    public List<?> getTopBrowserandSystem();

    @Query(value = "Select my_browser,COUNT(my_browser) from user group by my_browser order by COUNT(my_browser)desc limit 1",nativeQuery = true)
    public String getTopBrowser();

    @Query(value = "Select my_system,COUNT(my_system) from user group by my_system order by COUNT(my_system)desc limit 1",nativeQuery = true)
    public String getTopSystem();


}
