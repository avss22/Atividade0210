package br.edu.ifgoias.academico.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> procurarPorId(@PathVariable Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        return disciplina.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Disciplina inserir(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> alterar(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        return disciplinaRepository.findById(id).map(d -> {
            d.setNome(disciplina.getNome());
            d.setDescricao(disciplina.getDescricao());
            Disciplina updated = disciplinaRepository.save(d);
            return ResponseEntity.ok(updated);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return disciplinaRepository.findById(id).map(d -> {
            disciplinaRepository.delete(d);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
