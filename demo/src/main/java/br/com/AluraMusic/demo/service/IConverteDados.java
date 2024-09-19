package br.com.AluraMusic.demo.service;

public interface IConverteDados {
    <T> T converteDados(String json, Class<T> classe);
}
