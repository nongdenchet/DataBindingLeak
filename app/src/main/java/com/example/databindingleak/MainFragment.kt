package com.example.databindingleak

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import com.example.databindingleak.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private val vm = MainViewModel()
    private var binding: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            MainFragmentBinding.inflate(LayoutInflater.from(requireActivity()), container, false)
        binding?.vm = vm
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.description.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.d("Test", this@MainFragment.toString() + " description:" + vm.description.get())
            }
        })
        binding?.btnNext?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFragment, NextFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
