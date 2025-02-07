package org.formation.tp6restclient.service;

import com.fasterxml.jackson.annotation.JsonAlias;

public record User(@JsonAlias("nom") String name, String password, String email) {
}
