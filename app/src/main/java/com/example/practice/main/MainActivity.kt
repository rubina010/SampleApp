package com.example.practice.main

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.practice.FragmentCategory
import com.example.practice.FragmentContents
import com.example.practice.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), FragmentCategory.NavigationInterface,
    FragmentContents.NavigationInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // activity_main_link_text_view.setMovementMethod(LinkMovementMethod.getInstance())
        getDeviceInformation()
        showFragment(FragmentCategory.newInstance(), "FragmentCategory")
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activity_main_container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    private fun getDeviceInformation() {
        val dn =
            "Android version" + Build.VERSION.RELEASE + "Device name" + Build.MANUFACTURER + Build.MODEL
        Log.i("dkjhfksj", "$dn")
    }

    override fun onCategorySelected(categoryId: String) {
        showFragment(FragmentContents.newInstance(categoryId), "FragmentContents")
    }

    override fun onPostItemClicked(deepLinkUrl: String) {
        var link: String? = null
        if (deepLinkUrl.contains(" ")) {
            link = deepLinkUrl.substring(0, deepLinkUrl.indexOf(" "))
        }
        val uri = Uri.parse(link)
        Log.i("skjfhskjd", "$deepLinkUrl ${Uri.encode(deepLinkUrl)} ${Uri.parse(deepLinkUrl)} $uri")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
