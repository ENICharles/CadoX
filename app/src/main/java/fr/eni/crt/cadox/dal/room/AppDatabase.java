package fr.eni.crt.cadox.dal.room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import fr.eni.crt.cadox.ApplicationContext;
import fr.eni.crt.cadox.bo.Article;
import fr.eni.crt.cadox.dal.Dal;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
@TypeConverters({LocalDateConverters.class,BooleanConverters.class})
public abstract class AppDatabase extends RoomDatabase
{
    public abstract Dal getDal();

    //Mise en place du Singleton d'accès à la base de données
    private static AppDatabase instance = null;

    public static AppDatabase getInstance()
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(ApplicationContext.getContext(),
                    AppDatabase.class, "room-Article-database.db")
                    .build();
        }

        return instance;
    }
}
