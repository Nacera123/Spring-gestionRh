package getionRh.example.rh.controller;


import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.CiviliteService;
import getionRh.example.rh.service.implementation.PaysServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/civilite")
public class CiviliteController {

    @Autowired
    private CiviliteService civiliteService;


    @GetMapping("/list")
    public ResponseEntity<?> getAllCivilite(){
        try {
            return ResponseEntity.ok(civiliteService.getAll());
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }


    @GetMapping("/{designation}")
    public ResponseEntity getByDesignation(@PathVariable String designation){
        try {
            return ResponseEntity.ok(civiliteService.getByDesignation(designation));
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }
}
