package fr.eni.crt.cadox.dal;

public abstract class FactoryDAO
{
    public static Dal getImplementation()
    {
        return DAOImp.getReference();
    }
}
