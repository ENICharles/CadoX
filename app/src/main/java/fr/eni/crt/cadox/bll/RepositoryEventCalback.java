package fr.eni.crt.cadox.bll;

public interface RepositoryEventCalback<T>
{
    void onComplete(T data);
}
