package com.alurachallenge.LiterAlura_Challenge.config.IConfig;

public interface IConvertirDatos {
    <T> T convertirDatosJson(String json, Class<T> clase);
}
