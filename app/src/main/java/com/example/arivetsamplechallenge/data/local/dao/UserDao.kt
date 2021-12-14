package com.example.arivetsamplechallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.arivetsamplechallenge.api_response.UsersInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
        (onConflict = REPLACE)
    fun addUsersDetails(userModel: List<UsersInfo>?): Array<Long?>?
    @Query("SELECT * FROM users  LIMIT :size ")
    abstract suspend fun getUsersDataList(size: Int): List<UsersInfo>

    @Query("SELECT * FROM users  LIMIT :size ")
    abstract  fun getRandomPosts1(size: Int): Flow< List<UsersInfo>>

    @Query("SELECT * FROM users  WHERE myId=:id ")
    abstract suspend fun getSingleUserDetail(id: Long): UsersInfo
}
/*
*  @Query("SELECT * FROM " + CountryModel.TABLE_NAME +" WHERE isoCode =:iso_code LIMIT 1")
    LiveData<List<CountryModel>> getCountry(String iso_code);

    @Query("SELECT * FROM " + CountryModel.TABLE_NAME )
    LiveData<List<CountryModel>> getAllCountriesInfo();

    @Insert(onConflict = REPLACE)
    Long[] addCountries(List<CountryModel> countryModel);

    @Delete
    void deleteCountry(CountryModel... countryModel);

    @Update(onConflict = REPLACE)
    void updateEvent(CountryModel... countryModel);*/
/*
* return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database")
            .createFromAsset("database/bus_schedule.db")
            .build()
        INSTANCE = instance

        instance
    }
}*/