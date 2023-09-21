package br.com.gabriel.api.services;

import br.com.gabriel.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
