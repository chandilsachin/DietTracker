package com.chandilsachin.diettracker

import android.app.Application
import android.content.res.AssetManager
import com.chandilsachin.diettracker.database.FoodDatabase
import com.chandilsachin.diettracker.database.PersonalizedFood
import com.chandilsachin.diettracker.io.JSONReader
import com.chandilsachin.diettracker.model.Date
import com.chandilsachin.diettracker.other.AddFoodPresenter
import com.chandilsachin.diettracker.view_model.AddFoodViewModel
import com.chandilsachin.diettracker.view_model.FoodDetailsViewModel
import com.chandilsachin.diettracker.view_model.MainActivityModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import java.io.FileInputStream
import java.io.InputStream
import java.util.*
import kotlin.test.assertEquals

/**
 * Created by sachin on 27/5/17.
 */
@RunWith(MockitoJUnitRunner::class)
class DatabaseTest {


    @Mock
    val context: Application? = null
    @Mock
    val assetManager: AssetManager? = null

    @Before
    fun beforeTest() {
        FoodDatabase.TEST_MODE = true
        Mockito.`when`(context?.assets).thenReturn(assetManager)
        Mockito.`when`(assetManager?.open("food_Items.json")).thenReturn(
                javaClass.classLoader.getResourceAsStream("food_Items.json")
        )

        mainActivityModel = MainActivityModel(context!!)
        addFoodModel = AddFoodViewModel(context)


    }

    @After
    fun afterTest() {

    }

    var mainActivityModel: MainActivityModel? = null
    var addFoodModel: AddFoodViewModel? = null

    @Test
    fun initDbTest() {
        // load data from json to sqlite
        mainActivityModel?.prepareInitDatabase()
        assertEquals(addFoodModel!!.allFoodList.value?.size,
                AddFoodPresenter().readFoodItemsFile(context!!).size)
    }

    //@Test
    fun saveFoodTest() {

        mainActivityModel?.prepareInitDatabase()
        var foodDetailsModel = FoodDetailsViewModel(context!!)
        var list = addFoodModel!!.allFoodList
        var f1 = PersonalizedFood(1, "unit", Date())
        f1.foodId = list.value!!.get(0).id
        foodDetailsModel.saveFoodDetails(f1)
        f1 = PersonalizedFood(1, "unit", Date())
        f1.foodId = list.value!!.get(1).id
        foodDetailsModel.saveFoodDetails(f1)
        assertEquals(mainActivityModel!!.personalisedFoodList.value?.size, 2)

    }
}