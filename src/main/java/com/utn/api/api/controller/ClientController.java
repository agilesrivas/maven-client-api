package com.utn.api.api.controller;

import com.utn.api.api.models.User;
import com.utn.api.api.persistence.ClientRepository;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.NamedQueries;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/cliente",produces="application/json")
public class ClientController {

    @Autowired
    private ClientRepository repo;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        return repo.findAll();
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity newCliente(@RequestHeader("User-Agent") String request) {
        UserAgent agent = UserAgent.parseUserAgentString(request);
        String brow = String.valueOf(agent.getBrowser());
        String system = String.valueOf(agent.getOperatingSystem());
        Date fecha = new Date();
        SimpleDateFormat fort = new SimpleDateFormat("dd/MM/yyyy");
        User nw;
        nw = new User(brow, system, fort.format(fecha).toString());
        nw.setName("Usuario");
        repo.save(nw);

        return new ResponseEntity(nw.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/browser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getByBrowser(@PathVariable("id") Long id) {
        ResponseEntity mensaje;
        Optional<User> us = repo.findById(id);

        if (us.getClass() != null) {
            mensaje = new ResponseEntity(us.get().getMyBrowser(), HttpStatus.OK);
        } else {
            mensaje = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return mensaje;
    }

    @RequestMapping(value = "/System/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getMySystem(@PathVariable("id") Long id) {
        ResponseEntity mensaje;
        Optional<User> us = repo.findById(id);

        if (us.getClass() != null) {
            mensaje = new ResponseEntity(us.get().getMySystem(), HttpStatus.OK);
        } else {
            mensaje = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return mensaje;
    }

    @RequestMapping(value="/BrowserAndSystem/fecha",method = RequestMethod.GET)
    @ResponseBody
    public List<User> getBrowAndSystemFecha()
    {
        String fecha="";
        Date fechaHoy=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        fecha=sdf.format(fechaHoy);

        return repo.getByFecha(fecha);
    }

    @RequestMapping(value = "/browser/top", method = RequestMethod.GET)
    @ResponseBody
    public String getMaxTopBrowser()
    {
        return repo.getTopBrowser();
    }


    @RequestMapping(value = "/system/top", method = RequestMethod.GET)
    @ResponseBody
    public String getMaxTopSystem()
    {
        return repo.getTopSystem();
    }
}








