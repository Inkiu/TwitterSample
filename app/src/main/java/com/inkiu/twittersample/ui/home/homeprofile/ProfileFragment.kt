package com.inkiu.twittersample.ui.home.homeprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.inkiu.twittersample.R
import com.inkiu.twittersample.ui.base.BaseFragment
import com.inkiu.twittersample.ui.home.hometweet.HomeTweetsFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : DaggerFragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileRecycler.adapter = Adapter()
    }
}

class Adapter : RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return LayoutInflater.from(parent.context).inflate(
            R.layout.item_network_state,
            parent,
            false
        ).let {
            Holder(it)
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        /* no-op */
    }
}

class Holder(view: View) : RecyclerView.ViewHolder(view) {

}
