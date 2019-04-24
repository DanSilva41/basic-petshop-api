package br.com.silva.petshop.resource;

import br.com.silva.petshop.domain.Especie;
import br.com.silva.petshop.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciamento de espécies.
 * Esta classe acessa a entidade Especie e pode realizar operações necessárias.
 */
@RestController
@RequestMapping("/api/especies")
public class EspecieResource {

    @Autowired
    private EspecieService especieService;

    /**
     * GET  /api/especies : buscar todas as espécies.
     *
     * @return status 200 (OK) e a lista de todas as espécies
     */
    @GetMapping
    public List<Especie> listar() {
        return this.especieService.buscarTodos();
    }

    /**
     * GET  /api/especies/{codigo} : buscar espécie pelo código.
     *
     * @param codigo o código da espécie a ser buscada
     * @return a ResponseEntity com status 201 (Criado) e com o corpo da espécie correspondente
     */
    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Especie> especieSalva = this.especieService.buscarPorCodigo(codigo);
        return especieSalva.isPresent() ? ResponseEntity.ok(especieSalva.get()) : ResponseEntity.notFound().build();
    }

    /**
     * POST  /api/especies : Cria uma nova espécie.
     *
     * @param especie a espécie a ser criada
     * @return a ResponseEntity com status 201 (Criado) e com o corpo da nova espécie
     * @throws URISyntaxException se a sintaxe do URI de localização estiver incorreta
     */
    @PostMapping
    public ResponseEntity<Especie> cadastrar(@RequestBody @Valid Especie especie) throws URISyntaxException {
        Especie especieSalva = this.especieService.salvar(especie);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(especieSalva.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(especieSalva);
    }
}
