package br.com.itpacpalmas.api_sig_lab_itpac.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.itpacpalmas.api_sig_lab_itpac.config.FileStorageConfig;
import br.com.itpacpalmas.api_sig_lab_itpac.entities.VO.ManualResponseVO;
import br.com.itpacpalmas.api_sig_lab_itpac.services.ManualService;


// https://www.youtube.com/watch?v=DtC_KfU6b1o
@RestController
@RequestMapping("api/manual")
@CrossOrigin
public class ManualController {
    
	private ManualService servises = new ManualService(new FileStorageConfig());
    
    @GetMapping()
    public  ResponseEntity<List<ManualResponseVO>> getInfo() {
        return ResponseEntity.ok().body(servises.buscarTodosInfo());
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ManualResponseVO> getInfoById(@PathVariable int id) {
        return ResponseEntity.ok().body(servises.buscarbyIdInfo(id));
    }
    
    @GetMapping("/doc/{id}")
    public ResponseEntity<?> getDocById(@PathVariable int id, HttpServletRequest request) {
        return servises.downloadFile(id, request);
    }
    
    @PostMapping
	public ManualResponseVO uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("descricao") String descricao) {
        return servises.uploadFile(file,descricao);
    }
    
    @PatchMapping("/updateDescricao/{id}")
    public ManualResponseVO update(@PathVariable int id,@RequestParam("descricao")String descricao) {
        return servises.update(id, descricao);
    }

    @PatchMapping("/desativar/{id}")
    public ManualResponseVO desativar(@PathVariable int id) {
        return servises.desativar(id);
    
    }

    @PatchMapping("/ativar/{id}")
    public ManualResponseVO ativar(@PathVariable int id) {
        return servises.ativar(id);
    }

   

}
