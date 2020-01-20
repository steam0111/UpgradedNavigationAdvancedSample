package com.example.android.navigationadvancedsample.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigationadvancedsample.MainActivity
import com.example.android.navigationadvancedsample.MainActivityWithNavigation

import com.example.android.navigationadvancedsample.R
import com.example.android.navigationadvancedsample.setupWithNavController
import kotlinx.android.synthetic.main.activity_nav_main.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        setupBottomNavigationBar()
    }


    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = bottom_nav

        val navGraphIds = listOf(R.navigation.home, R.navigation.list, R.navigation.form)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = childFragmentManager,
                containerId = R.id.nav_host_container,
                intent = requireActivity().intent
        )

        controller.observe(this, Observer { navController ->
            Log.d(MainActivity.TAG, "MainActivity controller ${navController.currentDestination?.id}")
            NavigationUI.setupWithNavController((activity as MainActivityWithNavigation).toolbar, navController)
        })

        currentNavController = controller
    }
}
