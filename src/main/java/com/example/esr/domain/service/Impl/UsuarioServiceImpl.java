package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.EmailJaCadastadoException;
import com.example.esr.domain.exception.NegocioException;
import com.example.esr.domain.exception.UsuarioNaoEncontradoException;
import com.example.esr.domain.model.Usuario;
import com.example.esr.domain.repository.UsuarioRepository;
import com.example.esr.domain.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final EntityManager manager;

    public static final String MSG_EMAIL_JA_CADASTRADO = "Já existe um cadastro de usuário com esse email %s.";

    public UsuarioServiceImpl(UsuarioRepository repository, EntityManager manager) {
        this.repository = repository;
        this.manager = manager;
    }


    @Override
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Usuario salvar(Usuario usuario) {

        manager.detach(usuario);

        //verifica se o email já existe
        var usuarioExistente = repository.findByEmail(usuario.getEmail());

        //verifica se existe um usuário com esse email e se é diferente do usuário que vem no parâmetro
        if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)){
            throw new EmailJaCadastadoException(String.format(MSG_EMAIL_JA_CADASTRADO, usuario.getEmail()));
        }

        return repository.save(usuario);
    }

    @Transactional
    @Override
    public void atualizarSenha(Long id ,String senhaAtual, String novaSenha) {

        var usuario = this.buscarOuFalhar(id);

        //Valida se a senha atual é a mesma cadastrada no usuario
        if(!senhaAtual.equals(usuario.getSenha())){

            throw new NegocioException("Senha atual não coincide com a senha do usuário.");

        }

        usuario.setSenha(novaSenha);


    }

    @Override
    public Usuario buscarOuFalhar(Long id) {

        return repository.findById(id)
                .orElseThrow(()-> new UsuarioNaoEncontradoException(id));

    }


}
