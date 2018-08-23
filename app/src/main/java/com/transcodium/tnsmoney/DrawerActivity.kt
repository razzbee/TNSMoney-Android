package com.transcodium.tnsmoney

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.transcodium.app.DrawerListAdapter
import com.transcodium.app.DrawerListModel
import kotlinx.android.synthetic.main.app_bar.*
import org.jetbrains.anko.find

open class DrawerActivity : AppCompatActivity() {


    //lazy init vals
    private val toolbar: Toolbar by lazy{
        findViewById<Toolbar>(R.id.toolbar)
    }


    private val drawerLayout: DrawerLayout by lazy{
        findViewById<DrawerLayout>(R.id.drawer_layout)
    }

    private val navView: NavigationView by lazy{
        findViewById<NavigationView>(R.id.navView)
    }

    private  val contentView: View by lazy{
        findViewById<CoordinatorLayout>(R.id.contentView)
    }

    val context: Context by lazy {
        this
    }

    private val mDrawerListView by lazy{
        find<ListView>(R.id.drawerListView)
    }


    private lateinit var drawerToggle: ActionBarDrawerToggle

    /**
     * check if user is logged in, since
     * this is almost inherited by most activities requiring login
     */
    override  fun onStart() {
        super.onStart()

        //go to login
        //if(currentUser == null){
        //  startClassActivity(this,LoginActivity::class.java,true)
        //}
    }//end on start

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set toolbar as actionbar
        setSupportActionBar(toolbar)

        //remove elevation
        drawerLayout.drawerElevation = 0f


        //set Header Info
        setDrawerHeaderInfo()

        //set the scrim color (the dark overlay fading when the drawer is opened)
        drawerLayout.setScrimColor(Color.TRANSPARENT)


        //drawer options and events
        drawerToggle = object: ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ){
            //events

            /**
             * onDrawerSlide
             */
            //push content on drawer open
            override fun onDrawerSlide(drawerView: View, offset: Float ){

                contentView.translationX = (offset * drawerView.width)

                super.onDrawerSlide(drawerView, offset)
            }//end


        }//end toggle  options and events


        //lets set drawer listener
        drawerLayout.addDrawerListener(drawerToggle)

        //lets set drawer item click listener
        navView.setNavigationItemSelectedListener{
            item: MenuItem ->
            onNavItemSelected(item)
        }

        //menu data
        val listData = mutableListOf(

                DrawerListModel(
                        R.drawable.ic_mail,
                        getString(R.string.setting),
                        AboutActivity::class.java),

                DrawerListModel(
                        R.drawable.ic_mail,
                        getString(R.string.support),
                        AboutActivity::class.java),

                DrawerListModel(
                        R.drawable.ic_mail,
                        getString(R.string.about),
                        AboutActivity::class.java),

                DrawerListModel(
                        R.drawable.ic_mail,
                        getString(R.string.logout),
                        AboutActivity::class.java)

        )//end list data

        //lets create populate menu list
        mDrawerListView.adapter = DrawerListAdapter(this,listData)
    }//end oncreate



    //on NavItemSelected
    fun onNavItemSelected(item: MenuItem): Boolean{

        /**
        val itemId = item.itemId

        val itemActivity = when(itemId){
        R.id.about ->  AboutActivity::class.java
        R.id.contact_us -> ContactActivity::class.java
        else -> null
        }

        val  curActivityName = this.javaClass.name

        //is Same activity
        val isSameActivity = curActivityName == itemActivity?.name

        //if itemActivity is not null or
        //not the same activity, then open activity
        if(!(itemActivity == null || isSameActivity )){

        //Open activity
        startActivity(Intent(applicationContext,itemActivity))
        }


        //close drawer
        drawerLayout.closeDrawer(GravityCompat.START)
         */

        return true
    }//end

    //onOptionItemSelected
    override fun onOptionsItemSelected(item: MenuItem):Boolean{

        when(item.itemId){
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }

        //if item
        if(drawerToggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }//end


    /**
     * onPostCreate
     */
    override fun onPostCreate(savedInstanceState: Bundle?){
        super.onPostCreate(savedInstanceState)

        //sync state of toggle
        drawerToggle.syncState()
    }//end


    /**
     * onconfiguration change tool.. we should
     * change the toggle
     */
    override fun onConfigurationChanged(newConfig: Configuration){
        super.onConfigurationChanged(newConfig)

        //onConfigurationChanged
        drawerToggle.onConfigurationChanged(newConfig)
    }//end


    //set Header info
    private fun setDrawerHeaderInfo(){

        /*/lets get profile phot
        val photoUrl = currentUser?.photoUrl

        //if not empty lets update it
        if(photoUrl != null){

            //using glide insert pic
            Picasso.with(this)
                    .load(photoUrl)
                    .placeholder(R.drawable.ic_user)
                    .error(R.drawable.ic_user)
                    .resize(
                            photoViewParent.layoutParams.width,
                            photoViewParent.layoutParams.height
                    )
                    .into(photoView)
        }//end if photo

        //get user Phone
        val userPhoneNo = currentUser?.phoneNumber

        //user email
        val userEmail = currentUser?.email

        //provider
        val provider = currentUser?.providers

        var emailOrPhone = ""

        var displayName = currentUser?.displayName

        //set display name
        if(displayName != null){
            nameView.text = displayName
        }

        if(userEmail != null){
            emailOrPhone = userEmail
        }else if(userPhoneNo != null){
            emailOrPhone = userPhoneNo
        }

        //set the textView
        emailOrPhoneView.text = emailOrPhone

        //if the provider isnt empty
        if(provider != null){

            var providerName = provider[0].capitalize()

            //sometimes the provider name is provided by a domain
            //just remove the domain ext
            providerName = providerName.split(".")[0]

            //auth by
            val authBy = getString(R.string.auth_by)

            authByView.text = "$authBy $providerName"
        }//end

        */
    }//end set header info


}
