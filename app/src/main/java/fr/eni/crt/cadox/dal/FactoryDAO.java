package fr.eni.crt.cadox.dal;

import fr.eni.crt.cadox.dal.room.AppDatabase;

public abstract class FactoryDAO
{
    public static Dal getImplementation()
    {
//        return DAOImp.getReference();
        return AppDatabase.getInstance().getDal();
    }
}
