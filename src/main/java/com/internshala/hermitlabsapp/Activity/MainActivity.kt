package com.internshala.hermitlabsapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.internshala.hermitlabsapp.*
import com.internshala.hermitlabsapp.Fragment.*
import com.internshala.hermitlabsapp.R

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var navigationView: NavigationView
    lateinit var frameLayout: FrameLayout
    var previousMenuItem:MenuItem?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar)
        coordinatorLayout=findViewById(R.id.coordinatorLayout)
        drawerLayout=findViewById(R.id.drawerLayout)
        navigationView=findViewById(R.id.navigationView)
        frameLayout=findViewById(R.id.Frame)
        setUpToolbar()
        opendashboard()
        drawerLayout.closeDrawers()
        val actionBarDrawerToggle=ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer

        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            if (previousMenuItem!=null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it
            when(it.itemId){
                R.id.home ->{
                    opendashboard()
                    drawerLayout.closeDrawers()
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.Frame, ProfileFragment())
                        .commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.message ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.Frame, MessageFragment())
                    supportActionBar?.title="Message"
                    drawerLayout.closeDrawers()
                }
                R.id.settings ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.Frame, SettingFragment())
                        .commit()
                    supportActionBar?.title="Setting"
                    drawerLayout.closeDrawers()
                }
                R.id.logout ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.Frame, LogoutFragment())
                        .commit()
                    supportActionBar?.title="logout"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true


        }

    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Tega App"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun opendashboard(){
        val fragment= HomeFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Frame, HomeFragment())
        transaction.commit()
        supportActionBar?.title="Tega App"
        navigationView.setCheckedItem(R.id.home)
    }

    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.Frame)
        when(frag){
            !is HomeFragment ->opendashboard()
            else -> super.onBackPressed()
        }

    }

}