package getionRh.example.rh.controller;


import getionRh.example.rh.entity.Pays;
import getionRh.example.rh.service.PaysService;
import getionRh.example.rh.service.implementation.PaysServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pays")
@CrossOrigin(origins = "*")
public class PaysController {

    @Autowired
    private PaysServiceImpl paysService;


    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(paysService.getAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }


    @GetMapping("/list/{designation}")
    public Pays getPaysByDesignation(@PathVariable String designation) {
        return paysService.getDesignation(designation);
    }
}
