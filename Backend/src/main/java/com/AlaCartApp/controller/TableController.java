package com.AlaCartApp.controller;

import com.AlaCartApp.models.request.TableEntityDto;
import com.AlaCartApp.service.abstraction.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;

    @PostMapping
    public ResponseEntity<TableEntityDto> create (@RequestBody TableEntityDto tableDto){
        if(tableService.isTableIdDuplicate(tableDto.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
            return tableService.create(tableDto).map(t -> new ResponseEntity<>(t, HttpStatus.CREATED))
                    .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TableEntityDto>> getTables (){
       Optional<List<TableEntityDto>> tableListOptional = tableService.tableList();

       if(tableListOptional.isPresent()){
           List<TableEntityDto> tableList = tableListOptional.get();
           return new ResponseEntity<>(tableList, HttpStatus.OK);
       }
           return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableEntityDto> getTable (@PathVariable Long id){
        return tableService.tableId(id).map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/update")
    public ResponseEntity<TableEntityDto> update (@RequestBody TableEntityDto tableDto){
        return tableService.update(tableDto).map(t -> new ResponseEntity<>(t, HttpStatus.ACCEPTED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id){
        Optional<TableEntityDto> currentTableDto = tableService.tableId(id);

        if(currentTableDto.isPresent()){
            tableService.delete(id);
            return new ResponseEntity<>("Se elimino la mesa!", HttpStatus.OK);
        }
            return new ResponseEntity<>("La mesa ingresada no existe.", HttpStatus.NOT_FOUND);
    }

}
