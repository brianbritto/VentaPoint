package com.lucky.android.ventapoint.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lucky.android.ventapoint.db.dao.PointDao;
import com.lucky.android.ventapoint.db.dao.ProductDao;
import com.lucky.android.ventapoint.db.dao.UserDao;
import com.lucky.android.ventapoint.db.entity.Point;
import com.lucky.android.ventapoint.db.entity.Product;
import com.lucky.android.ventapoint.db.entity.ProductPoint;
import com.lucky.android.ventapoint.db.entity.ProductUser;
import com.lucky.android.ventapoint.db.entity.User;

@Database(entities = {User.class, Product.class,
                      Point.class, ProductPoint.class,
                      ProductUser.class},
          version = 1)
public abstract class AppDataBase extends RoomDatabase{

    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    public abstract PointDao pointDao();

    private static final String DB_NAME = "repoDatabase.db";
    private static volatile AppDataBase instance;


    public static synchronized AppDataBase getDatabase(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    @NonNull
    private static AppDataBase create(final Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, DB_NAME)
                .build();
    }
}
