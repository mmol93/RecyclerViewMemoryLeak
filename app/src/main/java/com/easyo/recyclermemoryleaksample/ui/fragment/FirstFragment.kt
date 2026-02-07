package com.easyo.recyclermemoryleaksample.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyo.recyclermemoryleaksample.R
import com.easyo.recyclermemoryleaksample.databinding.FragmentFirstBinding
import com.easyo.recyclermemoryleaksample.ui.adapter.BadRecyclerAdapter

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        BadRecyclerAdapter { position ->
            findNavController().navigate(R.id.action_main_to_second)
            Toast.makeText(requireContext(), "Clicked $position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@FirstFragment.adapter
        }

        val items = List(300) { "Item ${it + 1}" }
        adapter.submitList(items)
    }

    override fun onDestroyView() {
        // 使わないadapterは無効にする
        binding.recyclerView.adapter = null

        super.onDestroyView()
        _binding = null
    }
}