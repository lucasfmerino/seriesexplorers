package br.com.projects.seriesexplorers.service;

public interface IDataConverter {
    <T> T getData(String json, Class<T> projectClass);
}
